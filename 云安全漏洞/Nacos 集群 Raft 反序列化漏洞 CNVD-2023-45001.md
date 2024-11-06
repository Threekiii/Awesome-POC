# Nacos 集群 Raft 反序列化漏洞 CNVD-2023-45001

## 漏洞描述

该漏洞源于 Nacos 集群处理部分 Jraft 请求时，未限制使用 hessian 进行反 。序列化，攻击者可以通过发送特制的请求触发该漏洞，最终执行任意远程代码。

## 漏洞影响

```
1.4.0 <= Nacos < 1.4.6
2.0.0 <= Nacos < 2.2.3
```

## 漏洞复现

exp：https://github.com/c0olw/NacosRce/

## 漏洞修复

1. 默认配置下该漏洞仅影响 Nacos 集群间 Raft 协议通信的 7848 端口，此端口不承载客户端请求，可以通过限制集群外部 IP 访问 7848 端口来进行缓解。
2. 官方已发布漏洞补丁及修复版本，请评估业务是否受影响后，酌情升级至安全版本：https://github.com/alibaba/nacos/releases


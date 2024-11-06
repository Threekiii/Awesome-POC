# 用友 NC bsh.servlet.BshServlet 远程命令执行漏洞

## 漏洞描述

用友 NC bsh.servlet.BshServlet 存在远程命令执行漏洞，通过BeanShell 执行远程命令获取服务器权限

## 漏洞影响

```
用友 NC
```

## 网络测绘

```
icon_hash="1085941792"
```

## 漏洞复现

访问页面如下

![yongyou-4-1](images/yongyou-4-1.png)

漏洞Url为

```plain
/servlet/~ic/bsh.servlet.BshServlet
```

![yongyou-4-2](images/yongyou-4-2.png)
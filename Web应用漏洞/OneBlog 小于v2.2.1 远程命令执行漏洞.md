# OneBlog 小于v2.2.1 远程命令执行漏洞

## 漏洞描述

OneBlog 小于v2.2.1 由于使用含有漏洞版本的Apache Shiro和默认的密钥导致存在远程命令执行漏洞

## 漏洞影响

```
OneBlog <= v2.2.1
```

## FOFA

```
app="OneBlog开源博客后台管理系统"
```

## 漏洞复现

登陆页面如下



![](./images/202202101909905.png)



使用工具直接利用Apache Shiro漏洞即可



![](./images/202202101909954.png)
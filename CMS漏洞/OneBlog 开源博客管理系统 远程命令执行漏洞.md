# OneBlog 开源博客管理系统 远程命令执行漏洞

## 漏洞描述

由于使用含有漏洞版本的Apache Shiro和默认的密钥，导致OneBlog存在远程命令执行漏洞。

## 漏洞复现

shiro 默认密钥：

![image-20221206161928066](images/image-20221206161928066.png)
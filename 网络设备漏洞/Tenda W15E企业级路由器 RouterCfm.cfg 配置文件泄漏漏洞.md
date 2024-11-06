# Tenda W15E企业级路由器 RouterCfm.cfg 配置文件泄漏漏洞

## 漏洞描述

Tenda 企业级路由器 RouterCfm.cfg 配置文件可在未授权的情况下被读取，导致账号密码等敏感信息泄漏

## 漏洞影响

```
Tenda 企业级路由器
```

## 网络测绘

```
title=="Tenda | Login" && country="CN"
```

## 漏洞复现

登录页面

![image-20220519181331832](images/202205191813876.png)

访问路径![image-20220519181508329](images/202205191815422.png)

后台账号密码位于参数 `sys.userpass` base64解密后的字符

![image-20220519181456848](images/202205191814923.png)
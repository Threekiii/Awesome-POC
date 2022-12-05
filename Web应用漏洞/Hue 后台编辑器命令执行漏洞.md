# Hue 后台编辑器命令执行漏洞

## 漏洞描述

Hue 后台编辑器存在命令执行漏洞，攻击者通过编辑上传  xxx.sh 文件即可达到命令执行的目的

## 漏洞影响

```
Hue 后台编辑器
```

## FOFA

```
title="Hue - 欢迎使用 Hue"
```

## 漏洞复现

登录页面如下

![hue-1](./images/hue-1.png)



上传并编辑文件为执行的命令

![hue-2](./images/hue-2.png)



按如下步骤点击即可执行想要执行的命令



![hue-3](./images/hue-3.png)
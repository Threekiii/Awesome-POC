# H3C 企业路由器（ER、ERG2、GR系列）任意用户登录漏洞

## 漏洞描述

H3C企业路由器（ER、ERG2、GR系列）存在任意用户登录和命令执行漏洞，

## 漏洞复现

官方确认漏洞存在。

攻击者可通过访问 /userLogin.asp/../actionpolicy_status/../xxxx.cfg 接口，xxxx 为设备型号（比如设备型号为 ER5200G2，即访问 /userLogin.asp/../actionpolicy_status/../ER5200G2.cfg），绕过 COOKIE 验证，进行目录穿越，获取设备的明文配置文件。

配置中有明文的 Web 管理员账号 admin 密码，登录后台可通过开启 telnet 获取命令执行权限。

## 修复建议

1. 升级设备固件至最新版本：ERHMG2-MNW100-R1122 及之后版本、ERG2AW-MNW100-R1113 及之后版本、MiniGR1B0V100R014 及之后版本；
2. 关闭 H3C 设备 <远程 WEB 管理>和<远程 TELNET 管理> 功能；
3. 做好企业内外资产排查，将受影响的设备及时下线或转移至内网。
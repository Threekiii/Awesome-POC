# Awesome-POC

##### 【免责声明】本仓库所涉及的技术、思路和工具仅供学习，任何人不得将其用于非法用途和盈利，否则后果自行承担。

《中华人民共和国网络安全法》第二十七条规定：

- 任何个人和组织使用网络应当遵守宪法法律，遵守公共秩序，尊重社会公德，不得危害网络安全，不得利用网络从事危害国家安全、荣誉和利益，煽动颠覆国家政权、推翻社会主义制度，煽动分裂国家、破坏国家统一，宣扬恐怖主义、极端主义，宣扬民族仇恨、民族歧视，传播暴力、淫秽色情信息，编造、传播虚假信息扰乱经济秩序和社会秩序，以及侵害他人名誉、隐私、知识产权和其他合法权益等活动。

## 0x01 项目导航

- CMS漏洞

  * AspCMS commentList.asp SQL注入漏洞
  * BSPHP index.php 未授权访问 信息泄露漏洞
  * CmsEasy crossall_act.php SQL注入漏洞
  * CmsEasy language_admin.php 后台命令执行漏洞
  * CmsEasy update_admin.php 后台任意文件上传漏洞
  * CxCMS Resource.ashx 任意文件读取漏洞
  * DedeCMS common.func.php 远程命令执行漏洞
  * Discuz!X 3.4 admincp_setting.php 后台SQL注入漏洞
  * DocCMS keyword SQL注入漏洞
  * emlog widgets.php 后台SQL注入漏洞
  * ezEIP 4.1.0 信息泄露漏洞
  * Ke361 AuthManagerController.class.php 后台SQL注入漏洞
  * Ke361 DistrictController.class.php 后台SQL注入漏洞 CNVD-2021-25002
  * Ke361 GoodsController.class.php SSRF漏洞
  * Ke361 MenuController.class.php 后台SQL注入漏洞 CNVD-2021-25002
  * Ke361 TopicController.class.php SQL注入漏洞 CNVD-2017-04380
  * OKLite 1.2.25 后台插件安装 任意文件上传
  * OKLite 1.2.25 后台模块导入 任意文件上传 CVE-2019-16131
  * OKLite 1.2.25 后台风格模块 任意文件删除 CVE-2019-16132
  * OpenSNS Application ShareController.class.php 远程命令执行漏洞
  * OpenSNS AuthorizeController.class.php 后台远程命令执行漏洞
  * OpenSNS ChinaCityController.class.php SQL注入漏洞
  * OpenSNS CurlModel.class.php SSRF漏洞
  * OpenSNS ThemeController.class.php 后台任意文件上传漏洞
  * OpenSNS ThemeController.class.php 后台任意文件下载漏洞
  * PbootCMS domain SQL注入漏洞
  * PbootCMS ext_price SQL注入漏洞
  * PbootCMS search SQL注入漏洞
  * Pd-CMS Shiro默认密钥 远程命令执行漏洞
  * ShopXO download 任意文件读取漏洞 CNVD-2021-15822
  * TypesetterCMS 后台任意文件上传
  * WeiPHP3.0 session_id 任意文件上传漏洞
  * WeiPHP5.0 bind_follow SQL注入漏洞
  * WeiPHP5.0 download_imgage 前台文件任意读取 CNVD-2020-68596
  * WeiPHP5.0 任意用户Cookie伪造 CNVD-2021-09693
  * YzmCMS Version 小于V5.8正式版 后台采集模块 SSRF漏洞
  * ZZZCMS parserSearch 远程命令执行漏洞
  * 华宜互联 硬编码超级管理员漏洞
  * 原创先锋 后台管理平台 未授权访问漏洞
  * 发货100 M_id SQL注入漏洞 CNVD-2021-30193
  * 快排CMS Socket.php 日志信息泄露漏洞
  * 快排CMS 后台XSS漏洞
  * 快排CMS 后台任意文件上传漏洞
  * 极致CMS 1.81 后台存储型XSS
  * 极致CMS alipay_return_pay SQL注入漏洞
  * 极致CMS 后台文件编辑插件 任意文件上传
  * 狮子鱼CMS ApiController.class.php SQL注入漏洞
  * 狮子鱼CMS ApigoodController.class.php SQL注入漏洞
  * 狮子鱼CMS image_upload.php 任意文件上传
  * 狮子鱼CMS wxapp.php 任意文件上传漏洞
  * 禅道 11.6 api-getModel-api-getMethod-filePath 任意文件读取漏洞
  * 禅道 11.6 api-getModel-api-sql-sql 后台SQL注入漏洞
  * 禅道 11.6 api-getModel-editor-save-filePath 任意文件写入漏洞
  * 禅道 12.4.2 CSRF漏洞 CNVD-2020-68552
  * 禅道 12.4.2 后台任意文件上传漏洞 CNVD-C-2020-121325
  * 齐博CMS V7 job.php 任意文件读取漏洞
- OA产品漏洞

  * 万户OA fileUpload.controller 任意文件上传漏洞
  * 华天动力OA 8000版 workFlowService SQL注入漏洞
  * 启莱OA CloseMsg.aspx SQL注入漏洞
  * 启莱OA messageurl.aspx SQL注入漏洞
  * 启莱OA treelist.aspx SQL注入漏洞
  * 帆软报表 2012 SSRF漏洞
  * 帆软报表 2012 信息泄露漏洞
  * 帆软报表 V8 任意文件读取漏洞 CNVD-2018-04757
  * 帆软报表 V9 任意文件覆盖文件上传
  * 新点OA ExcelExport 敏感信息泄露漏洞
  * 泛微OA DBconfigReader.jsp 数据库配置信息泄漏漏洞
  * 泛微OA E-Bridge saveYZJFile 任意文件读取漏洞
  * 泛微OA E-Cology BshServlet 远程代码执行漏洞 CNVD-2019-32204
  * 泛微OA E-Office mysql_config.ini 数据库信息泄漏漏洞
  * 泛微OA getdata.jsp SQL注入漏洞
  * 泛微OA sysinterfacecodeEdit.jsp 任意文件上传漏洞
  * 泛微OA uploadOperation.jsp 任意文件上传
  * 泛微OA weaver.common.Ctrl 任意文件上传漏洞
  * 泛微OA WorkflowCenterTreeData SQL注入漏洞
  * 泛微OA WorkflowServiceXml RCE
  * 用友 ERP-NC NCFindWeb 目录遍历漏洞
  * 用友 GRP-U8 Proxy SQL注入 CNNVD-201610-923
  * 用友 NC bsh.servlet.BshServlet 远程命令执行漏洞
  * 用友 NC NCFindWeb 任意文件读取漏洞
  * 用友 NC XbrlPersistenceServlet反序列化
  * 用友 NC 反序列化RCE漏洞
  * 用友 NCCloud FS文件管理SQL注入
  * 用友 U8 OA test.jsp SQL注入漏洞
  * 蓝凌OA admin.do JNDI远程命令执行
  * 蓝凌OA custom.jsp 任意文件读取漏洞
  * 蓝凌OA kmImeetingRes.do 后台SQL注入漏洞 CNVD-2021-01363
  * 通达OA v11.2 upload.php 后台任意文件上传漏洞
  * 通达OA v11.5 login_code.php 任意用户登录
  * 通达OA v11.6 print.php 任意文件删除&RCE
  * 通达OA v11.7 auth_mobi.php 在线用户登录漏洞
  * 通达OA v11.7 delete_cascade.php 后台SQL注入
  * 通达OA v11.8 update.php 后台文件包含XSS漏洞
  * 通达OA v11.8 update.php 后台文件包含命令执行漏洞
  * 通达OA v11.9 upsharestatus 后台SQL注入漏洞
  * 通达OA v2017 action_upload.php 任意文件上传漏洞
  * 通达OA 部分漏洞信息整合
  * 金和OA C6 DossierBaseInfoView.aspx 后台越权信息泄露漏洞
  * 金和OA C6 download.jsp 任意文件读取漏洞
  * 金和OA C6 OpenFile.aspx 后台越权敏感文件遍历漏洞
  * 金蝶OA server_file 目录遍历漏洞
- Web应用漏洞

  * 1039家校通 万能密码绕过 CNVD-2020-31494
  * 1039家校通 后台任意文件上传漏洞
  * 360天擎 gettablessize 数据库信息泄露漏洞
  * 360天擎 rptsvcsyncpoint 前台SQL注入
  * Active UC index.action 远程命令执行漏洞
  * Adobe ColdFusion upload.cfm 任意文件上传漏洞 CVE-2018-15961
  * Alibaba AnyProxy fetchBody 任意文件读取漏洞
  * Alibaba Canal config 云密钥信息泄露漏洞
  * Alibaba Nacos 未授权访问漏洞
  * Atlassian Jira 信息泄露漏洞 CVE-2020-14181
  * AVCON6 系统管理平台 download.action 任意文件下载漏洞
  * AVCON6 系统管理平台 org_execl_download.action 任意文件下载漏洞
  * Citrix XenMobile 任意文件读取 CVE-2020-8209
  * Coremail 配置信息泄露漏洞
  * E-message 越权访问漏洞
  * F5 BIG-IP 远程代码执行漏洞 CVE-2020-5902
  * F5 BIG-IP 远程代码执行漏洞 CVE-2021-22986
  * GitLab Graphql邮箱信息泄露漏洞 CVE-2020-26413
  * GitLab SSRF漏洞 CVE-2021-22214
  * H3C IMC dynamiccontent.properties.xhtm 远程命令执行
  * H3C SecParh堡垒机 data_provider.php 远程命令执行漏洞
  * H3C SecParh堡垒机 get_detail_view.php 任意用户登录漏洞
  * Harbor 未授权创建管理员漏洞 CVE-2019-16097
  * Hue 后台编辑器命令执行漏洞
  * IBOS 数据库模块 后台任意文件上传漏洞
  * ICEFlow VPN 信息泄露漏洞
  * IceWarp WebClient basic 远程命令执行漏洞
  * JD-FreeFuck 后台命令执行漏洞
  * Jellyfin 任意文件读取漏洞 CVE-2021-21402
  * JumpServer 未授权接口 远程命令执行漏洞
  * Jupyter Notebook 未授权访问远程命令执行漏洞
  * kkFileView getCorsFile 任意文件读取漏洞 CVE-2021-43734
  * Konga 普通用户越权获取管理员权限漏洞
  * Lanproxy 目录遍历漏洞 CVE-2021-3019
  * MessageSolution 邮件归档系统EEA 信息泄露漏洞 CNVD-2021-10543
  * Nexus Repository Manger change-password 低权限修改管理员密码漏洞 CVE-2020-11444
  * Nexus Repository Manger extdirect 后台远程命令执行 CVE-2020-10204
  * Nexus Repository Manger extdirect 远程命令执行 CVE-2019-7238
  * Nexus Repository Manger group 后台远程命令执行 CVE-2020-10199
  * Node-RED ui_base 任意文件读取漏洞
  * OneBlog 小于v2.2.1 远程命令执行漏洞
  * Riskscanner list SQL注入漏洞
  * Seo-Panel 4.8.0 反射型XSS漏洞 CVE-2021-3002
  * ShowDoc 任意文件上传漏洞
  * SonarQube search_projects 项目信息泄露漏洞
  * SonarQube values 信息泄露漏洞 CVE-2020-27986
  * TamronOS IPTV系统 ping 任意命令执行漏洞
  * TamronOS IPTV系统 submit 任意用户创建漏洞
  * TerraMaster TOS exportUser.php 远程命令执行
  * Terra Master TOS RCE CVE 2020 28188
  * TerraMaster TOS 任意账号密码修改漏洞 CVE-2020-28186
  * TerraMaster TOS 后台任意文件读取漏洞 CVE-2020-28187
  * TerraMaster TOS 用户枚举漏洞 CVE-2020-28185
  * XXL-JOB 任务调度中心 后台任意命令执行漏洞
  * YApi 接口管理平台 后台命令执行漏洞
  * 三汇SMG 网关管理软件 down.php 任意文件读取漏洞
  * 中新金盾信息安全管理系统 默认超级管理员密码漏洞
  * 中科网威 NPFW防火墙 CommandsPolling.php 任意文件读取漏洞
  * 久其财务报表 download.jsp 任意文件读取漏洞
  * 亿赛通 电子文档安全管理系统 dataimport 远程命令执行漏洞
  * 亿邮电子邮件系统 moni_detail.do 远程命令执行漏洞
  * 会捷通云视讯 fileDownload 任意文件读取漏洞
  * 会捷通云视讯 list 目录文件泄露漏洞
  * 会捷通云视讯 登录绕过漏洞
  * 和信创天云桌面系统 远程命令执行 RCE漏洞
  * 图创软件 图书馆站群管理系统 任意文件读取漏洞
  * 天融信 DLP 未授权访问漏洞
  * 天融信 TopApp-LB enable_tool_debug.php 远程命令执行漏洞
  * 天融信 TopApp-LB SQL注入漏洞
  * 天融信 TopApp-LB 命令执行漏洞
  * 天融信 TopApp-LB系统 任意登陆
  * 孚盟云 AjaxMethod.ashx SQL注入漏洞
  * 安天 高级可持续威胁安全检测系统 越权访问漏洞
  * 安徽阳光心健 心理测量平台 目录遍历漏洞 CNVD-2021-01929
  * 安美数字 酒店宽带运营系统 server_ping.php 远程命令执行漏洞
  * 宝塔 phpmyadmin未授权访问漏洞
  * 思福迪堡垒机 任意用户登录漏洞
  * 思迪数据 Home 登录绕过漏洞
  * 智慧校园管理系统 前台任意文件上传漏洞
  * 极通EWEBS casmain.xgi 任意文件读取漏洞
  * 极通EWEBS testweb.php 敏感信息泄露漏洞
  * 杭州法源软件 公证实务教学软件 SQL注入漏洞
  * 杭州法源软件 法律知识数据库系统 后台XSS漏洞
  * 汉王人脸考勤管理系统 Check SQL注入漏洞
  * 浪潮ClusterEngineV4.0 sysShell 任意命令执行漏洞
  * 浪潮ClusterEngineV4.0 任意用户登录漏洞
  * 浪潮ClusterEngineV4.0 远程命令执行漏洞 CVE-2020-21224
  * 深信服 EDR c.php 远程命令执行漏洞 CNVD-2020-46552
  * 深信服 EDR 后台任意用户登陆漏洞
  * 深信服 SSL VPN 客户端远程文件下载
  * 深信服 日志中心 c.php 远程命令执行漏洞
  * 深信服 行为感知系统 c.php 远程命令执行漏洞
  * 畅捷CRM 后台附件任意文件上传漏洞
  * 科迈 RAS系统 硬编码管理员漏洞
  * 网御星云 web防护系统 信息泄露漏洞
  * 若依管理系统 Druid未授权访问
  * 若依管理系统 后台任意文件读取 CNVD-2021-01931
  * 蓝海卓越计费管理系统 debug.php 远程命令执行漏洞
  * 蓝海卓越计费管理系统 download.php 任意文件读取漏洞
  * 金山 V8 终端安全系统 downfile.php 任意文件读取漏洞
  * 金山 V8 终端安全系统 pdf_maker.php 命令执行漏洞
  * 银澎云计算 好视通视频会议系统 任意文件下载 CNVD-2020-62437
  * 阿尔法科技 虚拟仿真实验室 未授权访问漏洞
  * 章管家 Druid未授权访问漏洞
  * 默安 幻阵蜜罐未授权访问 RCE
  * 齐治堡垒机 gui_detail_view.php 任意用户登录漏洞
  * 龙璟科技 电池能量BEMS downloads 任意文件下载漏洞
- Web服务器漏洞

  * Apache ActiveMQ Console控制台默认弱口令
  * Apache ActiveMQ 反序列化漏洞 CVE-2015-5254
  * Apache Cocoon XML注入 CVE-2020-11991
  * Apache CouchDB epmd 远程命令执行漏洞 CVE-2022-24706
  * Apache Druid 远程代码执行漏洞 CVE-2021-25646
  * Apache Flink 小于1.9.1远程代码执行 CVE-2020-17518
  * Apache Flink 目录遍历漏洞 CVE-2020-17519
  * Apache HTTPd 换行解析漏洞 CVE-2017-15715
  * Apache Mod_jk 访问控制权限绕过 CVE-2018-11759
  * Apache OFBiz RMI反序列化漏洞 CVE-2021-26295
  * Apache Shiro 小于1.2.4反序列化漏洞 CVE-2016-4437
  * Apache Solr JMX服务 RCE CVE-2019-12409
  * Apache Solr RCE 未授权上传漏洞 CVE-2020-13957
  * Apache Solr RCE 远程命令执行漏洞 CVE-2017-12629
  * Apache Solr Velocity模板远程执行 CVE-2019-17558
  * Apache Solr XXE 漏洞 CVE-2017-12629
  * Apache Solr 任意文件读取漏洞
  * Apache Solr 远程执行漏洞 CVE-2019-0193
  * Apache Struts2 S2-062 远程代码执行漏洞 CVE-2021-31805
  * Apache Tomcat AJP 文件包含漏洞 CVE-2020-1938
  * Apache Tomcat WebSocket 拒绝服务漏洞 CVE-2020-13935
  * Apache Tomcat 信息泄露漏洞 CVE-2021-24122
  * Apache Tomcat 远程代码执行漏洞 CVE-2017-12615
  * Apache Zeppelin 未授权任意命令执行漏洞
  * Apache ZooKeeper 未授权访问漏洞 CVE-2014-085漏洞描述
  * JBoss 4.x JBossMQ JMS 反序列化漏洞 CVE-2017-7504
  * Nginx越界读取缓存漏洞 CVE-2017-7529
  * Weblogic LDAP 远程代码执行漏洞 CVE-2021-2109
  * Weblogic SSRF漏洞 CVE-2014-4210
  * Weblogic XMLDecoder 远程代码执行漏洞 CVE-2017-10271
  * Weblogic 反序列化远程代码执行漏洞 CVE-2019-2725
- 开发框架漏洞

  * jQuery XSS漏洞 CVE-2020-11022 11023
  * Laravel .env 配置文件泄露 CVE-2017-16894
  * Laravel 小于 8.4.2 Debug模式 _ignition 远程代码执行漏洞 CVE-2021-3129
  * MotionEye 视频监控组件 list 信息泄漏洞 CVE-2022-25568
  * PHPUnit eval-stdin.php 远程命令执行漏洞 CVE-2017-9841
  * Spring Cloud Function SPEL 远程命令执行漏洞
- 开发语言漏洞

  * PHP zerodium后门漏洞
- 操作系统漏洞

  * Linux kernel权限提升漏洞 CVE-2021-3493
  * Linux sudo权限提升漏洞 CVE-2021-3156
  * Windows CryptoAPI欺骗漏洞 CVE-2020-0601
  * Windows SMB远程代码执行漏洞 CVE-2020-0796
  * Windows Win32k 本地提权漏洞 CVE-2021-1732
- 服务器应用漏洞

  * ClickHouse API 数据库接口未授权访问漏洞
  * Elasticsearch 未授权访问
  * Git for Visual Studio远程执行代码漏洞 CVE-2021-21300
  * Git-LFS 远程命令执行漏洞 CVE-2020-27955
  * Microsoft Exchange SSRF漏洞 CVE-2021-26885
  * Microsoft Exchange 远程命令执行 CVE-2021-27065 26857 26858 27065
  * MinIO SSRF漏洞 CVE-2021-21287
  * NVIDIA GPU显示驱动程序 信息泄露 CVE-2021-1056
  * OpenSSH 命令注入漏洞 CVE-2020-15778
  * OpenSSL 心脏滴血漏洞 CVE-2014-0160
  * QEMU 虚拟机逃逸漏洞 CVE-2020-14364
  * Redis 小于5.0.5 主从复制 RCE
  * SaltStack 未授权访问命令执行漏洞 CVE-2020-16846 25592
  * Saltstack 远程命令执行漏洞 CVE-2020-11651 11652
  * VMware vCenter 任意文件读取漏洞
  * VMware vRealize Operations Manager SSRF漏洞 CVE-2021-21975
  * VoIPmonitor 远程命令执行漏洞 CVE-2021-30461
  * Windows Chrome 远程命令执行漏洞
- 网络设备漏洞

  * ACTI 视频监控 images 任意文件读取漏洞
  * Amcrest IP Camera Web Sha1Account1 账号密码泄漏漏洞 CVE-2017-8229
  * Arcadyan固件 cgi_i_filter.js 配置信息泄漏漏洞 CVE-2021-20092
  * Arcadyan固件 image 路径遍历漏洞 CVE-2021-20090
  * Cisco ASA设备 任意文件读取漏洞 CVE-2020-3452
  * Cisco ASA设备任意文件删除漏洞 CVE-2020-3187
  * Cisco HyperFlex HX storfs-asup 远程命令执行漏洞 CVE-2021-1497
  * Cisco HyperFlex HX upload 任意文件上传漏洞 CVE-2021-1499
  * Crestron aj.html 账号密码泄漏漏洞 CVE-2022-23178
  * D-Link AC管理系统 默认账号密码
  * D-Link DAR-8000 importhtml.php 远程命令执行漏洞
  * D-Link DCS系列监控 账号密码信息泄露漏洞 CVE-2020-25078
  * D-Link Dir-645 getcfg.php 账号密码泄露漏洞 CVE-2019-17506
  * D-Link DSL-28881A FTP配置错误 CVE-2020-24578
  * D-Link DSL-28881A 信息泄露 CVE-2020-24577
  * D-Link DSL-28881A 未授权访问 CVE-2020-24579
  * D-Link DSL-28881A 远程命令执行 CVE-2020-24581
  * D-Link DSR-250N 万能密码漏洞
  * D-Link ShareCenter DNS-320 system_mgr.cgi 远程命令执行漏洞
  * DD-WRT UPNP缓冲区溢出漏洞 CVE-2021-27137
  * DrayTek企业网络设备 远程命令执行 CVE-2020-8515
  * DVR 登录绕过漏洞 CVE-2018-9995
  * Finetree 5MP 摄像机 user_pop.php 任意用户添加漏洞 CNVD-2021-42372
  * FLIR-AX8 download.php 任意文件下载
  * H3C SecPath下一代防火墙 任意文件下载漏洞
  * HIKVISION DSIDSIPC 等设备 远程命令执行漏洞 CVE-2021-36260
  * HIKVISION 流媒体管理服务器 user.xml 账号密码泄漏漏洞
  * HIKVISION 流媒体管理服务器 后台任意文件读取漏洞 CNVD-2021-14544
  * HIKVISION 视频编码设备接入网关 $DATA 任意文件读取
  * HIKVISION 视频编码设备接入网关 showFile.php 任意文件下载漏洞
  * HIKVISION 联网网关 downdb.php 任意文件读取漏洞
  * Huawei DG8045 deviceinfo 信息泄漏漏洞
  * Huawei HG659 lib 任意文件读取漏洞
  * iKuai 流控路由 SQL注入漏洞
  * Intelbras Wireless 未授权与密码泄露 CVE-2021-3017
  * JCG JHR-N835R 后台命令执行漏洞
  * KEDACOM数字系统接入网关 任意文件读取漏洞
  * KONE 通力电梯管理系统 app_show_log_lines.php 任意文件读取漏洞
  * Kyan 网络监控设备 hosts 账号密码泄露漏洞
  * Kyan 网络监控设备 license.php 远程命令执行漏洞
  * Kyan 网络监控设备 module.php 远程命令执行漏洞
  * Kyan 网络监控设备 run.php 远程命令执行漏洞
  * Kyan 网络监控设备 time.php 远程命令执行漏洞
  * MagicFlow 防火墙网关 main.xp 任意文件读取漏洞
  * MSA 互联网管理网关 msa 任意文件下载漏洞
  * NetMizer 日志管理系统 cmd.php 远程命令执行漏洞
  * NetMizer 日志管理系统 data 目录遍历漏洞
  * NetMizer 日志管理系统 登录绕过漏洞
  * rConfig ajaxArchiveFiles.php 后台远程命令执行漏洞
  * rConfig ajaxEditTemplate.php 后台远程命令执行漏洞
  * rConfig useradmin.inc.php 信息泄露漏洞
  * rConfig userprocess.php 任意用户创建漏洞
  * Sapido 多款路由器 远程命令执行漏洞
  * Selea OCR-ANPR摄像机 get_file.php 任意文件读取漏洞
  * Selea OCR-ANPR摄像机 SeleaCamera 任意文件读取漏洞
  * SonicWall SSL-VPN 远程命令执行漏洞
  * Tenda 11N无线路由器 Cookie 越权访问漏洞
  * Tenda W15E企业级路由器 RouterCfm.cfg 配置文件泄漏漏洞
  * TOTOLink 多个设备 download.cgi 远程命令执行漏洞 CVE-2022-25084
  * TP-Link SR20 远程命令执行
  * TVT数码科技 NVMS-1000 路径遍历漏洞
  * Wayos AC集中管理系统默认弱口令 CNVD-2021-00876
  * Wayos 防火墙 后台命令执行漏洞
  * Wayos 防火墙 账号密码泄露漏洞
  * XAMPP phpinfo.php 信息泄漏漏洞
  * ZeroShell 3.9.0 远程命令执行漏洞 CVE-2019-12725
  * Zyxel NBG2105 身份验证绕过 CVE-2021-3297
  * Zyxel 硬编码后门账户漏洞 CVE-2020-29583
  * 三星 WLAN AP WEA453e路由器 远程命令执行漏洞
  * 中国移动 禹路由 ExportSettings.sh 敏感信息泄露漏洞 CNVD-2020-67110
  * 中国移动 禹路由 simple-index.asp 越权访问漏洞 CNVD-2020-55983
  * 中科网威 下一代防火墙控制系统 download.php 任意文件读取漏洞
  * 中科网威 下一代防火墙控制系统 账号密码泄露漏洞
  * 佑友防火墙 后台命令执行漏洞
  * 华夏创新 LotWan广域网优化系统 check_instance_state.php 远程命令执行漏洞
  * 华夏创新 LotWan广域网优化系统 static_arp_del.php SQL注入漏洞
  * 华夏创新 LotWan广域网优化系统 static_arp.php 远程命令执行漏洞
  * 博华网龙防火墙 cmd.php 远程命令执行漏洞
  * 博华网龙防火墙 users.xml 未授权访问
  * 启明星辰 天清汉马USG防火墙 逻辑缺陷漏洞
  * 启明星辰 天清汉马USG防火墙 默认口令漏洞
  * 大华 城市安防监控系统平台管理 attachment_downloadByUrlAtt.action 任意文件下载漏洞
  * 奇安信 网康 NS-ASG安全网关 cert_download.php 任意文件读取漏洞
  * 奇安信 网康 下一代防火墙 router 远程命令执行漏洞
  * 宏电 H8922 Telnet后门漏洞 CVE-2021-28149
  * 宏电 H8922 后台任意文件读取漏洞 CVE-2021-28152
  * 宏电 H8922 后台命令执行漏洞 CVE-2021-28150
  * 宏电 H8922 后台管理员信息泄露漏洞 CVE-2021-28151
  * 悦泰节能 智能数据网关 resources 任意文件读取漏洞
  * 惠尔顿 e地通 config.xml 信息泄漏漏洞
  * 朗视 TG400 GSM 网关目录遍历 CVE-2021-27328
  * 浙江宇视科技 网络视频录像机 ISC LogReport.php 远程命令执行漏洞
  * 烽火 HG6245D info.asp 信息泄露漏洞
  * 电信 中兴ZXHN F450A网关 默认管理员账号密码漏洞
  * 电信 天翼网关F460 web_shell_cmd.gch 远程命令执行漏洞
  * 电信 网关配置管理系统 login.php SQL注入漏洞
  * 百卓 Patflow showuser.php 后台SQL注入漏洞
  * 百卓 Smart importhtml.php 远程命令执行漏洞
  * 皓峰防火墙 setdomain.php 越权访问漏洞
  * 磊科 NI360路由器 认证绕过漏洞
  * 网康 NS-ASG安全网关 cert_download.php 任意文件读取漏洞
  * 网康 下一代防火墙 router 远程命令执行漏洞
  * 网御 Leadsec ACM管理平台 importhtml.php 远程命令执行漏洞
  * 网神 下一代极速防火墙 pki_file_download 任意文件读取漏洞
  * 蜂网互联 企业级路由器v4.31 密码泄露漏洞 CVE-2019-16313
  * 西迪特 Wi-Fi Web管理 Cookie 越权访问漏洞
  * 西迪特 Wi-Fi Web管理 jumpto.php 后台命令执行漏洞
  * 迈普 ISG1000安全网关 任意文件下载漏洞
  * 锐捷 EG易网关 branch_passw.php 远程命令执行
  * 锐捷 EG易网关 cli.php 远程命令执行漏洞
  * 锐捷 EG易网关 download.php 任意文件读取漏洞
  * 锐捷 EG易网关 phpinfo.view.php 信息泄露漏洞
  * 锐捷 EG易网关 管理员账号密码泄露漏洞
  * 锐捷 ISG 账号密码泄露漏洞
  * 锐捷 NBR 1300G路由器 越权CLI命令执行漏洞
  * 锐捷 NBR路由器 远程命令执行漏洞 CNVD-2021-09650
  * 锐捷 RG-UAC 账号密码信息泄露 CNVD-2021-14536
  * 锐捷 Smartweb管理系统 密码信息泄露漏洞
  * 锐捷 SSL VPN 越权访问漏洞
  * 锐捷 云课堂主机 pool 目录遍历漏洞
  * 飞鱼星 企业级智能上网行为管理系统 权限绕过信息泄露漏洞
  * 飞鱼星 家用智能路由 cookie.cgi 权限绕过

## 0x02 声明

本项目收集漏洞均源于互联网：

- Vulhub：https://github.com/vulhub/vulhub
- Peiqi：https://github.com/PeiQi0/PeiQi-WIKI-Book

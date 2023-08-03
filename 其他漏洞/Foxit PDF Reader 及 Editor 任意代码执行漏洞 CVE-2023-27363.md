# Foxit PDF Reader 及 Editor 任意代码执行漏洞 CVE-2023-27363

## 漏洞描述

Foxit PDF Reader 及 Editor 中存在任意代码执行漏洞，由于 Foxit PDFReader/Editor 未验证 exportXFAData 方法中的 cPath 参数，使得恶意的.hta 文件写入 Startup 目录中，攻击者可通过诱导受害者打开特制的 PDF 文档触发此漏洞，系统重启后将执行攻击者的恶意代码。

## 漏洞影响

```
Foxit PDF Reader <= 12.1.1.15289
Foxit PDF Editor 12.x <= 12.1.1.15289
Foxit PDF Editor 11.x <= 11.2.5.53785
Foxit PDF Editor <= 10.1.11.37866
```

## 漏洞复现

poc：

https://github.com/j00sean/SecBugs/tree/main/CVEs/CVE-2023-27363

## 漏洞修复

目前官方已发布可更新版本，受影响用户可通过以下任一步骤进行更新：

1. 在 Foxit PDF 阅读器或 Foxit PDF 编辑器中，点击“帮助”>“关于 Foxit PDF阅读器”或“关于 Foxit PDF 编辑器”>“检查更新”(对于 10 版本或更早的版本，点击“帮助”>“检查更新”)以更新到最新版本。
2. 手动下载更新：https://www.foxit.com/downloads/
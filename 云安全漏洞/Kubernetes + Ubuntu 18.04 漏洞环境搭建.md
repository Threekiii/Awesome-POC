# Kubernetes + Ubuntu 18.04 漏洞环境搭建

## 前置知识

本 Kubernetes + Ubuntu 18.04 漏洞环境适用于 [Awesome-POC](https://github.com/Threekiii/Awesome-POC/tree/master/%E4%BA%91%E5%AE%89%E5%85%A8%E6%BC%8F%E6%B4%9E) 中与 Kubernetes 相关的部分云安全漏洞。

各组件版本如下：

```
Docker version: 18.09.3
minikube version: v1.35.0
Kubectl Client Version: v1.32.3
Kubectl Server Version: v1.32.0
```

## 环境搭建

### Docker 18.09.3

> 注意，此版本是一个较老的版本（发布于 2019 年 3 月），它存在多个已知的安全漏洞。**安装脚本仅限于漏洞环境，请勿用于生产环境。**

安装脚本 `install_docker_18.09.03.sh`：

```
#!/bin/bash

set -e

echo "🔧 卸载旧版本 Docker（如果有）..."
sudo apt remove -y docker docker-engine docker.io containerd runc || true

echo "🧹 删除错误的 Docker 源..."
sudo rm -f /etc/apt/sources.list.d/docker.list || true
sudo sed -i '/download.docker.com/d' /etc/apt/sources.list

echo "🌐 添加清华大学 Docker 镜像源 GPG key..."
wget -qO - https://mirrors.tuna.tsinghua.edu.cn/docker-ce/linux/ubuntu/gpg | sudo apt-key add -

echo "📚 添加清华大学 Docker 镜像源..."
echo "deb [arch=amd64] https://mirrors.tuna.tsinghua.edu.cn/docker-ce/linux/ubuntu bionic stable" \
  | sudo tee /etc/apt/sources.list.d/docker.list

echo "📦 更新软件包索引..."
sudo apt update

echo "🔍 查找 Docker 18.09.3..."
VERSION_STRING=$(apt-cache madison docker-ce | grep 18.09.3 | head -n1 | awk '{print $3}')
if [ -z "$VERSION_STRING" ]; then
  echo "❌ 找不到 Docker 18.09.3"
  exit 1
fi
echo "✅ 找到版本：$VERSION_STRING"

echo "⬇️ 安装 Docker 版本 $VERSION_STRING ..."
sudo apt install -y docker-ce=$VERSION_STRING docker-ce-cli=$VERSION_STRING containerd.io

echo "📌 锁定版本，防止自动升级..."
sudo apt-mark hold docker-ce docker-ce-cli containerd.io

echo "✅ 安装完成，当前版本："
docker --version
```

### Kubectl v1.32.3

安装最新版本：

```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
```

或直接安装 v1.32.3：

```
curl -LO "https://dl.k8s.io/release/v1.32.3/bin/linux/amd64/kubectl"
```

将下载的 `kubectl` 安装到系统路径 `/usr/local/bin/`：

```
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl 
```

### Minikube v1.35.0

安装最新版本：

```
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
```

将下载的 `minikube` 安装到系统路径 `/usr/local/bin/`：

```
sudo install minikube-linux-amd64 /usr/local/bin/minikube
```

## 环境启动

### 启动 Minikube 集群

```
minikube start --force
```

由于网络原因，国内可以拉取 `kicbase`，自定义基础镜像源启动 minikube：

```
docker pull registry.cn-hangzhou.aliyuncs.com/google_containers/kicbase:v0.0.46
minikube start --force --base-image='registry.cn-hangzhou.aliyuncs.com/google_containers/kicbase:v0.0.46'
```

### 查看 Minikube 集群状态

```
minikube status
```

![](images/Kubernetes%20+%20Ubuntu%2018.04%20漏洞环境搭建/image-20250422113722290.png)

### 查看集群节点状态

```
kubectl get nodes
```

![](images/Kubernetes%20+%20Ubuntu%2018.04%20漏洞环境搭建/image-20250422113744552.png)

### 查看所有 Pod 状态

```
kubectl get pods --all-namespaces
```

![](images/Kubernetes%20+%20Ubuntu%2018.04%20漏洞环境搭建/image-20250422113927638.png)

### 停止 Minikube 集群

```
minikube stop
```

### 删除 Minikube 集群

```
minikube delete
```

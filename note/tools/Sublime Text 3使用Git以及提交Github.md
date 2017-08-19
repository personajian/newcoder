### 一、 安装、配置git和github

#### 1. 安装git

#### 2. 配置环境变量

#### 3. 在git bash中设置git的用户名和邮箱地址

    git config --global user.name "personajian"
    git config --global user.email "personajian@gmail.com"

#### 4. 设置一个github网站和本地git软件连接的密钥

    ssh-keygen -t rsa

#### 5. 用ssh把本地git和github授权连接

github ssh对密钥进行授权连接

    ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDxufKIMghd7VcV0St/10c02MFpdc+ZxNpYOASr9r8xL6rT9k8bhU6V27+x6pCpyOZTXpF3vzDjo7/BKcekpPSWmEz0omMjG4rLDDEhlBQlNvHYxpstrNDhzoXARNbeelFGHMPWPctrxz1AZS4zDILo791xihnaf8zaqrPVLtT6VbbuZf/Ix2j7rYnbIS7xBsBfM6kG7HdCXWci7kVTfQBtlDYg6lrst/njUZOUQ3AAl9xO7gkicM2ZLb4+YkI2Q3bMKk5h8ToMmOUOtXUiGqos/ftIlMXbcON5hfaucCi9gBQef2Qv8/tj2g31RC7weQ37xeg1FTZH7wFkaC0XY3Nr personajian@DESKTOP-59FDICP

本地git连接，git bash中键入：


    $ ssh git@github.com

#### gti其他命令

    git clone https://github.com/personajian/notebook.git



### 二、Sublime Text 3 安装SublimeGit插件（不是Git插件，Git插件有bug）

1. `Git:init`初始化项目目录
2. `Git:status`进入sublimeGit使用界面，可以使用单个字母的快捷键例如：
    1. 选中要提交的文件名，按“s”键添加文件到git；
    2. “c”提交文件到git；
    3. “u”取消已经添加的文件。
    4. 提交文件时，会自动弹出填写本次提交的注释的地方，关闭后，自动上传注释。
3. Git:push则将已经提交的文件及注释上传至github
4. Git:log查看提交的各个版本，并且会显示修改之前和修改之后的变化，和svn差不多
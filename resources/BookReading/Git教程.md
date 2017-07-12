[TOC]

### Sublime Git
```
git add <all>
git commit; add comments; CRTL+W
git push
done!
```

### 安装Git

```
$ git config --global user.name "Your Name"
$ git config --global user.email "email@example.com"
```

### 创建版本库

- 初始化一个Git仓库，使用`git init`命令。

- 添加文件到Git仓库，分两步：

    - 第一步，使用命令`git add <file>`，注意，可反复多次使用，添加多个文件；
    - 第二步，使用命令`git commit`，完成。

### 时光穿梭机

要随时掌握工作区的状态，使用`git status`命令。
如果`git status`告诉你有文件被修改过，用`git diff`可以查看修改内容。

#### 版本回退

HEAD指向的版本就是当前版本，因此，Git允许我们在版本的历史之间穿梭，使用命令`git reset --hard commit_id`。  
穿梭前，用`git log`可以查看提交历史，以便确定要回退到哪个版本。  
要重返未来，用git reflog查看命令历史，以便确定要回到未来的哪个版本。

#### 工作区和暂存区

工作区（Working Directory）  
版本库（Repository）

#### 管理修改

现在，你又理解了Git是如何跟踪修改的，每次修改，如果不add到暂存区，那就不会加入到commit中。


#### 撤销修改

场景1：当你改乱了工作区某个文件的内容，想直接丢弃工作区的修改时，用命令`git checkout -- file`。

场景2：当你不但改乱了工作区某个文件的内容，还添加到了暂存区时，想丢弃修改，分两步，第一步用命令`git reset HEAD file`，就回到了场景1，第二步按场景1操作。

场景3：已经提交了不合适的修改到版本库时，想要撤销本次提交，参考版本回退一节，不过前提是没有推送到远程库。

#### 删除文件

命令`git rm`用于删除一个文件。如果一个文件已经被提交到版本库，那么你永远不用担心误删，但是要小心，你只能恢复文件到最新版本，你会丢失最近一次提交后你修改的内容。

### 远程仓库

由于你的本地Git仓库和GitHub仓库之间的传输是通过SSH加密的，所以，需要一点设置：

- 第1步：创建SSH Key。在用户主目录下，看看有没有.ssh目录，如果有，再看看这个目录下有没有id_rsa和id_rsa.pub这两个文件，如果已经有了，可直接跳到下一步。如果没有，打开Shell（Windows下打开Git Bash），创建SSH Key：
```
$ ssh-keygen -t rsa -C "youremail@example.com"
```
你需要把邮件地址换成你自己的邮件地址，然后一路回车，使用默认值即可，由于这个Key也不是用于军事目的，所以也无需设置密码。  
如果一切顺利的话，可以在用户主目录里找到.ssh目录，里面有id_rsa和id_rsa.pub两个文件，这两个就是SSH Key的秘钥对，id_rsa是私钥，不能泄露出去，id_rsa.pub是公钥，可以放心地告诉任何人。  

- 第2步：登陆GitHub，打开“Account settings”，“SSH Keys”页面：

然后，点“Add SSH Key”，填上任意Title，在Key文本框里粘贴id_rsa.pub文件的内容：
```
github-addkey-1
```
点“Add Key”，你就应该看到已经添加的Key：

#### 添加远程库

要关联一个远程库，使用命令`git remote add origin git@server-name:path/repo-name.git`；  
关联后，使用命令`git push -u origin master`第一次推送master分支的所有内容；  
此后，每次本地提交后，只要有必要，就可以使用命令`git push origin master`推送最新修改；

#### 从远程库克隆
```
$ git clone git@github.com:michaelliao/gitskills.git
```
要克隆一个仓库，首先必须知道仓库的地址，然后使用git clone命令克隆。  
Git支持多种协议，包括https，但通过ssh支持的原生git协议速度最快。

### 分支管理

#### 创建与合并分支

#### 解决冲突

#### 分支管理策略

#### Bug分支

#### Feature分支

#### 多人协作

### 标签管理

#### 创建标签

#### 操作标签

### 使用GitHub

### 自定义Git

#### 忽略特殊文件

#### 配置别名

#### 搭建Git服务器

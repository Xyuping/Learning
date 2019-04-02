# Linux

## 操作系统

### 概述

![IMG_0179](https://ws4.sinaimg.cn/large/006tKfTcly1g1o4az40szj30td0j0497.jpg)

1. 可以运行在裸机（硬件）上 

2. 组成：内核、库、应用程序 

	> 内核：管理控制硬件资源 
	>
	> 库和应用程序：管理软件资源。 
	>
	> 库是没有执行入口的应用程序（即不能自己执行，必须调用执行。）库是为了提高软件的运行效率和开发效率 
	>
	> 这里的应用程序指的是操作系统自带的应用程序，例如鼠标管理、驱动、显卡等等。 
	>
	> - Windows系统的库文件：dll文件，动态链接库 
	> - Linux系统的库文件：so 共享对象 

![screenshot](https://ws1.sinaimg.cn/large/006tKfTcly1g1o4bjaxwaj30ua0oygql.jpg)

### **操作系统发展史**

- Unix
- Linux 基于Unix重新开发了内核 
- Windows三大版本的变化： 

​                   最早的Windows（95、98）直接修改自Linux，变成桌面版的 

​                    Windows2000 XP在nt基础上

​                    2000后，

- 大部分服务器端使用Linux、Unix，少量用Windows server（.net） 

### Linux 内核及发行版介绍 

- 所有发行版内核一样，库和应用程序不一样 

- Linux源码开源协议：自由软件协议。特点：病毒式开源。可以任意使用修改其源码但是必须公开。（另一种开源协议：Apache的开源协议。特点：任意使用，任意修改，修改完后可以不开源）。Linux内核不收版权费，只收服务费。因为内核对某个公司来说没有版权。（即操作系统本身无版权，但是操作系统自带的第三方软件包有） 

- 比较著名的发行版本：

	> Fedora 
	>
	>  Redhat（小红帽公司）
	>
	> Linux社区其他人把Fedora开源的代码重新编译，把Fedora一些收费的软件包用免费的第三方软件包替换—>centos。所以二者非常相似
	>
	> Ubuntu 

- 个人桌面领域中，Ubuntu和Fedora具有最大的市场占有率 
- 服务器领域中，Redhat公司的AS系列（占有率最高）、完全开源的Debian系列、suse EnterPrise 11 
- 嵌入式领域：机顶盒、数字电视、网络电视、程控交换机、手机、PDA、 

![IMG_0180](https://ws4.sinaimg.cn/large/006tKfTcly1g1o4dwie46j30uq0lawni.jpg)

## 软件安装和管理

### 一.软件包 

- bin文件，  .bin（适合所有Linux发行版） 可直接运行 
- rpm包   .rpm，yum（Redhat系列—centos同样适用） 
- 源码压缩包（适合所有Linux发行版） 
- 官方已经编译好的，下载软件包可以使用（绿色软件） 

### 二.rpm安装流程 

1. 下载 
2. 检查操作系统是否已经安装该软件 

​                rmp -q 包名  查询指定包是否已经安装 

​                        -i  包名  安装 

​                       -qa         检查已经安装的所有包 

​                        -qi 包名 查询指定包的说明信息 

​                        -ql 包名 查询指定报安装后生成的文件列表 

​                        -qc 包名 查询指定包安装的配置文件 

​                        -qd 包名 查询指定包的帮助文件 

​                        -qf 文件名 查询指定文件是由那个rpm包安装生成的 

3. 安装 

>            rpm -i  /PATH/TO/PACKGE_FILE 

​            rpm -h 以#显示进度，每个#表示2% 

​                   -v 显示安装详细过程 

​                一般$rpm -ivh 

rmp命令：安装过程不需要指定文件路径，rpm文件在制作时已经指定了安装路径。可用rpm -ql 包名 查询。 

(Linux 的软件包只包含核心部分，文件小，但要安装必须依赖于第三方软件） 

### 三.yum   rpm软件包管理 

- 好处：解决rpm下载问题，解决rpm查询问题，解决rpm安装问题，解决rpm依赖问题 
- 需要一个yum源（一堆rmp文件库，如<http://mirrors.163.com/centos/6.9/os/x86_64/Packages/>    http://mirror.centos.org--官方   [mirrors.aliyun.com](http://mirrors.aliyun.com)    [mirrors.163.com](http://mirrors.163.com) ） 
- $yum search mysql-server 
- $yum info mysql-server 
- $yum install mysql-server 安装 
- $yum remove mysql-server 卸载 

​    **自己构建yum源（局域网内部），需要准备各种rpm软件包。在别的yum源下载rpm文件到本地；自己的yum源可以让局域网内部别的机器访问。**

- Redhat公司拥有rpm版权
- [mirrors.163.com](http://mirrors.163.com) centos帮助文档

1. 备份 /etc/yum.repos.d/CentOS-Base.repo $mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup。备份文件不起作用
2. 163上下载 CentOS6-Base-163.repo，放到/etc/yum.repos.d/ 
3. $yum clean all清理原来的缓存
4. $yum makecache 建立163yum源的数据缓存

### 四.yum其他命令

- list
- repolist 下面是repo列表及其简要信息

​                            all 

​                            enabled（默认）

​                            disabled

- 组 group

### 五.本地yum源

- rpm文件获得方式：下载；光盘
- 光盘使用方式

1. 挂载  $mount /dev/cdrom  /mnt(或/media)/repos  （可以先在/mnt下建一个目录 repos）
2. 挂载完之后ls  /mnt/repos/Packages   就能看到很多rpm文件

- 要使用本地yum源，要修改/etc/yum.repos.d/

1. 备份原有的 .repo文件（用cp 备份）

2. 改名（MyCentOS-Base.repo）

	

### 六.下载163上的yum源 

- reposync 
- $ reposync -r base -p 目标目录       base 仓库名 
- 改 CentOS****.repo文件配置 

### 七.Linux网络文件共享 

## Linu文件系统

### 1.Linux下的文件系统和Windows下的文件系统不同： 

​    Linux下的文件系统为 rootfs：根文件系统 /，即所有的文件目录都来自于一个根文件目录； 

​    Windows下有多个根目录，如C:    D:     F： 

### 2./ 下的目录列表 

![screenshot](https://ws4.sinaimg.cn/large/006tKfTcly1g1o4oyh23aj30su03sdgg.jpg)

![IMG_0183](https://ws2.sinaimg.cn/large/006tKfTcly1g1o4p482r2j30lq0kt48k.jpg)

![IMG_0184](https://ws3.sinaimg.cn/large/006tKfTcly1g1o4p93zsoj30ly04k0t1.jpg)

- ​     /boot 系统启动相关的文件 
- ​    /media  Linux中，要读取任何设备中的数据都要先挂载（包括硬盘，但是硬盘在安装时已经挂载） 

​                 挂载命令 $mount 源文件(设备）（如/dev/cdrom)  目标目录（/media/) 

- ​    /usr 一般放安装的第三方文件夹 
- ​    /lost+found 一般为空，系统非正常关机时存放的文件 

3.  . 当前目录    ..上级目录 

4.一个文件有三种时间：最后一次访问时间（Access），最后一次修改时间（内容改动）（Modify），最后一次改变时间（文件的改动，包括内容和元数据）（Change） 

5.一个文件有两种数据：内容数据：文件内容本身；元数据：除了内容数据之外（如文件大小、文件名等） 

6.~代表用户的home目录 

## Linux命令

### **一.Linux两个重要基本原则：**

​                        一切皆文件。 

​                         配置文件保存为纯文本格式。 

### **二.用户接口：shell （**和用户交互的应用程序） 

​          所有操作系统的接口都分为两大类： 

​                GUI接口：图形桌面接口（Graphic User Interface） 

​                        Windows下的GUI接口：explorer.exe 

​                        Linux下的GUI接口：centos—>KDE 

​                      （X-Window:Gonome, KDE, Xface） 

​                CLI接口：命令行用户接口（Command Line Interface) 

​                        Windows: cmd应用程序 

​                        Linux：bash(一个用户启动一个） 

​                                    sh 

​                                    csh  

​                                    ksh  

​                                    zsh  

​                                    tcsh 

​                         

​        接口是操作系统自带的，属于应用程序 

**三.命令提示符 $   #**

​     **命令：**应用程序的执行入口文件（一个应用程序有多个执行入口，执行入口：例如Windows下的.exe文件） 

​                Linux下每个命令都有其对应的执行文件，用$whereis 命令   可以找到执行文件所在路径 

### **四.命令格式**

​        命令主体 选项1 （选项2 选项3……） 参数1 参数2 参数3...... 

- 命令：大多数命令可以省略选项和参数 
- 选项：-或--开头 

​                            短选项： - 

​                                        多个选项可以组合： -a -b和-ab一样 

​                            长选项： — 

​                    命令创建者自行决定是长选项还是短选项 

- 参数：命令的作用对象 

​                假如参数带空格，用“”或’’ 

### **五.如何学习命令**

​    命令类型：内置命令（shell内置，即用户接口自带的命令），内部，内建 

​                      外部命令：在文件系统的某个路径下有一个与命令名称相应的可执行文件

​    **获得命令的使用帮助：** 

​        $ type 命令 可看是否为内置命令

​        $ help 内置命令  内部命令官方文档   [可省略]

​        $ man 外部命令  外部命令官方文档

​        $ 外部命令 -- help 查看（部分）外部命令中文文档 

​                翻屏操作： 空格：往后翻一屏； b：向前翻一屏；J/ENTER：向后翻一行；k：向    前翻一行 

​                查找操作：输入 /关键字 再回车；  n：下一个；N：前一个 

​                q：退出 

​            使用man分章节查看帮助手册（文档的类别，或者说帮助手册的类别）： 

​                1：用户命令（/bin,/usr/bin,/usr/local/bin) 

​                5：查看文件格式 

​                8:管理命令（/sbin,/usr/sbin,/usr/local/sbin)  ifconfig——管理命令 

​                 如 $ man 8 ifconfig 

![IMG_0183](https://ws2.sinaimg.cn/large/006tKfTcly1g1o4q0dfjij30lq0kt48k.jpg)

### **六.命令**

1. ls(list) 显示文件列表

​          选项： 

- -l 长格式显示文件列表（文件详细信息） 

​         1）文件类型： - ：普通文件 

​                                  d :  目录文件 

​                                  b ：块（字节）设备文件 

​                                  c ：字符设备文件 

​                                   l ：符号链接文件（symbolic link file），例如快捷方式 

​                                  p ：命令管道文件（pipe）       

​                                  s ：套接字文件（socket）（网络文件） 



​          【 块（字节）设备、字符设备：根据设备的数据类型的设备分类（另一种分类方式输入输出设备是按照功能分类）例如 键盘是字符设备，显示器是字符设备，光驱是块设备，硬盘是块设备。】 

​          2）权限 

​               9位，分3组(U,G,O)，每组3位。每一组（rwx（读、写、执行），-表示无此权限）。第一组：文件的属主用户权限，第二组：文件的属组用户权限，第三组：其他用户权限） 

​          3）数字表示文件硬链接的次数 

​          4）文件的属主(owner) 

​          5）文件的属组(group)（owner的所属的组群） 

​          6）文件的字节大小 

​          7）时间：最近一次被修改的时间 

![screenshot](https://ws1.sinaimg.cn/large/006tKfTcly1g1o4qa5sagj30us07sn21.jpg)

- -a ：显示以 . 开头的隐藏文件 

​            .表示当前目录 

​            ..表示父目录 

- -d：显示目录自身属性 
- -R:递归显示 

![screenshot](https://ws3.sinaimg.cn/large/006tKfTcly1g1o4qmzmg6j30ku0jujsw.jpg)

​    2.cd 切换目录 

​                ~/username ,主目录，家目录 

​        cd ~/username 进入指定用户的家目录 

​        cd -：在当前目录和前一次所在的目录之间来回切换 

## 基本命令

- Linux三种引号 

​           单引号 ‘’ 字符串 

​           双引号  “" 有特殊符号会进行变量替换 

​            反引号`` 命令替换（即把内容当做命令执行） 

- ifconfig 看IP地址 
- shutdown now 关机 
- reboot 重启 
- printenv 查看环境变量 
- vim ~/.bashrc 改环境变量 
- date  操作系统时间（操作系统启动才有，CPU） 

​                date [OPTION]... [+FORMAT]——修改显示格式 

​                        $ date +%d/%m/%Y 

​               date [-u|--utc|--universal] [MMDDhhmm[[CC]YY][.ss]]——设置系统时间 

- hwclock 操作硬件时间（例如台式机的主板、纽扣电池，开关机不影响） 
- tzselect 修改时区 
- echo打印命令 
- whoami 打印当前用户 
- useradd 创建用户 
- whereis 命令    查询命令文件 
- printenv 查看环境变量 

![screenshot](https://ws2.sinaimg.cn/large/006tKfTcly1g1o4rd49hmj30y6046764.jpg)

## 文件命令

- cp 同时copy多个文件或目录 

​       cp [OPTION]... [-T] SOURCE DEST （可以改名） 

​       cp [OPTION]... SOURCE... DIRECTORY 

​       cp [OPTION]... -t DIRECTORY SOURCE… 

​            (不是原生cp，而是 cp -i） 

​            $ cp 源文件 ./ 复制到当前目录下 

- mv 移动 

​              $mv 1.txt 2.txt  相当于重命名   (一般文件备份，重命名为xxx.backup) 

​              $mv 2.txt x/   移动到x目录下 

- ls  （不是原生的ls，而是ls --color=auto,要使用原生的ls需要在前面加\） 
- mkdir 创建目录 

​            -p  或 --parent   创建目录，如果没有父目录，同时创建父目录，如果父目录已存在也不报错 

​            $mkdir x/{y,z} 在x下创建同级目录 y z 

- rm 删除文件 

​            -f 强制删除 

​            -i 删除前要确认 

​            -r 递归删除目录及其内容 

​            $ rm -rf  ./* 删除当前目录下所有文件 

- touch

​            新建文件 

​            将每个文件的访问时间和修改时间改为当前时间。 

​            不存在的文件将会被创建为空文件，除非使用-c 或-h 选项。 

- vi命令 
- pwd 查看文件路径 
- nano 编辑文件（没有vi那么强大） 
- stat 
- file 
- cat 查看文件（小文件） 
- tac 倒叙看文件 
- more 分屏查看文件（大文件）空格翻屏 
- less 查看文件（小文件） 
- tail 查看文件尾部 默认后十行 

​            -数字  查看的行数 

​            -f 实时更新 

- head 查看文件头部前十行 

​             -数字 

- find 查找文件 

​            $find pass*     查找以pass文件名开头的文件 *代表任意字符（还可以加路径） 

- grep查找文件内容 

​            $grep ‘^rpm’ /home/xyp/1.txt    在文件中查找 以rpm开头的行（^rpm Linux的正则表达式，rpm​$ 以rpm结尾的行） 

- | 管道 把左边的执行结果传到右边进行操作。常常结合别的命令使用。 

​            命令1|命令2|命令3|…… 

​             $head -9 /home/xyp/1.txt | tail -1  查看文件第九行 

​            $ls /home/ | grep xxx 在home下查找文件名包含xxx文件 



- du 列出文件大小 
- chmod 修改文件权限 

**文本处理命令** 

- cut 切割文本（字符串） 

​                -d分界符   -f获取片段 

​                $ echo “1,2,3,4,5,6,7,8” | cut -d”,” -f1-8    -d”,"以逗号切割  -f获取（-f1-8 ：获取前八个片段，-f1获取第一个，-f1,8获取第一和第八。  -表示到   ,表示和   可组合使用） 

- sort 排序 

​            -n 数值排序   

​            -r 降序排序 

- join 连接 
- sed

​            $sed 's/before/after/g’ 文件名     查找替换文件中的before（s是内部命令） 

- awk
- tar 解压或者压缩  

​            -z gzip 

​            -J lzip xz lzma 

​            -c 创建新的归档（压缩包） 

​            $tar -zcvf  文件名.tar.gz  要压缩的目录    压缩 

​            $tar -zxvf  压缩包名                                  解压 

- unzip  解压.zip文件 

- **网络拷贝命令**

1.NFS 网络文件系统 

2.命令 

- scp 拷贝文件 

​             -r 递归拷贝 

拷贝过去

![8E1C0920-190E-4119-BF71-34FCEF2FCCB3](https://ws3.sinaimg.cn/large/006tKfTcly1g1o4z6w7jkj30oc01cwel.jpg)

![screenshot](https://ws2.sinaimg.cn/large/006tKfTcly1g1o4zil3oxj30he03u74l.jpg)

拷贝过来

![screenshot](https://ws1.sinaimg.cn/large/006tKfTcly1g1o4zsiin0j30qe042t9g.jpg)

- grep: <https://www.cnblogs.com/flyor/p/6411140.html> 
- wc 该命令统计给定文件中的字节数、字数、行数。如果没有给出文件名，则从标准输入读取。wc同时也给出所有指定文件的总统计数。字是由空格字符区分开的最大字符串。<https://blog.csdn.net/shift_wwx/article/details/80736335> 

## 系统管理命令

进程：一个应用程序可能运行多个进程。只有进程才能向操作系统申请内存资源。 

​        守护进程（后台运行的进程，终端控制的进程），非守护进程 

- ps 查看系统进程

​        -a 显示终端上所有进程，包括其他用户的进程 

​        -u显示进程的详细情况 

​        -x显示没有控制终端的进程 

​        -w显示加宽，以便显示更多信息 

​        -r只显示正在运行的进程 

​    一般都是$ps -aux 

- top   
- kill 杀死进程 

​        $kill -9 进程id 

​        $killall tail 杀死所有tail进程 

- reboot 重启 
- shutdown 关机 
- init 0 关机  init 6重启 
- df查看磁盘空间 
- fdisk磁盘分区 

​            -l 查看磁盘分区情况 

硬盘:支持热插拔（不用关机重启就可以识别），只要分区、格式化、挂载就可以使用了。 

- du 查看磁盘占用空间或目录占用空间 
- ifconfig 查看配置网卡信息

​            $ifconfig eth0 up/down  把接口eth0重启 

​            （$service network restart  把所有网络接口重启） 

一个网卡可以有很多个网络接口 

- ping 测试远程主机连通性（  先改动 /etc/resolv.conf） 

- netstat -ntpl 查看网络情况

	

## **用户管理命令**

- useradd  

​            -g 指定基本组id 

​            -G指定附加组id 

​            -u 指定用户id 

​            -d指定用户家目录 

​            -s指定用户登录的shell（不指定，默认是bash） 

- userdel 删除用户 
- usermod 用户修改 

​            -u 改id 

​            -g 改组群id 

- passwd 改密码 

​            $passwd 用户名（没有用户名默认为root） 

​            $echo “123123” | passwd —stdin 用户名 

- chsh 改用户默认shell 

​            

- chfn 
- finger 查看账户信息 
- id 看用户id号 
- change 

​            改密码有效期 

- groupadd 添加组 
- chmod 修改用户权限（rwx rwx rwx） 

​            $chmod o+w 文件   文件的其他用户权限改为可写 

​            $chmod g+w                      组群 

​            $chmod 666 文件   文件所有权限改为可读可写 

​            $chmod -R o+w 目录  修改目录下所有文件的权限 

- chown 修改文件拥有者   

​            $chown laoxiao:xyp 1.txt 把文件拥有者改成laoxiao组下的xyp 

## init命令

![screenshot](https://ws4.sinaimg.cn/large/006tKfTcly1g1o51pbiqdj30w007mjsn.jpg)

init 命令 

init 0 关机  

init 1 单用户模式 

Init 2 3 多用户模式 

init 4 预留的 

Init 5 桌面运行模式 

init 6 重启 

- 专门用于编辑文本文件 
- vim是vi衍化版 



## vi vim

一.vi的三种基本工作模式： 

​    1.命令模式 

- 除了一些特殊键，键盘的任意一个键都当做编辑命令 

​    2.输入模式 

- 除了一些特殊键，键盘的任意一个键都当做文本内容 

​    3.末行模式 

- 除了一些特殊键，键盘的任意一个键都当成“文本管理命令” 

​    三种模式之间的转换 

- 命令模式进入输入模式： 

​                i：插入光标前一个字符 

​                I：插入行首 

​                a：插入光标后一个字符 

​                A:插入行末 

​                o: 向下新开一行，插入行首 

​                O：向上新开一行，插入行首 

- 输入转命令  esc 
- 命令转末行  

​            ：w 存盘 

​            ：wq存盘退出 

​            ：q！不存盘，强制退出 

- 末行转命令 两次esc 

二.命令模式下光标的移动  

- h j k l 左下上右 
- 0 移动到行首  ^移动到相对行首 $移动到行尾 
- G 移动到最末尾一行    gg移动到第一行     数字+gg或数字+G 移动到指定行（$vim 1.txt +10 打开文件时光标移到第十行） 
- w或W 单词之间的移动 



三.删除命令 

- x删除光标所在字符 
- X删除光标前一个字符，相当于delete键 
- dd 删除行 
- 数字+dd 多行删除 
- dw光标开始到整个单词结束 
- d0删除光标前本行所有内容，不包括光标 
- 数字+x 删除从光标开始 （数字） 个字符 

四.撤销 

- u撤销 
- Ctrl+r 反撤销 

五.重复命令  . 

六.保存退出 

​    命令模式下：ZZ 

​    末行模式下： wq保存退出   x保存退出   q!不保存退出 

六.  

- v 选中 
- \> 缩进 
- yy复制一行  数字+yy 多行复制 yw复制光标所在到单词结束 
- P粘贴 
- /关键字  （光标所在向后）查找 
- ?关键字 （光标所在向前）查找 
-  s查找替换 
-  !加命令 

末行模式  :.,$-3y  意思  .光标所在行  ,到  $最后一行  $-3最后一行所在的前三行（倒数第四行） y复制 

​                 :1,4d 删除1-4行 

​                 :%s/key1/key2/g     %整个文件 s查找替换        /key1/key2 key1替换成key2  /g全局（i忽略大小写） 

三. 

$vim ~/.vimrc 配置用户的vim（一般不直接修改操作系统的配置文件 即/etc/.vimrc 






















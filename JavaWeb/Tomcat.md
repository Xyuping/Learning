## 程序架构

- C/S 客户端-服务器端
- B/S 浏览器-服务器端

### 客户端

含有部分代码,服务器更新客户端也要更新.用户体验号

### 服务器

配置高级的计算机

#### web服务器软件

客户端在浏览器的地址栏输入地址,然后web服务器软接收请求并响应.

处理客户端的请求,返回资源/信息

常见的web应用(需要服务器支撑)

- Tomcat(apache),免费
- weblogic(BEA),收费
- websphere(IBM),收费
- IIS(微软)

## Tomcat

#### 安装

1.https://tomcat.apache.org下载压缩包

2.解压,找到bin目录下的startup(如果是windows点startup.bat,如果是Linux双击startup.sh),如果双击后窗口一闪而过,一般是jdk环境变量没有配置

3.安装成功后,验证:  浏览器打开http://localhost:8080

### Tomcat目录介绍

- bin:包含一些jar,bat文件
	- startup.bat
	- shutdown.bat
- conf:tomcat的配置
	- server.xml
	- web.xml
- lib:tomcat运行所需的jar文件
- logs:运行的日志文件
- temp:临时文件
- webapps:发布到tomcat上的项目,就存放在这个目录
	- 比如你有一个网页想让别人访问他,就可以把项目放在这个目录下
	- webapps下的每个文件夹看做一个项目
- work:jsp翻译成.class文件存放地

### 如何把一个项目(文件)发布在tomcat中

需求:如何把一个项目(文件)发布在tomcat中让别人访问

三种方法:

1. #### 拷贝这个文件到webapps下

  - 新建一个文件夹(不推荐直接放在ROOT文件夹下),浏览器输入http://localhost:8080/01_test/stus.xml  ;     http://localhost:8080 对应的是ROOT目录;   http://localhost:8080/01_test 对应/webapps/01-test

2. #### 配置虚拟路径

  1. 打开http://localhost:8080/docs/,点击进入configuration->context,对应地址路径http://localhost:8080/docs/config/context.html
  2. 打开conf目录下的server.xml文件,在<Host>标签下添加<Context>标签
  3. <Context path="/a" docBase="/Users/xyp/Documents/workspace/xml_practice/bin"  reloadable="true"></Context>
  4. docBase:项目路径地址,path:对应的虚拟路径

3. #### 配置虚拟路径

  在tomcat/conf/Catalina/localhost/文件夹下新建一个xml文件,名字可以自己定义.在文件中写入

  ```xml
  <?xml version='1.0' excoding='utf-8' ?>
  <Context docBase="文件路径"></Context>
  ```

  在浏览器中访问 http://localhost:8080/person/stus.xml (其中person是在localhost下新建的文件名,stus.xml是要访问的文件)

### 在eclipse中配置tomcat环境

![屏幕快照 2018-10-11 下午4.01.26](https://ws4.sinaimg.cn/large/006tKfTcly1g186ccrbi8j31pg0nsqcm.jpg)

server里面右键新建一个服务器,选择apache分类,找到对应tomcat版本,一步步配置

### 解析Tomcat内部结构和请求过程

转载：<https://www.cnblogs.com/zhouyuqin/p/5143121.html>
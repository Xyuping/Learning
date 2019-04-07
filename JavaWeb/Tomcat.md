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

以下内容摘自<https://www.cnblogs.com/zhouyuqin/p/5143121.html>

> 组件的生命线，Lifecycle接口，组件实现了这个接口就可以被拥有它的组件控制。最高的组件是Servlet，而控制Servlet 的就是Startup，也就是启动和关闭 tomcat。

> Connector：
>
> 最重要的功能是接收连接请求然后分配线程让Container来处理请求。多线程的处理是Connector 设计的核心。
>
> 一个 connector 将在某个指定端口上侦听客户请求，创建request 和 response 对象，产生一个线程来处理这个请求并把产生的 request 和 response 对象传给Engine，然后从Engine 中获得响应并返回给客户。
>
> tomcat 中两个经典的 connector：
>
> 1. Cotote HTTP/1.1 在端口8080侦听来自浏览器的 http 请求
> 2. Cotote JK2 在端口8009侦听其他Web Server 的Servlet/JSP请求

>Containner
>
>Container是容器的父接口，该容器的设计用的是典型的责任链的设计模式，它由四个自容器组件构成，分别是Engine、Host、Context、Wrapper。这四个组件是负责关系，存在包含关系。通常一个Servlet class对应一个Wrapper，如果有多个Servlet定义多个Wrapper，如果有多个Wrapper就要定义一个更高的Container，如Context。 
>Context 还可以定义在父容器 Host 中，Host 不是必须的，但是要运行 war 程序，就必须要 Host，因为 war 中必有 web.xml 文件，这个文件的解析就需要 Host 了，如果要有多个 Host 就要定义一个 top 容器 Engine 了。而 Engine 没有父容器了，一个 Engine 代表一个完整的 Servlet 引擎。
>
>- Engine 容器 
>	Engine 容器比较简单，它只定义了一些基本的关联关系
>- Host 容器 
>	Host 是 Engine 的子容器，一个 Host 在 Engine 中代表一个虚拟主机，这个虚拟主机的作用就是运行多个应用，它负责安装和展开这些应用，并且标识这个应用以便能够区分它们。它的子容器通常是 Context，它除了关联子容器外，还有就是保存一个主机应该有的信息。
>- Context 容器 
>	Context 代表 Servlet 的 Context，它具备了 Servlet 运行的基本环境，理论上只要有 Context 就能运行 Servlet 了。简单的 Tomcat 可以没有 Engine 和 Host。Context 最重要的功能就是管理它里面的 Servlet 实例，Servlet 实例在 Context 中是以 Wrapper 出现的，还有一点就是 Context 如何才能找到正确的 Servlet 来执行它呢？ Tomcat5 以前是通过一个 Mapper 类来管理的，Tomcat5 以后这个功能被移到了 request 中，在前面的时序图中就可以发现获取子容器都是通过 request 来分配的。
>- Wrapper 容器 
>	Wrapper 代表一个 Servlet，它负责管理一个 Servlet，包括的 Servlet 的装载、初始化、执行以及资源回收。Wrapper 是最底层的容器，它没有子容器了，所以调用它的 addChild 将会报错。 
>	Wrapper 的实现类是 StandardWrapper，StandardWrapper 还实现了拥有一个 Servlet 初始化信息的 ServletConfig，由此看出 StandardWrapper 将直接和 Servlet 的各种信息打交道。



Tomcat 的体系结构：

![img](https://ws4.sinaimg.cn/large/006tNc79ly1g1rm8u2v9lj30p10gugm1.jpg)

![img](https://ws3.sinaimg.cn/large/006tNc79ly1g1rma1ln9yj30mw0f2t92.jpg)

> Tomcat Server处理一个HTTP请求的过程

![img](https://ws4.sinaimg.cn/large/006tNc79ly1g1rluw2juhj30kv0awgmb.jpg) 
　　　　　　　　　　　　　　图三：Tomcat Server处理一个HTTP请求的过程



> 1、用户点击网页内容，请求被发送到本机端口8080，被在那里**监听**的Coyote HTTP/1.1 Connector获得。 
> 2、Connector把该请求交给它所在的Service的**Engine**来处理，并等待Engine的回应。 
> 3、Engine获得请求localhost/test/index.jsp，匹配所有的**虚拟主机**Host。 
> 4、Engine匹配到名为localhost的Host（即使匹配不到也把请求交给该Host处理，因为该Host被定义为该Engine的默认主机），名为localhost的Host获得请求/test/index.jsp，匹配它所拥有的所有的**Contex**t。Host匹配到**路径为/test的Context**（如果匹配不到就把该请求交给路径名为“ ”的Context去处理）。 
> 5、path=“/test”的Context获得请求/index.jsp，在它的mapping table中**寻找出对应的Servlet**。Context匹配到URL PATTERN为*.jsp的Servlet,对应于JspServlet类。 
> 6、**构造HttpServletRequest对象和HttpServletResponse对象**，作为参数调用JspServlet的**doGet（）**或**doPost（）**.执行业务逻辑、数据存储等程序。 
> 7、Context把执行完之后的HttpServletResponse对象返回给Host。 
> 8、Host把HttpServletResponse对象返回给Engine。 
> 9、Engine把HttpServletResponse对象返回Connector。 
> 10、Connector把HttpServletResponse对象返回给客户Browser。
---
typora-root-url: ../../笔记
---



# Servlet

## http协议

### 介绍

### 抓包

#### 请求数据解析

![IMG_0955(20190209-133525)](/Servlet/img/IMG_0955(20190209-133525).jpg)

1. 请求行

  > POST  /examples/servlets/servlet/RequestParamExample Http/1.1

  - 请求方式POST(常用的还有GET),表示已 post 去提交数据
  - 请求的地址路径(访问哪个地方)：/examples/servlets/servlet/RequestParamExample
  - HTTP/1.1  协议版本

2. 请求头（先于请求体被服务器接收）
  1. Accept:客户端向服务器端表示我能支持什么类型的数据

  2. referer:真正请求的地址路径,全路径

  3. accept-language:支持语言格式

  4. user-agent:向服务器表明当前来访的客户端信息

    > MSIE 8.0 IE ： 浏览器8.0版本
    >
    > Windows NT 6.1；WOW64 ：64位Windows系统

    - 拓展:为什么在浏览器中输入同一个路径,pc端和移动端得到的网页不一样?

      > 服务器根据user-agent判断请求方类型(判断访问的是 PC 端还是移动端),返回不同页面（服务器有两套网页）

  5. content-type:提交的数据类型，本例中是经过urlencoding编码的form表单数据

  6. accept-encoding:gzip,deflate:压缩算法(服务器返回给客户端的数据是压缩了的)

  7. host:主机地址

  8. content-length:数据长度

  9. connection:keep-alive 保持连接

  10. cache-control:对缓存的操作。no-cache：直接越过缓存向服务器请求数据。

3. 请求体
  1. 浏览器真正发送给服务器的数据
  2. 发送的数据呈现的是key=value形式,多个数据用&连接

#### Http响应数据解析

![C971313C119BEC4B065DEF3BD7568D53](/Servlet/img/C971313C119BEC4B065DEF3BD7568D53.jpeg)

1. 响应行

  - HTTP /1.1：协议版本      


  - 200:状态码(本次交互结果)

  > 200:成功,正常处理得到数据
  >
  > 403:for biddern 拒绝
  >
  > 404:Not Found
  >
  > 500:服务器异常
  >
  >  
  >
  > 一般：
  >
  > 1xx：Informational
  >
  > 2xx：成功
  >
  > 3xx：重定向
  >
  > 4xx：客户端错误
  >
  > 5xx：服务器端错误

  - OK:对应前面的状态码

2. 响应头

- Server: 服务器是哪种类型. Tomcat
- Content-Type:服务器返回给客户端的内容类型和字符编码
- content-length：返回的数据长度
- Date：通讯的日期，响应的时间

#### Get和Post请求区别

![F5E1B2426CF957158C5D22E7287A8AED](/Servlet/img/F5E1B2426CF957158C5D22E7287A8AED.png)

form表单提交方式（<form method='POST/GET'></form>）

> 区别:
>
> 1. 请求路径不同。post请求在URL后面不跟上任何数据,get请求 在url后面加上  <u>?数据内容</u>
> 	- POST：  http://localhost:8080/examples/servlets/servlet/RequestParamExample
> 	- GET: http://localhost:8080/examples/servlets/servlet/RequestParamExample?fistname=zhang&lastname=san
> 2. 带上的数据不同.post请求使用流的方式传输数据,get请求在地址栏上跟数据
> 3. 由于post请求用流的方式写数据,所以一定要一个content-length的头来说明数据长度有多少

- get请求在地址栏后面加数据,有安全隐患,现在一般不用get,用post
- 一般从服务器获取数据,并且客户端不需要提交数据时,可以用get
- get能够带的数据有限,1kb大小.而post用流的方式写数据,数据大小没有限制



#### Web资源

1. 静态资源
	- html,js,css
2. 动态资源
	- servlet/jsp



## Servlet

### servlet是什么

是一个小型java程序,运行在我们的web服务器上（如 tomcat）,用于接收和响应客户端的http请求,更多用在动态资源

客户端访问服务器端,无论是静态资源还是动态资源都要用到servlet.只不过静态资源用到的servlet是Tomcat里面已经定义好的一个DefaultServlet

Tomcat可看做servlet的容器

servlet是一个接口,在java代码中实现

### servlet的简单使用

1. 得写一个web工程,要有一个服务器
2. 测试运行web工程 
  1. 新建一个Java类,实现servlet接口

  	- ![](/Users/xyp/Desktop/JavaWeb/笔记/Servlet/img/屏幕快照 2018-10-18 上午11.19.21.png)

  2. 配置servlet,用意是告诉Tomcat服务器我们的应用有这么些个servlet

  	- 在项目的WEB-INF/web.xml里添加下面内容

  	- ```xml
  		<servlet>
  		  <!-- 向服务器(tomcat)报告这个应用里有这个servlet,名字叫做a,具体路径是 HelloServlet -->
  		  	<servlet-name>a</servlet-name>
  		  	<servlet-class>HelloServlet</servlet-class><!--全路径 包名.类名  -->
  		  </servlet>
  		  <!-- 注册servlet的映射. servletName:找到上面注册的具体servlet, url-pattern:在地址栏上的path -->
  		  <servlet-mapping>
  		  <servlet-name>a</servlet-name>
  		  <url-pattern>/h</url-pattern>
  		  </servlet-mapping>
  		```

![屏幕快照 2018-10-18 上午11.10.36](/Servlet/img/屏幕快照 2018-10-18 上午11.10.36.png)



![屏幕快照 2018-10-18 上午10.58.39](/Servlet/img/屏幕快照 2018-10-18 上午10.58.39.png)

在地址栏上输入   <u>http://localhost:8080/项目名称/h</u>   即可访问



### servlet执行过程

> url:http://localhost:8080/HelloWeb/h

1. 找到tomcat应用 http://localhost:8080
2. 找到项目HelloWeb
3. 找到web.xml,然后在里面找到<url-pattern>,有没有哪一个pattern的内容是 /h
4. 在这个<servlet-mapping>下找到<servlet-name>
5. 找到上面定义的<servlet>元素中的<servlet-name>,对应刚刚找到的<servlet-mapping>下的 <servlet-name>
6. 找到下面定义的<servlet-class>,然后创建该类的实例
7. 继而执行该servlet中的service方法



### Servlet通用写法

体系结构

>- Servlet(接口)
>	-  GenericServlet(接口实现类):通用实现
>		-  HttpServlet(抽象类):用于处理Http请求  用extends

1.  extends HttpServlet
2. 配置 web.xml
3. 重写 doGet  和 doPost 方法
  1. Get请求会来doGet方法
  2. Post请求会来doPost方法
  3. 注意删掉super



### Servlet生命周期

生命周期方法

>1. init()
>- 初始化方法,创建servlet实例时执行.默认情况下是初次访问servlet时 创建实例.一个servlet只会执行一次
>- 重新在浏览器访问也不会再运行
>2. service()
>- 只要客户端来了一个请求就会执行一次(比如说浏览器地址栏按一次回车就执行一次)
>3. destory()
>- servlet销毁时调用.
>  1. 该项目从tomcat中移除
>  2. 正常关闭tomcat（比如用 bin 下的 shutdown.bat）
>- 仅关闭浏览器不会调用



#### 初始化提前

默认情况下是初次访问servlet时才执行init方法,有的时候我们可能需要在这个方法里执行一些初始化工作,可能有一些比较耗时的逻辑。这个时候可能会在 init 方法中逗留很久。此时需要让 init 方法的调用时机提前一点。

> 在web.xml配置时,在该<servlet>下添加<load-on-startup>,数值越小,启动时间越早.一般不写负数.从2开始即可.

### ServletConfig

- 可以获取 servlet 在配置的一些信息

```java
ServletConfig config = getServletConfig();

//1.获取 servlet 名称(web.xml 中的 <servlet-name>的文本内容)
String servletName = config.getServletName();

//2.获取指定初始化参数的值
/*
*在 web.xml 中有
<servlet>
	<init-param>
		<param-name>address</param-name>
		<param-value>Beijing</param-value>
    </init-param>
</servlet>
*/
String address = config.getInitParameter("address");

//3.获取所有初始化参数名
Enumeration<String> names = config.getInitParameterNames();
//遍历
while(name.hasMoreElements()){
    String string = (String) names.nextElement();
}
```

#### ServletConfig作用

引入 jar，使用写好的 servlet类，而这个 servlet 类需要提供变量值，所以要求使用这个 servlet 的时候，在注册 servlet 时必须要在 web.xml 里面声明 init-param。

1. 在 web 工程中添加 jar，build path
2. 在 web.xml 中注册 servlet（全路径不能有.class）
3. 填写 init-param



## HttpServletRequest 和 HttpServletResponse

### servlet 路径匹配

1. 全路径匹配： 以 / 开头。 如： /a/b
2. 路径匹配：以 / 开头。 如： /*  /a/*。（  *是通配符，匹配任意文字。）
3. 以拓展名匹配：如：*.拓展名

### ServletContext

- servlet 上下文
- 每个 web 工程都有一个 ServletContext对象，即不管在哪个 servlet 中，获取到的这个类的对象都是同一个。

```java
ServletContext context = getServletContext();
```

- 服务器启动时，会为托管的每一个 web 应用程序创建一个 servletcontext 对象
- 从服务器移除托管或者是关闭服务器，servletContext 删除
- 作用范围：整个项目

作用：

#### 1.获取配置的全局参数

```xml
<web-app>
	<context-param>
		<param-name>address</param-name>
		<param-value>Beijing</param-value>
	</context-param>
</web-app>
```

```java
ServletContext context = getServletContext();
String address = context.getInitParameter("address");
//address = Beijing
```

#### ​2.可以获取 web 应用中的资源

##### 方式一：通过获取资源在 tomcat 中的绝对路径。

> String path = context.getRealPath("");得到的是在 tomcat 下的根路径：/Users/xyp/Desktop/JavaWeb/apache-tomcat-7.0.91/wtpwebapps/ServletContextDemo

![屏幕快照 2019-02-11 下午9.50.23](/Servlet/img/屏幕快照 2019-02-11 下午9.50.23.png)

```java
		ServletContext context = getServletContext();
		//获取资源文件的绝对路径
		String path = context.getRealPath("file/config.properties");
		System.out.println("path="+path);
//path=/Users/xyp/Desktop/JavaWeb/apache-tomcat-7.0.91/wtpwebapps/ServletContextDemo/file/config properties

		//1.创建属性对象
		Properties properties =  new Properties();
		InputStream is = new FileInputStream(path);
		properties.load(is);
		//2.获取 name 的值
		String name = properties.getProperty("name");
		System.out.println("name="+name);
```

##### 方式二：通过getResourceAsStream方法获取资源的流对象



```java
ServletContext context = getServletContext();
		
		//1.创建属性对象
		Properties properties =  new Properties();
		InputStream is = context.getResourceAsStream("file/config.properties");
		properties.load(is);
		//2.获取 name 的值
		String name = properties.getProperty("name");
		System.out.println("name="+name);
```

##### 方式三：根据 classloader 获取工程下的资源

```java
// 1.创建属性对象
		Properties properties = new Properties();
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("../../file/config.properties");
		properties.load(is);
		// 2.获取 name 的值
		String name = properties.getProperty("name");
		System.out.println("name=" + name);
		is.close();
```

注意ClassLoader路径跟 ServletContext不同：

![fullsizerender(1)](/Servlet/img/fullsizerender(1).jpg)

#### 3.存取数据（servlet 间共享数据 域对象）

String name = request.getPatameter("name");

PrintWriter pw =  response.getWriter();

pw.write("xjoladfao")

页面跳转：

> response.setStatus(302);
>
> response.setHeader("Location","xxx.html")

例子：计算登录次数

```java
Object obj = getServletContext().getAttribute("count");
int totalcount = 0;
if(obj!=null){
    totalcount = (int)obj;
}
getServletContext().setAttribute("count",totalcount+1);//更新全局参数 count
```

###  HttpServletRequest

这个对象封装了客户端提交过来的一切数据。

1.取出请求中的头信息

```java
Enumeration<String> headerNames = request.getHeaderNames();//枚举集合

While(headerNames.hasMoreElements){

​	String name = (String) headerNames.nextElement();

​	String value = request.getHeader(name);

}
```

2.获取用户提交的数据

```java
String name = request.getParameter("name");

Enumeration<String> parameterNames=request.getParameterNames();//枚举集合


Map<String,String[]> map = request.getParameterMap();
Set<String> keySet = map.keySet();
Iterator<String> iterator = keySet.iterator();
while(iterator.hasNext()){
    String key = (String) iterator.next();
    String value = map.get(key)[0];
}
```

3.获取中文数据（解决中文乱码问题）

get 请求：

> get 请求发来的数据在 url 地址栏上就已经经过编码了，tomcat 收到了这批数据，getParameter 默认使用 ISO-8859-1去解码

解决方式一：

```java
String username = request.getParameter("username");
//先让文字回到用 ISO-8895-1对应的字节数组，再按 urf-8组拼字符串
username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
```

解决方式二：

​	直接在 tomcat 里面做配置，以后 get 请求过来的数据永远都是用 UTF-8编码

tomcat/config/server 找到<connector ... port="8080" 添加URIEncoding="UTF-8"/>



post 请求：

数据都在请求体中，只需设置请求体的文字编码

解决方式：

```java
request.setCharacterEncoding("UTF-8");//设置请求体
```

### HttpServletResponse

服务器响应客户端

```java
//常用方法
//1.以字符流的方式写数据
response.getWriter().write("内容");
//2.以字节流的方式写数据
response.getOutputStream().write("内容");
//3.设置当前请求的处理状态码
response.setStatus("");
//4.设置一个头
response.setHeader(name，value);
//5.设置响应的内容类型以及编码
response.setContentType(type);

```

#### 1.响应的数据中有中文可能乱码

- 以字符流输出

> response.getWriter().write("内容");
>
> 默认以 ISO-8859-1编码
>
> 不同浏览器使用不同编码方式

```java
//1.指定输出到客户端时使用 UTF-8编码
response.setCharacterEncoding("UTF-8");
//2.直接规定浏览器看这份数据的时候使用什么编码
response.setHeader("Content-Type","text/html;charset=UTF-8");

response.getWriter().write("内容");
```

 

- 以字节流输出

> response.getOutputStream().write("内容");
>
> 如果不想 乱码只需确保出去的时候用的编码和客户端看者这份数据的编码是一样的
>
> 默认情况下 getOutputStream 输出使用的是 UTF-8编码
>
> String中 getBytes 方法没有参数的话默认是 UTF-8码表

```java
//1.指定输出到客户端时使用 UTF-8编码
response.getOutputStream().write("内容".getBytes("UTF-8"));
//2.直接规定浏览器看这份数据的时候使用什么编码
response.setHeader("Content-Type","text/html;charset=UTF-8");

```

- 不管是字节流还是字符流都可以用一行代码解决。

```java
response.setContentType("text/html;charset=UTF-8");
```

### 下载资源

1.直接以超链接的方式下载，不写 servlet 也能下载东西下来。

```html
<a href="">aa.jpg</a>
```

原因是 tomcat 里面有一个默认的 servlet（DefaultServlet），专门用于处理放在 tomcat 服务器上的静态资源。

2.

```java
String filename = request.getParameter("filename");
//获取这个文件在 tomcat 中的绝对路径地址
String path = getServletContext().getRealPath("download/"+filename);

//让浏览器收到这份资源的时候，以下载的方式提醒用户，而不是直接展示
response.setHeader("Content-Disposition","attachment;filename="+filename);

//转化成输入流
InputStream is = new FileInputStream(path);
OutputStram os = response.getOutputStream();

int len=0;
byte[] buffer = new byte[1024];
while((len=is.read(buffer))!=-1){
    os.write(buffer,0,len);
}
os.close();
is.close();
```

中文名称

加一句 fileName = new String(fileName.getBytes("ISO-8895-1","UTF-8"));

不同浏览器

```java
String clientType = request.getHeader("User-Agent");
//火狐浏览器
if(clientType.contains("Firefox")){
​	fileName = DownLoadUtil.base64EncodeFileName(fileName);
}else{
//IE 或谷歌
​	fileName = URLEncoder.encode(fileName,"UTF-8");
}
```

### 请求转发和重定向

#### 重定向

```java
response.sendRedirect("另一个页面.html");
```

#### 请求转发

```java
request.getRequestDispatcher("另一个页面.html").forward(request, response);
```


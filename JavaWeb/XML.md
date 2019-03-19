# XML

## 介绍

可扩展的标记语言 extendsible markup language

作用:

1. 保存数据
2. 做配置文件
3. 数据传输载体(客户端-><-服务器端

#### XML文档结构

树结构

## 定义xml

#### 1.文档声明(第一行)

```xml
<?xml version="1.0"?>---使用什么版本解析器解析
<? xml version="1.0" encoding="utf-8" ?> gbk big5(台湾繁体字)
<? xml version="1.0" encoding="utf-8" standalone="yes/no"?> 是否独立(no-依赖关联其他文档)
```

#### 2.元素(标签)

- 标签成对出现: <></>        <  ... />空标签

- 根标签

- 标签名字自己定义

- ##### 属性

```xml
<stus>
	<stu id="123">
    	<name>张三</name>
    </stu>
    <stu id="456">
    	<name>李四</name>
    </stu>
</stus>
```

- ##### 注释

	<!--注释内容 -->

#### CDATA区

- 五个预定义实体的引用

| &lt ;   | <    |
| ------- | ---- |
| &gt ;   | >    |
| &amp ;  | &    |
| &apos ; | '    |
| &quot ; | "    |

< 和 &  在xml中是非法字符

- CDATA使用:不想让xml解析器去解析的标签或关键字

```xml
<des><![CDATA[<a href="www.baidu.com">百度</a>]]</des>
```

#### XML解析方式

获取元素里面的字符数据或属性数据.

常用的 两种解析方式:

1. DOM(document object model)

	会把整个xml文档读进内存形成树状结构,整个文档称为document对象,元素节点对应Element对象,属性对应Attribute对象,文本为Text对象.以上所有对象都可以称之为Node节点.

	![fullsizerender(2)](/Users/xyp/Desktop/JavaWeb/xml/img/fullsizerender(2).jpg)

2. SAX(simple API for Xml)

	基于事件驱动(读取一行解析一行)

两种那个方式对比:

​	如果文档小,DOM效率高.如果文档大,会造成内存溢出;

​	DOM可以对文档进行删增,SAX不行,智能查阅

针对这两种解析方式的API(一些阻止或者公司针对以上两种解析方式给出的解决方案)

- jaxp (sun公司,比较繁琐)
- jdom
- dom4j(使用比较广泛)

#### dom4j

要导入dom4j.jar

##### 基本使用方法:

Element.element('')

Element.elements()

```xml
<?xml version="1.0" encoding="UTF-8"?>
<stus>
	<stu>
		<name>张三</name>
		<age>18</age>
		<address>深圳</address>
	</stu>
	<stu>
		<name>李四</name>
		<age>28</age>
		<address>北京</address>
	</stu>
</stus>
```



```java
package com.itheima.test;
import java.io.File;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class MainTest{
	public static void main(String[] args) {
		try {
			//1.创建sax读取对象
			SAXReader reader = new SAXReader();
			//2.指定解析的xml源(返回对象时Document对象)
			Document document = reader.read(new File("xml/stus.xml"));
			//3.得到元素(返回对象是Element对象)
			//得到根元素
			Element rootElement = document.getRootElement();
			System.out.println(rootElement.getName());
			//4.根据根元素获取单个子元素
			System.out.println(rootElement.element("stu").element("age").getStringValue());
            System.out.println(rootElement.element("stu").element("age").getText());
			//获取根元素的所有子元素.每个是stu
			List<Element> elements = rootElement.elements();
			for(Element element:elements) {
				String name = element.element("name").getText();
				String age = element.element("age").getText();
				String adress = element.element("address").getText();
				
				System.out.println("name="+name+" age="+age+" adress="+adress);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```

##### dom4j的Xpath

要导入jaxen.jar

/AAA/BBB

//AAA

```java
//Xpath方式获取元素
			//单个
			Element nameElement = (Element)rootElement.selectSingleNode("//name");
			System.out.println(nameElement.getText());
			//多个
			List<Element>list =rootElement.selectNodes("//name");
			for(Element element:list) {
				System.out.println("name:"+element.getText());
			}
```

#### XML约束

- DTD
	- 语法自成一派,早期出现的,可读性较差
	- xml解析器解析起来不方便
- Schema
	- 是DTD的继任者,为了替代DTD
	- 使用xml语法规则,xml解析器解析起来方便
	- 文本内容比DTD多,阅读性差,目前没有真正替代DTD

##### DTD

- DTD元素

```dtd
<?xml version="1.0" encoding="UTF-8"?>
<!ELEMENT stus (stu)+>  (正则表达式,+表示一个或多个;*表示零个或多个;?零或一)
<!ELEMENT stu (name,age,adress)> (元素是有序的)  <!ELEMENT stu (name|adress)>二选一
<!ELEMENT name (#PCDATA)>
<!ELEMENT age (#PCDATA)>
<!ELEMENT address (#PCDATA)>
```

- DTD属性

	<!ATTLIST 元素名称 属性名称 属性类型 默认值 "check">

	默认值:值;#FEQUIRED(属性值是必须的);#IMPLIED(属性值不是必须的);#FIXED value(属性值是固定的)


在XML中引入DTD来约束该XML

```xml
1.引入网络上的dtd文件
				网络    dtd文件名        dtd路径
<!DOCTYPE stus PUBLIC "//UNKNOWN/" "unknown.dtd">
                 本地     
2.<!DOCTYPE stus SYSTEM "stus.dtd" >

3.在xml中内嵌
<!DOCTYPE stus [
    <!ELEMENT stus (stu,stu)>
    <!ELEMENT stu (name,age,address)>
    <!ELEMENT name (#PCDATA)>
    <!ELEMENT age (#PCDATA)>
    <!ELEMENT address (#PCDATA)>
]>

```

##### Schema

约束文档被w3c标准约束,xml文档被xsd文档(schema)约束.xsd:约束文档;xml:实例文档

```xml
<?xml version="1.0" encoding="UTF-8"?>
               <!-- xmlns:全称xnl namespace,名称空间/命名空间,意思是该xsd文件参照w3标准来写 -->
               <!-- targetNamespace:目标名称空间,下面定义的元素都与这个命名空间绑定 -->
               <!-- elementFormDefault:元素格式化情况 -->
<schema xmlns="http://www.w3.org/2001/XMLSchema"  
targetNamespace="http://www.example.org/*teacher" 
xmlns:tns="http://www.example.org/*teacher" 
elementFormDefault="qualified">
</schema>

```

简单元素

```xml
<element name="name" type="string"></element>
<element name="age" type="int"></element>
```

复杂元素

<complextType> <sequence>

```xml
<element name="teacher">
	<complexType>
    	<sequence>
        	<element name="name" type="string"></element>
			<element name="age" type="int"></element>
        </sequence>
    </complexType>
</element>

要写多个元素可以<sequence maxOccur="值"></sequence>其中,值为unbounded就是无限个
```

在xml中引入

```xml
<teachers 
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"     #必须这样写
xmlns="http://www.example.org/teacher"#命名空间.写schema里面顶部目标名称空间,要与schema目标空间一致
xsi:schemaLocation="http://www.itheima.com/teacher teacher.xsd"  xsi:schemaLocation="{namespace}{location}" 
>
```



##### 多个约束规则

- DTD:只能有一个DTD
- Schema:可以有多个schema.命名空间的左右就是在写元素的时候,指定该元素用的是哪一套规则






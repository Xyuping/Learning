---
typora-copy-images-to: ./JavaWeb/笔记/java/img
---

# java

## 概述

### jdk 与 jre

>JRE：是Java语言的运行环境，它包含了Java虚拟机，也就是JVM，同时还包含了Java语言运行需要的核心类库。
>
>JDK：是Java语言的开发工具包，提供了Java语言的开发工具，它里面包含了JRE，同时也就包含JVMJava虚拟机。
>
>所以当你安装JDK之后，其实就不用再安装JRE了。

### 程序编译与运行

##### 未配置环境变量

>​              \* 作用：将程序员写的java源代码生成可以运行的Java程序(.class文件)              \* 过程：
>
>​                     \* a:开启DOS窗口并切换到.java文件所在的目录 比如HelloWord.java存放于d:\234\day01\code 中
>
>​                     \* b:切换到HelloWorld.java所在目录,但是此目录中没有javac命令,所以在编译时要写出javac命令的全路径
>
>​                     \* c: <u>d:\234\day01\code>d:\develop\java\jdk1.7.0_72\bin\javac</u> HelloWorld.java 回车
>
>​                     \* d:在d:\234\day01\code文件夹中多了个HelloWorld.class文件(又叫做字节码文件)

> A：运行程序
>
>​              \* a: 开启DOS窗口并切换到.class文件所在的目录
>
>​              \* b: 此目录中没有java命令,所以在运行时也要写出java命令的全路径
>
>​              \* c: d:\234\day01\code>d:\develop\java\jdk1.7.0_72\bin\java HelloWorld 回车(注意:运行时不用后缀名.class)
>
>​              \* d: 
>
>控制台打印显示结果
>
>"HelloWorld" 

##### 配置环境变量

>A: Path环境变量配置方式一
>
>​         \* a: 安装高级文本编辑器notepad++
>
>​         \* b: 配置Windows的path环境变量
>
>​               \* 环境变量的作用：让Java的bin目录下的javac命令可以在任意目录下执行
>
>​                \* 配置方法：
>
>​                       \* 右键点击计算机  →  选择属性  →  更改设置  →  点击高级  →  点击环境变量  →  找到系统变量中的path  →  将java安装目录下javac所在的bin目录路径配置到path变量中，用；(英文)与其他变量分隔
>
>​                \* 注意：
>
>​                       \* 配置path后文件的访问顺序：先访问当前路径，如果当前路径没有该文件，则再访问path配置的路径
>
>  \* B:配置过程(建议使用这种方式配置)
>
>​         \* a：右键点击计算机  →  选择属性  →  更改设置  →  点击高级  →  点击环境变量  →  创建名为JAVA_HOME的环境变量  →  将jdk所在的目录路径(bin所在的路径)配置到JAVA_HOME变量中
>
>​         *b: 用;与其他变量分隔→在path环境变量中添加%JAVA_HOME%\bin 

#### 文档注释

/**       */

> 对于文档注释，可以被JDK提供的工具 javadoc 所解析，生成一套以网页文件形式体现的该程序的说明文档

### 数据类型

#### 基本数据类型

>整数类型
>
>*十进制表示方式：正常数字   如 13、25
>
> \* 二进制表示方式：以0b(0B)开头    如0b1011 、0B1001 
>
>  \* 十六进制表示方式：以0x(0X)开头   数字以0-9及A-F组成  如0x23A2、0xa、0x10 
>
> \* 八进制表示方式：以0开头   如01、07、0721

#### 数据类型四类八种

o                               *四类      八种     字节数    数据表示范围

o                               *整型      byte            1     -128～127

o                                               short           2     -32768～32767

o                                               int                4     -2147483648～2147483648

o                                               long             8     -263～263-1

o                               *浮点型   float           4     -3.403E38～3.403E38

o                                                double        8     -1.798E308～1.798E308

o                               *字符型   char             2     表示一个字符，如('a'，'A'，'0'，'家')

o                               *布尔型   boolean       1     只有两个值true与false

#### 引用数据类型

##### scanner 类

- Scanner sc = new Scanner(System.in);

- int n = sc.nextInt();

- String str = sc.next();

```java
import java.util.Scanner;
public class ScannerDemo01 {
	public static void main(String[] args) {
		//创建Scanner引用类型的变量
		Scanner sc = new Scanner(System.in);
		//获取数字
		System.out.println("请输入一个数字");
		int n = sc.nextInt();
		System.out.println("n的值为" + n);
		//获取字符串
		System.out.println("请输入一个字符串");
		String str = sc.next();
		System.out.println("str的值为" + str);
	}
}

```

##### Random类

- public int nextInt(int maxValue)  产生[0,maxValue)范围的随机整数，包含0，不包含maxValue；

- public double nextDouble()  产生[0,1)范围的随机小数，包含0.0，不包含1.0。

```java
import java.util.Random;

public class RandomDemo {
	public static void main(String[] args) {
		// 创建Random类的实例
		Random r = new Random(); 
		// 得到0-100范围内的随机整数，将产生的随机整数赋值给i变量
		int i = r.nextInt(100); 
		//得到0.0-1.0范围内的随机小数，将产生的随机小数赋值给d变量
		double d = r.nextDouble(); 
		System.out.println(i); 

```

## 类和接口

> 类是现实事物的描述，接口是功能的集合。

#### 类：

> 构造方法是可以被private修饰的，作用：其他程序无法创建该类的对象。

#### 接口：

> public static final 修饰变量，所有接口中的成员变量已是静态常量，由于接口没有构造方法，所以必须显示赋值。可以直接用接口名访问。
>
> public abstract 修饰方法
>
> 接口可以 extends 多个接口

#### instanceof 关键字

> 格式： 对象名 instanceof 类名
>
> ​                      返回值： true, false
>
> ​                      作用： 判断指定的对象 是否为 给定类创建的对象
>
>  

#### 方法重写和重载

重写：子类

重载：同一类中

### 匿名对象

匿名对象是指创建对象时，只有创建对象的语句，却没有把对象地址值赋值给某个变量。

- 创建一个普通对象：Person p = new Person();

- 创建一个匿名对象：new Person();

### 内部类

#### 成员内部类

>定义格式



class 外部类 { 

​	修饰符 class 内部类 {

 		//其他代码

​	}

}

> 访问方式

外部类名.内部类名 变量名 = new 外部类名().new 内部类名();

#### 局部内部类

> 局部内部类，定义在外部类方法中的局部位置。与访问方法中的局部变量相似，可通过调用方法进行访问

> 定义格式

class 外部类 { 

​	修饰符 返回值类型 方法名(参数) {

​		class 内部类 {

​			//其他代码

​		}

​	}

}

访问方式

在外部类方法中，创建内部类对象，进行访问

例子

```java
 class Party {// 外部类，聚会
	public void puffBall() {// 吹气球方法
		class Ball {// 内部类，气球
			public void puff() {
				System.out.println("气球膨胀了");
			}
		}
//创建内部类对象，调用puff方法
		new Ball().puff();
	}
}

//访问
public static void main(String[] args) {
	//创建外部类对象
	Party p = new Party();
	//调用外部类中的puffBall方法
	p.puffBall();
}

```

#### 匿名内部类（常用）

>最常用到的内部类就是匿名内部类，它是局部内部类的一种。
>
>定义的匿名内部类有两个含义：
>
>1.临时定义某一指定类型的子类
>
>2.定义后即刻创建刚刚定义的这个子类的对象
>
>**作用：**匿名内部类是创建某个类型子类对象的快捷方式。

**格式：**

new 父类或接口(){

​    //进行方法重写

};

```java
//已经存在的父类：
public abstract class Person{
	public abstract void eat();
}
//定义并创建该父类的子类对象，并用多态的方式赋值给父类引用变量
Person  p = new Person(){
	public void eat() {
		System.out.println(“我吃了”);
	}
};
//调用eat方法
p.eat();

```

匿名内部类如果不定义变量引用，则也是匿名对象。代码如下：

```java
new Person(){
	public void eat() {
		System.out.println(“我吃了”);
	}
}.eat();

```



## 修饰符

### final

> 修饰引用类型：变量值为对象地址值，地址值不能更改，但是地址内的对象属性值可以修改。
>
>  修饰成员变量，需要在创建对象前赋值，否则报错。(当没有显式赋值时，多个构造方法的均需要为其赋值。)


注意：如果类用public修饰，则类名必须与文件名相同。一个文件中只能有一个public修饰的类。

## 代码块

>局部代码块：定义在方法中的，用来限制变量的作用范围
>
>构造代码块：定义在类中方法外，一般用来给对象中的成员初始化赋值
>
> 静态代码块：定义在类中方法外，一般用来给类的静态成员初始化赋值

### 构造代码块

>构造代码块是定义在类中成员位置的代码块



特点：

>  优先于构造方法执行，构造代码块用于执行所有对象均需要的初始化动作  每创建一个对象均会执行一次构造代码块。

### 静态代码块

>静态代码块是定义在成员位置，使用static修饰的代码块。

特点：

> 它优先于主方法执行、优先于构造代码块执行，当以任意形式第一次使用到该类时执行。
>
>  该类不管创建多少对象，静态代码块只执行一次。
>
>   可用于给静态变量赋值，用来给类进行初始化。

创建子类对象顺序：父静态代码块->子静态代码块->父构造代码块->父构造方法->子构造代码块->子构造方法

## API

### object 类

#### equals方法

> public boolean equals(Object obj)

用于比较两个对象是否相同，它其实就是使用两个对象的<u>内存地址</u>在比较。Object类中的equals方法内部使用的就是==比较运算符。

在开发中要比较两个对象是否相同，经常会根据对象中的属性值进行比较，也就是在开发经常需要子类<u>重写</u>equals方法根据对象的属性值进行比较。

```java
/*
 * 描述人这个类，并定义功能根据年龄判断是否是同龄人 由于要根据指定类的属性进行比较，这时只要覆盖Object中的equals方法
 * 在方法体中根据类的属性值进行比较
 */
class Person extends Object {
	int age;

//复写父类的equals方法，实现自己的比较方式
	public boolean equals(Object obj) {
		// 判断当前调用equals方法的对象和传递进来的对象是否是同一个
		if (this == obj) {
			return true;
		}
		// 判断传递进来的对象是否是Person类型
		if (!(obj instanceof Person)) {
			return false;
		}
		// 将obj向下转型为Perosn引用，访问其属性
		Person p = (Person) obj;
		return this.age == p.age;
	}
}
```

**注意**：在复写Object中的equals方法时，一定要注意public boolean equals(Object obj)的参数是Object类型，在调用对象的属性时，一定要进行类型转换，在转换之前必须进行类型判断。

String 类已重写过该方法。==比较的是内存地址，equals比较的是值。

#### toString方法

> public String toString()

返回该对象的字符串表示，其实该字符串内容就是对象的类型+@+内存地址值。

由于toString方法返回的结果是内存地址，而在开发中，经常需要按照对象的属性得到相应的字符串表现形式，因此也需要重写它。

### String 类

字符串是常量，一旦这个字符串确定了，那么就会在内存区域中就生成了这个字符串。字符串本身不能改变，但str变量中记录的地址值是可以改变的。

```java
String s3 = "abc";
String s4 = **new** String("abc");
System.*out*.println(s3==s4);//false
System.*out*.println(s3.equals(s4));//true,
//因为String重写了equals方法，建立了字符串自己的判断相同的依据（通过字符串对象中的字符来判断）
```

s3和s4的创建方式有什么不同呢？

l  s3创建，在内存中只有一个对象。这个对象在字符串常量池中

l  s4创建，在内存中有两个对象。一个new的对象在堆中，一个字符串本身对象，在字符串常量池中

![img](https://ws3.sinaimg.cn/large/006tKfTcly1g186ghutc6j30c005gjs3.jpg)

获取部分字符串

![img](https://ws4.sinaimg.cn/large/006tKfTcly1g186gpm7iaj30bq01ywem.jpg)

字符串是否以指定字符串开头、结尾

![img](https://ws2.sinaimg.cn/large/006tKfTcly1g186glf4ggj309u020t8t.jpg)

字符串中是否包含另一个字符串

![img](https://ws2.sinaimg.cn/large/006tKfTcly1g186girqcjj30c00210sw.jpg)

将字符串转成一个字符数组。或者字节数组

![img](https://ws3.sinaimg.cn/large/006tKfTcly1g186gj3ka5j30cl01et8r.jpg)

判断两个字符串中的内容是否相同

![img](https://ws4.sinaimg.cn/large/006tKfTcly1g186ggre97j30bb01z3ym.jpg)

 获取该字符串中指定位置上的字符

> char charAt(int index)

转换大小写

> toUpperCase()
>
> toLowerCase()

获取给定的字符，在该字符串中第一次出现的位置

> int indexOf(String)

### StringBuffer类

字符串缓冲区,支持可变的字符串

![img](https://ws3.sinaimg.cn/large/006tKfTcly1g186gmnaiyj30c005ot9f.jpg)

### StringBuilder类

不保证同步

比 StringBuffer 要快

### 正则表达式

正则表达式的语法规则：

**字符：x**

含义：代表的是字符x

例如：匹配规则为 **"a"**，那么需要匹配的字符串内容就是 ”a”

 

**字符：\\**

含义：代表的是反斜线字符'\'

例如：匹配规则为**"\\"** **，**那么需要匹配的字符串内容就是 ”\”

 

**字符：\t**

含义：制表符

例如：匹配规则为**"\t**" ，那么对应的效果就是产生一个制表符的空间

 

**字符：\n**

含义：换行符

例如：匹配规则为**"\n"**，那么对应的效果就是换行,光标在原有位置的下一行

 

**字符：\r**

含义：回车符

例如：匹配规则为**"\r"** ，那么对应的效果就是回车后的效果,光标来到下一行行首

 

**字符类：[abc]**

含义：代表的是字符a、b 或 c

例如：匹配规则为**"[abc]"** ，那么需要匹配的内容就是字符a，或者字符b，或字符c的一个

 

**字符类：[^abc]**

含义：代表的是除了 a、b 或 c以外的任何字符

例如：匹配规则为**"[^abc]"**，那么需要匹配的内容就是不是字符a，或者不是字符b，或不是字符c的任意一个字符

 

**字符类：[a-zA-Z]**

含义：代表的是a 到 z 或 A 到 Z，两头的字母包括在内

例如：匹配规则为**"[a-zA-Z]"**，那么需要匹配的是一个大写或者小写字母

 

**字符类：[0-9]**

含义：代表的是 0到9数字，两头的数字包括在内

例如：匹配规则为**"[0-9]"**，那么需要匹配的是一个数字

 

**字符类：[a-zA-Z_0-9]**

含义：代表的字母或者数字或者下划线(即单词字符)

例如：匹配规则为**" [a-zA-Z_0-9] "**，那么需要匹配的是一个字母或者是一个数字或一个下滑线

 

**预定义字符类：.**

含义：代表的是任何字符

例如：匹配规则为**" . "**，那么需要匹配的是一个任意字符。如果，就想使用 . 的话，使用匹配规则"\\."来实现

 

**预定义字符类：\d**

含义：代表的是 0到9数字，两头的数字包括在内，相当于[0-9]

例如：匹配规则为**"\d "**，那么需要匹配的是一个数字

 

**预定义字符类：\w**

含义：代表的字母或者数字或者下划线(即单词字符)，相当于**[a-zA-Z_0-9]**

例如：匹配规则为**"\w "**，，那么需要匹配的是一个字母或者是一个数字或一个下滑线

 

**边界匹配器：^**

含义：代表的是行的开头

例如：匹配规则为**^[abc][0-9]$** ，那么需要匹配的内容从[abc]这个位置开始, 相当于左双引号

 

**边界匹配器：$**

含义：代表的是行的结尾

例如：匹配规则为**^[abc][0-9]$** ，那么需要匹配的内容以[0-9]这个结束, 相当于右双引号

 

**边界匹配器：\b**

含义：代表的是单词边界

例如：匹配规则为**"\b[abc]\b"** ，那么代表的是字母a或b或c的左右两边需要的是非单词字符(**[a-zA-Z_0-9]**)

 

**数量词：X?**

含义：代表的是X出现一次或一次也没有

例如：匹配规则为**"a?"**，那么需要匹配的内容是一个字符a，或者一个a都没有

 

**数量词：X\***

含义：代表的是X出现零次或多次

例如：匹配规则为**"a\*"** ，那么需要匹配的内容是多个字符a，或者一个a都没有

 

**数量词：X+**

含义：代表的是X出现一次或多次

例如：匹配规则为**"a+"**，那么需要匹配的内容是多个字符a，或者一个a

 

**数量词：X{n}**

含义：代表的是X出现恰好 n 次

例如：匹配规则为**"a{5}"**，那么需要匹配的内容是5个字符a

 

**数量词：X{n,}**

含义：代表的是X出现至少 n 次

例如：匹配规则为**"a{5, }"**，那么需要匹配的内容是最少有5个字符a

 

**数量词：X{n,m}**

含义：代表的是X出现至少 n 次，但是不超过 m 次

例如：匹配规则为**"a{5,8}"**，那么需要匹配的内容是有5个字符a 到 8个字符a之间

####   字符串类中涉及正则表达式的常用方法

![img](https://ws3.sinaimg.cn/large/006tKfTcly1g186gnte3tj30cl02fq35.jpg)

### Date类

类 Date 表示特定的瞬间，精确到毫秒。

构造方法

![img](https://ws1.sinaimg.cn/large/006tKfTcly1g186gmwxxbj30cl01aq2y.jpg)

![img](https://ws4.sinaimg.cn/large/006tKfTcly1g186gq3me7j30c000ut8n.jpg)

### DateFormat类

DateFormat 是日期/时间格式化子类的抽象类，它以与语言无关的方式格式化并解析日期或时间。日期/时间格式化子类（如 **SimpleDateFormat****类**）允许进行格式化（也就是日期 -> 文本）、解析（文本-> 日期）和标准化。

##### SimpleDateFormat

![img](https://ws4.sinaimg.cn/large/006tKfTcly1g186gl20vjj30c50180sr.jpg)

![img](https://ws3.sinaimg.cn/large/006tKfTcly1g186gt7cz5j30b9023glq.jpg)

```java
代码演示：
//创建日期格式化对象,在获取格式化对象时可以指定风格
DateFormat df= new SimpleDateFormat("yyyy-MM-dd");//对日期进行格式化
Date date = new Date(1607616000000L);
String str_time = df.format(date);
System.out.println(str_time);//2020年12月11日

```

```java
代码演示：
练习一：把Date对象转换成String
     Date date = new Date(1607616000000L);//Fri Dec 11 00:00:00 CST 2020
	DateFormat df = new SimpleDateFormat(“yyyy年MM月dd日”);
	String str = df.format(date);
	//str中的内容为2020年12月11日

练习二：把String转换成Date对象
	String str = ”2020年12月11日”;
	DateFormat df = new SimpleDateFormat(“yyyy年MM月dd日”);
	Date date = df.parse( str );
	//Date对象中的内容为Fri Dec 11 00:00:00 CST 2020

```

### Calendar类

该类将所有可能用到的时间信息封装为静态成员变量，方便获取。

Calendar为抽象类，由于语言敏感性，Calendar类在创建对象时并非直接创建，而是通过静态方法创建，将语言敏感内容处理好，再返回子类对象，如下：

l  Calendar类静态方法

![img](https://ws1.sinaimg.cn/large/006tKfTcly1g186gqm4urj30az014dfs.jpg)

```java
Calendar c = Calendar.getInstance();  //返回当前时间
```

![img](https://ws1.sinaimg.cn/large/006tKfTcly1g186gjqb52j30c003k0t0.jpg)

l  public int **get**(int field) //获取时间字段值，字段参见帮助文档

n  YEAR 年

n  MONTH 月，从0开始算起，最大11；0代表1月，11代表12月。

n  DATE 天

n  HOUR 时

n  MINUTE分

n  SECOND秒

### 基本类型包装类

![img](https://ws4.sinaimg.cn/large/006tKfTcly1g186gvm3xxj30dg03p3yx.jpg)

将字符串转成基本类型：

调用类包中的方法

![img](https://ws1.sinaimg.cn/large/006tKfTcly1g186go9guuj30cl06d0tl.jpg)

#### 将基本数值转成字符串有3种方式：

##### 1.基本类型直接与””相连接即可；34+""

##### 2.调用String的valueOf方法；**String.valueOf(34)** ；

![img](https://ws1.sinaimg.cn/large/006tKfTcly1g186gndlf0j309w06nt9g.jpg)

##### 3.调用包装类中的toString方法；**Integer.toString(34)** 

![img](https://ws4.sinaimg.cn/large/006tKfTcly1g186gibf1bj30aa08imy7.jpg)

#### 基本类型和对象转换

使用int类型与Integer对象转换进行演示，其他基本类型转换方式相同。

l  基本数值---->包装对象

![img](https://ws3.sinaimg.cn/large/006tKfTcly1g186gr4w14j30cl020aa7.jpg)

Integer i = **new** Integer(4);//使用构造函数函数

Integer ii = **new** Integer("4");//构造函数中可以传递一个数字字符串

![img](https://ws2.sinaimg.cn/large/006tKfTcly1g186gk0pd1j30bs01zjrh.jpg)

Integer iii = Integer.*valueOf*(4);//使用包装类中的valueOf方法

Integer iiii = Integer.*valueOf*("4");//使用包装类中的valueOf方法

 包装对象---->基本数值

![img](https://ws1.sinaimg.cn/large/006tKfTcly1g186gm0747j30c0011jrb.jpg)

##### int num = i.intValue();

##### 自动装箱拆箱

### System类

System类不能手动创建对象，因为构造方法被private修饰，阻止外界创建对象。System类中的都是static方法，类名访问即可。

![img](https://ws4.sinaimg.cn/large/006tKfTcly1g186gkm4bfj30cl03jdg3.jpg)

l  **currentTimeMillis**()  获取当前系统时间与1970年01月01日00:00点之间的毫秒差值

l  **exit(int status)** 用来结束正在运行的Java程序。参数传入一个数字即可。通常传入0记为正常状态，其他为异常状态

l  **gc()** 用来运行JVM中的垃圾回收器，完成内存中垃圾的清除。

l  **getProperty(String key)** 用来获取指定**键**(字符串名称)中所记录的系统属性信息

![img](https://ws2.sinaimg.cn/large/006tKfTcly1g186grw9vzj30br0db0ud.jpg)

![img](https://ws2.sinaimg.cn/large/006tKfTcly1g186gfwblkj30c00200st.jpg)

### `Math` 类

![img](https://ws4.sinaimg.cn/large/006tKfTcly1g186gvt6imj30c005mmxo.jpg)

###  Arrays类

此类包含用来操作数组（比如排序和搜索）的各种方法。需要注意，如果指定数组引用为 null，则访问此类中的方法都会抛出空指针异常`NullPointerException`。

![img](https://ws3.sinaimg.cn/large/006tKfTcly1g186gsmapuj30c002jt8w.jpg)

### 大数据运算

####  BigInteger

![img](https://ws4.sinaimg.cn/large/006tKfTcly1g186ggbi2xj30cl052dgk.jpg)

#### BigDecimal

![img](https://ws4.sinaimg.cn/large/006tKfTcly1g186gu26j4j30cl07awfg.jpg)

![img](https://ws1.sinaimg.cn/large/006tKfTcly1g186gut2pzj30cl01kdfw.jpg)

![img](https://ws2.sinaimg.cn/large/006tKfTcly1g186gpavhvj30cl00wdft.jpg)

![img](https://ws4.sinaimg.cn/large/006tKfTcly1g186ghhfm5j30cl06ojs4.jpg)
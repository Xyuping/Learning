# String中intern 方法的使用



1. 常量池中的常量在编译器就存储在常量池中，如通过""、final。
2. 而 new String("a"):在编译器把"a"存储在常量池， 在运行期才进行new的操作在堆中创建对象。
3. s = s1 + s2;无论 s1、s2指向池中的字符串对象还是堆中的字符串对象，因为 s1、s2是变量，该语句在运行期执行。拼接结果不在常量池中。
4. 使用只包含常量的字符串连接符如"aa" + "aa"创建的也是常量,编译期就能确定,已经确定存储到String Pool中,String pool中存有“aaaa”；但不会存有“aa”。
5. String s2 = "aa" + s1; String s3 = "aa" + s1; 指向两个不同的对象，两行代码实际上在堆上new出了两个StringBuilder对象来进行append操作。
6. 对于final String s2 = "111"。s2是一个用final修饰的变量，在编译期已知，在运行s2+"aa"时直接用常量“111”来代替s2。所以s2+"aa"等效于“111”+ "aa"。在编译期就已经生成的字符串对象“111aa”存放在常量池中。



#### 1.String s = new String("abc");在内存中发生了什么

编译器：如果此时常量池中没有“abc”，则在常量池中创建一个“abc”对象。

运行期：会先在堆中new一个String 对象。

即：new 语句可能创建一个或两个对象。

![屏幕快照 2019-03-17 下午9.03.27](https://ws2.sinaimg.cn/large/006tKfTcgy1g163e41wvoj314w0jutar.jpg)

>String [s1](https://www.baidu.com/s?wd=s1&tn=24004469_oem_dg&rsv_dl=gh_pl_sl_csd) = new String("s1") ;
>　　String s2 = new String("s1") ;
>　　上面创建了几个String对象?
>　　答案:3个 ,编译期Constant Pool中创建1个,运行期heap中创建2个.（用new创建的每new一次就在堆上创建一个对象，用引号创建的如果在常量池中已有就直接指向，不用创建）

#### 2.和String s1 = "a";String s2 = "abc";

编译期：先看常量池中有没有该字符串对象或引用，如果没有则在常量池中创建该对象；

运行期：如果有则直接指向常量池中字符串对象或引用。

所以使用双引号来声明String有可能创建0个或1个对象。

![屏幕快照 2019-03-17 下午9.11.42](https://ws3.sinaimg.cn/large/006tKfTcgy1g163mlqhvyj31eo0ky41q.jpg)

所以 s1==s  //false

#### 3.String s4 = new String("1")+new String("1");

编译期：首先第1个new String（“1”）会在常量池中创建一个“1”的对象。

运行期：在堆中创建2个String 对象（"1"），然后计算“1”+“1”=>  "11"，在堆中创建1个"11"的S tring 对象，***但是“11”并不会放入常量池中***（与 s=new String （“11”）不同）

![屏幕快照 2019-03-17 下午9.18.43](https://ws2.sinaimg.cn/large/006tKfTcgy1g163tzth0ij31ek0kwn0x.jpg)

#### 4.String s6 = "a"+"b"

编译期：直接在常量池中创建"ab"，注意没有"a"和"b"。

#### 5.intern方法 （返回常量池中该字符串的引用）

```java
S4.intern();
String s5 = "11";
System.out.println(s5==s4);
====
true
```

（1） 当常量池中不存在**”11”**这个字符串的引用，将这个对象的**引用****加入常量池**，返回这个对象的引用。 

代码解释：

先运行S4.intern(); 发现常量池中没有“11”，则把**s4对象引用**加入常量池

再运行String s5 = "11"; 先找常量池，发现常量池中已经有“11”对象，不用再创建。

此时，s5指向的地址也是 s4指向的地址（都是同一个String对象）

![屏幕快照 2019-03-17 下午9.30.07](https://ws4.sinaimg.cn/large/006tKfTcgy1g1645tf3ezj317w0kwad0.jpg)

（2） 当常量池中存在**”abc”**这个字符串的引用，直接返回这个对象的引用。

```java
s==s2      //false
s.intern()==s2    //true
```

s.intern()和s2都指向常量池中的“abc”对象

![屏幕快照 2019-03-17 下午9.34.42](https://ws1.sinaimg.cn/large/006tKfTcgy1g164auj2ltj31f20l0gow.jpg)



```java
String s = new String("1")+new String("1");
		s.intern();
		String s1 = "11";
		System.out.println(s1==s); //true
		String s2 = "2";
		String s3 = "3";
		String s4 = s2+s3;
		s4.intern();
		String s5 = "23";
		System.out.println(s4==s5);//true
```

#### 为什么s = s1 + s2;拼接结果不在常量池中。而"aa" + "aa"常量池中存有“aaaa”，但不会存有“aa”。

常量池要保存的是已确定的字面量值。也就是说，对于字符串的拼接，纯字面量和字面量的拼接，会把拼接结果作为常量保存到字符串。

如果在字符串拼接中，有一个参数是非字面量，而是一个变量的话，整个拼接操作会被编译成StringBuilder.append，这种情况编译器是无法知道其确定值的。只有在运行期才能确定。

那么，有了这个特性了，intern 就有用武之地了。那就是很多时候，我们在程序中用到的**字符串是只有在运行期才能确定**的，在编译期是无法确定的，那么也就没办法在编译期被加入到常量池中。

这时候，对于那种可能经常使用的字符串，使用 intern 进行定义，每次 JVM 运行到这段代码的时候，就会直接把常量池中该字面值的引用返回，这样就可以减少大量字符串对象的创建了。 
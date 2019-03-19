

## Java程序执行流程

![20180207101332157](https://ws2.sinaimg.cn/large/006tKfTcgy1g161eclo5rj30my080my8.jpg)

## java 运行时数据区

其中：堆和方法区所有线程共享； 栈、程序计数器、本地方法栈每个线程独有。

![20180207103359733](https://ws4.sinaimg.cn/large/006tKfTcgy1g161ecxin3j30lr0acdh3.jpg)

### java 虚拟机栈

![20180207105300499](https://ws2.sinaimg.cn/large/006tKfTcgy1g161eeulunj30mo09y77c.jpg)

![20180207111531399](https://ws4.sinaimg.cn/large/006tKfTcgy1g161eegmi9j30my0a6jtq.jpg)

### 堆内存划分：

#### ![20180207145954238](https://ws3.sinaimg.cn/large/006tKfTcgy1g161eduxymj30mn09njso.jpg)

![20180207145902844](https://ws4.sinaimg.cn/large/006tKfTcgy1g161jy94irj30mf09s3zs.jpg)

堆被划分为新生代和旧生代，新生代又被进一步划分为Eden和Survivor区，最后Survivor由FromSpace和ToSpace组成

> 在 JDK1.8 之后将最初的永久代内存空间取消了（取消永久代的目的：是为了将 HotSpot与JRockit 两个虚拟机标准联合成一个。） 
> 在整个的JVM堆内存之中实际上将内存分为三块： 
> 年轻代：新对象和没达到一定年龄的对象都在年轻代； 
> 老年代：被长时间使用的对象，老年代的内存空间比年轻代更大 。（较大的对象和数组创建时也可在老年代）
> 元空间：像一些方法中的操作临时对象等，直接使用物理内存； 
>
> 最初的永久代是需要在JVM堆内存里面进行划分；

## GC

> 对于GC来说，当程序员创建对象时，GC就开始监控这个对象的地址、大小以及使用情况。通常，GC采用有向图的方式记录和管理堆(heap)中的所有对象。通过这种方式确定哪些对象是”[可达](https://www.baidu.com/s?wd=%E5%8F%AF%E8%BE%BE&tn=24004469_oem_dg&rsv_dl=gh_pl_sl_csd)的”，哪些对象是”不可达的”。当GC确定一些对象为”不可达”时，GC就有责任回收这些内存空间。倘若该对象重写了 finalize 方法，GC还会调用这个方法，如果对象在 finalize 方法中被引用（复活），则内存不会被回收；如果没有重写 finalize 方法，直接回收不可达对象的内存。
>
> 程序员可以手动执行System.gc()，通知GC运行，但是[Java语言](https://www.baidu.com/s?wd=Java%E8%AF%AD%E8%A8%80&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1YvPjcYryP-ujfvuj0kPvmk0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6K1TL0qnfK1TL0z5HD0IgF_5y9YIZ0lQzqlpA-bmyt8mh7GuZR8mvqVQL7dugPYpyq8Q1R4njnkPWn1P0)规范并不保证GC一定会执行。

#### System.gc()和Runtime.gc()会做什么事情？

参考：https://blog.csdn.net/chang_ge/article/details/79690256

> 每个 Java 应用程序都有一个 Runtime 类实例，使应用程序能够与其运行的环境相连接。可以通过 getRuntime 方法获取当前运行时。 Runtime.getRuntime().gc();
>
> java.lang.System.gc()只是java.lang.Runtime.getRuntime().gc()的简写，两者的行为没有任何不同。唯一的区别就是System.gc()写起来比Runtime.getRuntime().gc()简单点.

![屏幕快照 2019-03-17 下午4.47.32](https://ws1.sinaimg.cn/large/006tKfTcgy1g15w7bke88j318s07wmyp.jpg)

### gc 流程：

![20180207161532447](https://ws2.sinaimg.cn/large/006tKfTcgy1g161jhfzm2j30n50a8dhd.jpg)

> 对于整个GC流程里，最需要处理的就是年轻代和老年代的内存清理操作，而元空间（永久代）都不在GC范围内；

所有通过new创建的对象的内存都在堆中分配，其大小可以通过-Xmx和-Xms来控制。

#### 新生代

 新生代：新建的对象一般都是用新生代分配内存，Eden空间不足的时候，会把存活的对象转移到Survivor中，新生代大小可以由-Xmn来控制，也可以用-XX:SurvivorRatio来控制Eden和Survivor的比例旧生代。用于存放新生代中经过多次垃圾回收仍然存活的对象　

![20180208114528957](https://ws2.sinaimg.cn/large/006tKfTcly1g187bijgkaj30hv07fgmr.jpg)

　　对象的内存分配，往大方向上讲就是在堆上分配，对象主要分配在新生代的Eden Space和From Space，少数情况下会直接分配在老年代。

如果新生代的Eden Space和From Space的空间不足，则会发起一次Minor**GC****，如果进行了GC之后，Eden Space和From Space能够容纳该对象就放在Eden Space和From Space。在GC的过程中，会将Eden Space和From  Space中的存活对象移动到To Space，然后将Eden Space和From Space进行清理。如果在清理的过程中，To Space无法足够来存储某个对象，就会将该对象移动到老年代中。在进行了GC之后，使用的便是Eden space和To Space了，下次GC时会将存活对象复制到From Space，如此反复循环。当对象在Survivor区躲过一次GC的话，其对象年龄便会加1，默认情况下，如果对象年龄达到15岁，就会移动到老年代中。

#### 老年代

　　老年代主要接收由年轻代发送过来的对象，一般情况下，经过了数次Minor GC 之后还会保存下来的对象才会进入到老年代。如果要保存的对象超过了[伊甸](https://www.baidu.com/s?wd=%E4%BC%8A%E7%94%B8&tn=24004469_oem_dg&rsv_dl=gh_pl_sl_csd)园区的大小，此对象也将直接保存在老年代之中，当老年代内存不足时，将引发 **“major GC”，即，“Full GC”。**

一般来说，大对象会被直接分配到老年代，所谓的大对象是指需要大量连续存储空间的对象，最常见的一种大对象就是大数组，比如：

　　byte[] data = new byte[4*1024*1024]

　　这种一般会直接在老年代分配存储空间。

![20180208131958283](https://ws4.sinaimg.cn/large/006tKfTcly1g187bfpysbj30hp05waaz.jpg)

#### 分代收集算法

​         ***分代收集算法***是目前大部分JVM的垃圾收集器采用的算法。它的核心思想是根据对象存活的生命周期将内存划分为若干个不同的区域。一般情况下将堆区划分为老年代（Tenured Generation）和新生代（Young Generation），**老年代的特点是每次垃圾收集时只有少量对象需要被回收，而新生代的特点是每次垃圾回收时都有大量的对象需要被回收**，那么就可以根据不同代的特点采取最适合的收集算法。

　　目前大部分垃圾收集器对于**新生代**都采取**Copying算法**，因为新生代中每次垃圾回收都要回收大部分对象，也就是说需要复制的操作次数较少，但是实际中并不是按照1：1的比例来划分新生代的空间的，一般来说是将新生代划分为一块较大的Eden空间和两块较小的Survivor空间（8：1），每次使用Eden空间和其中的一块Survivor空间，当进行回收时，将Eden和Survivor中还存活的对象复制到另一块Survivor空间中，然后清理掉Eden和刚才使用过的Survivor空间。

![20180208114412823](https://ws1.sinaimg.cn/large/006tKfTcly1g187bh71h5j30ho07w0u7.jpg)

　　而由于**老年代**的特点是每次回收都只回收少量对象，一般使用的是**Mark-Compact**算法。

![20180208130923524](https://ws2.sinaimg.cn/large/006tKfTcgy1g1697wuslaj30mh09sq5c.jpg)

​       在回收清除的过程之中，发现所有在老年代中被回收的对象并没有进行空间的整理，所以老年代最头疼的就是碎片化问题。![20180208131704289](https://ws2.sinaimg.cn/large/006tKfTcgy1g1697vioqnj30hk07sjt1.jpg)





> 面试题：请解释“StackOverflowError”和“OutOfMemoryError”的区别：
>
> 1、stackoverflow： 每当java程序启动一个新的线程时，java虚拟机会为他分配一个栈，java栈以帧为单位保持线程运行状态；当线程调用一个方法是，jvm压入一个新的栈帧到这个线程的栈中，只要这个方法还没返回，这个栈帧就存在。 
> 如果方法的嵌套调用层次太多(如递归调用),随着java栈中的帧的增多，最终导致这个线程的栈中的所有栈帧的大小的总和大于-Xss设置的值，而产生生StackOverflowError溢出异常。
>
> 2、OutOfMemoryError: 如上。

#### 永久代（JDK 1.8 后消失）

​          虽然JAVA 的版本是 JDK 1.8 ，但是 JavaEE的版本还是JDK1.7，也就是说，在JavaEE里面必须对永久代进行设置。永久代也是在堆内存中保存的，但是永久代不会被回收，例如：intern（）方法产生的对象不会被回收。如果操作不当，导致永久代中的数据量过大，那么这个时候程序会报出 OOM 问题。 
一般情况下不会出现这种问题； 

![2018020813351658](https://ws3.sinaimg.cn/large/006tKfTcly1g187bgl3j4j30cg04rjru.jpg)

-XX:MaxPermSize: 设置永久代的最大值 
-XX：PremSize：设置永久代的初始大小

范例：设置永久代参数（java -XX:MaxPermSize10M TestDemo） 
在JDK 1.8之中设置永久代会报出错误

Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize10M; support was removed in 8.0

#### 元空间

元空间是JDK 1.8 之后才有的，功能和永久代类似。唯一到的区别是，永久代使用的是JVM的堆内存空间，而元空间使用的是物理内存，直接受到本机的物理内存限制。 

![20180208134125402](https://ws1.sinaimg.cn/large/006tKfTcly1g187bhrtcxj30hm07qgn9.jpg)

范例“设置一些参数，让元空间出错（java -XX:MaxMetaspaceSize=1m XX:MetaspaceSize=1m TestDemo） 

此时会报出“OutOfMemoryError:Metaspace”,元空间内存不足。

参考：

> https://blog.csdn.net/qq_34707744/article/details/79288787
>
> https://blog.csdn.net/weixin_39788856/article/details/80388002



##  finalize 的执行过程

#### (1)finalize大致流程：

当对象变成(GC Roots)不可达时，GC会判断该对象是否覆盖了finalize方法，若未覆盖，则直接将其回收。否则，若对象未执行过finalize方法，将其放入F-Queue队列，由一低优先级线程执行该队列中对象的finalize方法。执行finalize方法完毕后，GC会再次判断该对象是否可达，若不可达，则进行回收，否则，对象“复活”。

#### (2) 具体的finalize流程：

对象可由两种状态，涉及到两类状态空间，一是终结状态空间 F = {unfinalized, finalizable, finalized}；二是可达状态空间 R = {reachable, finalizer-reachable, unreachable}。各状态含义如下：

- unfinalized: 新建对象会先进入此状态，GC并未准备执行其finalize方法，因为该对象是可达的
- finalizable: 表示GC可对该对象执行finalize方法，GC已检测到该对象不可达。正如前面所述，GC通过F-Queue队列和一专用线程完成finalize的执行
- finalized: 表示GC已经对该对象执行过finalize方法
- reachable: 表示GC Roots引用可达
- finalizer-reachable(f-reachable)：表示不是reachable，但可通过某个finalizable对象可达
- unreachable：对象不可通过上面两种途径可达

参考https://www.cnblogs.com/Smina/p/7189427.html

![屏幕快照 2019-03-19 下午4.29.37](https://ws2.sinaimg.cn/large/006tKfTcly1g186qgbe3jj313c0n644d.jpg)

变迁说明：

1. 新建对象首先处于[reachable, unfinalized]状态(A)
2. 随着程序的运行，一些引用关系会消失，导致状态变迁，从reachable状态变迁到f-reachable(B, C, D)或unreachable(E, F)状态
3. 若JVM检测到处于unfinalized状态的对象变成f-reachable或unreachable，JVM会将其标记为finalizable状态(G,H)。若对象原处于[unreachable, unfinalized]状态，则同时将其标记为f-reachable(H)。
4. 在某个时刻，JVM取出某个finalizable对象，将其标记为finalized**并在某个线程中执行其finalize方法**。由于是在活动线程中引用了该对象，该对象将变迁到(**reachable**, finalized)状态(K或J)。该动作将影响某些其他对象从f-reachable状态重新回到reachable状态(L, M, N)
5. 处于finalizable状态的对象不能同时是unreahable的，由第4点可知，将对象finalizable对象标记为finalized时会由某个线程执行该对象的finalize方法，致使其变成reachable。这也是图中只有八个状态点的原因
6. 程序员手动调用finalize方法并不会影响到上述内部标记的变化，因此JVM只会至多调用finalize一次，即使该对象“复活”也是如此。程序员手动调用多少次不影响JVM的行为
7. 若JVM检测到finalized状态的对象变成unreachable，回收其内存(I)
8. 若对象并未覆盖finalize方法，JVM会进行优化，直接回收对象（O）
9. 注：System.runFinalizersOnExit()等方法可以使对象即使处于reachable状态，JVM仍对其执行finalize方法

#### (3)其他：

> - finalize()是Object的protected方法，子类可以覆盖该方法以实现资源清理工作，GC在回收对象之前调用该方法。
> - 不建议用finalize方法完成“**非内存资源**”的清理工作，但建议用于：① 清理本地对象(通过JNI创建的对象)；② 作为确保某些非内存资源(如Socket、文件等)释放的一个补充：在finalize方法中显式调用其他资源释放方法。

> - 一些与finalize相关的方法，由于一些致命的缺陷，已经被废弃了，如System.runFinalizersOnExit()方法、Runtime.runFinalizersOnExit()方法
> - System.gc()与System.runFinalization()方法增加了finalize方法执行的机会，但不可盲目依赖它们
> - Java语言规范并不保证finalize方法会被及时地执行、而且根本不会保证它们会被执行
> - finalize方法可能会带来性能问题。因为JVM通常在单独的低优先级线程中完成finalize的执行
> - 对象再生问题：finalize方法中，可将待回收对象赋值给GC Roots可达的对象引用，从而达到对象再生的目的
> - finalize方法至多由GC执行一次(用户当然可以手动调用对象的finalize方法，但并不影响GC对finalize的行为)
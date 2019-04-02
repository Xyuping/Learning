## Python

## 简介

1. Python是一种面向对象的解释性语言(可以立即执行,shell脚本，js，)：开发过程中没有了编译这个环节。类似于PHP和perl语言。 
2. Python来源：介于C与shell之间开发 
3. 解释性语言需要解释器（如shell--bash是他的解释器） 
4. 是一种交互式语言：可以在一个python提示符直接互动执行写你的程序。 
5. 面向对象的语言。 
6. 弱类型 
7. 遵循 GPL(GNU General Public License)协议。 
8. **可扩展：**如果你需要一段运行很快的关键代码，或者是想要编写一些不愿开放的算法，你可以使用C或C++完成那部分程序，然后从你的Python程序中调用。
9. 高层语言：不必考虑内存资源回收
10. 免费开源
11. 丰富的库
12. 强制缩进
13. 解释性： 解释器一行行解释源码，计算机一行行执行
14. 应用场景：web开发（Python，Java，PHP，.net），操作系统管理、服务器运维，机器学习，服务器软件（网络软件）——阿里云

​         Python具有丰富和强大的库。它常被昵称为胶水语言，能够把用其他语言制作的各种模块（尤其是C/C++）很轻松地联结在一起。常见的一种应用情形是，使用Python快速生成程序的原型（有时甚至是程序的最终界面），然后对其中有特别要求的部分，用更合适的语言改写，比如3D游戏中的图形渲染模块，性能要求特别高，就可以用C/C++重写，而后封装为Python可以调用的扩展类库。需要注意的是在您使用扩展类库时可能需要考虑平台问题，某些可能不提供跨平台的实现。 

​       由于Python语言的[简洁](https://baike.baidu.com/item/%E7%AE%80%E6%B4%81)性、易读性以及可扩展性，在国外用Python做科学计算的研究机构日益增多，一些知名大学已经采用Python来教授程序设计[课程](https://baike.baidu.com/item/%E8%AF%BE%E7%A8%8B)。例如[卡耐基梅隆大学](https://baike.baidu.com/item/%E5%8D%A1%E8%80%90%E5%9F%BA%E6%A2%85%E9%9A%86%E5%A4%A7%E5%AD%A6)的编程基础、麻省理工学院的计算机科学及编程导论就使用Python语言讲授。众多开源的科学计算软件包都提供了Python的调用[接口](https://baike.baidu.com/item/%E6%8E%A5%E5%8F%A3)，例如著名的计算机视觉库[OpenCV](https://baike.baidu.com/item/OpenCV)、三维可视化库VTK、医学图像处理库ITK。而Python专用的科学计算扩展库就更多了，例如如下3个十分经典的科学计算扩展库：NumPy、SciPy和matplotlib，它们分别为Python提供了快速数组处理、数值运算以及绘图功能。因此Python语言及其众多的扩展库所构成的开发环境十分适合[工程](https://baike.baidu.com/item/%E5%B7%A5%E7%A8%8B)技术、科研人员处理实验数据、制作图表，甚至开发科学计算[应用程序](https://baike.baidu.com/item/%E5%BA%94%E7%94%A8%E7%A8%8B%E5%BA%8F)。



​        Python在执行时，首先会将.py文件中的源代码编译成Python的byte code（字节码），然后再由Python Virtual Machine（Python虚拟机）来执行这些编译好的byte code。这种机制的基本思想跟Java，.NET 是一致的。然而，Python Virtual Machine与Java或.NET的Virtual Machine不同的是，Python的Virtual Machine是一种更高级的Virtual Machine。这里的高级并不是通常意义上的高级，不是说Python的Virtual Machine比Java或.NET的功能更强大，而是说和Java 或.NET相比，Python的Virtual Machine距离真实机器的距离更远。或者可以这么说，Python的Virtual Machine是一种抽象层次更高的Virtual Machine。 

基于C的Python编译出的字节码文件，通常是.pyc格式。 

除此之外，Python还可以以交互模式运行，比如主流操作系统Unix/Linux、Mac、Windows都可以直接在命令模式下直接运行Python交互环境。直接下达操作指令即可实现交互操作。 

## 安装

源码安装步骤: 

1. 下载 
2. 查看源码（看语言） 
3. 准备编译环境（由语言决定） 
4. 检查（依赖、兼容），预编译 
5. 编译 
6. 安装 



1. 官网下载压缩包 
2. 压缩包放到Linux下 
3. 准备编译环境（gcc） 
4. 解压缩 $tar -zxvf 包名 
5. 需要准备安装依赖包：zlib，openssl。Python的pip需要依赖这两个包 。$yum install zlib* openssl* 
6. 解压后进入目录，configure——预编译的程序 $ ./configure --prefix=/usr/python-3.6.1 --enable-optimizations  会弹出  (If you want a release build with all optimizations active (LTO, PGO, etc), please run ./configure --enable-optimizations)优化编译   /usr/python-3.6.1为安装目录 
7. 编译 $make 
8. 安装： make install 
9. 配置系统环境变量 

- 如果命令执行文件存在，但命令提示 command not found，一般是因为不在系统环境变量path中所以找不到执行文件。所以要配置PATH，让系统自动找到命令执行文件的路径。 
- 配置方法：$ vim ~/.bash_profile 或$vim ~/.bashrc ，在末尾新建一行追加PATH=$PATH:/usr/python-3.6.1/bin 
- $source ~/.bashrc  加载~/.bashrc文件（一般只要这个文件被修改了，就需要source一下） 
- 另一种配置方法，$vim ~./bashrc ,在文件末尾新建一行输入 

​                PYTHON_HOME=/usr/python-3.6.1 

​                PATH=$PATH:$PYTHON_HOME/bin 保存退出 

​            然后输入命令$source ~/.bashrc 



注意：/etc/profile 是整个系统的环境变量配置文件，轻易不要修改 

​          ~/.bashrc 是当前用户的环境变量配置文件 

​    10.最后安装一个Python的小工具，ipython    $pip3 install ipython 

解决pip更新问题<https://www.cnblogs.com/Fantinai/p/8622691.html> 

## Python 中的变量

1.Python 弱类型，无需声明变量类型，会自动识别 

2.type（变量名）   查看变量类型 

3.python中，String类型，既可以用’’，也可以用“” 

4.布尔类型值：True False 首字母必须大写 

5.None表示空值（防止出现空指针异常），有内存地址 

6.查看关键字（保留字） 

![screenshot](https://ws1.sinaimg.cn/large/006tKfTcly1g1omd1if1cj30vc076jtm.jpg)

7.引用指向的类型可变

![screenshot](https://ws2.sinaimg.cn/large/006tKfTcly1g1omegywmnj30ba0e2q3f.jpg)

8.整数类型占用的字节数为1，2，4，8……Python中整数不限大小， 字符型一个字节 

9.可变类型：list，dict， 

   不可变类型：int， String， 元组

注意 b+=a 和 b=a+b在可变类型中，内存地址变化不同。后者，会开辟新的内存空间。

![screenshot](https://ws1.sinaimg.cn/large/006tKfTcly1g1omewpsppj30ag0b63z0.jpg)

![screenshot](https://ws4.sinaimg.cn/large/006tKfTcly1g1omf7o0ooj30bi0ayq3g.jpg)

10.del 变量 删除变量 

11.id（变量）  返回内存地址 

## 字符串

1.len（字符串名或字符串常量）   返回字符串长度 

2.变量名[-n] 倒数第n个字符 

\3. 切片（字符串、列表、元组都支持切片） 

- 语法： 变量[起始：结束：步长]     截取包头不包尾。          V 起始位省略默认为0，结束位省略到字符串末尾。 步长：变化规律（起始位每次加几）步长可以为负数。例如步长为2，则中间跳一个。不写步长默认为1。 
- 倒序   name[-1::-1]或 name[-1:-1:-1] 

4.字符串常用方法   

-  .find(“XXXXX”)  返回子字符串第一个索引，找不到返回-1 
- .rfind(“xxxx”) 从右边开始找 
- .index 返回子字符串第一个索引，找不到报错 
- .count(“xxx”) 查找出现的次数 
- .replace(“aaa”,”bbb”)用bbb替换aaa （重复出现替换所有），后面加个参数n替换n次 
- .split(“xxx”)  以xxx作为隔开符切割，返回结果不包括xxx。后面加参数n，切割n次。若n超过所能切割的次数，按最大切割次数算。     split（）  默认以常用隔开符切割（如 空格 \t） 
- .partition（“xxx”） 切割返回结果包括xxx 
- .capitalize() 首字母大写 
- .startwith（“xxx”）判断是否以xxx开头 
- .endswith（“xxx”） 
- .lower()把所有字符串变成小写 
- .upper()把所有字符串变成大写（应用在 验证码不区分大小写） 
- .ljust（width） 返回一个原字符串左对齐，并用空格填充至长度width的新字符串 
- .rjust  （width）                         右 
- .center（width）                      居中 
- .strip    删除两端空格字符   (java中是trim（） ） 
- .lstrip() 删除左边空格字符 
- .rstrip()        右 
- .splitlines()以换行符为分隔符切割 
- .isalpha（）判断字符串是否所有字符都是字母 
- .isdigit()                                                      数字 
- .isalnum()                                                   数字或字母 
- .isspace()                                                   空格 
- .join(list)           字符串插入到list的每个元素后面，构造出一个新的字符串（一般应用于将列表转化成字符串  “”.join(list) ） 
- .replace(x, X) 用X替换x，但是字符串本身不变，只是返回值变了 

字符串也有乘法  “a”*30 

5.可以用for迭代 

6.eval(str ) 把字符串转换成表达式，返回值为表达式 

## 运算符

/ 除 

// 整除 

** 幂  a**b a的b次方 

~, |, ^, &, <<, >>必须应用于整数

*复制 

in       not in

## 控制语句

### 条件语句 

1.and or not 

2.<> 不等号     

3.if               : 

   elif            : 

   else: 

​        pass 

4.0--假  非0---真      “”—假   None—假  []—假 {}—假  （）—假   

### 循环语句 

for循环 

while循环 

while 条件： 

​    	循环语句 

```
while       : 

  循环语句 

else: 

​    循环不满足时语句 
```

```
for 临时变量 in 列表或字符串等： 

​    循环语句 

else：（break后不执行） 

​    循环不满足时语句 
```

```
for i  in range(1,10):    循环九次 

​    print(i) 
```

## 输入输出

### 输出print 

1.print（）可以输出多个字符串，用 ， 隔开  

2.%d 数值替换   %s所有类型都能替换   %f浮点数  %x十六进制 

![screenshot](https://ws2.sinaimg.cn/large/006tKfTcly1g1omhpobluj30oi05075h.jpg)

3.输出格式    

​    %04d  输出格式为至少四位，不足四位前面用0填充（不写0默认用空格填充） 

​    %.2f  保留两位小数    

​    %d%%  输出百分数（第一个%表示的是占位符，第二个%才是输出的%）               

### 输入 

1raw_input(提示内容)只在Python2有。输入的内容当做字符串 

2.input(提示内容) python2，python3都有，但是有区别。Python2中输入的内容作为表达式，Python3输入的内容作为字符串。 

3.可以用int（）进行数据类型转换 


















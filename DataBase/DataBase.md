# 数据库

## SQL分类

### DDL

数据库的定义

与数据库/表结构:create,drop,alter

### DML

数据操纵语言

操作表数据:insert,update,delete

### DCL

数据控制语言

设置用户的访问权限 安全

### DQL

数据查询语言

select,from,where

## 数据库操作

默认utf-8编码

### 创建数据库

```mysql
create database 数据库名字;
```

### 删除数据库

```mysql
drop database 数据库名字;
```

### 修改数据库

```mysql
alter database character set 字符集
```

#### 查看数据库

```mysql
---查看所有数据库
show databases;
---查看数据库定义
show create database 数据库名字;
---查看当前正在使用的数据库
select database();
```

### 选中数据库

```mysql
use 数据库名字
```



## 表结构的操作

### 创建表

```mysql
create table 表名(列名1 类型 约束,列名2 类型 约束...)
---列的约束
	主键约束:primary key (auto_increment)
	唯一约束:unique
	非空约束:not null
---例子
create table product(
    pid int primary key auto_increment,
    pname varchar(10),
    price double,
    pdate timestamp,
    con int
);
```

主键约束和唯一约束的区别

| 主键约束                     | 唯一约束               |
| ---------------------------- | ---------------------- |
| 唯一性                       | 唯一性                 |
| 默认不为空                   | 可以为空               |
| 主键一张表中只能有一个       | 表中可以有多个唯一约束 |
| 外键都是指向另外一张表的主键 |                        |



### 删除表

```mysql
drop table 表名;
```

### 修改表

add,modify,change,drop

#### 添加列

```mysql
alter table 表名 add 列名 列的类型 列的约束; 
```

#### 修改列

```mysql
alter table 表名 modify 列名 新的类型 新的约束; #修改该列的类型和约束
```

#### 修改列名

```mysql
alter table 表名 change 旧列名 新列名 新类型 新约束;
```

#### 删除列

```mysql
alter table 表名 drop 列名
```

#### 修改表的字符集

```mysql
alter table 表名 character set 字符集
```

#### 修改表名 

```mysql
rename table 旧表名 to 新表名;
```

### 查看表

```mysql
--查看当前数据库中所有表名(先use 数据库名)
show tables;
--查看表的定义结构/创建语句
show create table 表名
--查看表的结构
desc 表名;
```

## 表中数据的CRUD操作

### 插入数据

```mysql
insert into 表名(列名1,列名2...) values(值1,值2...)
insert into 表名 values(值1,值2...)
--批量插入
insert into 表名 values(值1,值2..),(值1,值2..)....

---例子
insert into product values (null,'小米mix4',998,null,1),(null,'锤子',2888,null,1),(null,'阿迪王',99,null,2),(null,'老村长',88,null,3),(null,'劲酒',35,null,3),(null,'小熊饼干',1,null,4),(null,'卫龙辣条',1,null,5),(null,'旺旺大饼',1,null,5);
```

### 删除数据

```mysql
delete from 表名 [where 条件]
--与truncate区别
	truncate 先删除表,再重建表
	delete 一条一条删
```

### 更新数据

```mysql
update 表名 set 列名=值,列名=值 [where 条件]
```

### 查询数据

```mysql
---通用格式
select [*][distinct][列1,列2...] from 表名 where 条件 [group by ][order by ]
```

#### 简单查询

##### 查询所有

```mysql
select *from 表名;
```

##### 查询指定列

```mysql
select 列1,列2... from 表名;
select pname,price from product;
```



##### 别名查询

1. 表别名
2. 列别名

```mysql
---别名查询,as关键字,as可省略
	--表别名
	select p.name,p.price from product as p; #先执行from product as p,给表product取了个别名p
	--列别名
	select pname as 别名1,price as 别名2 from product;
```

##### 去掉重复值查询

```mysql
	--关键字distinct
	select distinct price from product;
```

##### 运算查询

```mysql
	select *,price*1.5 from product;
	select *,price*1.5 as 折后价 from product;
```

##### 条件查询

```mysql
--where关键字
	select *from product where price>0;
		--where后的条件写法
			--关系运算符:...   注意<>是不等于(标准SQL写法),!=(非标准写法)
			--逻辑运算符 and or not
			--判断是否为空 is null|is not null
			--其他 between...and...
```

##### 模糊查询

```mysql
---模糊查询
	--like
		_ :代表的是一个字符
		% :代表的是多个字符
	select * from product where pname like '%云%'; #查询product表所有pname中带云字的
	
```

##### 范围查询

```mysql
---范围查询
	--in
	select *from product where cno in (1,4,5); #查询商品分ID在 1,4,5 中的所有商品
```

##### 排序查询

```mysql
--关键字:order by
	asc:ascend 升序(默认方式)
	desc:descend 降序
select * from product order by price; #默认按价格升序排序
select * from product order by price desc; #按价格降序排序

#查询所有商品名称中含'小'字的并按价格降序排序
select * from product where pname like '%小%' order by price desc;
```

##### 聚合函数

- sum()求和

- avg()求平均

- count()统计数量

- max(),min()

	```mysql
	select sum(price) from product;
	```

注意:where后面不能直接接聚合函数

~~select * from product where price >  avg(price) from product;~~

```mysql
---子查询
select * from product where price > (select avg(price) from product);
```

##### 分组

```mysql
--group by
#根据该列字段分组,分组后统计个数
select 列名,count(*) from product group by 列名;
#根据con分组,分组统计每组商品的平均价格,并且商品平均价格>60
select con,avg(price) from product group by con having avg(price)>60;
--having后面可以接聚合函数,主要出现在分组之后(对比where)
```

##### 编写顺序

```mysql
select...from 表 where...group by...having...order by...
--执行顺序
from 表 where...group by...having...select...order by...
```


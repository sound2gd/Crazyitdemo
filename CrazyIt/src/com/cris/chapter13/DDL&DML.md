## 1,DDL 数据定义语言
Data Definition Language,包括create,alter,drop,truncate

数据库对象一般有:
1. table,
2. database,
3. constaint,
4. view,
5. function,
6. procedure,
7. trigger

alter可以使用
1. add,
2. modify,
3. drop,
4. rename to/change

truncate是删除表中的数据保留表结构,比delete要快

大部分数据库支持以下约束:
1. notnull,
2. unique,
3. primary key,
4. foreign key,
5. check(MySQL不支持)

pk约束是not null 和unique的集合，同时会自动索引

索引index是存放模式schema中的一个数据库对象
创建索引的唯一作用就是加速对表的查询，索引通过快速路径访问来快速定位数据，从而减少了磁盘IO

创建索引有俩方式:
1. 自动：PK,UK,FK会自动加索引
2. 手动：create index 索引名 on 表名(要索引的字段)

坏处有：
1. 数据库增删改的时候，维护索引有一定的开销
2. 存储索引要一定的磁盘空间


view是一个或者多个数据的逻辑展示
语法: create or replace view as 子查询

子查询可以很复杂
使用with check option可以强制不能修改视图数据
删除使用drop view 视图名


## 2,DML 数据操作语言
DML,Data Manipulation Language,数据操作语言。
是用来改变数据库中的数据的，包括insert into,update,delete from三个


## 3,查询语句
查询又分为:
1. 单表查询
2. 多表查询

### 3.1 单表查询
从一张表里取出想要的记录。

select查询可以使用以下关键字:
算术运算符查询(+,-,*,/)
distinct 字段名 去除重复字段
where 来控制条件

其他的关键字有 between...and...,in,like,is null
SQL通配符：`_`匹配一个字符，`%`匹配任意字符

order by用于排序，默认是升序，要降序需要加上关键字DESC
order by 如果有多个字段，将以第一个字段为准，只有第一个字段内容一致，后续的排序字段才会起作用

***
MySQL提供了以下几个处理null的函数
ifnull(expr1,expr2):如果expr1为null，则返回expr2,否则返回expr1
nullif(expr1,expr2):如果expr1和expr2相等，则返回null,否则返回expr1
if(expr1,expr2,expr3):类似于三目运算符
isnull(expr1)

提供了case函数来进行流程控制:
case value
when compare_val1 then result1
when compare_val2 then result2
...
else result
end

提供了分组函数:
group by子句只有一列或者多列的值完全相同时，系统会把这些记录当成一组。
如果需要对分组进行过滤，只能使用having关键字

having和where关键字的区别如下：
1. 不能在where中过滤组，where仅用于过滤行，过滤组使用having子句
2. 不能在where子句中使用组函数，having子句才可使用组函数

### 3.2 多表查询
多表查询有俩规范:
1. SQL92
2. SQL99

SQL92：
- 等值连接
- 非等值连接
- 外连接 ->MySQL不支持
- 广义笛卡尔积

SQL99：
- 交叉连接
- 自然连接
- 使用using子句的连接
- 使用on子句连接
- 全外连接(full join,MySQL不支持)或者左右外连接

## 4 子查询
子查询就是指在查询语句中嵌套另一个查询，子查询的实质就是一个临时的视图。

**子查询必须使用括号括起来**

## 5 集合运算
集合运算包括intersect(交集),union(并集),minus(差集)
MySQL仅支持union






















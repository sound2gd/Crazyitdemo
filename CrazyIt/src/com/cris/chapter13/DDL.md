## DDL 数据定义语言
Data Definition Language,包括create,alter,drop,truncate

数据库对象一般有:
table,database,constaint,view,function,procedure,trigger

alter可以使用add,modify,drop,rename to/change
truncate是删除表中的数据保留表结构,比delete要快

大部分数据库支持以下约束:
notnull,
unique,
primary key,
foreign key,
check(MySQL不支持)

pk约束是not null 和unique的集合，同时会自动索引

索引index是存放模式schema中的一个数据库对象
创建索引的唯一作用就是加速对表的查询，索引通过快速路径访问来快速定位数据，从而减少了磁盘IO

创建索引有俩方式:
自动：PK,UK,FK会自动加索引
手动：create index 索引名 on 表名(要索引的字段)

坏处有：
1，数据库增删改的时候，维护索引有一定的开销
2，存储索引要一定的磁盘空间


view是一个或者多个数据的逻辑展示
语法: create or replace view as 子查询

子查询可以很复杂
使用with check option可以强制不能修改视图数据
删除使用drop view 视图名









## 1，JDBC简介
JDBC4.2包括如下常用接口和实现类

***
`DriverManager`:管理JDBC驱动的服务类，主要用来获得数据库连接java.sql.Connection

`Connection`:代表数据库的连接对象，每个Connection都代表一个物理连接会话。
该接口包括如下常用方法:
- Statement createStatement(),返回一个Statement对象
- PreparedStatement prepareStatement(String sql)，返回一个预编译的Statement对象
- CallableStatement prepareCall(String sql):返回一个CallableStatement对象，主要用于调用存储过程

`Statement`对象即可用于执行DDL,DCL语句，也可用于执行DML语句，还可以用于执行SQL查询。
常用方法是:
- ResultSet executeQuery(String sql):用于执行查询语句，只能用于执行查询语句
- int executeUpdate(String sql):用于执行DML语句和DDL语句，DML返回受影响的行数，DDL返回0
- boolean execute(String sql):可执行任何SQL语句

`ResultSet`对象是结果集对象，包含访问查询结果的方法。
有close(),absolute(结果集指针移到第几行),beforeFirst()指针定义到首行等等来访问结果集


以上就是JDBC编程最主要的对象。

## 2,JDBC编程步骤
JDBC一般编程步骤包括如下:
1，加载驱动
2,获得连接
3，获取Statament对象
4，执行sql
5,得到所要的结果集数据
6，回收数据库资源

## 3,执行SQL语句的方式
执行的方式有仨，
1. 使用Statement的execute,executeQuery,executeUpdate
2. 使用PreparedStatement
3. 使用CallableStatement调用存储过程


### 4,Java7的RowSet
RowSet继承了ResultSet接口，包含JdbcRowSet,CachedRowSet,FilteredRowSet,
JoinRowSet,除了JdbcRowSet需要保持与数据库的连接之外，其余4个子接口都是离线的
RowSet,无需保持与数据库的连接

### 5,DatabaseMetaData
DatabaseMetaData保存了数据库的元信息
除此之外，我们还可以使用系统表来查询数据库信息

通常:
tables:存放了所有的数据表信息
schema:存放了数据库里所有的数据库信息
views:存放了所有的视图信息
columns:存放数据库里所有列的信息
triggers:存放数据库里所有的触发器信息
routines:存放数据库里所有存储过程和函数信息
key_column_usage:存放数据库里所有具有约束的键的信息
table_constraints:存放数据库里全部约束的表信息
statistics:存放数据库里所有的索引信息




















## 第十五章 Java中的IO(输入/输出)

本章主要包括以下知识点:

1. File类访问文件系统
2. IO流的分类以及处理模型
3. IO流的典型用法
4. RandomAccessFile类自由移动指针访问文件
5. 序列化
6. Java的NIO
7. Java的NIO.2

### 一，使用File类来访问文件系统
File类是java.io包下代表与平台无关的**文件**和**目录**
如果需要在Java程序中操作文件和目录，就得使用File类来完成.

在默认情况下，系统总是根据用户的工作路径来解析相对路径。
工作路径由系统属性"user.dir"来指定，通常是运行Java虚拟机时所在的路径.

	File类只能增删文件，如果要读取文件内容并操作需要使用IO流

File类能创建临时文件，能创建文件，多级目录，得到某个目录下指定regex的文件

### 二，IO流的分类以及处理模型
IO流主要有如下分类:

1. 按照流向分，分为输入流(InputStream,Reader)和输出流(OutputStream,Writer)
2. 按照流的单元分，分为字节流(InputStream,OutputStream)和字符流(Reader,Writer)
3. 按照流的功能分，分为节点流和处理流(包装流)

节点流是可以直接从数据源进行IO的流，处理流不会直接连接到数据源，而是对节点流进行包装
处理流通过包装节点流来达到以下功能：

1. 提高性能：主要以增加缓冲的方式来提高IO效率
2. 简化操作:提供了一系列便捷的方法来一次输入/输入大量的内容，而不是每次处理1个处理单元

在Java中IO流的模型是：Java把所有设备里的有序数据抽象成流模型，把设备抽象成
一个个管道，管道里每个数据单元依次排列

输入流使用隐式的指针来记录当前准备从哪个数据单元开始读取，输出流也类似。

### 三，IO流的典型用法

InputStream和OutputSream都是抽象基类，常用的子类有FileInputSream,FileOutputStream
Reader和Writer类似，常用的有FileReader和FileWriter

它们的方法都类似，都采用经典IO流模型来进行IO操作。
其中，有如下方法不是所有流都支持的

- mark(),标记指针的位置
- markSupported(),是否支持mark
- reset(),指针移动到上次mark的位置
- skip(n),向前移动n个字节/字符

执行输出时，完成操作要**关闭输出流**
关闭输出流除了可以保证流的物理资源被回收之外，还可以将输出流的缓冲区数据刷新到物理节点里

***

#### 使用处理流
识别一个流是不是处理流，只要看它传入的参数是不是已经存在的流。如果传入的参数是一个物理节点
，那么就是节点流。否则就是处理流。

处理流常用的有PrintStream,使用PrintStream来包装OuputStream将在执行输出的时候更方便

***

其他典型的流有:

StringReader和StringWriter是字符流，也是节点流，其传入的参数是一个字符串
所以在字符流中，字符串也被认为是一种物理节点

访问管道的流有PipeInputStream,PipeOutputStream,PipeReader,PiperWriter
缓冲流是处理流，有以下4种：

1. BufferedInputStream
2. BufferedOutPutStream
3. BufferedReader
4. BufferedWriter

对象流主要用于实现对象的序列化，对象流有以下2种:

1. ObjectInputStream
2. ObjectOutputStream

转换流主要用于将字节流转化成字符流，有以下2种:

1. InputStreamReader
2. OutputStreamWriter

推回输入流有2种，且都是输入流:

1. PushBackInputStream
2. PushBackReader

推回输入流有一个unread方法，可以将指定数组的内容推回到缓冲区里。
其调用read方法总是先从缓冲区里读取，只有完全读取缓冲区的内容后，
才会从原输入流中读取

#### 重定向输入输出
System设置了以下三个方法来重定向标准输入输出的方法:

1. setErr:重定向标准错误输出流
2. setIn:重定向标准输入流
3. setOut:重定向标准输出流

#### Java虚拟机读写其他进程的数据
Process对象代表Java程序启动的子进程，Process提供三个方法:

1. getErrorStream:获取错误输出流
2. getInputStream:获取子进程的输入流
3. getOutputStream:获取子进程的输出流

子进程的输出流就是子进程的执行结果





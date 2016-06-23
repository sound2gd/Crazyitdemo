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

### 四,RandomAccessFile
RandomAccessFile是能任意访问文件的类，它既可以读取文件内容，又可以修改文件内容。
RandomAccessFile只能访问文件。

RandomAccessFile不能直接向文件的指定位置插入内容，如果直接将文件指针移动到中间
某个位置后开始输出，则新输出的内容就覆盖文件中原有的内容。如果需要向指定的位置插入内容
，程序需要先把插入点后面的内容读入缓冲区.

多线程中的下载工具一般就是使用RandomAccessFile来达到目标，多线程下载一般要建立
两个文件，一个是空文件，用来保存下载文件的内容，一个是文件指针文件，用来记录当前文件
写入到哪儿。

RandomAccessFile的seek()方法可以定位到文件的任意位置，其他诸如read和write方法
和InputStream,OutputStream差不多。

### 五，序列化
序列化是Java中一个非常重要的部分，是分布式应用的基础。
序列化的目标是将**Java对象**保存到磁盘中，或者允许在网络中直接传输对象。对象序列化机制允许
将Java对象转换成平台无关的**二进制流**,其他节点应用拿到二进制流可以恢复成Java对象。

序列化就是**将一个Java对象写入到IO流中**，反序列化就是**从IO流中读取出一个Java对象**

实现序列化有两种机制:

1. 实现Serializable接口
2. 实现Extenalizable接口

java.io.Serializable是一个标记接口，里面没有任何方法。

***

序列化一般需要两个步骤:

1. 创建一个ObjectOutputStream 
2. 使用ObjectOutputStream的writeObject(Object obj)方法

	反序列化读取的只是Java对象的数据，而不是Java类，采用反序列化必须提供该Java对象所属的class文件
	反序列化无需通过构造器来初始化Java对象

#### Java的序列化机制
Java采用了如下的序列化机制:

1. 所有保存到磁盘的对象都有一个序列化编号
2. 当程序试图序列化时，首先检查该对象是否已经被序列化过，只有该对象从未被
序列化过（在本虚拟机中），系统才会将该对象转换成字节序列并输出
3. 如果某个对象已经序列化过，那么程序只是输出一个序列化编号，而不是再次序列化该对象

如果要序列化一个类的某个对象，那么该对象的所有实例变量也必须是可序列化的。否则就要使用
static或者transient来修饰。序列化不会序列化这俩关键字修饰的变量。

#### 序列化的问题
当程序序列化第一个可变对象时，程序会将该对象转成字节序列并输出，然后在更改了该对象的值之后，
再次序列化时将不会再次转成字节序列，而是输出前面序列化的编号。即使后面该对象的实例变量值
已经改变。

#### 自定义序列化的两种方式
自定义序列化有两种方式，分别是:

1. 实现Serializable接口，并提供3个特殊签名的方法(readObject.writeObject,readObjectNoData)
2. 实现Externalizable接口，重写接口方法

#### 总结序列化的注意点

1. 对象的类名，实例变量(包括基本类型，数组，其他对象的引用)都会被序列化。
方法，类变量(static修饰的成员变量),transient实例变量(瞬态实例变量)都不会被序列化。
2. 实现Serializable接口的类如果需要让某个实例变量不被序列化，可在该变量前面加上
transient,而不是static(static修饰的是类变量，不属于对象)。
3. 保证序列化对象的实例变量类型也是可序列化的
4. 反序列化对象必须拥有该对象的class文件
5. **通过文件，网络来读取序列化后的对象时，必须按照实际写入的顺序读取。**

最后，介绍一下版本(serialVersionUID):反序列化必须提供该对象的class文件，为了保证两个class文件之间的兼容性，需要一个类变量标识该class的版本。只要SerialVersionUID不变，就认为是同一个序列化版本

### 六，NIO
NIO是java1.4之后加入的，传统的IO是要阻塞等待数据的存取的，而且面向流的IO系统只能一次处理一个字节，
因此效率不高。

NIO就是为了改进这些问题而诞生的，NIO采用了**内存映射文件**的方式来处理IO流，新IO将文件或者文件的一段区域
映射到内存中，这样就可以像访问内存一样来访问文件了。BIO是面向流的处理，NIO是面向块的处理。

NIO中的两个核心对象分别是:

1. Channel 通道，是对传统IO的模拟，NIO中的所有数据都要放在通道里传输
2. Buffer 缓冲,可以理解为一个容器，本质是一个数组。发送到Channel中的所有对象必须首先放在Buffer中。

此外，NIO还提供了两个对象:

3. Charset:将字符串映射成字节序列以及逆映射操作。
4. Selector:用于支持非阻塞式IO

#### Buffer
Buffer的创建时通过Buffer类的静态方法来创建的。
Buffer有三个核心概念:

1. position:位置，用于指明下一个可以被读出的或者写入的缓冲区位置索引
2. limit:界限，第一个不应该被读出或者写入的缓冲区位置索引
3. capacity:容量，创建后不能改变

Buffer类有一个实例方法:flip()。其作用是将limit设置为position所在的位置，然后将position置为0
，这就使得Buffer的读写指针又回到了开始位置。
clear()方法就是将position置为0，limit置为capacity.

通过Buffer.allocate(xx)创建的只是普通Buffer，ByteBuffer还提供了一个allocateDirect方法
创建直接buffer,创建成本高但是直接Buffer的读取效率更高。

#### Channel
Channel类似于传统的流对象，与传统的流对象有俩区别:

1. Channel可以直接将指定的文件的部分或者全部直接映射成Buffer
2. 程序不能直接访问Channel中的数据，包括读取，写入都不行。Channel只能和Buffer交互

java的NIO中Channel的主要实现类有:
DatagramChannel,FileChannel,Pipe.SinkChannel,Pipe.SourceChannel,
SelectableChannel,ServerSocketChannel,SocketChannel等等

**所有的Channel都不应该通过构造器来创建，而是通过传统的节点流的getChannel方法来创建**
Channel最常用的方法map(),read(),write();

#### 字符集和Charset
计算机里的文件，数据，图片只是一种表面现象，所有的文件底层都是二进制文件，全部都是**字节码**。
**编码**就是把字符序列转化成二进制序列，**解码**就是把二进制序列转化成字符序列

JDK1.4之后提供了Charset类来支持字符集，常用的Charset有:

- UTF-8
- GBK
- ISO-8859-1
- BIG5繁体字符集
- UTF-16
 
Java7新增了StandardCharsets类，用来支持常用的Charset
Charset类有2个实例方法，分别是**newDecoder（解码器）**和**newEncoder（编码器）**
用来支持编解码操作

#### 文件锁
多个运行程序需要并发修改同一个文件的时候，程序之间需要某种机制来通信，文件锁就是为了解决这个问题的。
JDK1.4以后，提供文件锁的支持。NIO中使用FileLock的tryLock()/lock()方法来提供支持。
lock()是阻塞等待，tryLock()不成功则返回null，成功返回文件锁。
这两个方法都有一个参数shared,如果shared为true，则是**共享锁**，允许别的程序读取该文件，如
果shared为false,则是**排他锁**，其他文件不允许使用该文件。默认得到的是**排他锁**

关于文件锁有以下几点:

1. 在某些平台上，文件锁仅仅是建议性的，而不是强制性的。
2. 在某些平台上，不能同步的锁定一个文件并把它映射到内存中。
3. 文件锁是由Java虚拟机持有的。
4. 在某些平台上关闭FileChannel时，会释放该Java虚拟机在该文件上的所有锁。因此避免
对一个被锁定的文件打开多个FileChannel

### 七，NIO.2
Java7对原有的NIO进行了重大的改进，主要包括以下内容：

1. 提供了全面的文件IO和文件系统访问支持
2. 基于异步Channel的IO

Java7把这些NIO的改进称为NIO.2

早期Java只提供了一个File类来访问文件系统，但是File有如下缺点:

1. 不能利用特定文件系统的特性
2. 性能不高
3. 大多数方法出错仅返回失败，不会提示异常信息

NIO.2引入了Path接口，代表一个与平台无关的平台路径。
同时还提供了Paths和Files类，提供了大量的操作文件的方法。











# 第十七章 网络编程

本章知识点：

1. 计算机网络基础知识
2. Java提供的网络编程工具类支持
3. ServerSocket和Socket实现TCP通信编程
4. NIO提供的非阻塞网络通信，AIO提供的非阻塞异步网络通信
5. DatagramPacket,DatagramSocket和MulticastSocket实现UDP通信
6. 利用Proxy和ProxySelector实现代理服务器


## 一，网络编程的基础知识

计算机网络：分布在不同地理区域的计算机与专门的外部设备用通信线路互联成一个规模大，功能强的网络系统
从而使得众多的计算机可以方便的互相传递信息，共享硬件，软件数据信息等资源


计算机网络中实现通信必须要有一些约定，这些约定被称为**通信协议**，通信协议负责对传输速度，传输代码，代码结构
，传输控制步骤，出错控制等制定处理标准。

通信协议通常由3部分组成：

1. 语义部分：决定双方对话的类型
2. 语法部分：决定对话的格式
3. 变换规则：决定通信双方的应答关系

OSI(Open System Interconnection)模型:开放系统互联参考模型将计算机网络分成七层：

1. 物理层(网线)
2. 数据链路层(路由器)
3. 网络层(IP)
4. 传输层(TCP/UDP)
5. 会话层
6. 表示层
7. 应用层(Tomcat,Nginx)


IP(Internet Protocol)协议：传输控制协议是计算机网络的基础协议，标志着计算机在什么位置，类似于门牌号
TCP(Transmission Control Protocol)协议：传输控制协议是另一种常用的协议，提供了一种可靠的数据传递服务

TCP和IP协议在设计上是互补的，本来是作为一个协议来设计的，所以实际使用中也把这俩协议统称为TCP/IP协议
按照TCP/IP协议，网络通常分为四层模型：

1. 物理+链路层
2. 网络层
3. 传输层
4. 应用层


在计算机网络里，IP地址用来标志唯一的通信实体，每个被传输的数据包也要包括一个源IP地址和目标IP地址
IP地址是数字型的，IP地址是一个32位的整数，但是通常为了便于记忆，会分成4个8位的二进制数

IP地址被分成ABCDE五类，每个类别的网络标志和主机标识各有规则
A类: 10.0.0.0 ~ 10.255.255.255
B类：172.16.0.0 ~ 172.31.255.255
C类：192.168.0.0 ~ 192.168.255.255

**端口**用来标志应该和某个通信实体的具体某一个应用程序进行通信。
端口是一个16位的正数，用于表示数据交给哪个通信程序处理（类似于房间号），端口就是应用程序与外界交流的出入口，它是一种抽象的
软件结构，包括一些数据结构和I/O缓冲区

通常将端口分为如下3类：

1. 公认端口(Well Known Ports):从0到1023，它们紧密绑定(Binding)一些特殊的服务
2. 注册端口(Registered Ports):从1024到49151,他们松散得绑定一些服务，应用程序通常应该使用这个范围内的端口
3. 动态和私有端口(Dynamic and/or Private Ports):从49152到65535,这些端口是应用程序使用的动态端口，应用程序一般不会主动使用这些端口


## 二，Java提供的网络支持

Java提供的网络支持工具类主要在java.net包下，一般使用如下工具类

1. InetAddress,代表IP地址和端口(Port)的抽象
2. URL和URLConnection,以变成的方式访问WEB服务
3. URLEncoder和URLDecoder,提供的普通字符串和application/x-www-urlencoded字符串相互转换的方法
4. URLPermission(JAVA8新增)：管理HttpUrlConnection的权限问题

### 2.1 InetAddress

InetAddress代表了IP地址相关的抽象封装，如域名解析，本机地址等等。旗下有俩子类Inet4Address,Inet6Address.分别代表IPv4和IPv6

常用方法见代码f2目录

### 2.2 URLEncoder和URLDecoder

提供的普通字符串和application/x-www-urlencoded字符串相互转换的方法，当URL地址包含中文时，这些关键字就会变成
application/x-www-urlencoded字符串。

URL编码使用URLEncoder类，URL解码使用URLDecoder类

具体代码见f2目录

### 2.3 URL,URLConnection和URLPermission

URL(Uniform Resource Locator)对象代表统一资源定位器，它是指向互联网"资源的指针"。资源可以是简单的文件或者目录，也可以是
对更为复杂的对象的引用，例如对数据库或者搜索引擎的查询。

URL由协议名，主机，端口和资源组成。

``` url
protocol://host:port/reourceName
```

URL类是JDK提供的对于URL的支持，一旦获得了URL对象，就可以调用它的一系列方法来获取对应的资源
URLConnection代表了本机与URL锁引用的远程资源的连接。

多线程下载使用的就是URL类的openStream方法，读取远程资源。
多线程下载的实现思路步骤如下：

1. 创建URL对象
2. 获取指定的URL对象所指向资源的大小(通过getContentLength()获得)
3. 在本机磁盘上创建一个和网络资源大小相同的空文件
4. 计算每个线程应该下载网络资源的哪个部分(从哪个字节开始，到哪个字节结束)
5. 一次创建，启动多个线程来下载网络资源的指定部分

通常创建一个和URL的连接，并发送请求，读取此URL引用的资源需要如下几个步骤：

1. 通过调用URL对象的openConnection方法来创建URLConnection对象
2. 设置URLConnection对象的参数和普通请求属性
3. 如果只是get请求，那么使用connect()方法建立和远程资源的连接即可。如果需要发送POST方式的请求，则需要获取URLConnection实例对应的输出流来设置参数
4. 远程资源变得可用，程序可以访问远程资源的头字段或者通过输入流读取远程资源的数据



### 三，基于TCP协议的网络编程

TCP/IP是一种可靠的网络协议，它在通信两端各建立一个Socket,从而在通信的两端形成一个虚拟链路。
Java使用Socket对象来代表两端的通信端口，并通过Socket产生IO流来进行网络通信

计算机通过IP协议发送数据时，数据在传输的过程中会分割成一个个小包，TCP协议负责收集这些小包，
并将其按照适当的次序放好传送，接收端收到后再将其正确的还原。TCP使用了重发机制，当一个通信实体
发送一个消息给另一个通信实体之后，需要收到另一个通信实体的确认消息，否则会重发。

Java中能接受其他通信实体请求的类是ServerSocket，用于监听来自客户端的链接。
ServerSocket使用完毕之后，应该使用close()方法关闭ServerSocket

使用Socket类可以连接到指定服务器,直接new Socket(host,port)就可以连接到一个服务器Socket,
但是如果要设置超时的话，就要构造一个无参数的Socket,然后调用它的connect方法

***
BIO多线程通信：

服务器端应该为每一个Socket单独启动一个线程，每个线程负责对一个客户端服务。因为BIO读取数据会阻塞线程。
客户端读取服务器端数据也会被阻塞，所以同样应该启动一个线程专门负责读取服务器端数据。
















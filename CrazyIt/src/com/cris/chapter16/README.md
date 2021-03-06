## 第十六章 Java中的多线程

本章共有6个知识点：

1. 多线程的基本概念(类结构，生命周期)
2. 多线程的启动方式(Runnable,Future,Thread)
3. 多线程的控制方式与同步
4. 多线程的通信(传统，Condition,BlockingQueue)
5. 线程池(ExecutorService)
6. 线程安全相关的工具(ThreadLocal,Collections.synchrizeXXX,java.util.concurrent)

### 一，多线程的基本概念
#### 1.1 进程和线程
首先看看什么是进程：

进程是操作系统层面的概念，当一个程序进入内存时，就变成了一个进程。进程是处于运行状态中的程序，并且具有一定的独立功能。
进程是**操作系统**进行资源分配和调度的一个独立单位。

进程有如下三个特征:

1. 独立性:进程是系统中独立存在的实体，可以拥有自己的独立的资源。每一个进程都拥有自己私有的地址空间
2. 动态性:进程具有自己的生命周期和各种不同的状态
3. 并发性:多个进程可以在单个处理器上并发执行，多个进程之间不会相互影响

现在计算机大部分都支持**多进程的并发**，你可以一边听歌一边用浏览器看网页就是例子。

**多线程**扩展了多进程的概念，使得同一个进程可以同时并发处理任务。线程(Thread)也称为**轻量级进程(LightWeight Process)**
线程是进程的执行单元，线程在进程程序中是独立的，并发的执行流。每个线程也是相互独立的。

线程是进程的组成部分，一个进程可以有多个线程，但是一个线程只能有一个父进程。线程可以拥有自己的堆栈，自己的程序计数器和自己的局部变量，
但是不拥有系统资源，它与父进程的其他线程共享该进程锁拥有的全部资源。

一个线程可以创建和撤销另一个线程，同一个进程中的多个线程之间可以并发执行

#### 1.2 多线程的优势
线程在程序中是独立的，并发的执行流。与分隔的进程相比，进程中线程的的隔离程度要小很多，它们共享内存，文件句柄和其他每个进程应有的状态

线程比进程具有更高的性能，这是因为多个线程共享同一个虚拟空间。
线程共享的环境包括:**进程代码段，进程的共有数据**等。

实际应用中，多线程十分有用，一个浏览器必须能下载多个图片，一个web服务器必须能同时响应多个用户的请求。

#### 1.3 多线程的生命周期
在Java中，线程的生命周期有如下几种:

1. New新建
2. Runnable 就绪
3. Running 运行
4. Blocked 阻塞
5. Dead 死亡

当一个线程新建之后，就变成New状态，当调用start()方法之后，就变成了Runnable状态，JVM
会为其创建方法调用栈和程序计数器。

***
在单核cpu的机器上，任何时刻都只有一个线程得到执行。
在多核cpu上，将会有多个线程并行（同时）执行。当线程数大于处理器数时候，依然会存在多个线程在同一cpu轮换的情况。
***

当发生如下情况时，会使线程阻塞：

1. 线程调用sleep方法主动放弃所占用的cpu资源
2. 线程调用了一个阻塞式IO方法，在该方法返回之前，该线程被阻塞
3. 线程正试图获得一个同步监视器(synchonized 修饰的对象的监视器)，但是该对象正在被其他线程持有。
4. 线程正在等待某个通知(notify)
5. 程序调用了线程的suspend方法将该线程挂起。**这方法容器导致死锁！尽量避免使用**


当发生如下情况时，会使线程恢复到就绪状态Runnable:

1. 程序从sleep恢复
2. 阻塞式IO返回了结果
3. 成功获得试图获得的同步监视器锁
4. 程序正在等待一个通知，其他线程发出了一个通知
5. 处于挂起状态的线程被调用了resume()方法

程序从Running到Runnable只要调用yield方法就可以放弃cpu资源

线程死亡一般有如下3种方式:

1. 线程完成了本身的任务
2. 线程抛出了一个异常UncaughtException或者错误Error
3. 直接调用该线程的stop()方法来结束该线程,**不推荐使用！容易导致死锁！**

一图解千言（图来自博客，版权归原作者所有,侵删）:

![Java多线程状态变化图](http://images.cnitblog.com/blog/325852/201302/20011044-cffc02c7b77b49dfaf42ed611c8b1cf8.png)

### 二，线程的创建和启动
Java使用Thread类代表线程，所有的线程对象都是Thread类或者其子类的实例。
Java实现多线程有3个方法:

1. 继承Thread，重写run方法
2. 实现Runnable,重写run方法
3. 实现Callable,重写call方法

#### 2.1 继承Thread类创建线程类
通过继承Thread类来启动多线的步骤如下:

1. 定义Thread类的子类，重写run方法
2. 创建该子类的实例，这样就得到了线程对象(New状态)
3. 调用线程对象的start方法来启动线程


**通过继承Thread类创建线程类时，多个线程之间无法共享线程类的实例变量**

#### 2.2 实现Runnable接口创建线程类
通过实现Runnabel接口创建线程类的步骤如下:

1. 实现java.lang.Runnable接口，重写run方法
2. 创建Runnable实现类的实例，并以此实例作为Thread的target来创建Thread对象,**该Thread对象才是真正的线程对象**


采用Runnable接口的方式创建的线程可以共享线程类的实例变量。因为在这种方式下，程序锁创建的Runnable对象只是Thread类的target
而多个线程可以共享同一个target,所以多个线程可以共享同一个线程类(实际上应该线程的target类)的实例变量

#### 2.3 实现Callable和Future接口创建线程类

使用Callable和Future创建线程的方法如下:

1. 创建Callable接口的实现类，并实现call方法
2. 创建FutureTask对象来包装Callable对象，该FutureTask封装了该Callable对象的call方法的返回值
3. 使用FutureTask对象作为Thread对象的target创建并启动新线程
4. 调用FutureTask的get方法来获得子线程执行结束后的返回值

#### 2.4 创建线程的3种方式的对比

采用Runnable和Callable实现多线程的优缺点如下:

- 线程只是实现了Runnable接口或者Callable接口，还可以继承其他类
- 多个线程共享同一个target对象，所以非常适合多个相同线程来处理同一份资源的情况
- 劣势是:变成稍复杂，如果需要访问当前线程，必须使用Thread.currentThread()方法

使用继承Thread类的方式来创建多线程的优缺点:

- 劣势是:已经继承了Thread类，不能继承其他的类
- 优势是：编写简单，如果需要访问当前线程，则无需使用Thread.currentThread方法，直接使用this

### 三，控制线程和同步

控制线程的执行有下面的方法:

1. join
2. sleep
3. 设置Deamon
4. 改变优先级
5. yield

Thread提供了让一个线程等待另一个线程完成的方法--join方法。
join方法通常由使用线程的程序调用，以将大问题分成许多小问题。每个小问题分配一个线程，当所有的小问题都得到
解决之后，再调用主线程来进行进一步的操作。


有一种线程，它是后台运行的，任务是为其他的线程提供服务，这种线程称为"后台线程(Deamon Thread)"，又称为
守护线程或者精灵线程。

后台线程有个特征，当前台线程死亡，它也会死亡。
前台线程创建的默认就是前台线程，后台线程创建的默认就是后台线程

线程可以让当前正在执行的线程暂停，但是不会阻塞该线程。只是让当前线程暂停一下，让系统线程调度器重新调度一下

每个线程都有一个优先级(1~10)，优先级高的线程获得较多的执行机会。每个线程默认的优先级都和其父线程相同
优先级级别需要操作系统支持，尽量避免直接为线程指定优先级。


***
线程同步有如下方法:

1. synchronized修饰的变量，方法，代码块
2. 使用java.util.concurrent.Lock

线程会在如下情况下释放同步监视器的锁定:

1. 当前线程的同步方法，同步代码块执行结束，当前线程立刻释放同步锁
2. 当前线程在代码块里遇到了break,return终止了该代码块
3. 当前线程在同步代码块，同步方法中出现了未处理的Error,Exception
4. 在执行同步代码块的时候，程序执行了同步监视器对象的wait()方法，当前线程暂停，并释放同步代码块


同步锁一般使用ReentrantLock（可重入锁）.某些锁允许对共享资源并发访问，如ReadWriteLock(读写锁)

当两个线程相互等待对方释放同步监视器锁的时候就会发生死锁。

### 四，线程间通信

线程间通信的方式主要有4种:

1. 传统的通信方式(wait,notify,notifyAll)
2. 使用java.util.Condition(一般配合Lock使用)
3. 使用BlockingQueue
4. 使用PipeXXXStream

#### 4.1 传统的线程通信
  Object类的对象有3个方法，用于实现线程间通信，分别是:
  
  1. wait():导致当前线程等待，直到其他线程调用该同步监视器的notify方法或者notifyAll方法来
  唤醒该线程。
  2. notify():唤醒在此同步监视器上等待的单个线程，如果所有线程都在此同步监视器上等待，则会选择唤醒
  其中的一个线程。选择是任意性的。
  3. notifyAll():唤醒在此同步监视器上等待的所有线程，只有当前线程放弃对该同步监视器的锁定之后，才可以
  执行被唤醒的线程。
  
  这三个方法必须由同步监视器对象来调用。
  
#### 4.2 使用Condition进行线程通信
  当使用Lock来保证同步时，Java提供了一个Condition类来保证线程协调。使用Condition可以让那些已经得到Lock对象却
  无法继续执行的线程释放Lock对象，Condition对象也可以唤醒其他处于等待的线程。
  
  Condition对象有3个方法,分别对应wait,notify,notifyAll:
  
  1. await()
  2. signal()
  3. signalAll()
  
  这三个方法必须由同一个Condition来调用


#### 4.3 使用BlockingQueue来进行线程通信
Java5以后提供了一个BlockingQueue,主要用途是用于线程同步的工具
它有一个特征:当生产者线程试图向BlockingQueue中放入元素时，如果该队列已满，则该线程被阻塞，
当消费者线程试图向BlockingQueue取出元素时，如果该队列已空，则该线程被阻塞

BlockingQueue提供了以下两个阻塞方法:

1. put()
2. take()

BlockingQueue的常用实现类有:ArrayBlockingQueue,LinkedBlockingQueue,PriorityBlockingQueue（每次取出最小的元素）,
SynchronousQueue(同步队列，对该队列的存取必须交替进行),DelayQueue

### 五，线程组和未处理的异常
Java使用ThreadGroup来表示线程租，它可以对一批线程进行分类管理，Java允许程序直接对线程组进行控制。对线程组的控制相当于
同时控制这批线程。在默认情况下，子线程和父线程处于同一线程组内。

一旦某个线程加入了指定的线程组之后，该线程将一直属于它的线程组，知道该线程死亡，线程运行中途不能改变它所属的线程租。

Thread提供了构造器来设置创建的线程属于哪个线程租。

ThreadGroup提供了以下几个常用方法来操作整个线程组里的所有线程:

1. int activeCount()：活动线程的数量
2. interrupt():中断此线程组中的所有线程。
3. isDaemon():判断该线程组是否属于后台线程组
4. setDaemon(boolean):设置线程组为后台线程组
5. setMaxPriority(int):设置线程租的最高优先级

ThreadGroup 还有一个十分实用的方法uncaughtExceptionHandler()，可以设置处理该线程组里所有抛出的未处理异常
线程组默认处理异常的逻辑如下:

1. 如果该线程组有父线程组，则调用父线程组的uncauthtExceptionHandler
2. 如果该线程实例所属的线程类有默认的异常处理器，那么调用该异常处理器来处理异常
3. 如果该异常对象是ThreadDeath对象，则不做任何处理，否则，将异常追踪栈的信息打印到System.err，并结束该线程

异常处理器和catch语法是不一样的，即使调用了异常处理器，异常仍然会传递给上一级调用者

### 六，线程池
系统启动一个线程的成本是比较高的，所以出现了一种名为"线程池"的技术。线程池在和数据库连接池类似，
线程池在系统启动的时候创建大量空闲线程，程序将一个Runnable对象或者Callable对象传入线程池，线程池会
启动一个线程来执行他们的run()方法或者call()方法。当run或者call结束后，该线程不会死亡，而是进入线程池中称为空闲状态。


从Java5开始，开发者无需手动实现自己的线程池，开始内建支持线程池。Java5新增了一个Executors工厂类来生产线程池。
有如下常用静态方法:

1. newCachedThreadPool：创建一个具有缓存功能的线程池，系统根据需要创建线程，这些线程将会被缓存在线程池中
2. newFixedThreadPool：创建一个可重用的，具有固定线程数量的线程池
3. newSingleThreadExcutor：只有一个线程的线程池
4. newScheduledThreadPool：创建具有指定线程数的线程池，可以在指定延迟之后执行线程任务
5. newSingleThreadScheduledExecutor：只有一个线程的线程池，可以在指定的延时之后执行任务
6. newWorkingStealingPool:创建持有足够线程的线程池来支持给定的并行级别，该方法会使用多个队列来减少竞争

前三个方法都返回一个ExecutorService对象，该对象代表一个尽快执行的线程池(只要线程中有空闲线程，就立即执行任务),
提供了以下3个方法:

1. Future<?> submit(Runnable task)
2. <T>Future<T> submit(Runnable task,T result):将一个Runnable交给指定线程池，线程池将在有空闲的时候执行task,
result显式指定返回值
3. <T>Future<T> submit(Callable<T> task)：将一个Callable传给线程池


用完一个线程池之后，应该调用该线程池的shutdown()方法。该方法将启动线程池的关闭序列，调用shutdown方法之后的所有线程
池都将不再接受新的任务，但是会将以前的所有提交的任务执行完成。

使用线程池执行线程任务的步骤如下:

1. 调用Executors类的静态工厂生产一个ExecutorService对象，该对象代表一个线程池
2. 创建Runnable实现类或者Callable实现类的实例，作为线程执行任务
3. 调用ExecutorService的submit方法提交Runnable或者Callable实例
4. 当不想提交任何任务时，调用ExecutorService对象的shutdown方法来关闭线程池

***

现在计算机大部分都是多核的CPU，为了充分利用CPU的计算能力，可以考虑把一个大任务分成多个
小任务并行计算，再把多个小任务的结果合并成总的计算结果。

Java7提供了ForkJoinPool来支持将一个任务拆分成多个"小任务"并行计算，再把多个小任务的结果合并成
总的计算结果，ForkJoinPool是ExecutorService的常见实现类因此是一种特殊的线程池，提供了2个构造器：

1. ForkJoinPool(int parallalism):创建一个包含parallelism个并行线程的ForkJoinPool
2. ForkJoinPool(),创建一个包含Runtime.availableProcessors()为参数个并行线程的ForkJoinPool

Java8为ForkJoinPool增加了通用池功能，通用池的运行状态不会收到shutdown或者shutdownNow的影响
创建ForkJoinPool之后，可以提交ForkJoinTask来执行任务，ForkJoinTask代表一个可以并行，合并的任务
常用的抽象子类有:RecursiveAction（无返回值）和RecursiveTask(有返回值)

### 七，线程相关工具

Java为线程安全提供了一些工具类，如ThreadLocal,Collections.synchronizedXXX,java.util.concurrent包下的一堆东西

早在JDK1.2推出的时候，Java就为多线程编程提供了一个ThreadLocal类，通过使用ThreadLocal类可以简化多线程编程时的并发访问，
使用这个工具类可以很简洁的隔离多线程程序的竞争资源

ThreadLocal的功用非常简单，就是为每一个使用该变量的线程提供一个变量值的副本，使得每一个线程都可以独立得改变自己的副本，而
不会和其他线程持有的副本冲突。从线程的角度看，好像线程持有该变量一样。

有3个简单的方法:

1. T get(),返回此线程局部变量中当前副本中的值
2. void remove(),返回此线程局部变量中当前线程的值
3. void set(T value),设置此线程局部变量中当前线程副本中的值。

Java集合里的ArrayList,HashSet,LinkedList,TreeSet,HashMap,TreeMap都是线程不安全的，要想并发访问，
可以使用Collections类提供的静态方法来保证线程安全性。


**如果需要把某个集合包装成线程安全的集合，应该在创建之后立刻包装**

***
从Java5开始，在java.util.concurrent包下提供了大量的支持高效并发访问的集合接口和实现类
这些线程安全的集合类可分为2类：

1. Concurrent开头的集合类，如ConcurrentHashMap,ConcurrentSkipListMap,ConcurrentSkipListSet,ConcurrentLinkedQueue和
ConcurrentLinkedDeque
2. 以CopyOnWrite开头的集合类，如CopyOnWriteArrayList,CopyOnWriteArraySet。

以Concurrent开头的集合类代表了支持并发访问的集合，他们可以支持多个线程高效并发写入和访问。它们采用了复杂的算法来保证永远不会锁住集合，
从而在并发写入的时候有较好的性能。


当多个线程共享访问一个公共集合时，ConcurrentLinkedQueue是一个恰当的选择。
默认情况下，ConcurrentHashMap支持16个线程并发写入，当超过16个线程并发写入时，可能有线程要等待。

对于CopyOnWriteArrayList而言，底层复制数组的形式来实现写操作，当执行读操作的时候，将会直接读取集合本身，无需加锁和解锁。
执行写入操作的时候，集合会在底层复制一份数组，接下来对新的数组执行写入操作，所以是线程安全的。
所以CopyOnWriteArrayList的写入性能很差，但是读取性能很高，所以适合读多写少的情况，如缓存。



















  
  
  







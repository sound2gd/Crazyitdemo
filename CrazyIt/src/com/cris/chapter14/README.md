## 第十四章 Annotation注解


知识点：

1. JDK提供的5个基本Annotation
2. JDK提供的6个修饰Annotation的元注解
3. 自定义Annotation
4. 使用APT(Annotation Processing Tool)工具来处理Annotation


### 一，基本Annotation

JDK提供的5个基本Annotation分别是:
1. @Override
2. @Deprecated
3. @SupressWarnings
4. @SafeVarargs（Java7新增）
5. @FunctionalInterface（Java8新增）


其中，@Override只能注解在方法上，标志着编译器会检查该方法
是否是继承并重写了父类的方法。

@Deprecated标记某个类或者某个方法已过时，当其他程序使用该类，编译器会报出警告
@SupressWanings指示被该Annotation修饰的元素（以及该元素的所有子元素），取消
显示指定的编译器警告

Java7的堆污染是指，发生了泛型擦除等现象。
可以使用@SafeVarargs来控制不显示该警告
Java8提供了@FunctionalInterface来标记一个接口是函数式接口，以支持lambda表达式
函数式接口只能有一个抽象方法，编译器会检查该方法是否为函数式接口

### 二，元注解(Meta Annotation)
所谓的元注解，就是注解其他注解的注解
JDK包括6种元注解:

1. @Retention
2. @Target
3. @Docuemnted
4. @Inherited
5. @interface
6. @Repeatable(JAVA8新增)

***

@Retention是标注该注解要保留到什么时候，取值有3个，分别是：

1. RetentionPolicy.CLASS 把该注解写入类文件字节码中，这是默认值，在运行时将无法读取
2. RetentionPolicy.RUNTIME 把该注解写入类文件字节码中，运行时将可以通过反射读取该注解信息
3. RetentionPolicy.SOURCE 仅在源码中使用，编译器将直接丢弃该注解

@Target是标志一个注解要注解在什么元素上，取值有8个，分别是:

1. ElementType.ANNOTATION_TYPE 只能注解在注解上
2. ElementType.CONSTRUCTOR 可以注解在构造器上
3. ElementType.TYPE 可以注解在类上
4. ElementType.METHOD 可以注解在方法上
5. ElementType.PARAMETER 可以注解在方法参数上
6. ElementType.PACKAGE 可以注解在包上
7. ElementType.LOCAL_VARIABLE 只能修饰局部变量

@Documented标志该注解将写入到JavaDoc中
@Inherited表示该注解将会被子类继承


### 三 自定义注解

@interface用来自定义一个注解，语法如下:

``` java
	public @interface test{
	   //增加成员变量采用`无形参的方法`形式来声明
	   //方法名和返回值定义了该成员变量的名字和类型
	   String name() default "name";
	   int age() default -1;
	   
	}
```

默认情况下，该注解可以修饰任何元素

自定义的注解可以定义成员变量，定义成员变量采用 `无形参的方法` 来定义
例如: String name();该成员的名字是name,类型是String

***
根据是否包含成员变量，注解可以分成2类：
1. 标记注解:没有定义成员变量，这种注解仅利用自身的存在来提供信息
2. 元数据注解:包含成员遍历的注解，可以接受更多的元数据
***
Java使用java.lang.annotation.Annotation来代表程序元素前面的注解，该接口是所有接口的父接口
该接口主要有以下实现类:

1. Class:类定义
2. Constructor:构造器定义
3. Field:类成员变量定义
4. Method:类成员方法定义
5. Package:包定义


Java8新增的一个@Repeatable注解，该注解用于标注一个注解可以重复注解
同一个元素，这在没有@Repeatable的Annotation中是会报错的。

Java8新增了Type Annotation,为ElementType新增了TYPE_PARAMETER和TYPE_USE两个值
这种注解称为类型注解Type Annotation。Type Annotation可以用到任何用到类型的地方

 
### 四 APT工具
APT（Annotation Processing Tool）注解处理工具是一种工具，它可以对源代码文件进行
检测，并找出源文件所包含的Annotation信息,然后针对Annotation进行额外的处理

Java提供的Javac工具有一个-processor选项，该选项可以指定一个Annotation处理器，如果
在编译Java文件的时候注定了Annotation处理器，那么这个处理器会在编译的时候提取并处理源文件中的Annotation

Annotation处理器一般继承javax.annotation.processing.AbstractProcessor类，
通过RoundEnvironment来获取Annotation信息，`getElementsAnnotationWith()`方法可以根据Annotation获取需要处理的程序单元
Element包含getKind方法，可以得到该Element所代表的程序单元












 





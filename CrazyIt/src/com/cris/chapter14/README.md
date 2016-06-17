## 第十四章 Annotation注解


知识点：
1. JDK提供的5个基本Annotation
2. JDK提供的修饰Annotation的元注解
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




package com.cris.chapter14;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 测试JAVA中的元注解
 * 
 * @author cris
 *
 */
// @ConstructorAnnotation 该注解会引发编译异常
@test(name = "MetaAnnotationTest", age = 1) // 一旦定义了成员变量，就可以指定值
public class MetaAnnotationTest {

	@ConstructorAnnotation
	public MetaAnnotationTest() {
	}

	public static void main(String[] args) throws Exception {
		// 通过 反射 遍历取出一个元素上的注解
		Annotation[] annotations = MetaAnnotationTest.class.getAnnotations();

		for (Annotation annotation : annotations) {
			// 打印注解信息

			// 输出注解类型
			System.out.println(annotation.annotationType());// interface
															// com.cris.chapter14.test
			// 输出注解所在类
			System.out.println(annotation.getClass());// class
														// com.cris.chapter14.$Proxy1
			// 输出详细信息 Annotation重写了toString方法
			System.out.println(annotation);// @com.cris.chapter14.test(name=MetaAnnotationTest,
											// age=1)

			// 如果需要某个注解详细的信息，可以强转为该类然后取出信息
			if (annotation instanceof test) {
				test t = (test) annotation;
				//输出注解的name信息
				System.out.println("name -> "+t.name());
				//输出注解的age信息
				System.out.println("age -> "+t.age());
			}
		}

	}
}

/**
 * 自定义注解使用{@code @interface}方法
 * 
 * @author cris
 *
 */
@Target(ElementType.CONSTRUCTOR) // 使用@Target可以指定该注解可以注解在什么上
@Retention(RetentionPolicy.RUNTIME) // 使用@Retention注定该注解保留到什么时候
@Inherited // 使用@Inherited标志该注解会被继承
@Documented // 使用@Documented标志该注解将写入到JavaDoc中
@interface ConstructorAnnotation {

}

@Retention(RetentionPolicy.RUNTIME)
@interface test {
	// 增加成员变量采用`无形参的方法`形式来声明
	// 方法名和返回值定义了该成员变量的名字和类型
	String name() default "name";

	int age() default -1;

}

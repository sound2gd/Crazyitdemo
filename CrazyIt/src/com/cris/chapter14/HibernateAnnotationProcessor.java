package com.cris.chapter14;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;

/**
 * 测试注解处理工具APT(Annotation Processing Tool)
 * 
 * @author cris
 *
 */
// 指定版本
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({ "Persistence", "Id", "Property" })
public class HibernateAnnotationProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		// 定义一个文件输出流，用来生成额外的文件
		PrintStream ps = null;

		try {
			// 遍历每一个被@Persistence修饰的class文件
			for (Element ele : roundEnv.getElementsAnnotatedWith(Persistence.class)) {
				// 获取正在处理的类名
				Name clazzName = ele.getSimpleName();
				System.out.println(clazzName);
				// 获取类定义前的@Persistence注解
				Persistence annotation = ele.getAnnotation(Persistence.class);

				System.out.println(annotation);
				// 创建输出流
				ps = new PrintStream(new FileOutputStream(clazzName + ".hbm.xml"));
				// 执行输出
				ps.println("<?xml version=\"1.0\"?>");
				ps.println("<!DOCTYPE hibernate-mapping PUBLIC");
				ps.println("		\"-//Hibernate/Hibernate Mapping DTD 3.0//EN\">");
				ps.println("<hibernate-mapping>");
				ps.println("<class name=\"" + ele);
				// 输出table值
				ps.println("\" table=\"" + annotation.table() + "\">");
				for (Element e : ele.getEnclosedElements()) {
					// 只处理成员变量上的Annotation
					if (e.getKind() == ElementKind.FIELD) {
						Id id = e.getAnnotation(Id.class);
						// 当@id存在时输出<id.../>元素
						if (id != null) {
							ps.println("		<id name=\"" + e.getSimpleName() + "\" column=\"" + id.column()
									+ "\" type=\"" + id.type() + "\" />");

						}
						Property prop = e.getAnnotation(Property.class);
						// 当@property存在输出元素
						if (prop != null) {
							ps.println("		<property name=\"" + e.getSimpleName() + "\" column=\"" + prop.column()
									+ "\" type=\"" + prop.type() + "\" />");

						}

					}
					
				}
				
			}
			ps.println("		</class>");
			ps.println("</hibernate-mapping>");
			ps.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
		}

		return true;
	}

}

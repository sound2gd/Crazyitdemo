package com.cris.chapter15.f5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 测试序列化<br>
 * 序列化的对象所在的类及其成员变量都必须可序列化
 * 
 * @author sound2gd
 *
 */
public class ReadWriteTeacher {

	public static void main(String[] args) {
		Person p1 = new Person("孙悟空", 600);
		Teacher t1 = new Teacher("菩提祖师", p1);
		Teacher t2 = new Teacher("唐僧", p1);

		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("src/com/cris/chapter15/f5/obj.txt"));) {
			oos.writeObject(t1);
			// 该行不起作用，多次序列化同一个对象时，只有第一次序列化会把该对象转为字节序列输出
			p1.setName("猪八戒");
			oos.writeObject(p1);
			oos.writeObject(t2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/com/cris/chapter15/f5/obj.txt"))) {
			// 顺序必须和上面序列化的时候一致
			// 是后进先出？不是，是先进先出
			Teacher t3 = (Teacher) ois.readObject();
			Person p2 = (Person) ois.readObject();
			Teacher t4 = (Teacher) ois.readObject();
			// 构造器参数并没有打印，所以不是通过构造器来创建对象的
			System.out.println(t3);
			System.out.println(t4);
			System.out.println(p2);

			System.out.println("t1和t3是不是同一个对象:" + (t1 == t3));
			System.out.println("t4中的Person和p2是不是同一个对象:" + (t4.getStudent() == p2));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

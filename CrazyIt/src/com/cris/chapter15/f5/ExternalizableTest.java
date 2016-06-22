package com.cris.chapter15.f5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 测试Externalizable实现序列化 据传性能比Serializable快很多
 * 
 * @author sound2gd
 *
 */
public class ExternalizableTest {

	public static void main(String[] args) {
		PersonEx p = new PersonEx("猪八戒", 300);
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("src/com/cris/chapter15/f5/obj.txt"));) {
			oos.writeObject(p);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/com/cris/chapter15/f5/obj.txt"))) {
			PersonEx readObject = (PersonEx) ois.readObject();
			System.out.println(readObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

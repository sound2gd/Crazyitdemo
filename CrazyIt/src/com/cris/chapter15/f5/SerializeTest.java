package com.cris.chapter15.f5;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * 测试序列化<br>
 * 写入Java对象到文件<br>
 * 从文件中读取Java对象
 * 
 * @author sound2gd
 *
 */
public class SerializeTest {

	public static void main(String[] args) {

		Person person = new Person("孙悟空", 500);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				"src/com/cris/chapter15/f5/obj.txt"));) {
			oos.writeObject(person);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

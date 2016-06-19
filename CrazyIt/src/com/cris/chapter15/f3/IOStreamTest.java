package com.cris.chapter15.f3;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 测试字节流InputStream和OutputStream
 * 
 * @author sound2gd
 *
 */
public class IOStreamTest {

	public static void main(String[] args) throws Exception {

		// InputStream常用的实现类有FileInputStream
		try (FileInputStream fis = new FileInputStream("src/com/cris/chapter15/f3/IOStreamTest.java");) {
			// 定义缓冲数组，将读取缓冲数据长度的数据单元到缓冲数组中
			byte[] buffer = new byte[1024];
			// 定义当前读取的长度返回值
			int length = 0;
			System.out.println("FileInputStream是否支持mark?"+fis.markSupported());
			while ((length = fis.read(buffer)) != -1) {
				System.out.println("当前读取的长度为:" + length);
				System.out.println("文件内容:");
				System.out.println(new String(buffer));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// OutputStream的常用实现类有FileOutputStream
		try (
				// 以追加的形式写入到文件中
				FileOutputStream fos = new FileOutputStream("src/com/cris/chapter15/f3/IOStreamTest.java", true)) {
			fos.write("//这是追加的内容".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
// 这是追加的内容//这是追加的内容
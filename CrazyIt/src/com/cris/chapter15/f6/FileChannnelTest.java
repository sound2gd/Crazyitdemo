package com.cris.chapter15.f6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * 测试FileChannel的用法
 * 
 * @author sound2gd
 *
 */
public class FileChannnelTest {

	public static void main(String[] args) {
		File file = new File("src/com/cris/chapter15/f6/FileChannnelTest.java");
		try (
				// FileInputStream打开的FileChannel只能读取
				FileChannel fc = new FileInputStream(file).getChannel();
				// FileOutputStream打开的FileChannel只能写入
				FileChannel fo = new FileOutputStream("src/com/cris/chapter15/f6/a.txt").getChannel();) {

			// 将FileChannel的数据全部映射成ByteBuffer
			MappedByteBuffer mbb = fc.map(MapMode.READ_ONLY, 0, file.length());
			// 使用UTF-8的字符集来创建解码器
			Charset charset = Charset.forName("UTF-8");
			// 直接将buffer里的数据全部输出
			fo.write(mbb);
			mbb.clear();
			// 创建解码器
			CharsetDecoder decoder = charset.newDecoder();
			// 使用解码器将byteBuffer转换为CharBuffer
			CharBuffer decode = decoder.decode(mbb);
			System.out.println(decode);
		} catch (Exception e) {

		}
	}

}

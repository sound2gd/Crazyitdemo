package com.cris.chapter15.f6;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * RandomAccessFile打开的Channel既可以读取，也可以写入
 * 
 * @author sound2gd
 *
 */
public class RandomAccessFileChannelTest {

	public static void main(String[] args) {
		File f =  new File("src/com/cris/chapter15/f6/a.txt");
		
		try(
				//创建一个RandomAccessFile对象
				RandomAccessFile raf = new RandomAccessFile(f, "rw");
				//获取对应的Channel
				FileChannel fc = raf.getChannel();
				) {
			//将Channel中的所有数据映射成ByteBuffer
			MappedByteBuffer buffer = fc.map(MapMode.READ_ONLY, 0, f.length());
			//将Channel记录指针移动到最后
			//表示从最后开始写入
			fc.position(f.length());
			//将buffer中的所有数据输出
			fc.write(buffer);
			
			
		} catch (Exception e) {
		}
	}
	
}

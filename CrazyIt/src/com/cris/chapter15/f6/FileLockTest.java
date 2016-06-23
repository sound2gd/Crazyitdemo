package com.cris.chapter15.f6;

import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 测试JDK1.4之后提供的文件锁支持
 * @author sound2gd
 *
 */
public class FileLockTest {

	public static void main(String[] args){
		
		try(FileChannel fc = 
				new FileOutputStream
				("src/com/cris/chapter15/f6/a.txt").getChannel();){
			//使用非阻塞式对指定文件枷锁
			FileLock fl = fc.tryLock();
			Thread.sleep(1000*10);
			fl.release();
		}catch(Exception e){
			
		}
		
		
	}
	
}

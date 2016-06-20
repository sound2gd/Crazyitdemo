package com.cris.chapter15.f4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 测试RandomAccessFile往指定位置添加数据
 * @author sound2gd
 *
 */
public class RandomAccessAppend {

	public static void main(String[] args) {
		String src = "src/com/cris/chapter15/f4/RandomAccessFileTest.java";
		try {
			insert(src, 300, "//哈哈哈哈哈\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insert(String fileName,long pos,String insertContent) throws IOException{
		File temp = File.createTempFile("tmp", null);
		temp.deleteOnExit();
		try(
				RandomAccessFile raf = new RandomAccessFile
				(new File(fileName), "rw");
				FileInputStream fin = new FileInputStream(temp);
				FileOutputStream fos = new FileOutputStream(temp);
				) {
			
			//找到指定位置
			raf.seek(pos);
			//保存指定位置之后的数据到临时文件
			byte[] buf = new byte[64];
			int hasRead = 0;
			
			while((hasRead = raf.read(buf))!=-1){
				fos.write(buf, 0, hasRead);
			}
			
			//追加内容
			raf.seek(pos);
			raf.write(insertContent.getBytes());
			
			//追加临时文件的内容
			while((hasRead = fin.read(buf))!= -1){
				raf.write(buf,0,hasRead);
			}
		}
	}
}

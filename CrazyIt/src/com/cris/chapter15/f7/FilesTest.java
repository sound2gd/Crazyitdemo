package com.cris.chapter15.f7;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试Files
 * 
 * @author sound2gd
 *
 */
public class FilesTest {

	public static void main(String[] args) {
		try {
			//复制文件
			String src = "src/com/cris/chapter15/f7/FilesTest.java";
			Path path = Paths.get(src);
			Files.copy(Paths.get(src) ,new FileOutputStream("src/com/cris/chapter15/f7/test.txt"));
			//判断是否为隐藏文件
			System.out.println(Files.isHidden(Paths.get(src)));
			//一次性读取所有行
			List<String> readAllLines = Files.readAllLines(path,StandardCharsets.UTF_8);
			System.out.println(readAllLines);
			
			//判断指定文件的大小
			System.out.println("大小为:"+Files.size(path));
			//直接将多个字符串内容写入指定文件中
			//会覆盖
			List<String> list = new ArrayList<>();
			list.add("天高任鸟飞");
			list.add("海阔凭鱼跃");
			Files.write(Paths.get("src/com/cris/chapter15/f7/a.txt"), list, Charset.forName("UTF-8"));
			//列出一切
			Files.list(Paths.get(".")).forEach( file -> System.out.println(file));
			//使用Java8新增的Stream API来读取文件内容
			Files.lines(path,StandardCharsets.UTF_8).forEach(line -> System.out.println(line));
			//判断C盘总空间，可用空间
			FileStore fileStore = Files.getFileStore(Paths.get("c:"));
			System.out.println("C盘共有空间:"+fileStore.getTotalSpace()/1024/1024/1024d+"G");
			System.out.println("C盘可用空间:"+fileStore.getUsableSpace()/1024/1024/1024d+"G");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}

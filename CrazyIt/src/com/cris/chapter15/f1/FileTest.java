package com.cris.chapter15.f1;

import java.io.File;

/**
 * 测试Java中的File类访问文件系统
 * 
 * @author sound2gd
 *
 */
public class FileTest {

	public static void main(String[] args) throws Exception{
		File file = new File(".");
		System.out.println("获取文件名:"+file.getName());//.
		System.out.println("获取文件路径:"+file.getPath());//.
		System.out.println("获取文件绝对路径:"+file.getAbsolutePath());
		//获取相对路径的父路径可能会出错，下面的代码输出null
		System.out.println("获取父路径:"+file.getParent());//null
		//获取绝对路径的父目录
		System.out.println(file.getAbsoluteFile().getParentFile());
		//以下会移除多余的名称,得到真实的文件，unix系统会分析符号连接,windows系统会把盘符转小写
		System.out.println(file.getCanonicalFile());
		
		//在当前目录下新建一个临时文件
		File temp = File.createTempFile("sound2gd", ".test",file);
		//指定JVM退出时删除文件
		temp.deleteOnExit();
		
		//演示创建一个新文件
		File test = new File("src/com/cris/chapter15/f1/newFile");
		//并没有真正创建一个文件，而是创建了一个代表该文件的对象
		//只有调用createNewFile才会真正创建文件(写入磁盘)
		System.out.println("文件test是否存在? "+test.exists());
		if(!test.exists()){
			//创建一个新文件
			test.createNewFile();
		}
		
		//演示创建一个目录
		File testDir = new File("src/com/cris/chapter15/f1/testDir1/testDir2");
		if(!testDir.exists()){
			//创建目录mkdir只会创建一个目录
			//mkdirs会连子目录和父目录一起创建
			testDir.mkdirs();
		}
		
		//使用list方法可以列出当前路径下的所有文件和路径(不包含子目录的文件)
		String[] list = file.list();
		System.out.println("----------当前路径下所有的文件和目录------------");
		for (String name:list){
			System.out.println(name);
		}
		
		//使用FileNameFilter来过滤文件
		list = new File("src/com/cris/chapter14").list((dir,name) -> name.endsWith("java"));
		System.out.println("----------src/com/cris/chapter14下所有的以java结尾的文件和目录------------");
		for (String name:list){
			System.out.println(name);
		}
		//列出磁盘根路径,Windows下是盘符,unix下是/
		File[] listRoots = File.listRoots();
		System.out.println("----------磁盘根路径-----------");
		for (File fi:listRoots){
			System.out.println(fi);
		}
		
	}
	
}

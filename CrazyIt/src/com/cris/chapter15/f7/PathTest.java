package com.cris.chapter15.f7;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 测试NIO.2的Path
 * 
 * @author sound2gd
 *
 */

public class PathTest {

	public static void main(String[] args) {

		// 以当前路径来创建Path对象
		Path path = Paths.get(".");
		System.out.println("path里包含的路径数量:" + path.getNameCount());
		System.out.println("path的根路径:" + path.getRoot());// null
		// 获取Path的绝对路径
		Path absolutePath = path.toAbsolutePath();
		System.out.println(absolutePath);
		// 获取绝对路径的根路径
		System.out.println(absolutePath.getRoot());
		// 绝对路径包含的路径数量
		System.out.println(absolutePath.getNameCount());

		// 使用多个String来构造Path对象
		Path localPath = Paths.get("L:", "User1s");
		System.out.println(localPath);
		System.out.println(localPath.getFileName());
		System.out.println(localPath.getFileSystem());
		System.out.println(localPath.toFile().exists());
	}

}

package com.cris.chapter15.f7;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 使用FileVisitor来遍历目录
 * @author sound2gd
 *
 */
public class FileVisitorTest {

	public static void main(String[] args) throws Exception {
		Files.walkFileTree(Paths.get("L:/workspace1"), new SimpleFileVisitor<Path>(){

			@Override
			//开始访问目录触发该方法
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				System.out.println("正在访问:"+dir+" 目录");
				return FileVisitResult.CONTINUE;
			}

			//访问文件触发该方法
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				System.out.println("正在访问: "+file+" 文件");
				if(file.endsWith("FileVisitorTest.java")){
					return FileVisitResult.TERMINATE;
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				return super.visitFileFailed(file, exc);
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				return super.postVisitDirectory(dir, exc);
			}
			
		});
		
	}
	
}

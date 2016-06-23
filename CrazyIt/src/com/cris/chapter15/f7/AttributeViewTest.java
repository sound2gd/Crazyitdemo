package com.cris.chapter15.f7;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.util.Date;
import java.util.List;

/**
 * 测试NIO.2访问文件属性
 * 
 * @author sound2gd
 *
 */
public class AttributeViewTest {

	public static void main(String[] args) throws Exception {
		//获取将要操作的文件
		Path path = Paths.get("src/com/cris/chapter15/f7/AttributeViewTest.java");
		//获取访问基本属性的BasicFileAtributeView
		BasicFileAttributeView bv = Files.getFileAttributeView(path, BasicFileAttributeView.class);
		//获取访问基本属性的BasicFileAttribute
		BasicFileAttributes basicAttr = bv.readAttributes();
		//访问文件的基本属性
		System.out.println("创建时间:"+new Date(basicAttr.creationTime().toMillis()).toLocaleString());
		System.out.println("最后访问时间:"+new Date(basicAttr.lastAccessTime().toMillis()).toLocaleString());
		System.out.println("最后修改时间:"+new Date(basicAttr.lastModifiedTime().toMillis()).toLocaleString());
		System.out.println("文件大小:"+basicAttr.size());
		
		//访问获取文件属主信息的FileOwnerAttributeView
		FileOwnerAttributeView fov = Files.getFileAttributeView(path, FileOwnerAttributeView.class);		
		//获取该文件所属的用户
		System.out.println("文件所属用户 :"+fov.getOwner());
		//获取系统中guest对应的用户
		UserPrincipal guest = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("guest");
		//修改用户
//		fov.setOwner(guest);
		
		//获取访问自定义文件属性的UserDefinedFileAttributeView
		UserDefinedFileAttributeView udfav = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
		List<String> list = udfav.list();
		for(String name:list){
			ByteBuffer buf = ByteBuffer.allocate(udfav.size(name));
			udfav.read(name, buf);
			buf.flip();
			String value = Charset.defaultCharset().decode(buf).toString();
			System.out.println(name+"--->"+value);
		}
		
		//添加一个自定义属性
		udfav.write("作者", Charset.defaultCharset().encode("sound2gd"));
		//获取访问Dos属性的DosFileAttributeView
		DosFileAttributeView dos = Files.getFileAttributeView(path, DosFileAttributeView.class);
		dos.setHidden(true);
		dos.setReadOnly(true);
	}
	
}

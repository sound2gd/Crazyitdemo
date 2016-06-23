package com.cris.chapter15.f7;

import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * 使用Path类提供的WatchService来监控文件的变化
 * 
 * @author sound2gd
 *
 */
public class WatchServiceTest {

	public static void main(String[] args) throws Exception {
		WatchService watchService = FileSystems.getDefault().newWatchService();
		// 为C:盘根路径设置注册监听
		Paths.get("C:/").register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
		while (true) {
			// 获取下一个文件变化事件
			WatchKey key = watchService.take();
			for (WatchEvent<?> event : key.pollEvents()) {
				System.out.println(event.context()+" 文件发生了"+event.kind()+"事件");
			}
			//重设watchkey
			boolean valid = key.reset();
			if(!valid){
				//重设失败则退出
				break;
			}
		}

	}

}

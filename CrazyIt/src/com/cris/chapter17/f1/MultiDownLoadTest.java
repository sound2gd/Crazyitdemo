package com.cris.chapter17.f1;

/**
 * 多线程下载测试类
 * 
 * @author cris
 *
 */
public class MultiDownLoadTest {

	public static void main(String[] args) {
		// 初始化downUtil类对象
		final DownUtil downUtil = new DownUtil(
				"http://mirrors.hust.edu.cn/apache/lucene/solr/6.1.0/solr-6.1.0.tgz",
				"/Users/cris/Desktop/solr-6.1.0.tgz", 512);
		// 开始下载
		try {
			downUtil.download();
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Thread(() -> {
			while (downUtil.getCompleteRate() < 1) {
				// 每隔0.1秒查询一次进度
				// GUI程序可以根据该进度来绘制进度条
				System.out.println("已完成：" + downUtil.getCompleteRate());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}

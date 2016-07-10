package com.cris.chapter17.f1;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 粗浅实现多线程下载
 * 
 * @author cris
 *
 */
public class DownUtil {

	// 定义下载资源的路径
	private String path;
	// 定义所下载的文件的保存位置
	private String targetFile;
	// 定义需要多少个线程下载该资源
	private int threadNum;
	// 定义下载的线程对象
	private DownThread[] downThreads;
	// 定义下载的文件总大小
	private int fileSize;

	public DownUtil(String path, String targetFile, int threadNum) {
		super();
		this.path = path;
		this.targetFile = targetFile;
		this.threadNum = threadNum;
		// 初始化threads数组
		downThreads = new DownThread[threadNum];
	}

	public void download() throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Aceept",
				"image/gif,image/jpeg,image/pjepg," + "application/x-shockwave-flash,application/xaml+xml,"
						+ "application/vnd.ms-xpsdocument,application/x-ms-xbap"
						+ "application/x-ms-application,application/vnd.ms-excel"
						+ "application/vnd.ms-powerpoint,application/msword,*/*");
		conn.setRequestProperty("Accept-Language", "zh-CN");
		conn.setRequestProperty("Charset", "UTF-8");

		// 得到文件大小
		fileSize = conn.getContentLength();
		conn.disconnect();

		int currentPartSize = fileSize / threadNum + 1;

		// 初始化下载文件
		RandomAccessFile file = new RandomAccessFile(new File(targetFile), "rw");
		// 设置本地文件的大小
		file.setLength(fileSize);
		file.close();

		for (int i = 0; i < threadNum; i++) {
			// 计算每个线程下载的开始位置
			int startPos = i * currentPartSize;
			// 每个线程使用一个RandomAccessFile进行下载
			RandomAccessFile currentPart = new RandomAccessFile(targetFile, "rw");
			// 定义该线程的下载位置
			currentPart.seek(startPos);
			// 创建下载线程
			downThreads[i] = new DownThread(startPos, currentPartSize, currentPart);
			// 启动下载线程
			downThreads[i].start();
		}
	}

	// 获取下载完成的百分比
	public double getCompleteRate() {
		// 统计多个线程已下载的大小
		int sumSize = 0;
		for (int i = 0; i < threadNum; i++) {
			sumSize += downThreads[i].length;
		}
		// 返回已下载的百分比
		return sumSize * 1.0 / fileSize;
	}

	/**
	 * 定义下载的线程
	 * 
	 * @author cris
	 *
	 */
	private class DownThread extends Thread {
		// 定义当前线程下载位置
		private int startPos;
		// 定义当前线程负责下载的文件大小
		private int currentPartSize;
		// 当前线程需要下载的文件块
		private RandomAccessFile currentPart;
		// 定义该线程已经下载的字节数
		private int length;

		public DownThread(int startPos, int currentPartSize, RandomAccessFile currentPart) {
			super();
			this.startPos = startPos;
			this.currentPartSize = currentPartSize;
			this.currentPart = currentPart;
		}

		@Override
		public void run() {
			try {
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5 * 1000);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Aceept",
						"image/gif,image/jpeg,image/pjepg," + "application/x-shockwave-flash,application/xaml+xml,"
								+ "application/vnd.ms-xpsdocument,application/x-ms-xbap"
								+ "application/x-ms-application,application/vnd.ms-excel"
								+ "application/vnd.ms-powerpoint,application/msword,*/*");
				conn.setRequestProperty("Accept-Language", "zh-CN");
				conn.setRequestProperty("Charset", "UTF-8");

				InputStream inputStream = conn.getInputStream();
				// 跳过startPos个字节，表示该线程只下载自己负责的那部分文件
				inputStream.skip(startPos);

				// 定义缓冲数组
				byte[] buffer = new byte[1024];
				int hasRead = 0;
				while ((hasRead = inputStream.read(buffer)) != -1 && length < currentPartSize) {
					currentPart.write(buffer, 0, hasRead);
					// 累计该线程下载的总大小
					length += hasRead;
				}

				currentPart.close();
				inputStream.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}

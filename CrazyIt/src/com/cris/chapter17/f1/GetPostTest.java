package com.cris.chapter17.f1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * 测试使用GET和POST方法发送请求
 * 
 * @author cris
 *
 */
public class GetPostTest {

	public static String sendGET(String url, String param) {
		String result = "";
		String urlName = url + "?" + param;
		try {
			URL readURL = new URL(urlName);
			// 打开和URL之间的链接
			URLConnection conn = readURL.openConnection();

			conn.setConnectTimeout(2000);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
			// 建立实际的连接
			conn.connect();

			// 获取所有响应头字段
			Map<String, List<String>> headerFields = conn.getHeaderFields();
			// 遍历所有的响应头字段
			for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
				System.out.println(entry.getKey() + "-->" + entry.getValue());
			}

			// 定义BufferedReader输入流来URL的响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;

			while ((line = reader.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求异常！");
			e.printStackTrace();
		}
		return result;
	}

	public static String sendPOST(String url, String param) {
		String result = "";
		try {
			URL readURL = new URL(url);
			// 打开和URL之间的链接
			URLConnection conn = readURL.openConnection();

			conn.setConnectTimeout(2000);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");

			// 发送POST需要设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);

			// 获取URLConnection对应的输出流
			PrintWriter printWriter = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			printWriter.print(param);
			printWriter.flush();

			// 定义BufferedReader输入流来URL的响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;

			while ((line = reader.readLine()) != null) {
				result += "\n" + line;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		String sendGET = sendPOST("https://www.baidu.com", "");
		System.out.println(sendGET);
	}

}

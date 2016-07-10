package com.cris.chapter17.f1;

import java.net.InetAddress;

/**
 * 测试使用Java提供的InetAddress类
 * 
 * @author cris
 *
 */
public class InetAddressTest {

	public static void main(String[] args) throws Exception {
		// 根据主机名来获取对应的InetAddress实例
		InetAddress ip = InetAddress.getByName("www.baidu.com");
		System.out.println("www.baidu.com的ip是: " + ip);

		// 判断是否可达
		boolean reachable = ip.isReachable(2000);
		System.out.println("IP是否可达: " + reachable);

		// 获取该InetAddress实例的IP字符串
		System.out.println(ip.getHostAddress());

		// 根据原始IP地址来获取对应的InetAddress实例
		InetAddress local = InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 });
		System.out.println("本机是否可达: " + local.isReachable(2000));

		// 获取该Ip地址的全限定域名
		System.out.println(local.getCanonicalHostName());

	}

}

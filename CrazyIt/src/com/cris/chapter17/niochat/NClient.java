package com.cris.chapter17.niochat;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * NIO聊天客户端程序
 * Created by sound2gd on 2016/7/12 0012.
 */
public class NClient {

    //定义检测SocketChannel的Selector对象
    private Selector selector = null;

    //定义处理编码和解码的字符集
    private Charset charset = StandardCharsets.UTF_8;

    //客户端SocketChannel
    private SocketChannel sc = null;

    public void init() throws Exception {
        selector = Selector.open();
        InetSocketAddress isa = new InetSocketAddress("localhost", 8888);
        sc = SocketChannel.open(isa);
        //设置该sc 以非阻塞的方式工作
        sc.configureBlocking(false);
        // 将SocketChannel对象注册到指定的Selector
        sc.register(selector, SelectionKey.OP_READ);
        //启动读取数据的线程
        new ClientThread().start();

        //创建键盘输入流
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            //读取键盘输入
            String line = scanner.nextLine();
            //将键盘输入的内容输出到ScoketChannel中
            sc.write(charset.encode(line));
        }


    }

    private class ClientThread extends Thread{

        @Override
        public void run() {

            try {
                while (selector.select() > 0) {
                    //遍历每个有可用IO操作的Channel对应的SelectionKey
                    for (SelectionKey sk : selector.selectedKeys()) {
                        //删除正在处理的SelectionKey
                        selector.selectedKeys().remove(sk);

                        //如果该SelectionKey对应的Channel中有可读的数据
                        if (sk.isReadable()) {
                            //使用NIO从Channel中读取数据
                            SocketChannel channel = (SocketChannel) sk.channel();
                            //创建缓冲
                            ByteBuffer bf = ByteBuffer.allocate(1024);
                            String content = "";

                            while ((channel.read(bf)) > 0) {
                                sc.read(bf);
                                bf.flip();
                                content += charset.decode(bf);
                            }

                            //打印输出读取的内容
                            System.out.println("聊天信息:" + content);

                            //为下一次读取做准备
                            sk.interestOps(SelectionKey.OP_READ);

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws Exception {
        new NClient().init();
    }

}

package com.cris.chapter17.niochat;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 使用NIO来实现聊天室
 * Created by sound2gd on 2016/7/11 0011.
 */
public class NServer {

    // 用于检测所有Channel状态的selector
    private Selector selector = null;

    // 定义实现编码，解码的字符集对象
    private Charset charset = StandardCharsets.UTF_8;

    public void init() throws Exception {
        selector = Selector.open();
        //通过open方法来打开一个未绑定的ServerSocketChannel实例
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 8888);
        // 绑定到指定地址
        server.bind(isa);
        // 设置以非阻塞的方式工作
        server.configureBlocking(false);
        // 将Server注册到指定的Selector对象
        server.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            // 依次处理selector上的已选择的SelectionKey
            for (SelectionKey sk : selector.selectedKeys()) {
                //从selector上的已选择key集中删除正在处理的SelectionKey
                selector.selectedKeys().remove(sk);
                //如果sk对应的Channel包含客户端的连接请求
                if (sk.isAcceptable()) {
                    //调用accept方法接受此连接,产生服务器端的SocketChannel
                    SocketChannel accept = server.accept();
                    //采用非阻塞模式
                    accept.configureBlocking(false);
                    //将该SocketChannel注册到selector
                    accept.register(selector, SelectionKey.OP_READ);
                    //将sk对应的Channel设置成准备接受其他请求
                    sk.interestOps(SelectionKey.OP_ACCEPT);

                }

                // 如果sk对应的Channel有数据需要读取
                if (sk.isReadable()) {
                    // 获取该SelctionKey对应的Channel,该Channel有可读的数据
                    SocketChannel channel = (SocketChannel) sk.channel();
                    //定义准备执行读取数据的ByteBuffer
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    String content = "";
                    //开始读取数据
                    try {
                        while (channel.read(buffer) > 0) {
                            buffer.flip();
                            content += charset.decode(buffer);
                        }
                        //打印从该SK对应的Channel读取到的数据
                        System.out.println("读取的数据" + content);
                        //将sk对应的channel设置成准备下一次读取
                        sk.interestOps(SelectionKey.OP_READ);
                    } catch (Exception e) {
                        //如果捕获到了该SK对应的Channel出现了异常，即表明
                        //该Channel对应的Client出现了问题，所以从selctor中取消该Sk的注册

                        sk.cancel();
                        if (sk.channel() != null) {
                            sk.channel().close();
                        }

                    }
                    //如果content的长度大于0,即聊天信息不为空，
                    if (content.length() > 0) {
                        //遍历该selecor里注册的所有SelectionKey
                        for (SelectionKey key : selector.keys()) {
                            //获取该key对应的channel
                            SelectableChannel target = key.channel();
                            //如果该Channel是SocketChannel对象
                            if (target instanceof SocketChannel) {
                                // 将读取到的内容写入该channel中
                                SocketChannel dest = (SocketChannel) target;
                                dest.write(charset.encode(content));
                            }
                        }
                    }

                }
            }
        }

    }

    public static void main(String[] args) {
        try {
            new NServer().init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

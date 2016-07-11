package com.cris.chapter17.biochat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 改进的Server端，可以实现记住用户信息的聊天室
 * Created by sound2gd on 2016/7/11 0011.
 */
public class Server2 {

    public static Map<String, Socket> users = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Server2 server2 = new Server2();
        server2.init();
    }

    private void init() {
        //建立监听的Socket
        try (ServerSocket ss = new ServerSocket(8888)) {

            while (true) {
                Socket socket = ss.accept();
                new ServerThread2(socket).start();
            }

        } catch (IOException e) {
            System.out.println("服务器启动失败，是否端口已占用?");
            e.printStackTrace();
        }
    }
}

/**
 * 服务器端线程，负责为客户端服务
 * 每次监听到客户端连接都会启动一个新的该线程
 */
class ServerThread2 extends Thread {

    private Socket socket;
    private BufferedReader br;
    private PrintStream ps;

    public ServerThread2(Socket socket) throws IOException {
        this.socket = socket;
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ps = new PrintStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            //不断读取客户端Socket发来的消息
            String line = null;
            while ((line = br.readLine()) != null) {

                //如果读取到的是以CrazyItProtocol.User_ROUND开头和结束，就可以判断
                //读取到的是用户名
                if (line.startsWith(CrazyItProtocol.USER_ROUND)
                        && line.endsWith(CrazyItProtocol.USER_ROUND)) {
                    //得到真实信息
                    String userName = line.substring(CrazyItProtocol.PROTOCOL_LEN, line.length() - CrazyItProtocol.PROTOCOL_LEN);

                    //如果用户名重复
                    if (Server2.users.containsKey(userName)) {
                        System.out.println("用户名重复");
                        ps.println(CrazyItProtocol.NAME_REP);
                    }else {
                        System.out.println("成功");
                        ps.println(CrazyItProtocol.LOGIN_SUCCESS);
                        Server2.users.put(userName, socket);
                    }

                } else if (line.startsWith(CrazyItProtocol.PRIVATE_ROUND)
                        && line.endsWith(CrazyItProtocol.PRIVATE_ROUND)) {
                    //如果是以PRIVATE_ROUND开头的就认为是私聊信息
                    String realMsg = line.substring(CrazyItProtocol.PRIVATE_ROUND.length(), line.length() - CrazyItProtocol.PRIVATE_ROUND.length());
                    //以SPLIT_SIGN 分割字符串，前面认为是用户名，后面认为是聊天信息
                    String[] split = realMsg.split(CrazyItProtocol.SPLIT_SIGN);
                    String userName = split[0];
                    String msg = split[1];

                    //获取私聊用户对应的输出流,以及本人对应用户名
                    String myName = null;
                    if (!Server2.users.containsValue(socket)){
                        throw new Exception("用户断开了连接或者不存在");
                    }
                    for (Map.Entry<String, Socket> entry : Server2.users.entrySet()) {
                        if (entry.getValue().equals(socket)) {
                            myName = entry.getKey();
                        }
                    }

                    new PrintStream(Server2.users.get(userName).getOutputStream())
                            .println(myName+"用户 悄悄的对你说:"+msg);

                }else {
                    //公聊向每一个Scoket发送
                    //获取私聊用户对应的输出流,以及本人对应用户名
                    String myName = null;
                    if (!Server2.users.containsValue(socket)){
                        throw new Exception("用户断开了连接或者不存在");
                    }
                    for (Map.Entry<String, Socket> entry : Server2.users.entrySet()) {
                        if (entry.getValue().equals(socket)) {
                            myName = entry.getKey();
                        }
                    }
                    for (Socket s : Server2.users.values()) {
                        new PrintStream(s.getOutputStream()).println(myName+"用户对所有人说:"+line);
                    }
                }

            }
        } catch (Exception e) {
            for (Map.Entry<String, Socket> entry : Server2.users.entrySet()) {
                if (entry.getValue().equals(socket)) {
                    Server2.users.remove(entry.getKey());
                }
            }
            try {
                //关闭资源
                br.close();
                ps.close();
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }
}
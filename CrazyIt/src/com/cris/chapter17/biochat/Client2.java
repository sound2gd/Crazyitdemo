package com.cris.chapter17.biochat;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 多线程服务器BIO通信客户端
 * Created by sound2gd on 2016/7/11 0011.
 */
public class Client2 {

    private Socket socket;
    private PrintStream ps;
    private BufferedReader reader;
    private BufferedReader keyIn;

    public void init(){
        try {
            //监听键盘的输入流
            keyIn = new BufferedReader(new InputStreamReader(System.in));
            //连接到服务器
            socket = new Socket("localhost", 8888);
            //获取该Socket对应的输入输出流
            ps = new PrintStream(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String tip = "";
            while (true) {
                String userName = JOptionPane.showInputDialog(tip + "输入用户名:");
                //在输入的用户名上加上协议字符串后发送
                ps.println(CrazyItProtocol.USER_ROUND+userName+CrazyItProtocol.USER_ROUND);
                //读取服务器端的响应
                String result = reader.readLine();
                //如果用户名重复则开始下次循环
                if (result.equals(CrazyItProtocol.NAME_REP)) {
                    tip = "用户名重复，请重新";
                    continue;
                }

                //登陆成功就退出循环
                if (result.equals(CrazyItProtocol.LOGIN_SUCCESS)){
                    break;
                }

            }
            new ClientThread(socket).start();
        } catch (Exception e) {
            System.out.println("连接远程服务器失败");
            closeRS();
            System.exit(1);
        }
    }

    public void readAndSend(){
        try{
            //不断读取键盘输入
            String line = null;
            while ((line = keyIn.readLine()) != null) {
                //认为是私聊信息
                if (line.indexOf(":") > 0 && line.startsWith("//")) {
                    line = line.substring(2);
                    ps.println(CrazyItProtocol.PRIVATE_ROUND+"" +
                            line.split(":")[0]+CrazyItProtocol.SPLIT_SIGN
                    +line.split(":")[1]+CrazyItProtocol.PRIVATE_ROUND);
                }else {
                    ps.println(line);
                }
            }

        }catch (Exception e){
            closeRS();
        }
    }

    public void closeRS(){
        try {
            reader.close();
            ps.close();
            socket.close();
            keyIn.close();
        } catch (Exception e) {

        }
    }

    //主方法
    public static void main(String[] args) {
        Client2 client2 = new Client2();
        client2.init();
        client2.readAndSend();
    }

}

package com.cris.chapter17.biochat;

/**
 * 用于编写简单多线程服务器中通信协议字段
 * Created by sound2gd on 2016/7/11 0011.
 */
public interface CrazyItProtocol {

    //定义协议字符串的长度
    int PROTOCOL_LEN = 5;
    //下面是一些协议字符串，服务器端和客户端都应该在前后加上这种特殊字符串
    String MSG_ROUND = "$MSG";
    String USER_ROUND = "$USER";
    String LOGIN_SUCCESS = "1";
    String NAME_REP = "-1";
    String PRIVATE_ROUND = "$PRIVATE";
    String SPLIT_SIGN = "#";
}

package com.crakama.common;

import java.io.Serializable;

public class MsgProtocol implements Serializable{
    private final MsgType msgType;
    private final String msgBody;


    public MsgProtocol(MsgType msgType, String msgBody){
        this.msgType = msgType;
        this.msgBody = msgBody;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType, String msgBody){

    }
}

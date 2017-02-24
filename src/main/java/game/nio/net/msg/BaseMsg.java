package game.nio.net.msg;

import game.nio.Constants;

import java.io.Serializable;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.net.msg
 * 概要：消息基类
 */
public abstract class BaseMsg implements Serializable{
    private static final long serialVersionUID = 1L;
    private final String cid= Constants.CID;
    private MsgType msgType;

    public MsgType getMsgType() {
        return msgType;
    }

    protected void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public final String getCid(){return this.cid;}
}

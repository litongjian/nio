package game.nio.net.msg;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.net.msg
 * 概要：心跳消息
 */
public final class PingMsg extends BaseMsg {
    public PingMsg(){
        super();
        setMsgType(MsgType.PING);
    }
}

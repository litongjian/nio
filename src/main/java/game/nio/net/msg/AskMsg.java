package game.nio.net.msg;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.net.msg
 * 概要：请求消息
 */
public class AskMsg extends BaseMsg {
    public AskMsg(){
        super();
        setMsgType(MsgType.ASK);
    }
    private AskParam param;

    public AskParam getParam() {
        return param;
    }

    public void setParam(AskParam param) {
        this.param = param;
    }
}

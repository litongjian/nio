package game.nio.net.msg;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.net.msg
 * 概要：服务端响应消息体
 */
public final class ReplyServerBody extends ReplyBody {
    private String info;

    public ReplyServerBody(){
        super();
    }

    public ReplyServerBody(String info){
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

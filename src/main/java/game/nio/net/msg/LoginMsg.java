package game.nio.net.msg;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.net.msg
 * 概要：登录消息
 */
public final class LoginMsg extends BaseMsg{
    public LoginMsg(){
        super();
        setMsgType(MsgType.LOGIN);
    }
    private String user;
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

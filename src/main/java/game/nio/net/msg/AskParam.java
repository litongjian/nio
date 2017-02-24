package game.nio.net.msg;

import java.io.Serializable;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.net.msg
 * 概要：请求参数
 */
public final class AskParam implements Serializable {
    private static final long serialVersionUID = 1L;
    private String auth;//令牌
    private String user;//用户
    private byte[] data;//数据 json

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

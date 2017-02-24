package game.nio.po;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.po
 * 概要：返回数据
 */
public final class ResData {
    @JSONField
    private int state;
    @JSONField
    private String uri;
    @JSONField
    private String data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

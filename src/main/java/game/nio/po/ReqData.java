package game.nio.po;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.po
 * 概要：请求参数Bean
 */
public final class ReqData {
    @JSONField
    private String dest;//来源ID
    @JSONField
    private String uri;//目标服务URI
    @JSONField
    private String data;//数据json字符串

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
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

    public JSONObject dataObject(){
        return data==null?null:JSONObject.parseObject(data);
    }

    public String serviceURI(){
        String[] uris = uri.split("@");
        return uris.length>0?uris[1]:null;
    }

    public String methodURI(){
        String[] uris = uri.split("@");
        return uris.length>1?uris[1]:null;
    }
}

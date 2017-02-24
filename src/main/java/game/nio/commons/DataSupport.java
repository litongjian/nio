package game.nio.commons;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import game.nio.net.msg.AskParam;
import game.nio.po.ReqData;
import game.nio.po.ResData;

import java.io.UnsupportedEncodingException;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.commons
 * 概要：数据编解码器
 */
public final class DataSupport {

    /**
     * @param param 请求参数
     * @return 请求数据
     * @throws UnsupportedEncodingException 解码异常
     */
    public static ReqData decodeByJson(AskParam param) throws UnsupportedEncodingException {
        String str = new String(param.getData(),"UTF-8");
        return JSONObject.parseObject(str,ReqData.class);
    }

    /**
     * @param data 返回对象数据
     * @return json字符串
     */
    public static String encodeByJson(ResData data){
        return JSON.toJSONString(data);
    }
}

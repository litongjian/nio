package game.nio.global;

import com.alibaba.fastjson.JSONObject;
import game.nio.expr.ProcessFiledException;
import game.nio.expr.SystemException;
import game.nio.po.ReqData;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.global
 * 概要：基础服务接口
 */
public interface IService {

    /**
     * 业务请求处理
     * @param param 请求消息
     * @return 处理结果
     * @throws ProcessFiledException 业务处理失败抛出此异常
     * @throws SystemException 代码执行失败抛出此异常
     */
    JSONObject process(ReqData param)throws ProcessFiledException,SystemException;
}

package game.nio.server.service;

import com.alibaba.fastjson.JSONObject;
import com.sun.istack.internal.NotNull;
import game.nio.expr.ProcessFiledException;
import game.nio.expr.SystemException;
import game.nio.global.IService;
import game.nio.global.ServiceRoute;
import game.nio.net.msg.ReplyBody;
import game.nio.net.msg.ReplyServerBody;
import game.nio.po.ReqData;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.server.service
 * 概要：服务端 路由服务
 */
public class ServerServiceRoute extends ServiceRoute {

    public ReplyBody process(ReqData param, @NotNull String dest) throws ProcessFiledException, SystemException {
        param.setDest(dest);
        IService service = get(param.serviceURI());
        if (service==null)throw new ProcessFiledException("URI错误");
        JSONObject data =  service.process(param);
        ReplyServerBody rb = new ReplyServerBody();
        rb.setInfo(data.toJSONString());
        return rb;
    }

    public ReplyBody process(ReqData data) throws ProcessFiledException, SystemException {
        IService service = get(data.serviceURI());
        if (service==null)throw new ProcessFiledException("URI错误");
        JSONObject rs =  service.process(data);
        ReplyServerBody rb = new ReplyServerBody();
        rb.setInfo(rs.toJSONString());
        return rb;
    }

}

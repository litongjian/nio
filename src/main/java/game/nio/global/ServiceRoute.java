package game.nio.global;

import com.sun.istack.internal.NotNull;
import game.nio.expr.ProcessFiledException;
import game.nio.expr.SystemException;
import game.nio.net.msg.ReplyBody;
import game.nio.po.ReqData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.global
 * 概要：服务路由
 */
public abstract class ServiceRoute {

    private final Map<String,IService> map = new ConcurrentHashMap<String, IService>();//服务集合

    /**
     * 注册服务
     * @param uri 服务URI
     * @param service 服务对象
     */
    public final void register(String uri,IService service){
        map.put(uri,service);
    }

    /**
     * 注销服务
     * @param uri URI
     */
    public final void unregister(String uri){
        map.remove(uri);
    }

    /**
     * 获取服务
     * @param uri 服务URI
     * @return 服务对象
     */
    public final IService get(String uri){
        return map.get(uri);
    }

    /**
     * @param param 参数
     * @param dest 来源
     * @return 返回体
     * @throws ProcessFiledException 业务失败抛出此异常
     * @throws SystemException 代码异常
     */
    public abstract ReplyBody process(ReqData param,@NotNull String dest)throws ProcessFiledException,SystemException;

    /**
     * @param param 参数
     * @return 返回体
     * @throws ProcessFiledException 业务失败抛出此异常
     * @throws SystemException 代码异常
     */
    public abstract ReplyBody process(ReqData param)throws ProcessFiledException,SystemException;
}

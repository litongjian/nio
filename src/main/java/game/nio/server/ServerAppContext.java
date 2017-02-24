package game.nio.server;

import game.nio.annotation.ProcessService;
import game.nio.commons.ConfigsLoader;
import game.nio.global.IService;
import game.nio.global.ServiceRoute;

import java.io.IOException;
import java.util.List;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.server
 * 概要：客户端全局上下文
 */
public final class ServerAppContext {

    static {
        try {
            init();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
    private static ServiceRoute serviceRoute;

    /**
     * @return 服务路由
     */
    public static ServiceRoute serviceRoute(){
        return serviceRoute;
    }

    /**
     * 初始化
     * 加载服务
     */
    private static void init() throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {
        //加载路由
        serviceRoute  = ConfigsLoader.loadServerRoute();
        //加载服务组
        List<Class<? extends IService>> services = ConfigsLoader.loadServerServiceClassList();
        for (Class<? extends IService> cs:services){
            ProcessService ps = cs.getAnnotation(ProcessService.class);
            serviceRoute.register(ps.uri(),cs.newInstance());
        }

    }

}

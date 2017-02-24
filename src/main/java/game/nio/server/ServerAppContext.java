package game.nio.server;

import game.nio.annotation.ProcessService;
import game.nio.annotation.UserHandler;
import game.nio.commons.ConfigsLoader;
import game.nio.commons.HandlerSortComparator;
import game.nio.global.IService;
import game.nio.global.ServiceRoute;
import io.netty.channel.ChannelHandlerAdapter;

import java.io.IOException;
import java.util.ArrayList;
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
    private static ServiceRoute serviceRoute;//服务器服务路由
    private static List<Class<? extends ChannelHandlerAdapter>> handlerList;
    private static ChannelHandlerAdapter decoder;
    private static ChannelHandlerAdapter encoder;

    /**
     * 获取ChannelHandler列表
     */
    public static List<ChannelHandlerAdapter> handlerList(){
        List<ChannelHandlerAdapter> list = new ArrayList<>();
        handlerList.forEach(hd ->{
            UserHandler uh = hd.getAnnotation(UserHandler.class);
            if (uh!=null&&uh.name().startsWith("server.")){
                try {
                    list.add(hd.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        list.sort(new HandlerSortComparator());
        return list;
    }

    /**
     * @return 解码器
     */
    public static ChannelHandlerAdapter decoder(){
        return decoder;
    }

    /**
     * @return 编码器
     */
    public static ChannelHandlerAdapter encoder(){
        return encoder;
    }

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
        handlerList = new ArrayList<>();
        //加载路由
        serviceRoute  = ConfigsLoader.loadServerRoute();
        //加载服务组
        List<Class<? extends IService>> services = ConfigsLoader.loadServerServiceClassList();
        for (Class<? extends IService> cs:services){
            ProcessService ps = cs.getAnnotation(ProcessService.class);
            serviceRoute.register(ps.uri(),cs.newInstance());
        }
        //加载解码器
        decoder = ConfigsLoader.loadDecoder();
        //加载编码器
        encoder = ConfigsLoader.loadEncoder();
        //加载Handler
        List<Class<? extends ChannelHandlerAdapter>> handlers = ConfigsLoader.loadServerHandlerList();
        for (Class<? extends ChannelHandlerAdapter> cs:handlers){
            UserHandler uh = cs.getAnnotation(UserHandler.class);
            if (uh!=null) {
                handlerList.add(cs);
            }
        }
        System.out.println("初始化完成");
    }

}

package game.nio.commons;

import game.nio.annotation.ProcessService;
import game.nio.annotation.UserHandler;
import game.nio.global.IService;
import game.nio.server.service.ServerServiceRoute;
import io.netty.channel.ChannelHandlerAdapter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.commons
 * 概要：配置文件加载器
 */
public final class ConfigsLoader {
    static {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Properties sp;//服务端配置文件
    private static Properties cp;//客户端配置文件

    public static ServerServiceRoute loadServerRoute() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return ClassScanTool.scanClassByProperties(sp,"server.route");
    }

    public static ChannelHandlerAdapter loadDecoder() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return ClassScanTool.scanClassByProperties(sp,"server.decoder");
    }


    public static ChannelHandlerAdapter loadEncoder() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return ClassScanTool.scanClassByProperties(sp,"server.encoder");
    }

    public static List<Class<? extends ChannelHandlerAdapter>> loadServerHandlerList() throws UnsupportedEncodingException {
        List<Class<? extends ChannelHandlerAdapter>> list = new LinkedList<>();
        List<Class> classList = ClassScanTool.scanClassListByProperties(sp,"server.handler");
        assert classList!=null;
        classList.forEach(clz-> {
            if(ChannelHandlerAdapter.class.isAssignableFrom(clz) && clz.getAnnotation(UserHandler.class) != null)list.add(clz);
        });
        return list;
    }

    public static List<Class<? extends IService>> loadServerServiceClassList() throws IOException, ClassNotFoundException {
        List<Class<? extends IService>>  list = new LinkedList<>();
        List<Class> classList = ClassScanTool.scanClassListByProperties(sp,"server.service");
        assert classList != null;
        classList.forEach(clz ->{
            if(IService.class.isAssignableFrom(clz)&&clz.getAnnotation(ProcessService.class)!=null){
                list.add(clz);
            }
        });
        return list;
    }

    private static void init() throws IOException {
        sp = new Properties();
        sp.load(ClassLoader.getSystemResourceAsStream("config/server.properties"));
        cp = new Properties();
        cp.load(ClassLoader.getSystemResourceAsStream("config/client.properties"));
    }
}

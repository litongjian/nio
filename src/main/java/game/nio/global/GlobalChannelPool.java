package game.nio.global;

import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.net
 * 概要：全局Channel缓存池
 */
public final class GlobalChannelPool {
    private static Map<String,SocketChannel> chMap = new ConcurrentHashMap<String, SocketChannel>();

    /**
     * @return 当前活动的Channel
     */
    public static int getActiveCount(){
        int count = 0;
        for (SocketChannel ch:chMap.values()){
            count+=ch.isActive()?1:0;
        }
        return count;
    }

    /**
     * @return 当前所有Channel
     */
    public static int getCount(){
        return chMap.size();
    }

    /**
     * 获取Channel
     * @param id 客户端ID
     * @return 客户端Channel
     */
    public static SocketChannel get(String id){
        return chMap.get(id);
    }

    /**
     * 添加Channel
     * @param id 客户端ID
     * @param channel 客户端Channel
     */
    public static void add(String id,SocketChannel channel){
        for (SocketChannel sc: chMap.values()){
            if (sc.id()==channel.id())return;
        }
        chMap.put(id,channel);
    }

    /**
     * 移除Channel,如果当前Channel有用户已登录则一并移除
     * @param channel 客户端Channel
     */
    public static void remove(SocketChannel channel){
        for (Map.Entry entry: chMap.entrySet()){
            if (entry.getValue()==channel) {
                chMap.remove(entry.getKey());
                GlobalAuthUserPool.removeByKey(entry.getKey());
            }
        }
    }
}

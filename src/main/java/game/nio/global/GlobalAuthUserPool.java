package game.nio.global;

import game.nio.po.AuthUser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.global
 * 概要：全局合法用户池
 */
public final class GlobalAuthUserPool {
    private static Map<String, AuthUser> map = new ConcurrentHashMap<String, AuthUser>();

    /**
     * @return 当前在线总数
     */
    public static int getOnlineCount(){
        int count = 0;
        for (Map.Entry<String,AuthUser> entry:map.entrySet()){
            count+=entry.getValue().isOnline()
                    && GlobalChannelPool.get(entry.getKey()).isActive()
                    && GlobalChannelPool.get(entry.getKey())!=null?1:0;
        }
        return count;
    }

    /**
     * 获取当前AuthUser总数
     */
    public static int getCount() {
        return map.size();
    }

    /**
     * 获取AuthUser
     */
    public static AuthUser get(String cid) {
        return map.get(cid);
    }

    /**
     * 添加AuthUser
     */
    public static void add(String cid, AuthUser val) {
        map.put(cid, val);
    }

    /**
     * 移除AuthUser
     */
    public static void remove(AuthUser val) {
        for (Map.Entry<String, AuthUser> entry : map.entrySet()) {
            if (entry.getValue().equals(val)) map.remove(entry.getKey());
        }
    }

    /**
     * 移除AuthUser
     * @param key 客户端ID
     */
    public static void removeByKey(Object key) {
        map.remove(key);
    }
}

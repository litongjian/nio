package game.nio.commons;

import game.nio.po.AuthUser;
import game.nio.global.GlobalAuthUserPool;
import game.nio.net.msg.AskMsg;

import java.util.UUID;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.commons
 * 概要：校验工具类
 */
public final class CheckSupport {

    /**
     * 校验请求交易是否合法
     * @param ask 请求消息
     * @return 合法返回true
     */
    public static boolean checkAskMsgAuth(AskMsg ask){
        AuthUser user = GlobalAuthUserPool.get(ask.getCid());
        return user!=null&&user.getUser()!=null&&user.getUser().equals(ask.getParam().getUser())&&user.getAuth()!=null&&user.getAuth().equals(ask.getParam().getAuth());
    }

    /**
     * 用户登录
     * @param user 用户名
     * @param password 密码
     * @return 登录成功返回合法对象，失败返回null
     */
    public static AuthUser checkUserLogin(String user,String password){
        AuthUser authUser = new AuthUser();
        authUser.setUser(user);
        authUser.setAuth(UUID.randomUUID().toString());
        authUser.setOnline(true);
        return authUser;
    }
}

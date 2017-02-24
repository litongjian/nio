package game.nio.server.handler;

import game.nio.annotation.UserHandler;
import game.nio.commons.CheckSupport;
import game.nio.commons.DataSupport;
import game.nio.expr.MsgWrongException;
import game.nio.expr.ProcessFiledException;
import game.nio.global.GlobalAuthUserPool;
import game.nio.global.GlobalChannelPool;
import game.nio.net.msg.*;
import game.nio.po.AuthUser;
import game.nio.po.ReqData;
import game.nio.po.ResData;
import game.nio.server.ServerAppContext;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.server.handler
 * 概要：服务端核心处理器
 */
@UserHandler(name = "server.core",index = 1)
public final class ServerCoreHandler extends ChannelHandlerAdapter{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel sc = (SocketChannel) ctx.channel();
        System.out.println("客户端已连接 Channel_ID ["+sc.id()+"]");
        super.channelActive(ctx);
    }

    /**
     * Channel失效时，从全局缓存中移除
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        GlobalChannelPool.remove((SocketChannel) ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof BaseMsg))throw new MsgWrongException();
        BaseMsg base= (BaseMsg) msg;
        GlobalChannelPool.add(base.getCid(), (SocketChannel) ctx.channel());
        SocketChannel curr = GlobalChannelPool.get(base.getCid());//当前客户Channel
        switch (base.getMsgType()){
            case PING:
                curr.writeAndFlush(new PingMsg());
                break;
            case LOGIN:
                LoginMsg loginMsg = (LoginMsg)msg;
                AuthUser authUser = CheckSupport.checkUserLogin(loginMsg.getUser(),loginMsg.getPassword());
                if (authUser!=null){
                    GlobalAuthUserPool.add(loginMsg.getCid(),authUser);
                    ReplyServerBody rsb = new ReplyServerBody();
                    ResData rd  = new ResData();
                    rd.setState(0);
                    rd.setUri("login");
                    rsb.setInfo(DataSupport.encodeByJson(rd));
                    ReplyMsg rm = new ReplyMsg();
                    rm.setBody(rsb);
                    curr.writeAndFlush(rm);
                }else {
                    curr.writeAndFlush(new LoginMsg());
                }
                break;
            case ASK:
                AskMsg ask = (AskMsg)base;
                if (CheckSupport.checkAskMsgAuth(ask)){
                    ReplyMsg rm = new ReplyMsg();
                    //令牌效验正确执行业务
                    ReqData reqData = DataSupport.decodeByJson(ask.getParam());
                    try {
                        rm.setBody(ServerAppContext.serviceRoute().process(reqData));
                    } catch (ProcessFiledException e) {
                        e.printStackTrace();
                        rm.setBody(new ReplyServerBody(e.getMessage()));
                    }
                    curr.writeAndFlush(rm);
                }else {
                    //令牌效验失败要求登录
                    curr.writeAndFlush(new LoginMsg());
                }
                break;
            case REPLY:
                ReplyMsg rm = (ReplyMsg)msg;
                ReplyClientBody rcb = (ReplyClientBody)rm.getBody();
                System.out.println("客户端["+rm.getCid()+"]回复消息："+rcb);
                break;
            default:break;
        }
        ReferenceCountUtil.release(msg);
    }
}

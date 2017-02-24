package game.nio.client.handler;

import game.nio.commons.DataSupport;
import game.nio.net.msg.*;
import game.nio.po.ReqData;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

/**
 * 所属项目：nio
 * 创建时间：2017/2/25.
 * 路径：game.nio.client.handler
 * 概要：客户端核心处理器
 */
public final class ClientCoreHandler extends ChannelHandlerAdapter{
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent ise = (IdleStateEvent) evt;
            switch (ise.state()){
                case WRITER_IDLE:
                    ctx.writeAndFlush(new PingMsg());
                    AskMsg am = new AskMsg();
                    AskParam ap = new AskParam();
                    ap.setUser("111");
                    ap.setData("teststr".getBytes());
                    am.setParam(ap);
                    ctx.writeAndFlush(am);
                    System.out.println("信息发送");
                    break;
                default:break;
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof BaseMsg){
            BaseMsg bm = (BaseMsg)msg;
            switch (bm.getMsgType()){
                case LOGIN:
                    // TODO: 2017/2/25 发送登录请求
                    LoginMsg loginMsg = new LoginMsg();
                    loginMsg.setPassword("111");
                    loginMsg.setUser("test001");
                    ctx.writeAndFlush(loginMsg);
                    break;
                case PING:
                    System.out.println("服务器反馈的心跳包");
                    break;
                case ASK:
                    AskMsg am = (AskMsg)bm;
                    ReqData data = DataSupport.decodeByJson(am.getParam());
                    System.out.println("服务器推送的消息 --> "+data.getData());
                    break;
                case REPLY:
                    ReplyMsg rm = (ReplyMsg)bm;
                    ReplyServerBody rsb=(ReplyServerBody)rm.getBody();
                    System.out.println("服务器反馈消息 -->"+rsb.getInfo());
                    break;
                default:break;
            }
            ReferenceCountUtil.release(msg);
        }
    }
}

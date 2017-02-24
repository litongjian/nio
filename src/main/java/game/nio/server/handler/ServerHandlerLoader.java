package game.nio.server.handler;

import game.nio.annotation.UserHandler;
import game.nio.server.ServerAppContext;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.util.List;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.server.handler
 * 概要：服务端Handler加载器
 */
public class ServerHandlerLoader extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast(ServerAppContext.decoder());
        p.addLast(ServerAppContext.encoder());
        List<ChannelHandlerAdapter> list = ServerAppContext.handlerList();
        list.forEach(hd->{
            UserHandler uh = hd.getClass().getAnnotation(UserHandler.class);
            p.addLast(uh.name(),hd);
        });
    }
}

package game.nio.server.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.server.handler
 * 概要：服务端Handler加载器
 */
public class ServerHandlerLoader extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new ObjectEncoder()).addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null))).addLast(new ServerCoreHandler());
    }
}

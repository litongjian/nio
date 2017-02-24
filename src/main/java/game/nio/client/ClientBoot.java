package game.nio.client;

import game.nio.client.handler.ClientCoreHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.client
 * 概要：客户端启动器
 */
public final class ClientBoot {
    private int port;
    private String host;
    private static final EventExecutorGroup eeg = new DefaultEventExecutorGroup(20);

    public ClientBoot(int port,String host){
        this.port = port;
        this.host = host;
    }

    public void connect() throws InterruptedException {
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        Bootstrap bt = new Bootstrap();
        bt.group(loopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .remoteAddress(host,port)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new IdleStateHandler(20,10,0),new ObjectEncoder(),new ObjectDecoder(ClassResolvers.cacheDisabled(null)),new ClientCoreHandler());
                    }
                });
        ChannelFuture f = bt.connect().sync();
        if (f.isSuccess()){
            System.out.println("服务器连接成功 -> HOST: ["+host+"] PORT: ["+port+"]");
        }
    }
}

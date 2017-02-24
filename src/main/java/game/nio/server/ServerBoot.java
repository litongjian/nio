package game.nio.server;

import game.nio.server.handler.ServerHandlerLoader;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.server
 * 概要：服务端启动器
 */
public class ServerBoot {
    private int port;
    public ServerBoot(int port){
        super();
        this.port = port;
    }
    public void bind() throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bt = new ServerBootstrap();
        bt.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,128)
                .option(ChannelOption.TCP_NODELAY,true)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childHandler(new ServerHandlerLoader());
        ChannelFuture cf = bt.bind(port).sync();
        if (cf.isSuccess()){
            System.out.println("服务器启动成功 当前绑定端口 ["+port+"]");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ServerBoot sb = new ServerBoot(9999);
        ServerAppContext sap = new ServerAppContext();
        sb.bind();
    }
}

package server.run;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import server.ChanelInitServer;


/**
 * Created by magic_000 on 29/09/2017.
 */
public class MainServer {
    public static void main(String... args) throws Exception {

        // tạo group cha
        NioEventLoopGroup bootstrapGroup = new NioEventLoopGroup();

        // tạo group con
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        // tạo server bootstrap
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bootstrapGroup, workerGroup);

        serverBootstrap.channel(NioServerSocketChannel.class);

        // create thread pool
        //EventExecutorGroup executorGroup = new DefaultEventExecutorGroup(100);


        serverBootstrap.childHandler(new ChanelInitServer());


        serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);

        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

        //try {
        ChannelFuture channelFuture = serverBootstrap.bind(8081).sync();
        System.out.println("Server started");
        channelFuture.channel().closeFuture().sync();

    }
}

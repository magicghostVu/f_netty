package server.run;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import log.LoggingService;
import server.ChanelInitServer;


/**
 * Created by magic_000 on 29/09/2017.
 */
public class MainServer {
    public static void main(String... args) throws Exception {

        // tạo group cha, group này sẽ chứa threadpool khởi tạo các connection mới
        NioEventLoopGroup acceptorGroup = new NioEventLoopGroup();

        // tạo group con, chịu trách nhiệm nhận xử lý gói tin từ các connection đã có
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        // tạo server bootstrap, một dạng helper để khởi động server
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(acceptorGroup, workerGroup);

        serverBootstrap.channel(NioServerSocketChannel.class);

        // create thread pool
        //EventExecutorGroup executorGroup = new DefaultEventExecutorGroup(100);


        //set nơi xử lý chung, khởi tạo kênh kết nối mới, remove kênh disconnect
        serverBootstrap.childHandler(new ChanelInitServer());

        serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);

        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

        ChannelFuture channelFuture = serverBootstrap.bind(8081).sync();

        channelFuture.addListener(f ->
            LoggingService.getInstance().getLogger().info("Server socket started")
        );

        channelFuture.channel().closeFuture().sync();*/


        channelFuture.addListener(f -> {
            LoggingService.getInstance().getLogger().info("Server socket started");
        });

        channelFuture.channel().closeFuture().sync();

    }
}

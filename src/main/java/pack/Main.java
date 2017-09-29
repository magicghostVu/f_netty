package pack;

import codec.PersonDecoder;
import codec.PersonEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import server.ServerHandler;


/**
 * Created by magic_000 on 29/09/2017.
 */
public class Main {
    public static void main(String[] args)  throws InterruptedException {

        // tạo group cha
        NioEventLoopGroup bootstrapGroup= new NioEventLoopGroup();

        // tạo group con
        NioEventLoopGroup workerGroup= new NioEventLoopGroup();

        // tạo server bootstrap
        ServerBootstrap serverBootstrap= new ServerBootstrap();

        serverBootstrap.group(bootstrapGroup, workerGroup);

        serverBootstrap.channel(NioServerSocketChannel.class);

        // create thread pool
        EventExecutorGroup executorGroup= new DefaultEventExecutorGroup(100);

        ChannelInitializer<SocketChannel> channelInitializer = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline cp= socketChannel.pipeline();
                cp.addLast("idleStateHandler",
                        new IdleStateHandler(0, 0, 5));
                cp.addLast(new PersonEncoder());
                cp.addLast(new PersonDecoder());
                cp.addLast(executorGroup, "serverHandler", new ServerHandler());
            }
        };

        serverBootstrap.childHandler(channelInitializer);
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

        serverBootstrap.bind(8082).sync();

        System.out.println("Server started");
    }
}

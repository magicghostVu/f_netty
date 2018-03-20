package client.run;

import client.ChannelInitClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Fresher on 20/03/2018.
 */
public class MainRunClient {
    public static void main(String... args) throws Exception {
        String host = "localhost";
        int portServer = 8081;
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrapClient = new Bootstrap();
        bootstrapClient.group(workerLoopGroup);
        bootstrapClient.channel(NioSocketChannel.class);
        bootstrapClient.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrapClient.handler(new ChannelInitClient());
        ChannelFuture channelFuture = bootstrapClient.connect(host, portServer).sync();
        channelFuture.channel().closeFuture().sync();
    }
}

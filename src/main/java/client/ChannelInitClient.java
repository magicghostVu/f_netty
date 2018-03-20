package client;

import codec.client.RequestEncoder;
import codec.client.ResponseDecoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by Fresher on 20/03/2018.
 */
public class ChannelInitClient extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new RequestEncoder());
        pipeline.addLast(new ResponseDecoder());
        pipeline.addLast(new ClientHandler());
    }
}

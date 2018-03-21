package server;

import codec.server.RequestDecoder;
import codec.server.ResponseEncoder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import log.LoggingService;

/**
 * Created by Fresher on 20/03/2018.
 */

public class ChanelInitServer extends ChannelInitializer<SocketChannel> {


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("A client connected to server");

        LoggingService.getInstance().getLogger().info("A client connected to server {}", ctx.channel().remoteAddress());

    }


    // mỗi khi có một kết nối đến Server, thì hàm này sẽ được gọi
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new RequestDecoder());
        pipeline.addLast(new ResponseEncoder());
        //pipeline.addLast(new ServerHandler());
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("A handler removed");
        //ctx.channel()
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exception occur");
        cause.printStackTrace();
    }
}

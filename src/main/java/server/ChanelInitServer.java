package server;

import codec.server.RequestDecoder;
import codec.server.ResponseEncoder;
import handler.ProcessingHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by Fresher on 20/03/2018.
 */
public class ChanelInitServer extends ChannelInitializer<SocketChannel> {


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("A client connected to server");
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new RequestDecoder());
        pipeline.addLast(new ResponseEncoder());
        pipeline.addLast(new ProcessingHandler());
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("A client disconnected");
        //ctx.channel()
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exception occur");
        cause.printStackTrace();
    }
}

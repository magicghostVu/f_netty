package server;

import entities.User;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import log.LoggingService;
import manage.UserManagementService;

/**
 * Created by Fresher on 20/03/2018.
 */

public class ChanelInitServer extends ChannelInitializer<SocketChannel> {


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("A client connected to server");


        Channel channel= ctx.channel();
        LoggingService.getInstance().getLogger().info("A client connected to server {}", channel.remoteAddress());
        //LoggingService.getInstance().getLogger().info("Class of channel is {}", channel.getClass());


    }


    // mỗi khi có một kết nối đến Server, thì hàm này sẽ được gọi
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        /*pipeline.addLast(new RequestDecoder());
        pipeline.addLast(new ResponseEncoder());*/
        //pipeline.addLast(new ServerHandler());
        pipeline.addLast(new ServerHandler());

    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("A handler removed");
        //ctx.channel()


        LoggingService.getInstance().getLogger().info("A handler removed {}", ctx.channel().remoteAddress());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exception occur");
        cause.printStackTrace();
    }
}

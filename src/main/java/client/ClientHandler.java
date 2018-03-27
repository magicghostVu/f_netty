package client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import log.LoggingService;

/**
 * Created by Fresher on 19/03/2018.
 */
//nhận gói tin đến bên phía client
public class ClientHandler extends ChannelInboundHandlerAdapter {


    //
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoggingService.getInstance().getLogger().info("Channel {} activated", ctx.channel().remoteAddress());

        ByteBuf byteBuf = ctx.alloc().buffer(8);

        byteBuf.writeLong(System.currentTimeMillis()/1000L);
        ctx.channel().writeAndFlush(byteBuf).addListener(l->
                LoggingService.getInstance().getLogger().info("bytebuf {} is sent ", byteBuf));

    }


    // bắt các exception ở đây
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}

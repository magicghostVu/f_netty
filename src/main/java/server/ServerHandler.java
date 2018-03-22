package server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GenericFutureListener;
import log.LoggingService;

/**
 * Created by Fresher on 20/03/2018.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {


    // khi tạo được một kết nối mới
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoggingService.getInstance().getLogger().info("a connection established at {}", ctx.channel().remoteAddress());


        ByteBuf byteBuf = Unpooled.buffer(4);
        int crTime = (int) (System.currentTimeMillis() / 1000);
        byteBuf.writeInt(crTime);
        ChannelFuture res = ctx.writeAndFlush(byteBuf);
        GenericFutureListener<ChannelFuture> callBack= f->{
            ctx.close().addListener(ChannelFutureListener.CLOSE);
            LoggingService.getInstance().getLogger().info("ref count of byte buf is {}", byteBuf.refCnt());
        };
        res.addListener(callBack);
    }


    // it work perfect :)), as expected
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf dataIn = Unpooled.copiedBuffer((ByteBuf) msg);
        ReferenceCountUtil.release(msg);
        byte[] arr = dataIn.array();
        dataIn.release();
        String res = new String(arr);
        LoggingService.getInstance().getLogger().info("String is {}", res);


        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LoggingService.getInstance().getLogger().error("err in server handler {} ", cause);
    }
}

package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import request.AbsRequestData;

/**
 * Created by Fresher on 20/03/2018.
 */
public class ProcessingHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        AbsRequestData data = (AbsRequestData) msg;
        System.out.println("Code is " + data.getCode());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        System.out.println("some exception occur ");
        cause.printStackTrace();

    }
}

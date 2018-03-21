package server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import log.LoggingService;
import request.AbsRequestData;

/**
 * Created by Fresher on 20/03/2018.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        AbsRequestData data = (AbsRequestData) msg;
        //System.out.println("Code is " + data.getCode());


        LoggingService.getInstance().getLogger().info("Code from client {}", data.getCode());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        LoggingService.getInstance().getLogger().error("err in server handler {} ", cause);

    }
}

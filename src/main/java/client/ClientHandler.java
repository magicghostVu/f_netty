package client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import response.BaseResponseMsg;

/**
 * Created by Fresher on 19/03/2018.
 */
//nhận gói tin đến bên phía client
public class ClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel of client inactivated");
    }


    //
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        BaseResponseMsg msg1 = (BaseResponseMsg) msg;


        int codeReceived = msg1.getCode();

        System.out.println("Code from server is " + codeReceived);


        // tại sao lại đóng kênh truyền ??
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel of client inactivated");
    }
}

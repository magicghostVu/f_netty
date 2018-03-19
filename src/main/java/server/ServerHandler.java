package server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by magic_000 on 29/09/2017.
 */

// class sẽ xử lý nhận tin từ client
public class ServerHandler extends ChannelInboundHandlerAdapter {

    ByteBuf tmpBuffer;

    public ServerHandler() {
        //this.tmpBuffer = tmpBuffer;
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);

        System.out.println("handler added ");

        // cấp phát 100 bytes
        tmpBuffer = ctx.alloc().buffer(100);

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        tmpBuffer.release();
        tmpBuffer = null;
        System.out.println("handler removed");

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        ByteBuf byteBuf = (ByteBuf) msg;

        // chuyển toàn bộ data từ byteBuf của gói tin đến vào một ByteBuf khác
        tmpBuffer.writeBytes(byteBuf);

        // giải phóng buffer gói tin đến
        byteBuf.release();
        int age = tmpBuffer.readInt();
        int sizeByteStr = tmpBuffer.readInt();
        byte[] dataStr = new byte[sizeByteStr];
        for (int i = 0; i < sizeByteStr; i++) {
            dataStr[i] = tmpBuffer.readByte();
        }
        String name= new String(dataStr);
        System.out.println("age is "+ age+" "+ "name is "+ name);

    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

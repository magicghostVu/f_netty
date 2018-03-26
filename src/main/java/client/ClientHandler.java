package client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import log.LoggingService;

import java.util.concurrent.TimeUnit;

/**
 * Created by Fresher on 19/03/2018.
 */
//nhận gói tin đến bên phía client
public class ClientHandler extends ChannelInboundHandlerAdapter {


    //
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        ByteBuf byteBuf = (ByteBuf) msg;
        int timeStampInt = byteBuf.readInt();

        LoggingService.getInstance().getLogger().info("time stamp from server is {}", timeStampInt);


        // tại sao lại đóng kênh truyền ??
        //ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoggingService.getInstance().getLogger().info("Connected to {}", ctx.channel().remoteAddress());


        Runnable r = () -> {
            ByteBuf byteBuf = ctx.alloc().buffer(4);
            int time = (int) (System.currentTimeMillis() / 1000);
            byteBuf.writeInt(time);
            ctx.writeAndFlush(byteBuf).addListener(l -> {
                LoggingService.getInstance().getLogger().info("sent time to server {}", time);
            });
        };

        // cứ mỗi giây lại gửi lên server một lần
        MyThreadPoolService.getInstance().getScheduledExecutorService().scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);


    }


    // bắt các exception ở đây
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}

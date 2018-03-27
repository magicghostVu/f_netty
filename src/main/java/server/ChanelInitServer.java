package server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import log.LoggingService;

/**
 * Created by Fresher on 20/03/2018.
 */

public class ChanelInitServer extends ChannelInitializer<SocketChannel> {


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("A client connected to server");
        Channel channel= ctx.channel();
        LoggingService.getInstance().getLogger().info("A client connected to server {}", channel.remoteAddress());

    }


    // mỗi khi có một kết nối đến Server, thì hàm này sẽ được gọi
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();


        // sử dụng thêm 2 byte đầu tiên để lưu cỡ của package,
        // và bỏ 2 byte đó đi trong pack (ByteBuf nhận được)
        pipeline.addLast(new LengthFieldBasedFrameDecoder(128,
                0,2,0 ,2));


        // các buffer xuống đây chỉ còn lại là data nguyên bản
        pipeline.addLast(new ServerHandler());



        //todo : add các lớp đi ra

    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("A handler removed");
        //ctx.channel()


        LoggingService.getInstance().getLogger().info("A handler removed {}", ctx.channel().remoteAddress());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //System.out.println("exception occur");
        cause.printStackTrace();
    }
}

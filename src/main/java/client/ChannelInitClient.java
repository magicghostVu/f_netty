package client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import log.LoggingService;

/**
 * Created by Fresher on 20/03/2018.
 */
public class ChannelInitClient extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        /*pipeline.addLast(new RequestEncoder());
        pipeline.addLast(new ResponseDecoder());*/

        // add lớp lắng nghe gói tin đến
        // trước lớp này nên có một lớp handler chống phân mảnh
        pipeline.addLast(new LengthFieldBasedFrameDecoder(128,
                0,2,0 ,2));
        pipeline.addLast(new ClientHandler());


        // todo: add các lớp đi ra, lớp pretend nên được đặt ở cuối để thêm size vào đầu tất cả các gói tin đi ra
        pipeline.addLast(new LengthFieldPrepender(2,false ));

        LoggingService.getInstance().getLogger().info("Channel at {} init done ", socketChannel.localAddress());


    }
}

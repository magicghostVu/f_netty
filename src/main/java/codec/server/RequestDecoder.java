package codec.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import log.LoggingService;

import java.util.List;

/**
 * Created by Fresher on 20/03/2018.
 */
public class RequestDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf inBuff, List<Object> listOut) throws Exception {
        ByteBuf newByteBuf = Unpooled.copiedBuffer(inBuff);
        int numByteCanRead = newByteBuf.readableBytes();
        LoggingService.getInstance().getLogger().info("readable bytes is {}", numByteCanRead);
        if (numByteCanRead > 0) {
            byte[] arr = newByteBuf.array();
            String res = new String(arr);
            LoggingService.getInstance().getLogger().info("String is {}", res);
        }
        channelHandlerContext.write(newByteBuf);
        channelHandlerContext.flush();
        inBuff.release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}

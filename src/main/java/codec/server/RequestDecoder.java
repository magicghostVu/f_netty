package codec.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;
import log.LoggingService;
import request.AbsRequestData;

import java.util.List;

/**
 * Created by Fresher on 20/03/2018.
 */
public class RequestDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf inBuff, List<Object> listOut) throws Exception {
        int codeRequest = inBuff.readInt();
        AbsRequestData requestData = new AbsRequestData(codeRequest);
        listOut.add(requestData);

        int numByteCanRead = inBuff.readableBytes();


        LoggingService.getInstance().getLogger().info("readable bytes is {}", numByteCanRead);


        ReferenceCountUtil.release(inBuff);

        String message= "Hello world";

        channelHandlerContext.writeAndFlush(message.getBytes());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}

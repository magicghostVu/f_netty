package codec.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import request.AbsRequestData;

/**
 * Created by Fresher on 20/03/2018.
 */
public class RequestEncoder extends MessageToByteEncoder<AbsRequestData> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, AbsRequestData absRequestData, ByteBuf outByteBuf) throws Exception {
        outByteBuf.writeBytes(absRequestData.createData());
    }
}

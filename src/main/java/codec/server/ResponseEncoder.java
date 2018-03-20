package codec.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import response.BaseResponseMsg;

/**
 * Created by Fresher on 20/03/2018.
 */
public class ResponseEncoder extends MessageToByteEncoder<BaseResponseMsg> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, BaseResponseMsg dataToEncode, ByteBuf out) throws Exception {
        out.writeBytes(dataToEncode.createData());
    }
}

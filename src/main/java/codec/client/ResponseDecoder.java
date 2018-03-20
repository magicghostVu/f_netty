package codec.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import response.BaseResponseMsg;

import java.util.List;

/**
 * Created by Fresher on 20/03/2018.
 */
public class ResponseDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> outList) throws Exception {
        int code = byteBuf.readInt();
        BaseResponseMsg msg = new BaseResponseMsg(code);
        outList.add(msg);
    }
}

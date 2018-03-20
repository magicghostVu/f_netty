package codec.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
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
    }
}

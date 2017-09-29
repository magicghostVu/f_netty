package codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import pojos.Person;
import utils.Utils;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by magic_000 on 29/09/2017.
 */
public class PersonDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf byteBuf, List<Object> list) throws Exception {

        int length = byteBuf.readableBytes();

        System.out.println("length is "+ length);

        if (length < Person.minSize()) {
            System.out.println("it is not a person");
            return;
        }

        byte[] rawBytes = new byte[length];

        // read all bytes
        byteBuf.readBytes(rawBytes, 0, length);

        Person p = Utils.fromByteArr(rawBytes);

        list.add(p);
    }
}

package codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import pojos.Person;
import utils.Utils;

import java.util.List;

/**
 * Created by magic_000 on 29/09/2017.
 */
public class PersonDecoder extends ByteToMessageDecoder{



    //tham số listOut, không hiểu sao netty sử dụng list nhưng đó là data được lấy ra sau khi decode
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf byteBufIn, List<Object> listOut) throws Exception {

        int length = byteBufIn.readableBytes();

        System.out.println("length is "+ length);

        if (length < Person.minSize()) {
            System.out.println("it is not a person");
            return;
        }

        byte[] rawBytes = new byte[length];

        // read all bytes
        byteBufIn.readBytes(rawBytes, 0, length);

        Person p = Utils.fromByteArr(rawBytes);

        listOut.add(p);
    }
}

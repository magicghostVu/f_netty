package codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import pojos.Person;

/**
 * Created by magic_000 on 29/09/2017.
 */
public class PersonEncoder extends MessageToByteEncoder<Person> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext,
                          Person person, ByteBuf byteBuf) throws Exception {
        byteBuf.writeBytes(person.createData());
    }
}

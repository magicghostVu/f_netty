package server;

import entities.User;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import log.LoggingService;
import manage.UserManagementService;
import message.MsgService;

/**
 * Created by Fresher on 20/03/2018.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    // hàm này được gọi khi có một client bị
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        LoggingService.getInstance().getLogger().info("channel is inactive at {}", ctx.channel().remoteAddress());
        UserManagementService.getInstance().removeUserByChannel(ctx.channel());
    }

    // khi tạo được một kết nối mới, thêm một user mới
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoggingService.getInstance().getLogger().info("a connection established at {}", ctx.channel().remoteAddress());
        try {
            User u = UserManagementService.getInstance().createNewUser(ctx.channel());
        } catch (Exception e) {
            LoggingService.getInstance().getLogger().error("err while create user with channel {} err is {}", ctx.channel(), e);
        }
    }


    // it work perfect, có client gửi data lên server
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;

        int readableBytes = byteBuf.readableBytes();


        LoggingService.getInstance().getLogger().info("readable byte is {}", readableBytes);



        User u = UserManagementService.getInstance().getUserByChannel(ctx.channel());

        if (u != null) {
            LoggingService.getInstance().getLogger().info("User {} send info to server", u.getUid());
        }
        ByteBuf data = (ByteBuf) msg;

        MsgService.sendMessToManyUser(UserManagementService.getInstance().getAllUser(), data);



        /*ByteBuf dataIn = Unpooled.copiedBuffer((ByteBuf) msg);
        ReferenceCountUtil.release(msg);
        byte[] arr = dataIn.array();
        dataIn.release();
        String res = new String(arr);
        LoggingService.getInstance().getLogger().info("String is {}", res);
        ctx.close();*/
    }

    // bất kỳ khi nào có exception được văng ra ở các hàm trên, hoặc có IOException, client bị dis đột ngột
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LoggingService.getInstance().getLogger().error("err in server handler {} ", cause);
        ctx.close().addListener(f -> LoggingService.getInstance().getLogger().info("ctx at {} close because exception {}",
                ctx.channel().remoteAddress(), cause.getMessage()));
    }


}

package message;

import entities.User;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.util.ReferenceCountUtil;
import log.LoggingService;
import manage.UserManagementService;

import java.util.Collection;
import java.util.function.Consumer;

public class MsgService {

    // gửi một bytebuff đến một user theo id
    public static void sendByUid(int uid, ByteBuf byteBuf, boolean releaseBuffImmediately) {
        try {
            User target = UserManagementService.getInstance().getUserByUid(uid);
            if (target != null) {
                if (releaseBuffImmediately) {
                    ChannelFuture f = target.getSocketChannel().writeAndFlush(byteBuf);
                    f.addListener(l -> LoggingService.getInstance().getLogger().info("message {} is sent to user {} at {}",
                            byteBuf, uid, target.getSocketChannel().remoteAddress()));
                } else {
                    ByteBuf tmp = Unpooled.copiedBuffer(byteBuf);
                    ChannelFuture f = target.getSocketChannel().writeAndFlush(tmp);
                    f.addListener(l -> LoggingService.getInstance().getLogger().info("message {} is sent to user {} at {}",
                            tmp, uid, target.getSocketChannel().remoteAddress()));
                }
            }
        }catch (Exception e){
            LoggingService.getInstance().getLogger().error("err while send message to user {} because {}", uid, e);
        }
    }

    // gửi một byteBuff đến nhiều
    public static void sendMessToManyUser(Collection<User> users, ByteBuf byteBuf) {
        Consumer<User> sendToUser = u -> {
            sendByUid(u.getUid(), byteBuf, false);
        };
        users.forEach(sendToUser);
        ReferenceCountUtil.release(byteBuf);

    }

}

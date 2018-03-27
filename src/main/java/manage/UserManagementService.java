package manage;

import entities.User;
import io.netty.channel.Channel;
import log.LoggingService;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserManagementService {




    private static UserManagementService ourInstance = new UserManagementService();

    public static UserManagementService getInstance() {
        return ourInstance;
    }

    private UserManagementService() {
        mapUidUsers= new ConcurrentHashMap<>();
        mapChannelUsers= new ConcurrentHashMap<>();
        autoId= new AtomicInteger();
    }


    private AtomicInteger autoId;


    private ConcurrentHashMap<Integer, User> mapUidUsers;

    private ConcurrentHashMap<Channel, User> mapChannelUsers;


    public User getUserByUid(Integer id){
        return mapUidUsers.get(id);
    }


    public User getUserByChannel(Channel channel){
        return mapChannelUsers.get(channel);
    }

    public User createNewUser(Channel channel){
        if(mapChannelUsers.containsKey(channel)){
            throw new IllegalArgumentException("This channel belong to existing user");
        }

        int uid= autoId.getAndIncrement();
        User u= new User(uid);
        u.setSocketChannel(channel);
        mapChannelUsers.put(channel, u);
        mapUidUsers.put(uid, u);

        LoggingService.getInstance().getLogger().info("User {} had been created with channel {}", u.getUid(), u.getSocketChannel().remoteAddress());

        return u;
    }

    public void removeUser(User u){
        mapUidUsers.remove(u.getUid());
        mapChannelUsers.remove(u.getSocketChannel());
        LoggingService.getInstance().getLogger().info("User {} at {} is removed", u.getUid(), u.getSocketChannel().remoteAddress());
    }

    public void removeUserByChannel(Channel channel){
        if(mapChannelUsers.containsKey(channel)){
            User target= mapChannelUsers.get(channel);
            removeUser(target);
        }else{
            LoggingService.getInstance().getLogger().info("channel {} is not exist or is is removed before", channel.remoteAddress());
        }
    }

    public Collection<User> getAllUser(){
        return mapUidUsers.values();
    }


}

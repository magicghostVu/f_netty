package entities;

import io.netty.channel.Channel;

public class User {
    private int uid;
    private Channel socketChannel;

    public User(int uid) {
        this.uid = uid;
    }


    public int getUid() {
        return uid;
    }

    /*public void setUid(int uid) {
        this.uid = uid;
    }*/

    public Channel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(Channel socketChannel) {
        this.socketChannel = socketChannel;
    }
}

package pojos;

import abs.to_byte_arr.CanCreateData;

import java.nio.ByteBuffer;

/**
 * Created by Fresher on 19/03/2018.
 */
public class BaseResponseMsg implements CanCreateData {
    private int code;

    public BaseResponseMsg() {
    }

    public BaseResponseMsg(int code) {
        this.code = code;
    }

    @Override
    public byte[] createData() {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.putInt(code);
        int pos = buffer.position();
        byte[] res = new byte[pos];
        buffer.flip();
        buffer.get(res, 0, pos);
        return res;
    }

    @Override
    public int calSize() {
        return Integer.SIZE / Byte.SIZE;
    }
}

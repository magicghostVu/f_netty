package request;

import abs.to_byte_arr.CanCreateData;

import java.nio.ByteBuffer;

/**
 * Created by Fresher on 20/03/2018.
 */
public class AbsRequestData implements CanCreateData {
    private int code;

    public AbsRequestData(int code) {
        this.code = code;
    }

    @Override
    public byte[] createData() {
        ByteBuffer buffer = makeBuffer();
        buffer.putInt(code);
        return packBuffer(buffer);
    }

    @Override
    public int calSize() {
        return 0;
    }

    protected ByteBuffer makeBuffer() {
        // hard code cấp phát 256 byte
        ByteBuffer buffer = ByteBuffer.allocate(256);
        return buffer;
    }

    public byte[] packBuffer(ByteBuffer buffer) {
        int curPos = buffer.position();
        byte[] res = new byte[curPos];
        buffer.flip();
        buffer.get(res, 0, curPos);
        return res;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

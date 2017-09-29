package utils;

import pojos.Person;

import java.nio.ByteBuffer;

/**
 * Created by magic_000 on 29/09/2017.
 */
public class Utils {

    public static byte[] longToByteArray(Long l) {
        int sizeLong = Long.SIZE;
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeLong);
        byteBuffer.putLong(l);
        return byteBuffer.array();
    }

    public static void putStrToByteBuff(String source, ByteBuffer des) {
        des.putInt(source.length());
        char[] charArr = source.toCharArray();
        for (char c : charArr) {
            des.putChar(c);
        }
    }

    public static Person fromByteArr(byte[] rawBytes) {
        if (rawBytes.length < Person.minSize()) {
            System.out.println("size is to small, can't parse "+ rawBytes.length);
            return null;
        }
        try {
            ByteBuffer byteBuffer = ByteBuffer.wrap(rawBytes);
            int age = byteBuffer.getInt();
            int sizeName = byteBuffer.getInt();
            char[] charArrName = new char[sizeName];
            for (int i = 0; i < sizeName; ++i) {
                charArrName[i] = byteBuffer.getChar();
            }
            return new Person(new String(charArrName), age);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}

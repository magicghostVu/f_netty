package pojos;

import abs.to_byte_arr.CanCreateData;
import utils.Utils;

import java.nio.ByteBuffer;

/**
 * Created by magic_000 on 29/09/2017.
 */
public class Person implements CanCreateData {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public byte[] createData() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(calSize());

        // put age
        byteBuffer.putInt(getAge());

        // put String
        Utils.putStrToByteBuff(getName(), byteBuffer);

        return byteBuffer.array();

    }

    @Override
    public int calSize() {

        // age = int, String
        int res = Integer.SIZE + Integer.SIZE + name.length() * Character.SIZE;
        return res;
    }

    public static int minSize(){
        return Integer.SIZE+ Integer.SIZE;
    }

}

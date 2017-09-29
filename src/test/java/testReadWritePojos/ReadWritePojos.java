package testReadWritePojos;

import junit.framework.TestCase;
import pojos.Person;

import java.nio.ByteBuffer;

/**
 * Created by magic_000 on 29/09/2017.
 */
public class ReadWritePojos  extends TestCase{

    public void testReadWritePojos(){

        ///assertEquals(1, 1);
        Person p= new Person("Hoàng Công Vương", 22);

        byte data[]= p.createData();

        ByteBuffer wrap= ByteBuffer.wrap(data);

        int age= wrap.getInt();

        System.out.println("age is "+ age);

        int sizeName= wrap.getInt();

        char[] nameChar= new char[sizeName];

        for (int i = 0; i < sizeName ; i++) {
            nameChar[i]= wrap.getChar();
        }

        String name= new String(nameChar);

        System.out.println("name is " + name);

    }


}

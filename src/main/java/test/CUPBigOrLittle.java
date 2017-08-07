package test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteOrder;

/**
 * 判断当前CPU是大端字节顺序还是小段字节顺序
 *
 * @author joels
 * @create 2017-07-20 17:34
 **/
public class CUPBigOrLittle {
    private static final Unsafe unsafe = getUnsafe();

    public static void main(String[] args) {
        long a = unsafe.allocateMemory(8);
        try {
            unsafe.putLong(a, 0x0102030405060708L);
            byte b = unsafe.getByte(a);
            switch (b) {
                case 0x01: System.out.println("大端字节顺序");     break;
                case 0x08: System.out.println("小端字节顺序");  break;
                default:
                    assert false;
                    System.out.println("error");
            }
        } finally {
            unsafe.freeMemory(a);
        }
    }

    public static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe)f.get(null);
        } catch (Exception e) {
       /* ... */
        }

        return null;
    }
}

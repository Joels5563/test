package test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 反射的工具类
 *
 * @author joels
 * @create 2017-07-11 9:20
 **/
public class ReflectionUtil {

    private ReflectionUtil() {
        throw new AssertionError();
    }

    /**
     * 通过反射取对象指定字段(属性)的值
     *
     * @param target    目标对象
     * @param fieldName 字段的名字
     *                  如果取不到对象指定字段的值则抛出异常
     * @return 字段的值
     */
    public static Object getValue(Object target, String fieldName) {
        Class<?> clazz = target.getClass();
        String[] fs = fieldName.split("\\.");

        try {
            for (int i = 0; i < fs.length - 1; i++) {
                //获取对象引用的fieldName的值
                Field f = clazz.getDeclaredField(fs[i]);
                f.setAccessible(true);
                target = f.get(target);
                clazz = target.getClass();
            }

            Field f = clazz.getDeclaredField(fs[fs.length - 1]);
            f.setAccessible(true);
            return f.get(target);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 通过反射给对象的指定字段赋值
     * @param target 目标对象
     * @param fieldName 字段的名称
     * @param value 值
     */
    public static void setValue(Object target, String fieldName, Object value) {
        Class<?> clazz = target.getClass();
        String[] fs = fieldName.split("\\.");
        try {
            for(int i = 0; i < fs.length - 1; i++) {
                //设置对象引用的fieldName的值
                Field f = clazz.getDeclaredField(fs[i]);
                f.setAccessible(true);
                Object val = f.get(target);
                if(val == null) {
                    //若对象引用没有在target对象创建时初始化
                    //则通过调用对象引用的构造器创建引用的对象
                    Constructor<?> c = f.getType().getDeclaredConstructor();
                    c.setAccessible(true);
                    val = c.newInstance();
                    f.set(target, val);
                }
                target = val;
                clazz = target.getClass();
            }

            Field f = clazz.getDeclaredField(fs[fs.length - 1]);
            f.setAccessible(true);
            f.set(target, value);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        TestClass testClass = new TestClass(1);
        setValue(testClass,"testYinYongClass.yin", 10);


        Object idValue = getValue(testClass, "id");
        System.out.println(idValue);
        Object yinValue = getValue(testClass, "testYinYongClass.yin");
        System.out.println(yinValue);

        TestClass.TestInnerClass innerClass = testClass.new TestInnerClass(3);
        Object idValue1 = getValue(innerClass, "inner");
        System.out.println(idValue1);
    }
}


class TestClass {
    private int id;
    private TestYinYongClass testYinYongClass;

    public TestClass(int id) {
        this.id = id;
    }

    public TestClass(int id, TestYinYongClass testYinYongClass) {
        this.id = id;
        this.testYinYongClass = testYinYongClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TestYinYongClass getTestYinYongClass() {
        return testYinYongClass;
    }

    public void setTestYinYongClass(TestYinYongClass testYinYongClass) {
        this.testYinYongClass = testYinYongClass;
    }

    class TestInnerClass {
        private int inner;

        public TestInnerClass(int inner) {
            this.inner = inner;
        }

        public int getInner() {
            return inner;
        }

        public void setInner(int inner) {
            this.inner = inner;
        }
    }
}

class TestYinYongClass {
    private int yin;

    public TestYinYongClass() {
    }

    public TestYinYongClass(int yin) {
        this.yin = yin;
    }

    public int getYin() {
        return yin;
    }

    public void setYin(int yin) {
        this.yin = yin;
    }
}
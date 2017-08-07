package test;

/**
 * 测试Runtime类
 *
 * @author joels
 * @create 2017-07-20 15:44
 **/
public class TestRuntime {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().maxMemory());
        System.out.println(Runtime.getRuntime().freeMemory());

    }
}

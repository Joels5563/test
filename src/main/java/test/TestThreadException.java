package test;

/**
 *  测试线程异常
 *
 * @author joels
 * @create 2017-06-29 11:02
 **/
public class TestThreadException extends Thread {

    @Override
    public void run() {
        System.out.println("测试线程中抛出异常");
        System.out.println(15/0);
    }

    public static void main(String[] args) {
        TestThreadException testThreadException = new TestThreadException();
        Thread.currentThread().setUncaughtExceptionHandler(
                (thread, e) -> {
                    System.out.println(thread.getName());
                    System.out.println(e.getMessage());
                    System.out.println(e.getClass());
                });
        testThreadException.run();
    }
}

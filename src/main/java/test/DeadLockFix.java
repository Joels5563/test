package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 死锁示例
 *
 * @author joels
 * @create 2017-06-29 15:23
 **/
public class DeadLockFix {

    /**
     *  Both method are now requesting lock in same order, first Integer and then String.
     *  You could have also done reverse e.g. first String and then Integer,
     *  both will solve the problem, as long as both method are requesting lock
     *  in consistent order.
     */
    public void method1() {
        synchronized (Integer.class) {
            System.out.println("Aquired lock on Integer.class object");
            synchronized (String.class) {
                System.out.println("Aquired lock on String.class object");
            }
        }
    }

    public void method2() {
        synchronized (Integer.class) {
            System.out.println("Aquired lock on Integer.class object");
            synchronized (String.class) {
                System.out.println("Aquired lock on String.class object");
            }
        }
    }


    public static void main(String[] args) {
        int executorCount = 0;
        ExecutorService service = new ScheduledThreadPoolExecutor(5);
        DeadLockFix deadLockDemo = new DeadLockFix();
        for(int i = 0; i < 150; i++){
            executorCount++;
            service.execute(new TestThreadDeadLock11(deadLockDemo));
            service.execute(new TestThreadDeadLock12(deadLockDemo));
        }
        service.shutdown();
        System.out.println("执行次数:" + executorCount);
    }


}

class TestThreadDeadLock11 implements Runnable {
    private DeadLockFix deadLockDemo;

    public TestThreadDeadLock11(DeadLockFix deadLockDemo) {
        this.deadLockDemo = deadLockDemo;
    }

    @Override
    public void run() {
        deadLockDemo.method1();
    }
}

class TestThreadDeadLock12 implements Runnable {
    private DeadLockFix deadLockDemo;

    public TestThreadDeadLock12(DeadLockFix deadLockDemo) {
        this.deadLockDemo = deadLockDemo;
    }

    @Override
    public void run() {
        deadLockDemo.method2();
    }
}

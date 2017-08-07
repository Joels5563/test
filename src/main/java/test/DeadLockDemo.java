package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 死锁示例
 *
 * @author joels
 * @create 2017-06-29 15:23
 **/
public class DeadLockDemo {

    /*
     * This method request two locks, first String and then Integer
     */
    public void method1() {
        synchronized (String.class) {
            System.out.println("Aquired lock on String.class object");
            synchronized (Integer.class) {
                System.out.println("Aquired lock on Integer.class object");
            }
        }
    }

    /*
     * This method also requests same two lock but in exactly
     * Opposite order i.e. first Integer and then String.
     * This creates potential deadlock, if one thread holds String lock
     * and other holds Integer lock and they wait for each other, forever.
     * */
    public void method2() {
        synchronized (Integer.class) {
            System.out.println("Aquired lock on Integer.class object");
            synchronized (String.class) {
                System.out.println("Aquired lock on String.class object");
            }
        }
    }


    public static void main(String[] args) {
        ExecutorService service = new ScheduledThreadPoolExecutor(5);
        DeadLockDemo deadLockDemo = new DeadLockDemo();
        for(int i = 0; i < 5; i++){
            service.execute(new TestThreadDeadLock1(deadLockDemo));
            service.execute(new TestThreadDeadLock2(deadLockDemo));
        }
    }
}

class TestThreadDeadLock1 implements Runnable {
    private DeadLockDemo deadLockDemo;

    public TestThreadDeadLock1(DeadLockDemo deadLockDemo) {
        this.deadLockDemo = deadLockDemo;
    }

    @Override
    public void run() {
        deadLockDemo.method1();
    }
}

class TestThreadDeadLock2 implements Runnable {
    private DeadLockDemo deadLockDemo;

    public TestThreadDeadLock2(DeadLockDemo deadLockDemo) {
        this.deadLockDemo = deadLockDemo;
    }

    @Override
    public void run() {
        deadLockDemo.method2();
    }
}

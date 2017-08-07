package test;

/**
 * 测试线程方法join
 * 有三个线程T1，T2，T3，确保它们按顺序执行
 *
 * @author joels
 * @create 2017-06-30 10:29
 **/
public class TestThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new JoinThread(1,Thread.currentThread());
        Thread t2 = new JoinThread(2,t1);
        Thread t3 = new JoinThread(3,t2);

        t3.start();
    }
}

class JoinThread extends Thread {
    private int num;
    private Thread currentThread;

    public JoinThread(int num,Thread currentThread) {
        this.num = num;
        this.currentThread = currentThread;
    }

    @Override
    public void run() {
        System.out.println("===========");
        System.out.println("开始执行线程" + num);
        if (currentThread instanceof JoinThread){
            currentThread.start();
            try {
                currentThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("线程" + num + "执行完毕");
        System.out.println("===========");
    }
}

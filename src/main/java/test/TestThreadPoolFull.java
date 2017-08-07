package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试线程池满的情况
 * 网上解释:    如果一个任务不能被调度执行那么ThreadPoolExecutor’s submit()方法将会抛出一个RejectedExecutionException异常。
 * 实际测试:    没有抛出异常,线程会进行等待,等到线程池有空位后再执行
 *
 * @author joels
 * @create 2017-06-30 11:14
 **/
public class TestThreadPoolFull {
    public static void main(String[] args) throws InterruptedException {
        //创建一个线程池,包含5个线程
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            TestFullThread testFullThread = new TestFullThread();

            service.submit(testFullThread);
        }
    }
}


class TestFullThread extends Thread {

    @Override
    public void run() {
        System.out.println("线程:" + this.getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
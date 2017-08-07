package test;

/**
 * 测试停止线程的运行
 *
 * @author joels
 * @create 2017-05-02 15:35
 **/
public class TestStopThread extends Thread {
    //退出线程标识
    private volatile boolean exitFlag = false;

    public void exitThread(boolean exitFlag) {
        this.exitFlag = exitFlag;
    }


    @Override
    public void run() {
        while (!exitFlag) {
            System.out.println("thread is running.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("thread is interrupted.");
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        TestStopThread thread = new TestStopThread();
        thread.start();

        try {
            Thread.sleep(4500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.exitThread(true);
    }
}

package test;

/**
 * 测试伪共享性能影响
 *
 * @author joels
 * @create 2017-07-20 11:36
 **/
public class FalseSharing implements Runnable {
    //线程数量
    public final static int NUM_THREADS = 4; // change
    //迭代次数
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    //
    private final int arrayIndex;

    //volatile的Long类型数组,每个线程一个
    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }


    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));

        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    //模拟伪共享
    public final static class VolatileLong {
        public volatile long value = 0L;
        //模拟缓存行,去除下面这行运行33秒,增加下面4运行时间4秒
        //当没有下面这行的时候,VolatileLong[]数组很大概率分配到同一块内存,模拟成了这个数组组成的缓存行
        //当多个线程调用这个对象数据的时候,出现了伪共享的情况
        public long p1, p2, p3, p4, p5, p6; // comment out
    }

    public static void main(final String[] args) throws Exception {
        final long start = System.currentTimeMillis();
        runTest();
        System.out.println("duration = " + (System.currentTimeMillis() - start) / 1000 + "秒");
    }

}

package test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 结论为:
 * 1.ConcurrentHashMap的性能比Hashtable和synchronizedMap要快一倍以上
 * 2.Hashtable和synchronizedMap相差不大
 * 3.当插入数据量相等的情况下,速度取决于是否存在相同数据,若数据存在较大重复,则速度快
 *
 * @author joels
 * @create 2017-05-03 9:33
 **/
public class TestMap {

    public static final int THREAD_POOL_SIZE = 5;

    private TestMap() {
        //do nothing
    }

    public static void main(String[] args) throws InterruptedException {
        perform(Collections.synchronizedMap(new HashMap<>()));
        perform(new Hashtable<>());
        perform(new ConcurrentHashMap<>());
    }

    public static void perform(final Map<String, Integer> map) throws InterruptedException {
        System.out.println("test started for:" + map.getClass());
        long averageTime = 0;
        for (int i = 0; i < 5; i++) {
            //执行五次,计算平均值

            long start = System.currentTimeMillis();

            ExecutorService executorService =
                    Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            for (int j = 0; j < THREAD_POOL_SIZE; j++) {
                Thread thread = new Thread(new TestMapThread(map));
                executorService.execute(thread);
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

            long end = System.currentTimeMillis();
            long total = end - start;
            averageTime += total;
            System.out.println("perform times:" + total);
        }
        System.out.println(map.getClass() + " average times:" + averageTime / 5);
        System.out.println();
    }
}

class TestMapThread implements Runnable {
    private Map<String, Integer> map;
    private static final int RUN_TIMES = 500000;

    TestMapThread(Map<String, Integer> map) {
        this.map = map;
    }

    @Override
    @SuppressWarnings("unused")
    public void run() {
        //运行RUN_TIMES次存在较大的覆盖
        long start = System.currentTimeMillis();
        for (int i = 0; i < RUN_TIMES; i++) {
            Integer randomNumber = (int) Math.ceil(Math.random() * 5500);

            // Retrieve value. We are not using it anywhere
            Integer value = map.get(String.valueOf(randomNumber));
            // Put value
            map.put(String.valueOf(randomNumber), randomNumber);
        }
        long end = System.currentTimeMillis();
        System.out.println("运行RUN_TIMES次存在较大的覆盖:" + (end - start));
        //运行RUN_TIMES次不存在较大的覆盖
        for (int i = 0; i < RUN_TIMES; i++) {
            Integer randomNumber = (int) Math.ceil(Math.random() * 550000);

            // Retrieve value. We are not using it anywhere
            Integer value = map.get(String.valueOf(randomNumber));
            // Put value
            map.put(String.valueOf(randomNumber), randomNumber);
        }

        long end1 = System.currentTimeMillis();
        System.out.println("运行RUN_TIMES次不存在较大的覆盖:" + (end1 - start));
    }
}

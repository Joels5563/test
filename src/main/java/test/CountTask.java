package test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 使用Fork/Join框架计算数值相加问题
 *
 * @author joels
 * @create 2017-06-30 15:22
 **/
public class CountTask extends RecursiveTask<Map<String, Long>> {
    //阈值
    private int threshold;
    private Long start;
    private Long end;

    public CountTask(Long start, Long end, int threshold) {
        this.start = start;
        this.end = end;
        this.threshold = threshold;
    }

    @Override
    protected Map<String, Long> compute() {
        Long sum = 0L;
        //计算次数
        Long computeCount = 0L;
        Map<String, Long> result = new HashMap<>();
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            computeCount++;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            result.put("sum", sum);
            result.put("computeCount", computeCount);
        } else {
            //如果任务大于阈值,就分裂成两个子任务计算
            Long middle = (start + end) / 2;
//            System.out.print("start:" + start + "____");
//            System.out.print("end:" + end + "____");
//            System.out.print("中间值:" + middle + "____");
//            System.out.println();
            CountTask leftTask = new CountTask(start, middle, threshold);
            CountTask rightTask = new CountTask(middle + 1, end, threshold);
            //执行子任务
            leftTask.fork();
            rightTask.fork();

            //等待子任务执行完,并得到结果
            Map<String, Long> leftSum = leftTask.join();
            Map<String, Long> rightSum = rightTask.join();
            //合并子任务
            sum = leftSum.get("sum") + rightSum.get("sum");
            computeCount = leftSum.get("computeCount") + rightSum.get("computeCount");
            result.put("sum", sum);
            result.put("computeCount", computeCount);
        }
        return result;
    }

    public static void main(String[] arg) {
        long startTime = System.currentTimeMillis();

        Long start = 1L;
        Long end = 10000000000L;
        int threshold = 1000;

        //10个ForkJoin池
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        //生成一个计算任务
        CountTask countTask = new CountTask(start, end, threshold);
        //执行一个任务
        Future<Map<String, Long>> resultFuture = forkJoinPool.submit(countTask);
        try {
            Map<String, Long> result = resultFuture.get();
            System.out.println("计算结果为:" + result.get("sum"));
            System.out.println("计算次数为:" + result.get("computeCount"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("验证结果1为" + (start + end) * end / 2);
        long endTime1 = System.currentTimeMillis();
        Long sum = 0L;
        for (Long i = start; i <= end; i++) {
            sum += i;
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("验证结果2为" + sum);
        System.out.println("分片计算耗时:" + (endTime - startTime));
        System.out.println("一次计算耗时:" + (endTime1 - endTime));
        System.out.println("循环计算耗时:" + (endTime2 - endTime1));
    }
}

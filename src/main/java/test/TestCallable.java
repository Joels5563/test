package test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 测试Callable
 * Java 5以前实现多线程有两种实现方法：一种是继承Thread类；另一种是实现Runnable接口
 * Java 5以后创建线程还有第三种方式：实现Callable接口，该接口中的call方法可以在线程执行结束时产生一个返回值
 *
 * @author joels
 * @create 2017-07-04 10:00
 **/
public class TestCallable {

    public static void main(String[] args) throws Exception {
        List<Future<Integer>> list = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
//            list.add(service.submit(new MyTask((int) (Math.random() * 100))));
            list.add(service.submit(new MyTask(10)));
        }

        int sum = 0;
        for (Future<Integer> future : list) {
            // while(!future.isDone()) ;
            sum += future.get();
        }
        service.shutdown();

        System.out.println(sum);
    }
}


class MyTask implements Callable<Integer> {
    private int upperBounds;

    public MyTask(int upperBounds) {
        this.upperBounds = upperBounds;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= upperBounds; i++) {
            sum += i;
        }
        return sum;
    }

}
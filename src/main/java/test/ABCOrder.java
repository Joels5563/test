package test;

/**
 * 连续输出10次ABC
 * 一个线程输出A
 * 一个线程输出B
 * 一个线程输出C
 *
 * @author joels
 * @create 2017-06-29 15:45
 **/
public class ABCOrder {

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        Thread aThread = new Thread(new ObjectThread("A",c,a));
        Thread bThread = new Thread(new ObjectThread("B",a,b));
        Thread cThread = new Thread(new ObjectThread("C",b,c));

        aThread.start();
        Thread.sleep(100);
        bThread.start();
        Thread.sleep(100);
        cThread.start();
    }

}


class ObjectThread implements Runnable {
    private String name;
    private Object prev;
    private Object current;

    public ObjectThread(String name, Object prev, Object current) {
        this.name = name;
        this.prev = prev;
        this.current = current;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            synchronized (prev) {
                synchronized (current) {
                    System.out.print(name);
                    current.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
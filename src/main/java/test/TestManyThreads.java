package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试多线程
 * <p>
 * 演示了100个线程同时向一个银行账户中存入1元钱
 * 在没有使用同步机制和使用同步机制情况下的执行情况
 *
 * @author joels
 * @create 2017-07-04 9:54
 **/
public class TestManyThreads {
    public static void main(String[] args) {
        Account account = new Account();
        ExecutorService service = Executors.newFixedThreadPool(100);

        for (int i = 1; i <= 100; i++) {
            service.execute(new AddMoneyThread(account, 1));
        }

        service.shutdown();

        while (!service.isTerminated()) {
        }

        System.out.println("账户余额: " + account.getBalance());
    }
}


/**
 * 银行账号
 */
class Account {
    private Lock accountLock = new ReentrantLock();
    private double balance;     // 账户余额

    /**
     * 存款
     *
     * @param money 存入金额
     */
    public void deposit(double money) {
        double newBalance = balance + money;
        try {
            Thread.sleep(10);   // 模拟此业务需要一段处理时间
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        balance = newBalance;
    }

    /**
     * 存款
     *
     * @param money 存入金额
     */
    public synchronized void depositSynchoronized(double money) {
        double newBalance = balance + money;
        try {
            Thread.sleep(10);   // 模拟此业务需要一段处理时间
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        balance = newBalance;
    }

    /**
     * 存款
     *
     * @param money 存入金额
     */
    public void depositLock(double money) {
        accountLock.lock();
        try {
            double newBalance = balance + money;
            try {
                Thread.sleep(10); // 模拟此业务需要一段处理时间
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            balance = newBalance;
        }
        finally {
            accountLock.unlock();
        }
    }

    /**
     * 获得账户余额
     */
    public double getBalance() {
        return balance;
    }
}

/**
 * 存钱线程
 */
class AddMoneyThread implements Runnable {
    private Account account;    // 存入账户
    private double money;       // 存入金额

    public AddMoneyThread(Account account, double money) {
        this.account = account;
        this.money = money;
    }

    @Override
    public void run() {
        synchronized (account) {
            account.deposit(money);
        }
        //account.deposit(money);
    }

}
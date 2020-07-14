package com.zcr.d_thread.productconsumer.waitnotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock结合Condition
 * 1、基本思路
 * 与ReentrantLock搭配的通行方式是Condition，如下：
 * private Lock lock = new ReentrantLock();
 * private Condition condition = lock.newCondition();
 * condition.await();//this.wait();
 * condition.signal();//this.notify();
 * condition.signalAll();//this.notifyAll();
 * Condition是被绑定到Lock上的，必须使用lock.newCondition()才能创建一个Condition。
 * 从上面的代码可以看出，Synchronized能实现的通信方式，Condition都可以实现，功能类似的代码写在同一行中。
 * 这样解题思路就和第一种方法基本一致，只是采用的方法不同。
 *
 */
public class PrintABCLockCondition {

    private Lock lock = new ReentrantLock();
    private Condition conditiona = lock.newCondition();
    private Condition conditionb = lock.newCondition();
    private Condition conditionc = lock.newCondition();
    private static int count = 0;


    public void printA() {
        lock.lock();
        for (int i = 0; i < 10; i++) {
            while (count % 3 != 0) {
                try {
                    conditiona.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("A");
            count++;
            conditionb.signal();
        }
        lock.unlock();
    }

    public void printB() {
        lock.lock();
        for (int i = 0; i < 10; i++) {
            while (count % 3 != 1) {
                try {
                    conditionb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("B");
            count++;
            conditionc.signal();
        }
        lock.unlock();
    }

    public void printC() {
        lock.lock();
        for (int i = 0; i < 10; i++) {
            while (count % 3 != 2) {
                try {
                    conditionc.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C");
            System.out.println(i);
            count++;
            conditiona.signal();
        }
        lock.unlock();

    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        PrintABCLockCondition printABCLockCondition = new PrintABCLockCondition();
        executorService.execute(() -> printABCLockCondition.printA());
        executorService.execute(() -> printABCLockCondition.printB());
        executorService.execute(() -> printABCLockCondition.printC());

    }



}

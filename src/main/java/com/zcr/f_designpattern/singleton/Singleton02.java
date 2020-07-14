package com.zcr.f_designpattern.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//不好的解法二：可用于多线程但效率不高
//为了保证在多线程环境下我们还是只能得到类型的一个实例，需要加上一个同步锁

/**
 * 我们假设有两个线程同时想创建一个实例。由于在一个时刻只有一个现成能得到同步锁，当第一个线程加上锁时，第二个线程只能等待
 * 保证了在多线程下也只能得到一个实例
 *
 * 但是枷锁非常耗时，在没有必要的时候我们应该尽量避免
 */
public class Singleton02 {
    public Singleton02() {
    }

    private static Singleton02 instance = null;
    private static Object syncObj = new Object();
    public static Singleton02 getInstance() {
        synchronized (syncObj) {
            if (instance == null) {
                instance = new Singleton02();
            }
            return instance;
        }
    }

    public static void main(String[] args) {
        Singleton02 singleton01 = Singleton02.getInstance();
        Singleton02 singleton011 = Singleton02.getInstance();
        System.out.println(singleton01);
        System.out.println(singleton011);
        System.out.println(singleton01 == singleton011);
    }
}


class Test{

    private static class MyThread implements Runnable {
        @Override
        public void run() {
            Singleton02 singleton01 = Singleton02.getInstance();
            System.out.println(singleton01);
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        //创建多个线程Thread，都是去执行的同一个目标类对象----这就叫做多线程打印同一个任务，其实没什么意义。
        // 每个线程都是把同样的任务执行一遍，并没有加快打印的速度、或者是以分而治之的方法分解问题
        //只是测试了一下多线程打印同一个任务时候的时候，对目标类型对象的争夺情况
        for (int i = 0; i < 10; i++) {
            new Thread(myThread).start();
        }

        //用线程池的方式
        ExecutorService service = Executors.newFixedThreadPool(10);
        /*for (int i = 0; i < 10; i++) {
            service.submit(myThread);
        }
        service.shutdown();*/

    }


}




package com.zcr.f_designpattern.singleton;
//可行的解法：同步锁前后两次判断实例是否已经存在

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *我们只是在实例还没有创建之前（instance为null）需要加锁操作，以保证只有一个线程创建出实例
 * 而当实例已经创建之后，我们已经不需要再做加锁操作了
 *
 * 但是这样的代码实现起来比较复杂，容易出错
 */
public class Singleton03 {
    public Singleton03() {
    }

    private static Singleton03 instance = null;
    private static Object syncObj = new Object();
    public static Singleton03 getInstance() {
        if (instance == null) {
            synchronized (syncObj) {
                if (instance == null) {
                    instance = new Singleton03();
                }
            }
        }
        return instance;

    }

    public static void main(String[] args) {
        Singleton03 singleton01 = Singleton03.getInstance();
        Singleton03 singleton011 = Singleton03.getInstance();
        System.out.println(singleton01);
        System.out.println(singleton011);
        System.out.println(singleton01 == singleton011);
    }
}


class Test03{

    private static class MyThread implements Runnable {
        @Override
        public void run() {
            Singleton03 singleton01 = Singleton03.getInstance();
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



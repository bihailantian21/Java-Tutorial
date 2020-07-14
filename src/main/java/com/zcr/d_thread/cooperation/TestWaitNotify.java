package com.zcr.d_thread.cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestWaitNotify {

    public synchronized void before() {
        System.out.println("before");
        notifyAll();
    }

    public synchronized void after() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
    }

    /**
     * before
     * after
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        TestWaitNotify example = new TestWaitNotify();
        executorService.execute(() -> example.after());
        executorService.execute(() -> example.before());
    }

}

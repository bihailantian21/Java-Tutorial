package com.zcr.d_thread.mutualsynchroized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {

    private Lock lock = new ReentrantLock();

    public void func() {
        lock.lock();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(i + "");
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9
     * @param args
     */
    public static void main(String[] args) {
        TestReentrantLock testReentrantLock = new TestReentrantLock();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> testReentrantLock.func());
        executorService.execute(() -> testReentrantLock.func());
    }
}

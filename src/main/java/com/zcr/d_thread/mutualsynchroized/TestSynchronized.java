package com.zcr.d_thread.mutualsynchroized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSynchronized {

    public void func() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(i + " ");
            }
        }
    }

    public void func2() {
        synchronized (TestSynchronized.class) {
            for (int i = 0; i < 5; i++) {
                System.out.println(i + " ");
            }
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        TestSynchronized e1 = new TestSynchronized();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> e1.func());
        executorService.execute(() -> e1.func());
        //0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9

        TestSynchronized e2 = new TestSynchronized();
        executorService.execute(() -> e1.func());
        executorService.execute(() -> e2.func());
        //0 0 1 1 2 2 3 3 4 4 5 5 6 6 7 7 8 8 9 9

        executorService.execute(() -> e1.func2());
        executorService.execute(() -> e2.func2());
        //0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9


    }

}

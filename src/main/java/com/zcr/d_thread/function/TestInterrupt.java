package com.zcr.d_thread.function;

public class TestInterrupt implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("Thread run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main run
     * java.lang.InterruptedException: sleep interrupted
     *     at java.lang.Thread.sleep(Native Method)
     *     at InterruptExample.lambda$main$0(InterruptExample.java:5)
     *     at InterruptExample$$Lambda$1/713338599.run(Unknown Source)
     *     at java.lang.Thread.run(Thread.java:745)
     * @param args
     */
    public static void main(String[] args) {
        TestInterrupt testInterrupt = new TestInterrupt();
        Thread thread = new Thread(testInterrupt);
        thread.start();
        thread.interrupt();
        System.out.println("Main run");
    }
}

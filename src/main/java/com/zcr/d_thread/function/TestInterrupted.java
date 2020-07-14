package com.zcr.d_thread.function;

public class TestInterrupted implements Runnable {
    @Override
    public void run() {
        while (!Thread.interrupted()) {

        }
        System.out.println("Thread end");
    }

    public static void main(String[] args) {
        TestInterrupted testInterrupt = new TestInterrupted();
        Thread thread = new Thread(testInterrupt);
        thread.start();
        thread.interrupt();
        System.out.println("Main run");
    }
}

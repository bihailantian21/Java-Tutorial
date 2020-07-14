package com.zcr.d_thread.function;

public class TestYield implements Runnable {
    @Override
    public void run() {
        Thread.yield();
    }
}

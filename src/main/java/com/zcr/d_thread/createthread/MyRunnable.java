package com.zcr.d_thread.createthread;

public class MyRunnable implements Runnable{

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        MyRunnable instance = new MyRunnable();
        Thread thread = new Thread(instance);
        thread.start();
    }
}

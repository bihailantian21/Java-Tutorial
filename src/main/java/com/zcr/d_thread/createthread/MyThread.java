package com.zcr.d_thread.createthread;

public class MyThread extends Thread{
    @Override
    public void run() {

    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

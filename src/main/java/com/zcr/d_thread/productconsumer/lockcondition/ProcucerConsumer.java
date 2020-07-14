package com.zcr.d_thread.productconsumer.lockcondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProcucerConsumer {

    public static class Clerk {
        private int product = 0;
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void get() {
            lock.lock();
            try {
                while (product >= 1) {
                    System.out.println("产品已满");
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ":" + ++product);
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public void sale() {
            lock.lock();
            try {
                while (product <= 0) {
                    System.out.println("产品缺货");
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ":" + --product);
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public static class Producer implements Runnable{

        private Clerk clerk;

        public Producer(Clerk clerk) {
            this.clerk = clerk;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clerk.get();
            }
        }
    }

    public static class Consumer implements Runnable{

        private Clerk clerk;

        public Consumer(Clerk clerk) {
            this.clerk = clerk;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clerk.sale();
            }
        }
    }

    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer productor = new Producer(clerk);
        Consumer consumer = new Consumer(clerk);
        new Thread(productor, "生产者A").start();
        new Thread(consumer, "消费者B").start();
        new Thread(productor, "生产者C").start();
        new Thread(consumer, "消费者D").start();

    }
}

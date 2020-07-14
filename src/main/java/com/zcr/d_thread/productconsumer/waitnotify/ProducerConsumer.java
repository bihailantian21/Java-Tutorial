package com.zcr.d_thread.productconsumer.waitnotify;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


/**
 * （1）object自带的synchronized 、wait、notify机制
 *
 * 1）使用wait()、notify()和notifyAll()时需要先对调用对象加锁。
 * 2）调用wait()方法后，线程状态由RUNNING变为WAITING，并将当前线程放置到对象的等待队列。
 * 3）notify()或notifyAll()方法调用后，等待线程依旧不会从wait()返回，需要调用notify()或notifAll()的线程释放锁之后，
 * 等待线程才有机会从wait()返回。
 * 4）notify()方法将等待队列中的一个等待线程从等待队列中移到同步队列中，
 * 而notifyAll()方法则是将等待队列中所有的线程全部移到同步队列，被移动的线程状态由WAITING变为BLOCKED。
 * 5）从wait()方法返回的前提是获得了调用对象的锁。从上述细节中可以看到，等待/通知机制依托于同步机制，
 * 其目的就是确保等待线程从wait()方法返回时能够感知到通知线程对变量做出的修改。
 *
 * 总结：
 * 1.WaitThread首先获取了对象的锁，然后调用对象的wait()方法，从而放弃了锁并进入了对象的等待队列WaitQueue中，进入等待状态。
 * 2.由于WaitThread释放了对象的锁，NotifyThread随后获取了对象的锁，并调用对象的notify()方法，将WaitThread从WaitQueue移到SynchronizedQueue中，此时WaitThread的状态变为阻塞状态。
 * 3．NotifyThread释放了锁之后，WaitThread再次获取到锁并从wait()方法返回继续执行。
 *
 *
 * 永远在synchronized的函数或对象里使用wait、notify和notifyAll，不然Java虚拟机会生成IllegalMonitorStateException。
 * 永远在while循环里而不是if语句下使用wait。这样，循环会在线程睡眠前后都检查wait的条件，并在条件实际上并未改变的情况下处理唤醒通知。
 * 永远在多线程间共享的对象上使用wait。
 * notify随机通知一个阻塞在对象上的线程；notifyAll通知阻塞在对象上所有的线程。
 *
 */
public class ProducerConsumer {

    public static class Producer implements Runnable {
        private Queue<Integer> queue;
        private int maxSize;
        public Producer(Queue<Integer> queue,int maxSize) {
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == maxSize) {
                        try {
                            System.out.println("queue id full");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Random random = new Random();
                    int i = random.nextInt();
                    System.out.println("product" + i);
                    queue.add(i);
                    queue.notifyAll();
                }
            }
        }
    }


    public static class Consumer implements Runnable {
        private Queue<Integer> queue;
        private int maxSize;
        public Consumer(Queue<Integer> queue,int maxSize) {
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            System.out.println("queue is empty");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int r = queue.remove();
                    System.out.println("consumer" + r);
                    queue.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        int maxSize = 10;
        Producer producer = new Producer(queue,maxSize);
        Consumer consumer = new Consumer(queue,maxSize);

        Thread pT = new Thread(producer);
        Thread pC = new Thread(consumer);
        pT.start();
        pC.start();

    }
}

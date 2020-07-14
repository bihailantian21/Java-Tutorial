package com.zcr.d_thread.productconsumer;


/**
 * Synchronized同步法
 * 1、基本思路
 * 使用同步块和wait、notify的方法控制三个线程的执行次序。具体方法如下：
 * 从大的方向上来讲，该问题为三线程间的同步唤醒操作，主要的目的就是ThreadA->ThreadB->ThreadC->ThreadA循环执行三个线程。
 * 为了控制线程执行的顺序，那么就必须要确定唤醒、等待的顺序，所以每一个线程必须同时持有两个对象锁，才能进行打印操作。
 *
 * 一个对象锁是prev，就是前一个线程所对应的对象锁，其主要作用是保证当前线程一定是在前一个线程操作完成后
 * （即前一个线程释放了其对应的对象锁）才开始执行。
 * 还有一个锁就是自身对象锁。主要的思想就是，为了控制执行的顺序，必须要先持有prev锁（也就前一个线程要释放其自身对象锁），
 * 然后当前线程再申请自己对象锁，两者兼备时打印。之后首先调用self.notify()唤醒下一个等待线程（注意notify不会立即释放对象锁，
 * 只有等到同步块代码执行完毕后才会释放），再调用prev.wait()立即释放prev对象锁，当前线程进入休眠，
 * 等待其他线程的notify操作再次唤醒。
 *
 *
 * 可以看到程序一共定义了a,b,c三个对象锁，分别对应A、B、C三个线程。A线程最先运行，A线程按顺序申请c,a对象锁，
 * 打印操作后按顺序释放a,c对象锁，并且通过notify操作唤醒线程B。
 * 线程B首先等待获取A锁，再申请B锁，后打印B，再释放B，A锁，唤醒C。
 * 线程C等待B锁，再申请C锁，后打印C，再释放C,B锁，唤醒A。
 * 看起来似乎没什么问题，但如果你仔细想一下，就会发现有问题，就是初始条件，三个线程必须按照A,B,C的顺序来启动，
 * 但是这种假设依赖于JVM中线程调度、执行的顺序。
 *

 *
 */
public class PrintABC {

    public static class ThreadPrinter implements Runnable {

        private String name;
        private Object prev;
        private Object self;

        public ThreadPrinter(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        /**
         * 原实现存在的问题：
         * 如果把上述代码放到eclipse上运行，可以发现程序虽然完成了交替打印ABC十次的任务，但是打印完毕后无法自动结束线程。这是为什么呢？原因就在于下面这段代码：
         * try {
         * prev.wait(); // 立即释放 prev锁，当前线程休眠，等待唤醒
         * /**
         * * JVM会在wait()对象锁的线程中随机选取一线程，赋予其对象锁，唤醒线程，继续执行。
         *
         *
         *
         * prev.wait(); 是释放prev锁并休眠线程，等待唤醒。在最后一次打印完毕后，因为count为0，
         * 无法进入while循环的同步代码块，自然就不会触发notifyAll操作。
         * 这样一来，执行完打印操作后，线程就一直处于休眠待唤醒状态，导致线程无法正常结束。
         *
         * 改进实现：
         * 我们找到了了问题的原因，解决起来就简单了。最直接的思路就是在最后一次打印操作时在不休眠线程的情况下释放对象锁，
         * 这可以通过notifyAll操作实现。于是改进的代码如下：
         *
         * */


        @Override
        public void run() {
            int count = 10;
            while (count > 0) {
                synchronized (prev) {
                    synchronized (self) {
                        System.out.println(name);
                        count--;
                        self.notifyAll();
                    }
                    try {
                        if (count == 0) {
                            prev.notifyAll();
                        } else {
                            prev.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        ThreadPrinter ta = new ThreadPrinter("A",c,a);
        ThreadPrinter tb = new ThreadPrinter("B",a,b);
        ThreadPrinter tc = new ThreadPrinter("C",b,c);

        new Thread(ta).start();
        Thread.sleep(100);
        new Thread(tb).start();
        Thread.sleep(100);
        new Thread(tc).start();
        Thread.sleep(100);




    }
}

package com.zcr.f_designpattern.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//不好的解法一：只适用于单线程
//在单线程的时候工作正常，但在多线程的情况下：如果两个线程同时判断instance是否为空的if语句
//并且instance的确没有创建的时候，那么两个线程都会创建一个实例，此时就不再满足单例模式的要求
class Singleton011 {
    //要求只能生成一个实例，因此必须把构造函数设为私有函数以禁止他人创建实例
    private Singleton011() {
    }
    //定义一个静态的实例
    private static Singleton011 instance = null;
    //在需要的时候创建该实例
    //只有在instance为null的时候才创建一个实例以避免重复创建
    public static Singleton011 getInstance() {
        if (instance == null) {
            instance = new Singleton011();
        }
        return instance;
    }

    public static void main(String[] args) {
        /*不能再使用原来的用new创建新对象的方式了
        Singleton01 singleton01 = new Singleton01();
        Singleton01 singleton011 = new Singleton01();
        System.out.println(singleton01 == singleton011);*/

        /*在单线程的环境下测试是单例的
        Singleton011 singleton01 = Singleton011.getInstance();
        Singleton011 singleton011 = Singleton011.getInstance();
        System.out.println(singleton01);
        System.out.println(singleton011);
        System.out.println(singleton01 == singleton011);*/

        /*说是Lambda表达式中必须都是final；然后改成final后，又说final不能被赋值
        Singleton01 singleton02;
        new Thread(() -> {
             singleton02 = Singleton01.getInstance();
        }).start();

        Singleton01 singleton022;
        new Thread(() -> {
             singleton022 = Singleton01.getInstance();
        }).start();

        System.out.println(singleton02);*/

        /*说是内部类变量必须为final
        Singleton01 singleton012;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                     singleton012 = Singleton01.getInstance();
                }
            }
        }).start();
        System.out.println(singleton012);*/

        /**
         *为什么java内部类访问局部变量必须声明为final？
         * 先抛出让我疑惑了很久的一个问题
         * 编程时，在线程中使用局部变量时候经常编译器会提示：局部变量必须声明为final
         * public class ThreadTest {
         * 	public void function(String a) {
         * 		new Thread(){
         *            @Override
         *            public void run() {
         * 				System.out.println(a);
         *            }
         *     }.start();
         * 	}
         * 	public static void main(String[] args) {
         * 		new ThreadTest().function("a");
         * 	}
         * }
         * 上图中由于方法function中的形参a没有声明为final，编译抛出异常：Cannot refer to the non-final local variable a defined in an enclosing scope
         * 其实原因就是一个规则：java内部类访问局部变量时局部变量必须声明为final。
         * 那为什么要这样呢？还有线程为什么和内部类一样？接下来我们慢慢揭秘。
         * public class Out {
         * 	public void test(final String a) {
         * 		class In{
         * 			public void function() {
         * 				System.out.println(a);
         * 			}
         * 		}
         * 		new In().function();
         * 	}
         * 	public static void main(String[] args) {
         * 		new Out().test("hi");
         * 	}
         * }
         * 编译这个类后发现产生了两个class文件
         * 也就是说内部类和外部类各一个class文件，这样就产生了一个问题，调用内部类方法的时候如何访问外部类方法中的局部变量呢？
         * 实际上编译后的内部类的构造方法的里面，传了对应的外部类的引用和所有局部变量的形参。
         * （由于外部类方法执行完后局部变量会消亡，所以内部类构造函数中的局部变量实际是一份“复制”。而为了访问外部类中的私有成员变量，外部类编译后也产生了访问类似与getXXX的方法。）
         * 这时产生了一个不一致的问题，如果局部变量不设为final，那内部类构造完毕后，外部类的局部变量又改变了那怎么办？
         * public class Out {
         * 	public void test(String a) {
         * 		class In{
         * 			public void function() {
         * 				System.out.println(a);
         * 			}
         * 		}
         * 		a="hello";
         * 		new In().function();
         * 	}
         * 	public static void main(String[] args) {
         * 		new Out().test("hi");
         * 	}
         * }
         * 如代码中所示，这样调用内部类方法时会造成外部类局部变量和内部类中对应的变量的不一致。（注意内部类编译成class文件与new无关，a="hello"放在new In()前后不影响不一致关系，new在jvm运行class文件时才起效）*
         * 理解完内部类必须访问final声明的局部变量原因，我们回到最开始的问题：为什么线程和内部类一样
         * 因为线程也是一个类，所以new Thread也相当于创建了一个内部类啦
         * 我们编译一下最开始的ThreadTest.java文件
         * 发现线程编译后也是产生了单独的class文件。
         * 至此，问题全部解决啦~~
         * 最后说明一下java1.8和之前版本对这个规则编译的区别。
         * 如果在1.8的环境下，会很神奇的发现我们最开始的ThreadTest.java文件编译和运行是完全没有问题的，也就是说内部类使用的局部变量是可以不声明为final？！
         * 且慢，如果我们给局部变量再赋下值会发现编译又会出现同样的错误
         * public class ThreadTest {
         * 	public void function(String a) {
         * 		a="b";
         * 		new Thread(){
         *                        @Override
         *            public void run() {
         * 				System.out.println(a);
         *            }
         *        }.start();
         *    }
         *
         * 	public static void main(String[] args) {
         * 		new ThreadTest().function("a");
         *
         *    }
         * }
         * 在a="b"这一行报错：Local variable a defined in an enclosing scope must be final or effectively final
         * 也就是说规则没有改变，只是java1.8的编译变得更加智能了而已，在局部变量没有重新赋值的情况下，它默认局部变量为final型，认为你只是忘记加final声明了而已。如果你重新给局部变量改变了值或引用，那就无法默认为final了，所以报错。
         *
         */

       /* new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Singleton011.getInstance());
            }
        }).start();*/



       /* for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Singleton011.getInstance());
                System.out.println(Thread.currentThread().getName());
            }).start();
        }*/

        /*这种情况是指这么一个Tread线程去执行一个任务，这个任务是循环打印，创建单例类型
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Singleton011.getInstance());
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }).start();*/

        /*for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Singleton011.getInstance());
                    System.out.println(Thread.currentThread().getName());
                }
            }).start();
        }*/
        /**不知道为什么这里并没有发现多线程争夺的情况
         * com.zcr.offer.designpattern.singleton.Singleton011@2a9726dc
         * Thread-0
         * com.zcr.offer.designpattern.singleton.Singleton011@2a9726dc
         * Thread-1
         * com.zcr.offer.designpattern.singleton.Singleton011@2a9726dc
         * Thread-2
         * com.zcr.offer.designpattern.singleton.Singleton011@2a9726dc
         * Thread-3
         * com.zcr.offer.designpattern.singleton.Singleton011@2a9726dc
         * Thread-4
         * com.zcr.offer.designpattern.singleton.Singleton011@2a9726dc
         * Thread-5
         * com.zcr.offer.designpattern.singleton.Singleton011@2a9726dc
         * Thread-6
         * com.zcr.offer.designpattern.singleton.Singleton011@2a9726dc
         * Thread-7
         * com.zcr.offer.designpattern.singleton.Singleton011@2a9726dc
         * Thread-8
         * com.zcr.offer.designpattern.singleton.Singleton011@2a9726dc
         * Thread-9
         */

        //用线程池的方式
        /*ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.submit(() -> {
                System.out.println(Singleton011.getInstance());
                System.out.println(Thread.currentThread().getName());
            });
        }
        service.shutdown();*/



    }
}

public class Singleton01{

    private static class MyThread implements Runnable {
        @Override
        public void run() {
            Singleton011 singleton01 = Singleton011.getInstance();
            System.out.println(singleton01);
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        //创建多个线程Thread，都是去执行的同一个目标类对象----这就叫做多线程打印同一个任务，其实没什么意义。
        // 每个线程都是把同样的任务执行一遍，并没有加快打印的速度、或者是以分而治之的方法分解问题
        //只是测试了一下多线程打印同一个任务时候的时候，对目标类型对象的争夺情况
        /*for (int i = 0; i < 10; i++) {
            new Thread(myThread).start();
        }*/

        //用线程池的方式
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.submit(myThread);
        }
        service.shutdown();

    }
    /**打印结果说明了多线程环境下不行
     *  com.zcr.offer.designpattern.singleton.Singleton011@192d7af8
     * com.zcr.offer.designpattern.singleton.Singleton011@6695b54d
     * com.zcr.offer.designpattern.singleton.Singleton011@6695b54d
     * com.zcr.offer.designpattern.singleton.Singleton011@6695b54d
     * com.zcr.offer.designpattern.singleton.Singleton011@6695b54d
     * com.zcr.offer.designpattern.singleton.Singleton011@6695b54d
     * com.zcr.offer.designpattern.singleton.Singleton011@6695b54d
     * com.zcr.offer.designpattern.singleton.Singleton011@6695b54d
     * com.zcr.offer.designpattern.singleton.Singleton011@6695b54d
     * com.zcr.offer.designpattern.singleton.Singleton011@6695b54d
     */

    /**每次创建完类型并打印后，再打印下当前线程的名字，更清楚的发现了多线程打印单例模式时的错误之处
     * 前两个线程同时判断到instanc不为null，于是就都去创建了这个单例类型
     * com.zcr.offer.designpattern.singleton.Singleton011@3ffc8195
     * com.zcr.offer.designpattern.singleton.Singleton011@5d03da1e
     * Thread-2
     * com.zcr.offer.designpattern.singleton.Singleton011@5d03da1e
     * Thread-1
     * Thread-0
     * com.zcr.offer.designpattern.singleton.Singleton011@5d03da1e
     * Thread-3
     * com.zcr.offer.designpattern.singleton.Singleton011@5d03da1e
     * Thread-4
     * com.zcr.offer.designpattern.singleton.Singleton011@5d03da1e
     * Thread-5
     * com.zcr.offer.designpattern.singleton.Singleton011@5d03da1e
     * Thread-6
     * com.zcr.offer.designpattern.singleton.Singleton011@5d03da1e
     * Thread-7
     * com.zcr.offer.designpattern.singleton.Singleton011@5d03da1e
     * Thread-8
     * com.zcr.offer.designpattern.singleton.Singleton011@5d03da1e
     * Thread-9
     */

    /**用线程池的方式
     * com.zcr.offer.designpattern.singleton.Singleton011@5c562746
     * com.zcr.offer.designpattern.singleton.Singleton011@5c562746
     * pool-1-thread-1
     * com.zcr.offer.designpattern.singleton.Singleton011@5c4376b4
     * pool-1-thread-2
     * com.zcr.offer.designpattern.singleton.Singleton011@5c562746
     * pool-1-thread-3
     * pool-1-thread-4
     * com.zcr.offer.designpattern.singleton.Singleton011@5c562746
     * pool-1-thread-5
     * com.zcr.offer.designpattern.singleton.Singleton011@5c562746
     * pool-1-thread-6
     * com.zcr.offer.designpattern.singleton.Singleton011@5c562746
     * pool-1-thread-7
     * com.zcr.offer.designpattern.singleton.Singleton011@5c562746
     * pool-1-thread-8
     * com.zcr.offer.designpattern.singleton.Singleton011@5c562746
     * pool-1-thread-9
     * com.zcr.offer.designpattern.singleton.Singleton011@5c562746
     * pool-1-thread-10
     */

}



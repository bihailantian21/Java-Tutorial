package com.zcr.f_designpattern.singleton;

/**
 * 设计模式
 * 创建型模式：帮助我们创建对象的。
 * *单例模式：只涉及到一个类的对象创建。
 * 保证一个类只有一个实例，并且提供一个访问该实例的全局访问点。
 * 例子：java.lang.RuntimeRuntime类封装了Java运行时的环境。每一个java程序实际上都是启动了一个JVM进程，那么每个JVM进程都是对应这一个Runtime实例，此实例是由JVM为其实例化的。每个 Java 应用程序都有一个 Runtime 类实例，使应用程序能够与其运行的环境相连接。由于Java是单进程的，所以，在一个JVM中，Runtime的实例应该只有一个。所以应该使用单例来实现。
 * public class Runtime {
 *     private static Runtime currentRuntime = new Runtime();
 *
 *     public static Runtime getRuntime() {
 *         return currentRuntime;
 *     }
 *
 *     private Runtime() {}
 * }
 * 以上代码为JDK中Runtime类的部分实现，可以看到，这其实是饿汉式单例模式。在该类第一次被classloader加载的时候，这个实例就被创建出来了。
 * 一般不能实例化一个Runtime对象，应用程序也不能创建自己的 Runtime 类实例，但可以通过getRuntime方法获取当前Runtime运行时对象的引用。
 *
 *
 *
 *
 * **饿汉式（天生线程安全，调用效率高。但是，不能延时加载。）
 * 私有化静态属性（类加载时就创建对象）、私有化构造器、公共的方法（私有构造函数保证了不能通过构造函数来创建对象实例，
 * 只能通过公有静态函数返回唯一的私有静态变量。）
 * private static Singleton uniqueInstance = new Singleton();
 *
 * 5>虚拟机会保证一个类的clinit()方法在多线程环境中被正确的加锁、同步，如果多个线程同时去初始化一个类，
 * 那么只会有一个线程去执行这个类的clinit()方法，其他线程都需要阻塞等待，直到活动线程执行clinit()方法完毕。
 * 如果在一个类的clinit()方法中有耗时很长的操作，就可能造成多个进程阻塞，在实际应用中这种阻塞往往是很隐蔽的。
 * 特别需要注意的是，在这种情形下，其他线程虽然会被阻塞，但如果执行<clinit>()方法的那条线程退出后，
 * 其他线程在唤醒之后不会再次进入/执行<clinit>()方法，因为 在同一个类加载器下，一个类型只会被初始化一次。
 */
public class SingletonHungry {

    private static SingletonHungry singletonHungry = new SingletonHungry();

    private  SingletonHungry() {
    }

    public static SingletonHungry get() {
        return singletonHungry;
    }
}

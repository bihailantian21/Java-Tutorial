package com.zcr.f_designpattern.singleton;


/**
 * **静态内部类式(线程安全，调用效率高。 但是，可以延时加载)  ***********     》 懒汉式
 * 静态内部类(第一次加载静态内部类时并不会去创建对象，而是调用方法后才会创建)中定义静态属性，
 * 私有化static final静态属性、私有化构造方法、公有化方法
 *
 * public class Singleton {
 *     private Singleton() {
 *     }
 *     private static class SingletonHolder {
 *         private static final Singleton INSTANCE = new Singleton();
 *     }
 *     public static Singleton getUniqueInstance() {
 *         return SingletonHolder.INSTANCE;
 *     }
 * }
 * 当 Singleton 类加载时，静态内部类 SingletonHolder 没有被加载进内存。只有当调用 getUniqueInstance() 方法
 * 从而触发 SingletonHolder.INSTANCE 时 SingletonHolder 才会被加载，此时初始化 INSTANCE 实例，
 * 并且 JVM 能确保 INSTANCE 只被实例化一次。
 *
 * 内部类方式线程安全懒汉式单例的内在原理在于：虚拟机会保证一个类的类构造器<clinit>()在多线程环境中被正确的加锁、同步，
 * 如果多个线程同时去初始化一个类，那么只会有一个线程去执行这个类的类构造器<clinit>()，其他线程都需要阻塞等待，
 * 直到活动线程执行<clinit>()方法完毕。特别需要注意的是，在这种情形下，其他线程虽然会被阻塞，
 * 但如果执行<clinit>()方法的那条线程退出后，其他线程在唤醒之后不会再次进入/执行<clinit>()方法，
 * 因为 在同一个类加载器下，一个类型只会被初始化一次。
 */
public class SingletonInnerClass {

    private SingletonInnerClass() {
    }
    private static class SingletonHolder {
        private static final SingletonInnerClass INSTANCE = new SingletonInnerClass();
    }
    public static SingletonInnerClass getUniqueInstance() {
        return SingletonHolder.INSTANCE;
    }
}

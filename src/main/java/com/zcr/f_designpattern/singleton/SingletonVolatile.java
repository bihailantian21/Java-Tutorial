package com.zcr.f_designpattern.singleton;

/**
 * **双重检测锁式（由于JVM底层内部模型原因，偶尔会出问题。不建议使用） 对应于懒汉式！！！
 * 私有化volatile静态属性（只是声明而不去初始化对象）、私有化构造器、公有化方法（只在第一次调用时初始化对象）只不过把sychronized的位置从方法上移到了方法内部
 * public class Singleton {
 *     private volatile static Singleton uniqueInstance;
 *     private Singleton() {
 *     }
 *     public static Singleton getUniqueInstance() {
 *         if (uniqueInstance == null) {
 *             synchronized (Singleton.class) {
 *                 if (uniqueInstance == null) {
 *                     uniqueInstance = new Singleton();
 *                 }
 *             }
 *         }
 *         return uniqueInstance;
 *     }
 * }
 * 第一次加锁：加锁操作只需要对实例化那部分的代码进行，只有当 uniqueInstance 没有被实例化时，才需要进行加锁。
 * 第二次加锁：考虑下面的实现，也就是只使用了一个 if 语句。在 uniqueInstance == null 的情况下，如果两个线程都执行了 if 语句，
 * 那么两个线程都会进入 if 语句块内。虽然在 if 语句块内有加锁操作，但是两个线程都会执行 uniqueInstance = new Singleton(); 这条语句，
 * 只是先后的问题，那么就会进行两次实例化。因此必须使用双重校验锁，也就是需要使用两个 if 语句。
 * if (uniqueInstance == null) {
 *     synchronized (Singleton.class) {
 *         uniqueInstance = new Singleton();
 *     }
 * }
 *
 *
 * Volatile：uniqueInstance 采用 volatile 关键字修饰也是很有必要的， uniqueInstance = new Singleton(); 这段代码其实是分为三步执行：
 * 1.	为 uniqueInstance 分配内存空间
 * 2.	初始化 uniqueInstance
 * 3.	将 uniqueInstance 指向分配的内存地址
 * 但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1>3>2。指令重排在单线程环境下不会出现问题，
 * 但是在多线程环境下会导致一个线程获得还没有初始化的实例。例如，线程 T1 执行了 1 和 3，
 * 此时 T2 调用 getUniqueInstance() 后发现 uniqueInstance 不为空，因此返回 uniqueInstance，但此时 uniqueInstance 还未被初始化。
 *
 * 使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
 * synchronized既然能保证有序性，为什么还需要用volatile防止指令重拍？
 * 锁只能保证: 访问临界区时的原子性, 可见性, 有序性. 然而在tag 1那一行, 并不是在临界区中, 不受锁的保护, 因此即使在双重检查时加锁并初始化,
 * 也不能保证其他线程能够看到这个初始化后的单例. 因此, 需要在单例前面加volatile, 保证实例的可见性.
 * 看完volatile之后，你就可以对于指令重排序有进一步的了解后，尤其是volatile可以保证即使Java虚拟机对代码执行了指令重排序，也会保证它的正确性。
 * 然后我们来指出下为什么用了synchronized还要用volatile,以及可能出现的指令重排序影响双重检查加锁（double-checked locking)的正确性。
 * 可以看以下例子。
 * public class Singleton {
 *     private volatile static Singleton uniqueInstance;
 *     private Singleton(){}
 *     public static Singleton getInstance(){
 *         if(uniqueInstance == null){
 *         // B线程检测到uniqueInstance不为空
 *             synchronized(Singleton.class){
 *                 if(uniqueInstance == null){
 *                     uniqueInstance = new Singleton();
 *                     // A线程被指令重排了，刚好先赋值了；但还没执行完构造函数。
 *                 }
 *             }
 *         }
 *         return uniqueInstance;// 后面B线程执行时将引发：对象尚未初始化错误。
 *     }
 * }
 * 具体来说就是synchronized虽然保证了原子性，但却没有保证指令重排序的正确性，会出现A线程执行初始化，但可能因为构造函数里面的操作太多了，
 * 所以A线程的uniqueInstance实例还没有造出来，但已经被赋值了。而B线程这时过来了，错以为uniqueInstance已经被实例化出来，
 * 一用才发现uniqueInstance尚未被初始化。要知道我们的线程虽然可以保证原子性，但程序可能是在多核CPU上执行。
 *
 * 单例模式中用volatile和synchronized来满足双重检查锁机制
 * 背景：我们在实现单例模式的时候往往会忽略掉多线程的情况，就是写的代码在单线程的情况下是没问题的，
 * 但是一碰到多个线程的时候，由于代码没写好，就会引发很多问题，而且这些问题都是很隐蔽和很难排查的。
 * 例子1：没有volatile修饰的uniqueInstance
 * public class Singleton {
 *     private static Singleton uniqueInstance;
 *     private Singleton(){
 *     }
 *     public static Singleton getInstance(){
 *         if(uniqueInstance == null){ //#1
 *             synchronized(Singleton.class){ //#2
 *                 if(uniqueInstance == null){ //#3
 *                     uniqueInstance = new Singleton(); //#4
 *                     System.out.println(Thread.currentThread().getName() + ": uniqueInstance is initalized..."); //#5.1
 *                 } else {
 *                     System.out.println(Thread.currentThread().getName() + ": uniqueInstance is not null now..."); //#5.2
 *                 }
 *             }
 *         }
 *         return uniqueInstance;
 *     }
 * }
 * public class TestSingleton {
 *  2     public static void main(final String[] args) throws InterruptedException {
 *  3         for (int i = 1; i <= 100000; i++) {
 *  4             final Thread t1 = new Thread(new ThreadSingleton());
 *  5             t1.setName("thread" + i);
 *  6             t1.start();
 *  7         }
 *  8     }
 *  9
 * 10     public static class ThreadSingleton implements Runnable {
 * 11         @Override
 * 12         public void run() {
 * 13             Singleton.getInstance();
 * 14         }
 * 15     }
 * 16 }
 * 这里面的结果有可能会是：（没有真正重现过，太难模拟了）
 * 1 thread2: uniqueInstance is initalized...
 * 2 thread3: uniqueInstance is initalized...
 * Singleton被实例化两次了，和我们的单例模式设计期望值不一致：类永远只被实例化一次.
 * 原因分析：
 * 1. thread2进入#1， 这时子线程的uniqueInstance都是为空的，thread2让出CPU资源给thread3
 * 2. thread3进入#1， 这时子线程的uniqueInstance都是为空的, thread3让出CPO资源给thread2
 * 3. thread2会依次执行#2，#3，#4， #5.1，最终在thread2里面实例化了uniqueInstance。thread2执行完毕让出CPO资源给thread3
 * 4. thread3接着#1跑下去，跑到#3的时候，由于#1里面拿到的uniqueInstance还是空（并没有及时从thread2里面拿到最新的），所以thread3仍然会执行#4，#5.1
 * 5. 最后在thread2和thread3都实例化了uniqueInstance
 * 例子2：用volatile修饰的uniqueInstance
 * 这里就不贴重复的代码了，因为只是加多一个volatile来修饰成员变量：uniqueInstance，
 * 但是结果却是正确的了, 其中一个可能结果：
 * thread2: uniqueInstance is initalized
 * thread3: uniqueInstance is not null now...
 * 原因分析：
 * volatile（java5）：可以保证多线程下的可见性;
 * 读volatile：每当子线程某一语句要用到volatile变量时，都会从主线程重新拷贝一份，这样就保证子线程的会跟主线程的一致。
 * 写volatile: 每当子线程某一语句要写volatile变量时，都会在读完后同步到主线程去，这样就保证主线程的变量及时更新。
 * 1. thread2进入#1， 这时子线程的uniqueInstance都是为空的（java内存模型会从主线程拷贝一份uniqueInstance=null到子线程thread2），thread2让出CPU资源给thread3
 * 2. thread3进入#1， 这时子线程的uniqueInstance都是为空的（java内存模型会从主线程拷贝一份uniqueInstance=null到子线程thread2）, thread3让出CPO资源给thread2
 * 3. thread2会依次执行#2，#3，#4， #5.1，最终在thread2里面实例化了uniqueInstance(由于是volatile修饰的变量，会马上同步到主线程的变量去)。thread2执行完毕让出CPO资源给thread3
 * 4. thread3接着#1跑下去，跑到#3的时候，会又一次从主线程拷贝一份uniqueInstance！=null回来，所以thread3就直接跑到了#5.2
 * 5. 最后在thread3不再会重复实例化uniqueInstance了
 */
public class SingletonVolatile {

    private static SingletonVolatile singletonVolatile;

    private SingletonVolatile() {

    }

    public static SingletonVolatile get() {
        if (singletonVolatile == null) {
            synchronized (SingletonVolatile.class) {
                if (singletonVolatile == null) {
                    singletonVolatile = new SingletonVolatile();
                }
            }
        }
        return singletonVolatile;
    }
}

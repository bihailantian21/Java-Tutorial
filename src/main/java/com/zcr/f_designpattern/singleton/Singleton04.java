package com.zcr.f_designpattern.singleton;

//推荐的解法一：利用静态构造函数

/**
 * 有一个函数只能被调用一次，那就是静态构造函数
 * 我们在初始化静态变量instance的时候创建一个实例
 * 但是，调用静态构造函数的时机不是由程序员掌控的，而是第一次使用一个类型的时候自动调用该类型的额静态构造函数，因此会过早地床噶你实例，从而降低内存的使用效率
 */
public class Singleton04 {

    private Singleton04() {

    }

    private static Singleton04 instance = new Singleton04();
    public static Singleton04 getInstance() {
        return instance;
    }
}

package com.zcr.f_designpattern.singleton;

/**
 * **枚举式(线程安全，调用效率高，不能延时加载。并且可以天然的防止反射和反序列 化漏洞！)  ********  》 饿汉式
 * 定义一个枚举的元素，它就代表了单例的一个实例.这个枚举元素，本身就是单例对象！单例可以有自己的操作.添加自己需要的操作！
 * public enum Singleton {
 *     INSTANCE;
 *     private String objName;
 *     public String getObjName() {
 *         return objName;
 *     }
 *     public void setObjName(String objName) {
 *         this.objName = objName;
 *     }
 *     public static void main(String[] args) {
 *         // 单例测试
 *         Singleton firstSingleton = Singleton.INSTANCE;
 *         firstSingleton.setObjName("firstName");
 *         System.out.println(firstSingleton.getObjName());
 *         Singleton secondSingleton = Singleton.INSTANCE;
 *         secondSingleton.setObjName("secondName");
 *         System.out.println(firstSingleton.getObjName());
 *         System.out.println(secondSingleton.getObjName());
 *         // 反射获取实例测试
 *         try {
 *             Singleton[] enumConstants = Singleton.class.getEnumConstants();
 *             for (Singleton enumConstant : enumConstants) {
 *                 System.out.println(enumConstant.getObjName());
 *             }
 *         } catch (Exception e) {
 *             e.printStackTrace();
 *         }
 *     }
 * }
 * firstName
 * secondName
 * secondName
 * secondName
 * 该实现在多次序列化再进行反序列化之后，不会得到多个实例。而其它实现需要使用 transient 修饰所有字段，并且实现序列化和反序列化的方法。
 * 该实现可以防止反射攻击。在其它实现中，通过 setAccessible() 方法可以将私有构造函数的访问级别设置为 public，
 * 然后调用构造函数从而实例化对象，如果要防止这种攻击，需要在构造函数中添加防止多次实例化的代码。
 * 该实现是由 JVM 保证只会实例化一次，因此不会出现上述的反射攻击。
 */
public enum  SingletonEnum {

    INSTANCE;
    private String objName;
    public String getObjName() {
        return objName;
    }
    public void setObjName(String objName) {
        this.objName = objName;
    }
    public static void main(String[] args) {
        // 单例测试
        SingletonEnum firstSingleton = SingletonEnum.INSTANCE;
        firstSingleton.setObjName("firstName");
        System.out.println(firstSingleton.getObjName());

        SingletonEnum secondSingleton = SingletonEnum.INSTANCE;
        secondSingleton.setObjName("secondName");
        System.out.println(firstSingleton.getObjName());
        System.out.println(secondSingleton.getObjName());

        // 反射获取实例测试
        try {
            SingletonEnum[] enumConstants = SingletonEnum.class.getEnumConstants();
            for (SingletonEnum enumConstant : enumConstants) {
                System.out.println(enumConstant.getObjName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

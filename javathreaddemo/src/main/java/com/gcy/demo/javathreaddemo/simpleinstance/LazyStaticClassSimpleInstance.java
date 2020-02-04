package com.gcy.demo.javathreaddemo.simpleinstance;


/**
 * 单例模式 懒汉静态内部类式
 * 线程安全，调用效率高，可以延时加载
 */
public class LazyStaticClassSimpleInstance {
    private LazyStaticClassSimpleInstance(){
        System.out.println("初始化 LazyStaticClassSimpleInstance");
    }

    public static LazyStaticClassSimpleInstance getSimpleInstance(){
        return LazyStaticClassSimpleInstanceHold.simpleInstance;
    }
    private static class LazyStaticClassSimpleInstanceHold{
        private static final LazyStaticClassSimpleInstance simpleInstance = new LazyStaticClassSimpleInstance();
    }

    public String getName(){
        return "懒汉静态内部类单例模式：LazyStaticClassSimpleInstance";
    }
}

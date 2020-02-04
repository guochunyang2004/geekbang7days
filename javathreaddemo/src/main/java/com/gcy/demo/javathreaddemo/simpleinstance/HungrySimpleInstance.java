package com.gcy.demo.javathreaddemo.simpleinstance;

/**
 * 单例模式 饿汉式
 * 线程安全，调用效率高，但是不能延时加载
 */
public class HungrySimpleInstance {
    private HungrySimpleInstance(){
        System.out.println("初始化 HungrySimpleInstance");
    }
    private static HungrySimpleInstance simpleInstance = new HungrySimpleInstance();
    public static HungrySimpleInstance getSimpleInstance(){
        return simpleInstance;
    }

    public String getName(){
        return "饿汉单例模式：HungrySimpleInstance";
    }
}

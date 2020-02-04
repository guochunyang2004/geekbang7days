package com.gcy.demo.javathreaddemo.simpleinstance;

/**
 * 单例模式 懒汉双锁式
 */
public class LazyLockSimpleInstance {
  private LazyLockSimpleInstance(){
      System.out.println("初始化 LazyLockSimpleInstance");
  }
  private static volatile LazyLockSimpleInstance simpleInstance;
  public static LazyLockSimpleInstance getSimpleInstance(){
      if (simpleInstance==null){
          synchronized(LazyLockSimpleInstance.class){
              if (simpleInstance==null){
                  simpleInstance = new LazyLockSimpleInstance();
              }
          }
      }
      return simpleInstance;
    }

    public String getName(){
      return "懒汉双锁单例模式：LazyLockSimpleInstance";
    }
}

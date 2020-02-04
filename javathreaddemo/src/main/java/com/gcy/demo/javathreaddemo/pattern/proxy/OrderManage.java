package com.gcy.demo.javathreaddemo.pattern.proxy;

public class OrderManage {
    public static void save(){
        IOrder order = new Order();
        OrderProxy orderProxy =  new OrderProxy(order);
        orderProxy.save();
    }

}

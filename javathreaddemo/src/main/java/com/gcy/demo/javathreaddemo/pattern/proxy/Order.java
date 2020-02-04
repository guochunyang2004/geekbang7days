package com.gcy.demo.javathreaddemo.pattern.proxy;

public class Order implements IOrder{

    @Override
    public void save() {
        System.out.println("Order.save");
    }
}

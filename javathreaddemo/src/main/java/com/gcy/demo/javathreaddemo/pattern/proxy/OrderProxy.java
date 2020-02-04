package com.gcy.demo.javathreaddemo.pattern.proxy;

public class OrderProxy implements IOrder {

    private IOrder order ;
    public OrderProxy(IOrder order){
        super();
        this.order = order;
    }

    private void savePre(){
        System.out.println("savePre");
    }

    @Override
    public void save() {
        savePre();
        this.order.save();
        saveAfter();
    }

    private void saveAfter(){
        System.out.println("saveAfter");
    }
}

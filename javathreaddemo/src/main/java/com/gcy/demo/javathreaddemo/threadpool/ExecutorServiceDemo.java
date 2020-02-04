package com.gcy.demo.javathreaddemo.threadpool;

import java.util.concurrent.*;

public class ExecutorServiceDemo {

    public void  exec() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
//        ExecutorService executor = new ThreadPoolExecutor(1, 1,
//                0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>());
        // 创建Result对象r
        Result r = new Result();
        r.setCode(1);
        r.setMsg("成功");
        // 提交任务
        Future<Result> future = executor.submit(new Task(r), r);
        Result fr = future.get();
// 下面等式成立
        System.out.println(fr == r);
        System.out.println(fr.getCode() == 1);
    }


    class Task implements Runnable{
        Result r;
        //通过构造函数传入result
        Task(Result r){
            this.r = r;
        }
        public void run() {
            //可以操作result
           // a = r.getCode();
           // r.setXXX(x);
        }
    }
}

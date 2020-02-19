package com.gcy.demo.javathreaddemo;

import com.gcy.demo.javathreaddemo.pattern.proxy.OrderManage;
import com.gcy.demo.javathreaddemo.simpleinstance.HungrySimpleInstance;
import com.gcy.demo.javathreaddemo.simpleinstance.LazyLockSimpleInstance;
import com.gcy.demo.javathreaddemo.simpleinstance.LazyStaticClassSimpleInstance;
import com.gcy.demo.javathreaddemo.threadpool.*;

import java.util.concurrent.*;

public class JavaThreadDemoApplication {

    private static final int age = 1;
    private static final Integer name = 1;

    private static ThreadLocal<Integer> num = new ThreadLocal();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
      //  System.out.println("JavaThreadDemoApplication");
        //MyThreadTest();

        //Test();
       // new ExecutorServiceDemo().exec();
        //futureTaskDemo();
       // futureTaskDemo();
      //  new TeaDemo().run();
        //new CompletableFutureDemo().run();

       //System.out.println(HungrySimpleInstance.getSimpleInstance().getName());
//       System.out.println(LazyLockSimpleInstance.getSimpleInstance().getName());
//       System.out.println( LazyStaticClassSimpleInstance.getSimpleInstance().getName());
        num.set(1);
        OrderManage.save();
    }


    public static void  futureTaskDemo() throws ExecutionException, InterruptedException {

    // 创建FutureTask
            FutureTask<Integer> futureTask = new FutureTask<>(()-> 1+2);
    // 创建线程池
            ExecutorService es = Executors.newCachedThreadPool();
    // 提交FutureTask
            es.submit(futureTask);
        Integer result = futureTask.get();
        System.out.println(result);
            es.submit(new MyCallable());
    // 获取计算结果
             result = futureTask.get();
            System.out.println(result);
            es.shutdown();
    }

    public static void futureTaskDemo2() throws ExecutionException, InterruptedException {
        // 创建FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(()-> 1+2);
        // 创建并启动线程
        Thread T1 = new Thread(futureTask);
        T1.start();
        // 获取计算结果
        Integer result = futureTask.get();
        System.out.println(result);
    }

    public static void MyThreadTest() throws InterruptedException {
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
        // 创建线程池
        MyThreadPool pool = new MyThreadPool(10, workQueue);
        // 提交任务
        pool.execute(()->{
            System.out.println("hello");
        });
    }

    public static void Test() {
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
        3,
        10,
        TimeUnit.SECONDS,
        workQueue );
        for (int i=0;i<10;i++) {
            threadPoolExecutor.execute(new MyTask("task"+ Integer.toString(i),threadPoolExecutor));
        }
    }

    static class MyCallable implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            return 1+2+3;
        }
    }

    static class MyTask implements Runnable{

        private String name;
        private ThreadPoolExecutor threadPoolExecutor;

        public MyTask(String name,ThreadPoolExecutor threadPoolExecutor){
            this.name = name;
           this.threadPoolExecutor=threadPoolExecutor;
        }

        @Override
        public void run() {
            //System.out.println("myTask :"+this.name);
            System.out.println("count:"+threadPoolExecutor.getTaskCount()+",activeCount:"+threadPoolExecutor.getActiveCount());
            System.out.println(this);
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}

package com.gcy.demo.ioc.overview.dependency.lookup;

import com.gcy.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ObjectProviderDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();
        //lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);
        applicationContext.close();
        //test();
        supplierDemo();
    }

    private  static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext){
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        Iterable<String> stringIterable = objectProvider;
        for (String string : stringIterable){
            System.out.println(string);
        }
        objectProvider.stream().forEach(System.out::println);
    }

    private  static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext){
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable(User::createUser);
        System.out.println("user:"+user);
    }
    private static void supplierDemo(){
        Supplier<String> supplier = String::new;
        System.out.println(supplier.get());//""
        Supplier<Emp> supplierEmp = Emp::new;
        System.out.println("---Emp::new end---");
        Emp emp = supplierEmp.get();
        System.out.println("---get() end---");
        emp.setId(100L);
        emp.setName("dd");
        System.out.println(emp.toString());//dd

    }
    private static void test(){
        Consumer<Integer> consumer = x -> {
            int a = x + 2;
            System.out.println(a);// 12
            System.out.println(a + "_");// 12_
        };
        consumer.accept(10);
    }

    @Bean
    @Primary
    public String helloWorld(){
        return "Hello,World";
    }
    @Bean
    public String message() {
        return "Message";
    }

    public static class Emp {
        private Long id;
        private String name;

        public Emp() {
            System.out.println("---init----");
        }

        public Emp(String name) {
            super();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Emp{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}

package com.gcy.demo.ioc.overview.dependency.lookup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class NoUniqueBeanDefinitionExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        applicationContext.refresh();

        try{
            applicationContext.getBean(String.class);
        }
        catch (NoUniqueBeanDefinitionException e){
            System.err.printf("Spring 上下文中存在%d个 %s 类型的 Bean，原因：%s%n",
                    e.getNumberOfBeansFound(),
                    String.class.getName(),
                    e.getMessage());
        }

         applicationContext.close();
    }

    @Bean
    public String bean1() {
        return "11";
    }
    @Bean
    public String bean2() {
        return "22";
    }

    @Bean
    public String bean3() {
        return "33";
    }

    static class POJO implements InitializingBean {

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("For afterPropertiesSet ");
        }
    }
}

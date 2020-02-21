package com.gcy.demo.ioc.overview.dependency.injection;

import com.gcy.demo.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 基于{@link Aware ，spring 3.1} 接口回调的依赖注入示例
 */
public class AwareInterfaceDependencyInjectionDemo  implements BeanFactoryAware, ApplicationContextAware {

    private static BeanFactory beanFactory;
    private static ApplicationContext applicationContext;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //Configuration Class （配置类）
        applicationContext.register(AwareInterfaceDependencyInjectionDemo.class);

//        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
//        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
//        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        System.out.println(beanFactory == applicationContext.getBeanFactory());
        System.out.println(applicationContext == applicationContext);

        User user = getBean(User.class);
        System.out.println(user);

        User user2 =  AppUtil.getBean(User.class);
        System.out.println(user==user2);

        applicationContext.close();
    }

    /**
     * {@link BeanFactoryAware.setBeanFactory}
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.beanFactory = beanFactory;
    }

    /**
     * {@link ApplicationContextAware}
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        if(applicationContext==null){
            System.out.println("applicationContext是空的");
        }else{
            System.out.println("applicationContext不是空的");
        }
        return applicationContext.getBean(clazz);
    }

    @Bean
    public User user(){
        return new User();
    }

    @Component
    public static class AppUtil implements ApplicationContextAware {

        private static ApplicationContext applicationContext;//声明一个静态变量保存

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            System.out.println("applicationContext正在初始化,application:"+applicationContext);
            this.applicationContext=applicationContext;
        }

        public static <T> T getBean(Class<T> clazz){
            if(applicationContext==null){
                System.out.println("applicationContext是空的");
            }else{
                System.out.println("applicationContext不是空的");
            }
            return applicationContext.getBean(clazz);
        }

        public static ApplicationContext getApplicationContext(){
            return applicationContext;
        }
    }
}

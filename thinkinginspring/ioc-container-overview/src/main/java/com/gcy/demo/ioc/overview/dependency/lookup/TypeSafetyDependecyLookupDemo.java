package com.gcy.demo.ioc.overview.dependency.lookup;

import com.gcy.demo.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TypeSafetyDependecyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDependecyLookupDemo.class);
        applicationContext.refresh();
        displayObjectFactoryGetBean(applicationContext);
        displayObjectFactoryGetObject(applicationContext);
        displayObjectProviderIfAvailable(applicationContext);

        displayListableBeanFactoryGetBeansOfType(applicationContext);
        displayObjectProviderStreamOps(applicationContext);
        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectFactory<User> userObjectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps",()->((ObjectProvider<User>) userObjectFactory).forEach(System.out::println));

    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory listableBeanFactory) {
        printBeansException("displayListableBeanFactoryGetBeansOfType",()->listableBeanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectFactory<User> userObjectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable",()->((ObjectProvider<User>) userObjectFactory).getIfAvailable());

    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext){
        ObjectFactory<User> userObjectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject",()->userObjectFactory.getObject());
    }

    private static void displayObjectFactoryGetBean(BeanFactory beanFactory){
        printBeansException("displayObjectFactoryGetBean",()->beanFactory.getBean(User.class));
    }

    private static void printBeansException(String source,Runnable runnable){
        System.err.println("===================================");
        System.err.println("Source from :"+source);

        try{
            runnable.run();
        }
        catch ( BeansException exception) {
            exception.printStackTrace();
        }
    }
}

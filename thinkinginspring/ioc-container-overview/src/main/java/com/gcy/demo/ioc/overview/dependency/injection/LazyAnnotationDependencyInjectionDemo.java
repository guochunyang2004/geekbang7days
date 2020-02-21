package com.gcy.demo.ioc.overview.dependency.injection;

import com.gcy.demo.ioc.overview.dependency.injection.annotation.UserGroup;
import com.gcy.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Set;

public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; //实时注入

    @Autowired
    private ObjectProvider<User> userObjectProvider; //延迟注入

    @Autowired
    private ObjectFactory<Set<User>> usersObjectFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);



        applicationContext.refresh();

        //依赖查找
        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        System.out.println("demo.user = "+ demo.user);
        System.out.println("demo.userObjectProvider = "+ demo.userObjectProvider.getObject()); //继承 ObjectFactory
        System.out.println("demo.usersObjectFactory = "+ demo.usersObjectFactory.getObject()); //继承 ObjectFactory
        demo.userObjectProvider.forEach(System.out::println);

        applicationContext.close();
    }
}

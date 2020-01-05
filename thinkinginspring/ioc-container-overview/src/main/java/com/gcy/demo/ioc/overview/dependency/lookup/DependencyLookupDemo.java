package com.gcy.demo.ioc.overview.dependency.lookup;

import com.gcy.demo.ioc.overview.annotation.Super;
import com.gcy.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找示例
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
//        User user = (User)beanFactory.getBean("user");
//        System.out.println(user);
        //按类型查找
        lookupByType(beanFactory);
        //按类型查找集合对象
        lookupByCollectionType(beanFactory);
        //通过注解查找
        lookupByAnnotationType(beanFactory);

//        lookupInRealTime(beanFactory);
//        lookupInlazy(beanFactory);
    }

    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory =(ListableBeanFactory)beanFactory;
            Map<String,User> users = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找标注 @Super 的所有 User 集合对象："+users);
        }
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory =(ListableBeanFactory)beanFactory;
            Map<String,User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有集合对象："+users);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("实时查找："+user);
    }

    private static void lookupInlazy(BeanFactory beanFactory) {
        //FactoryBean
      ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
      User user = objectFactory.getObject();
        System.out.println("延迟查找："+user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory){
        User user = (User)beanFactory.getBean("user");
        System.out.println("实时查找："+user);
    }
}

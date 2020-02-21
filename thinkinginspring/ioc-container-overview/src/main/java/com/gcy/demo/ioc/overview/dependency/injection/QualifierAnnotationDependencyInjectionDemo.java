package com.gcy.demo.ioc.overview.dependency.injection;

import com.gcy.demo.ioc.overview.dependency.injection.annotation.UserGroup;
import com.gcy.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;


public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user") //指定 Bean 名称或 ID
    private User namedUser;

    // 上下文 4 个 User 类型的 Bean
    // superUser
    // user
    // user1 -> @Qualifier
    // user2 -> @Qualifier

    @Autowired
    private Collection<User> allUsers; //2 Beans = user + superUser

    @Autowired
    @Qualifier
    private Collection<User> qualifiedUsers; // 2 Beans = user1 + user2 -> 4 Beans = user1 + user2 + user3 + user4

    @Autowired
    @Qualifier
    private Collection<User> qroupedUsers; // 2 Beans = user3 + user4

    @Bean
    @Qualifier //进行逻辑分组
    public User user1(){
        return createUser(7L);
    }

    @Bean
    @Qualifier //进行逻辑分组
    public User user2(){
        return createUser(8L);
    }

    @Bean
    @UserGroup //进行逻辑分组
    public User user3(){
        return createUser(9L);
    }

    @Bean
    @UserGroup //进行逻辑分组
    public User user4(){
        return createUser(10L);
    }

    private static User createUser(Long id){
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);



        applicationContext.refresh();

        //依赖查找
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        System.out.println("demo.user = "+ demo.user);
        System.out.println("demo.namedUser = "+ demo.namedUser);

        System.out.println("demo.allUsers = "+ demo.allUsers);
        System.out.println("demo.qualifiedUsers = "+ demo.qualifiedUsers);
        System.out.println("demo.qroupedUsers = "+ demo.qroupedUsers);
        applicationContext.close();
    }
}

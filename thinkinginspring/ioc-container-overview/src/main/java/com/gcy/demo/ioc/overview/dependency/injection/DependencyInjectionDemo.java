package com.gcy.demo.ioc.overview.dependency.injection;

import com.gcy.demo.ioc.overview.annotation.Super;
import com.gcy.demo.ioc.overview.domain.User;
import com.gcy.demo.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/*
依赖注入示例
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-injection-context.xml");

        // 依赖来源一：自定义Bean
        UserRepository userRepository = beanFactory.getBean("userRepository",UserRepository.class);

        //System.out.println(userRepository.getUsers());

        // 依赖来源二：依赖注入（内建依赖）
        System.out.println(userRepository.getBeanFactory());
        //System.out.println(userRepository.getBeanFactory()==beanFactory);

        ObjectFactory userFactory = userRepository.getUserObjectFactory();
        System.out.println(userFactory.getObject()==beanFactory);

        //依赖查找(错误)
        //System.out.println(beanFactory.getBean(BeanFactory.class));

        // 依赖来源三：容器内建 Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取 Enviroment 类型 Bean："+environment);
    }

}

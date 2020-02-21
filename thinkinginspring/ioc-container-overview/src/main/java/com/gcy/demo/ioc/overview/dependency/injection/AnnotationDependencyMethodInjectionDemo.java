package com.gcy.demo.ioc.overview.dependency.injection;

import com.gcy.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

public class AnnotationDependencyMethodInjectionDemo {

    private
    //static //@Autowired 会忽略掉静态字段
    UserHolder userHolder;

    private UserHolder userHolder2;

    @Autowired
    public void init1(UserHolder userHolder){
        this.userHolder = userHolder;
    }

    @Resource
    public void init2(UserHolder userHolder2){
        this.userHolder2 = userHolder2;
    }

    @Bean
    public UserHolder userHolder(User user){
        //return new UserHolder(user);
        UserHolder userHolder = new UserHolder(user);
//        userHolder.setUser(user);
        return userHolder;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        //依赖查找
        AnnotationDependencyMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);

        // @Autowired 字段关联
        UserHolder userHolder = demo.userHolder; //applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);
        System.out.println(demo.userHolder2);
        System.out.println(userHolder == demo.userHolder2);

        applicationContext.close();
    }


}

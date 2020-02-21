package com.gcy.demo.ioc.overview.dependency.injection;

import com.gcy.demo.ioc.overview.dependency.injection.annotation.InjectedUser;
import com.gcy.demo.ioc.overview.dependency.injection.annotation.MyAutowired;
import com.gcy.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;
import javax.swing.text.html.Option;
import java.lang.annotation.Annotation;
import java.util.*;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * 自定义依赖注入注解
 */
public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired
    private User user;

    @Autowired
    private Map<String,User> users;
    @MyAutowired
    private Optional<User> userOptional;//

//    @Inject
//    private User injetedUser;

    @InjectedUser
    private User myInjectUser;

    /**
     * Bean提升为static，注册机制提早触发
     * @return
     */
//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        // 替换原有注解处理，使用新注解 @InjectedUser，此种方法原生注解失效
//        //beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
//
//        // @Autowired + @InjectedUser
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(Arrays.asList(InjectedUser.class,Autowired.class));
//        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//
//        return beanPostProcessor;
//    }

    /**
     *
     * @return
     */
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return beanPostProcessor;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        //依赖查找
        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        // @Autowired 字段关联
        System.out.println("demo.user="+demo.user);
        System.out.println("demo.myInjectUser="+demo.myInjectUser);

        applicationContext.close();
    }
}

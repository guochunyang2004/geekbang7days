# 第九章：重新认识Spring Boot

## Spring Boot 的特性

* 方便的创建可独立运行的Spring应用程序
* 直接内嵌Tomcat、Jetty或Undertow
* 简化了项目的构建配置
* 为Spring及第三方库提供自动配置
* 提供生产级特性
* 无需生成代码或进行XML配置

## Spring Boot的四大核心

* 自动配置 - Auto Configuration

* 起步依赖 - Starter Dependency
* 命令行界面 - Spring Boot CLI
* Actuator

## 自动配置

* 基于添加的JAR依赖自动对Spring Boot应用程序进行配置
* spring-boot-autoconfiguration

## 开启自动配置

* @EnableAutoConfiguration
* exclude = Class<?>[]
* @SpringBootApplication

## 自动配置的实现原理

* @EnableAutoConfiguration
  * AutoConfigurationImportSelctor
  * META-INF/spring.factories
    * org.springframework.boot.autoconfigure.EnableAutoConfiguration(spring-boot-autoconfigure-**.jar)
  
      ```
      org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
      geektime.spring.hello.greeting.GreetingAutoConfiguration
      ```
  
      
* 条件注解
  * @Conditional
  * @ConditionalOnClass
  * @ConditionalOnBean
  * @ConditionalOnMissingBean
  * @ConditionalOnProperty
  * ……

* 观察自动配置的判断结果
  * 启动调试参数：Program arguments：--debug
    * Positive matches 匹配的
    * Negative matches 不匹配的
    * Exclusions 排除的
    * Unconditional classes 无条件匹配
* ConditionEvaluationReportLoggingListener
  * Positive matches
  * Negative mathches
  * Exclusions
  * Unconditional classes

## 手动实现自动配置

* 编写Java Config
  * 标注：@Configuration
* 添加添加
  * 标注：@Conditional
* 定位自动配置
  * META-INF/spring.factories

## 条件注解大家庭（Spring 4引入）

* 条件注解
  * @Conditional
* 类条件
  * @ConditionalOnClass
  * @ConditionalOnMissingClass
* 属性条件
  * @ConditionalOnProperty

* Bean 条件注解
  * @ConditionalOnBean
  * @ConditionOnMissingBean
  * @ConditionOnSingleCandidate
* 资源条件
  * @ConditionalOnResource

* Web 应用条件
  * @ConditionalOnWebApplication
  * @ConditionalOnNotWebApplication
* 其他条件
  * @ConditionalOnExpression
  * @ConditionalOnJava
  * @ConditionalOnJndi
* 执行顺序
  * @AutoConfigureBefore
  * @AutoConfigureAfter
  * @AutoConfigureOrder
  * 

* Spring Boot 错误分析机制
  * FailureAnalysis 类
    * 例如：ctrl + alt +B 找到DataSourceBeanCreationFailureAnalyzer

## 【扩展】spring 常用接口

* Aware接口族

  * 各种Aware接口，方便从上下文中获取当前的运行环境

  * 常见的几个子接口有：

    BeanFactoryAware,BeanNameAware,ApplicationContextAware,EnvironmentAware，BeanClassLoaderAware等

* InitializingBean接口和DisposableBean接口
  * InitializingBean：自定义的初始化方法或者做一些资源初始化操作
  * DisposableBean：可以添加自定义的一些销毁方法或者资源释放操作

* ImportBeanDefinitionRegistrar接口

* BeanPostProcessor接口和BeanFactoryPostProcessor接口
  * 这两个接口为Spring的Bean后置处理器接口,作用是为Bean的初始化前后提供可扩展的空间
  * BeanPostProcessor：
    * 针对Bean实例
    * 在Bean创建后踢狗定制逻辑回调
  * BeanFactoryPostProcessor：
    * 针对Bean定义
    * 在容器创建Bean前获取配置元数据
    * Java Config 中需要定义为static方法

* BeanDefinitionRegistryPostProcessor 接口
  
  * BeanDefinitionRegistryPostProcessor 接口可以看作是BeanFactoryPostProcessor和ImportBeanDefinitionRegistrar的功能集合，既可以获取和修改BeanDefinition的元数据，也可以实现BeanDefinition的注册、移除等操作
* FactoryBean接口
* ApplicationListener

## Maven依赖技巧

* 了解你的依赖
  * mvn dependency:tree   命令
  * IDEA Maven Helper 插件
  
* 排除特定依赖

  * exclusion

* 统一管理依赖

  * dependencyManagement

  * Bill of Materials - bom

    ```xml
    <properties>
    		<java.version>1.8</java.version>
    		<spring-boot.version>2.1.3.RELEASE</spring-boot.version>
    	</properties>
    
    	<dependencyManagement>
    		<dependencies>
    			<dependency>
    				<groupId>org.springframework.boot</groupId>
    				<artifactId>spring-boot-dependencies</artifactId>
    				<version>${spring-boot.version}</version>
    				<type>pom</type>
    				<scope>import</scope>
    			</dependency>
    		</dependencies>
    	</dependencyManagement>
    ```

## Spring Boot 的起步依赖

* Start Dependencies

  * 直接面向功能
  * 一站获得所有相关依赖，不再复制粘贴

* 官方的 Starters

  * spring-boot-starter-*

    https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/html/using-spring-boot.html#using-boot-starter

* 自己的 Starter
  * 主要内容
    * autoconfigure 模块，包含自动配置代码
    * starter 模块，包含指向自动配置模块的依赖及其他相关依赖
  * 命名方式
    * xxx-spring-boot-autoconfigure
    * xxx-spring-boot-starter
  * 注意事项
    * 不要使用 spring-boot 作为依赖的前缀
    * 不要使用 spring-boot 的配置命名空间
    * starter 中仅添加必要的依赖
    * 声明对 spring-boot-starter的依赖

## 外置化配置加载顺序

* 开启 DevTools 时，~/.spring-boot-devtools.properties

* 测试类上的@TestPropertySource注解

* @SpringBootTest#properties属性

* <u>命令行参数（--server.port=9000）</u>

* SPRING_APPLICATION_JSON 中的属性

* ServletConfig 初始化参数

* ServletContext 初始化参数

* java:comp/env 中的JNDI属性

* System.getProperties()

* <b>操作系统环境变量</b>

* random.*涉及到的RandomValuePropertySource

* application文件

  * jar 包外部的 application-{profile}.properties 或 .yml

  * jar 包内部的 application-{profile}.properties 或 .yml

  * jar 包外部的 application.properties 或 .yml

  * jar 包内部的 application.properties 或 .yml

  * 默认位置

    * ./config
    * ./
    * CLASSPATH 中的 /config
    * CLASSPATH 中的 /

  * 修改名字或路径

    * spring.config.name
    * spring.config.location
    * spring.config.additional-location

  * Relaxed Binding

    * | 命名风格           | 使用范围                         | 示例                            |
      | ------------------ | -------------------------------- | ------------------------------- |
      | 短划线分割         | Properties 文件YAML 文件系统属性 | geektime.spring-boot.first-demo |
      | 驼峰式             | 同上                             | geektime.springBoot.firstDemo   |
      | 下划线分割         | 同上                             | geektime.spring_boot.first_demo |
      | 全大写，下划线分割 | 环境变量                         | GEEKTIME_SPRINGBOOT_FIRSTDEMO   |

* @Configuration 类上的 @PropertySource

* SpringApplication.setDefaultProperties()设置的默认属性

## PropertySource

* 添加 PropertySource
  * <context:property-placeholder>
  * PropertySourcesPlaceholderConfigurer
    * PropertyPlaceholderConfigurer
  * @PropertySource
  * @PropertySources
* Spring Boot 中的 @ConfigurationProperties
  * 可以将属性绑定到结构化对象上
  * 支持 Relaxed Binding
  * 支持安全的类型转换
  * @EnableConfigurationProperties
* 定制 PropertySource
  * 主要步骤
    * 实现 PropertySource<T>
    * 从Environment 取得 PropertySources
    * 将自己的 PropertySource 添加到合适的位置
  * 切入位置
    * EnvironmentPostProcessor
    * BeanFactoryPostProcessor
  * 例子：官方JdbcProperties、RandomValuePropertySource

# 第十章：运行中的Spring Boot

## Actuator

* 目的：监控并管理应用程序
* 访问方式：HTTP、JMX
* 依赖：spring-boot-starter-actuator

## 常用 Endpoint

* | ID             | 说明                                  | 默认开启 | 默认HTTP | 默认JMX |
  | -------------- | ------------------------------------- | -------- | -------- | ------- |
  | beans          | 显示容器中的 Bean 列表                | Y        | N        | Y       |
  | caches         | 显示容器中的缓存                      | Y        | N        | Y       |
  | conditions     | 显示配置条件的计算情况                | Y        | N        | Y       |
  | configprops    | 显示 @ConfigurationProperties 的信息  | Y        | N        | Y       |
  | env            | 显示 ConfigurableEnvironment 中的属性 | Y        | N        | Y       |
  | health         | 显示健康检查信息                      | Y        | Y        | Y       |
  | httptrace      | 显示HTTP Trace 信息                   | Y        | N        | Y       |
  | info           | 显示设置好的应用信息                  | Y        | Y        | Y       |
  | loggers        | 显示并更新日志配置                    | Y        | N        | Y       |
  | metrics        | 显示应用的度量信息                    | Y        | N        | Y       |
  | mappings       | 显示所有的 @RequestMapping 信息       | Y        | N        | Y       |
  | scheduledtasks | 显示应用的调度任务信息                | Y        | N        | Y       |
  | shutdown       | 优雅地关闭应用程序                    | N        | N        | Y       |
  | threaddump     | 执行 Thread Dump                      | Y        | N        | Y       |
  | heapdump       | 返回 Heap Dump 文件，格式为 HPROF     | Y        | N        | N/A     |
  | prometheus     | 返回可供 Prometheus 抓取的信息        | Y        | N        | N/A     |

## 访问 Actuator Endpoint

* HTTP 访问
  * /actuator/<id>
* 端口与路径
  * management.server.address=
  * management.server.port=
  * management.endpoints.web.base-path=/actuator
  * management.endpoints.web.path-mapping.<id>=路径
* 开启 Endpoint
  * management.endpoint.<id>.enabled=true
  * management.endpoint.enabled-by-default=false
* 暴露 Endpoint
  * management.endpoints.jmx.exposure.exclude=
  * management.endpoints.jmx.exposure.include=*
  * management.endpoints.web.exposure.exclude=
  * management.endpoints.web.exposure.inclued=info，health


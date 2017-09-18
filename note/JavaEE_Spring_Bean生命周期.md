## Bean的生命周期

Bean的完整生命周期包括：Spring实例化bean->销毁bean。其中过程关键点调用的方法主要分为：

1. Bean自身方法：包括实例化、设置属性、初始化、销毁。
2. Bean级生命周期：通过XxxAware接口，bena直接调用接口方法。
3. 容器级生命周期：后置处理器，独立于bean。（为bean提供后续加工处理的切入点，AOP，动态代理）

--

- 【1】Bean自身方法
    - 1.1 Instantiation——实例化bean
    - 1.2 setPropertyValues——设置属性值
    - 1.3 Initialization——初始化bean——init-method
    - 1.4 DisposableBean——销毁bean——destory-method
- 【2】Bean级生命周期
    - 1.1 BeanNameAware#setBeanName()
    - 2.2 BeanFactoryAware#setBeanFactory()
    - 2.3 InitializingBean#afterPropertiedSet()
    - 2.4 DisposableBean#afterPropertiedSet()
- 【3】容器级生命周期
    - 3.1 InstantiationAwareBeanPostProcessor#——实例化后置处理器
         + postProcessBeforeInstantiation()        
         + postProcessAfterInstantiation()
         + postProcessPropertyValues()
    - 3.2 BeanProcessor#——后置处理器
         + postProcessBeforeInitialization()
         + postProcessAfterInitialization()

#### BeanFactory中的Bean生命周期

- 【---】getBean()
- 【3.1】InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation()
- 【1.1】Instantiation——实例化bean
- 【3.1】InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation()
- 【3.1】InstantiationAwareBeanPostProcessor#postProcessPropertyValues()
- 【1.2】setPropertyValues——设置属性值
- 【2.1】BeanNameAware#setBeanName()
- 【2.2】BeanFactoryAware#setBeanFactory()
- 【3.2】**BeanProcessor#postProcessBeforeInitialization()**
- 【2.3】InitializingBean#afterPropertiedSet()
- 【1.3】Initialization——初始化bean——init-method
- 【3.2】**BeanProcessor#postProcessAfterInitialization()**
- 【---】singletion||prototype
- 【2.4】DisposableBean#afterPropertiedSet()
- 【1.4】DisposableBean——销毁bean——destory-method

#### ApplicationContext中的Bean生命周期

ApplicatonContext的Bean生命周期与BeanFactory相似，多实现一个接口：ApplicationContextAware调用了setApplicationContext()。

- 【1】Bean自身方法
    - 1.1 Instantiation——实例化bean
    - 1.2 setPropertyValues——设置属性值
    - 1.3 Initialization——初始化bean——init-method
    - 1.4 DisposableBean——销毁bean——destory-method
- 【2】Bean级生命周期
    - 1.1 BeanNameAware#setBeanName()
    - 2.2 BeanFactoryAware#setBeanFactory()
    - **ApplicationContextAware#setApplicationContext()**
    - 2.3 InitializingBean#afterPropertiedSet()
    - 2.4 DisposableBean#afterPropertiedSet()
- 【3】容器级生命周期
    - 3.1 InstantiationAwareBeanPostProcessor#——实例化后置处理器
         + postProcessBeforeInstantiation()        
         + postProcessAfterInstantiation()
         + postProcessPropertyValues()
    - 3.2 BeanProcessor#——后置处理器
         + postProcessBeforeInitialization()
         + postProcessAfterInitialization()


ApplicationContext在启动时，为配置文件的每一个<bean>生成一个BeanDefinition对象。（BeanDefinition是<bean>在Spring容器中的内部表示）

- 【---】getBean()
- 【3.1】InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation()
- 【1.1】Instantiation——实例化bean
- 【3.1】InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation()
- 【3.1】InstantiationAwareBeanPostProcessor#postProcessPropertyValues()
- 【1.2】setPropertyValues——设置属性值
- 【2.1】BeanNameAware#setBeanName()
- 【2.2】BeanFactoryAware#setBeanFactory()
- 【---】 **ApplicationContextAware#setApplicationContext()**
- 【3.2】BeanProcessor#postProcessBeforeInitialization()
- 【2.3】InitializingBean#afterPropertiedSet()
- 【1.3】Initialization——初始化bean——init-method
- 【3.2】BeanProcessor#postProcessAfterInitialization()
- 【---】singletion||prototype
- 【2.4】DisposableBean#afterPropertiedSet()
- 【1.4】DisposableBean——销毁bean——destory-method

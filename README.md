#从零开始写javaWeb框架 
Now I need to imitate
>边看黄勇老师书边写，从未这么一次看书写这么多代码

##2016/06/22
- 1. 添加ClassLoad类
- 2. 添加annotation
- 3. 实现bean 容器
  + java反射 网上可以找点资料
- 4. 添加依赖注入功能（但是只能单例 当一个类被初始化两次就会有异常）
  + (1) 反射-获取属性注入 
  + (2) getFields()获得某个类的所有的公共（public）的字段，包括父类。 
  + (3) getDeclaredFields()获得某个类的所有申明的字段，即包括public、private和proteced，但是不包括父类的申明字段。 
- 5.添加DispatcherServlet
  + (1) 先通过调用request.getParameter() 方法得到参数后，
  + (2) 再调用 request.getInputStream()或request.getReader()已经得不到流中的内容，
  + (3) 因为在调用 request.getParameter()时
  + (4) 系统可能对表单中提交的数 据以流的形式读了一次,反之亦然。
- 6.添加app包
- 7.bug
  + (1)ClassUtil:63 Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".","/")); 格式应该是 /C:/ddd
  + (2)smart.properties smart.framework.app.base_package = org.smart4j.app 扫描的包应该是app包
- 8.proxy(代理模式)
  + (1)java静态代理(使用较少)
  + (2)java动态代理(只能代理接口，创建速度较快)
  + (3)CGLib代理（可代理类，但是创建速度较慢）
 - 9.Aspect(切面 基于方法)
  + (1)获取所有继承AspectProxy代理类
  + (2)判断是否有注解@Aspect的类
  + (3)获取所有有注解@Aspect中value的注解的类 @Aspect(Controller.class)
  + (4)对代理类和目标类进行映射 现在是一个代理类映射多个目标类 但我们需要的是一个目标类对应多个代理类
  + (5)遍历进行反向映射
  + (6)现在当有两个代理类都实现了begin方法的时候会不确定那个方法先运行
- 10.map
  + 后面添加相同的Key的会覆盖前面的值
- 11.添加事务
  + *(1)为所有Service方法添加事务代理
  + *(2)判断是否有事务注解 
  + *(3)有事务注解的开启事务->执行方法->提交事务
  + *(4)无事务注解的执行方法(应用于单条查询)
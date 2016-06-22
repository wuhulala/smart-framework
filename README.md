#从零开始写javaWeb框架第三章
the time I need imitate
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
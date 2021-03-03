1.讨论Action类的三种书写形式：
pojo 
Action  -  五个常量 + 一个方法
ActionSupport

2.讨论配置文件中Action的方法的四种书写形式：
不指定-默认：String execute()
指定方法名：不需要()
通配符 : 使用较多
动态方法访问:默认是关闭的，需要配置常量 - 开发中使用较少

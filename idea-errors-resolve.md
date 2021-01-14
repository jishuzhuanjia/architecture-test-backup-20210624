# 记录idea使用期间出现错误的解决方法

# 1.Error:java: Compilation failed: internal java compiler error
# 或
# Error:java: 无效的源发行版: 11
    原因：jdk设置不一致导致。
    解决：保证以下3处设置相同：
    1.Project Structure -> Project -> Project SDK
    2.Project Structure -> Project -> Project language level
    3.Setting...Java Compiler -> 对应Module -> Target bytecode version

# 2.idea通过tomcat部署项目deployment中没有exploded
    1.Cannot start compilation: the output path is not specified for module "Test". Specify the out
    解决：  
    a.Project Sturcture -> Project ->
    为Project compiler output指定一个文件夹。
    
    b.Project Sturcture -> Module -> 找到指定的模块 -> Paths ->
    选中lnherit project compile output path即可。
    
    2.Tomcat deployment没有exploded解决：
    先导入模块 -> 然后打开Project Structure -> Artifact -> + -> web application: exploded -> from module
    即可创建exploded，然后tomcat中配置Deployment即可。
    
# 3.解决Module名和pom中artifactId不一致
    删除Module下的iml文件 -> Maven reimport即可
    备注：被导入的Maven Module下会生成iml文件。
    
# 4.maven导入依赖不成功，可以尝试如下的操作
    1.maven窗口中可以尝试对导入依赖失败的module单独进行reimport。
    
# 5.编译报错：Error:(74, 120) java: -source 1.5 中不支持 diamond 运算符(请使用 -source 7 或更高版本以启用 diamond 运算符)
    Build,Execution,Deployment->..->Java Compiler -> Override compile parameters per-module中的参数移除掉. 
    
# 6.
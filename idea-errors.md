# 记录idea使用期间出现错误的解决方法

# 1.Error:java: Compilation failed: internal java compiler error
原因：jdk设置不一致导致。
解决：保证以下3处设置相同：
1.Project Structure -> Project -> Project SDK
2.Project Structure -> Project -> Project language level
3.Setting...Java Compiler -> 对应Module -> Target bytecode version

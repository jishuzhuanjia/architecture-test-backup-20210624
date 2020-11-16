1.关闭代码格式化(ctrl + alt + l)时，对文档注释的格式化
Settings -> Editor -> Code Style -> Java -> JavaDoc -> 取消Enable JavaDoc formatting。

2.错误将窗体字体设置巨大，导致idea启动失败解决：
修改
C:\Users\Administrator\AppData\Roaming\JetBrains\IntelliJIdea2020.1\options\ui.inf.xml
添加
<option name="FONT_SIZE" value="14" />
恢复正常。

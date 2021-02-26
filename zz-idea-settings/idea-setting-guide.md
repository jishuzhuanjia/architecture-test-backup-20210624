# 1.关闭代码格式化(ctrl + alt + l)时，对文档注释的格式化
Settings -> Editor -> Code Style -> Java -> JavaDoc -> 取消Enable JavaDoc formatting。

# 2.错误将窗体字体设置巨大，导致idea启动失败解决：
修改
C:\Users\Administrator\AppData\Roaming\JetBrains\IntelliJIdea2020.1\options\ui.inf.xml
添加
<option name="FONT_SIZE" value="14" />
恢复正常。

# 3.设置Settings repository后import settings和export settings消失的解决办法
Settings -> Tools-> Settings Repository -> Delete

# 4.设置Maven自动导入依赖
Settings - Editor - Auto Import -> Add unambigious imports on the fly(动态地添加明确的导入)

注: 设置此项后,对于唯一定义, 会自动import。有多定义的, 需要手动导入。

# 5.设置Maven import动态优化
如移除没有用到的import

具体设置如下: Optimize imports on the fly(for current project)

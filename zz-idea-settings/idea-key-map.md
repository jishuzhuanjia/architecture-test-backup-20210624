# 以下为idea自带快捷键

## 1.设置
Ctrl + Shift + s						全局设置✔
Ctrl + Shift + Alt + s					设置项目结构✔

## 2.代码自动化
Ctrl + I                                实现未实现的接口方法✔
Ctrl + O                                重写父类方法✔
Alt+Insert                              生成代码(如get,set方法,构造函数等) 或者右键（Generate）✔
Ctrl + Alt + O                          优化导入的类和包 / 快速导入类
Ctrl + Alt + l						    格式化代码，缩进对齐✔
Ctrl + Alt + T                          捕获处理异常,生成try catch(或者 Alt+enter)✔
Ctr + Shift + U                         大小写转化✔
Alt +   回车                             导入包,自动修正✔
Alt + /                                 代码自动补全✔
Ctrl + J                                自动代码✔
Ctrl + Shift+J                          整合多行为1行,该命令不可实现反向功能(可用Ctrl + Alt + L来实现)✔
Ctrl + 空格                              代码提示(×)
Ctrl + Shift + SPACE                    自动补全代码✔,没有Alt + /给力
Ctrl + Alt + T                          Surround With: 把选中的代码放在 TRY catch{}、IF{} ELSE{}等语句里✔
Ctrl + Alt + I                          自动缩进代码，自动缩进光标所在行或缩进选中的行✔
Ctrl + Alt + V                          提取变量✔
Ctrl + Shift + T                        快速构建单元测试✔

fori/sout/psvm + Tab                    ×

## 3.辅助命令
F4                                      查看类/接口/变量...定义
Ctrl + Q                                可以看到当前方法的声明✔
Ctrl + H                                打开Type Hierarchy, 可以查看类继承关系✔
Ctrl + E                                Recent Files,查看最近文件✔
Ctrl + Shift + E                        Recent Locations,可以查看最近编辑过的位置,比Ctrl + E更精确，精确到行。✔
Ctrl + Alt + SPACE                      手动打开代码提示,提示类名、接口名、方法、关键字等...✔
Ctrl + P                                手动打开方法参数提示,有的方法参数多,这个命令就很重要了✔
Alt + F7                                Find Usages: 查找类/接口等在哪些地方被使用过
Ctrl + Alt + F7                         (浮动窗口)找到你的函数或者变量或者类的所有引用到的地方

Alt+ Shift + F                          将当前文件位置添加到favorites中，会保留行位置、选中内容、光标位置。

Ctrl + F12                              浮动显示当前文件的结构

Ctrl + G                                定位行,格式row:column,如果只输入一个定位行。

# 文本查找
Ctrl + F                                在当前窗口查找文本
Ctrl + Shift + F                        在指定位置查找文本,In Project,Module,Directory,Scope,scope就相当于自定义位置
F3                                      向下查找关键字出现位置
Shift + F3                              向上一个关键字出现位置

# 文本替换
Ctrl + R                                在当前窗口替换内容
Ctrl + Shift + R                        在指定位置替换内容

## 查找文件、变量、方法
Ctrl+N                                  查找类              
Ctrl + Shift + N                        查找类/接口/文件
Ctrl + Alt + Shift + N                  查找类中的方法或变量
Shift+Shift                             查找所有

Alt + Shift + C                         查看IDEA文件修改记录(Recent Changes)

## 记忆书签
ctrl + F11                              为光标位置添加/移除书签,为其分配数字或字母
ctrl + 数字/字母                          快速跳转到指定的标签位置

## 4.重构
Shift + F6                              重命名 (包、类、方法、变量、甚至注释等)✔
Ctrl + Alt + Shift + T                  弹出重构菜单✔
F6                                      移动✔
F5                                      复制✔
Alt + Delete                            安全删除✔
Ctrl + Alt + N                          内联

## 5.窗口
Alt + 1                                 Project窗口
Alt + 9                                 Git
Alt + 7                                 Structure
Alt + 2                                 Favorites
Alt +  0                                Messages
Alt+ 4                                  Run
Alt + ←                                 选择上一个文件标签
Alt + →                                 选择下一个文件标签

ctrl + shift + Backspace                回到上一个编辑的文件，如果文件被关闭，会立即打开

## 6.debug
Alt + F8                                Evaluate Expression: 以独立窗口查看变量具体值
                                        可以使用变量进行表达式计算，如将对象转换成json字符串
Ctrl + Shift + F8                       查看和管理当前断点
Ctrl + Shift + F8                       查看断点
################################## 以上为开发中常用 ##################################    

3.2、查询快捷键
选中文本，按Alt+F3 ，高亮相同文本，F3逐个往下查找相同文本
F4 查找变量来源
Ctrl+Shift+O 弹出显示查找内容
Ctrl+W 选中代码，连续按会有其他效果
F2 或Shift+F2 高亮错误或警告快速定位
Ctrl+Up/Down 光标跳转到第一行或最后一行下
Ctrl+B 快速打开光标处的类或方法
Ctrl+Alt+B 找所有的子类
Ctrl+Shift+B 找变量的类
Ctrl+Shift+上下键 上下移动代码
Ctrl+Alt+ left/right 返回至上次浏览的位置
Ctrl+X 删除行
Ctrl+D 复制行
Ctrl+/ 或 Ctrl+Shift+/ 注释（// 或者/…/ ）
Ctrl+H 显示类结构图
Ctrl+Q 显示注释文档
Alt+F1 查找代码所在位置
Alt+1 快速打开或隐藏工程面板
Alt+ left/right 切换代码视图
Alt+ ↑/↓ 在方法间快速移动定位
Ctrl+Alt+ left/right 前后导航编辑过的地方
Ctrl＋Shift＋Backspace可以跳转到上次编辑的地
Alt+6 查找TODO

3.3、其他快捷键
Shift+ENTER 另起一行
Ctrl+Z 倒退(撤销)
Ctrl+Shift+Z 向前(取消撤销)
Ctrl+Alt+F12 资源管理器打开文件夹
Alt+F1 查找文件所在目录位置
Shift+Alt+INSERT 竖编辑模式
Ctrl+F4 关闭当前窗口
Ctrl+Alt+V，可以引入变量。例如：new String(); 自动导入变量定义
Ctrl+~，快速切换方案（界面外观、代码风格、快捷键映射等菜单

3.4、调试快捷键
Alt+Shift+F9，选择 Debug
Alt+Shift+F10，选择 Run
Ctrl+Shift+F9，编译

F7，步入
Shift+F7，智能步入
Alt+Shift+F7，强制步入
F8，步过
Shift+F8，步出
Alt+Shift+F8，强制步过
Alt+F9，运行至光标处
Ctrl+Alt+F9，强制运行至光标处
F9，恢复程序
Alt+F10，定位到断点

3.5、重构
Ctrl+Alt+Shift+T，弹出重构菜单
Shift+  F6，重命名
F6，移动
F5，复制
Alt+Delete，安全删除
Ctrl+Alt+N，内联



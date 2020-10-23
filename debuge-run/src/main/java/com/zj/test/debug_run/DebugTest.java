package com.zj.test.debug_run;

import com.zj.test.util.TestHelper;

/* @author: zhoujian
 * @create-time: 2020/9/23 13:33
 * @description: idea debug工具测试
 * @version: 1.0
 */
public class DebugTest {

    public static void main(String[] args) {
        TestHelper.startTest("Debug 测试");
        TestHelper.println("debuger test: code line-1");
        TestHelper.println("debuger test: code line-2");
        TestHelper.println("debuger test: code line-3");
        //001.注意：当执行到断点处时，断点处该行代码为将执行代码，此时还没有执行。
        //002.在调试的过程中，可以在还没有执行的代码处添加断点。
        TestHelper.println("debuger test: code line-4");
        TestHelper.println("debuger test: code line-5");
        TestHelper.println("debuger test: code line-6");
        TestHelper.println("debuger test: code line-7");
        TestHelper.println("debuger test: code line-8");
        TestHelper.println("debuger test: code line-9");
        TestHelper.println("debuger test: code line-10");

        /*003.Debug窗口中几个按钮的作用:
        *step over(F8): 一次执行一行代码，会在下一行代码处停下，
        * 如果当前方法代码执行完，会跳出该方法，并到下一行该执行的代码处停下。
        *
        * step into(F7):进入到断点处的方法内部，不能是官方的方法。
        * 如果是官方的方法就相当于执行一次step over
        *
        * Force step into(alt+shift+F7): 强制进入方法，包括官方的方法。
        *
        * Step out(shift+F8)：跳到当前方法之外的下一行该执行的代码处。
        *
        * Drop Frame：跳出框框，跳到当前方法之外的下一行该执行的代码处。
        * 它与Step out的不同时它会记录step into的栈，跳到顶部时
        * 就不能继续跳出了。而Step out会继续跳出，可能会导致剩下所有的代码都会被
        * 执行完。
        *
        * Run To Cursor: 运行到鼠标的位置停下。
        * 需要注意的是不能是已经运行过的代码
        * 可以在当前要执行的方法内部执行该命令
        *
        * Rerun: 重新调试
        *
        * Resume Program(F9): 恢复程序正常运行，它会在下一个断点处停下。
        *
        * Stop: ctrl+F2: 停止调试
        *
        * View Beakpoints... 查看当前的所有断点
        *
        * Mute Beakpoints: 停用接下来所有的断点,该功能可以在调试的过程中开启/关闭。
        *
        * */
    }
}

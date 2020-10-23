package com.zj.test.java.try_catch;

import com.zj.test.util.TestHelper;
import org.apache.commons.lang.StringUtils;

import java.io.FileNotFoundException;
import java.net.SocketException;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/22 10:27
 * @description: throw、try-catch测试
 * @finished: 1
 * @finished-time: 2020年10月22日 10:37:31
 */
public class TryCatchTest {

    // 参数不能为空，否则抛出NullPointerException
    public static boolean println(String content) {
        if (null == content) {
            throw new NullPointerException("content can not null");
            /**
             * 1.throw语句后的代码执行不到
             * 方法一旦执行了throw语句，该方法后的所有代码都不会执行。调用者也会中断执行,
             * 因为代码出现了错误，已经走上了错误的道路，所以必须终止
             *
             * 如果想要throw后不终止，需要使用try catch语句来正确处理异常，后面的代码才会继续执行。
             */
            // Unreachable statement
            // TestHelper.println("code after throw");
        }

        // 如果发生异常，不会被执行
        TestHelper.println("code after throw");
        TestHelper.println(content);
        return true;
    }

    /**
     * <p>
     * 2.throws
     * 方法中抛出Exception，会要求处理异常，此时我们可以将处理异常的工作交给调用者。就是通过throws关键字，
     * 如果多种异常是父子关系，只需要写父类即可，但是这不便于调用者对异常的原因进行细分。
     * <p>
     * 注意：T extens RuntimeException: 运行时异常不用添加throws关键字，就算添加了，调用者调用时也不会提醒。
     */
    public static void findFile(String path) throws FileNotFoundException, SocketException {
        if (StringUtils.isBlank(path))
            throw new FileNotFoundException("文件" + path + "找不到");

        // 这里只是模拟方法抛出多个异常，异常可能不符合上下文，请忽略
        throw new SocketException("socket exception");
    }

    /**
     * 3.异常的处理
     * 对于我们能够处理的异常，应尽量处理。可以使用其他逻辑来代替抛出异常。
     * 对于那些我们无法决定处理方式的异常，应该throws交给调用者来决定如何处理。
     * @param args
     */
    public static void main(String[] args) {
        // findFile("xxx");
    }
}

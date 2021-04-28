package test.frame;

import com.zj.test.util.TestHelper;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.junit.Test;

import java.io.IOException;

/**
 * @time：2019年12月27日 下午11:52:06
 * @author：zhoujian
 * @description：apache commons-io,FilenameUtils使用
 * @finished：1
 * @finishTime：2019年12月27日23:52:28
 */
public class FilenameUtilsTest {

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 1.public static String concat(String basePath, String fullFilenameToAdd)
     *
     *
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     * 1.windows下会将'/'替换成'\'，而linux下会将'\'替换成'/'。
     *
     * 2.如果参数2以 / 或 // 开头，直接返回参数2。
     *
     * 3.如果参数1或参数2超过2个分隔符，则返回null。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void concat() {
        // apache commons-io包已经有文件相关api
        // 以下提取常用api

        /**
         * 1.拼接两个部分：并不是简单的合并字符串，会根据命令行分隔符进行分隔符替换，分隔符填充、移除(多个连续分隔符只保留一个)
         * 除了分隔符，对于windows和uninx输出是一样的。
         * 注意：参数中的/或 \会被命令行分隔符替换，如windows下使用\，所有的/会被转换成\
         * 注意：参数2应该是相对路径，不能以分隔符开头，可以是文件或目录，如果参数2以分隔符/或\开头，则返回等于参数2
         * 注意：参数1可以不以分隔符结尾，如果没有，api会自动添加。
         * 注意：这两个参数开头不能连续超过2个分隔符，否则方法返回null，如果开头是两个分隔符，则保留两个分隔符，除开头位置，其他位置的连续分隔符只会保留一个。
         * 注意：结果不会为参数1开头部分添加分隔符，调用者需要注意。
         */
        /*
        c\b\x\dsa

        windwos下，会用'\'替换'/'
        参数1尾部和参数2头部没有分隔符，会自动在参数1和2之间添加分隔符。
         */
        System.out.println(FilenameUtils.concat("c/////b", "x/dsa"));

        // a\b\x\dsa
        System.out.println(FilenameUtils.concat("a/b", "x/dsa"));
        // \\x\dsa
        // 参数2以1或2个分隔符开头，则直接返回参数2
        // 超过2个分隔符，返回null。
        System.out.println(FilenameUtils.concat("a/b", "\\\\x/dsa"));
        // a\b\x\dsa
        System.out.println(FilenameUtils.concat("a/b", "x/dsa"));
        // a\b\x\dsa
        System.out.println(FilenameUtils.concat("a/b/", "x/dsa"));

        // null: 第一个参数开头也不能超过2个分隔符,否则返回null
        System.out.println(FilenameUtils.concat("///a/b/", "x//dsa"));

        // \\x\\dsa\：如果参数2以/或//开头，直接返回参数2，分隔符最多保留2个
        System.out.println("1: " + FilenameUtils.concat("//a/b////", "//x///////dsa/"));

        // \a\b\c\v
        System.err.println(FilenameUtils.concat("/a", "b/c//v"));

        TestHelper.println("FilenameUtils.concat(\"a/b/c//\",\"d/e\")", FilenameUtils.concat("a/b/c//", "d/e"));
        // a\b\c\d\e
        TestHelper.println("FilenameUtils.concat(\"a/b/c//\",\"d//e\")", FilenameUtils.concat("a/b/c//", "d//e"));
        // a\b\c\d\e
        TestHelper.println("FilenameUtils.concat(\"a/b/c//\",\"d////e\")", FilenameUtils.concat("a/b/c//", "d////e"));
        //  \\a\b\c\d
        TestHelper.println("FilenameUtils.concat(\"//a/b\",\"c/d\")", FilenameUtils.concat("//a/b", "c/d"));

        // \\c\d
        TestHelper.println("FilenameUtils.concat(\"//a/b\",\"//c/d\")", FilenameUtils.concat("//a/b", "//c/d"));

        TestHelper.println("FilenameUtils.concat(\"///a/b\", \"/c/d\")", FilenameUtils.concat("///a/b", "c/d"));

    }

    public static void main(String[] args) {
        /**
         * 2.1.比较文件名 - 默认情况下，大小写敏感的 就是简单的比较两个字符串,但是对于空指针也会正确进行比较。是String.equals的改进版。 注意：
         * equals(null,null) //true
         */
        // true
        System.out.println(FilenameUtils.equals(null, null));
        // false
        System.out.println(FilenameUtils.equals(null, "1"));
        // false
        System.out.println(FilenameUtils.equals(" ", "1"));
        // false 大小写敏感的
        System.out.println(FilenameUtils.equals("a", "A"));

        /**
         * 2.2. 三参数equals，第三个参数可以设置大小写敏感 IOCase.INSENSITIVE IOCase.SENSITIVE
         * IOCase.SYSTEM //由系统决定，可移植性强,如在windows下相当于IOCase.INSENSITIVE
         *
         * nor
         *
         */
        // false：空格不会被移除
        System.out.println(FilenameUtils.equals("a", "A ", false, IOCase.INSENSITIVE));
        // true
        System.out.println(FilenameUtils.equals("a", "A", false, IOCase.INSENSITIVE));
        System.out.println("---");
        // true
        System.out.println(FilenameUtils.equals("a", "A", false, IOCase.SYSTEM));

        String s1 = "\\a\\b", s2 = "\\\\a\\b";
        // false
        System.out.println(FilenameUtils.equals(s1, s2, false, IOCase.INSENSITIVE));
        // \a\b
        System.out.println(s1);
        System.out.println(s2);

        /**
         * 3.1.格式化文件名：normalize
         *
         */
        // \\a\a\a
        System.out.println(FilenameUtils.normalize("//a/a/a").length());
        // 注意：空格不会移除
        System.out.println(FilenameUtils.normalize("//a/a/a ").length());

        /**
         * 3.2.normalizeNoEndSeparator：作用相当于normalize +(如果末尾有分隔符，移除末尾的分隔符)
         */
        // a/b/b/c
        System.out.println(FilenameUtils.normalizeNoEndSeparator("a/b/b/c"));
        // a\b\b\c
        System.out.println(FilenameUtils.normalizeNoEndSeparator("a/b/b/c/"));
        // a\b\b\c
        System.out.println(FilenameUtils.normalizeNoEndSeparator("a/b/b/c/////"));

        /**
         * 4. directoryContains：检测文件/目录是否是另一个名录的子文件/子目录
         * 注意：请保证使用相同类型和个数的分隔符，你也可以normalize来确保这一点。
         */
        try {
            // true
            System.out.println(FilenameUtils.directoryContains("a/b/c", "a/b/c/d"));

            // true
            System.out.println(FilenameUtils.directoryContains("/a/b/c", "/a/b/c/d"));

            // true
            System.out.println(FilenameUtils.directoryContains("/a/b/c", "/a/b/c/d/e/1.txt"));

            // false
            System.out.println(FilenameUtils.directoryContains("/a/b/c", "\\a/b/c/d/e/1.txt"));

            // normalize以确保同类型分隔符，但是开头分隔符需要开发者保证一致
            // true
            System.out.println(FilenameUtils.directoryContains(FilenameUtils.normalize("/a/b/c"),
                    FilenameUtils.normalize("\\a/b/c/d/e/1.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 5.getExtension 获取拓展名，无"."
         */
        // txt
        System.out.println(FilenameUtils.getExtension("a/b/c/ds.txt"));
        // "" - 空串而不是null
        System.out.println(FilenameUtils.getExtension("a/b/c/ds"));

        /**
         * 6.indexOfExtension：字符串中后缀开始下标，需要注意的是查找的是"."的位置。
         */

        // 1
        System.out.println(FilenameUtils.indexOfExtension("a.txt"));

        /**
         * 7.getFullPath 获取文件的所在路径，如果是路径，则返回路径本身
         */
        // /a/b/c/
        System.out.println(FilenameUtils.getFullPath("/a/b/c/d"));

        // /a/b/c/
        System.out.println(FilenameUtils.getFullPath("/a/b/c/"));

        // ~/a/b/c/
        System.out.println(FilenameUtils.getFullPath("~/a/b/c/"));

        // */a/b/c/
        System.out.println(FilenameUtils.getFullPath("*/a/b/c/d"));

        // ""
        System.out.println(FilenameUtils.getFullPath("c"));

        // c:
        System.out.println(FilenameUtils.getFullPath("c:"));

        /**
         * 8.getBaseName 获取文件名，如果是目录返回""
         */

        // 22
        System.out.println(FilenameUtils.getBaseName("a/b/c/22.txt"));
        // ""
        System.out.println(FilenameUtils.getBaseName("a/b/c/"));

        /**
         * 9.移除后缀名，并返回，如果是目录，则直接返回目录
         */
        // a/b/1
        System.out.println(FilenameUtils.removeExtension("a/b/1.txt"));
        // a/b/
        System.out.println(FilenameUtils.removeExtension("a/b/"));
    }
}

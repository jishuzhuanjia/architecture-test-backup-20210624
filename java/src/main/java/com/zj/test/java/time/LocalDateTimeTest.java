package com.zj.test.java.time;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static java.time.temporal.ChronoField.*;

/**
 * @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/4/2 15:08
 * @description: LocalDateTime测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class LocalDateTimeTest {

    /**
     * 1.LocalDateTime常用api测试
     * 【描述】
     *
     *
     * 【返回值】
     *
     * 【注意事项】
     */
    @Test
    public void test() {
        LocalDateTime localDateTime = LocalDateTime.now();
        TestHelper.println("localDateTime", localDateTime);
        // 年
        TestHelper.println("getYear", localDateTime.getYear());
        // 一年当中的第几天 [1,366]
        TestHelper.println("getDayOfYear", localDateTime.getDayOfYear());
        // 月： [1,12]
        TestHelper.println("getMonthValue", localDateTime.getMonthValue());
        // 月，枚举类型，如JUNE
        TestHelper.println("getMonth", localDateTime.getMonth());
        // 日：[1,31]
        TestHelper.println("getDayOfMonth", localDateTime.getDayOfMonth());
        TestHelper.println("getHour", localDateTime.getHour());
        TestHelper.println("getMinute", localDateTime.getMinute());
        TestHelper.println("getSecond", localDateTime.getSecond());
        // 星期几,枚举类型，如WEDNESDAY
        TestHelper.println("getDayOfWeek", localDateTime.getDayOfWeek());
        // 星期几，数值，如WEDNESDAY为3
        TestHelper.println("getDayOfWeek().getValue()", localDateTime.getDayOfWeek().getValue());

        TestHelper.startTest("预定义LocalDateTime格式化测试");
        //20210618
        TestHelper.println("BASIC_ISO_DATE", localDateTime.format(DateTimeFormatter.BASIC_ISO_DATE));
        // java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: InstantSeconds
        //TestHelper.println("ISO_INSTANT",localDateTime.format(DateTimeFormatter.ISO_INSTANT));
        //java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: OffsetSeconds
        //TestHelper.println("ISO_ZONED_DATE_TIME",localDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));

        // 13:43:53.157
        TestHelper.println("ISO_LOCAL_TIME", localDateTime.format(DateTimeFormatter.ISO_LOCAL_TIME));

        // 2021-06-18
        TestHelper.println("ISO_LOCAL_DATE", localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE));

        // 2021-06-18
        TestHelper.println("ISO_DATE", localDateTime.format(DateTimeFormatter.ISO_DATE));

        // 13:45:25.602
        TestHelper.println("ISO_TIME", localDateTime.format(DateTimeFormatter.ISO_TIME));

        // 2021-06-18T13:46:32.937
        TestHelper.println("ISO_DATE_TIME", localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));

        TestHelper.println("ISO_WEEK_DATE", localDateTime.format(DateTimeFormatter.ISO_WEEK_DATE));

        TestHelper.println("ISO_ORDINAL_DATE", localDateTime.format(DateTimeFormatter.ISO_ORDINAL_DATE));

        // java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: OffsetSeconds
        //TestHelper.println("ISO_OFFSET_DATE",localDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE));

        // java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: OffsetSeconds
        //TestHelper.println("ISO_OFFSET_DATE_TIME",localDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        // java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: OffsetSeconds
        //TestHelper.println("ISO_OFFSET_DATE_TIME",localDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        // 2021-06-18T13:51:03.017
        TestHelper.println("ISO_LOCAL_DATE_TIME", localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    /**
     * <p>
     *     2.测试: 自定义LocalDateTime格式化
     * </p>
     *
     * 【出入参记录】
     *
     * 【结论】
     *
     * 【注意点】
     *
     */
    @Test
    public void customFormat() {
        LocalDateTime localDateTime = LocalDateTime.now();

        // 1.可以组合DateTimeFormatter已有的格式化器进行LocalDateTime格式化
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ISO_LOCAL_DATE)
                .appendLiteral(' ')
                .append(DateTimeFormatter.ISO_LOCAL_TIME).toFormatter();

        // 2021-06-18 14:03:15.565
        // 结果里包含了毫秒
        // 因为ISO_LOCAL_TIME格式化器有毫秒，我们不想要的话，可以自定义
        TestHelper.println(localDateTime.format(dateTimeFormatter));

        //2.自定义Time格式化器(控制能够更精准)
        DateTimeFormatter dateTimeFormatter1 = new DateTimeFormatterBuilder()
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(MINUTE_OF_HOUR, 2)
                .appendLiteral(':')
                .appendValue(SECOND_OF_MINUTE, 2)
                .toFormatter();

        TestHelper.println(localDateTime.format(dateTimeFormatter1));

        //3.快速生成格式化器
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        TestHelper.println(localDateTime.format(dateTimeFormatter2));

        // %2021-%17-%18 14:17:56
        //DateTimeFormatter dateTimeFormatter3 = DateTimeFormatter.ofPattern("%Y-%m-%d HH:mm:ss");
        //TestHelper.println(localDateTime.format(dateTimeFormatter3));

        DateTimeFormatter dateTimeFormatter4 = DateTimeFormatter.ofPattern("y-M-d h:m:s");
        // 2021-6-18 2:18:58
        TestHelper.println(localDateTime.format(dateTimeFormatter4));
    }
}

package com.zj.test.json.fastjson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zj.test.util.TestHelper;
import org.junit.Test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;


/**
 * @time：2019年12月26日 下午9:43:50
 * @author：zhoujian
 * @corporation：luke
 * @description：alibaba fastjson使用
 * @finished：false
 * @finishTime：
 *
 */

/**
 *优点：
 * json字符串反序列化的过程中:
 * 1.支持动态对象/数组,会判断json字符串是对象还是数组，灵活转换成JSONObject / JSONArray      // true
 * 2.可以不提供PO类,可以直接转换成JSONObject/JSONArray对象。
 * 当然，也支持转换成指定的类对象。
 * 3.可以很方便的向JSONArray/JSONObject对象获取和存储值。
 * 4.支持序列化控制。
 *
 * object序列化的过程: 更方便的控制序列化。
 *
 * */
public class FastJsonTest {

    /**
     * 1.fastjson反序列化方法测试(json字符串 -> 对象)
     */
    @Test
    public void json2Object() {
        TestHelper.startTest("fastjson反序列化方法测试(json字符串 -> 对象)");
        String userJson = "{\"username\":\"zhoujian\",\"age\":25}";

        /*
        1.1.JSON.parse(..)测试
        优点: 会自动判断json字符串并转换成JSONObject/JSONArray，
        缺点: 在后续的赋值中，需要判断返回值的具体类型，是JSONObject/JSONArray，否则会强转失败。如：
        JSONObject object = (JSONObject) JSON.parse(user);

        java.lang.ClassCastException: com.alibaba.fastjson.JSONArray cannot be cast to com.alibaba.fastjson.JSONObject
         * */
        JSONObject userJSONObject = (JSONObject) JSON.parse(userJson);
        TestHelper.println("JSON.parse({\"username\":\"zhoujian\",\"age\":25})", userJSONObject);

        /*
        1.2.JSON.parseObject(..) 和JSON.parseArray(..)系列方法
        如果没有指定类型，会转换成JSONObject/JSONArray，并支持反序列化设置。
        如果指定了类型，会转换成指定的类，并支持反序列化设置。
        */

        //获取属性的方法,这些get开头的方法很容易控制返回类型，如返回int或double
        Object username = userJSONObject.get("username");
        Integer age = userJSONObject.getInteger("age");
        TestHelper.println("userJSONObject.get(\"username\")", username);
        TestHelper.println("userJSONObject.getInteger(\"age\")", age);

        // 反序列化数组测试
        String arr = "[1,\"zhoujian\"]";
        //注意：不要尝试将数组转换成JSONObject
        JSONArray array = JSON.parseArray(arr);

        Object[] objects = array.toArray();

        // 测试数组元素的类型
        // array.toArray()元素类型: class java.lang.Integer
        TestHelper.println("array.toArray()[0]元素类型", objects[0].getClass());
        // array.toArray()[1]元素类型: class java.lang.String
        TestHelper.println("array.toArray()[1]元素类型", objects[1].getClass());

        // JSONArray也可以像List一样添加元素
        array.add("name");
        array.add(true);
        //[1, zhoujian, name, true]
        TestHelper.println(Arrays.toString(array.toArray()));
    }

    /**
     * 2.测试: 将对象/数组转换成json + 反序列化控制
     *
     * 默认情况下，null属性不会被序列化，String属性为""也会被序列化。
     *
     * 可以使用过滤器 + SerializerFeature进行控制
     *
     * NOTE:
     * SerializerFeature.WriteNullStringAsEmpty: 将null值返回"", 默认情况下无效，被策略过滤了，混合值过滤器无效，建议单独使用值过滤器实现。
     * SerializerFeature.UseSingleQuotes: 将属性和值的单引号替换成''，当和SerializerFeature.QuoteFieldNames同时存在，后者无效，属性和值都是''
     * SerializerFeature.QuoteFieldNames: 属性名使用"",默认.       // 默认情况下,值也用""包围。
     * SerializerFeature.WriteNullListAsEmpty: List字段如果为null,输出为[],而非null  // 默认
     * SerializerFeature.WriteMapNullValue: 序列化null值
     * </pre>
     * */
    @Test
    public void objectAndArrayToJson() {

        // 使用JSONObject创建准备序列化的对象
        String user = "{\"username\":\"zhoujian\",\"age\":25,\"school\":null}";
        JSONObject userObject = JSON.parseObject(user);

        // 输出{"age":25,"username":"zhoujian"} -> JSON反序列化默认会忽略null值属性
        TestHelper.println(userObject);

        /*
         * TEST: 测试null属性和""属性是否会被序列化?
         *
         * 【结论】
         * 默认情况下，null值不会被序列化,""值会序列化。
         * */
        userObject.put("emptyField", "");
        userObject.put("nullField", null);
        // {"age":25,"emptyField":"","username":"zhoujian"} -> 因为默认序列化null值会被值过滤器过滤掉。
        TestHelper.println(userObject);

        // DEMO: 将属性和值的""用''代替
        // UseSingleQuotes：属性和值的""被替换成''
        // {'age':25,'emptyField':'','username':'zhoujian'}
        TestHelper.println(JSONObject.toJSONString(userObject, SerializerFeature.UseSingleQuotes));

        // SerializerFeature.WriteNullStringAsEmpty - 将null值返回为""
        // 无效，不能将null值返回成"",可以使用值过滤器实现
        // 注意：System.out.println(JSONObject.toJSONString(userObject,filter,SerializerFeature.WriteNullStringAsEmpty)); -这种无效
        // System.out.println(JSONObject.toJSONString(userObject,SerializerFeature.WriteNullStringAsEmpty));

        // DEMO: 使用值过滤器将null返回为""
        // {"age":25,"child":"","emptyField":"","nullField":"","school":"","username":"zhoujian"}
        System.out.println(JSONObject.toJSONString(userObject, new ValueFilter() {

            public Object process(Object source, String name, Object value) {
                // TODO Auto-generated method stub
                if (value == null)
                    return "";

                return value;
            }

        }));

        TestHelper.println("-------1------");

        // DEMO: 返回null
        // SerializerFeature.WriteMapNullValue)：返回null的规则
        // {'age':25,'emptyField':'','nullField':null,'school':null,'username':'zhoujian'}
        TestHelper.println(JSONObject.toJSONString(userObject, SerializerFeature.WriteMapNullValue, SerializerFeature.UseSingleQuotes));

        /*
        TEST: 测试QuoteFieldNames 和  UseSingleQuotes优先级

        【结论】
        不管其添加顺序，UseSingleQuotes优先级更高,属性和值都用''包围。
         */
        TestHelper.println(JSONObject.toJSONString(userObject, SerializerFeature.UseSingleQuotes, SerializerFeature.QuoteFieldNames));

        ArrayList<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");

        userObject.put("list", list);
        userObject.put("emptyList", new JSONArray());
        userObject.put("nullList", null);
        // {"age":25,"emptyField":"","emptyList":[],"list":["1","2"],"username":"zhoujian"}
        System.out.println(JSONObject.toJSONString(userObject));

    }

    /**
     * 3.测试: fastjson时间日期序列化控制
     *
     * 【方式】
     * 1.使用@JSONField + JSON.toJSONString(..)控制时间日期的序列化。
     * 2.对属性统一处理:
     * JSON.toJSONStringWithDateFormat(..)
     * 3.修改JSON默认序列化格式,使用规则SerializerFeature.WriteDateUseDateFormat进行控制。
     */
    @Test
    public void dateTimeToJson() {

        /* 1.fastjson JSONObject默认是如何序列化Date对象的?

        【结论】
        序列化成时间戳(ms)
         */
        JSONObject object = new JSONObject();
        object.put("username", "xiaocaiji");
        object.put("lastLoginTime", new Date());

        //object: {"lastLoginTime":1610344260013,"username":"xiaocaiji"}
        TestHelper.println("object", object);

        /*
        2.控制时间日期的序列化
        2.1.使用@JSONField控制时间日期的序列化

        注: 网上查到新版本使用JSONFormat注解,我的是旧版本,使用@JSONField

        【测试过程遇到的问题】
        1.@JSONField + JSON.toJSON()无效
        解决:
        JSON.toJSON()默认是序列化日期时间为时间戳, 所以@JSONField注解无效。
        要使用JSON.toJSONString(String),问题就解决了。
        */
        User user = new User();
        user.setUsername("xiaocaiya");
        user.setPassword("123456");
        user.setLastLoginTime(new Date());
        // JSON.toJSON(String)无效,输出为时间戳,使用toJSONSting问题解决
        TestHelper.println(JSON.toJSONString(user));

        // 2.2.对属性统一处理
        // 需要注意的是: 如果属性有@JSONField注解,则最终序列化格式由@JSONField控制
        TestHelper.println(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));

        // 2.3.修改JSON默认序列化,使用规则进行控制
        JSONObject.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        TestHelper.println(JSON.toJSONString(user, SerializerFeature.WriteDateUseDateFormat));
    }
}

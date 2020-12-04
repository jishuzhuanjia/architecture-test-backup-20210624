package test.frame.json;

import java.util.ArrayList;
import java.util.Arrays;

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
 * json ->Object
 * 1.会根据json是对象还是数组，灵活转换成JSONObject / JSONArray
 * 2.可以很方便向JSONArray/JSONObject 获取和存储值。
 *
 * object->json
 * 更方便的控制序列化。
 * */
public class FastJsonTest {

    @Test
    public void json2Object() {
        String user = "{\"username\":\"zhoujian\",\"age\":25}";

        /* 1.1.JSON -> Object，缺点是不能设置转换Class

        Object JSON.parse(..)：
        会自动判断并将json字符串转换成JSONObject/JSONArray，
        缺点是在后续的赋值中，需要判断返回值的具体类型，是JSONObject/JSONArray，否则会强转失败。如：
        JSONObject object = (JSONObject) JSON.parse(user);

        java.lang.ClassCastException: com.alibaba.fastjson.JSONArray cannot be cast to com.alibaba.fastjson.JSONObject
         * */
        JSONObject object = (JSONObject) JSON.parse(user);

        // 1.2.指定返回JSONObject，前提是对user json字符串足够了解，确保不是数组字符串。
        //JSONObject aObject = JSONObject.parseObject(user);

        //1.3.获取属性的方法,这些get开头的方法很容易控制返回类型，如返回int或double
        object.get("username");
        object.getInteger("age");
        System.out.println(object.get("username") + "，" + object.getInteger("age"));

        String arr = "[1,\"zhoujian\"]";

        //2.1.json -> JSONArray
        JSONArray array = JSON.parseArray(arr);
        //class com.alibaba.fastjson.JSONArray
        System.out.println(JSON.parse(arr).getClass());

        //注意：不要尝试将数组转换成JSONObject
        //java.lang.ClassCastException: com.alibaba.fastjson.JSONArray cannot be cast to com.alibaba.fastjson.JSONObject
        //System.out.println(JSON.parseObject(arr).getClass());
        //转换成Object[]
        array.toArray();
        //也可以像List一样添加元素
        array.add("name");
        array.add(true);
        //[1, zhoujian, name, true]
        System.out.println(Arrays.toString(array.toArray()));

        //class com.alibaba.fastjson.JSONObject
        System.out.println(object.getClass());
    }

    /**
     * 将对象/数组转换成json
     *
     * 默认情况下，null属性不会被序列化，String属性为""也会被序列化。
     *
     * 可以使用过滤器 + SerializerFeature进行控制
     *
     * <pre>
     * NOTE:
     * SerializerFeature.WriteNullStringAsEmpty - 将null值返回为"" - 默认情况下无效，被策略过滤了，混合值过滤器无效，建议单独使用值过滤器实现。
     * SerializerFeature.UseSingleQuotes - 将属性和值的单引号替换成''，当和SerializerFeature.QuoteFieldNames同时存在，后者无效，属性和值都是''
     * SerializerFeature.QuoteFieldNames- 属性名使用"",默认为true
     * SerializerFeature.WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null,默认true
     * </pre>
     * */
    @Test
    public void objectAndArrayToJson() {

        String user = "{\"username\":\"zhoujian\",\"age\":25,\"school\":null}";
        JSONObject userObject = JSON.parseObject(user);

        //1.parse(String)
        // {"age":25,"username":"zhoujian",}
        System.out.println(userObject);
        // {"age":25,"username":"zhoujian"}
        //System.out.println(JSON.parse(user).toString());

        /*
         * 测试null属性能否序列化：null不会被序列化。
         * String为""会被序列化*/

        userObject.put("school", "");
        ////{"age":25,"school":"","username":"zhoujian"}
        System.out.println(userObject);

        userObject.put("child", null);

        //UseSingleQuotes：属性和值的""被替换成''
        //{'age':25,'school':'','username':'zhoujian'}
        System.out.println(JSONObject.toJSONString(userObject, SerializerFeature.UseSingleQuotes));

        //SerializerFeature.WriteNullStringAsEmpty - 将null值返回为""
        //无效，不能将null值返回成"",可以使用值过滤器实现
        //注意：System.out.println(JSONObject.toJSONString(userObject,filter,SerializerFeature.WriteNullStringAsEmpty)); -这种无效
        //System.out.println(JSONObject.toJSONString(userObject,SerializerFeature.WriteNullStringAsEmpty));

        //2.使用值过滤器将null返回为""
        //{"age":25,"child":"","school":"","username":"zhoujian"}
        System.out.println(JSONObject.toJSONString(userObject, new ValueFilter() {

            public Object process(Object source, String name, Object value) {
                // TODO Auto-generated method stub
                if (value == null)
                    return "";

                return value;
            }

        }));

        //3.如何指定返回null?
        //SerializerFeature.WriteMapNullValue)：返回null的规则
        //{"age":25,"child":null,"school":"","username":"zhoujian"}
        System.out.println(JSONObject.toJSONString(userObject, SerializerFeature.WriteMapNullValue, SerializerFeature.UseSingleQuotes));

        System.out.println(JSONObject.toJSONString(userObject, SerializerFeature.QuoteFieldNames, SerializerFeature.UseSingleQuotes));
        ArrayList<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");

        userObject.put("list", new JSONArray());
        //{"age":25,"list":[],"school":"","username":"zhoujian"}
        System.out.println(JSONObject.toJSONString(userObject));

    }

    public String object2JSON() {

        return null;
    }
}

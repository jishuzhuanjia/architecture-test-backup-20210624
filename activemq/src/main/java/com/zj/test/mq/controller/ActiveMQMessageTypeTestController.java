package com.zj.test.mq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/23 15:52
 * @description: 测试activemq默认支持的类型: String, byte array, Map<String,?>, Serializable object.
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年10月23日 16:31:46
 */
@RequestMapping("test/mq/activemq/typetest")
@RestController
public class ActiveMQMessageTypeTestController {
    @Autowired
    Queue queue;
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * String -> ActiveMQTextMessage
     */
    @RequestMapping("sendString")
    public String sendString() {
        return "发送String类型消息成功";
    }

    /**
     * byte[] -> ActiveMQBytesMessage
     */
    @RequestMapping("sendByteArray")
    public String sendByteArray() {
        byte[] bytes = new byte[3];
        bytes[0] = 1;
        bytes[1] = 2;
        bytes[2] = 3;
        jmsMessagingTemplate.convertAndSend(queue, bytes);
        return "发送byte[]类型消息成功";
    }

    /**
     * Map<String,?> -> ActiveMQMapMessage
     * <p>
     * map要求:
     * key: 只能是String、基础类型、包装类、Map、List
     * 其他类型会报错
     * <p>
     * map不能传递复杂的类型，因此，如果需要传递复杂的类型，请使用PO类进行传递。
     */
    @RequestMapping("sendMap")
    public String sendMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "mapusername");

        // map value不能为自定义po类型
        //UserPO userPO =new UserPO();
        //userPO.setUsername("pousername");
        // javax.jms.MessageFormatException: Only objectified primitive objects, String, Map and List types are allowed but was: com.zj.test.mq.controller.UserPO@45c92c05 type: class com.zj.test.mq.controller.UserPO
        //map.put("po",userPO);

        // value为List类型测试
        // 成功: 从queue消息队列获取新消息:{list=[1, 2, 3], username=mapusername}
        map.put("listInt", Arrays.asList(1, 2, 3));

        // value为List类型，但是List存储PO类
        // 失败：java.io.IOException: Object is not a primitive: com.zj.test.mq.controller.UserPO@8df4d13
        /*List<UserPO> userPOList = new ArrayList<UserPO>();
        UserPO userPO =new UserPO();
        userPO.setUsername("pousername");
        userPOList.add(userPO);
        map.put("listObject", userPOList);*/

        // 包装类测试: Integer
        // 成功
        map.put("integer", Integer.valueOf(1));

        jmsMessagingTemplate.convertAndSend(queue, map);
        return "发送Map类型消息成功";
    }
}
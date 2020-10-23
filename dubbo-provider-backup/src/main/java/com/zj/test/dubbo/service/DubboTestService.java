package com.zj.test.dubbo.service;

/**
 * 本次调用zookepper中的dubbo接口，使用的工具是telnet命令: telnet 127.0.0.1 2181
 * 此时的时候没有发生问题
 *
 * 但是一个礼拜后，我再次测试的时候telnet不好使了: 可以连接上zookeeper，但是一直没有响应内容，
 * 窗口黑的~因此这次无法进行测试，所以我现在改名jmeter进行dubbo接口测试。
 * */
public interface DubboTestService {

    /*dubbo测试PO类*/
    public static class DubboTestPO {
        String username;
        String password;
        String age;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "DubboTestPO{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }

    /*1.测试：dubbo简单类型接口的调用：调用println(String string)
     *println(String string)dubbo接口调用：
     *invoke com.zj.test.dubbo.service.DubboTestService.println("hahaha")
     *
     *调用结果:OK
     *
     * 001结论：dubbo接口的调用就像是调用普通的方法一样，传递的参数要和方法定义的一样。如调用println(String string,int count)：
        dubbo>invoke com.zj.test.dubbo.service.DubboTestService.println("hello")
        No such method println in service com.zj.test.dubbo.service.DubboTestService
        dubbo>invoke com.zj.test.dubbo.service.DubboTestService.println("hello",5)              //调用成功
        null
        elapsed: 1 ms.
    * */
    public void println(String string, int count);

    /*2.测试：dubbo调用带有PO引用类型的接口
    *
    * 调用过程1：
    * dubbo>invoke com.zj.test.dubbo.service.DubboTestService.printJSON("{username:'zhoujian'})
      null
      dubbo>invoke com.zj.test.dubbo.service.DubboTestService.printJSON({'username':'zhoujian'})
      "invoke dubbo printJSON() success!"
      elapsed: 0 ms.

      调用过程2：
      dubbo>invoke com.zj.test.dubbo.service.DubboTestService.printJSON({username:'zhoujian'},101)
      "invoke dubbo printJSON() success!"
      elapsed: 0 ms.
      dubbo>

      002.结论：调用po引用类型的dubbo接口，json字符串不用""包围，直接用{}包围即可。
      003.json属性名可以不用引号包围。


    * */
    public String printJSON(DubboTestPO loginingUser, Integer loginTimes);
}

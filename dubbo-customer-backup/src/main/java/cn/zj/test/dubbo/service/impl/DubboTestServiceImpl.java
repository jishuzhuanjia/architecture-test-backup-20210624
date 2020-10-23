package cn.zj.test.dubbo.service.impl;

/* @author: zhoujian
 * @create-time: 2020/9/29 11:16
 * @description:
 * @version: 1.0
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.zj.test.dubbo.service.DubboTestService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class DubboTestServiceImpl {

    /*xml方式配置接口订阅: 这里的id和interface类型都可作为后续属性绑定。
    <dubbo:reference id="iHTFundBusiService"
    interface="com.htsc.uf.api.IHTFundBusiService"
    timeout="30000" check="false"/>*/

    /*001.@Reference和xml方式中dubbo:reference的区别：
    @Reference: 不仅会完成对应接口的实例化，而且会绑定到属性上。
    <dubbo:reference...>: 仅仅会完成接口的实例化，但是需要手动绑定到属性上，如使用@Autowired。
    */

    /*002.【使用注意点】
    1.如果provider暴露的接口指定了version,则此处必须指定version,且必须与provider提供的version一致，否则接口实例为null。
    2.如果provider暴露的接口没有指定version，则此处不能指定version，否则也会因找不到而导致接口实例为null。


    check=true: 对于非懒加载，会检查依赖的服务是否存在，不存在就会报错，从而应用启动失败。
                而对于懒加载的bean而言，check=true意义不大。

    check=false: 总是能拿到正确的引用，当然也可能为null，但是服务恢复的时候，可以正常使用。
    【应用场景】
    应用于要求强制检查依赖服务的时候。
    */
    @Reference(timeout = 5000, check = true)
    DubboTestService dubboTestService;

    public void test1() {
        if (dubboTestService != null)
            dubboTestService.println("asdsadas", 5);
        else {
            System.out.println("获取订阅接口失败~");
        }
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("如果看到这句话，说明我不是懒人：懒加载哦~");
    }
}

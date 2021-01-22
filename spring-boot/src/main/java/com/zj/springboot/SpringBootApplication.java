package com.zj.springboot;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/21 17:26
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

import org.springframework.boot.SpringApplication;

// SpringBootApplication是一个组合注解,也可以使用下面的多个注解来达到相同的效果:
/*
@Configuration
@EnableAutoConfiguration
@ComponentScan
*/
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

    public static void main(String[] args) {

        /**
         * 1.默认banner: Spring Boot的banner。
         *
         * 如果包根目录下有banner.txt文件，则会替代默认banner。
         *
         */
        SpringApplication.run(SpringBootApplication.class,args);

        /**
         * 2.关闭banner显示
         */
        /*
        方式1

        SpringApplication application = new SpringApplication(SpringBootApplication.class);
        // 旧版本: application.setShowBanner(false);
        // 新版本关闭
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);*/

        /*
        2.2.方式2: fluent api
         */
        // 旧版本:new SpringApplicationBuilder(SpringBootApplication.class).showBanner(false).run(args);
        // new SpringApplicationBuilder(SpringBootApplication.class).bannerMode(Banner.Mode.OFF).run(args);

    }
}

package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description
 * @Package PACKAGE_NAME
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020/8/27 9:17
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
//这是一个spring boot启动类
    //指定扫描本包，指定扫描一个
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication//{json,形式，注入}
@MapperScan(basePackages = "com.dao")
public class KillOrderApp {
    public static void main(String[] args) {
//          启动运行
        SpringApplication.run(KillOrderApp.class,args);
    }
}

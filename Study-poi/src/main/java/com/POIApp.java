package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description
 * @Package com
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020-10-19 15:25
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
@EnableScheduling
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class POIApp {
    public static void main(String[] args) {
//          启动运行
        SpringApplication.run(POIApp.class,args);
    }
}

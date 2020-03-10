package com.grain.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/3/9 17:43
 * @description：
 * @modified By：
 * @version: $
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class StatisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class, args);
    }
}

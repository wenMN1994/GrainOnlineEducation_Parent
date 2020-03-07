package com.grain.vod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/3/7 17:23
 * @description：Swagger2配置文件
 * @modified By：
 * @version: $
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket webApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .build();

    }

    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("谷粒学院-阿里云视频点播API文档")
                .description("本文档描述了阿里云视频点播服务接口定义")
                .version("1.0")
                .contact(new Contact("Dragon Wen", "www.dragonwen.cn", "18475536452@163.com"))
                .build();
    }

}

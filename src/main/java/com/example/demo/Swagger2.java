package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration//@Configuration注解，让spring来加载该配置
@EnableSwagger2//@EnableSwagger2注解来启动Swagger
public class Swagger2 {
    //2再写上面
    @Bean//此注解会加载rest风格
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.Controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //先写下面
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("汇萃在线答题系统API文档")
                .description("汇萃在线答题系统有接口描述")
                .contact(new Contact("zxhw", "", "1042450015@qq.com"))
                .version("1.0")
                .build();
    }

}

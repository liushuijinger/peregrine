package com.shuijing.peregrine.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/04/26
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                                       .name("Accept-Language")
                                       .required(false)
                                       .in(ParameterType.HEADER)
                                       .build());
        return new Docket(DocumentationType.OAS_30)
                        .apiInfo(apiInfo())
                        .globalRequestParameters(parameters)
                        .select()
                        .apis(RequestHandlerSelectors.basePackage("com.shuijing.peregrine.controller"))
                        .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Peregrine RESTful APIs").description("Peregrine 的 RESTFul 接口文档说明")
                        .contact(new Contact("刘水镜", "https://liushuijinger.blog.csdn.net", "liushuijinger@163.com"))
                        .termsOfServiceUrl("https://liushuijinger.blog.csdn.net").version("1.0").build();
    }
}

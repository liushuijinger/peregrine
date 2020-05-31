/*
 * Copyright (c) 2017-2018, Cardinal Operations and/or its affiliates. All rights reserved.
 * CARDINAL OPERATIONS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.shuijing.peregrine.common.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/05/18
 */
@Component
public class WebMvcAppConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
                        .addResourceLocations("classpath:/META-INF/resources/", "/static", "/public");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}

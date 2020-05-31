/*
 * Copyright (c) 2018, Cardinal Operations and/or its affiliates. All rights reserved.
 * CARDINAL OPERATIONS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.shuijing.peregrine.common.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/05/18
 */
@Configuration
@ConditionalOnClass({JSON.class})
public class FastJsonHttpMessageConvertersConfig {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                                             SerializerFeature.WriteMapNullValue,
                                             SerializerFeature.WriteNullStringAsEmpty,
                                             SerializerFeature.WriteNullListAsEmpty
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }
}
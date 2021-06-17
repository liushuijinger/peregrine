/*
 * Copyright (c) ©2021, Cardinal Operations and/or its affiliates. All rights reserved.
 * CARDINAL OPERATIONS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.shuijing.peregrine.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2021/06/16
 */
@Data
@Component
@ConfigurationProperties(prefix = "mail")
public class MailConfig {

    private List<MailProperties> configs;

    @Data
    public static class MailProperties {

        /**
         * 密码
         */
        private String username;

        /**
         * 密码
         */
        private String password;

        /**
         * host
         */
        private String host;

        /**
         * 端口
         */
        private Integer port;

        /**
         * 协议
         */
        private String protocol;

        /**
         * 默认编码
         */
        private String defaultEncoding;

    }

}
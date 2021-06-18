/*
 * Copyright (c) ©2021, Cardinal Operations and/or its affiliates. All rights reserved.
 * CARDINAL OPERATIONS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.shuijing.peregrine.common.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2021/06/16
 */
@Slf4j
@Component
@AllArgsConstructor
public class MailSenderBuilder {

    private final MailConfig mailConfig;

    private final Map<String, JavaMailSenderImpl> senderMap;

    /**
     * 初始化 sender
     */
    @PostConstruct
    public void buildMailSender(){
        List<MailConfig.MailProperties> mailConfigs = mailConfig.getConfigs();
        mailConfigs.forEach(mailProperties -> {

            // 邮件发送者
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setDefaultEncoding(mailProperties.getDefaultEncoding());
            javaMailSender.setHost(mailProperties.getHost());
            javaMailSender.setPort(mailProperties.getPort());
            javaMailSender.setProtocol(mailProperties.getProtocol());
            javaMailSender.setUsername(mailProperties.getUsername());
            javaMailSender.setPassword(mailProperties.getPassword());

            Map<String, String> propertyMap = new HashMap<>();
            propertyMap.put("mail.smtp.auth", "true");
            propertyMap.put("mail.smtp.starttls.required", "true");
            propertyMap.put("mail.smtp.starttls.enable", "true");
            propertyMap.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            Properties properties = new Properties();
            properties.putAll(propertyMap);
            javaMailSender.setJavaMailProperties(properties);

            // 添加数据
            senderMap.put(mailProperties.getUsername(), javaMailSender);
        });
    }

    /**
     * 获取MailSender
     * @return CustomMailSender
     */
    public JavaMailSenderImpl getSender(String from){
        if(senderMap.isEmpty()){
            buildMailSender();
        }

        return senderMap.get(from);
    }

    /**
     * 清理 sender
     */
    public void clear(){
        senderMap.clear();
    }

}
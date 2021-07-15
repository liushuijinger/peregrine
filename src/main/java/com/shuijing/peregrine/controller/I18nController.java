package com.shuijing.peregrine.controller;

import com.shuijing.peregrine.common.base.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @since 2020-05-31
 */
@RestController
@RequestMapping("/i18n")
@Api(value = "国际化",tags = "国际化")
public class I18nController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public Result<String> get() {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("message.language", null, locale);
        return Result.success(locale.getLanguage());
    }
}

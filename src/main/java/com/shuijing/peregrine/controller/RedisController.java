package com.shuijing.peregrine.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2021-06-21
 */

@Api
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/hello")
    public Object hello() {
        stringRedisTemplate.opsForValue().set("hello","world");
        redisTemplate.opsForHash().put("key","hk","hv");
        return redisTemplate.opsForHash().get("key", "hk");
    }

    @GetMapping("/jdkSerializer")
    public void jdkSerializer() {
        redisTemplate.opsForValue().set("key","value");
    }

    @GetMapping("/expire")
    public void setExpire(String key) {
        stringRedisTemplate.expire(key, 30, TimeUnit.SECONDS);
    }

    @DeleteMapping("/delete")
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    @GetMapping("/get")
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
package com.shuijing.peregrine.service.impl;

import com.shuijing.peregrine.entity.User;
import com.shuijing.peregrine.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liushuijing (shuijing@shanshu.ai)
 * @date 2021-06-18
 */
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService service;

    @Test
    void validParam() {
        User user = new User().setAge(1);
        service.validParam(user);
    }
}
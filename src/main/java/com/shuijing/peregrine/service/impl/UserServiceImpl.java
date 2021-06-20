package com.shuijing.peregrine.service.impl;

import com.shuijing.peregrine.entity.User;
import com.shuijing.peregrine.mapper.UserMapper;
import com.shuijing.peregrine.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @since 2020-05-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Boolean validParam(@Valid User user) {
        return true;
    }
}

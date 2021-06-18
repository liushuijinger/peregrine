package com.shuijing.peregrine.service;

import com.shuijing.peregrine.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @since 2020-05-31
 */
public interface UserService extends IService<User> {

    Boolean validParam(@Validated(Update.class) User user);
}

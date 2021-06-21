package com.shuijing.peregrine.controller;

import com.shuijing.peregrine.common.base.ApiMessage;
import com.shuijing.peregrine.common.base.Assert;
import com.shuijing.peregrine.common.base.Result;
import com.shuijing.peregrine.service.UserService;
import com.shuijing.peregrine.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
@RequestMapping("/user")
@Api(value = "User对象",tags = "用户信息")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "查询", response = User.class)
    @GetMapping(value = "/{id}")
    public Result<User> info(@PathVariable Integer id) {
      User user = userService.getById(id);
      Assert.assertNotNull(user, ApiMessage.NOT_FOUND);
      return Result.success(user);
    }

    @ApiOperation(value = "新增")
    @PostMapping
    public Result<Boolean> add(@RequestBody User user) {
      userService.save(user);
      return Result.success();
    }

    @ApiOperation(value = "修改")
    @PutMapping
    public Result<Boolean> modify(@RequestBody User user) {
      userService.updateById(user);
      return Result.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> remove(@PathVariable Integer id) {
      userService.removeById(id);
      return Result.success();
    }
}

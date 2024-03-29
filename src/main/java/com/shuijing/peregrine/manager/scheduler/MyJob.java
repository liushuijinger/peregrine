package com.shuijing.peregrine.manager.scheduler;

import com.alibaba.fastjson.JSON;
import com.shuijing.peregrine.entity.User;
import com.shuijing.peregrine.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2021-10-07
 */
@Slf4j
@Component
public class MyJob extends QuartzJobBean {

    @Autowired
    private UserService userService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<User> userList = userService.list();
        log.info("my job");
        log.info(JSON.toJSONString(userList));
    }
}

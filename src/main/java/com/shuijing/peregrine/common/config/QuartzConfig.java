package com.shuijing.peregrine.common.config;

import com.shuijing.peregrine.manager.scheduler.MyJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2021-10-07
 */
@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail myJobDetail() {
        return JobBuilder.newJob(MyJob.class)
                        .withIdentity(MyJob.class.getSimpleName(), "myjob")
                        .storeDurably()
                        .build();
    }

    @Bean
    public Trigger myTrigger() {
        return TriggerBuilder.newTrigger()
                        .forJob(myJobDetail())
                        .withIdentity(MyJob.class.getSimpleName(), "myjob")
                        .startNow()
                        .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? "))
                        .build();
    }
}

package com.shuijing.peregrine.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/05/31
 */
@Configuration
@MapperScan("com.shuijing.peregrine.mapper")
public class MybatisPlusConfig {
	/**
	 * 分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		// 开启 count 的 join 优化,只针对 left join !!!
		return new PaginationInterceptor().setCountSqlParser(new JsqlParserCountOptimize(true));
	}
}

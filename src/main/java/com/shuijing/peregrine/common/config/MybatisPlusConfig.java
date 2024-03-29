package com.shuijing.peregrine.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

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
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

		// 动态表名
		DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
		dynamicTableNameInnerInterceptor.setTableNameHandler((sql, tableName) -> {
			// 获取参数方法
			Map<String, Object> paramMap = RequestDataHelper.getRequestData();

			return tableName + paramMap.get("TABLE_POSTFIX");
		});
		interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);

		// 分页
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
		return interceptor;
	}

}

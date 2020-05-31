package com.shuijing.peregrine.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/05/13
 */
@Slf4j
@Component
public class CustomerMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		log.info("start insert fill ....");
		this.strictInsertFill(metaObject, "creator", String.class, "admin");
		this.strictUpdateFill(metaObject, "updator",  String.class, "admin");
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		log.info("start update fill ....");
		this.strictUpdateFill(metaObject, "updator",  String.class, "admin");
	}

}


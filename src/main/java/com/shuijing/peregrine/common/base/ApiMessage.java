package com.shuijing.peregrine.common.base;

import lombok.Getter;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/05/18
 */
@Getter
public enum ApiMessage {
	UNKNOWN_ERROR(-1, "未知错误！"),
	ERROR(500, "系统错误"),
	SUCCESS(0, "操作成功！"),
	NOT_FOUND(404, "数据不存在！"),
	;
	private Integer code;
	private String content;

	ApiMessage(Integer code, String message) {
		this.code = code;
		this.content = message;
	}
}


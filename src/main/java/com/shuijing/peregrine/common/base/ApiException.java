package com.shuijing.peregrine.common.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/05/18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiException extends RuntimeException{

	private Integer code;

	private String message;

	public ApiException(ApiMessage message) {
		super(message.getContent());
		this.code = message.getCode();
		this.message = message.getContent();
	}

	public ApiException(String message) {
		super(message);
		this.code = ApiMessage.ERROR.getCode();
		this.message = message;
	}

}


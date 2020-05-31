package com.shuijing.peregrine.common.base;

import lombok.Data;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/05/18
 */
@Data
public class PeregrineException extends RuntimeException{

	private Integer code;

	public PeregrineException(MessageEnum messageEnum) {
		super(messageEnum.getMessage());
		this.code = messageEnum.getCode();
	}

}

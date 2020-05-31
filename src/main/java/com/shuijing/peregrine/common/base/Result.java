package com.shuijing.peregrine.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/05/18
 */
@Data
@AllArgsConstructor
public class Result<T> {

	private Integer code;

	private String message;

	private T data;

	public static <T> Result<T> success() {
		return success(null);
	}

	public static <T> Result<T> success(T data) {
		ApiMessage success = ApiMessage.SUCCESS;
		return new Result<>(success.getCode(), success.getContent(), data);
	}

	public static <T> Result<T> error() {
		return error(ApiMessage.ERROR);
	}

	public static <T> Result<T> error(ApiMessage apiMessage) {
		return new Result<>(apiMessage.getCode(),apiMessage.getContent(),null);
	}

	public static <T> Result<T> error(String message) {
		return error(message, ApiMessage.ERROR.getCode());
	}

	protected static <T> Result<T> error(String message,Integer code) {
		return new Result<>(code,message,null);
	}
}


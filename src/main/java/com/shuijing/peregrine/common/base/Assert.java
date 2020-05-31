package com.shuijing.peregrine.common.base;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/05/27
 */
public class Assert {
	public static void assertNotNull(Object obj, ApiMessage message){
		if (obj == null) {
			newException(message);
		}
	}

	public static void newException(ApiMessage message) {
		throw new ApiException(message);
	}
}

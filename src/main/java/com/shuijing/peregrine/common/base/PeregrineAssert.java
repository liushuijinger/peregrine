package com.shuijing.peregrine.common.base;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/05/27
 */
public class PeregrineAssert {
	public static void assertNotNull(Object obj, MessageEnum messageEnum){
		if (obj == null) {
			newException(messageEnum);
		}
	}

	public static void newException(MessageEnum messageEnum) {
		throw new PeregrineException(messageEnum);
	}
}

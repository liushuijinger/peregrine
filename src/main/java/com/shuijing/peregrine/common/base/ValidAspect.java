package com.shuijing.peregrine.common.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2021/06/16
 */
@Slf4j
@Aspect
@Component
public class ValidAspect {

	@Pointcut("execution(public * com.shuijing.peregrine.service.*.*(..))")
	public void pointCut() {
	}

	@Before(value = "pointCut()")
	public void before(JoinPoint joinPoint) {

		Object[] args = joinPoint.getArgs();

		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

		// 需要校验的方法实例
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		ExecutableValidator validator = factory.getValidator().forExecutables();


		// 获得校验结果 Set 集合，有多少个字段校验错误 Set 的大小就是多少
		Set<ConstraintViolation<Object>> constraintViolationSet = validator
						.validateParameters(joinPoint.getThis(), method, args);

		int size = constraintViolationSet.size();
		log.info("非法参数校验结果条数: {}", size);
		if (CollectionUtils.isNotEmpty(constraintViolationSet)) {
			constraintViolationSet.forEach(cons -> {
				log.warn("参数错误：{}", cons.getMessage());
			});
			throw new ApiException(constraintViolationSet.iterator().next().getMessage());
		}
	}
}

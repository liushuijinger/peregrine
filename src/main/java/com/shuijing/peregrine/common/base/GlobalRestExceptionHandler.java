package com.shuijing.peregrine.common.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/05/27
 */
@Slf4j
@RestControllerAdvice
public class GlobalRestExceptionHandler {

	@ExceptionHandler({ Exception.class })
	public Result<Boolean> globalException(Exception e, HttpServletResponse response) {
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		log.error("执行逻辑出现异常.", e);
		return Result.error();
	}

	@ExceptionHandler({ ApiException.class })
	public Result<Boolean> apiException(ApiException e, HttpServletResponse response) {
		response.setStatus(HttpStatus.OK.value());
		log.error("执行逻辑出现异常.", e);
		return Result.error(e.getMessage(),e.getCode());
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public Result<Boolean> validException(MethodArgumentNotValidException e, HttpServletResponse response) {
		response.setStatus(HttpStatus.OK.value());
		log.error("执行逻辑出现异常.", e);
		List<ObjectError> errors =e.getBindingResult().getAllErrors();
        StringBuffer errorMsgBuffer = new StringBuffer();
        errors.forEach(x -> errorMsgBuffer.append(x.getDefaultMessage()).append(";"));
		return Result.error(errorMsgBuffer.toString());
	}
}

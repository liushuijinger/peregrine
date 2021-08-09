package com.shuijing.peregrine.common.config.security;

import com.alibaba.fastjson.JSONObject;
import com.shuijing.peregrine.common.base.Result;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author liushuijing (shuijing@shanshu.ai)
 * @date 2021-08-09
 */
@Component
public class CustomerAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(JSONObject.toJSONString(Result.error("没有权限！")));
    }
}

package com.tianming.interceptor;

import com.tianming.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
//@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        //2.判断是否是登录请求，如果路径中包含/login，则放行
        if (requestURI.contains("/login")){
            log.info("登录请求......,放行");
            return true;
        }
        //3.获取请求头中的token
        String token = request.getHeader("token");

        //4.验证token，如果不存在则说明用户没有登录，则返回错误信息（响应401）
        if (token == null || token.isEmpty()){
            log.info("令牌为空，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //5.如果token存在，则校验令牌，如果令牌验证失败，则返回错误信息（响应401）
        try {
            JwtUtils.parseToken(token);
        }catch (Exception e)
        {
            log.info("令牌验证失败，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return  false;
        }

        //6.如果令牌验证成功，则放行
        log.info("令牌验证成功，放行");
        return true;
    }
}

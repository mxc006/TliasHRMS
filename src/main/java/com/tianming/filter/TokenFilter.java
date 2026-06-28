package com.tianming.filter;

import com.tianming.utils.CurrentHolder;
import com.tianming.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取请求路径
        String requestURI = request.getRequestURI();

        //2.判断是否是登录请求，如果路径中包含/login，则放行
        if (requestURI.contains("/login")){
            log.info("登录请求......,放行");
            filterChain.doFilter(request,response);
            return;
        }
        //3.获取请求头中的token
        String token = request.getHeader("token");

        //4.验证token，如果不存在则说明用户没有登录，则返回错误信息（响应401）
        if (token == null || token.isEmpty()){
            log.info("令牌为空，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //5.如果token存在，则校验令牌，如果令牌验证失败，则返回错误信息（响应401）
        try {
            Claims claims =JwtUtils.parseToken(token);
            //将当前登录员工ID存入ThreadLocal
            Integer empid =Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empid);
            log.info("当前登录员工ID:{},将其存入ThreadLocal",empid);
        }catch (Exception e)
            {
            log.info("令牌验证失败，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //6.如果令牌验证成功，则放行
        log.info("令牌验证成功，放行");
        filterChain.doFilter(request,response);

        //7.删除ThreadLocal中的数据
        CurrentHolder.remove();
    }
}

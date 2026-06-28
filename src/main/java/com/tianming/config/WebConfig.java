package com.tianming.config;

import com.tianming.interceptor.DemoInterceptor;
import com.tianming.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*配置类*/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*@Autowired
    private DemoInterceptor demoInterceptor;*/

   /* @Autowired
    private TokenInterceptor tokenInterceptor;*/
    /*添加拦截器*/
   // @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
      /*  registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login");*/

    }
}

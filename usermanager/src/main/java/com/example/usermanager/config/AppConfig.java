package com.example.usermanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName:AppConfig
 * Package:com.example.usermanager.config
 * Description:
 *
 * @Author:HP
 * @date:2021/7/29 10:43
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {
    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/js/**.js")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/css/**.css")
                .excludePathPatterns("/fonts/**")
                .excludePathPatterns("/login.html")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/regin.html")
                .excludePathPatterns("/user/regin")
        ;
    }
}

package com.baojk.we.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/18
 */
@Component
public class WebMvcAppConfig implements WebMvcConfigurer {
    @Autowired
    private AccessVerifyInterceptor accessVerifyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessVerifyInterceptor).addPathPatterns("/api/**");
    }
}
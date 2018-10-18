package com.baojk.we.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/18
 */
@Component
public class WebMvcAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private AccessVerifyInterceptor accessVerifyInterceptor;

    //增加拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessVerifyInterceptor)    //指定拦截器类
                        .addPathPatterns("/api/**");        //指定该类拦截的url
        super.addInterceptors(registry);
    }
}
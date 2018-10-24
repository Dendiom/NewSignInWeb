package com.lucky.NewSignInWeb.config;

import com.lucky.NewSignInWeb.interceptor.LogInterceptor;
import com.lucky.NewSignInWeb.interceptor.SessionInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/*");
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/main/*");
    }
}

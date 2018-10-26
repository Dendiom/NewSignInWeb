package com.lucky.NewSignInWeb.config;

import com.lucky.NewSignInWeb.interceptor.LogInterceptor;
import com.lucky.NewSignInWeb.interceptor.SessionInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class WebConfig implements WebMvcConfigurer {

    @Bean(name = "sessionInterceptor")
    public SessionInterceptor getSessionInterceptor() {
        return new SessionInterceptor();
    }

    @Bean(name = "logInterceptor")
    public LogInterceptor getLogInterceptor() {
        return new LogInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLogInterceptor()).addPathPatterns("/*");
        registry.addInterceptor(getSessionInterceptor()).addPathPatterns("/main/*");
    }
}

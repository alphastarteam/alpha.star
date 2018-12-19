package com.example.demoarticle;

import com.example.demoarticle.HandlerInterceptor.GlobalHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfigurerAdapter extends WebMvcConfigurationSupport {

    @Bean
    public GlobalHandlerInterceptor getGlobalHandlerInterceptor(){
        return new GlobalHandlerInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(getGlobalHandlerInterceptor());
    }
}

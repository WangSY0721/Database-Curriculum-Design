package com.wang.config;

import com.wang.head.interceptor.AuthInterceptor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC的所有配置
 * Configuration:配置文件的内容
 * @author Zjx
 */
@Log4j2
@Configuration
public class HeadMvcConfig implements WebMvcConfigurer {
    @Autowired
    private AuthInterceptor authInterceptor;

    /**
     * 拦截器的配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("==registry=={}", registry);
        /* 把自定义的拦截器放到SpringBoot中
         * addPathPatterns:哪些路径要拦截;/back/**
         * excludePathPatterns:哪些路径要排除;/back/aa
         *  */
        registry.addInterceptor(this.authInterceptor).addPathPatterns("/head/**");
    }
}

package com.thc.sprbasic2025.config;

import com.thc.sprbasic2025.interceptor.DefaultInterceptor;
import com.thc.sprbasic2025.util.TokenFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    final TokenFactory tokenFactory;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DefaultInterceptor(tokenFactory))
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/auth", "/api/user/signup", "/api/user/login");
    }
}

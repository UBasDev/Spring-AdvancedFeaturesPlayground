package org.ucbdev.Core.Application.Registration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.ucbdev.Core.Application.Interceptor.CustomLoggerInterceptor1;

@Configuration
public class AddInterceptorRegistrations implements WebMvcConfigurer {
    private final CustomLoggerInterceptor1 customLoggerInterceptor1;
    public AddInterceptorRegistrations(CustomLoggerInterceptor1 customLoggerInterceptor1){
        this.customLoggerInterceptor1 = customLoggerInterceptor1;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customLoggerInterceptor1);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

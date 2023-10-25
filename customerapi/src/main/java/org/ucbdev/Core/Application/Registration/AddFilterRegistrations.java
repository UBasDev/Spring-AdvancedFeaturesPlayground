package org.ucbdev.Core.Application.Registration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ucbdev.Core.Application.Middleware.LoggingFilter1;
import org.ucbdev.Core.Application.Middleware.LoggingFilter2;

@Configuration
public class AddFilterRegistrations {
    @Bean
    public FilterRegistrationBean<LoggingFilter1> loggingFilter1(){
        FilterRegistrationBean<LoggingFilter1> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new LoggingFilter1());
        registrationBean.addUrlPatterns("/api/v1/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean<LoggingFilter2> loggingFilter2(){
        FilterRegistrationBean<LoggingFilter2> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new LoggingFilter2());
        registrationBean.addUrlPatterns("/api/v1/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}

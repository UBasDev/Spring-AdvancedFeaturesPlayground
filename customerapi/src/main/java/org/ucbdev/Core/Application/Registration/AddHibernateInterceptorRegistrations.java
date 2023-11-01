package org.ucbdev.Core.Application.Registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;
import org.ucbdev.Core.Application.Interceptor.HibernateInterceptor1;

import java.util.Map;

@Configuration
public class AddHibernateInterceptorRegistrations implements HibernatePropertiesCustomizer {
    private final HibernateInterceptor1 hibernateInterceptor1;
    public AddHibernateInterceptorRegistrations(HibernateInterceptor1 hibernateInterceptor1){
        this.hibernateInterceptor1 = hibernateInterceptor1;
    }
    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.session_factory.interceptor", hibernateInterceptor1);
    }
}

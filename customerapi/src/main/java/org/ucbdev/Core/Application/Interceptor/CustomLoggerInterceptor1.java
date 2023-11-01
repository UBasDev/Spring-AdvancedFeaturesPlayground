package org.ucbdev.Core.Application.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

@Component
public class CustomLoggerInterceptor1 extends WebRequestHandlerInterceptorAdapter {
    private static Logger log = LoggerFactory.getLogger(CustomLoggerInterceptor1.class);
    public CustomLoggerInterceptor1(WebRequestInterceptor requestInterceptor) {
        super(requestInterceptor);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String forwardedIpAdress = request.getHeader("X-FORWARDED-FOR");
        String currentIpFromHeader = forwardedIpAdress == null ? request.getRemoteAddr() : forwardedIpAdress ;
        log.info("Method: {}\nURI: {}\nIP Address: {}", request.getMethod(), request.getRequestURI(), currentIpFromHeader);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}

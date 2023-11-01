package org.ucbdev.Core.Application.Middleware;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoggingFilter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //System.out.printf("Query1: %s", request.getParameter("query1"));
        //System.out.printf("Header1: %s", httpRequest.getHeader("header1"));
        System.out.println("Request received at " + System.currentTimeMillis());
        System.out.println("Filter1 works");

        // Continue the request-response chain

        var x1 = httpRequest.getMethod();
        var x2 = httpRequest.getRequestURI();
        var x3 = httpRequest.getContentType();
        var x4 = httpRequest.getSession();
        var x5 = httpRequest.getParameter("query1");
        var x6 = httpRequest.getHeader("header1");
        var x7 = httpRequest.getContextPath();
        Cookie[] x8 = httpRequest.getCookies();
        /*
        for(Cookie currentCookie : x8){

        }
        */
        /*
        Stream<Cookie> x9 =Arrays.stream(x8).filter(c -> c.getName().equals("cookie1"));
        Optional<Cookie> x10 = x9.findFirst();
        if(x10.isPresent()){
            var cookie1 = x10.get();
            var x11 = cookie1.getName();
            var x12 = cookie1.getValue();
            var x13 = cookie1.getAttributes();
            var x14 = cookie1.getDomain();
            var x15 = cookie1.getMaxAge();
            var x16 = cookie1.getPath();
            var x17 = cookie1.getSecure();
            var x18 = cookie1.isHttpOnly();
            cookie1.setAttribute("attr1", "value1");
            cookie1.setDomain("localhost");
            cookie1.setPath("path1");
            cookie1.setSecure(true);
            cookie1.setValue("value1_1");
            cookie1.setHttpOnly(true);
            cookie1.setMaxAge(600);
            httpResponse.addCookie(cookie1);
        }
        */
        /*
        httpResponse.setStatus(HttpStatus.CREATED.value());
        httpResponse.addHeader("header1", "value1");
        httpResponse.setContentType("text/plain");
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.getOutputStream().println("abc");
        httpResponse.getOutputStream().write("def".getBytes());
        */
        chain.doFilter(request, httpResponse);

        // Post-processing logic
        System.out.println("Response sent at " + System.currentTimeMillis());
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

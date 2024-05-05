package com.game.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

//@WebFilter("/*") // 这里的 "/*" 表示拦截所有请求
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filter初始化时调用，通常用于资源的加载或一次性工作
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 设置CORS响应头
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Origin", "*"); // 允许任何源
        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"); // 允许的HTTP方法
        httpResponse.setHeader("Access-Control-Max-Age", "3600"); // 预检请求的缓存时间
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, Authorization"); // 允许的HTTP请求头

        // 如果HTTP请求是OPTIONS，则返回状态码200
        if ("OPTIONS".equalsIgnoreCase(((javax.servlet.http.HttpServletRequest) request).getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            // 传递给下一个过滤器或目标资源
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Filter销毁时调用，通常用于释放资源
    }
}
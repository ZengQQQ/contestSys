package com.game.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.game.utils.JWTUtils;
import com.game.utils.Result;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 管理员过滤器

 */

//@WebFilter("/user/*")
public class userFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filter初始化时调用，通常用于资源的加载或一次性工作
    }

    @Override
    public void destroy() {
        System.out.println("AdminFilter destroy");
    }

    @Override
    public void doFilter(ServletRequest request1, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request =(HttpServletRequest)request1;
        // 返回的结果
        Result result = null;

        // 获取请求头中的token
        String token = request.getHeader("token");

        // token存在
        if (token == null) {
            result = Result.unAuth(null);
        }

        // token合法
        DecodedJWT decodedJWT = JWTUtils.decodedJWT(token);
        if (decodedJWT == null) {
            result = Result.unAuth(null);
        }

        // token过期
        if (JWTUtils.isExpire(decodedJWT)) {
            result = Result.unAuth(null);
        }

        // 身份验证
        if (JWTUtils.verifyAdmin(decodedJWT)) {
            result = Result.unPri();
        }

        // 判定结果
        if (request != null) {
            response.getWriter().write(Result.toJson(result));
        }else {
            // 传递给下一个过滤器或目标资源
            chain.doFilter(request, response);
        }


    }
}

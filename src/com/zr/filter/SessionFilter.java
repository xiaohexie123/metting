package com.zr.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/*")
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String uri = request.getRequestURI();
        System.out.println(uri);
        if(uri.contains("/index.jsp")||uri.contains("login.jsp")||uri.contains("/img")||uri.contains("/code")||uri.contains("/user")
                ||uri.contains("/bootstrap-3.4.1")||uri.contains("/css")||uri.contains("/js")){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            //判断是否登录
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("loginUser");
            if(obj != null){
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                response.sendRedirect("login.jsp");
            }
        }
    }

    @Override
    public void destroy() {

    }
}

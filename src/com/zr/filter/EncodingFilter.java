package com.zr.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@WebFilter("/*")
public class EncodingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		System.out.println("执行字符编码过滤器");
		HttpServletRequest req=(HttpServletRequest)servletRequest;
		System.out.println(req.getRequestURL());
		servletRequest.setCharacterEncoding("utf-8");
		servletResponse.setCharacterEncoding("utf-8");

		//执行下一个过滤器链
		filterChain.doFilter(servletRequest,servletResponse);
		System.out.println("字符编码过滤器完毕");
	}

	@Override
	public void destroy() {

	}
}

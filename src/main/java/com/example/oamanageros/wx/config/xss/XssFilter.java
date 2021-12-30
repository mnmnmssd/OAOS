package com.example.oamanageros.wx.config.xss;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 笑的心酸 - Red4Lion - mnmnmssd
 * @date 2021.12.21
 * 配置拦截器 防止Xss攻击
 */
@WebFilter(urlPatterns = "/*")
public class XssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        XssHttpRequestWrapper wrapper = new XssHttpRequestWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(wrapper,servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

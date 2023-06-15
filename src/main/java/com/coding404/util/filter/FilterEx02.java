package com.coding404.util.filter;


import javax.servlet.*;
import java.io.IOException;

//필터체이닝
//@WebFilter("/FilterEx02")
public class FilterEx02 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("두번째 필터 실행됨");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}

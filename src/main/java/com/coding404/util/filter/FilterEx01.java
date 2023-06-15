package com.coding404.util.filter;


import javax.servlet.*;
import java.io.IOException;

// 1. Filter클래스를 상속받는다
//3. 어노테이션 방법 or web.xml 로 걸러낼 요청 선언
//@WebFilter("/*") // #모든 요청
public class FilterEx01 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        
        System.out.println("첫번째 필터 실행됨");
        // 2. 다음프로그램 코드로 연결 ( 필터 체인이 있다면 다음 필터로 연결)
        filterChain.doFilter(servletRequest, servletResponse);
    }

}

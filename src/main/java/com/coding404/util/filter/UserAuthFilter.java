package com.coding404.util.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//세션 검사를 하기위한 필터
//String[] arr = {} -> urlPattens 의 default 는 ""이다 : url
@WebFilter( {"/user/user_mypage.user", // 마이페이지
             "/user/user_modify.user", //정보 수정페이지
             "/board/board_write.board", // 글 동록페이지
             "/board/registForm.board"}) // 글 등록요청
public class UserAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        // servletRedquest은 HttpServletRequest의 부모타입이다 => 세션을 사용하기 위해 다운캐스팅
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        //세션을 얻는다
        HttpSession session = req.getSession();
        // 회원이 로그인 할 때 저장한 인증값
        String user_id = (String) session.getAttribute("user_id");
        // 로그인이 안됨
        if(user_id == null) {
            //절대 경로 path + 컨트롤러의 주소값
            String path = req.getContextPath() + "/user/user_login.user";
//            res.sendRedirect(path);
            res.setContentType("text/html; charset=UTF-8;");
            PrintWriter out = res.getWriter();
            out.println("<script>");
            out.println("alert('로그인이 필요한 서비스입니다');");
            // 중요
            out.println("location.href='" + path +"';");
            out.println("</script>");
            return; // 필터 종료 -> 빠지면 doFilter 가 실행된다 : error
        }

        //다음코드를 실행함 or 필터를 실행함
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

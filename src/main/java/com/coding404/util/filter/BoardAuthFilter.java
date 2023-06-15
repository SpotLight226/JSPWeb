package com.coding404.util.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

// 본이이 작성한 글만, 수정 삭제를 할 수 있음
@WebFilter({"/board/board_modify.board",
            "/board/board_update.board",
            "/board/board_delete.board"})
public class BoardAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        //작성자를 구함 -> 글 작성자
        String writer = req.getParameter("writer");
        //세션에 저장된 작성자를 구함
        HttpSession session = req.getSession();
        String user_id = (String) session.getAttribute("user_id");
        
        //절대 경로
        String path = req.getContextPath() + "/user/user_login.user";
        // 아이디 or 세션값이 null인 경우
        if(writer == null || user_id == null){

            res.setContentType("text/html; charset=UTF-8;");
            PrintWriter out = res.getWriter();
            out.println("<script>");
            out.println("alert('허용하지 않는 접근입니다');");
            // 중요
            out.println("history.go(-1);"); // 뒤로 가기
            out.println("</script>");
            return;
        }
        // 작성자와 세션이 같지 않은경우
        if(!writer.equals(user_id)){

            res.setContentType("text/html; charset=UTF-8;");
            PrintWriter out = res.getWriter();
            out.println("<script>");
            out.println("alert('권한이 없습니다');"); // 뒤로 가기
            // 중요
            out.println("history.go(-1);");
            out.println("</script>");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}

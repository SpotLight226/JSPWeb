
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //모든 세션 무효화
    session.invalidate();
    // 로그인 화면으로 이동
    response.sendRedirect("/user/user_login.user");
%>



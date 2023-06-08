<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<section>
    <div align="center">
        <h3>회원정보 페이지</h3>
        ${sessionScope.user_id }
        (${sessionScope.user_name}) 님의 정보를 관리중입니다

        <br>
        <a href="#">회원 수정</a>
        <a href="#">회원 탈퇴</a>
        <a href="user_logout.user">로그 아웃</a>

    </div>
</section>

<%@ include file="../include/footer.jsp"%>


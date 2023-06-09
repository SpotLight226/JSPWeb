<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<div align="center" class="div_center">
	<h3>게시판 글 작성 페이지</h3>
	<hr>
	<!--
	로그인 된 사람만 작성페이지에 진입할 수 있게 작성자를 셰션값으로 고정
	-->
	
	<form action="registForm.board" method="post">
		<table border="1" width="500">
			<tr>
				<%-- 컬럼 이름을 데이터베이스와 똑같이 작성한다--%>
				<td>작성자</td>
				<td>
					<input type="text" name="writer" value="${sessionScope.user_id}" readonly size="10">
				</td>
			</tr>
			<tr>
				<td>글 제목</td>
				<td>
					<input type="text" name="title" >
				</td>
			</tr>
			<tr>
				<td>글 내용</td>
				<td>
					<textarea rows="10" style="width: 95%;" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="작성 완료" onclick="">
					&nbsp;&nbsp;
					<input type="button" value="목록" onclick="location.href='board_list.board'">
				</td>
			</tr>
			
		</table>
	</form>
	
</div>

<%@ include file="../include/footer.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<div align="center" class="div_center">
	<h3>게시판 글 수정 페이지</h3>
	<hr>
<%--	필요한 데이터를 update해준다 --%>
	<form action="board_update.board" method="post">

		<input type="hidden" name="bno" value="${vo.bno}">
		<table border="1" width="500">
<%--			화면에서 보여질 필요는 없지만, 데이터는 form으로 전송해야할 때
				input태그의 hidden 속성을 쓴다  -> 중요 !!!!!!!!  --%>
			<%--
			<tr>
				<td>글 번호</td>
				<td>${vo.bno}</td>
			</tr>
			--%>

			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer" value="${vo.writer}" readonly></td>
			</tr>
			<tr>
				<td>글 제목</td>
				<td>
					<input type="text" name="title" value="${vo.title}">
				</td>
			</tr>
			<tr>
				<td>글 내용</td>
				<td>
					<textarea rows="10" style="width: 95%;" name="content">${vo.content}
					</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정 하기" onclick="location.href='board_content.board'">&nbsp;&nbsp;
					<input type="button" value="목록" onclick="location.href='board_list.board'">
				</td>
			</tr>
			
		</table>
	</form>
	
</div>

<%@ include file="../include/footer.jsp"%>
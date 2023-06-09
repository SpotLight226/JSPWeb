<%--
  Created by IntelliJ IDEA.
  User: wkdqh
  Date: 2023-06-09
  Time: 오전 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/header.jsp" %>

<section>
  <div align="center">
      <h3>회원정보 수정</h3>
      <b>${ sessionScope.user_name }님 회원 정보를 수정합니다</b>

      <%--
      readonly 는 태그의 읽기 전용 ( 파라미터 값에 포함 )
      disabled 는 태그의 사용금지 (파라미터 값에서 제외됨)
      checked 는 미리 선택함 -> checked만 작성해도 적용된다
      required 는 필수로 값을 지정
      onclick="location.href='경로' 는 버튼을 클릭시 주소로 이동한다 ( 컨트롤러를 타고 넘어가게 주소를 지정 )
      태그 안에 삼항 연산자 사용가능

      EL태그의 문법은 앞의 get을 제외하고, 뒤의 get명만 작성한다
         -> EL태그 만의 문법 (자바는 위에서 선언 <% %> 에서 vo를 선언하고, vo.get~한다)
            본래 근본은 getter 이다 : EL태그에서는 자바의 문법을 줄여서 사용할 수 있게 한다
      인풋태그에 미리 값을 가지려면 value를 사용한다
      --%>
      <%-- 정보 수정으로 넘어간다 --%>
      <form action="user_update.user" method="post">
          <table border="1">
              <tr>
                  <td>아이디</td> <%-- session의 id를 가져와서 사용해도 OK--%>
                  <td><input type="text" name="id" value="${vo.id}" readonly="readonly"></td >
              </tr>
              <tr>
                  <td>비밀번호</td>
                  <td><input type="text" name="pw" required="required" pattern="\w{4,}"></td>
              </tr>
              <tr>
                  <td>닉네임</td>
                  <td><input type="text" name="name" value="${vo.name}" required="required"></td>
              </tr>
              <tr>
                  <td>이메일</td>
                  <td><input type="email" name="email" value="${vo.email}"></td>
              </tr>
              <tr>
                  <td>성별</td>
                  <%-- 삼항연산자로 checked를 작성할 수 있다 : ${ 삼항 연산자 } 각 버튼의 checked 옵션을 삼항 연산자로
                       M일 때 남자에 체크, F일 때 여자에 체크--%>
                  <td><input type="radio" name="gender" value="M" ${vo.gender == 'M' ? 'checked' : ''}>남자</td>
                  <td><input type="radio" name="gender" value="F" ${vo.gender == 'F' ? 'checked' : ''}>여자</td>
              </tr>
          </table>

          ${vo.name}<br/>
          <%--  submit은 데이터를 파라미터로 form의 action으로 나간다 --%>
          <input type="submit" value="정보 수정">
          <input type="button" value="회원페이지로 가기" onclick="location.href='user_mypage.user'">

      </form>


  </div>
</section>


<%@ include file="../include/footer.jsp" %>

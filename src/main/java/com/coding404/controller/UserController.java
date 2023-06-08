package com.coding404.controller;

import com.coding404.sevice.UserService;
import com.coding404.sevice.UserServiceImpl;
import com.coding404.user.model.UserVO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("*.user")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doAction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doAction(request, response);
    }

    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //3. 요청분기
        request.setCharacterEncoding("utf-8");
        //uri
        String uri = request.getRequestURI();
        // path
        String conPath = request.getContextPath();
        // uri에서 path만큼 잘라낸다
        String command = uri.substring(conPath.length());
        //필요한 객체를 if문 위에 선언
        UserService service = new UserServiceImpl(); //인터페이스가 있다면 다형성을 이용해서 자식타입을 부모타입으로

//        System.out.println(command); 주소 확인 -> 삭제해도 OK

        // ** MVC2에서는 화면을 띄우는 요청도 컨트롤러를 거쳐 나가도록 처리
        // ** 기본이동이 전부 forward형식으로 처리한다
        // ** 리다이렉트는 다시 컨트롤러를 태워 나가는 용도로 사용한다 : 재활용

        // 들어오는 경로는 폴더명을 포함해서 작성한다
        if (command.equals("/user/user_join.user")) {
            // forward로 나가는 주소는 실제 파일의 위치(절대 경로)로 작성한다
            request.getRequestDispatcher("user_join.jsp").forward(request, response);

        } else if (command.equals("/user/user_login.user")) {
            request.getRequestDispatcher("user_login.jsp").forward(request, response);

            //회원 가입 요청 ( user_join.jsp 에서 submit으로 데이터와 같이 호출하면 )
        } else if (command.equals("/user/joinForm.user")) {
//          UserServiceImpl service = new UserServiceImpl(); //인터페이스가 없다면
            //가입
            //service는 위에 선언해 둠
            int result = service.join(request, response);

            if (result == 1) { // 중복, 가입 실패
                //msg - 데이터 ( 1회성 )
                request.setAttribute("msg", "중복된 아이디 입니다");
                // msg 데이터를 가지고 가야하기 때문에 forward
                request.getRequestDispatcher("user_join.jsp").forward(request, response);

            } else { // 가입 성공
                // MVC2 방식으로 리다이렉트는 컨트롤러를 태워서 처리한다 -> 다시 컨트롤러에 user_login.user로 들어간다 : 재활용
                response.sendRedirect("user_login.user");

            }
            // 로그인
        } else if (command.equals("/user/loginForm.user")) {

            UserVO vo = service.login(request, response);

            if (vo == null) { // 로그인 실패 : vo가 null
                //request에 메세지를 담아서
                request.setAttribute("msg", "아이디 비밀번호를 확인하세요");
                //login화면으로 forward한다
                request.getRequestDispatcher("user_login.jsp").forward(request, response);

            } else { // 로그인 성공
                //세션에 회원정보를 저장 ( 자바에서 세션얻는 방법 ) - 암기
                // .getSession -> 현재 접속되어 있는 Session을 반환해 준다
                HttpSession session = request.getSession();
                session.setAttribute("user_id", vo.getId());
                session.setAttribute("user_name", vo.getName());

                //담을 데이터가 없으므로 mypage로 redirect 한다
                response.sendRedirect("user_mypage.user");
            }
        } else if (command.equals("/user/user_mypage.user")) { // 회원 페이지로 이동
            request.getRequestDispatcher("user_mypage.jsp").forward(request, response);

        } else if (command.equals("/user/user_logout.user")) { // 로그아웃 페이지로 이동

            request.getRequestDispatcher("user_logout.jsp").forward(request, response);
        }
    }
}

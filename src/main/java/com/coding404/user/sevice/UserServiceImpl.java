package com.coding404.user.sevice;

import com.coding404.user.model.UserDAO;
import com.coding404.user.model.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserServiceImpl implements UserService {

    // 컨트롤러에서 service.join()으로 호출함
    @Override
    public int join(HttpServletRequest request, HttpServletResponse response) {
        // 넘어온 데이터에서 id, pw, name, email, gender를 받는다
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        // 위의 값으로 DAO로 진입한다

        //1. 아이디 중복 검사
        //싱글톤 객체를 얻는 방법
        UserDAO dao = UserDAO.getInstance();

        //dao의 idcheck 메서드 실행 해서 결과를 result에
        int result = dao.idCheck(id);

        if(result == 1){ // 중복
            return 1;
        } else { //가입처리
            UserVO vo = new UserVO(id, pw, name, email, gender, null);
            dao.join(vo);
            return 0;
        }
    } // join 메서드 끝

    //login 메서드
    @Override
    public UserVO login(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        //로그인
        // dao 객체를 생성
        UserDAO dao = UserDAO.getInstance();
        // vo객체를 dao.login 메서드를 실행해서 만들어준다
        UserVO vo = dao.login(id, pw);

        return vo;
    } // login 메서드 끝

    // 정보 수정페이지에 나타낼 user의 정보
    @Override
    public UserVO getInfo(HttpServletRequest request, HttpServletResponse response) {

        //회원 아이디가 필요하다  -> 이전 화면에서 필요한 값을 넘겨주던 vs 회원 아이디는 세션에 존재
        // 세션을 얻어온다
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("user_id");// 세션의 user_id를 꺼낸다
        //dao 호출
        UserDAO dao = UserDAO.getInstance();
        UserVO vo = dao.getInfo(id);

        return vo;
    } // getInfo 메서드 끝

    //정보 수정메서드

    @Override
    public int updateInfo(HttpServletRequest request, HttpServletResponse response) {
        
        /*
            서비스와 DAO영역 작성
            1. 아이디 기반으로 회원정보를 수정
            2. 성공 실패여부를 컨트롤러로 반환 받는다
            3. 수정 성공시에는 mypage로 리다이렉트, 실패시에는 modify로 리다이렉트

         */

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        //dao객체
        UserVO vo = new UserVO(id, pw, name, email, gender, null);
        UserDAO dao = UserDAO.getInstance();

        int result = dao.updateInfo(vo);

        return result;
    } // update 메서드 끝

    @Override
    public int delete(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");

        UserDAO dao = UserDAO.getInstance();

        int result = dao.delete(id);

        return result;
    }
}

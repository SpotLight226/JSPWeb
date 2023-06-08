package com.coding404.sevice;

import com.coding404.user.model.UserDAO;
import com.coding404.user.model.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    }
}

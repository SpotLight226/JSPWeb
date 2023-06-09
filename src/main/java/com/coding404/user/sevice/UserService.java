package com.coding404.user.sevice;

import com.coding404.user.model.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    //인터페이스 = 추상메서드의 집합

    //컨트롤러의 doAction에서 받는 request와 response를 받는다
    int join(HttpServletRequest request, HttpServletResponse response) ;
    UserVO login(HttpServletRequest request, HttpServletResponse response);
    // 회원정보 수정시 vo를 가지고 간다
    UserVO getInfo(HttpServletRequest request, HttpServletResponse response);

    int updateInfo(HttpServletRequest request, HttpServletResponse response);

    int delete(HttpServletRequest request, HttpServletResponse response);

}

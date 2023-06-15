package com.coding404.board.service;

import com.coding404.board.model.BoardVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface BoardService {
    // 작성 -> 목록 -> 상세 -> 수정  -> 목록
//                        -> 삭제
    //글 작성
    void regist(HttpServletRequest request, HttpServletResponse response);
    // 글 목록
    List<BoardVO> getList(HttpServletRequest request, HttpServletResponse response);

    //상세 글 보기
    BoardVO getContent(HttpServletRequest request, HttpServletResponse response);

    //글 수정
    void update(HttpServletRequest request, HttpServletResponse response);

    //글 삭제
    void delete(HttpServletRequest request, HttpServletResponse response);
}

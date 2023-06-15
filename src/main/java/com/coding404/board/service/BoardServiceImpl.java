package com.coding404.board.service;

import com.coding404.board.model.BoardDAO;
import com.coding404.board.model.BoardVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BoardServiceImpl implements BoardService{

    // 글 작성 메서드
    @Override
    public void regist(HttpServletRequest request, HttpServletResponse response) {
        String writer = request.getParameter("writer");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        BoardDAO dao = BoardDAO.getInstance();
        dao.resist(writer, title, content);

    } // regist 끝

    //글 목록 확인 메서드
    @Override
    public List<BoardVO> getList(HttpServletRequest request, HttpServletResponse response) {

        BoardDAO dao = BoardDAO.getInstance();

        List<BoardVO> list = dao.getList();

        return list;
    } // getList 끝

    //글 상세보기 메서드
    @Override
    public BoardVO getContent(HttpServletRequest request, HttpServletResponse response) {

        String bno = request.getParameter("bno");

        BoardDAO dao = BoardDAO.getInstance();
        BoardVO vo = dao.getContent(bno);

        return vo;
    } // getContent 끝

    //글 수정 메서드
    @Override
    public void update(HttpServletRequest request, HttpServletResponse response) {

        String bno = request.getParameter("bno");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        BoardDAO dao = BoardDAO.getInstance();
        dao.update(bno, title, content);

    } // update 끝

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response) {

        String bno = request.getParameter("bno");

    }
}

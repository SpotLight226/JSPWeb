package com.coding404.controller;

import com.coding404.board.model.BoardVO;
import com.coding404.board.service.BoardService;
import com.coding404.board.service.BoardServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BoardController() {
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

        System.out.println(command); // 주소를 확인한다
        //게시글 서비스 선언
        BoardService service = new BoardServiceImpl();

        //글쓰기 화면에 대한 처리
        if(command.equals("/board/board_write.board")){
            request.getRequestDispatcher("board_write.jsp").forward(request, response);

        // 목록 화면 처리
        } else if (command.equals("/board/board_list.board")) {

            // 목록을 가지고 나온다 -> list로 vo를 여러개 담아서 가지고 나온다
            List<BoardVO> list = service.getList(request, response);
            request.setAttribute("list", list);

            request.getRequestDispatcher("board_list.jsp").forward(request, response);

        // 글 등록 화면
        } else if (command.equals("/board/registForm.board")) {
            //서비스를 호출(regist)
            service.regist(request, response);
            //1.forward : 가지고 나갈 데이터가 있을 때만 사용한다 -> 이번에는 list.board에 list가 있으므로 리다이렉트 사용
//            request.getRequestDispatcher("board_list.board").forward(request, response);
            //2. Redirect
            response.sendRedirect("board_list.board");

        // 글 상세보기 화면
        } else if (command.equals("/board/board_content.board")) {
            //getContent : bno
            BoardVO vo = service.getContent(request, response);
            request.setAttribute("vo", vo); // request에 vo를 담아준다
            request.getRequestDispatcher("board_content.jsp").forward(request, response);

        // 글 수정하기 화면
        } else if (command.equals("/board/board_modify.board")) {
            //getContent : bno
            BoardVO vo = service.getContent(request, response);
            request.setAttribute("vo", vo);

            request.getRequestDispatcher("board_modify.jsp").forward(request, response);

        // 글 수정기능
        } else if (command.equals("/board/board_update.board")) {

            service.update(request, response);
            String bno = request.getParameter("bno"); // 글 상세보기에서 bno가 필요하므로 a태그에 GET방식으로 bno를 붙여서 보낸다
            // 내용 페이지로 이동
            response.sendRedirect("board_content.board?bno=" + bno );
        
        // 글 삭제 : 내가 구현
        } else if (command.equals("/board/board_delete.board")) {



        }


    }


}

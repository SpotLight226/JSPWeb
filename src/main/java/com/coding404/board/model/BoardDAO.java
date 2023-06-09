package com.coding404.board.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    //싱글톤 형태의 클래스로 생성하는 편이 좋다
    //1. 나 자신의 객체를 static으로 선언
    private static BoardDAO instance = new BoardDAO();

    //2. 직접 생성하지 못하도록 생성자 제한
    private BoardDAO() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {

        }
    }

    //3. getter를 통해 객체를 반환
    public static BoardDAO getInstance() {
        return instance;
    }

    //데이터베이스 연결 주소
    // + 오라클 커넥터
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String uid = "JSP";
    private String upw = "JSP";

    //글 등록 메서드
    public void resist(String writer, String title, String content) {

        String sql = "INSERT INTO board(BNO, WRITER, TITLE, CONTENT)\n" +
                     "VALUES (board_seq.NEXTVAL, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = DriverManager.getConnection(url, uid, upw);

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, writer);
            pstmt.setString(2, title);
            pstmt.setString(3, content);

            pstmt.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    } // resist 끝

    //글 목록 메서드
    public List<BoardVO> getList(){
        // 글 을 담을 list
        List<BoardVO> list = new ArrayList<>();

        String sql = "SELECT * FROM board ORDER BY BNO DESC";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, uid, upw);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            /*
              조회된 데이터를 순서대로 vo에 담고 리스트에 추가하는 프로그램 코드
             */
            while(rs.next()){
                int bno = rs.getInt("bno");
                String writer = rs.getString("writer");
                String title = rs.getString("title");
                String content = rs.getString("content");
                int hit = rs.getInt("hit");
                Timestamp regdate = rs.getTimestamp("regdate");

                BoardVO vo = new BoardVO(bno, writer, title, content, hit, regdate);

                list.add(vo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
                rs.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return list;
    } // getList 끝

    // 상세 글 보기
    public BoardVO getContent(String bno){

        BoardVO vo = null;

        String sql = "SELECT * FROM board WHERE bno = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, uid, upw);

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bno);

            rs = pstmt.executeQuery();
            // 가져온 rs에서 각 요소를 추출하고, vo에 담아준다
            if(rs.next()) {
                int bno2 = rs.getInt("bno");
                String writer = rs.getString("writer");
                String title = rs.getString("title");
                String content = rs.getString("content");
                int hit = rs.getInt("hit");
                Timestamp regdate = rs.getTimestamp("regdate");

                vo = new BoardVO(bno2, writer, title, content, hit, regdate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
                rs.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return vo;
    } // get Content끝
    
    //글 수정하기
    public void update(String bno, String title, String content){
        
       String sql = "UPDATE board SET title = ?, content = ? WHERE bno = ?";

       Connection conn = null;
       PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(url, uid, upw);
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, title);
            pstmt.setString(2,content);
            pstmt.setString(3,bno);

            pstmt.executeQuery();  //끝

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
                pstmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

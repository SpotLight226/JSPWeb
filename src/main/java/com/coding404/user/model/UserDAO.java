package com.coding404.user.model;

import java.sql.*;

public class UserDAO {

    //싱글톤 형태의 클래스로 생성하는 편이 좋다
    //1. 나 자신의 객체를 static으로 선언
    private static UserDAO instance = new UserDAO();

    //2. 직접 생성하지 못하도록 생성자 제한
    private UserDAO() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {

        }
    }

    //3. getter를 통해 객체를 반환
    public static UserDAO getInstance() {
        return instance;
    }

    //데이터베이스 연결 주소
    // + 오라클 커넥터
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String uid = "JSP";
    private String upw = "JSP";

    //  중복검사
    // service의 join 메서드에서 idCheck()가 호출
    public int idCheck(String id) {
        int result = 1;

        String sql = "SELECT * FROM users WHERE ID = ?";

        // 싱글톤에서는 지역변수로 사용해야 한다
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            //1. Connector - 디비 연결
            conn = DriverManager.getConnection(url, uid, upw);
            //2. Pstmt - sql을 실행하기 위한 클래스
            pstmt = conn.prepareStatement(sql);
            // ? 위치에 id를 넣는다
            pstmt.setString(1, id);
            //3, ResultSet
            rs = pstmt.executeQuery(); // select 문

            //re.next() : sql 문에서 가져온 값이 있다면
            if (rs.next()) { // 중복 o -> sql문에서 조회가 된다면 중복이다
                result = 1;
            } else { // 중복 x -> sql에서 조회가 안됨
                result = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
                rs.close();
            } catch (Exception e2) {
            }
        }
        return result;
    } // idCheck 메서드 끝

    //회원 가입
    public void join(UserVO vo) {
        String sql = "INSERT INTO users(id, pw, name, email, gender) VALUES ( ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(url, uid, upw);

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getId());
            pstmt.setString(2, vo.getPw());
            pstmt.setString(3, vo.getName());
            pstmt.setString(4, vo.getEmail());
            pstmt.setString(5, vo.getGender());

            pstmt.executeUpdate(); // 성공시 1, 실패시 0

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
            } catch (Exception e2) {
            }
        }
    } // join 메서드 끝
    
    // 로그인
    public UserVO login(String id, String pw) {
        //로그인 성공이면 객체가 반환, 로그인 실패면 null값이 반환
        UserVO vo = null;

        String sql = "SELECT * FROM users WHERE id = ? AND pw = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = DriverManager.getConnection(url, uid, upw);

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);

            rs = pstmt.executeQuery();

            // 값이 있다면 작업
            if (rs.next()) {

                String id2 = rs.getString("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String gender = rs.getString("gender");
                Timestamp regdate = rs.getTimestamp("regdate");
                // pw제외하고 나머지를 vo에 담는다
                vo = new UserVO(id2, null, name, email, gender, regdate);

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
    } //login 메서드 끝
}

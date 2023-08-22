package com.example.demo.Section1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 * SQL 삽입(Improper Neutralization of Special Elements used in an SQL Command,SQL
 * Injection)
 * 
 * DB 연동 웹 앱에서 입력된 데이터에 대한 유효성 미검증시, 입력폼 및 url 입력란 내 sql 삽입으로 인한 DB 정보 열람 및 조작
 * 가능 보안 약점
 * 
 * @param props
 *              tbl 정보 갖고 있는 object
 * 
 */
public class doc1 {

    /**
     * 동적쿼리 생성
     */
    public void part1(Map<String, String> props) {
        try {
            PreparedStatement stmt = null;
            Connection con = null;

            /**
             * 변수 직접 할당하여 쿼리문 작성 X
             */
            String tableName = props.get("jdbc.tableName");
            String name = props.get("jdbc.name");

            String errorQuery = "SELECT * FROM " + tableName + " WHERE Name =" + name;

            /**
             * pstmt 에 담아서 쿼리 수행
             */
            String correctQuery = "SELECT * FROM " + tableName + " WHERE Name =" + name;
            stmt = con.prepareStatement(correctQuery);

            stmt.setString(1, tableName);
            // public abstract void setString(int arg0, java.lang.String arg1) throws
            // java.sql.SQLException;
            stmt.setString(2, name);
            ResultSet rs = stmt.executeQuery(correctQuery);

        } catch (SQLException sqle) {
            sqle.getSQLState();
        }
    }





    /**
     * http request 로부터 사용자 ID와 암호를 추출하여 SQL 질의문 생성
     * "makeSecureString"
     */

    // 1. 작업의 type 을 지정한다.
    private final String GET_USER_INFO_CMD = "get_user_info";
    private Connection con;

    // 2. id와 password의 최대 길이 제한을 8character 와 16character 로 제한
    private final static int MAX_USER_ID_LENGTH = 8;
    private final static int MAX_PASSWORD_LENGTH = 16;

    // 3. 기존 명령어와 알파벳, 숫자를 제외한 다른 문자 검출 정규식 설정
    private final static String UNSECURED_CHAR_REGULAR_EXPRESSION = "[^\\p{Alnum}]|select|delete|update|insert|create|alter|drop";

    private Pattern unsecuredCharPattern;

    // 4. 정규식을 초기화한다.
    public void initialize(){
        unsecuredCharPattern = Pattern.compile(UNSECURED_CHAR_REGULAR_EXPRESSION, Pattern.CASE_INSENSITIVE); 
    }

    // 5. 입력값을 정규식을 이용해 필터링한 후 의심 부분 제거한다.
    private String makeSecureString(final String str, int maxLength){
        
        String secureStr = str.substring(0, maxLength);
        Matcher matcher = unsecuredCharPattern.matcher(secureStr); return matcher.replaceAll("");
    
    }


    protected void part2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    
        // 일반 로직 -> 취약점 검출
        String command = request.getParameter("command");
        if (command.equals(GET_USER_INFO_CMD)){
            Statement stmt = con.createStatement();
            
        /**
         Id 를 guest' OR 'a'='a‘-- 로, 설정할 경우
            (SELECT * FROM members WHERE userId ='guest' OR 'a'='a‘-- AND password = ‘’) 로, 사용자 정보를 얻을 수 있다. 
        */
            String userId = request.getParameter("user_id");
            String password = request.getParameter("password");
            
            String query = "SELECT * FROM members WHERE username= '" + userId + "\\' AND password = '" + password + "'"; 
        
        stmt.executeUpdate(query);

    }






    // MakeSureString 사용
    String command2 = request.getParameter("command");
    if (command2.equals(GET_USER_INFO_CMD)){
        Statement stmt = con.createStatement();
        String userId = request.getParameter("user_id");
        String password = request.getParameter("new_password");
        String query = "SELECT * FROM members WHERE username= '" + makeSecureString(userId, MAX_USER_ID_LENGTH) +
                    "' AND password = '" + makeSecureString(password, MAX_PASSWORD_LENGTH) + "'";
        stmt.executeUpdate(query);
        }

    }


       
        /*  Q. 동적쿼리 생성

            * Sol.
            * 
            * 1. 동적쿼리 생성 불가 로직 필요
            * 1-1. PreparedStatement 내 form 형식 삽입 -> 외부입력으로 인한 구조 변경 X
            * 1-2. 유효성 검증
            * 1-3. 입력값 필터링 처리 -> SQL 구문 제한, 특수문자 제한, 길이제한 복합 사용
            * 
            */

        /*
            * opinion.
            * 
            * 1. spring(boot), java 파일 내 데이터 Dto, VO 내 담아서 mapper 로 쿼리 수행시
            * 웹 등에서 input value 로 받아오는 값 부분에 대한 유효성 점검
            * 
            * 2. 단일 java 내 session Factory 사용하여, 인스턴스 내 유효성 처리 고려
            * Dao 내 쿼리 구문 내 유효성 검증 로직 보강
            */


}// class
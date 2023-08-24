package com.example.demo.Section1;

import com.example.demo.Util.HTMLInputFilter;

import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

/**
 * 크로스 사이트 스크립트(Improper Neutralization of Input During Web Page Generation, Cross-site Scripting) 
 * 
 * 웹 페지 악의적인 스크립트를 포함시켜 사용자 측에서 실행되게 유도하여 정보 유출 가능 약점
 *
 */
public class doc3  extends HttpServletRequestWrapper{

    public doc3(HttpServletRequest request) {
        super(request);

        // html 부분
    }

    private static final String request = null;

    /** 
    자바 내 보안 API 사용
    util - HTMLInputFilter
    */
    private String part1(String input){
        String clean = new HTMLInputFilter().filter(input);
        return clean; 
    }

        /*  Q. 자원 삽입

            * Sol.
            * 
            * 1. <, >, &, “,”  치환  &lt, &gt, &amp, &quot
            * 2. HTML 태그의 리스트 선정 후, 해당 태그만 허용 방식 적용
            * 
            * opinion.
            * 1. 관련 HTML case 확인
        */
        

}
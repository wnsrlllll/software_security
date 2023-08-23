package com.example.demo.Section1;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;

import java.net.*;
import java.io.*;

import org.springframework.boot.CommandLineRunner;

import com.example.demo.DemoApplication;
import com.example.demo.Util.SocketClient;



/**
 * 자원 삽입(Improper Control of Resource Identifiers, Resource Injection) 
 * 
 * 외부 입력(Service)을 소켓 번호로 그대로 사용. 만일, 공격자가 Service No 값 으로 -2920 값 지정시, 기존 80 포트에서 구동되는 서비스와 충돌되어 에러 발생
 * @param 
 * 
 * @param props
 * config 등 설정 파일
 */
public class doc2 implements CommandLineRunner{
    

    /** 
    시스템 정보 직접 사용시, 유효성 검증
    */
    public void part1() throws IOException{
        int def = 1000;
        
        ServerSocket serverSocket;
        Properties props = new Properties();
        
        String fileName = "file_list";
        FileInputStream in = new FileInputStream(fileName);
        props.load(in);

        // 외부에서 입력한 데이터 수신.
        String service = props.getProperty("Service No");
        int vun_port = Integer.parseInt(service);
        
        /** 
         * 취약.
         * 포트번호 등 시스템 정보 직접 적용으로 인한 보완 대책 필요
        */
        // 외부에서 입력받은 값으로 소켓 생성
        if (vun_port != 0)
        serverSocket = new ServerSocket(vun_port + 3000); 
        else serverSocket = new ServerSocket(def + 3000);



        /** 
         * 보완 코드
         * 포트번호 등 시스템 정보 직접 적용으로 인한 보완 대책 필요
        */
        if (in != null && in.available() > 0) {
            props.load(in);
            // 외부로부터 데이터를 입력받는다.
            service = props.getProperty("Service No");
        }
       
        // 외부의 입력을 기본적인 내용 검사를 한다.
        if ("".equals(service)) service = "8080";
        int port = Integer.parseInt(service);

        // 포트번호 검사 후, 값 할당한다.
         switch (port){
                        case 1:
                        port = 3001; break;
                        case 2:
                        port = 3002; break;
                        case 3:
                        port = 3003; break;
                        default:
                        port = 3000;
                        }

            // 서버소켓에 검사완료 포트 할당
            serverSocket = new ServerSocket(port);
    }





    /**
     * 
     * main 함수에서 값을 받아 수행하는 경우, 직접할당 방지
     * (CommandLineRunner for boot)
     */
    @Override
    public void run(String... args) throws Exception {
    
        SocketClient c;
        String ip = args[0];
        int port = 7777;
    
        try {
            // 인자 사용시, 유효성 검증 필요
            if("127.0.0.1".equals(ip)){
            c = new SocketClient(ip, port);
            c.startSocket();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



        /*  Q. 자원 삽입

            * Sol.
            * 
            * 1. 예측 가능 범위에 대한 유효성 검증
            * 2. 시스템 자원에 대한 할당 및 사용시, 검증 확인

            * opinion.
            * 1. exec 실행시, 환경변수로 설정되어 있는 파일 위변조 방지
            * 2. 상수로 설정되어 있는 VO 파일 내 객체 추출하여 사용

        */
}

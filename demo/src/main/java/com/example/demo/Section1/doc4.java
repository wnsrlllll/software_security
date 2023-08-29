package com.example.demo.Section1;

import java.io.FileInputStream;
import java.util.Properties;



/**
 * 운영체제 명령어 삽입(Improper Neutralization of Special Elements Used in an OS Command, OS Command Injection)
 * 
 * 적절한 검증절차를 거치지 않은 사용자 입력값이 운영체제 명령어의 일부 또는 전부로 구성되어 실행시, 의도치 않은 시스템 명령어가 실행되어 부적절한 권한 변경 및 시스템(동작 및 운영)에 악영향에 대한 약점
 * @param props
 * config 등 설정 파일
 */
public class doc4  {

    /**
     *  운영체제 명령어 삽입 
    */
    private void part1(String fileName) {
        
        try {
            String file_name = fileName;
            Properties props = new Properties();
            FileInputStream in = new FileInputStream(file_name);
            props.load(in);

            String version = props.getProperty("dir_type");
            String cmd = new String("cmd.exe /K \"rmanDB.bat \"");
            
            /**
             * 변수 직접 할당하여 실행문 작성 X
             */
            Runtime.getRuntime().exec(cmd + " c:\\prog_cmd\\" + version);
    


            /**
             * 초기 할당 범위 설정
             */
            String version_correct[] = {"1.0", "1.1"};
            int versionSelection = Integer.parseInt(props.getProperty("version"));
            String cmd_correct = new String("cmd.exe /K \"rmanDB.bat \"");

            // 버전 등, 환경변수 지정범위 설정값 사용
            String vs = "";
                if (versionSelection == 0)
                    vs = version_correct[0];
                else if (versionSelection == 1)
                    vs = version_correct[1]; 
                else
                    vs = version_correct[1];

                Runtime.getRuntime().exec(cmd_correct + "c:\\prog_cmd\\" + vs);


        } catch (Exception e) {
            // TODO: handle exception
        }
    
    }




        /*  Q. OS Command Injection

            * Sol.
            * 
            * 1. 웹 인터페이스를 통해 시스템 명령어를 전달시키지 않도록 어플리케이션을 구성하며,
            * 외부 전달값을 그대로 사용 X 
            * 
            * 2. 명령어 임의 생성하여, 지정값에 따른 호출로 사용
            
            * opinion.
            * 1. 개발 중, CommandLine code 는 공공기관 운영지침에 따라 제한되는 경우가 있으므로, 
            * 해당 경우들을 미리 참조하여 소스코드를 작성하여야 한다.

            * 2. CommandLine 사용시, 관련 그룹 및 권한등 참조되는 경우를 미리 확인하여 2차, 3차 피해를 예방하여야 한다.
        */

}
package com.example.demo.Section1;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.Util.ServletFileUpload;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 위험한 형식 파일 업로드(Unrestricted Upload of File with Dangerous Type)
 * 
 * 적절한 검증절차를 거치지 않은 사용자 입력값이 운영체제 명령어의 일부 또는 전부로 구성되어 실행시, 의도치 않은 시스템 명령어가 실행되어
 * 부적절한 권한 변경 및 시스템(동작 및 운영)에 악영향에 대한 약점
 * 
 */
public class doc5 {

    // 임의 파일 크기 제한
    private int  MAX_FILE_SIZE = 10;

    private final String UPLOAD_DOCUMENT_COMMAND = "upload_document";
    private final String ADDITIONAL_OPERATION_PARAM = "additional_operation";

    private String filePath;

    /**
     * 업로드 파일의 확장자를 검사하여 허용되지 않은 확장자일 경우 업로드를 제한하고 있으며, 저장 시 외부에서
     * 입력된 파일명을 그대로 사용하지 않고 있다.
     * 
     * 임의 Exception
     */
    public void part1(HttpServletRequest request) throws Exception {

        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        String next = (String) mRequest.getFileNames().next();
        MultipartFile file = mRequest.getFile(next);

        // MultipartFile로부터 file을 얻음
        String fileName = file.getOriginalFilename();

        // upload 파일에 대한 확장자, 크기의 유효성 체크를 하지 않음
        File uploadDir = new File("/app/webapp/data/upload/notice");
        String uploadFilePath = uploadDir.getAbsolutePath() + "/" + fileName;

        /* 이하 file upload 루틴 */

        // 1. 업로드 파일 크기 제한
        int size = (int) file.getSize();
        if (size > MAX_FILE_SIZE)
            throw new ServletException("에러");

        // 2. 화이트리스트 방식으로 업로드 파일의 확장자를 체크.
        if (fileName != null) {
            if (fileName.endsWith(".doc") || fileName.endsWith(".hwp")
             || fileName.endsWith(".pdf") || fileName.endsWith(".xls")) {

                /* file 업로드 루틴 */
            }

        } else
            throw new ServletException("에러");

        // 3. 업로드 파일의 디렉터리 위치는 다큐먼트 루트의 밖에 위치.
        File correct_uploadDir = new File("/app/webapp/data/upload/notice");
        String correct_uploadFilePath = uploadDir.getAbsolutePath() + "/" + fileName;

    }

    /** 
     *   업로드 파일에 대한 유효성 미검사시, 위험 유형의 파일을 공격자가 공격 및 업로드 가능 
    */
    protected void part2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        String next = (String) mRequest.getFileNames().next();
        MultipartFile file = mRequest.getFile(next);
        
        // 임의 factory 패턴을 통한 인스턴스 사용
        Object factory = null;  // null 로 표기

        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        
        upload.setSizeMax( MAX_FILE_SIZE );
        
        try {
        // Parse the request to get file items.
        List fileItems = upload.parseRequest(request);
        
        // Process the uploaded file items
        Iterator i = fileItems.iterator();

        while ( i.hasNext () ) {
        FileItem fi = (FileItem)i.next();

        if (!fi.isFormField ()){

        // Get the uploaded file parameters
        String fieldName = fi.getFieldName();
        String fileName = fi.getName();
        String contentType = fi.getContentType();
        boolean isInMemory = fi.isInMemory();
        long sizeInBytes = fi.getSize();

        // Write the file
        if( fileName.lastIndexOf("\\") >= 0 ){
        file = (MultipartFile) new File( filePath + fileName.substring( fileName.lastIndexOf("\\")));
        } else {
        fileName.substring(fileName.lastIndexOf("\\")+1);
        }
        
        // 임의 파일 타입 지정
        Object EXECUTABLE_FILE_TYPE = null;

        // 파일 내용을 기반으로 하여 file의 type을 판별한다.
        if(extractFileType(file) == EXECUTABLE_FILE_TYPE) {
        // 실행 파일은 upload하면 위험한 파일이므로 에러 처리.
        return; 
        }

        // fi.write( file );
        }
        
        }
        }catch (Exception e){
            // TODO: handle exception
        }
        //...
        

        /*
        * Q. Unrestricted Upload of File with Dangerous Type
        * 
        * Sol.
        * 
        * 1. 업로드 파일 타입 및 크기 제한, 업로드 디렉토리 웹서버 다큐먼트 외부에 설정
        * 
        * 2. 화이트리스트 방식으로 허용된 확장자 업로드 허용 및 대소문자 구분 없이 처리하도록 구분
        * 
        * 3. 공격자의 웹을 통한 직접 접근 차단 및 파일 실행 여부 설정 가능인 경우, 실행 속성 제거
        * 
        * opinion.
        * 1. 지정 타입 설정 및 파일 유효성 검사 수행
        * 
        * 2. 
        * 
        */

    }



    // 파일 체크타입 설정
    private Object extractFileType(MultipartFile file) {

        // 임의 체크 로직

        
        return null;
    }
}
### 제 1절 입력 데이터 검증 및 표현


##### doc1. SQL 삽입(Improper Neutralization of Special Elements used in an SQL Command, SQL Injection) 
프로그램 입력 값에 대한 검증 누락 또는 부적절한 검증, 데이터의 잘못된 형식지정, 일관되지 않은 언어셋 사용 등으로 인해 발생되는 보안 약점

##### doc2. 자원 삽입(Improper Control of Resource Identifiers, Resource Injection) 
외부 입력값을 검증하지 않고 시스템 자원(resource)에 대한 식별자로 사용하는 경우, 공격자는 입력값 조작을 통해 시스템 임의 접근 및 수정 가능 약점

##### 3. 크로스 사이트 스크립트(Improper Neutralization of Input During Web Page Generation, Cross-site Scripting) 
웹 페이지 악의적인 스크립트를 포함시켜 사용자 측에서 실행되게 유도하여 정보 유출 가능 약점

##### 4. 운영체제 명령어 삽입(Improper Neutralization of Special Elements Used in an OS Command, OS Command Injection) 
적절한 검증절차를 거치지 않은 사용자 입력값이 운영체제 명령어의 일부 또는 전부로 구성되어 실행시, 의도치 않은 시스템 명령어가 실행되어 부적절한 권한 변경 및 시스템(동작 및 운영)에 악영향에 대한 약점
(1). Request 에 조작된 커맨드 삽입
(2). 공격자가 요청한 커맨드 명령어 실행

##### 5. 위험한 형식 파일 업로드(Unrestricted Upload of File with Dangerous Type) 
서버측, 실행 가능 스크립트 파일(asp, jsp, php 파일 등 .. )이 업로드 가능하고, 이 파일을 공격자가 웹을 통해 직접 실행시킬 수 있는 경우 스크립트 파일을 업로드 하고 내부 명령어를 실행하거나, 외부와 연결하여 시스템을 제어에 대한 약점

##### 6. 신뢰되지 않는 URL 주소로 자동 접속 연결(URL Redirection to Untrusted Site, Open Redirect) 
사용자로부터 받는 입력 값을 외부사이트주소로 사용하여 자동으로 연결하는 서버 프로그램은 피싱 공격에 노출되는 취약점 보유, 일반적으로 클라이언트에서 전송된 URL 주소로 연결하기 때문에 안전하다고 생각할 수 있으나, 해당 폼의 요청을 변조함으로서 공격자는 사용자가 위험한 URL 로 접속할 수 있도록 공격 약점


##### 8. XPath 삽입(Failure to Sanitize Data within XPath Expressions, XPath injection)

9. LDAP 삽입(Improper Neutralization of Special Elements used in an LDAP Query, LDAP Injection) 

10. 크로스사이트 요청 위조(Cross-Site Request Forgery) 

11. 디렉토리 경로 조작(Path Traversal)
11-1. (Relative Path Traversal) 
11-2. (Absolute Path Traversal) 

12. HTTP 응답 분할(Improper Neutralization of CRLF Sequences in HTTP Headers, HTTP Response Splitting) 

13. 정수 오버플로우(Integer Overflow or Wraparound) 

14. 보호 메커니즘을 우회할 수 있는 입력값 변조 (Reliance on Untrusted Inputs in a Security Decision) 

15. SQL : JDO(SQL Injection: JDO) 

16. SQL : Persistence(SQL Injection: Persistence) 

17. SQL 삽입 공격: mybatis Data Map(SQL Injection: mybatis Data Map) 

18. LDAP (LDAP Manipulation)

19. (External Control of System or Configuration Setting) 

22. (Process Control) 

23. 안전하지 않은 리플렉션(Use of Externally-Controlled Input to Select Classes or Code, Unsafe Reflection) 

24. 무결성 점검 없는 코드 다운로드(Download of Code Without Integrity Check) 

25. SQL : Hibernate(SQL Injection: Hibernate) 
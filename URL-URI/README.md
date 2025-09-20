## URI, URL

URI: 자원을 식별하기 위한 모든 문자열을 뜻함

URL: URI의 한 종류로 자원의 위치와 접근 방법까지 모두 가짐



URI ->  isbn:adf:123-2139 

URL -> http://localhost:8080/api/users/1

protocol, host, path까지 정확한 접근방법, 경로가 있음

## Spring boot에서 URI를 어떻게 처리할까

1. 모든 요청을 Dispatcher Servlet이 받는다
2. URI를 Handler mapping이 URI에 해당하는 컨트롤러 메서드 반환 후
3. Handler adapter가 실행
4. 그 후 메서드 실행
5. view resolver 아니면 MessageConverter 동작
6. dispatcher servlet에 반환
7. 
# Cookie And Session


Http는 기본적으로 stateless이다(상태를 저장하지 않는다).
우리가 사용하는 웹사이트들은 로그인 하면 로그인 한 후로 작업을 진행 할 수 있다. 이유가 무엇일까 ?

## Cookie
Cookie는 웹에서 사용자와 서버간의 상태를 저장하고 관리함 .

이름과 값을 가짐. 만료시간

## Session
로그인 하면 그 상태가 저장되는건 마법이 아니라 Session이다.


상태가 없는 http 프로토콜 상에서 한 사용자가 여러 요청을 보내도 한 상태를 유지하는것. 

-> 로그인 한 후 마이페이지 갔다가 다른 페이지가도 내 정보가 유지된다.

was 에서 session은 JSESSIONID 라는 쿠키를 사용해서 구별한다.

### WAS 에서의 Session 생성

```java

    HttpSession session = request.getSession();
    // -> Session이 있으면 가져오고 없으면 생성한다.
    HttpSession session = request.getSession(false);
    // -> Session 이 있으면 가져오고 없으면 null 
```


### Session 종료
session은 쿠키를 사용한다고 했다. 
cookie.setMaxAge(0) 이라고 하면 브라우저는 쿠키가 만료된것을 보고 삭제한다.


```java
    HttpSession session = request.getSession();
    session.invalidate();
    

```






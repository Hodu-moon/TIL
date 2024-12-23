# jwt 발급 후 Header에 넣어주는 방법

### Security
```java

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.formLogin(formLogin ->
                formLogin
                        .loginPage("/auth/login") // 사용자 정의 로그인 페이지
                        .usernameParameter("id") // 사용자명 파라미터 이름
                        .passwordParameter("password") // 비밀번호 파라미터 이름
                        .loginProcessingUrl("/login/process") // 로그인 처리 URL
                        .successHandler(new AuthSuccessHandler(authFeignClient))// jwt token 추가하기
//                        .successForwardUrl("/")
                        .permitAll() // 로그인 페이지 접근 허용
        );
        http.authorizeHttpRequests(authRequest -> {
            authRequest.requestMatchers("/auth/login", "/public/**").permitAll()
                    .anyRequest().authenticated();
        });

        // logout 시 쿠키 삭제하기
        http.logout( logout -> {
            logout.logoutUrl("/auth/logout").
                    deleteCookies("Authorization");
        });


        return http.build();
    }
```

```javascript

fetch('/login/process', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify({
    id: 'user', // usernameParameter에 매핑된 이름
    password: 'password', // passwordParameter에 매핑된 이름
  }),
});

```

이렇게 보내니 spring security filter를 타지 않았다. 

이유를 찾아보니 security의 loginProcessingUrl 은 application/x-www-form-urlencoded 형식만 사용 가능하다고 한다.

```javascript
fetch('/login/process', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded', // 전송 데이터 형식 설정
  },
  body: new URLSearchParams({
    id: 'user',       // usernameParameter와 동일하게 설정
    password: 'pass', // passwordParameter와 동일하게 설정
  }),
});

```

이렇게 보내니 spring security의 filter chain을 탄다.

이렇게 하면 나중에 브라우저의 session storage, local storage를 사용해서 jwt token을 사용할 수 있을 것이다.

그건 나중에 해보겠다. 



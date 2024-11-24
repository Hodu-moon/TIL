# Spring Security Config

dependency 추가

다양한 취약점 공격이 있음. 그것들을 막는 최소한의 기능들을 구현하고 있다.
선언적 보안 기능 제공 프레임워크

>  선언적 -> 데이터 소스를 만들어놓으면 흘러가는

## 인증 인가

인증 Authentication -> 사용자의 신원을 확인

인가 Authorization-> 인증된 사용자에게 특별한 리소스나 기능에 대한 접근권한

인증 --> 인가

## pom.xml

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

## @Configuration

```java
@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable); // !!! dl

        http.authorizeHttpRequests(authorizedRequest ->
                authorizedRequest
                        .requestMatchers(HttpMethod.POST, "/members").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/member/**").hasRole("MEMBER")
                        .requestMatchers("/google/**").hasRole("GOOGLE")
                        .requestMatchers("/private-project/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MEMBER", "ROLE_GOOGLE")
                        .requestMatchers("/public-project/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
        );

        // filter chain 인가요

        http.formLogin(Customizer.withDefaults());

        http.formLogin(
                (formLogin) -> {
                    formLogin.loginPage("/auth/login")
                            .usernameParameter("id")
                            .passwordParameter("password")
                            .loginProcessingUrl("/login/process");

                }
        );

        return http.build();
    }
    }


```


loginPage -> authentication 이 필요하면 /auth/login 을 호출함 ("/auth/login" Controller 나 레지스트리에 등록해야함 ) 

userParameter loginPage에서 id 라는 파라미터로 데이터가 올것임
password도 마찬가지 

loginProcessingUrl -> /login/process라는 url로 http요청이 들어오면 작동함

## 너무 많은 Redirection 
spring security를 켜놓고 무작정 formLogin을 키면 리디렉션이 엄청 발생할 수 있다.

### 이유

1. 보통 security 마지막에 anyRequest.authenticated()를 걸어준다.
2. 인증 (authentication) 이 필요해서 /auth/login 으로 요청을 보냄
3. /auth/login에 접근하려면 인증이 필요함.
4. 인증이 필요하니 /auth/login으로 또 보냄 
5. 반복.....


### 해결법

```java
        http.authorizeHttpRequests(authorizedRequest ->
                authorizedRequest
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated()
        );

```

인증이 필요할 때  /auth/login로 요청을 보낸다면 /auth/login의 접근권한을 모두로 바꿔주면 된다.

UsernamePasswordAuthenticationFilter가 동작!


## /auth/login 의 view

```html
<form action="/login/process" method="post">
    id : <input type="text" name="id" id="id">
    <br/>
    password : <input type="password" name="password" id="password">
    <button type="submit">
        로그인
    </button>
</form>
```
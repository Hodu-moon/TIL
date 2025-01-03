# JWT

## project 인증 인가 구현 

front - security 인증을 auth 서버로 보냄 

gateway - 인가 

eureka - gateway, authentication server, task server 등록

authentication server - 인증이 성공하면 jwt를 발급

task server - 

## front security 구조

### security config

```java

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private final AuthFeignClient authFeignClient;

    @Bean
    public SecurityFilterChain devSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);


        http.formLogin(formLogin ->
                formLogin
                        .loginPage("/auth/login") // 사용자 정의 로그인 페이지
                        .usernameParameter("id") // 사용자명 파라미터 이름
                        .passwordParameter("password") // 비밀번호 파라미터 이름
                        .loginProcessingUrl("/login/process") // 로그인 처리 URL
                        .successHandler(new AuthSuccessHandler(authFeignClient))// jwt token 추가하기
                        .permitAll() // 로그인 페이지 접근 허용
        );
        // dev 외의 환경에서는 제한된 접근만 허용
        http.authorizeHttpRequests(authRequest -> {
            authRequest.requestMatchers("/", "/auth/login", "/public/**",
                            "/css/**", "/js/**", "/images/**", "/join", "/test/**",
                            "/style.css").permitAll()
                    .anyRequest().authenticated();
        });

        // logout 시 쿠키 삭제하기
        http.logout(logout -> {
            logout.logoutUrl("/auth/logout").
                    deleteCookies("Authorization");
        });

        return http.build();
    }


    // Password Encoder, InMemory is Dev
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt 암호화 사용

    }


}

```

front 에서는 security filter를 걸어서 인증이 필요하면 login page로 가게함. 


### SuccessHandler
```java

@RequiredArgsConstructor
@Slf4j
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private final AuthFeignClient authFeignClient;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("success handler call ");
        log.info("authentication :{}", authentication);
        User principal = (User)authentication.getPrincipal();

        String username = principal.getUsername();
        log.info("username : {}", username);

        // TODO - jwt
        // 여기서 FeignClient로 Auth 서버에 보냄
        JwtLoginIdRequest jwtLoginIdRequest = new JwtLoginIdRequest();
        jwtLoginIdRequest.setId(username);

        ResponseEntity<TokenResponse> jwtToken = authFeignClient.getJwtToken(username);
        log.info("jwt token AccessToken : {}", jwtToken.getBody().getAccessToken());
        log.info("jwt token tokenType : {}", jwtToken.getBody().getTokenType());
        log.info("jwt token ExpiredIn : {}", jwtToken.getBody().getExpiredIn());
//          cookie

        // cookie 이름 바꾸기
        Cookie cookie = new Cookie("Authorization",
                 jwtToken.getBody().getAccessToken()
        );

        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(jwtToken.getBody().getExpiredIn());

        response.addCookie(cookie);

//        response.addHeader("Authorization", jwtToken.getBody().getTokenType() + " " + jwtToken.getBody().getAccessToken());
        response.sendRedirect("/");
    }
}

```

인증이 성공하면 auth server로 jwt를 요청함. 응답 받은 jwt를 cookie에 "Authorization"으로 넣어준다.

> 지금 상황 로그인에 성공한 후 browser cookie에 Authorization으로 jwt token이 있음

### task로 요청

task server로 요청을 보내려면 gateway를 통해서 보냄 

gateway로 요청을 보낼 때 FeignClient를 사용함 

예시

```java
@FeignClient(name = "memberClient", url = "${onebook.gatewayUrl}")
public interface MemberClient {
    @PostMapping("/task/members")
    MemberResponseDto registerRequest(@RequestBody MemberRegisterRequestDto memberRegisterRequestDto);
}
```

### Interceptor

gateway에서 인가를 할 때 쿠키에 있는 Authorization 값을 보고 인증이 됐는지 확인함. 그래서 Front Server -> Gateway로 보낼 때 Header에 jwt token을 붙히는 작업을 했음.

```java
@Component
@RequiredArgsConstructor
public class FeignRequestInterceptor implements RequestInterceptor {

    private final HttpServletRequest request;
    @Override
    public void apply(RequestTemplate requestTemplate) {

        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if("Authorization".equals(cookie.getName())){
                    String jwtToken = cookie.getValue();
                    requestTemplate.header("Authorization", "Bearer " + jwtToken);
                }
            }
        }

    }
}

```

## Gateway

git url https://github.com/Hodu-moon/Onebook-gateway/tree/develop()

```java
package com.nhnacademy.gateway.filter;


@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {

    private final OneBookJwtParser oneBookJwtParser;

    public JwtAuthFilter(OneBookJwtParser oneBookJwtParser) {
        this.oneBookJwtParser = oneBookJwtParser;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (exchange.getRequest().getPath().toString().equals("/auth/jwt")) {
            return chain.filter(exchange);
        }
        // 토큰 형식 검사 예시
        // 테스트 주석
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);
        // 실제 JWT 검증 로직 필요 (signature, 만료시간, 클레임 등)

        String id = null;
        try{
            id = validateToken(token);

            String finalId = id;
            exchange = exchange.mutate()
                    .request(builder -> builder.header("X-MEMBER-ID", finalId))
                    .build();
        }catch (ExpiredJwtException e) {
            return handleUnauthorized(exchange, "JWT token is expired");
        } catch (UnsupportedJwtException e) {
            return handleUnauthorized(exchange, "JWT token is unsupported");
        } catch (MalformedJwtException e) {
            return handleUnauthorized(exchange, "Malformed JWT token");
        } catch (Exception e) {
            return handleUnauthorized(exchange, "Invalid JWT token");
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1; // 우선순위를 높게 주고 싶다면 음수 값 지정
    }

    private String  validateToken(String token) {
        // TODO jwt 검증
        System.out.println("token : {}" + token);

        Claims body = oneBookJwtParser.getJwtParser().
                parseClaimsJws(token)
                .getBody();


        if( body != null){
            System.out.println("body !! " + body.toString());
            return (String)body.get("id");
        }

        throw new RuntimeException();
    }

    private Mono<Void> handleUnauthorized(ServerWebExchange exchange, String message){
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().add("Content-Type", "application/json");
        String response = "{\"error\": \"Unauthorized\", \"message\": \"" + message + "\"}";

        return exchange.getResponse().writeWith(
                Mono.just(exchange.getResponse().bufferFactory().wrap(response.getBytes()))
        );
    }
}

```

요청이 들어오면 Header에 Authorization이 있는지 본다.

있으면 인증 성공, 없으면 인증이 안된거라 401 Unauthorized 에러가 발생

그리고 인증이 필요없는 Path는 넘긴다.

task에서는  RequestHeader "X-MEMBER-Id"로 값을 받기 때문에 위에 gateway filter에서 Header에 jwt를 파싱해서 id를 넣어줬다.

```java
@GetMapping("/addresses/{addressId}")
    public ResponseEntity<GetMemberAddressResponse> getMemberAddressById(@RequestHeader("X-MEMBER-ID") Long memberId, @PathVariable Long addressId ){

        GetMemberAddressResponse resp = addressService.getMemberAddress(memberId,addressId);
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }
```

### front feign, task 

front feign

```java

@FeignClient(name = "AddressClient",  url = "${onebook.gatewayUrl}")
public interface AddressClient {

    @PostMapping("/task/addresses")
    ResponseEntity<AddMemberAddressResponse> addMemberAddress
            (@RequestBody AddMemberAddressRequest addMemberAddressRequest);

}
```

task api

```java
@PostMapping("/addresses")
    public ResponseEntity<AddMemberAddressResponse> addMemberAddress(@RequestHeader("X-MEMBER-ID") Long memberId ,
                                                                     @RequestBody AddMemberAddressRequest request){

        AddMemberAddressResponse resp = addressService.addMemberAddress(memberId, request);
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }
```

feign 에는 차이가 있다.

### jwt 만드는 부분, 검증하는 부분

```java
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtils {
    private final JwtProperties jwtProperties;

    public TokenResponse makeJwt(JwtMemberDto jwtMemberDto){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, jwtProperties.getExpirationTime());

        // TODO - jwt 합쳐지면 여기 수정
        SecretKey key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
        String jwtToken = Jwts.builder().claim("id", "test")
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(key)
                .compact();

        return new TokenResponse(jwtToken, jwtProperties.getTokenPrefix(), jwtProperties.getExpirationTime());
    }
}

```

jwt를 서명할 때랑 토큰을 풀 때 key가 필요한대

kesy.hmacShakeyFor 로 한다.


풀 때 
```java
  private String  validateToken(String token) {
        // TODO jwt 검증
        System.out.println("token : {}" + token);

        Claims body = oneBookJwtParser.getJwtParser().
                parseClaimsJws(token)
                .getBody();


        if( body != null){
            System.out.println("body !! " + body.toString());
            return (String)body.get("id");
        }

        throw new RuntimeException();
    }

```


```java
 public OneBookJwtParser(@Value("${jwt.secret}") String stringKey) {
        this.stringKey = stringKey;
        SecretKey key = Keys.hmacShaKeyFor(stringKey.getBytes());

        jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
    }
```
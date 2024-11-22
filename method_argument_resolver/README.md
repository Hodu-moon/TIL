# Method Argument Resolver 

Handler Method Argument Resolver

Controller에 ServletRequest 라던지 많은 파라미터를 받을 수 있다.

스프링에선 어떻게 가능한 것인지 알아보자

## HandlerMethodArgumentResolver

컨르롤러 메소드에 특정 파라미터가 있으면 공통된 로직을 이용하여 처리할 수 있도록 도와줌

내가 사용하는 많은 컨트롤러에서 Requester에 대한 정보를 알고싶다. 어떻게 처리 할 수있을까?

```java
@Getter
public class Requester{
    private String ip;
    private Locale lang;
}
```

## 구현1 Requester

### Controller 

```java
@PostMapping("/members")
public ResponseEntity addMember(@RequestBodty Member member, Requester requester){
    System.out.println(requester);
    return ResponseEntity.ok().build();
}

```

### Resolver

```java
public class RequesterResolver implements HandlerMethodArgumentResolver{
    private LocaleResolver localeResolver;
    public RequesterResolver(LocaleResolver localeResolver){
        this.localeResolver = localeResolver;
    }
    
    @Override
    public boolean supportsParameter(MethodParameter parameter){
        return parameter.getParameterType().equals(Requester.class);
    }
    
    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception{
        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        return new Requester(httpServletRequest.getRemoteAddr(), localeResolver.resolveLocale(httpServletRequest));
    }
}

```

만든 resolver를 configuration에 추가해주면 된다.

## 구현 2 Pageable argument Resolver

서버 개발자는 서버를 지켜야 한다. 사용자가 악의적으로 size를 100만개를 요청하면 막아야한다. 또 실 사용되는 서비스의 멤버는
수십 수만명일것이다. getMembers같이 멤버를 조회하는 메서드를 호출하면 다 주는게 아니라 잘라서 줘야한다.

이때 사용되는 기술은 Pagealbe이다. 

### Pageable이란?

Pageable은 Spring Data JPA에서 제공하는 인터페이스로 페이징(Pagination)과 정렬(Sorting) 정보를 캡슐화하여 대량의 데이터를 효율적으로 처리할 수 있도록 돕는 도구

* 기본 페이징을 page = 0, size = 10 
* max size 는 50으로 바꿔보자

```xml
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-commons</artifactId>
    <version>3.2.5</version>
</dependency>
```
### Controller

```java
@GetMapping("/members")
    public List<Member> getMembers(Pageable pageable){
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());
        return Arrays.asList(new Member("신건영", 20, ClassType.A));
    }
```

### Resolver
getParameter null check를 안해도 되는 이유는 page 와 size 가 없으면 resolveArgument를 호출하지 않는다.

```java

public class pagealbeArgumentResolver implements HandlerMethodArgumentResolver {
    
    @Override
    public boolean supportsParameter(MethodParameter parameter){
        return parameter.getParameterType().equals(Pagealbe.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception{
        
        
        webRequest.getParameter("page");
        webRequest.getParameter("size");
        
        int page = Integer.parseInt("page");
        int size = Math.min(Integer.parseInt("size"), 50);
        
        return PageRequest.of(page, size);
    }
}

```

!! resolver 등록하기 확인 후 다시 올릴것.

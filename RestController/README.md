# Rest Controller


### @Controller

```java

@Controller
public class HelloController{
    
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }
}

```

hello 라고 반환하면 -> DispatcherServlet 이 hello 를 던지고 ViewResolver가 동작하여 
view를 반환해줄것이다.

| hello 라는 문자열을 응답하고 싶다면 ?
### @Rest Controller

```java

@RestController
public class HelloController{
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }
}
----------------------------
// 이것과 동일하다.
@Controller
public class HelloController{
    @GetMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}

```

string hello를 반환할것이다.

Spring boot에선 왜 가능할까 ? 

## HttpMessageConverter

>  컨트롤러 메서드가 반환하는 객체를 HTTP 응답 본문으로 변환하여 클라이언트전송
데이터를 주고 받을 때 데이터를 컨버팅 해주는 역할 

RestController에서 "hello" 문자열을 반환해주는 이유? 
StringHttpMessageConverter가 존재하기 때문이다.

### MessageConverter 추가하기
```java

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(... )
    }
}

```

### Converter는 여러개이다 
반환하는 객체를 보고 어떻게 동작할 수 있을까?

Converter에는 support 메소드가 있고
그 값이 참이면 다룰수 있는것이고 거짓이면 다룰수 없다.


## 직렬화, 역직렬화

* 직렬화는 객체를 Json으로 만드는것이고
* 역직렬화는 Json을 객체로 만드는것이다.

### 직렬화

 1.예약어 "class"로 json을 내보내야 한다면?

```java
public class Member {
    private String name;
    private Integer age;
    @JsonProperty("class")
    private String clazz;

    public Member(String name, Integer age, String clazz) {
        this.name = name;
        this.age = age;
        this.clazz = clazz;

    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}


```
result 

```java
{
  "name" : "백종원",
  "age" : 50,
  "class" : "A"
}
```

2. 우리회사는 전부 문자열로 받고싶어요. (숫자도)

```java
@JsonSerialize(using = ToStringSerializer.class)
private Integer age;


---------------------
        "age" : "50",
```

이렇게 하면 숫자도 문자열로 바뀌어 직렬화 될것이다.

3. 소문자로 바꿔주세요
-> toString을 override하면 된다.

### 역직렬화

RequestBody로 하고

생성자에 @JsonCreator 를 지정해주거나 Enum이면 밑에처럼 처리하면 된다.


```java

public enum ClassType {
    A, B, C;

    @JsonCreator
    public static ClassType fromString(String str){
        for (ClassType value : ClassType.values()) {
            if (value.name().equalsIgnoreCase(str)) { // -> 대소문자 상관없이 
                return value;
            }
        }
        //default
        return A;
    }
    
    // 사용자가 오타내면 A로 해줘 
    // 소문자, 대문자 오면 

    @JsonValue
    public String toJson(){
        return this.name().toLowerCase();
    }
}

```

## Content Negotiation

클라이언트가 선호하는 표현 방식을 결정하는 역할
HTTP Request의 Accept를 본다.

클라이언트는 3가지 방법으로 응답 포멧을 결정 가능
1. 요청에서 URL 접미사 사용  localhost:8080/me.xml  -> 보안문제 중지
2. URL 매개 변수 사용 ..?format=json
3. 요청에서 Accept 헤더 사용


2번 URL 매개변수 사용은
```java
public class WebConfig implements WebMvcConfigurer {
    // ...

   @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.parameterName("format")
                .favorParameter(true).defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
    }
}
```

이렇게 하면 응답을 할 수 있다.

3번 요청에서 Accept헤더 사용은 MessageConverter가 동작한다.

서버에서 해석할 수 없는 타입이 들어오면 415에러가 발생함
415 Unsupported Media Type - 클라이언트가 준 컨텐트 타입을 해석 X


Converter 만들기

```java

public class CsvHttpMessageConverter extends AbstractHttpMessageConverter<Member> {

    public CsvHttpMessageConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
    }

    @Override
    protected Member readInternal(Class<? extends Member> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected boolean canRead(MediaType mediaType) {
        return false;
    }

    @Override
    protected void writeInternal(Member member
            , HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        outputMessage.getHeaders().setContentType(MediaType.valueOf("text/csv; charset=UTF-8"));
        try (Writer writer = new OutputStreamWriter(outputMessage.getBody())) {
       
        }

        return;
    }


}
```

## HandlerMethodArgumentResolver

우리는 콘트롤러에서 사용할 수 있는 인자들을 안다.
HttpServletRequest, HttpSErvletResponse, Model ...

어떻게 넣어주는 것일까?

```java
HandlerMethodArgumentResolver 을 구현해서 

public class RequesterResolver implements HandlerMethodArgumentResolver {
    private LocaleResolver localeResolver;
    public RequesterResolver(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Requester.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        // 로직
        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        return new Requester(httpServletRequest.getRemoteAddr(), localeResolver.resolveLocale(httpServletRequest));
    }
}

spring application context에 등록하는거임 
```

그러면 parameter에 resolver가 supports할 수 있는거면 값을 넣어준다.













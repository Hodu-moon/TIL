# Content Negotiation

클라이언트가 선호하는 표현 방식을 결정하는 역할

> 같은 API를 클라이언트가 호출하는 방법에 따라 다르게 응답함
> ex) json, xml


클라이언트는 3가지 방법으로 응답 포멧을 결정할 수 있음
* 요청 url 접미사 ex .xml, .json -> 보안문제 사용중지
* 요청에서 URL 매개 변수 사용 ex) ?format=json
* 요청에서 Accept 헤더 사용 (restful 원칙에 가장 적합)

결국 Content Negotiation은 적절한 HttpMessageConverter를 결정하는 역할임

## 요청에서 URL 매개 변수 사용

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

xml설정을 안해주고 xml로 보내달라 요청하면

Resolved [org.springframework.web.HttpMediaTypeNotAcceptableException: No acceptable representation]

이런 에러가 발생한다. 406 Not Acceptable 이겠지

xml을 처리할 수 있는 컨버터가 없는것이다. 

### 요청에서 Accept 헤더 사용

Accept : text/csv
Accept : application/json

이런식으로 해주면 된다.


# RestController Advice

ControllerAdvice처럼 에러를 공통적으로 잡아서 처리할 수 있는 RestController Advice가 존재함

## RestControllerAdvice (ResponseBody)

```java
@ResetControllerAdvice
public class RestGlobalExceptionHanderl{
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex){
        return new ResponseEntity<>("서버 오류가 발생", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

ExceptionHandler 쪽에 처리하려는 Exception 클래스를 적어둔다. 

# Controller Method

Spring boot프로젝트에서 Contrller를 다룰때 편의성을 위해 메소드를 선언할 때 
도움을 주는 기능이 있다. 

```java

    @Controller
    public class AController{
        ...
    }

```



## Controller Method에서 사용 가능한 method argument

* HttpServletRequest, HttpServletResponse, HttpSession, WebRequest
* Locale
* InputStream, OutputStream, Reader, Writer
* @PathVariable, @RequestParam, @RequestHeader, @CookieValue, @Value
* Map, Model, ModelMap, @ModelAttribute, @RequestBody
* Error, BindingResults, ... 

참고 : https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/arguments.html

더 많지만 현업에서도 자주 사용하는 argument는 이정도라고 한다.

### 배웠던것들 정리 
### @PathVariable 

GET 요청으로 localhost:8080/student/13 이 들어온다고 생각해보자.

```java
@GetMapping("student/{id}")
public void studentView(@PathVariable String id){
    //    id 는 13으로 사용할 수 있다.
}

```

### @RequestParam
get 요청으로 /student?id=25
또는 form에 id = 25 이런값이 들어온다면
```java
@GetMapping("/student")
public void studentView(@RequestParam("id") String id ){
    // id -> 25 로 파라미터 바인딩이 되어 손쉽게 사용 가능하다.
}

```


### @RequestHeader

Http Request Header에 들어오는 값을 읽을 수 있다.
```http request
GET /some-request HTTP/1.1
Host: localhost:8080
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36
```

@RequestHeader(name = "User-Agent")를 사용하면 Mac, PC, iOS, Android 등등을 알아낼 수 있다.
이를 통해 iOS에서만 처리해야하는 path를 지정해주거나 Android에서만 해야하는 작업을 지정해 줄 수 있다.

### @CookieValue

public void getStudent (@CookieValue(name = "SESSION") String sessionId)

이렇게 하면 cookie의 value를 알아낼 수 있다.

### Map, Model

@GetMapping("student")
public void studentView(Model model){
    model.addAttribute("student", student);
}

이런식으로 model을 넘겨줄 수도 있다.

@GetMapping("student")
public void studentView(Map Map){
    map.put("student", student);
}

하면 model에 값을 추가해서 넘겨줄 수도 있다.


### @ModelAttribute
방법은 2가지가 있다.
1. 메소드의 인자
2. 메소드에

 parameter binding을 해준다.

```java
        class Student{
          String name;
          String password;
          int age;
        }


        form 에 저 name, password, age를 담아서 보내면 
        
        @GetMapping("student")
        public void studentView(@ModelAttribute Student student){
            
    
        }
        
        바인딩을 시켜준다.
```

```java
    
    @ModelAttribute("student")
    public Student setStudent(){
        Student student = ...
        return student;
    }
    
    @GetMapping("/student/{id}")
    public void studentView(@PathVariable String id){
    
    }

    @GetMapping("/student/{id}/~~")
    public void studentView2(@PathVariable String id){

    }

    @GetMapping("/student/{id}")
    public void studentView3(@PathVariable String id){

    }

```

메소드 단에 붙히면 각 메소드를 시작하기 전 student를 model에 넣어준다.




## Controller Method에서 사용 가능한 return type

* ModelAndView, View

* Map, Model, ModelMap

* String

* void

* @ResponseBody

* POJO

참고: https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/return-types.html

### ModelAndView

```java
    @GetMapping("student")
    ModelAndView studentView(){
    ModelAndView mav = new ModelAndView("여기에 view name");
    
    mav.add -> model
    
    return mav;
    
}

```

생성자로 지정한 View로 보내줌


### String

return "student"

하면 student.html -> 가 보여진다.
# Spring MVC 개요 

* Spring Framework 이 직접 제공하는 Servlet API 기반의 웹 프레임워크
* Spring MVC는 다음의 구현체
  * MVC Pattern
  * Front Controller Pattern

### MVC Pattern

Model - View - Controller

* 애플리케이션의 개발 영역을 Model, View, Controller 세 가지 역할로 분류
* 역할을 나눔으로써 코드의 복잡도를 줄일 수 있는 장점

Controller가 요청을 받아서 처리한다. 처리하는과정에서 Model이 비즈니스 로직 및 데이터 처리 담당.
그 후 모델이 처리한 결과를 화면에 View가 보여준다.


MVC Pattern의 대표적인 구현체 JSP Model2

### Front Controller Pattern

* 모든 요청을 Front Controller에서 받아서 요청에 따라 실제 처리할 컨트롤러에 위임
* 인증, 인가 등 공통적으로 처리해야할 부분을 Front Controller에서 처리하기 용이


### Dispatcher Servlet
* Spring MVC 의  중심이 되는 서블릿임
* Controller로 향하는 모든 웹 요청의 Entry Point
* FrontController 디자인 패턴의 표현 


## Spring MVC

### Controller

* 사용자의 요청을 받아 어떻게 처리할 지 결정하고 요청을 분석
* 주로 비즈니스 로직을 처리하고 결과를 모델에 추가

### Model

* 컨트롤러가 뷰에 전달할 데이터를 보관
* 데이터와 비즈니스 로직을 결합하여 뷰에 필요한 정보 제공

### View
* 모델 데이터를 기반으로 클라이언트에게 HTML, JSON 등의 형식으로 응답을 생성


## Spring MVC 다시보기

Spring MVC는 MVC Pattern과 Front Controller Pattern의 구현체라고 하였다.

Front Controller Pattern -> Dispatcher Servlet

MVC Pattern 
* view -> Thymeleaf
* Model -> POJO (자바코드로 구현되어있는거 말함)
* Controller -> @Controller로 구현 (다른 페이지에서 설명 )



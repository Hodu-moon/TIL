# Reflection API

Reflection API는 런타임에 classpath를 스캔하여 클래스, 메소드, 변수 등을 조사하여 조작할 수 있게 해주는 API임.

이를 통해 컴파일 시점에 알 수 없는 클래스나 메서드에 접근하여 조작할 수 있습니다.



특정 annotation이 붙은 클래스, 메소드 가져오기.
예를들어 String을 반환하는 메소드 가져오기
특정 타입의 서브타입들을 가져오기 등이 가능해짐


## OCP
변경에는 열려있고 수정에는 닫혀있는 원칙을 잘 지킨 코드이다. 

Response type을 모두 list에 추가하는 작업을 한다고 생각해보자.

reflection을 사용하지 않았을 때
```
List<Response>list = new ArrayList<>();
list.add(new ResponseA());
list.add(new ResponseB());
...

```

reflection을 사용했을 때
```
Reflections reflections = new Reflections("package");
Set<Class<? extends Response>> clazz = reflections.subtype ...

for(Class<? extends Response>> class : clazz){
  list.add(class> 
}

```


이렇게 하면 Response타입의 구현체가 추가되도 코드의 수정은 이루어지지 않고 변경에는 자유롭다. 



스프링과 같은  DI를 프레임워크에서 자동으로 특정 어노테이션이 붙은 클래스를 검색하여 처리할 수 있다.

## 장점
유연성 -> 다양한 검색 기능을 제공. 런타임에 동적으로 클래스 로드, 특정 조건에 맞는 클래스나 매소드 쉽게 검색 가능.

강력한 성능 -> 성능이 좋다

## 단점
성능 저하 : 처음에 스캔을 쫙 해야해서 초반에만 느림

의존성 증가 : 유지보수 복잡하게 만들 수 있다.



> clazz Class<?> 타입의 객체를 의미하는 관용적 표현 




org.reflections 를 사용

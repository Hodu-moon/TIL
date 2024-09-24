# Reflection API

Reflection API는 런타임에 클래스, 메소드, 변수 등을 조사하여 조작할 수 있게 해주는 API임.

이를 통해 컴파일 시점에 알 수 없는 클래스나 메서드에 접근하여 조작할 수 있습니다.

특정 annotation이 붙은 클래스, 메소드 가져오기.
예를들어 String을 반환하는 메소드 가져오기
특정 타입의 서브타입들을 가져오기 등이 가능해짐

스프링에서도 사용한다.



> clazz Class<?> 타입의 객체를 의미하는 관용적 표현 


org.reflections 를 사용

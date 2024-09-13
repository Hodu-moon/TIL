# Mockito
단위테스트에서 사용한다.
실제 객체를 사용하는 대신 mock을 만들어 실제 객체처럼 동작하는 객체를 만든다.
mock객체의 동작을 지정할 수 있다.

### mock 생성

```java
List mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.clear();

verify(mockedList).add("one");


```

mock을 생성하면, mock은 모든 상호작용을 기억한다.
골라서 verify 할 수 있다.

### stubing ? 

특정 메서드 호출에 대해 원하는 동작(값)을 미리 정의하는 것

```java
    when( [여기에 mock객체]).thenReturn;
    이런식으로 사용 가능

//    검증할 때는
    // 이코드는 mock객체에 Get이 일어났냐 .
    verify([mock객체]).get(0);
    
    // 
```

객체의 상태를 조작할 수 있고 조작한 것들을 기억해서 검증할 수 있게 해주는 라이브러리이다. 



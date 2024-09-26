# StringBuffer와 StringBuilder

StringBuffer와 StringBuilder 모두 AbstractStringBuilder를 상속받는다.
append method를 살펴보면 모두 AbstractStringBuilder의 append()를 실행시킨다.

둘이 하는 역할은 똑같다. 내부적으로 byte[] 을 가지고 그것을 조작한다.  

그렇다면 둘의 차이점은 무엇일까?

## StringBuffer

Java doc에서 살펴보면 A Thread-safe, mutable sequence of characters.
라고 나와있다. 쓰레드 세이프티하다? -> 동기화된 메소드들을 구현한다.

> 동기화? A, B, C가 있으면 A,B, C가 동시에 실행 가능.

## StringBuilder

StringBuffer와 하는 역할은 똑같은데 thread-safety하지 않다.

## 써야하는 상황

Server - client 환경에서 StringBuffer나 StringBuilder를 쓰면 이점이 무엇일까. 

네트워크의 연결을 tcp로 한다고 가정하자. 클라이언트가 서버를 
```
h
e
l
l
o
```
라고 의도적으로 보내면 서버는 tcp 3way-handshake나 다른 부가적인 작업을 5번 해야한다.

하지만 버퍼를 넣어서 hello로 보내면 자원을 절약할 수 있다. 



## 결론
multi thread 환경에서 Buffer를 공유해서 사용해야 하는 일이 있다면
StringBuffer를 사용하고 공유할 일이 없다면 StringBuilder를 사용하자.


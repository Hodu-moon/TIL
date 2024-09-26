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



## 결론
multi thread 환경에서 Buffer를 공유해서 사용해야 하는 일이 있다면
StringBuffer를 사용하고 공유할 일이 없다면 StringBuilder를 사용하자.


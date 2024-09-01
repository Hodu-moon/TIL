## Iterator pattern

군집 객체는 iterable 이면 foreach문을 사용할 수 있다.
내부적으로 어떻게 동작할까

군집객체는 Iterable 타입을 가지고있고
iterable 인터페이스는 iterator를 반환한다.

iterator는 hasNext와 Next()를 구현한 콘크리트 클래스가 들어간다.

한번 생성되면 다시 생성해서 사용해야 한다.

내부적으로 배열을 사용하는 iterator를 구현해보았고
내부적으로 person을 3개만 가지고 있는 그룹에서는 iterator pattern과 같은 방식으로 동작하는
Enumerator, Enumerable을 만들어서 구현해보았다.



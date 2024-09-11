# Hash Code

hashcode란 객체를 식별하기 위한 int값이다.
쉽게 말해 객체가 가지고있는 데이터를 hash 함수를 통해서 계산된 정수값이다.

## Java hashcode()

String에는 각 문자열을 순회하면 31을 곱하고 누적해서 리턴함.
 
> 31을 쓰는이유 홀수이면서 소수이다 (shift연산 유리)
> 계산 속도가 빠름. (관행) 비트이동

## HashCode를 어디에 이용하는가.

두 객체의 HashCode가 다르면 다른객체임.

두 객체의 HashCode가 같으면 같을수도 있고 다를수도 있다. 
-> 이건 나중에 설명하겠다.

hashcode를 이용해 두 객체가 같은지 비교한 후 equals()를 이용해 같은지 확인한다.

이게 무슨말일까

HashSet이 있고  user1 - name: y age : 25, user2 - name:y age:25
가 있다고 생각해보자. HashSet.add(user1), HashSet.add(user2)라 하면 무슨일이 벌어질까

Set은 같은 객체는 못들어간다. 먼저 user1의 hashcode를 이용해 bucket위치를 찾고
그후 equals()를 통해서 같은 객체가 있는지 확인한다.

당연히 user1.hashcode() 와 user2.hashcode()는 다른값이 나오고
Set에는 추가된다.

그럼 이름과 나이가 같으면 같은 객체라고 인식하게 만들면 hashcode와 equals를 오버라이드해주면된다.


그러므로 hashcode가 같으면 객체가 같을수도 있고 다를수도 있다.

hashcode가 같으면 equals()를 한다는 말은 hashSet으로 테스트해보면 알 수 있다. 
# Stream
컬렉션이나 배열을 담고 원하는 결과를 얻으려면 for문을 돌렸다. -> 길고 재사용성이 떨어짐

List를 정렬할 땐 Collections.sort, 배열을 정렬할 땐 Arrays.sort를 사용해야함.

이러한 문제점을 해결 하려고 Stream 등장.

데이터 소스를 추상화 한 후 데이터를 다루는 데 자주 사용되는 메서드들을 정의해놓음. (추상화 ? -> 어떤 데이터소스가 들어와도 가능하게)

### stream 생성법

```java
String[] strArr = {"aaa", "bbb", "ccc"};
List<String> strList = Arrays.asList(strArr);

Stream<String> strStream1 = strList.stream();
Stream<String> strStream2 = Arrays.stream(strArr);

```

### 스트림은 데이터 소스를 변경하지 않는다.
 스트림은 데이터 소스로 부터 데이터를 읽기만 할 뿐 데이터 소를 변경하지 않는다.
원한다면 .collect 써서 해버림

### 스트림은 일회용
-> Iterator처럼 한번 사용하면 닫힘

### 스트림은 작업을 내부 반복으로 처리한다.

```java
import java.util.Objects;for(String str :strList)
        System.out.

println(str);

// 는 밑의 Stream과 같다.  

stream.

forEach(System.out::println);

------------------------
how?

void forEach(Consumer<? super T> action) {
    Objects.requireNonNull(action);
    
    for(T t : src){
        action.accept(t);
    }
}


```

### Stream의 연산

다양한 연산을 간단히 처리할 수 있음

> 스트림에 정의된 메서드 중에서 데이터 소스를 다루는 작업을 수행하는 것을 연산 (operation)이라고 함.

중간 연산과 최종연산이 있음.

중간 연산 : 연산 결과가 스트림. 연속해서 중간연산 가능
최종 연산 : 스트림의 요소를 소모하므로 단 한번만 가능

### 중간 연산 
| 중간 연산                                                                               | 설명             |
|-------------------------------------------------------------------------------------|----------------|
| Stream&lt;T&gt; distinct()                                                          | 중복을 제거         |
| Stream&lt;T&gt; filter(Predicate&lt;T&gt; predicate)                                | 조건에 안 맞는 요소 제외 |
| Stream&lt;T&gt; limit(long maxSize)                                                 | 스트림의 일부를 잘라낸다. |
| Stream&lt;T&gt; skip(long n)                                                        | 스트림의 일부를 건너뛴다. |
| Stream&lt;T&gt; sorted()<br/>Stream&lt;T&gt; sorted(Comparator&lt;T&gt; comparator) | 정렬한다           |
| Stream&lt;T&gt; map(Function&lt;T,R&gt; mapper)                                     | 스트림 요소를 반환     |

mapToDouble, mapToInt, mapToLong ...

flatMap, flatMapToDouble, flatMapToInt, flatMapToLong 이런것도 있음

### 최종 연산
| 최종 연산                                                                                                                                                                                                       | 설명                                                       |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------|
| void forEach(Consumer&lt;? super T&gt; action)<br/>void forEachOrdered(Consumer&lt;? super T &gt; action)                                                                                                   | 각 요소에 지정된 작업 수행                                          |
| long count()                                                                                                                                                                                                | 스트림의 요소의 개수 반환                                           |
| Optional&lt;T&gt; max(Comparator&lt;? super T&gt; comparator) <br/>Optional&lt;T&gt; min(Comparator&lt;? super T&gt; comparator)                                                                            | 스트림의 최대값, 최소값을 반환                                        |
| Optional&lt;T&gt; findAny() <br/> Optional&lt;T&gt; findFirst()                                                                                                                                             | 스트림의 요소 하나를 반환                                           |
| boolean allMatch(Predicate&lt;T&gt; P) // 모두 만족하는지 <br/> boolean anyMatch(Predicate&lt;T&gt; P)  <br/>boolean nonMatch(Predicate&lt;T&gt; P)                                                                | 주어진 조건에 따라 반환                                            |
| Object[] toArray()<br/> A[] toArray(IntFunction&lt;A[]&gt; generator)                                                                                                                                       | 스트림의 모든 요소를 배열로 반환                                       |
| Optional&lt;T&gt; reduce(BinaryOperator&lt;T&gt; accumulator) <br/> T reduce(T identity, &BinaryOperatorlt;T&gt; accumulator)                                                                               | 스트림의 요소를 하나씩 줄여가면서(리듀싱) 계산한다                             |
| R collect(Collector&lt;T, A, R&gt; collector) <br/> R collect(Supplier&lt;T&gt; supplier, BiConsumer&lt;R,T&gt; accumulator, BinConsumer&lt;R, R&gt; combiner)   | 스트림의 요소를 수집한다. 주로 요소를 그룹화하거나 분할한 결과를 컬렉션에 담아 반환하는데 사용된다. |


핵심 -> map(), flatMap() , reduce(), collect()

### 지연된 연산
최종 연산이 수행되기 전까지 중간 수행연산이 수행되지 않음.

### Stream&lt;Integer&gt; Intstream

boxing, unboxing -> Intstream이 더 효율적 

### 병렬 스트림
stream.parallel() 붙혀주면 된다.

## 스트림 만들기

### 컬렉션
Collection에는 stream()이 정의되어 있음 

### 배열 
Stream.of(T... values)

### 특정 범위의 정수

IntStream IntStream.range(int begin, int end) -> (1, 5) 면 1,2,3,4
IntStream IntStream.rangeClosed(int begin, int end) -> (1, 5) 면 1,2,3,4,5

## 중간 연산

### skip(), limit()
skip(3) -> 3개 요소 건너뛰고, limit(5) -> 5개 가져오기

### filter(), distinct()

filter(Predicate predicate) 가 들어오는데

```java

IntStream intStream = IntStream.of(1,2,3,4,5);
intStream.filter(i -> i % 2 == 0).forEach(System.out::println);

// 2, 4 출력

```

### 변환 

> Stream&lt;R&gt; map(Function&lt;? super T, ? extends R &gt; mapper)

StreamEx2.java 확인 


### mapToInt(), mapToLong(), mapToDouble()


나중에 나머지 정리하겠삼...


출처 : 자바의 정석
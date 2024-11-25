# JPA Enum

JPA에서 enum 타입을 매핑하는 방법은 @Enumerated 어노테이션을 사용하여 정의함.
2가지 방법이 있다.

### 1. EnumType.ORDINAL
* enum 값의 순서를 저장함.
* 데이터베이스엔 정수 값이 저장된다.
* 순서가 바뀌거나 enum값이 추가되면 데이터 무결성에 영향을 줌

### 2. EnumType.STRING
* enum의 이름 자체를 문자열로 저장
* 순서와 무관하고 가독성이좋고 확장성이 뛰어남



### 예제 EnumType.STRING
User가 존재하고
```java

@Entity
public class user{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    
}


-------------------------------------------------
public enum Role{
    USER, ADMIN
}
```

Role이 USER인 객체가 데이터베이스에 저장되면 "USER"라고 저장됌

### EnumType.ORDINAL
enum의 순서값 USER 면 0 , ADMIN이면 1


### 결론
ORDINAL을 사용하면 Enum값이 추가되거나 변경되면 데이터 무결성이 깨질 수 있어서 STRING을 사용.
가독성, 확장성 모두 뛰어남. 

메모리를 적게 사용하고 싶다면 ORDINAL도 괜찮겠지

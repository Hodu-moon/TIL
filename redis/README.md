# Redis

key-value 구조의 비정형 데이터를 저장하고 관리하기 위한 오픈 소스 기반의 비관계형 데이터베이스 관리 시스템

모든 데이터를 메모리로 불러와서 처리하는 메모리 기반 DBMS

Remote Dictionary Server

바로 나와야 하는건 -> 인메모리 디비 (RAM)


사용 이유
```text
인메모리 데이터 저장: 레디스는 데이터를 메모리에 저장하므로 매우 빠른 응답 시간을 제공합니다. 이는 주로 캐싱과 같이 빠른 응답이 필요한 용도에 적합합니다.

다양한 데이터 구조: 문자열, 해시, 리스트, 셋, 정렬된 셋, 등 다양한 데이터 구조를 지원하여 다양한 용도로 활욜할 수 있습니다.

내장된 복제 및 고가용성: 레디스는 마스터-슬레이브 복제를 지원하여 데이터의 복제본을 유지하고 고가용성을 보장합니다.

영속성 지원: 레디스는 디시크에 데이터를 저장하고 영속성을 제공하는 기능도 지원합니다. 이를 통해 재시작 후에도 데이터를 보존할 수 있습니다.

고성능 및 확장성: 레디스는 대규모 데이터 처리에도 뛰어난 성능을 보여줍니다. 또한 클러스터링을 통해 확장성을 제공합니다.

```

중요한건 
다양한 데이터 구조 


### 설치

MAC : brew install redis

### 시작
* brew services start redis
* redis-server

### 종료
* quit
* brew services redis stop

## 데이터 타입 
https://redis.io/docs/manual/data-types/

* String
* Lists
* Sets
* Hashes
* Sorted Sets


### 생각해볼것 1
다양한 자료구조를 지원한다는ㄱ서은 우리가 작업을 할 것에 따라 자료구조를 다르게 사용해야 한다는것임.

한개만 가져오는게 아니라 여러개를 가져오는 작업이 많다면 list와 map중에선 list를 사용하는게 좋고

삽입하는 작업이 많다면 arraylist, linked list 뭐사용? 

linked list 

### 생각해볼것 2

hash set에 emeber라는 키가 존재하고 그안에 숫자를 알고싶다면 key를 가져오는게 아니라 
heln 을 불러서 확인해보기.


## Redis Repository
저수준 인터페이스, 고수준 인터페이스

저수준 인터페이스 -> c언어 같은거 (처리할건 복잡하지만, 개발자의 능력에 따라 성능 좋아짐)
고수준 인터페이스 -> java 같은거 

저 -> jedis, lettuce

고 -> redisTemplate, RedisRepository

#  Redis Template  사용

## pom.xml
```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```
## Bean 

redis template을 bean으로 등록

```java
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> sessionRedisTemplate = new RedisTemplate<>();
        sessionRedisTemplate.setConnectionFactory(redisConnectionFactory);
        sessionRedisTemplate.setKeySerializer(new StringRedisSerializer());
        sessionRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        sessionRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        sessionRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        
        return sessionRedisTemplate;
    }
}

```

### GenericJackson2JsonRedisSerializer

이 Serializer의 성격은 json으로 직렬화 할 때 class 를 껴서 넣어준다.

예시를 보자

Member 가 존재하고 
```java
public class Member {
    private String id;
    private String name;
    private Integer age;
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("class")
    private ClassType clazz;
    private Role role;


    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public ClassType getClazz() {
        return clazz;
    }

    public Role getRole() {
        return role;
    }
}
```

redis 에 넣는다면 

```java
@Service
public class MemberService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private String HASH_NAME = "Member:";

    public Member saveUser(MemberCreateCommand member) {
        redisTemplate.opsForHash().put(HASH_NAME, member.getId(), member);
        return member;
    }

}

```

redis 결과

```java
5) "test2"
6) "{\"@class\":\"nhn.academy.model.Member\",\"id\":\"test2\",\"name\":\"testName1\",\"age\":\"501\",\"class\":\"A\",\"role\":\"ADMIN\"}"
```

앞에 클래스가 붙어서 저장된다. 그래서 역직렬화를 하려고 하면 저장된 클래스를 보고 바로 가능.

단점 : 서버 구조는 자주 바뀐다. 선호하는 Serializer는 아님
잠깐 쓰고 안쓰는곳에서 많이 씀

JacksonJsonRedisSerializer도 있음

```java

@Bean
public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<String, Object> sessionRedisTemplate = new RedisTemplate<>();
    sessionRedisTemplate.setConnectionFactory(redisConnectionFactory);
    sessionRedisTemplate.setKeySerializer(new StringRedisSerializer());
//        sessionRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    sessionRedisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));

    sessionRedisTemplate.setHashKeySerializer(new StringRedisSerializer());

//        sessionRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
    sessionRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));

    return sessionRedisTemplate;

}

    
    저장하면 
    1) "test"
    2) "{\"id\":\"test\",\"name\":\"testName1\",\"age\":\"501\",\"class\":\"A\",\"role\":\"ADMIN\"}"
    
    
  ObjectMapper objectMapper = new ObjectMapper();
  Member member = objectMapper.convertValue(o, Member.class);  
  
```

mapping작업이 따로 필요하다.


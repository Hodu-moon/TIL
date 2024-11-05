# ConditionalOnXXX

spring boot 는 간편한 사용을 지향한다. web을 의존한다면 타입이 WebApplication 으로 바뀌고 Jpa, Redis를 추가하면 바로 사용할 수 있다.

가능한 이유는 자동구성 때문이다. 

## 자동구성 ? 

코드가 복잡해서 잘 모르겠지만 ConditionalOnBean -> Bean이 존재하면 Bean으로 등록한다.



| 구분                           | 내용                                               |
|------------------------------|--------------------------------------------------|
| @ConditionalOnWebApplication | 프로젝트가 웹 애플리케이션이면 동작                              |
| @ConditionalOnBean           | 해당 Bean이 Spring Context에 존재하면 동작 (자동 구성에 사용)     |
| @ConditionalOnMissingBean    | 해당 Bean이 Spring Context에 존재하지 않으면 동작 (자동 구성에 사용) |
| @ConditionalOnClass          | 해당 클래스가 존재하면 동작                                  |
| @ConditionalOnMissingClass   | 해당 클래스가 존재하지 않으면 동작                              |
| @ConditionalOnResource       | 자원 (file 등 ) 존재하면 동작                             |
| @ConditionalOnProperty       | Property가 존재하면 동작                                |
| @ConditionalOnJava           | JVM 버전에 따라 동작여부 결정                               |
| @ConditionalOnWarDeployment  | 전통적인 war 배포 방식에서만 동작                             |
| @ConditionalOnExpression     | SpEL 의 결과에 따라 동작여부 결정                            |

> 자동구성 (AutoConfiguration) -> 자동 구성 클래스에만 사용하는걸 권장 Ex) 데이터베이스 Bean 이 존재할 때만 추가설정


### 사용법

Bean은 Component Scan시 등록된다. 

```java

@Configuration
public class greetingConfig{
    
    @Bean
    Greeting englishGreeting(){
        return new EnglishGreeting();
    }
    
    // Greeting Type의 bean이 없다면 bean 으로 등록한다. 
    @Bean
    @ConditionalOnMissingBean(Greeting.class)
    Greeting defaultGreeting(){
        return new KoreanGreeting();
    }
    
}


```

### 문제점 
 같은 config 패키지 안에 있을땐 순서가 위에서 아래로 보장되는것 같다. 다른 패키지 안에 있으면 순서가 보장이 되질 않아 주의해서 사용해야함

## Scan 대상이 되는 기준

알아본것 -> webApplication, bean, clsss, resource, property

Bean, resource, property -> bean을 다 스캔한 후 Condition에 따라 bean을 등록한다.

class, webApllication은 -> class가 없다면 스캔의 대상이 아니라 무시한다. WebApplication도 애플리케이션이 시작할때 타입이 정해져서  
WebApplication이 아니라면 스캔대상이 아니라 무시된다.
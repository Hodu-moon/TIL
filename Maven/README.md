# Maven - bulid tool 


maven의 환경설정을 하기 위해선 xml파일을 사용한다.

> xml파일은 < > data < >  <> 안에 속성이름을 적고 안에 데이터를적는다

Package란 무엇이냐 class들의 모음이다.

현재 패키지들은 Class명의 고유성을 보장한다. 
만약 회사 이름이 NHNacademy이면 도메인을 NHNacademy.com으로 사용할것인데
com.NHNacademy 로 패키지 이름을 지정한다.  -> 고유성 보장

관례다.

### 장점

프로젝트의 구조가 획일화 되어있다.
자바의 source파일은 src/java
컴파일된 결과는 target밑에 정해져있어서 소스가 어디에있는지 바로 알 수 있겠지.

참고로 mvn clean같은 명령어를 사용하려면 pom.xml이 있는 디렉토리에서 해야한다.

maven project를 새로 생성하였다면 바로 설정해야하는것은 java version 설정이다.
source는 21로 되어있고 실행될때는 11로 해도 되지만. 보통은 같이 맞추는편이라고 한다.


```
<properties>
    <maven.compiler.source> your java <maven.compiler.source>
    <maven.compiler.target> your java <maven.compiler.target>
<properties>
```
원래 mvn -f pom.xml clean 해야하지만
pom.xml이 있는 디렉토리에선 mvn clean 해도 된다.


## Life Cycle
* clean -> 독립적이다.
이친구는 target밑의 파일들을 다 지운다.
* validate -> pom.xml이 정상적으로 작성되어있는지.
* compile -> validate되고나서 target밑의 파일들이 만들어지고
* test -> class들이 정상적으로 적혔는지
* packaging -> 위의 과정들이 이상이 없다면 class결과물들을 jar파일로 묶어준다.
* verify -> packaging한 결과가 품질에 적합한지 ex) 테스트코드 100개중 80개를 통과하면 품질에 적합하다
생각했다면 통과
* install -> packaging된 jar 파일을 로컬에 돌려보는것
* site -> 프로젝트 관련 문서들
* deploy -> 원격지에 있는 서버에 배포하는것

우리가 특히 잘아야하는 건
validate -> compile -> test -> packaging 까지이다. 

나머지 verify, install, site, deploy는 다른 도구들과 함께 하기때문이다.





## jar
Jar는 자바에서 제공하는 압축파일이다. 
Compile된 클래스들과 resource(image, text)들이 모여있다.

패키징 한다. -> 내가만든 코드의 컴파일된 클래스파일들을 묶어서 배포한다.

서버 쪽에 자바를 구동할 수 있는 환경이 구축되어있다면 바로 실행시킬 수 있겠지

실행 가능한 jar파일을 어떻게 만드는가. 이걸 maven이나 다른 빌드 도구가 도와준다.

Java는 open source 생태계가 잘 구성되어있다.
Library를 xml로 추가해주면 외부 의존성을 maven이 추가해준다.

> 동적인 모바일 쪽에선 gradle을 사용하긴 하지만 웹쪽에선 흐름대로 가기때문에 maven사용해도 차이없다.


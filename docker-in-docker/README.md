## docker in docker는 왜쓸까

gitlab runner를 사용하며 CI 파일을 작성중에 테스트 환경으로 dind를 사용했다.



### 쓰는 이유

CI/CD 파이프 라인(Job) 안에서 또 다른 Docker 데몬 을 띄우는 방식

1. CI 파이프라인 안에서 Docker 이미지 빌드가 필요할 때 
    1. Spring Boot JAR을 만들고 그걸 docker build로 이미지로 감쌀 경우.
2. Job 마다 독립된 환경 보장
   1. 잡마다 새 Docker 데몬이 뜨니까 다른 잡이랑 충돌할 일이 없음.
   2. 러너를 여러 팀이 같이 쓰는 경우(멀티테넌시) 안정성 굳.
3. 호스트를 보호
    1. Dind는 호스트의 Docker를 건드리지 않음.
   2. 그래서 docker rm -f 같은 명령으로 EC2에 돌고 있는 컨테이너를 날려버릴 위험이 없다.

### 단점
* 느림 : 매 Job 마다 Docker 데몬을 새로 띄우니까 캐시도 잘 날아가고 속도가 떨어짐
* priviledged 권한 필요 
* 지속 배포엔 부적합 DinD에서 띄운 컨테이너는 Job이 끝나면 사라짐
### 요약
* DinD = CI 파이프라인 안에서 Docker 이미지를 빌드 테스트하려고 띄우는 독립 Docker 데몬
* 주 용도, "이미지 빌드 후 레지스트리에 push"
* 배포에는 잘 안씀



**DinD는 “빌드 전용 깔끔한 Docker 놀이터”**

**DooD는 “호스트 Docker에 직접 붙어서 진짜 배포”**




# 지속적인 통합과 배포 (CI / CD)

## CI
* Continuous Intergation
  * 지속적인 통합
* Build / Test 자동화 과정 

-> 버그 수정이나 새로 만드는 기능들이 메인 레포지토리에 주기적으로 빌드 되고 테스트 되는거

오래 쌓아서 머지 하면  충돌 -> 작은 단위

코드의 변경이 있다면 build 가 된 후 build가 잘 됐는지 test까지 자동화 . 
CI script 


## CD
* Continuous Delivery or Continuous Deployment
  * 지속적인 전달(지속적인 서비스 제공), 지속적인 배포

Delivery -> CI merge 돼서 Build, Test 된 결과를 release 하기전에 사람이 확인것

Deployment -> 위의 프로세스가 자동화 

개발단계 부터 배포까지 자동화를 통해서 


## CI / CD process

CODE -> BUILD -> TEST -> RELEASE -> DEPLOY


## CI/CD 종류

* jenckins - 무료
* CircleCI -> 부분 무료
* TravisCI -> 부분 무료 
* github actions - 부분 무료
* 

gitlab CICD를 사용하기 위해서 gitlab runner를 사용

1.ec2에 gitlab runner 를 다운받고.

2. gitlab ec2의 gitlab runner를 등록 후

3. .gitlab-ci.yml을 설정해주면 gitlab cicd 완성된다.





ec2와 깃랩 서버 사이엔 어떤 동작 방식이 있는건지 궁금했다.



**미리 결론을 말하면**

- EC2 -> Gitlab 방향으로 통신



즉 Gitlab이 EC2에 신호를 보내는게 아니라

EC2에서 띄운 Gitlab Runner가 Gitlab 서버에 주기적으로 요청(Polling)해서 Job을 가져옴



### 동작 흐름
1. EC2에서 Gitlab Runner를 띄움.

2. Runner는 Gitlab 서버에 주기적으로 HTTPS요청을 보냄

3. Gitlab 서버가 새로운 Job을 할당하면, Runner가 받아서 EC2 내부에서 실행.

   이때 실제 빌드, 테슽, 도커 빌드 등은 전부 EC2 안에서 일어남.

4. Runner가 실행 결과 (로그 , 상태)를 HTTP/HTTPS 응답으로 Gitlab에 전송함.





결론
gitlab runner 프로그램이 gitlab에 job이 있는지 계속 확인해서 job이 있다면 실행시키는 방식.

 
# Kaniko

Docker Daemon 없이도 컨테이너 이미지를 빌드 가능하게 해주는 도구

docker 가 없이 컨테이너 이미지를 만든다?? 무슨말일까 

### 도커 파일 

FROM , RUN, COPY 같은 지시어를 위에서부터 차례대로 읽음 

하나 읽을때마다 하나의 레이어가 생김

각 레이어는 그 시점의 변경 사항만 저장

깃 커밋이 쌓여서 최종 버전이 만들어지는 것과 비슷

실제 저장 형태 -> tar 아카이브 

### OCI 레이어
Open Container Initative: Docker가 주도해서 만든 표준 스펙

레이어 아까 tar layer
config.json : 환경변수, CMD, Entry Point
manifest, json : 어떤 레이어를 조합해야 최종 이미지가 되는지

OCI 레이어 -> tar레이어를 표준 방식에 맞춰 정의한것


### 그래서 동작은?

도커파일 분석 -> 실행하면서 스냅샷으로 새 레이어화 (이때 변경분만 저장 ) -> 완성된 레이어들을 OCI/Docker 이미지로 조립 -> 레지스트리에 푸쉬 
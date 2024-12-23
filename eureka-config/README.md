# Eureka

Service discovery pattern 

분산 환경에선 서로 간의 서비스를 원격 호출하기 위해서는 각 서비스의 IP 주소와 PORT 를 알아야 호출이 가능함.

클라우드 기반 MSA 어플리케이션인 경우에 네트워크 주소가 ({server-ip:server-port}) 동적으로 할당

클라이언트가 서비스를 호출하기 위해서 서비스를 찾는 매커니즘이 필요함

### 주요 기능
* 서비스를 등록하고 등록된 서비스 목록을 반환
* health check를 통해서 현재 서비스가 가능한 서비스를 판별한 후, 서비스 가능한 목록을 제공
* 서비스 간의 부하 분산 비율을 조정

## Client side discovery 

Service Client가 Service Registry에서 서비스 위치를 찾아서 호출하는 방식

## Server side discovery

proxy server (Spring Cloud Gateway)를 제공하고, 제공된 proxy server (Spring Cloud Gateway) 를 호출하면 Service registry(Eureka) 로 부터 등록된 서비스의 위치를 기반으로 라우팅 하는 방식

## Service registry 
Service Discovery 를 하기 위한 중요한 역할

사용 가능한 서비스 인스턴스의 목록을 관리

서비스 등록, 해제, 조회 API 제공

Netflix Eureka


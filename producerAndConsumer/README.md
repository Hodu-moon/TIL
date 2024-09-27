## Producer And Consumer pattern

분산 시스템에서는 작업을 작업을 수행하기 위해 서로 통신해야하는 여러 구성요사가 있는것이
일반적이다. 이 통신의 일반적인 패턴 중 하나는 생산자 소비자 패턴이다.
이 패턴에는 생산자와 소비자 라는 두가지의 유혀으이 구성요소가 있습니다.

생산자는 데이터나 이벤트를 생성하고 소비자는 데이터나 이벤트를 처리합니다.

## Http Web Server
대표적인 예이다. 

Producer : Web Server는 client로부터 Http request(요청) 을 생성하는 역할을 합니다.

Consumer : Http Request 는 Queue에 의해 배치되고 Worker Thread(작업자) 에 의해서
요청을 처리하고 적절한 응답을 반환함. 




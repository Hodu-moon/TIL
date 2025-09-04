### 개요

PostgreSQL은 객체-관계형 데이터베이스 관리 시스템(ORDBMS)

오픈소스이며, SQL 표준을 충실히 따르고 다양한 확장 기능을 제공

ACID 트랜잭션 보장 (원자성, 일관성, 독립성, 지속성)

대규모 데이터 처리와 확장성에 강점

### 특징

오픈소스 & 크로스 플랫폼: Linux, macOS, Windows 지원

데이터 타입 다양성: JSON, JSONB, Array, Hstore, UUID 등

확장성: 사용자 정의 함수(UDF), 사용자 정의 데이터 타입 추가 가능

MVCC (Multi-Version Concurrency Control): 높은 동시성을 지원, 읽기/쓰기 충돌 최소화

파티셔닝 & 인덱싱: LIST, RANGE, HASH 파티셔닝 / B-Tree, GIN, GiST, BRIN 등 다양한 인덱스 지원

확장 기능: PostGIS(지리정보), TimescaleDB(시계열 DB), FDW(Foreign Data Wrapper)

### 기본 구조

Database → Schema → Table 계층 구조

Tablespace: 실제 데이터 저장 위치 관리

WAL(Write Ahead Log): 장애 발생 시 복구를 위한 로그

### JSONB 저장 가능
NoSQL 유사 기능 제공


### 장점 vs 단점

장점

오픈소스 + 무료

다양한 데이터 타입과 확장성

트랜잭션 안정성 및 성능 우수

단점

MySQL 대비 초기 진입장벽이 다소 있음

관리/튜닝 난이도가 높은 편

### 사용 사례

금융 시스템 (ACID 보장 필수)

빅데이터 분석 (PostGIS, TimescaleDB)

JSONB 활용한 하이브리드 RDB + NoSQL


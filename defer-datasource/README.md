# defer - datasource initialization

개발할 때만 발생하는 문제인데 ddl을 실행시 자동으로 만들 때 sql로 데이터를 넣어 주고 싶다면
resources밑에 data.sql을 생성해서 추가한다 이 때 ddl 을 실행하기 전 dml이 실행되면 에러가 생김

해결방법 dml 실행시점을 늦추면 된다.

applcation.properties
```xml
spring.jpa.defer-datasource-initialization=true 
```
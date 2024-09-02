## Utf-8 한글 File Read
한글 가사 파일을 읽으면 이상한 외계어가 나온다. 이유는 JVM에서는 utf-16을 사용해서 char을 보여주고 우리가 흔히 쓰는 txt파일은 utf-8로 되어있기 때문이다.

그래서 utf-8로 읽는다고 설정해 주어야한다. 

---
## csv file
csv 파일은 형식이 정해져있다.

Name,Height,Weight
yy,100, 551

이런식으로 그래서 List<List<String>> 으로 받아서 읽고 쓰는것도 가능하다. 


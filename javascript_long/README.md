## java script long

```javascript
          Promise.all(promises)
                .then(base64Images => {
                    // 서버에 전달할 body
                    let requestBody = {
                        memberId: parseInt(userId, 10),
                        bookId: bookId,
                        grade: parseInt(rating, 10),
                        description: message,
                        imageUrl: base64Images
                    };
```

이런식으로 660305461748646819 얘를 보내고 싶었는데 서버에서는 660305461748646800 이렇게 받았다.

long의 범위를 벗어나서 근사값 00으로 처리한다고 한다. 

### 해결방법

Bigint를 사용하거나, String으로 보낸 후 서버에서 처리하는방법

### 신기했던점

String 으로 보내니 Hibernate에서 db의 타입이 BIGINT면 String을 BIGINT로 변환해주는 작업을 해주는게 신기했다.
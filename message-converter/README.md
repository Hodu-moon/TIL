# Http Message Converter

> @ResponseBody는 컨트롤러 메서드가 반환하는 객체를 HTTP 응답 본문으로 변환하여 클라이언트 전송
> 
> 데이터를 주고 받을 때 데이터를 컨버팅 해주는 역할


RestController에서 "hello" 문자열을 응답 할 수 있었던 이유 -> SpringHttpMessageConverter

스프링에서 기본으로 제공해주는 Converter들이 많은데 확인하는 방법 

```java

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        System.out.println(converters);
    }
}
```
[org.springframework.http.converter.ByteArrayHttpMessageConverter@1a0d313, org.springframework.http.converter.StringHttpMessageConverter@285bf5ac, org.springframework.http.converter.StringHttpMessageConverter@13908f9c, org.springframework.http.converter.ResourceHttpMessageConverter@329b331f, org.springframework.http.converter.ResourceRegionHttpMessageConverter@640a8f93, org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter@6335f04a, org.springframework.http.converter.json.MappingJackson2HttpMessageConverter@3739f3c9, org.springframework.http.converter.json.MappingJackson2HttpMessageConverter@74ce7fdf, org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter@1f26b992]

Json을 보내면 @RequestBody로 Json을 객체로 바꿔주는 직렬화 해주는것도 MappingJackson2HttpMessageConverter 덕분이고
객체를 응답하면 Json으로 역직렬화도 저 converter가 해준다.

## Csv Message Converter

csv로 http 요청이 들어오면 객체로 바꿔주고 , 응답을 보낼 때 csv로 바꿔주는  converter를 만들어서 적용시켜보자.

### 구현
extends AbstractHttpMessageConverter<?> 를 구현해주면 된다.

Converter

```java
package com.nhnacademy.restsecurity.common.converter;

import com.nhnacademy.restsecurity.member.domain.Member;
import com.nhnacademy.restsecurity.member.domain.Role;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public class CsvHttpMessageConverter extends AbstractHttpMessageConverter<Member> {
    public CsvHttpMessageConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
    }

    @Override
    protected Member readInternal(Class<? extends Member> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream body = inputMessage.getBody();



        try (InputStreamReader reader = new InputStreamReader(body)) {

            CSVParser csvParser = CSVParser.parse(reader, CSVFormat.DEFAULT.withHeader());

            Iterator<CSVRecord> iterator = csvParser.iterator();

            if (!iterator.hasNext()) {
                throw new IllegalArgumentException("CSV file is empty!");
            }

            CSVRecord firstRecord = iterator.next();
            return new Member(
                    firstRecord.get("id"),
                    firstRecord.get("name"),
                    firstRecord.get("password"),
                    Integer.parseInt(firstRecord.get("age")),
                    Role.fromStr(firstRecord.get("role"))
            );

        }


    }

    @Override
    protected void writeInternal(Member member, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream outputStream = outputMessage.getBody();

        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            Field[] declaredFields = member.getClass().getDeclaredFields();

            for (int i = 0; i < declaredFields.length; i++) {
                br.append(declaredFields[i].getName());
                if (i < declaredFields.length - 1) { // 마지막 필드가 아니면 쉼표 추가
                    br.append(",");
                }
            }
            br.append("\r\n");

            for (int i = 0; i < declaredFields.length; i++) {
                Field field = declaredFields[i];
                field.setAccessible(true);
                Object value = field.get(member); // 필드 값 가져오기
                br.append(value != null ? value.toString() : ""); // 값이 null이면 빈 문자열
                if (i < declaredFields.length - 1) { // 마지막 필드가 아니면 쉼표 추가
                    br.append(",");
                }
            }

            br.flush();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

```

핵심은 supports이다. 만약 client에서 accpet "text/csv"로 받길 원한다면 저 친구가 응답할 때 csv 컨버터가 작동한다.



```http request
### csv save member
POST localhost:8080/members
Content-Type: text/csv

id,name,password,age,role
test8,yh,1234,15,MEMBER

### csv 
GET localhost:8080/members/test8
Accept: text/csv

---- 결과 -----

id,name,password,age,role
test8,yh,1234,15,MEMBER

```

Config에 add converter 해주면 등록이 끝난다.

```java

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new CsvHttpMessageConverter());
    }
    
}
```

### 406 Not Acceptable

클라이언트가 Accept : text/csv로 요청을 보냈는데 서버에서 객체를 역직렬화하는 컨버터가 없다면 

406 Not Acceptable 가 발생함


### 415 Unsupported Media Type

클라이언트가 csv데이터로 전송햇는데 서버가 즉 @RequestBody에서 컨버팅 직렬화를 할 때 할 수 없다면 
415 Unsupported Media Type이 발생 


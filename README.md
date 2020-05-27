# Note

spring boot를 공부하면서 정리하는 저장소입니다

## Annotation

### @AutoWired
의존성을 자동으로 주입합니다
쉽게 말하면 클래스에서 다른 클래스의 객체를 생성해서 사용할 때 어노테이션을 붙여줍니다

## yml

### application.yml

```
spring:
  jpa:
    show-sql: true
```

위 코드를 추가하면 로그에 sql을 출력합니다
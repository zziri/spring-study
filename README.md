# Note

spring boot를 공부하면서 정리하는 저장소입니다

## @AutoWired
의존성을 자동으로 주입합니다
쉽게 말하면 클래스에서 다른 클래스의 객체를 생성해서 사용할 때 어노테이션을 붙여줍니다

## @OneToOne
일대일 매핑을 할때 사용합니다
contact 예제의 경우 Person 클래스에 Block 객체가 포함되는 상황이고 이 두 클래스 모두 @Entity로 테이블에 매핑됩니다
이때 @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) 어노테이션과 속성을 설정합니다

## @Query(), @Param()
Jpa Query Method 위에 선언해줍니다
value에 해당하는 쿼리를 실행해서 method가 실행되도록 합니다
nativeQuery = true로 설정해주면 네이티브 SQL을 사용할 수 있습니다 (default는 false입니다)

```java
@Query(value ="select person from Person person where person.birthday.monthOfBirthday = :monthOfBirthday")
List<Person> findByMonthOfBirthday(@Param("monthOfBirthday") int monthOfBirthday);
```
Person 객체인 person 중에 person.getBirthday().getMonthOfBirthday() 값이 monthOfBirthday(파라미터의 monthOfBirthday)와 같은 person들을 모두 반환하는 method로 동작하도록 합니다

## @Embedded, @Embeddable

## @Embedded & @Embeddable과 @OneToOne의 차이

## @GeneratedValue(strategy = GenerationType.IDENTITY)

## @Transactional

## DAO

## DTO

## Entity

## application.yml

```
spring:
  jpa:
    show-sql: true
```

위 코드를 추가하면 로그에 sql을 출력합니다
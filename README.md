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
어떤 클래스에 사용자 정의한 클래스를 포함하고 싶을 때(멤버로서) @Embedded로 사용 가능합니다  
그리고 포함하고자 하는 대상 클래스를 @Embeddable annotation을 달아줍니다  

## @Embedded & @Embeddable과 @OneToOne의 차이  
@OneToOne은 두개의 DB Table들을 매핑하는 것이지만 @Embedded는 그들을 포함한 클래스의 lifecycle을 따르게 됩니다  
contact 프로젝트의 경우 Person 객체가 update 혹은 delete될 때 Birthday도 따르게 됩니다  

## @GeneratedValue(strategy = GenerationType.IDENTITY)  
키 생성 전략을 IDENTITY로 설정합니다  
IDENTITY로 설정하면 기본 키 생성을 데이터베이스에 위임합니다  

## @Transactional  

## @ResponseStatus(HttpStatus.CREATED)  

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
# spring-boot-study  

스프링 부트를 공부하기 위한 저장소입니다  

## Source Code  

### PersonDto.java

```java
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class PersonDto {
    private String name;
    private String hobby;
    private String address;
    private LocalDate birthday;
    private String job;
    private String phoneNumber;
}
```

#### DTO  

DTO(Data Transfer Object)는 VO(Value Object)로 바꿔 말할 수 있습니다. 계층(Controller, View, Business Layer, Persistent Layer)간 데이터 교환을 위한 자바빈즈를 말합니다. 일반적인 DTO는 로직을 갖고 있지 않은 순수한 데이터 객체이며 속성에 접근하기 위한 getter, setter 메소드만 가지는 클래스입니다.  

VO는 개념은 동일하지만 read only 속성을 가지는 차이점이 있습니다.  


#### @Data  

롬복의 어노테이션 중 하나입니다.  
클래스 안의 모든 private 필드에 대해 `@Getter`, `@Setter`를 적용합니다.  
클래스 내에 `@ToString`, `@EqualsAndHashCode`를 적용시켜 method들을 override 합니다.  
`@RequiredArgsConstructor`를 지정합니다.  

#### @AllArgsConstructor(staticName = "of")  

롬복의 어노테이션 중 하나입니다.
모든 필드를 파라미터로 가지는 생성자를 만듭니다.  
staticName을 설정하면 그 이름으로 static method를 만듭니다.  

```java
PersonDto.of("martin", "programming", "판교", LocalDate.now(), "programmer", "010-1111-2222");
```

#### @NoArgsConstructor  

롬복의 어노테이션 중 하나입니다.  
파라미터가 없는 생성자를 만듭니다.  

### Person.java  

```java
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Where(clause = "deleted = false")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String name;
    private String hobby;
    private String address;

    @Valid
    @Embedded
    private Birthday birthday;
    private String job;
    private String phoneNumber;

    @ColumnDefault("0")
    private boolean deleted;

    public void set(PersonDto personDto) {
        if (!StringUtils.isEmpty(personDto.getHobby())) {
            this.setHobby(personDto.getHobby());
        }
        if(!StringUtils.isEmpty(personDto.getAddress())) {
            this.setAddress(personDto.getAddress());
        }
        if(!StringUtils.isEmpty(personDto.getJob())) {
            this.setJob(personDto.getJob());
        }
        if(!StringUtils.isEmpty(personDto.getPhoneNumber())) {
            this.setPhoneNumber(personDto.getPhoneNumber());
        }
        if (personDto.getBirthday() != null) {
            this.setBirthday(Birthday.of(personDto.getBirthday()));
        }
    }

    public Integer getAge() {
        if (this.birthday != null) {
            return LocalDate.now().getYear() - this.birthday.getYearOfBirthday() + 1;
        } else {
            return null;
        }
    }

    public boolean isBirthdayToday() {
        return LocalDate.now().equals(LocalDate.of(this.birthday.getYearOfBirthday(), this.birthday.getMonthOfBirthday(), this.birthday.getDayOfBirthday()));
    }
}
```

#### @Entity  

객체와 테이블을 매핑할 때 사용합니다.  
JPA를 사용해서 테이블과 매핑할 클래스는 반드시 @Entity를 붙여야 합니다.  


#### @Where(clause = "deleted = false")  

JPA에서 Person 객체를 가져올 때 항상 deleted가 false인 데이터만 가져오게 됩니다.  
person.deleted == true 인 경우 삭제된 것으로 간주하고 데이터를 가져오지 않습니다.  

#### @Id, @GeneratedValue(strategy = GenerationType.IDENTITY)  

@Id 어노테이션을 사용하면 해당 변수(attribute)를 PK(Primary Key)로 지정합니다  
@GeneratedValue 어노테이션을 사용하면 자동으로 키를 생성합니다  
strategy = GenerationType.IDENTITY 로 지정하면 PK 생성을 데이터베이스에 위임하며 auto increment 방식으로 자동 생성합니다  

#### @NonNull, @NotEmpty, @Column(nullable = false), @ColumnDefault("0")  

@NonNull은 롬복의 어노테이션이며 사용하면 해당 변수는 null을 허용하지 않습니다  
@NotEmpty 어노테이션을 사용하면 해당 변수는 null, ""를 허용하지 않습니다  
@Column(nullable = false) 어노테이션을 사용하면 데이터베이스 테이블에서 해당 컬럼의 null을 허용하지 않습니다  
@ColumnDefault("0"), 데이터베이스 테이블에서  해당 컬럼의 default값을 "0"으로 합니다  

#### @Valid, @Embedded  

@Valid는 @Min, @Max, @Size, @NotNull 등의 조건에 따라서 해당 객체가 유효한지 검사합니다  
@Embedded는 JPA Entity 안에 하나의 객체(birthday)를 포함할 때 사용합니다 해당 객체(birthday)에는 @Embeddable 어노테이션이 필요합니다  

#### public void set(PersonDto personDto)  

DTO 객체로 Person 객체를 세팅할 때 사용하는 함수입니다  

### PersonRepository.java

```java
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);
    @Query(value ="select person from Person person where person.birthday.monthOfBirthday = :monthOfBirthday")
    List<Person> findByMonthOfBirthday(@Param("monthOfBirthday") int monthOfBirthday);

    @Query(value = "select * from Person person where person.deleted = true", nativeQuery = true)
    List<Person> findPeopleDeleted();
}
```

#### PersonRepository extends JpaRepository
PersonRepository는 JpaRepository 클래스를 상속받는 interface 입니다
이 Repository를 이용해 검색 메소드를 정의할 수 있습니다

#### findByName()
name 속성으로 Person 객체를 검색하는 메소드입니다

#### findByMonthOfBirthday()
생일이 몇 월인지로 Person 객체를 검색하는 메소드입니다
생일 데이터는 Birthday 클래스 내에 연월일 데이터가 있으므로 쿼리에 "where person.birthday.monthOfBirthday = :monthOfBirthday" 조건을 붙여 실행합니다
매개변수가 쿼리의 "monthOfBirthday"와 매핑될 수 있도록 @Param 어노테이션을 적용합니다

#### findPeopleDeleted()
삭제된 Person 객체를 검색하는 메소드입니다
Person 클래스에 @Where(clause = "deleted = false") 어노테이션이 적용되어 있으므로 쿼리에 "where person.deleted = true" 조건을 붙여 실행합니다


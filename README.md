# Sticket - Server

## WAR 배포

1. `Edit configurations..` 
2. 왼쪽 상단 `+`
3. `Maven` 선택
4. `command line`에 `clean package -Dmaven.test.skip=true` 입력
5. `Apply` & `OK`
6. 방금 만든 설정을 선택 후 `Run`(`Alt` + `Shift` + `f10`)
7. `war`파일을 찾은 후 ec2의 `/var/lib/tomcat8/webapps/` 디렉터리 밑에 전송
    - `filezilla` 등 ftp 클라이언트 이용
8. 배포 완료. 테스트 ㄱㄱ

## 에러 해결 일지

### 2019-02-10, 배포 시 에러

War파일을 packaging 후 배포하여도 에러가 나면서 정상적으로 실행되지 않던 현상

- `pom.xml`에 `spring-boot-starter-tomcat`의 dependency를 추가해준 후
- `xxxApplication` class에 `SpringBootServletInitializer`를 상속시켜주었더니 해결

```java
public class SticketApplication {
//...
}
```

TO

```java
public class SticketApplication extends SpringBootServletInitializer {
//...
}
```

### 2019-02-19, JPA ID 설정 에러

`user_sticon_purchase`, `user_asset_purchase`, `user_quest`와 같은 매핑테이블들은
각각 다른 테이블들의 id를 외래키로 참조하고, 그 두 개를 묶어 기본키로 사용한다.
- (user_id, sticon_id)
- (user_id, asset_id)
- (user_id, quest_id)
 
1. 여기서 문제는 JPA Entity에서는 @Id 값이 없으면 에러가 난다.
```
org.hibernate.AnnotationException: No identifier specified for entity: com.ec.sticket.models.mapping.UserAssetPurchase
```

```java
public class UserAssetPurchase {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;
    //...
}
```

2. 그래서 찾아보니 각 참조하는 키마다 `@Id`를 붙여줘야한다.
```java
public class UserAssetPurchase {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;
    //...
}

```

하지만 이번엔 다음과 같이 매핑 에러가 난다.
```
org.hibernate.MappingException: Composite-id class must implement Serializable: com.ec.sticket.models.mapping.UserAssetPurchase
```

3. 더 찾아보니 이런 multi-pk의 경우엔 id를 묶어주는 class가 따로 있어야한다고 한다.
    - implements Serializable를 해야한다.
    - 필드로는 @Id를 붙여준 class들을 갖는다.
        - 이렇게 해도 실제 DB엔 int값으로 외부키를 가지게 되니 안심하자.
```java
public class UserAssetPurchaseKey implements Serializable {
    private User user;
    private Asset asset;
}
```

```java
@IdClass(value= UserSticonPurchaseKey.class)
public class UserAssetPurchase {


    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;
    //...
}
```

4. 해결!

### H2 console 접속

1. 스프링 실행 후 브라우저 주소창에 다음 url 입력 
[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

2. 스프링 부트에서 기본설정된 h2 console 정보를 입력

- Driver class: org.h2.Driver
- JDBC URL: jdbc:h2:mem:testdb
- User Name : sa
- Password : 
    - (패스워드 공백)

3. 접속!

### 2019.03.26, Spring Security 적용 이후 H2 Console 접속 안되던 현상.



#### 참고 문헌

- [Spring Security, H2 연동했을때 문제해결](https://www.slipp.net/questions/546)

### 2019.04.11, AWS RDS UTF-8 설정

이거 때문에 몇 시간 삽질한거지....
- `character-set-client-handshake` : 1
- `character_set_client` : utf8
- `character_set_connection` : utf8
- `character_set_database` : utf8
- `character_set_filesystem` : utf8
- `character_set_results` : utf8
- `character_set_server` : utf8
- `skip-character-set-client-handshake` : 0
- `collation_connection` : utf8_general_ci
- `collation_server` : utf8_general_ci

특히 이거 꼭 해줘야함;; 이거 안하면 위에서 설정해줘봤자 안 바뀜
- `character-set-client-handshake` : 1
- `skip-character-set-client-handshake` : 0

### 2019.05.24, Spring Security OAuth 2.0 + JWT

#### 참고 문헌

- [Spring security JWT 연동](https://yookeun.github.io/java/2017/07/23/spring-jwt/)
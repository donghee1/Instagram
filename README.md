#### Instagram
#### 2021.08.02 ~ 2021.08.05

* DB ERD 구성 (1차)
  * USER(회원정보테이블) 
  * USER_FOLLOWER(팔로워)
  * USER_FOLLOWING(팔로잉)
  * POST (게시물)
  * LIKE_COMMENT (좋아요 & 댓글)
   <img width="1114" alt="1차 테이블 구성" src="https://user-images.githubusercontent.com/48265181/128356267-e34aa745-fc30-42fc-bb9d-6437e211855c.png">
* GITHUB
  * branch 생성 및 삭제 테스트
  * Spring Boot Project 생성 후 PUSH, CLONE

#### 2021.08.06

* DB 선택
  * 기존 MySQL에서 Maria DB로 변경
  * 변경 사유
  * MySQL 초기 개발자가 따로 독립해서 만든 플랫폼으로 MySQL 문법과 비슷하다.
  * 무료로 기능사용이 가능하다.(스레드 풀, 백업 기능, 메모리 사용량 체크 등)
  * MySQL 보다 가볍고 속도가 빠르며 라이센스가 자유롭다.
  * 호환성이 뛰어나다.
* Maria DB 설치(로컬)
  * 추 후 AWS 서버 설정 시 추가할 예정
* STS 및 로컬 JDK1.8로 변경
* Redis
* 레디스 조사 및 사용 이유
* 레디스는 관계형 데이터 베이스가 아닌 NoSQL 개념이다.
* NoSQL 이란
  * NoSQL(Not Only SQL) : 비관계형 데이터베이스
  * 아주 많은 양의 데이터를 효율적으로 처리가 필요할 때 데이터의 분산처리, 빠른 쓰기 및 데이터의 안정성이 필요할 때 사용한다.
  * 키-값 이나 컬럼, 문서 형식의 데이터 모델을 주로 사용한다.
  * 특정 서버에 장애가 발생했을 때 데이터 유실이나 서비스 중지가 없는 형태의 구조가 장점.
* NoSQL 종류
  * 키-값 스토리지형 : Redis, Memcached
  * 열 지향 와이드 컬럼 스토어 : Cassandra,HBASE
  * 문서형 : MongoDB, Couchbase
  * 그래프형 : Neo4j 
* 레디스 사용 이유
  * 데이터베이스 부하를 줄이기 위해 병행해서 사용하기 위함.
  * 레디스는 메모리 기반의 키-값 구조 데이터 관리 시스템이며, 모든 데이터를 메모리에 저장하고 조회하기에 빠른 Read, Write 속도를 보장하는 비 관계형 데이터베이스이다.
  * 영속성을 지원하는 인메모리 데이터 저장소
  * 읽기 성능 증대를 위한 서버 측 복제를 지원한다.
  * 쓰기 성능 증대를 위한 클라이언트 측 샤딩을 지원한다.
  * 다양한 데이터형을 지원한다.(문자열,리스트,해시,셋,정렬된 셋)

#### 2021.08.07

* spring Security 조사
* 사용 이유
  * spring에서 제공해주는 보안 솔루션으로 개발자가 보안 로직을 짤 필요가 없어 편리하다.
  * 모둔 URL에 대한 인증 요구
  * 로그인 폼 생성 및 로그아웃 처리
  * CSRF 공격을 방어(CSRF : 웹사이트 취약점 공격중 하나로 사용자가 자신의 의지와 상관없이 공격자가 의도한 행위(수정,삭제,등록)를 웹사이트에 요청하게 만드는 공격을 뜻한다.)
  * 옥션 개인정보 탈취 사건
* 인증과 권한
  * 인증(Authentication)
  * 인증은 자기 자신이 누구인지 알려주고, 확인하는 일렬의 과정을 뜻한다.
  * 권한(Authorization)
  * 특정 부분에 접근할 수 있는지에 대한 권한 여부를 확인하는 과정을 뜻한다.

추가정보 확인 중

* 로직 작성 중

* MariaDB
  * local 서버 설정 완료(계정,Table)
  * UI는 Oracle Developer 연동
  * 문제점 
  * ERD 파일로 끌어왔으나 문법('' 제거) 및 에러 처리
  * database, table, colum 생성 후 관계 관련 문제점 발견하여 처리진행중

#### 2021.08.08
* DB ERD 수정 (2차)
  * 각 테이블 식별 처리시 중복된 컬럼 생성 확인되어 제거
  * 추후에 식별처리된 테이블 문제 발생 시 Mapping 테이블 생성하여 변경할 예정
  * Foreign Key 설정
  * DB TABLE 생성, CONSTRAINT 설정
  <img width="1037" alt="2차 테이블 수정" src="https://user-images.githubusercontent.com/48265181/128623269-9ee0bd07-158b-4e65-acae-30d05764de35.png">
  
* Spring Security
  * build.gradle에서 의존성 추가
    * implementation 'org.springframework.boot:spring-boot-starter-security'
      * Spring Security 사용을 위해
    * implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'
      * View에서 현재 로그인된 사용자의 정보를 가져오기 위해
  * 어노테이션 정리
    * @RequiredArgsConstructor 
      * Lombok 라이브러리에서 제공하는 어노테이션
      * 추가 작업을 필요로 하는 필드에 대한 생성자 생성
      * 초기화 되지 않은 모든 final 필드, @NonNull로 마크돼있는 모든 필드들에 대한 생성자를 자동으로 생성
    * @EnableWebSecurity
      * Spring Security 사용을 위한 어노테이션
      * 웹 보안을 활성화
      * 스프링 시큐리티가 WebSecurityConfigurer 구현하거나 컨텍스트의 WebSebSecurityConfigurerAdapter를 확장한 빈으로 설정되어 있어야 함.
      * tip : WebSebSecurityConfigurerAdapter를 확장하여 클래스를 설정하는 것이 가장 편하고 자주 쓰임.
    * @Configuration
      * Spring에서 Bean 등록하기 위한 어노테이션
      * 1개 이상 Bean 등록하고 있음을 명시
      * @Bean을 사용하는 클래스에는 반드시 해당 어노테이션을 활용하여 해당 클래스에 Bean을 등록하고자 함을 명시해주어야 함

* Web Security
  * web.ignoring().antMatchers  
    * Spring 보안
    * css, js, img는 무조건 접근이 가능해야 하기 때문에 설정된 모든 파일은 모든사람이 사용가능(인증무시) 
      * ex ) web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
* HttpSecurity
  * HTTP 관련 인증 설정 가능
  * http.authorizeRequests()
    * 경로, 권한 설정
    * antMatchers에서 설정한 사용자만이 사용가능 (인증됨)
      * .antMatchers("/login", "/signup", "/user").permitAll() : 누구나 접근 가능
      * .antMatchers("/").hasRole("USER") : USER, ADMIN만 접근 가능
      * .antMatchers("/admin").hasRole("ADMIN") : ADMIN만 접근 가능
      * .anyRequest().authenticated() : 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 함
  * http.formLogin()
    * 로그인 페이지, 기타 로그인 처리 및 성공 실패 처리를 위해
      * .loginPage("/login") : 개발자가 따로 만든 로그인 페이지를 사용하려고 할때 설정 (따로 설정하지 않으면 스프링이 제공하는 기본 로그인페이지 호출 "/login")
      * .defaultSuccessUrl("/") : 로그인 성공 시 이동되는 주소
  * http.logout()  
    * 로그아웃 처리를 위해
      * .logoutSuccessUrl("/login") : 로그아웃 성공 시 이동되는 주소
      * .invalidateHttpSession(true) : 로그아웃 성공 후 세션 전체 삭제 여부

* Thymeleaf
  * 개념
    * JAVA 라이브러리
    * 환경 양쪽에서 TEXT, HTML, XML, JavaScript, CSS를 생성할 수 있는 템플릿 엔진
    * Web Application에서 View 계층에 적합하지만, 오프라인 환경에서 많은 형태로 처리 가능
    * Spring MVC와의 통합 모듈 제공, Application에서 JSP로 만든 기능들을 완전히 대체 가능
  * 목표
    * 세련되고 잘다듬어진 템플릿 제공
    * Standard와 SpringStandard를 통해 강력하고 자연스러운 템플릿을 만들어낼 수 있어 정적 프로토타입도 브라우저에서 정확하게 동작
    * Dialect를 개발하여 타임리프 확장 가능
  * 사용이유
    * XML, XHTML, HTML5를 위한 자바 템플릿 엔진이며 다른 템플릿 포멧으로 확장 가능
    * 웹과 비웹 양쪽 환경에서 동작, 서블릿 API에 대한 강한 의존성 없음
    * 방언Dialects라고 불리는 기능 셋 모듈 기반
    * 다양한 템플릿 모드 제공
    * 완벽하고 확장 가능한 국제화 지원
    * 높은 성능의 파싱된 템플릿 캐시를 설정하여 입,출력을 줄여 최소화
    * 자동으로 DOCTYPE 번역
    * 필요시 템플릿 엔진 프레임워크 사용가능
    * 다양한 예제 애플리케이션을 포함한 문서화 제공

* OAuth 프로토콜
  * 개념
    * 인증과 인가가 핵심
    * Resource Server는 Resource Owner에게 인증을 받음 --> 해당 Client가 Resource에 접근할 수 있는지 확인
    * 인증된 Client는 자신의 목적에 따라 허용된 정보에만 접근가능
    * Client : Resource에 접근하려고 하는 주체
    * Resource : 서비스 제공자에 저장된 정보
    * Resource Owner : 커뮤니티 사용자
    * Resource Server : Resource를 가지고 있는 서버
  * 동작흐름
    * 1. Service Provider에 Client 정보 등록
      * 보통 Service Provider에서 계정 생성후 사용 등록 --> Client별로 고유로 식별할 수 있는 Client Id와 Secret Kye 생성
    * 2. Client의 인증 요청 & 권한 부여 요청  
      * Service provider에 권한 부여를 위해 Resource Owner의 승인을 요청
    * 3. 인증 성공 시 Authentication Code 부여
      * Resource Owner가 인증, 권한 사용 승인 --> Service Provider의 인증 서버는 Access Token을 발행받기 위한 Authentication Code를 302 상태 코드를 통해 
        Resource Owner가 모르게 슬쩍 Client에게 전달
    * 4. Access Token 발행 요청
      * Authentication Code를 발급 받을때 사용했던 Redirect Url, Resource Owner에게 받은 인가 코드를 가지고 Access Token을 발행 요청
    * 5. 리소스에 접근 요청
      * Client는 Token을 통해 Resource Server에서 사용자의 정보에 접근
      * 참고 : Access Token이 저장된 DB에 비교하여 지정된 Scope에 접근하는 게 맞는지 확인 후에 반환,보통 요청 헤더에 많이 셋팅 (Authorization: Bearer <ACCESS TOKEN>)
    * 6. Access Token 재발급 요청
       * Access Token이 만료 --> Refresh Token을 통해 새로 발급 가능
       * Access Token 발급시 보통 expires라는 항목으로 유효시간 받음 --> 유호시간 확인 가능하며, 지나면 Refresh Token으로 재발급
 
 #### 2021.08.10
  * Lombok
    * 웹 애플리캐이션에 사용되는 VO 객체들을 DB 컬럼과 맞게 private 변수화 하여 getter/setter 메서드로 정의한 후 toString으로 추가 정의한다.
    * 프로젝트가 커질수록 사용되는 VO 객체들은 기하급수적으로 늘어나고, 관리가 힘들어져 위 같은 일련의 과정을 자동화한 라이브러리가 Lombok이다.
  * 설치방법
    * 외부 의존성처리를 위한 라이브러리 설정
    * 또한 동작을 위한 .jar 파일을 다운 및 적용(우선 로컬적용) -> https://projectlombok.org/download
    * jar 파일 실행
    * 인스톨 화면에서 Specify location 클릭
    * STS/eclipse/springtoolsuit4.ini 파일 경로 설정 후 퀵 인스톨 실행
    * .ini 파일을 열어 Lombok.jar 가 추가됬는 지 확인 > -javaagent:/Users/donghee/Downloads/SpringToolSuite4.app/Contents/Eclipse/lombok.jar
  * 사용방법
    * 자주사용하는 애노테이션
    * @toString : 투스트링 메서드를 생성한다.@ToString(exclude={“제외값”}) 으로 재외할 문자열을 설정할 수 있다.
    * @Getter/Setter : getter/setter 메서드를 생성한다.
    * @EqualsAndHashCode : equals(), hashCode() 메소드를 생성한다.
    * @RequiredArgsConstructor : 모든 멤버 변수를 초기화시키는 생성자를 생성한다.
    * @Data : 위에 언급한 5가지 어노테이션 설정을 모두 포함한다.
    * 공식 라이브러리 : https://projectlombok.org/features/all
 
 #### 2021.08.12
* Spring Boot 프로젝트 테스트 코드
  * localhost 8080 연결 거부
    * 원인 : application.properties에서 spring.main.web-application-type=none 추가로 인한 연결거부
    * 해결 : spring.main.web-application-type=none 주석처리
  * private final UserService userService;시 에러
    * 원인 : Lombok 라이브러리에서 제공하는 @RequiredArgsConstructor 미추가
    * 해결 : @RequiredArgsConstructor 추가
  * Security 적용 후 Redis 로컬연동 테스트 중 http://localhost:8080/redis 호출시 http://localhost:8080/login만 호출
    * 에러내용 : Failed to authorize filter invocation [GET /redisTest] with attributes [authenticated]
    * 원인 : http.authorizeRequests().antMatchers.("/login","/user","/admin").permitAll()으로 되어있어 /redis 대한 접근 막혀있음
    * 해결 : ("/login","/user","/admin")에서 ("/**")로 변경 
  * 위 antMatch 적용 후 미반영
    * 원인 : @EnableWebSecurity 어노테이션 미추가
    * 해결 : @EnableWebSecurity 추가하여 스프링시큐리티 사용
* Spring Boot와 Redis 로컬 연동
  * build.gradle 의존성 추가
      * implementation 'org.springframework.boot:spring-boot-starter-data-redis' 
  * application.properties에 redis 정보 추가
      * spring.cache.type=redis
      * spring.redis.host=localhost
      * spring.redis.port=6379
  * 연동테스트(get, set)를 위한 Controller, Config 샘플 코드 개발
      * http://localhost:8080/redisTest 호출시 test(key), success(value) SET
          * keys * 로 확인
          <img width="202" alt="스크린샷 2021-08-12 오후 9 25 57" src="https://user-images.githubusercontent.com/48265181/129197449-06373722-d82c-48d4-ab35-63e70a84623c.png">
       * http://localhost:8080/redis/{key} 호출시 value값 조회
          <img width="642" alt="스크린샷 2021-08-12 오후 9 26 12" src="https://user-images.githubusercontent.com/48265181/129198192-11edf9ff-0d92-4a85-bbbd-47d42c663a08.png">

 
 #### 2021.08.13
* MariaDB 로컬 연동
  * build.gradle 파일 의존성 추가
       - implementation 'org.springframework.boot:spring-boot-starter-jdbc'
       - runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'
  * application.properties 수정
      spring.datasource.driverClassName=org.mariadb.jdbc.Driver
      spring.datasource.url=jdbc:mariadb://localhost:3306/hyj?characterEncoding=UTF-8&serverTimezone=UTC
      spring.datasource.username=root
      spring.datasource.password=admin
* Mybatis 매핑
  * build.gradle 파일 의존성 추가
       - implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.0'
  * application.properties 수정
       - mybatis.type-aliases-package=h.project.vo (매핑 정보)
       - mybatis.mapper-locations=mapper/**/*.xml (매퍼 xml파일 경로지정)
       - mybatis.configuration.map-underscore-to-camel-case=true
* 로그인 부분
  * Security를 이용한 로그인 구현
    * Controller, DAO, Repository, Service, Role 구현 진행중
        - user_id(USER테이블 아이디)로 Role 권한 설정
           ADMIN : 관리자
           USER : 사용자
* 회원가입 부분
  * Security를 이용한 회원가입 구현
    * Controller, DAO, Repository, Service, Dto 구현 진행중
    - 암호화 처리
    - valid 처리
    - 회원가입 테스트 html 작성
  * 테스트 진행 후 추가 작성 예정
 
#### 2021.08.14
 * DB ERD 수정 (3차)
   * 더미데이터 insert시 에러 발생
      * 에러 내용
          * Cannot add or update a child row: a foreign key constraint fails (`hyj`.`user`, CONSTRAINT `FK_USER_FOLLOWER_TO_USER_1` FOREIGN KEY (`follower_id`) REFERENCES `USER_FOLLOWER` (`follower_id`))
      * 에러 원인
          * foreign key로 설정되어 있는 테이블에 데이터가 없어 발생한 오류
      * 해결 내용
          * USER테이블에서 foreign key로 되어있는 테이블은 데이터가 없어도 참조만 하므로 식별관계에서 비식별 관계로 수정 후 insert 성공 확인
   * 각 테이블 시퀀스의 경우 인덱스 자동 증가를 위해 DDL을 사용하며 AUTO_INCREMENT 속성 추가
      * SQL 문법
          * ALTER TABLE {테이블명} MODIFY {컬럼명} INT NOT NULL AUTO_INCREMENT;
   * <img width="1232" alt="3차 테이블 수정" src="https://user-images.githubusercontent.com/48265181/129521968-682f8c1f-d843-4741-9105-eda5ff65f252.png">
 * thymeleaf 적용
   * application.properties 수정
      * spring.thymeleaf.enabled=true
      * spring.thymeleaf.prefix=classpath:/templates/
      * spring.thymeleaf.suffix=.html
      * spring.thymeleaf.mode=HTML5
      * spring.thymeleaf.cache=false
   * build.gradle 의존성 추가
      * implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
   * 적용 시 에러 발생
      * template might not exist or might not be accessible by any of the configured Template Resolvers
          * 문법오류 제일 포괄적임 (html 내 th:replace other:html 생성으로 인한 에러가 1개 이상일 경우)
      * org.thymeleaf.exceptions.TemplateInputException: An error happened during template parsing (template: "class path resource [templates/account.html]")
          * th:replace 사용 시 발생하는 에러. 리플레이스 경로에 파일이 없어서 발생
      * template might not exist or might not be accessible by any of the configured Template Resolvers (template: "account" - line 4, col 7)
          * 참조하던 html 파일에서 로직상에 <header></header><footer></footer><body></body> 3태그에서 th:replace 가 설정되어 있었고, 그로인해 마이트 및 파싱 에러 발생
 * Mybatis를 이용하여 간단한 SELECT 테스트
   * DTO, Mapper, VO를 이용하여 List 출력 테스트
   * SQL 문법 
      * SELECT * FROM USER
   * MariaDB SELECT 화면
      * <img width="1169" alt="DB 화면" src="https://user-images.githubusercontent.com/48265181/129522678-23bfcb47-441e-486d-84fa-4afa0b4ed46a.png">
   * http://localhost:8080/maria 호출 화면
      * <img width="1513" alt="웹" src="https://user-images.githubusercontent.com/48265181/129522738-be1ac4d2-fcd3-490a-b2c1-ba7e053e3e09.png"> 

 #### 2021.08.16
 * 간단한 로그인 구현
   * Config 구현 
      * 로그인 처리를 하기 위해 AuthenticationManagerBuilder 설정
      * AuthenticationManagerBuilder : Spring Security의 모든 인증을 관리하는 AuthenticationManager를 생성하는 class
      * passwordEncoder : Bean으로 등록한 passwordEncoder()를 사용하겠다는 설정
   * UserInfo 구현
      * 회원의 아이디로 사용될 userId, 비밀번호로 사용될 userPassword 생성
      * @Entity
          * 객체와 테이블 매핑 (JPA가 관리)
      * @Table(name = "USER")
          * Entity와 매핑할 테이블 지정 (name : 테이블명)
      * @NoArgsConstructor(access = AccessLevel.PROTECTED)
          * 기본 생성자 자동 추가
          * 기본생성자의 접근 권한을 protected로 제한
          * Entity 클래스를 project 코드에서 기본생성자로 생성하는 것은 막되, JPA에서 Entity클래스를 생성하는 것은 허용
      * @GeneratedValue(strategy = GenerationType.IDENTITY)
          * 키본 키 생성을 DB에 위임(AUTO_INCREMENT 와 같음)
      * @Column(name = "user_id")
          * 객체 필드를 테이블 컬럼에 매핑 (name : 컬럼명)
      * @Builder
          * Model 객체 생성시 Builder을 자동으로 추가 
   * Repository 구현
      * JpaRepository<> 상속받아 Repository 생성, UserDetailService에서 userId로 회원을 검색할 수 있도록 메서드를 정의
   * Service 구현
       * 로그인을 위해 MaraDB에서 가입된 user정보 조회
       * userId에 따라 admin, user 권한 부여
 * 로그인 테스트시 에러 발생
   * 에러 내용
      * JpaSystemException: No default constructor for entity
   * 에러 원인
      * 로직 수정 시 UserInfo에서 @NoArgsConstructor(access = AccessLevel.PROTECTED) 삭제
      * @Builder 사용시 @NoArgsConstructor 나 @AllArgsConstructor 사용해야 함
   * 에러 해결
      * @NoArgsConstructor(access = AccessLevel.PROTECTED) 추가 후 정상로그인 확인
 * 로그인 테스트 화면
   * USER 테이블에 회원정보 없거나 비밀번호가 일치 하지 않을 시 http://localhost:8080/user/login?error 
   * 성공시 임시로 http://localhost:8080/maria 이동
   * 로그인 index 화면
      * <img width="496" alt="로그인화면" src="https://user-images.githubusercontent.com/48265181/129528246-f720537c-c53a-4054-a858-953b63ef1f10.png">
   * 로그인 실패 화면
      * <img width="538" alt="에러시" src="https://user-images.githubusercontent.com/48265181/129528300-33c2f5c5-57f9-49f7-99a7-be7f11e51d7b.png">
   * 로그인 성공 시
      * <img width="1106" alt="성공시" src="https://user-images.githubusercontent.com/48265181/129528445-bccddcae-f837-4931-a55c-548ae994f22f.png">
 
 
 #### 2021.08.19
 * 로그인
   * 소셜 로그인 진행
   * 1. kakao REST API 연동을 위한 애플리케이션 신청
   * 2. kakao REST API 연동을 위한 application.properties 추가
     spring.security.oauth2.client.registration.kakao.client-id=13396affec7c4b602ce59a9d9be4c8df
     spring.security.oauth2.client.registration.kakao.client-secret=3o9qmFBiHKrgzgKWsdcApPCio8b5j9Ze
     spring.security.oauth2.client.registration.kakao.redirect-uri=http://localhost:8080/login/oauth2/code/kakao
     spring.security.oauth2.client.registration.kakao.authorization-grant-type=authorization_code
     spring.security.oauth2.client.registration.kakao.scope=account_email
     spring.security.oauth2.client.registration.kakao.client-name=kakao
     spring.security.oauth2.client.registration.kakao.client-authentication-method=POST

     spring.security.oauth2.client.provider.kakao.authorization-uri= https://kauth.kakao.com/oauth/authorize
     spring.security.oauth2.client.provider.kakao.token-uri=https://kauth.kakao.com/oauth/token
     spring.security.oauth2.client.provider.kakao.user-info-uri=https://kapi.kakao.com/v2/user/me
     spring.security.oauth2.client.provider.kakao.user-name-attribute=id

    * 3. secuity와 oauth2를 이용한 kakao 연동
      .oauth2Login() //ouath2 로그인을 위해
      .defaultSuccessUrl("/maria") //oauth2 인증 성공시 이동될 url(임시)
      /*로그인 성공시 유저정보를 가지고 userservice에서 처리*/
      .userInfoEndpoint()
      .userService(userservice);
    * 4. Attributes, UserInfo, Dto, Repository, Service 연동테스트 로직개발
         - 로그인 화면까지는 노출되나 아이디, 비밀번호 입력 후 로그인하면 500에러 발생
      원인파악중
    * 5. Maria DB 연동로직에 소셜로그인 로직 추가하니 마리아DB에 있는 로그인정보로 
         로그인실패되는 현상 발생
         원인파악중
 * 회원가입
   * 임시 회원가입 페이지 연동
   * 기존 ajax 방식과는 다르게 springboot + spring security + 타임리프 를 사용하여 통신
   * th:action 부분에 회원가입 인터페이스 mapping 정보 저장
   * 저장할 데이터들은 input에 th:field 값을 추가하여 값을 전달받아 컨트롤러에 보내준다.
   * spring security 에서 문제발생
   * 맨 처음 회원가입 후 시큐리티에서 제공하는 암호화를 password 적용하는 데 성공
   * 이후 추가 가입 시 비밀번호 에러 발생
   * 원인 파악 중
 
 #### 2021.08.20
* 소셜로그인 구현
   * kakao 로그인
       * 에러 내용
           * 잘못된 요청입니다. (KOE205)
       * 에러 원인
           * spring.security.oauth2.client.registration.kakao.scope 부분에 해당되지 않은 정보추가하여 발생
       * 해결 내용
           * 수정전
	          	- spring.security.oauth2.client.registration.kakao.scope=account_email, profile
           * 수정후
	          	- spring.security.oauth2.client.registration.kakao.scope=profile_nickname,account_email,profile_image
   * Maria DB 연동로직에 소셜로그인 로직 추가하니 마리아DB에 있는 로그인정보로 로그인실패되는 현상 발생
       * 에러 내용
           * SQL Exception 발생
       * 에러 원인
           * 소셜로그인에 필요한 객체를 @Column선언하니 마리아DB에 해당 컬럼이 없어 발생한 문제
           * 마리아DB와 동일한 정보를 가지고 있는 컬럼으로 변경하니 마리아DB, 카카오 로그인 모두 성공

   * Facebook 로그인
       * properties 추가
           * spring.security.oauth2.client.registration.facebook.client-id=527316975006896 (앱ID)
           * spring.security.oauth2.client.registration.facebook.client-secret=532b6844dcc14809b9f4b38bbce85a7c (앱 시크릿 코드)
           * spring.security.oauth2.client.registration.facebook.scope=public_profile,email
           * spring.security.oauth2.client.provider.facebook.user-info-uri=https://graph.facebook.com/me?fields=email,name,locale
       * Facebook 연동은 되나 안전하지 않은 페이지에서 액세스 토큰을 가져오거나 이 앱에 로그인할 수 없습니다. https://로 페이지를 새로 고쳐보세요 오류리턴
           * 원인 : SSL 인증서가 없어 발생한 원인으로 파악
           * 개발테스트를 위한 오픈SSL이나 구글로그인으로 대체할 에정
* 회원가입 진행상황
   * springSecurity + mariaDB 연동 성공
   * 소셜 회원가입 진행중
       * DB Error 연결 중 문제발생
          * 테이블을 찾지 못한다는 에러 발생 
          * 확인결과 DB명.dao class 명으로 설정되어 있음. -> DB명.user table명으로 설정되어야함
          * 해결방안 : dao @Table(name="user") 로 테이블 설정 후 실행.

#### 2021.08.21
* Cloud Computing Setting
  * AWS EC2 및 RDS 설정
* EC2
  * EC2 생성
    * java 1.8 jdk 설치
    * timezone 설정
    * git 설치 및 instagram 프로젝트 clone
    * 빌드 테스트 중 에러발생 -> jdk 문제로 확인 추가 확인 중

* RDS
  * RDS 생성
  * RDS 보안그룹 추가
  * Mysql 접속 허용 설정
  * sql Developer 임포트 실패
    * 에러 내용 : Communications link failure The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.
    * 조치 내용 : sql Developer 내 외부 JDBC 드라이버 교체 
    * before : mysql-connector-java-8.0.26.jar
    * after : mysql-connector-java-5.1.43-bin.jar + mariadb-java-client-2.1.0.jar

#### 2021.08.22
* REST(Representational State Transfer) 란
  * 자원을 이름(자원의 표현)으로 구분하여 해당 자원의 상태(정보)를 주고 받는 모든 것을 의미한다.
  * REST는 기본적으로 웹의 기존 기술과 HTTP 프로토콜을 그대로 활용하기 때문에 웹의 장점을 최대한 활용할 수 있는 아키텍처 스타일이다.
  * REST는 네트워크 상에서 Client와 Server 사이의 통신 방식 중 하나
* 구체적인 개념
  * HTTP URI(Uniform Resource Identifier)를 통해 자원(Resource)을 명시하고, HTTP Method(POST, GET, PUT, DELETE)를 통해 해당 자원에 대한 CRUD Operation을 적용하는 것을 의미한다.
    * 즉, REST는 자원 기반의 구조(ROA, Resource Oriented Architecture) 설계의 중심에 Resource가 있고 HTTP Method를 통해 Resource를 처리하도록 설계된 아키텍쳐를 의미한다.
    * 웹 사이트의 이미지, 텍스트, DB 내용 등의 모든 자원에 고유한 ID인 HTTP URI를 부여한다.
* 장단점
  * 장점
    * HTTP 프로토콜의 인프라를 그대로 사용하므로 REST API 사용을 위한 별도의 인프라를 구출할 필요가 없다.
    * HTTP 프로토콜의 표준을 최대한 활용하여 여러 추가적인 장점을 함께 가져갈 수 있게 해준다.
    * HTTP 표준 프로토콜에 따르는 모든 플랫폼에서 사용이 가능하다.
    * Hypermedia API의 기본을 충실히 지키면서 범용성을 보장한다.
    * REST API 메시지가 의도하는 바를 명확하게 나타내므로 의도하는 바를 쉽게 파악할 수 있다.
    * 여러가지 서비스 디자인에서 생길 수 있는 문제를 최소화한다.
    * 서버와 클라이언트의 역할을 명확하게 분리한다.
  * 단점
    * 표준이 존재하지 않는다.
    * 사용할 수 있는 메소드가 4가지 밖에 없다.
      * HTTP Method 형태가 제한적이다.
    * 브라우저를 통해 테스트할 일이 많은 서비스라면 쉽게 고칠 수 있는 URL보다 Header 값이 왠지 더 어렵게 느껴진다.
    * 구형 브라우저가 아직 제대로 지원해주지 못하는 부분이 존재한다.
      * PUT, DELETE를 사용하지 못하는 점
      * pushState를 지원하지 않는 점
* REST 사용 이유
  * 애플리케이션 분리 및 통합
  * 다양한 클라이언트의 등장
    * 최근의 서버 프로그램은 다양한 브라우저와 안드로이폰, 아이폰과 같은 모바일 디바이스에서도 통신을 할 수 있어야 한다. 이러한 멀티 플랫폼에 대한 지원을 위해 서비스 자원에 대한 아키텍처를 세우고 이용하는 방법을 모색한 결과, REST에 관심을 가지게 되었다.
 * REST 구성 요소
   * 자원(Resource): URI
     * 모든 자원에 고유한 ID가 존재하고, 이 자원은 Server에 존재한다.
     * 자원을 구별하는 ID는 ‘/groups/:group_id’와 같은 HTTP URI 다.
     * Client는 URI를 이용해서 자원을 지정하고 해당 자원의 상태(정보)에 대한 조작을 Server에 요청한다.
   * 행위(Verb): HTTP Method
     * HTTP 프로토콜의 Method를 사용한다.
     * HTTP 프로토콜은 GET, POST, PUT, DELETE 와 같은 메서드를 제공한다.
   * 표현(Representation of Resource)
     * Client가 자원의 상태(정보)에 대한 조작을 요청하면 Server는 이에 적절한 응답(Representation)을 보낸다.
     * REST에서 하나의 자원은 JSON, XML, TEXT, RSS 등 여러 형태의 Representation으로 나타내어 질 수 있다.
     * JSON 혹은 XML를 통해 데이터를 주고 받는 것이 일반적이다.
 * REST API 란
   * API(Application Programming Interface)란
     * 데이터와 기능의 집합을 제공하여 컴퓨터 프로그램간 상호작용을 촉진하며, 서로 정보를 교환가능 하도록 하는 것
   * REST API의 정의
     * REST 기반으로 서비스 API를 구현한 것
     * 최근 OpenAPI(누구나 사용할 수 있도록 공개된 API: 구글 맵, 공공 데이터 등), 마이크로 서비스(하나의 큰 애플리케이션을 여러 개의 작은 애플리케이션으로 쪼개어 변경과 조합이 가능하도록 만든 아키텍처) 등을 제공하는 업체 대부분은 REST API를 제공한다.
 * REST API의 특징
   * 사내 시스템들도 REST 기반으로 시스템을 분산해 확장성과 재사용성을 높여 유지보수 및 운용을 편리하게 할 수 있다.
   * REST는 HTTP 표준을 기반으로 구현하므로, HTTP를 지원하는 프로그램 언어로 클라이언트, 서버를 구현할 수 있다. 즉, REST API를 제작하면 델파이 클라이언트 뿐 아니라, 자바, C#, 웹 등을 이용해 클라이언트를 제작할 수 있다.
 * RESTful 란
   * RESTful은 일반적으로 REST라는 아키텍처를 구현하는 웹 서비스를 나타내기 위해 사용되는 용어이다. ‘REST API’를 제공하는 웹 서비스를 ‘RESTful’하다고 할 수 있다.
   * RESTful은 REST를 REST답게 쓰기 위한 방법으로, 누군가가 공식적으로 발표한 것이 아니다. 즉, REST 원리를 따르는 시스템은 RESTful이란 용어로 지칭된다.
 * RESTful 목적
   * 이해하기 쉽고 사용하기 쉬운 REST API를 만드는 것
   * RESTful한 API를 구현하는 근본적인 목적이 성능 향상에 있는 것이 아니라 일관적인 컨벤션을 통한 API의 이해도 및 호환성을 높이는 것이 주 동기이니, 성능이 중요한 상황에서는 굳이 RESTful한 API를 구현할 필요는 없다.

출처 : https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html

정리글이 이해 안될 떄 : https://mangkyu.tistory.com/46

* SOAP(Simple Object Access Protocol)
  * 개념
    * SOAP는 다른 언어로 다른 플랫폼에서 빌드된 애플리케이션이 통신할 수 있도록 설계된 최초의 표준 프로토콜이다. 
    * 프로토콜이기 때문에 복잡성과 오버헤드를 증가시키는 빌트인 룰을 적용하므로, 페이지 로드 시간이 길어질 수 있습니다. 그러나 이러한 표준은 빌트인 컴플라이언스를 제공한다는 의미이므로, 기업에서 선호하는 방식이기도 하다. 
    * 빌트인 컴플라이언스 표준에는 보안과 안정적인 데이터베이스 트랜잭션의 기본 속성인 원자성, 일관성, 격리성, 내구성(Atomicity, Consistency, Isolation and Durability, ACID)이 포함된다.
  * 일반적인 웹 서비스 사양
    * 웹 서비스 보안(WS-security): 토큰이라고 불리는 고유 식별자를 통해 메시지를 보호하고 전송하는 방식을 표준화합니다.
    * WS-ReliableMessaging: 불안정한 IT 인프라로 전송되는 메시지 간 오류 처리를 표준화합니다.
    * 웹 서비스 주소지정(WS-addressing): 심층 네트워크에 라우팅 정보를 유지관리하는 대신, SOAP 헤더 내에 메타데이터로 해당 정보를 패키징합니다.
    * 웹 서비스 기술 언어(WSDL): 웹 서비스가 무엇을 하는지, 해당 서비스의 시작과 종료 위치를 기술합니다.
  * SOAP과 REST 비교
    * 대부분의 레거시 시스템에서 SOAP를 준수하며, REST는 그보다 뒤에 고려하거나 웹 기반 시나리오에서의 더 빠른 대안으로 여기는 경우가 많습니다. REST는 유연한 구현을 제공하는 가이드라인 세트고, SOAP는 XML 메시징과 같은 특정 요건이 있는 프로토콜입니다.
    * REST API는 경량화되어 있기 때문에 사물 인터넷(IoT), 모바일 애플리케이션 개발, 서버리스(servreless) 컴퓨팅과 같이 보다 새로운 컨텍스트에 이상적입니다. SOAP 웹 서비스는 많은 기업에서 필요로 하는 기본 보안과 트랜잭션 컴플라이언스를 제공하지만, 이로 인해 좀 더 무거운 경향이 있습니다. 또한 Google Maps API와 같은 대부분의 퍼블릭 API는 REST 가이드라인을 따릅니다.

 * HTTP(Hyper Text Transfer Protocol) 란
   * HTTP는 서버/클라이언트 모델을 따라 데이터를 주고 받기 위한 프로토콜이다.
   * HTTP는 인터넷에서 하이퍼텍스트를 교환하기 위한 통신 규약으로, 80번 포트를 사용하고 있다. 따라서 HTTP 서버가 80번 포트에서 요청을 기다리고 있으며, 클라이언트는 80번 포트로 요청을 보내게 된다.
   * HTTP는 1989년 팀 버너스 리(Tim Berners Lee)에 의해 처음 설계되었으며, WWW(World-Wide-Web) 기반에서 세계적인 정보를 공유하는데 큰 역할을 하였다.
 * HTTP(Hyper Text Transfer Protocol Secure) 란
   * HyperText Transfer Protocol over Secure Socket Layer, HTTP over TLS, HTTP over SSL, HTTP Secure 등으로 불리는 HTTPS는 HTTP에 데이터 암호화가 추가된 프로토콜이다. 
   * HTTPS는 HTTP와 다르게 443번 포트를 사용하며, 네트워크 상에서 중간에 제3자가 정보를 볼 수 없도록 공개키 암호화를 지원하고 있다.

출처: https://mangkyu.tistory.com/98?category=762469

 * DATABASE
   * 트랜잭션이란
     * DBMS에서 데이터를 다루는 논리적인 작업의 단위
     * DB에서 데이터를 다룰 때 장애가 일어난 경우 데이터를 복구하는 작업의 단위가 된다.
     * DB에서 여러 작업이 동시에 같은 데이터를 다룰 때가 이 작업을 서로 분리하는 단위가 된다.
     * 트랜잭션은 전체가 수행되거나 또는 전혀 수행되지 않아야 한다.(All or Nothing)
   * 트랜잭션 성질
     * ACID
       * 원자성(Atomicity): 트랜잭션에 포함된 작업은 전부 수행되거나 전부 수행되지 않아야 한다.
       * 일관성(Consistency): 트랜잭션을 수행하기 전이나 후나 데이터베이스는 항상 일관된 상태를 유지해야 한다.
       * 고립성(Isolation): 수행 중인 트랜잭션에 다른 트랜잭션이 끼어들어 변경중인 데이터 값을 훼손하지 않아야한다.
       * 지속성(Durability): 수행을 성공적으로 완료한 트랜잭션은 변경한 데이터를 영구히 저장해야 한다.
   * 동시성 제어
     * 다중 사용자 환경에서 둘 이상의 트랜잭션이 동시에 수행될 때, 일관성을 해치지 않도록 트랜잭션의 데이터 접근 제어
     * 다중 사용자 환경을 지원하는 DBMS의 경우, 반드시 지원해야 하는 기능
     * 동시성 제어관리
       * 문제점 
         * 1개의 트랜잭션은 쓰고 1개의 트랜잭션은 읽는 경우에 상황에 따라 오손 읽기, 반복불가능 읽기, 유령데이터 읽기 문제가 발생할 수 있다 
         * 2개의 트랜잭션이 모두 쓰기(Write) 시 무제어 병행 수행을 하는 경우에 갱신 손실, 모순성, 연쇄 복귀 등의 문제가 발생할 수 있습니다.
       * 해결방안 or 종류
         * 트랜잭션 스케줄
           * 그 연산들의 실행 순서를 의미한다. 직렬, 비직렬, 직렬가능 스케줄등으로 나뉜다.
         * 락(Rocking)
           * 트랜잭션들이 동일한 데이터 항목에 대해 임의적인 병행 접근을 하지 못하도록 제어하는 것
           * 읽기를 할 때 사용하는 공유락(LS, Shared Lock)과 읽고 쓰기를 할 때 사용하는 배타락(LX, Exclusive Lock)이 있다.
           * 그 외에 2단계록킹과 데드락이 있다.
    * 트랜잭션 고립 수준
      *문제점
        * 오손읽기
          * 읽기 작업을 하는 트랜잭션 1이 쓰기 작업을 하는 트랜잭션2가 작업한 중간 데이터를 읽기 때문에 발생하는 문제
          * 작업중인 트랜잭션 2가 작업을 Rollback한 경우 트랜잭션 1은 무효가 된 데이터를 읽게 되고 잘못된 결과를 도출한다.
        * 반복불가능 읽기
          * 트랜잭션 1이 데이터를 읽고 트랜잭션 2가 데이터를 쓰고(Update) 르랜잭션 1이 다시 한번 데이터를 읽을 때 생기는 문제
          * 트랜잭션 1이 읽기 작업을 다시 한 번 반복할 경우 이전의 결과와 다른 결과가 나오는 현상
        * 유령데이터 읽기
	  * 트랜잭션 1이 데이터를 읽고 트랜잭션 2가 데이터를 쓰고(Insert) 트랜잭션 1이 다시 한번 데이터를 읽을 때 생기는 문제
          * 트랜잭션 1이 읽기 작업을 다시 한 번 반복할 경우 이전에 없던 데이터(유령 데이터)가 나타나는 현상
      * 해결방안
        * 트랜잭션 고립 수준 명령어(Transaction Isolation Level Instruction)
	  * DBMS는 트랜잭션을 동시에 실행시키면서 락보다 좀 더 완화된 방법으로 문제를 해결하기 위해 제공하는 명령어
	  * Lock보다는 완화된 방법으로 트랜잭션을 동시에 실행시키면서, 발생하는 문제를 해결하기 위해 DBMS가 제공하는 명령어
    * 회복
      * 데이터베이스를 갱신하는 과정에서 장애가 발생한 경우 회복절차를 수행하여 장애 발생 이전의 데이터베이스로 만드는 것
      * 장애의 유형
        * 트랜잭션 장애: 트랜잭션의 실행 시 논리적인 오류로 발생할 수 있는 에러 상황
        * 시스템 장애: H/W 시스템 자체에서 발생할 수 있는 에러 상황
        * 미디어 장애: 디스크 자체의 손상으로 발생할 수 있는 에러 상황
      * 회복 종류
        * 로그파일(Log File)
          * 트랜잭션이 반영한 모든 데이터의 변경사항을 데이터베이스에 기록하기 전에 미리 기록해두는 별도의 데이터베이스
          * 안전한 하드디스크에 저장되며 전원과 관계없이 기록이 존재
          * 로그의 구조: <트랜잭션 번호, 로그의 타입, 데이터 항목 이름, 수정 전 값, 수정 후 값>
          * 로그의 타입: START, INSERT, UPDATE, DELETE, ABORT, COMMIT 등 트랜잭션의 연산 타입
          * 회복방법
            * REDO(재실행) : REDO는 장애가 발생한 후 시스템을 다시 가동을 했을 때, 로그 파일에 트랜잭션의 시작(START)이 있고 종료(COMMIT)이 있는 경우 로그를 보면서 트랜잭션이 변경한 내용을 다시 기록하는 과정.
	    * UNDO(취소) : NDO는 장애가 발생한 후 시스템을 재가동했을때, 로그 파일에 트랜잭션의 시작(START)만 있고 종료(COMMIT)이 없는 경우 완료하지 못했지만 버퍼의 변경 내용이 데이터베이스에 기록되어 있을 가능성이 있기 때문에 로그를 보면서 트랜잭션이 변경한 내용을 원상복구시키는 과정.

 * Spring FrameWork
   * FrameWork 란
     * 뼈대나 기반구조를 뜻하며, 개발 시 필수적인 코드나, 알고리즘, 데이터베이스 연동등의 기능들을 어느정도 제공해주는 구조(뼈대). 
     * 소프트웨어의 특정 문제를 해결하기 위해서 상호 협력하는 클래스와 인터페이스의 집합
   * Library 란
     * 단순 활용이 가능한 도구들의 집합
   * 차이점
     * 제어의 역전(Inversion Of Control)
     * Framework와 Library의 차이는 Flow(흐름)에 대한 제어 권한이 어디에 있느냐의 차이입니다. 프레임워크는 전체적인 흐름을 자체적으로 가지고 있으며, 프로그래머가 그 안에 필요한 코드를 작성하는 반면에 라이브러리는 사용자가 흐름에 대해 제어를 하며 필요한 상황에 가져다 쓰는 것. 
   * 스프링 프레임워크 특징
     * 경량화한 컨테이너
     * IoC(제어의 역전)
	* 어떠한 일을 하도록 만들어진 프레임워크에 제어의 권한을 넘김으로써 클라이언트 코드가 신경서야 할 것을 줄이는 전략입니다.
     * DI(의존성 주입)
	* 객체를 직접 생성하는 게 아니라 외부에서 생성한 후 주입 시켜주는 방식이다. DI(의존성 주입)를 통해서 모듈 간의 결합도가 낮아지고 유연성이 높아진다.
   * Servlet 란
     * 클라이언트의 요청을 처리하고, 그 결과를 반환하는 Servlet 클래스의 구현 규칙을 지킨 자바 웹 프로그래밍 기술
     * 자바를 사용하여 웹을 만들기 위해 필요한 기술입니다. 그런데 좀더 들어가서 설명하면 클라이언트가 어떠한 요청을 하면 그에 대한 결과를 다시 전송해주어야 하는데, 이러한 역할을 하는 자바 프로그램입니다.
   * Servlet Container 란
     * 서블릿을 관리해주는 컨테이너
     * 클라이언트의 요청(Request)을 받아주고 응답(Response)할 수 있게, 웹서버와 소켓으로 통신하며 대표적인 예로 톰캣(Tomcat)이 있습니다.
   * Spring MVC 란
     * Model-View-Controller 의 약자이다.
     * 프로그래밍 아키텍처의 한 방법이다.
     * Model : 모델은 뷰를 랜더링하기 위한 데이터이다. 
     * View : Web Application 에서 뷰는 보여지는 부분으로 모델을 사용해 랜더링 된다.
     * Controller : 컨트롤러는 사용자의 액션에 응답하는 컴포넌트이다. 컨트롤러는 모델을 업데이트하고 다른 액션들을 수행한다.
   * Spring MVC LifeCycle
     * <img width="1430" alt="스크린샷 2021-08-22 오후 7 29 26" src="https://user-images.githubusercontent.com/37688894/130352199-c4938eea-7944-4d77-b8be-42265302da37.png">
   
 * 빌드관리 도구 
   * Maven
     * 장점
	* 빌드를 쉽게 한다
	* POM.xml을 통해 정형화된 빌드 시스템을 구축한다.
   * Gradle
     * 장점
	* Ant 와 Maven 의 장점을 모아 만듬
	* 유연한 범용 빌드 도구
	* Maven을 사용할 수 있는 변환 가능 컨벤션 프레임워크
	* 원격 저장소나, pom 파일없이 연결되는 의존성 관리 지원
	* 그루비 문법을 사용
     * 차이점
	* 다양한 퍼포먼스를 제공(gradle)
	* xml은 동적인 요소를 정의하기 어렵다
	* 상속구조를 이용한 멀티 모듈 구현
	* 빠른 속도













	










    

     










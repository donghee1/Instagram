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
 

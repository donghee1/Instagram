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

 

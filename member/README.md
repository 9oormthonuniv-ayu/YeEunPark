# Spring Boot + Thymeleaf 회원 관리 시스템

## 프로젝트 개요

이 프로젝트는 Spring Boot + Thymeleaf 기반의 학습용 웹 애플리케이션으로, 회원 가입·로그인·수정·탈퇴 등의 기본적인 사용자 관리 기능을 구현합니다.

---

## 학습 목표

- Spring Boot MVC 아키텍처 이해 및 프로젝트 셋업
- Thymeleaf 폼 바인딩 및 서버-클라이언트 데이터 흐름 파악
- Spring Data JPA 기반 엔티티 매핑 및 CRUD 구현
- Controller → Service → Repository 계층 설계 및 역할 분리

---

## 기술 스택

| 분류        | 기술                             |
|-------------|----------------------------------|
| Language    | Java 17                          |
| Framework   | Spring Boot 3.x                  |
| Template    | Thymeleaf                        |
| ORM         | Spring Data JPA (Hibernate)      |
| Database    | MySQL                            |
| Build Tool  | Gradle                           |
| Test Tool   | JUnit 5, MockMvc                 |
| IDE         | IntelliJ IDEA                    |

---

## 디렉토리 구조

```text
member/
├─ src/main/java/com/Yeeun/member/
│  ├─ MemberApplication.java  # 메인 애플리케이션 클래스
│  ├─ controller/
│  ├─ service/
│  ├─ repository/
│  ├─ entity/
│  └─ dto/
├─ src/main/resources/
│  ├─ templates/  # Thymeleaf 뷰
│  │  ├─ detail.html
│  │  ├─ index.html
│  │  ├─ list.html
│  │  ├─ login.html
│  │  ├─ main.html
│  │  ├─ save.html
│  │  └─ update.html
│  └─ application.yml
└─ build.gradle
```

---

## 주요 기능 요약

### 회원 가입 & 로그인
- `/member/save` : 회원가입 폼 및 가입 처리
- `/member/login` : 로그인 폼 및 로그인 처리

### 회원 목록 & 상세 조회
- `/member/` : 전체 회원 목록 조회
- `/member/{id}` : 회원 상세 조회

### 프로필 수정 & 탈퇴
- `/member/update` : 프로필 수정 폼 및 수정 처리
- `/member/delete/{id}` : 회원 삭제 처리

### 기타 기능
- `/member/logout` : 로그아웃 (세션 무효화)
- `/member/email-check` : 이메일 중복 검사 (AJAX)

---

## 고려했던 점 & 해결 방법

- **폼 중복 제출 방지:** `redirect:` 적용으로 새로고침 시 재요청 방지
- **유효성 검증:** `@Valid` + `BindingResult`로 입력값 검증 및 오류 처리
- **세션 관리:** 로그인 후 세션에 사용자 정보 저장, 로그아웃 시 세션 무효화

---

## 실행 방법

1. MySQL 데이터베이스 생성
   ```sql
   CREATE DATABASE memberDB;
   ```
2. `application.yml`에 DB 접속 정보 입력
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/memberDB
       username: root
       password: <YOUR_DB_PASSWORD>
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true
   ```
3. Gradle 빌드 및 실행
   ```bash
   ./gradlew clean bootRun
   ```
4. 브라우저에서 `http://localhost:8081` 접속

---

## 향후 계획

- Spring Security 기반 인증·인가(ROLE_ADMIN / ROLE_USER)
- 이메일 인증 및 비밀번호 찾기 기능 추가
- REST API 형태로 확장 및 SPA 연동

---

## 참고 자료

- [Spring Boot 공식 문서](https://spring.io/projects/spring-boot)
- [Thymeleaf 공식 가이드](https://www.thymeleaf.org/)
- [Spring Data JPA 레퍼런스](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [코딩레시피 - Spring Boot 회원 프로젝트(Youtube)](https://www.youtube.com/watch?v=RhM1bQ76Tv0&list=PLV9zd3otBRt5ANIjawvd-el3QU594wyx7)

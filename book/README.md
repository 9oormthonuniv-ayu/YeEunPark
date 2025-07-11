# Spring Boot + Thymeleaf 도서 대여 시스템

## 프로젝트 개요
이 프로젝트는 Spring Boot 기반의 학습용 웹 애플리케이션으로, 회원 관리부터 도서 등록·수정·삭제 및 대여·반납 처리까지 실습하며 MVC 아키텍처와 JPA 연동을 경험하는 포트폴리오 프로젝트입니다.

## 학습 목표

- Spring Boot MVC 아키텍처 이해 및 프로젝트 구성
- Thymeleaf Form 바인딩 및 서버-클라이언트 데이터 흐름 경험
- Spring Data JPA로 엔티티 관계 매핑 및 CRUD 구현
- Controller-Service-Repository 계층별 역할 분리
- 글로벌 예외 처리(`@ControllerAdvice`) 및 입력 검증(`@Valid`) 적용 기법 습득

---

## 기술 스택

| 분류        | 기술                             |
|-------------|----------------------------------|
| Language    | Java 17                          |
| Backend     | Spring Boot 3.x                  |
| Frontend    | Thymeleaf                        |
| ORM         | Spring Data JPA (Hibernate)      |
| Database    | MySQL                            |
| Build Tool  | Gradle                           |
| Test Tool   | JUnit 5, MockMvc                 |
| IDE         | IntelliJ IDEA                    |

---

## 디렉토리 구조

```text
📦 book/
├─ 📁 controller/        # 애플리케이션 요청 처리
├─ 📁 service/           # 비즈니스 로직
├─ 📁 repository/        # JPA 리포지토리
├─ 📁 entity/            # JPA 엔티티
├─ 📁 resources/
│  ├─ 📁 templates/      # Thymeleaf 뷰
│  │  ├─ book/
│  │  ├─ user/
│  │  ├─ author/
│  │  ├─ genre/
│  │  └─ rental/
│  └─ 📄 application.yml  # 환경 설정
└─ 📄 PyeBookApplication.java
```

---

## 주요 기능 요약

### 회원(User)
- 회원 등록, 수정, 삭제
- 전체 회원 목록 조회

### 도서(Book)
- 도서 등록(작가/장르 선택), 수정, 삭제
- 도서 목록 조회 및 검색

### 대여(Rental)
- 도서 대여 요청 및 기록 저장
- 반납 처리 및 연체 여부 확인
- 대여 기록 목록 조회

---

## 고려했던 점 & 해결 방법

- `author.id` → `author.authorId` 등 정확한 필드 참조로 오류 해결
- `<option>` 태그 닫힘 문제로 인한 Thymeleaf 오류 해결
- 등록/수정/삭제 시 `redirect:` 처리 정상 작동
- `createdAt` 필드 수정 시 보존 처리 (`hidden` 필드 유지)

---

## 실행 방법

1. MySQL 데이터베이스 생성
   ```sql
   CREATE DATABASE bookdb;
   ```
2. `application.yml`에 DB 접속 정보 입력
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/bookdb
       username: root
       password: <YOUR_PASSWORD>
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

- 로그인/관리자 인증 (Spring Security)
- 미반납 목록/연체 표시
- 도서 검색, 필터링
- Bootstrap UI 적용
- 통계 페이지 (장르별 도서 수, 사용자별 대여 횟수 등)

---

## 참고 자료

- [Spring Boot 공식 문서](https://spring.io/projects/spring-boot)
- [Thymeleaf 공식 가이드](https://www.thymeleaf.org/)
- [Spring Data JPA 레퍼런스](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
  

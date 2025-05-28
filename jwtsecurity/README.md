# Spring Boot + JWT + Redis 인증/인가 시스템

## 프로젝트 개요

이 프로젝트는 Spring Boot 기반으로 JWT 인증/인가 기능을 구현하고, Redis를 연동하여 Refresh Token 저장 및 캐시 기능을 실습한 학습용 시스템입니다.  
Stateless 구조를 중심으로 Spring Security 필터 체인과 Redis TTL 제어까지 학습하며, 인증 흐름의 전반적인 이해를 목표로 개발하였습니다.


## 학습 목표

- JWT 기반 인증/인가 시스템의 흐름을 직접 구현하며 이해
- Redis를 통한 RefreshToken 저장 및 블랙리스트 설계
- Spring Security 필터 체인 구조 및 커스텀 필터 적용
- 인증, 캐시, 보안 구성을 코드로 실습하여 개념 내재화


## 사용 기술 스택

| 분류 | 기술 |
|------|------|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| Security | Spring Security + JWT (jjwt 0.12.3) |
| Cache | Redis (Lettuce) |
| DB | MySQL + JPA |
| Build Tool | Gradle |
| Test Tool | Postman |
| 기타 | Intellij, Markdown, GitHub |


## 주요 기능 요약

### JWT 기반 인증 / 인가

- `/join`: 회원가입 시 기본 `ROLE_ADMIN` 부여
- `/login`: JWT 발급 (Access + Refresh)
- `/admin/**`: `ROLE_ADMIN` 권한만 접근 가능
- JWT Filter/Util 클래스 직접 구현
- SecurityFilterChain 커스텀 설정

### Redis 연동

- 로그인 시 Refresh Token을 Redis에 저장 (TTL 적용)
- 로그아웃 시 Access Token 블랙리스트 등록 (예정)
- `/redis/set`, `/get`, `/delete`: 직접 캐시 확인용 API

### 캐시 기능 테스트

- `@Cacheable`, `@CacheEvict`을 적용한 키 기반 캐싱 테스트 API 구현


## 디렉토리 구조

```plaintext
jwtsecurity/
├── config/                  # Spring Security 설정
│   └── SecurityConfig.java
├── jwt/                     # JWT 관련 유틸 및 필터
│   ├── JWTUtil.java
│   ├── JWTFilter.java
│   └── LoginFilter.java
├── controller/              # API 엔드포인트
│   ├── JoinController.java
│   ├── RedisController.java
│   └── RedisCacheController.java
├── service/                 # 비즈니스 로직
│   ├── JoinService.java
│   └── RedisService.java
├── entity/                  # JPA Entity
│   └── UserEntity.java
├── repository/              # JPA Repository
│   └── UserRepository.java
└── dto/                     # DTO 클래스
    └── JoinDTO.java
```


## 어려웠던 점 & 해결 방법

- `lettuce-core` 의존성 인식 오류  
  → Gradle에 명시해도 인식 안됨  
  → 여러 방법 시도 후, `.jar` 직접 다운로드하여 `libs` 폴더에 수동 추가로 해결

- Redis 연결 오류  
  → 서버는 실행 중이나 연결 불가 → 포트/호스트 확인 및 애플리케이션 재시작으로 해결

- JWT 필터 적용 안 됨  
  → SecurityFilterChain에서 `addFilterBefore()` 설정 누락 → 명시적으로 필터 위치 지정하여 해결


## API 테스트 예시 (Postman)

**POST /join**  
Content-Type: `application/x-www-form-urlencoded`  
➡️ `username=admin&password=1234`

**POST /login**  
➡️ JWT 토큰 발급 (헤더: `Authorization: Bearer {token}`)

**GET /admin**  
➡️ 헤더에 토큰 포함 시 접근 가능

**GET /redis/set?key=test&value=value&duration=30**  
➡️ TTL 30초로 Redis 캐시 저장



## 설정 파일 예시 (`application.yml`)

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/jwt
    username: root
    password: yourpassword

  data:
    redis:
      host: localhost
      port: 6379

jwt:
  secret: your_secret_key_here
  expiration: 3600000
```


## 실행 방법

1. **Redis 실행**
    - 로컬 Redis 실행 또는 `redis-server.exe`

2. **MySQL DB 생성**
   ```sql
   CREATE DATABASE jwt;
   ```

3. **`application.yml` 설정**
    - 위 DB/Redis 정보 입력

4. **애플리케이션 실행**
   ```bash
   ./gradlew bootRun
   ```


## 향후 계획

- [ ] AccessToken 블랙리스트 처리 기능 구현
- [ ] RefreshToken 재발급 API 구현
- [ ] Redis TTL 상태 확인용 관리자 페이지
- [ ] 관리자/일반 사용자 권한 분리 확장


## 참고 자료

- [Spring Security 공식 문서](https://docs.spring.io/spring-security/)
- [Spring Data Redis 공식 문서](https://docs.spring.io/spring-data/redis/)
- [개발자 유미 - Spring Boot JWT 강의 (YouTube)](https://www.youtube.com/@devyoumi)

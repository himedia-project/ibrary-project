# 📚 IBrary - 도서관 관리 시스템

![image](https://github.com/user-attachments/assets/30f3f364-bd21-489a-827e-ac6f18d1be47)

> 자바 기반의 콘솔 도서관 관리 시스템으로, MySQL 데이터베이스를 활용한 CRUD 기능을 제공합니다.

> 시연영상: https://www.youtube.com/watch?v=PZwhMQPSs6k&list=PLrtykmiaS87tFT2POACEd4d17YG4gyv-G&index=2

[![Java](https://img.shields.io/badge/Java-8+-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)](https://www.mysql.com/)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)]()

## ✨ 주요 기능

- 👤 **사용자 관리**: 회원가입, 로그인, 프로필 관리
- 📖 **도서 관리**: 도서 등록, 수정, 삭제, 검색
- 🏷️ **카테고리 관리**: 도서 분류 체계
- 📋 **대출 관리**: 도서 대출/반납 시스템
- ⭐ **즐겨찾기**: 개인 도서 즐겨찾기 기능
- 📊 **엑셀 연동**: 도서 데이터 가져오기/내보내기
- 📍 **주소 관리**: 사용자 주소 정보 관리

## 🏗️ 시스템 아키텍처

```
src/
├── App.java                 # 메인 애플리케이션 진입점
├── menu/                    # 메뉴 관리
├── db/                      # 데이터베이스 연결 관리
│   └── DBConnectionUtil.java
├── user/                    # 사용자 관리
│   ├── User.java
│   ├── UserManager.java
│   └── UserRepository.java
├── book/                    # 도서 관리
│   ├── Book.java
│   ├── BookManager.java
│   ├── BookRepository.java
│   ├── CategoryManager.java
│   └── CategoryRepository.java
├── rent/                    # 대출 관리
│   ├── Rent.java
│   ├── RentManager.java
│   └── RentRepository.java
├── favorite/               # 즐겨찾기 관리
├── excel/                  # 엑셀 파일 처리
├── address/               # 주소 관리
└── test/                  # 테스트 코드
```

## 🛠️ 기술 스택

- **언어**: Java 8+
- **데이터베이스**: MySQL 8.0+
- **JDBC**: MySQL Connector/J
- **개발환경**: IntelliJ IDEA
- **빌드도구**: Maven/Gradle (기본 자바 프로젝트)

## 🚀 시작하기

### 필수 요구사항

- Java 8 이상
- MySQL 8.0 이상
- MySQL Connector/J 드라이버

### 데이터베이스 설정

1. MySQL 서버에 데이터베이스 생성:

```sql
CREATE DATABASE ibrary;
```

2. `src/db/DBConnectionUtil.java`에서 데이터베이스 연결 정보 확인:

```java
private static final String url = "jdbc:mysql://127.0.0.1:3306/ibrary";
private static final String id = "root";
private static final String pw = "1234";
```

### 실행 방법

1. 프로젝트 클론

```bash
git clone <repository-url>
cd Ibrary-project
```

2. 데이터베이스 연결 정보 설정

3. 애플리케이션 실행

```bash
javac -cp ".:mysql-connector-java.jar" src/App.java
java -cp ".:mysql-connector-java.jar:src" App
```

## 📊 데이터베이스 스키마

### 주요 테이블 구조

- **users**: 사용자 정보
- **books**: 도서 정보
- **categories**: 도서 카테고리
- **rents**: 대출 정보
- **favorites**: 즐겨찾기 정보
- **addresses**: 주소 정보

## 🎯 사용법

1. **애플리케이션 시작**: `App.java` 실행
2. **메뉴 탐색**: 콘솔 메뉴를 통한 기능 접근
3. **사용자 인증**: 로그인/회원가입
4. **도서 관리**: 도서 등록, 검색, 수정, 삭제
5. **대출 시스템**: 도서 대출 및 반납 처리

## 📋 주요 클래스 설명

### 🔌 DBConnectionUtil

- MySQL 데이터베이스 연결 관리
- Connection Pool 없는 단순 연결 방식
- 자원 해제 유틸리티 메서드 제공

### 👤 User 관련 클래스

- `User`: 사용자 엔티티
- `UserManager`: 사용자 비즈니스 로직
- `UserRepository`: 사용자 데이터 접근 계층

### 📚 Book 관련 클래스

- `Book`: 도서 엔티티
- `BookManager`: 도서 비즈니스 로직
- `BookRepository`: 도서 데이터 접근 계층

## 🔧 개발 가이드

### 코드 스타일

- Repository 패턴 사용
- Manager 클래스를 통한 비즈니스 로직 분리
- 예외 처리 및 자원 관리 철저

### 확장 가능성

- 새로운 기능 모듈 추가 시 기존 패턴 준수
- 데이터베이스 스키마 변경 시 Repository 계층 수정
- UI 개선을 위한 GUI 프레임워크 도입 가능

## 📝 라이선스

이 프로젝트는 교육 목적으로 개발되었습니다.

## 📞 문의사항

프로젝트 관련 문의사항이 있으시면 이슈를 등록해 주세요.

---

_Made with ❤️ for Learning Java & Database_

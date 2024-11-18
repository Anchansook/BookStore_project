# 📚 Online Bookstore Project
## 📝 프로젝트 개요
- <strong>설명</strong>: 이 프로젝트는 유저가 책을 구매하고 리뷰와 평점을 남길 수 있는 온라인 서점 시스템입니다.

- <strong>목적</strong>: 기본적인 상거래 기능과 RESTful API 설계 능력을 확인하고, 데이터베이스 설계와 깃헙을 이용한 협업 및 버전 관리 능력을 증명하는데 있습니다.
## ⚙️ 기술 스택
- <strong>Back-end</strong>: Java 17+, Spring Boot 3.x.x
- <strong>Database</strong>: MySQL, JPA
- <strong>API</strong>: RESTful API
- <strong>기타</strong>: Spring Scheduler(배송 상태 자동 업데이트)
## 🛠️ 주요 기능
### 1. 책 구매
- 사용자가 장바구니에 담긴 상품을 주문으로 전환하고, 결제 후 상태가 자동으로 업데이트됩니다.
### 2. 리뷰 및 평점 작성
- 구매한 책에 한해 리뷰와 평점을 남길 수 있으며, 한 사람당 하나의 리뷰만 작성 가능합니다.
### 3. 유저 관리
- 회원가입, 로그인, JWT를 통한 인증 및 권한 관리.
### 4. 배송 상태 업데이트
- 구매일을 기준으로, 1일 후에 '배송 중', 2일 후에 '발송 완료'로 상태가 자동 변경됩니다.
### 5. 카테고리 조회 및 추천
- 카테고리별 책 조회, 정렬, 할인 책 조회 기능 제공 및 구매 패턴을 반영한 추천 기능.
## 📊 데이터베이스 설계 (ERD)
- <strong>Users</strong>: 사용자 정보 저장
- <strong>Books</strong>: 책 정보 저장
- <strong>Books_category</strong>: 책과 카테고리 매핑 정보 저장
- <strong>Categories</strong>: 책 카테고리
- <strong>Orders</strong>: 주문 정보 관리
- <strong>Order_items</strong>: 주문 상품 목록 관리
- <strong>Reviews</strong>: 리뷰 정보 관리
<img width="913" alt="Bookstore_ERD" src="https://github.com/user-attachments/assets/4fc54169-3655-4d5c-9748-f4e22b2aa5d3">

## 🪩 API 명세서
### DOMAIN : http://localhost:4000
- ### Auth API
> - /api/v1/auth
- ### 유저 API
> - /api/v1/users
- ### 책 API
> - /api/v1/books
- ### 주문 API
> - /api/v1/orders
### 자세한건 API 명세(설계)서 내용 안에 있습니다.
## 💡 사용된 설계 패턴 및 주요 구현 사항
- <strong>스케줄러</strong>: Spring Scheduler를 이용해 매일 정해진 시간에 주문 상태를 자동으로 업데이트.
## 🗂️ 프로젝트 구조
- <strong>src/main/java</strong>: 주요 코드
> - <strong>controller</strong>: 요청을 처리하는 컨트롤러
> - <strong>service</strong>: 비즈니스 로직을 처리하는 서비스 계층
> - <strong>repository</strong>: 데이터 접근을 담당하는 계층
- <strong>src/main/resources</strong>: 설정 파일 및 SQL 스크립트
## 🚀 시작 가이드
### 1. 로컬 환경 설정
 - JDK 17 이상
 - MySQL 데이터베이스 설정
### 2. API 테스트
 - Postman을 활용하여 API를 테스트합니다.
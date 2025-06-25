# 🧑‍💻 너굴 마켓 

너굴마켓은 사용자 간 중고 물품을 등록하고 실시간 채팅을 통해 거래할 수 있는 중고 거래 플랫폼입니다.
물품 등록, 채팅, 관심 상품 기능 등 핵심 중고 마켓 기능을 구현하였고,
JWT 기반 인증과 WebSocket 기반 채팅, 등을 적용했습니다.

<br/>

## 🛠 기술 스택

| 구분         | 기술 |
|--------------|------|
| Frontend     | React, Vite, chakra-ui |
| Backend      | Spring Boot, WebSocket, JWT ,stomp |
| Database     | MariaDB |
| Infra/Deploy | AWS EC2, Docker|

<br/>

## ⌛ 개발 기간

- **2024.12 ~ 2025.01 (1달)**

<br/>

## 👥 팀원 소개

| 이름     | 역할           | GitHub |
|----------|----------------|--------|
| 김용수 | •채팅 CRUD (실시간 채팅)
• 지도 (카카오 API) - 카테고리별 검색, 장소 검색 | [github.com/your-id](https://github.com/Qortn4925) |
| 김수민 | 관리자 - 회원 관리, 문의 관리
• 1:1 문의 CRUD
• 마이페이지 (가계부)
• 결제 (카카오페이) | [github.com/your-id](https://github.com/your-id) |
| 김민경 | •메인페이지
• 상품 CRUD, 찜 CRUD
• 소셜 로그인 (카카오, 네이버)
• 후기 CRUD
• 마이페이지 (구매/판매/찜/후기 내역 조회) | [github.com/your-id](https://github.com/your-id) |
| 이석민 | •회원가입, 로그인
• 게시판 CRUD, 댓글 CRUD
• 마이페이지 (회원 정보 조회/수정) | [github.com/your-id](https://github.com/your-id) |

<br/>

## 🧩 서비스 기획 및 설계

### 📌 ERD (Entity Relationship Diagram)

![너굴마켓 db](https://github.com/user-attachments/assets/1a8394b3-ac06-4531-ac04-b3cce38c9ba5)

---

### 📚 메뉴 구조

![너굴마켓 플로우 차트](https://github.com/user-attachments/assets/e4f51ee8-1e7d-46ba-b82f-2dfc4a40b917)


## 🖼️ 구현 화면


### 🏠 홈 & 상품 목록

- 사용자들이 등록한 중고 물품을 리스트 형태로 확인할 수 있는 메인 페이지입니다.
- 카테고리, 가격 등 조건별 필터 및 정렬 기능이 포함되어 있습니다.

![home](https://your-image-url.com/home.png)

---

### ➕ 상품 등록

- 제목, 설명, 가격, 사진을 입력하여 새 상품을 등록할 수 있습니다.
- 등록된 상품은 실시간으로 메인 목록에 반영됩니다.

![register](https://your-image-url.com/register.gif)

---

### 💬 실시간 채팅

- WebSocket 기반의 실시간 채팅 기능을 제공합니다.
- 상품별 1:1 채팅방 생성 및 이전 대화 내역 조회가 가능합니다.

![chat](https://your-image-url.com/chat.gif)

---

### ❤️ 관심 상품

- 사용자가 마음에 드는 상품을 찜하여 따로 모아볼 수 있습니다.
- 중복 등록 방지 및 찜 목록 관리 기능이 포함되어 있습니다.

![like](https://your-image-url.com/like.gif)

---

### 👤 마이페이지

- 내가 등록한 상품, 찜한 상품, 채팅 내역 등을 확인할 수 있는 개인 페이지입니다.
- 회원 정보 수정 및 탈퇴 기능도 포함되어 있습니다.

![mypage](https://your-image-url.com/mypage.png)

---



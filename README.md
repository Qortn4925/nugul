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

| 이름   | 역할 | GitHub |
|--------|------|--------|
| 김용수 | • 채팅 CRUD (실시간 채팅)<br>• 지도 (카카오 API) - 카테고리별 검색, 장소 검색 | [github.com/Qortn4925](https://github.com/Qortn4925) |
| 김수민 | • 관리자 - 회원 관리, 문의 관리<br>• 1:1 문의 CRUD<br>• 마이페이지 (가계부)<br>• 결제 (카카오페이) | [github.com/your-id](https://github.com/your-id) |
| 김민경 | • 메인페이지<br>• 상품 CRUD, 찜 CRUD<br>• 소셜 로그인 (카카오, 네이버)<br>• 후기 CRUD<br>• 마이페이지 (구매/판매/찜/후기 내역 조회) | [github.com/your-id](https://github.com/your-id) |
| 이석민 | • 회원가입, 로그인<br>• 게시판 CRUD, 댓글 CRUD<br>• 마이페이지 (회원 정보 조회/수정) | [github.com/your-id](https://github.com/your-id) |

<br/>

## 🧩 서비스 기획 및 설계

### 📌 ERD (Entity Relationship Diagram)

![너굴마켓 db](https://github.com/user-attachments/assets/1a8394b3-ac06-4531-ac04-b3cce38c9ba5)

---

### 📚 메뉴 구조

![너굴마켓 플로우 차트](https://github.com/user-attachments/assets/e4f51ee8-1e7d-46ba-b82f-2dfc4a40b917)


## 🖼️ 구현 화면


### 🏠 로그인/회원 가입
![회원가입](https://github.com/user-attachments/assets/f7f793b7-7627-4ae0-a6ed-16df6d98347b)
![캡처](https://github.com/user-attachments/assets/4da2caf8-8f6f-4a57-9804-b8110a82e756)

---

### ➕ 상품 등록

- 제목, 설명, 가격, 사진을 입력하여 새 상품을 등록할 수 있습니다.
- 등록된 상품은 실시간으로 메인 목록에 반영됩니다.
![상품리스트 보는 페이지](https://github.com/user-attachments/assets/21718913-400c-4bfa-b202-834a01ef8736)
![상품 등록창](https://github.com/user-attachments/assets/9a8d59ed-043e-490e-ad7e-512c05de13df)
![등록상품](https://github.com/user-attachments/assets/e94dccdd-a9b5-41b4-b3c3-f3e8eadeabe1)

![register](https://your-image-url.com/register.gif)

---
###  게시판

![게시판리스트](https://github.com/user-attachments/assets/27be9173-7e3c-420d-8672-41ac263c6278)
![게시판 상세](https://github.com/user-attachments/assets/10f63eb8-634a-4a0d-92e8-510f10213f7f)


---

### 💬 실시간 채팅

- WebSocket 기반의 실시간 채팅 기능을 제공합니다.
- 상품별 1:1 채팅방 생성 및 이전 대화 내역 조회가 가능합니다.

![chat](https://your-image-url.com/chat.gif)

---


### 1대1 문의
![문의 리스트](https://github.com/user-attachments/assets/467ddd3a-b274-4689-95d0-f37b4bf6e3ac)

![1대1문의창](https://github.com/user-attachments/assets/ca77a3f7-f32d-4053-b096-f2e46b4c584a)

---



### 👤 마이페이지

- 내가 등록한 상품, 찜한 상품, 채팅 내역 등을 확인할 수 있는 개인 페이지입니다.
- 회원 정보 수정 및 탈퇴 기능도 포함되어 있습니다.

![mypage](https://your-image-url.com/mypage.png)

---



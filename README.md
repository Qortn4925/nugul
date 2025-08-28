# 🧑‍💻 너굴 마켓 

너굴마켓은 사용자 간 중고 물품을 등록하고 실시간 채팅을 통해 거래할 수 있는 중고 거래 플랫폼입니다.  
물품 등록, 채팅, 관심 상품 기능 등 핵심 중고 마켓 기능을 구현하였고,  
JWT 기반 인증과 WebSocket 기반 채팅 등을 적용했습니다.

<br/>

## 🙋‍♂️ 나의 역할 및 기여 (My Role & Contributions)

 <ul>
<li>실시간 채팅 시스템 개발:

WebSocket, STOMP, SockJS를 활용하여 사용자 간 실시간 커뮤니케이션 환경을 구축했습니다.

채팅방 생성, 입장, 퇴장 로직 및 메시지 송수신 처리 기능을 구현하여 원활한 실시간 대화를 지원했습니다.

네트워크 끊김이나 재접속 상황에 대응하는 자동 재연결 로직 및 예외처리를 구현하여 사용자 경험을 크게 개선했습니다. </li>

<li>지도 기반 장소 검색 기능 개발:

Kakao Maps API를 활용하여 키워드 및 카테고리 기반 장소 검색 기능을 구현했습니다.

검색 결과를 지도 마커와 리스트 형태로 동시에 렌더링하여 사용자가 직관적으로 정보를 파악하고 편리하게 이용할 수 있도록 했습니다.

React 컴포넌트 분리 및 상태 관리 최적화를 통해 지도 관련 UI의 재사용성 및 유지보수성을 향상시켰습니다.</li>
</ul>

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
| 김수민 | • 메인페이지<br>• 상품 CRUD, 찜 CRUD<br>• 소셜 로그인 (카카오, 네이버)<br>• 후기 CRUD<br>• 마이페이지 (구매/판매/찜/후기 내역 조회) | [github.com/soomni01](https://github.com/soomni01) |
| 김민경 | • 관리자 - 회원 관리, 문의 관리<br>• 1:1 문의 CRUD<br>• 마이페이지 (가계부)<br>• 결제 (카카오페이) | [github.com/keaimk](https://github.com/keaimk) |
| 이석민 | • 회원가입, 로그인<br>• 게시판 CRUD, 댓글 CRUD<br>• 마이페이지 (회원 정보 조회/수정) | [github.com/seokminlee24](https://github.com/seokminlee24) |

<br/>

## 🧩 서비스 기획 및 설계

### 📌 ERD (Entity Relationship Diagram)

<p align="center">
  <img src="https://github.com/user-attachments/assets/1a8394b3-ac06-4531-ac04-b3cce38c9ba5" width="600"/>
</p>

---

### 📚 메뉴 구조

<p align="center">
  <img src="https://github.com/user-attachments/assets/e4f51ee8-1e7d-46ba-b82f-2dfc4a40b917" width="600"/>
</p>

---

## 🖼️ 구현 화면

<details>
<summary>🏠 <strong>로그인/회원 가입</strong></summary>

<ul>
  <li>JWT 인증 방식을 사용하여 로그인/회원가입 처리</li>
  <li>로그인 후 사용자 역할에 따라 접근 가능한 메뉴가 달라집니다.</li>
</ul>

<p align="center">
  <img width="48%" src="https://github.com/user-attachments/assets/f7f793b7-7627-4ae0-a6ed-16df6d98347b" />
  <img width="48%" src="https://github.com/user-attachments/assets/4da2caf8-8f6f-4a57-9804-b8110a82e756" />
</p>

</details>

---

<details>
<summary>➕ <strong>상품 등록</strong></summary>

<ul>
  <li>상품 등록 시 이미지 업로드, 카테고리 선택, 가격 등의 정보 입력 가능</li>
  <li>작성 완료 전 미리보기 기능을 통해 등록 내용을 확인할 수 있습니다.</li>
</ul>

<p align="center">
  <img width="48%" src="https://github.com/user-attachments/assets/21718913-400c-4bfa-b202-834a01ef8736" />
  <img width="48%" src="https://github.com/user-attachments/assets/9a8d59ed-043e-490e-ad7e-512c05de13df" />
  <img width="48%" src="https://github.com/user-attachments/assets/e94dccdd-a9b5-41b4-b3c3-f3e8eadeabe1" />
  <img width="48%" src="https://your-image-url.com/register.gif" />
</p>

</details>

---

<details>
<summary>📝 <strong>게시판</strong></summary>

<ul>
  <li>사용자들이 자유롭게 글을 작성하고 댓글을 달 수 있는 게시판 기능</li>
  <li>카테고리별 분류와 최신순 정렬 기능을 제공</li>
</ul>

<p align="center">
  <img width="48%" src="https://github.com/user-attachments/assets/27be9173-7e3c-420d-8672-41ac263c6278" />
  <img width="48%" src="https://github.com/user-attachments/assets/10f63eb8-634a-4a0d-92e8-510f10213f7f" />
</p>

</details>

---

<details>
<summary>💬 <strong>실시간 채팅</strong></summary>

<ul>
  <li>STOMP WebSocket 기반의 실시간 채팅 기능</li>
  <li>구매자와 판매자 간 1:1 채팅 및 알림 기능 구현</li>
</ul>

<p align="center">
  <img width="48%" src="https://github.com/user-attachments/assets/b22d80ed-d6ca-47ec-8319-5e64e93d6aca" />
  <img width="48%" src="https://github.com/user-attachments/assets/185146cf-9b42-4287-8bec-d1d4b8903a40" />
  <img width="48%" src="https://github.com/user-attachments/assets/51c58c58-6dcd-4a92-b6b3-7375556282d6" />
  <img width="48%" src="https://github.com/user-attachments/assets/3ce3e3ea-7420-4aec-9a31-1e2308aa3c75" />
  <img width="48%" src="https://github.com/user-attachments/assets/e2640909-7f2a-4ba6-a5c2-fb22b252c0c7" />
</p>

</details>

---

<details>
<summary>🗺️ <strong>지도</strong></summary>

<ul>
  <li>Kakao Maps API를 이용한 지도 기반 장소 검색 기능</li>
  <li>리스트 클릭 시 마커와 연동되어 상세 정보 제공</li>
</ul>

<p align="center">
  <img width="48%" src="https://github.com/user-attachments/assets/a555f665-e6c1-433c-9cfc-e9fd6d065bb6" />
  <img width="48%" src="https://github.com/user-attachments/assets/15c5f7a9-5b73-41e3-9915-33a3255d2504" />
  <img width="48%" src="https://github.com/user-attachments/assets/ba1594b8-9bff-49f4-94e5-e4e428a9968c" />
</p>

</details>

---

<details>
<summary>📩 <strong>1대1 문의</strong></summary>

<ul>
  <li>사용자가 운영자에게 직접 문의를 보낼 수 있는 기능</li>
  <li>운영자는 문의 내역을 확인하고 답변을 작성할 수 있습니다.</li>
</ul>

<p align="center">
  <img width="48%" src="https://github.com/user-attachments/assets/467ddd3a-b274-4689-95d0-f37b4bf6e3ac" />
  <img width="48%" src="https://github.com/user-attachments/assets/ca77a3f7-f32d-4053-b096-f2e46b4c584a" />
</p>

</details>

---

<details>
<summary>👤 <strong>마이페이지</strong></summary>

<ul>
  <li>내가 등록한 상품, 찜한 상품, 채팅 내역 등을 확인할 수 있는 개인화 페이지</li>
  <li>회원 정보 수정, 탈퇴 기능도 제공합니다.</li>
</ul>

<p align="center">
  <img width="48%" src="https://github.com/user-attachments/assets/8d9152c8-d015-4ad5-892a-5b7f7b411ec9" />
  <img width="48%" src="https://github.com/user-attachments/assets/cc921748-39f4-4fe8-ad7f-96a3215040c2" />
  <img width="48%" src="https://github.com/user-attachments/assets/73369335-8324-449d-beb4-e1d43637febe" />
  <img width="48%" src="https://github.com/user-attachments/assets/bcbd77bc-7bd4-4988-9feb-a4da6648e77d" />
  <img width="48%" src="https://github.com/user-attachments/assets/a7a5c8f0-07f1-418c-b3fb-f57f9c548982" />
  <img width="48%" src="https://github.com/user-attachments/assets/419b4303-fe1d-4f5f-b1d5-504f9ec7b89a" />
  <img width="48%" src="https://github.com/user-attachments/assets/322abc7b-8e4d-490a-ac45-ee09d2ad3251" />
</p>

</details>



## 💡 트러블 슈팅 (Troubleshooting & Solutions)
<details>
 <p>
문제 발생:
이 프로젝트는 저에게 **실시간 통신(WebSocket, STOMP)**과 외부 지도 API 연동(Kakao Maps API) 모두 첫 경험이었습니다. 특히 생소한 STOMP 프로토콜을 이용한 서버-클라이언트 통신 설정과, npm kakao 라이브러리의 기능 제약으로 React 환경에서 JSX 내부에 일반 JavaScript 코드를 직접 사용하며 불필요한 리렌더링 문제가 발생하는 등, 예상치 못한 기술적 난관에 부딪혔습니다.

해결 과정:
이러한 문제들을 해결하기 위해 개발 문서를 끊임없이 참고하고 적용하는 방식으로 접근했습니다. STOMP 통신 안정화를 위해서는 관련 공식 문서와 예제 코드를 분석하며 서버 및 클라이언트 설정을 최적화했고, 네트워크 끊김 시 자동 재연결 로직을 구현하여 사용자 경험을 개선했습니다.
Kakao Maps API의 리렌더링 문제는  필요한 시점에만 렌더링되도록 최적화하여 성능을 확보했습니다. 이 과정에서 React의 렌더링  기법에 대한 이해를 높였습니다.

배운 점:

 문제 해결 능력: 처음 접하는 기술인 STOMP와 Kakao Maps API를 개발 문서만으로 학습하고 실제 프로젝트에 적용하며 문제점들을 해결해 나가는 과정을 통해 주도적인 문제 해결 역량을  강화할 수 있었습니다.

기술 문서 활용 능력: 생소한 기술 스택을 마주했을 때, 공식 개발 문서를 분석하고 적용하는 능력이 얼마나 중요한지 체감했으며, 이는 새로운 기술 습득의 중요한 자산이 되었습니다.

React 최적화: React 환경에서 외부 라이브러리 연동 시 발생할 수 있는 렌더링 성능 이슈를 직접 겪고 해결하며, 컴포넌트 최적화 기법에 대한 실질적인 경험과 이해를 쌓았습니다.
</p>
</details>



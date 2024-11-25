<div align="center">

<img width="300px" src="image/logo.png" />

# 산산산 프로젝트 🚩

다양한 API를 활용해 전국 산 정보와 등산 정보를 제공하는 사이트

</div>
</br>

## 🖥️ 프로젝트 소개

- 등반한 산 포인트를 토대로 레벨 부여와 프로필 변화를 통해 동기 부여
- Kakao Map api와 weather api를 통해 등산에 대한 정보 제공
-

</br>

### API 명세서

<img width="100%" src="image/API1.png" />
<img width="100%" src="image/API2.png" />
<img width="100%" src="image/API3.png" />
</br>

### 🕰️ 개발 기간

24.05.17 - 24.05.24
</br>

### 👥 멤버 소개

<table>
    <tr>
        <td align="center">
          <a href="YOUR_GIT_URL_1">
            <img src="https://avatars.githubusercontent.com/lso401" width="120px;" alt="이소연">
          </a>
        </td>
        <td align="center">
          <a href="YOUR_GIT_URL_2">
            <img src="https://avatars.githubusercontent.com/player397" width="120px;" alt="최승탁">
          </a>
        </td>
    </tr>
    <tr>
        <td align="center">
          <a href="YOUR_GIT_URL_1">
            이소연
          </a>
        </td>
        <td align="center">
          <a href="YOUR_GIT_URL_2">
            최승탁
          </a>
        </td>
    </tr>
    <tr>
        <td align="center">
            FE, BE, 팀장
        </td>
        <td align="center">
            FE, BE
        </td>
    </tr>
</table>

</br>

### ⚙️ 개발 환경

- `Java 17`
- `JDK 17.0.1`
- **IDE** : STS 4, Eclipse
- **Framework** : Springboot, JWT
- **Database** : MySQL
- **ORM** : Mybatis
- **api** : Kakao Map api, 공공데이터 Weather api

</br>

## 📌 주요 기능

#### 로그인

- JWT 인증

#### 회원가입

#### 마이페이지

- 회원 정보 조회/수정/삭제
- 등반 내역 확인
- 팔로잉, 팔로워 친구 조회/추가/삭제

#### 메인페이지

- 로그인 전 / 로그인 후 페이지 제한

</br>

## 📌 겪었던 어려움과 해결 과정

1. **초기설계의 부재에 따른 회의 증가**

- 어려움 쓰기

2. **날씨 api**

- 기존 기상청 날씨누리 api를 사용했을 때 산 봉우리의 날씨가 일치하지 않는 오류가 있었습니다. 고도와 위도가 잘못된 오류라 생각하고 DB를 수정했지만 날씨가 일치하지 않는 오류가 계속 돼서 팀원과 함께 문제점을 찾아보니 산악 날씨 데이터는 따로 제공된다는 점을 알게 되었습니다.

</br>

## 💓 소감

> 이소연

- 삼성 청년 sw 아카데미에서 학습했던 api와 rest api를 활용해서 구현해보았습니다. 일주일 동안 온전히 둘이서 하는 프로젝트는 처음이라 서툰 부분도 많았고 해결하는 데 시간도 더 걸린 부분도 있었습니다. 아직 완전한 이해를 하지 못한 부분도 많아 팀원의 도움을 많이 받아 프로젝트를 진행했습니다.
  이 경험을 토대로 하반기 프로젝트를 위해 api와 로그인 보안기능, JPA에 대해 공부하고 연습해보겠습니다.

> 최승탁

-

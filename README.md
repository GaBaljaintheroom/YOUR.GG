# GEN.G : YOUR.GG

### 과제 기간 📅

2024/12/05 - 2024/12/08 (3일간 진행)

### 프로그램 소개 📋

- League of Legend의 소환사 이름을 검색하면 최근 소환사의 협곡 매치(일반, 솔랭, 자랭) 20개 목록을 보여줍니다.
- 상세 조회 버튼을 누르면 해당 매치의 상세 정보를 보여줍니다.

**검색 후 정보**

- 공통 : 소환사 프로필, 닉네임, 레벨
- 기본 정보 : 챔피언 이미지, 승패 여부, K/D/A, 게임 시간, 게임 시작 시간
- 상세 정보 : 챔피언 이미지, 딜량, 골드, CS, 킬 관여율, K/D/A, 와드(와드 설치 수, 와드 제거 수, 제어 와드 설치 수)
<img width="628" alt="image" src="https://github.com/user-attachments/assets/c842d331-2958-4f7a-8f4f-39e83970a07e">
<img width="644" alt="image" src="https://github.com/user-attachments/assets/0563c90c-56d5-469c-82ab-3ef6d7a14390">



## 과제 고려사항 🔎

### 개발 고려 사항 🛠️

1. Github Actions을 이용한 CI 파이프라인 구축

- 소환사의 정보를 조회하기 위해서는 Riot API를 사용해야 했습니다.
- 이때, Riot API에 제대로 요청/응답을 하는지 확인하기 위해서 통합 테스트를 진행하였습니다.
- 깃 허브에 PR을 생성할 때, Github Actions로 프로그램의 테스트들이 정상적으로 작동하는지 확인을 하여 서비스의 안정성을 보장하도록 했습니다.

2. Thymeleaf를 사용하여 View를 구성

- 지금껏 경험이 있었던 Thymeleaf로 충분히 View를 구성할 수 있다고 판단하였습니다.
- 소환사 정보, 매치 간단 정보, 매치 세부 정보로 Model & View를 구성했습니다.
- bootstrap을 사용하여 UI를 개선시켰습니다.
- Javascript을 이용하여 API를 호출하였습니다.

3. Local-Memory를 이용한 캐시 도입

- League of Legend 게임 중 소환사의 협곡 모드만 필터링을 해야 하고, 최근 20 경기를 보여줘야 하므로 Latency가 굉장히 느렸습니다.
- Riot API의 자체 응답 속도를 높일 수 있는 방법은 없으므로, 캐시를 도입하여 Latency를 개선할 수 있다고 판단했습니다.
- Redis 같은 외부 메모리 DB를 사용할 순 있지만, 현재 상황에서는 오버 엔지니어링일 수 있다고 생각했습니다.
- 따라서 우선 Application의 Local-Memory를 이용한 캐시를 도입하였습니다. 그 결과, 8.06s -> 45ms 로 개선할 수 있었습니다.
- 캐싱하는 Value가 20경기의 데이터, 매치 세부 등 용량이 크다고 판단되어 스케줄러로 1분마다 Cache를 비워주도록 했습니다.

4. @RestControllerAdvice을 이용한 Riot API 응답 예외처리 

- Riot API에 요청 후 예외가 발생했을 때, Riot API 제공해주는 예외가 아닌 프로그램의 자체 커스텀 예외가 필요하다고 느꼈습니다.
- 따라서 Riot API에서 발생할 수 있는 예외 목록을 코드로 모아두고 해당 예외가 발생했을 때 알맞은 커스텀 예외를 찾아 던지도록 하였습니다.
- 커스텀 예외는 BusinessException을 상속받아, @RestControllerAdvice로 핸들링해 예외 처리 코드를 깔끔하게 작성할 수 있었습니다.

### 추후 개선 방안 🔧
**문제점 1**: 캐시는 Cache Hit 비율이 낮으면 처음 응답속도는 똑같이 느리기 때문에 사용자 경험에 문제가 발생할 수 있습니다.
- 문제 해결 방안 고민: 처음 응담 속도가 개선되지 않으면, 캐시로는 한계가 있있을 것 이라고 판단했습니다. 따라서 Riot API는 각 Account, Champion, League
  등등 카테고리로 나누어져 있으므로, 현재 프로그램도 각각 카테고리마다 애플리케이션을 나누어 요청을 분산시키면, 기본 응답 속도를 개선할 수 있을 것이라고 생각이 듭니다.
  이때, 각 서버로 요청을 보낼 떄 비동기 방식으로 보낸다면, 응답 속도가 더 개선될 수 있음을 예상합니다.
<img width="802" alt="image" src="https://github.com/user-attachments/assets/d3891d13-4c9f-4c74-8fe4-afde34fbb2a9">


**문제점 2**: 외부 API에 의존도가 높은 서비스이므로, 외부 API 자체에 문제가 발생하거나 요청 시 문제가 발생하면 장애 전파 및 사용자 경험이 낮아집니다.
- 문제 해결 방안 고민: 
 1. CircuitBreaker를 이용해 외부 API 장애를 관리하도록 합니다. 총(n)번 통신 중 실패율(n%)를 지정하여 CircuitBreaker를 오픈하면
Fail Fast로 빠른 응답을 반환하도록 합니다.
 2. 로딩 창을 생성하여 사용자가 응답을 기다릴 때까지 지루하지 않도록 사용자 경험을 개선합니다.


### 기술 ⚙️

- JAVA 17
- JUnit, Mockito
- Spring Boot 3.4.0
- Thymeleaf
- Javascript
- Git/Github
- Github Actions
- [Riot API](https://developer.riotgames.com/apis)

### 깃 전략 🧑🏻‍💻

main → develop → feature

main : 상용 서버에 적용될 브랜치

develop : 개발 브랜치

feature : 각 기능을 구현할 브랜치

- feat/{name}-#{issue_number}

규칙

1. feature에서 develop으로 머지할 때는 squash 머지를 한다.
2. develop에서 main으로 머지할 때는 rebase 머지를 한다.

### 깃 컨벤션 ✅

- feat : 기능 구현
- refactor : 개선 사항
- test : 테스트 작성
- docs : 리드미 작성
- fix : 버그 수정
- chore : 세팅 업무

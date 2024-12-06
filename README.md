# GEN.G : YOUR.GG

### 과제 기간

2024/12/05 - 2024/12/08 (3일간 진행)

## 과제 고려사항

### 기술

- JAVA 17
- Spring Boot 3.4.0
- Thymeleaf
- Javascript
- Git/Github
- Github Actions

### 깃 전략

main → develop → feature

main : 상용 서버에 적용될 브랜치

develop : 개발 브랜치

feature : 각 기능을 구현할 브랜치

- feat/{name}-#{issue_number}

규칙

1. feature에서 develop으로 머지할 때는 squash 머지를 한다.
2. develop에서 main으로 머지할 때는 rebase 머지를 한다.

### 깃 컨벤션

- feat : 기능 구현
- refactor : 개선 사항
- test : 테스트 작성
- docs : 리드미 작성
- fix : 버그 수정
- chore : 세팅 업무
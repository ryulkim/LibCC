# MSA 환경 서버에서 쓸 공통 라이브러리
### 제공되는 기능
- JWT 생성/파싱
- 공통 예외 핸들러
- 공통 예외 코드 및 응답 처리
- 랜덤 닉네임 생성

### 라이브러리
**jitpack을 사용한 라이브러리 배포**  
https://github.com/ryulkim/LibCC/releases

**git package로 라이브러리 배포** (구)
`com.library:jwt` (구)
- JWT 생성/파싱

`com.library:libcc` (구)
- 공통 예외 핸들러
- 공통 예외 코드 및 응답 처리
- 랜덤 닉네임 생성

### 목적
`com.library:jwt` (구)
- gateway와 member 서버에서 jwt 토큰 생성 및 유효한지 체크를 하기 위한 목적
  
`com.library:libcc` (구)
- 다른 서버들에서 예외 상태 코드와 응답 코드 및 공통 예외 핸들러를 쓰기 위한 목적
- StatusCode interface를 구현하는 enum을 생성해 예외 코드 및 성공 코드 커스텀 가능



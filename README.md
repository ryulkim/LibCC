# MSA 환경 서버에서 쓸 공통 라이브러리
### 제공되는 기능
- JWT 생성/파싱
- 공통 예외 핸들러
- 공통 예외 코드 및 응답 처리
- 랜덤 닉네임 생성

## 🔧 설치 및 사용

Gradle에 다음 의존성을 추가하세요:

```groovy
repositories {
    mavenCentral()
    // 이 부분 추가
    maven {
        url 'https://jitpack.io'
    }
}

implementation 'com.github.ryulkim.LibCC:lib:v1.1.0' // JWT 외 모든 기능
implementation 'com.github.ryulkim.LibCC:jwt:v1.1.0' // JWT 관련된 기능
```

예외 핸들러를 사용하고 싶다면,   
SpringBootApplication에서 다음을 추가해주세요:
```java
@SpringBootApplication(scanBasePackages = {
  "com.mycompany.app",       // 여러분 앱의 패키지
  "com.library.lib.exception"  // 모듈의 패키지
})
```
---


## ✨ New Features

### ✅ 공통 예외 처리

#### 1. 공통 예외 핸들러 (`GlobalExceptionHandler`)

- `@RestControllerAdvice` 기반의 예외 처리 지원
- 발생한 예외에 대해 로그 자동 출력
  - 출력 형식:

    ```
    {Exception 클래스명}: {지정한 에러 메시지}
    ```

#### 2. 공통 코드 정의

- 제공되는 기본 에러 코드:

  ```java
  SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생하였습니다.")
  ```

- 제공되는 기본 성공 코드:

  ```java
  SUCCESS(HttpStatus.OK, "성공했습니다.")
  ```

- 제공되는 ApiResponseEntity 형태
  - 출력 형식:

    ```json
    {
      "code": "{enum_name}",
      "message": "{message}",
      "result": "{data}"
    }
    ```

  - 사용 예시:
    - 성공 응답

      ```java
      ApiResponseEntity.onSuccess(Object result);
      ApiResponseEntity.onSuccess(null);
      ```

    - 실패 응답

      ```java
      ApiResponseEntity.onFailure(StatusCode code);
      ```

    - 응답 커스텀

      ```java
      ApiResponseEntity.from(StatusCode code, Object result);
      ApiResponseEntity.from(StatusCode code, null);
      ```

#### 3. 사용 방법 예시

**(1) 공통 예외 사용**

```java
@GetMapping
public ResponseEntity<ApiResponse<Object>> test() {
    throw new GlobalException(ErrorCode.SERVER_ERROR);
}
```

- 💡 결과:  
  ![response](https://github.com/user-attachments/assets/46f07ff8-2410-4d34-ba0d-644ff145ae63)  
  ![image](https://github.com/user-attachments/assets/b0736d7e-8af1-4fab-b975-7a0f39364a62)

- 📄 로그:  
  ![log](https://github.com/user-attachments/assets/dd37b452-d906-4b1b-b751-60e2533d6454)

---

**(2) 에러 코드 확장**

- `StatusCode`를 구현한 enum을 새로 정의

```java
@Getter
@RequiredArgsConstructor
public enum ERORORO implements StatusCode {
    TEST(NOT_FOUND, "test다. test"),
    TEST2(NOT_FOUND, "testtesttest다.");

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public String getName() {
        return this.name();
    }
}
```

- 사용 예:

```java
@GetMapping
public ResponseEntity<ApiResponse<Object>> test() {
    throw new GlobalException(ERORORO.TEST2);
}
```

- 💡 결과:  
  ![response](https://github.com/user-attachments/assets/7454e5d3-fcd1-436c-a501-b9ecfc4d2287)  
  ![image](https://github.com/user-attachments/assets/a105fe99-3355-43a7-bdd1-5ff2817f22ae)

- 📄 로그:  
  ![log](https://github.com/user-attachments/assets/551edc19-7e0a-4386-9bee-35e03672768b)

---

**(3) 커스텀 예외 클래스 사용**

```java
@Getter
public class AError extends GlobalException {
    public AError(StatusCode errorCode) {
        super(errorCode);
    }
}
```

```java
@GetMapping
public ResponseEntity<ApiResponse<Object>> test() {
    throw new AError(ERORORO.TEST2);
}
```

- 💡 결과:  
  ![custom](https://github.com/user-attachments/assets/e210ecb3-5f0e-489d-b930-76cb43eee3eb)

---

**(4) ResponseEntity create 응답**
- **ApiResponseEntity.class**:  
```java
public static <T> ResponseEntity<ApiResponse<T>> create(StatusCode code, String url, T result) {
    return ResponseEntity.created(URI.create(url))
            .body(new ApiResponse<>(code.getName(), code.getMessage(), result));
}
```
- 사용법 예
```java
@GetMapping
public ResponseEntity<ApiResponse<Object>> test() {
    long resourceId=1L;

    return ApiResponseEntity.create(ERORORO.CREATE,"/located/"+resourceId, null);
}
```


---

### 🐣 랜덤 닉네임 생성기

#### 1. 제공 포맷

- 형식: **형용사 + 명사**
- 형용사 예시:  
  `"행복한", "즐거운", "신나는", "멋진", "용감한", "친절한", "성실한", "유머러스한"...`
- 명사 예시:  
  `"호랑이", "고양이", "펭귄", "돌고래", "알파카", "수달", "전기뱀장어"...`

#### 2. 사용 방법

```java
NickGenerator.generateUniqueNickname();
```

- 💡 결과 예시:  
  ![nickname](https://github.com/user-attachments/assets/5be5519a-7783-4bd7-b2f2-1fd57714ea77)  
  ![image](https://github.com/user-attachments/assets/81ea631c-7e8f-4c92-814f-96a5765964b7)


---



### 라이브러리
**jitpack을 사용한 라이브러리 배포**  
- 사용법 및 기능들 보기  
  > [릴리즈 노트](https://github.com/ryulkim/LibCC/releases)

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



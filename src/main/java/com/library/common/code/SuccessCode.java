package com.library.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    SUCCESS(HttpStatus.OK, "성공했습니다."),

    //File
    //Image
    //Sound
    //Video

    //Member(2100~2199)
    CREATE_MEMBER(HttpStatus.CREATED, "사용자 등록 성공했습니다."),
    AUTH_SUCCESS(HttpStatus.REQUEST_TIMEOUT, "사용자 인증 성공했습니다."),
    TOKEN_REFRESHED(HttpStatus.CREATED, "토큰 재발급 성공했습니다."),
    VALID_EMAIL(HttpStatus.OK, "사용 가능한 Email 입니다."),
    SUCCESS_MEMBER_REGISTER(HttpStatus.OK, "회원가입에 성공하였습니다."),
    SUCCESS_LOGIN(HttpStatus.OK, "로그인에 성공하였습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}

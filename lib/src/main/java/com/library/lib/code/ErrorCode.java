package com.library.lib.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@Getter
public enum ErrorCode implements StatusCode {
//
//    //File
//    //Image
//    INVALID_REQUEST(BAD_REQUEST, "올바르지 않은 요청입니다."),
//    INVALID_IMAGE_FORMAT(BAD_REQUEST, "지원하지 않는 이미지 형식입니다."),
//    EXCEED_IMAGE_CAPACITY(BAD_REQUEST, "이미지 크기가 허용된 최대 용량을 초과했습니다."),
//    FAIL_IMAGE_UPLOAD(BAD_REQUEST, "이미지 업로드에 실패했습니다."),
//    //Sound
//    NOT_SOUND(NOT_FOUND, "요청한 음성 파일이 없습니다."),
//    FAIL_CONVERT_SOUND(INTERNAL_SERVER_ERROR, "음성 파일 변환에 실패했습니다."),
//    //Video
//
//    //Auth
//    NOT_SUPPORT_PROVIDER(BAD_REQUEST, "지원하지 않는 social login 방식입니다."),
//    INVALID_AUTHORITY(FORBIDDEN, "해당 리소스에 접근할 권한이 없습니다."),
//    INVALID_PASSWORD(UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
//    INVALID_JWT_FORMAT(BAD_REQUEST, "JWT 형식이 아닙니다."),
//    JWT_NOT_FOUND(NOT_FOUND, "토큰이 전달되지 않았습니다."),
//    INVALID_ACCESS_TOKEN(UNAUTHORIZED, "유효하지 않은 AccessToken 입니다."),
//    INVALID_REFRESH_TOKEN(UNAUTHORIZED, "유효하지 않은 RefreshToken 입니다."),
//    INVALID_AUTHORIZATION_CODE(UNAUTHORIZED, "유효하지 않은 인가 코드 입니다."),
//    EXPIRED_ACCESS_TOKEN(UNAUTHORIZED, "만료된 AccessToken 입니다."),
//    EXPIRED_REFRESH_TOKEN(UNAUTHORIZED, "만료된 RefreshToken 입니다."),
//    FAIL_OAUTH_USERINFO_RETRIEVAL(UNAUTHORIZED, "회원 정보를 가져오는데 실패했습니다."),
//    INVALID_USER_LOGGED_IN(UNAUTHORIZED, "로그인이 필요합니다."),
//
//
//    //Member
//    NOT_FOUND_MEMBER_ID(NOT_FOUND, "요청한 ID에 해당하는 사용자가 존재하지 않습니다."),
//    NOT_FOUND_MEMBER_EMAIL(NOT_FOUND, "요청한 이메일에 해당하는 사용자가 존재하지 않습니다."),
//    NOT_FOUND_MEMBER_PROFILE_FILE(NOT_FOUND, "요청한 이미지 파일을 찾을 수 없습니다."),
//    NON_VALIDATED_PASSWORD(UNAUTHORIZED, "비밀번호가 유효하지 않습니다."),
//    ALREADY_EXIST_EMAIL(CONFLICT, "이미 존재하는 Email 입니다."),
//    BLANK_EMAIL_INPUT(BAD_REQUEST, "Email 을 입력해주세요."),
//    INVALID_EMAIL_PATTERN(BAD_REQUEST, "올바르지 않은 Email 형식입니다."),
//    NOT_ACCEPT_TERMS(CONFLICT, "약관에 동의해주세요."),
//    INVALID_PASSWORD_PATTERN(CONFLICT, "올바르지 않은 Password 형식입니다. 8글자 이상 16글자 이하로 작성해주세요."),
//    BLANK_PASSWORD_INPUT(BAD_REQUEST, "Password 를 입력해주세요."),
//    LOGIN_FAILED(CONFLICT, "아이디와 비밀번호를 확인해주세요."),
//
//    SERVER_ERROR(INTERNAL_SERVER_ERROR, "서버 에러가 발생하였습니다.")
//    ;
//
//
    SERVER_ERROR(INTERNAL_SERVER_ERROR, "서버 에러가 발생하였습니다.")

    ;

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public String getName() {
        return this.name();
    }
//
}

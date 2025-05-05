package com.library.common.response;

import com.library.common.code.StatusCode;
import com.library.common.code.SuccessCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseEntity {
    public static <T> ResponseEntity<ApiResponse<T>> onSuccess(T result) {
        return ResponseEntity.ok().body(new ApiResponse<>(SuccessCode.SUCCESS.name(), SuccessCode.SUCCESS.getMessage(), result));
    }

    public static <T> ResponseEntity<ApiResponse<T>> from(StatusCode code, T result) {
        return ResponseEntity.status(code.getHttpStatus())
                .body(new ApiResponse<>(code.getName(), code.getMessage(), result));
    }

    public static <T> ResponseEntity<ApiResponse<T>> onFailure(StatusCode code) {
        return ResponseEntity.status(code.getHttpStatus())
                .body(new ApiResponse<>(code.getName(), code.getMessage(), null));
    }

    public static <T> ResponseEntity<ApiResponse<T>> badRequest(String message) {
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>(HttpStatus.BAD_REQUEST.name(), message, null));
    }

    public static <T> ResponseEntity<ApiResponse<T>> ok(String message) {
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.name(), message, null));
    }
}

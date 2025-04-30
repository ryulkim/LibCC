package com.library.common.exception;

import com.library.common.code.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomRedisException extends RuntimeException {
    private final ErrorCode errorCode;
}

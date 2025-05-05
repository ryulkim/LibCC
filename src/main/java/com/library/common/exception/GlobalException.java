package com.library.common.exception;

import com.library.common.code.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GlobalException extends RuntimeException{
    private final StatusCode errorCode;
}

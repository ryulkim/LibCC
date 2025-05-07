package com.library.lib.exception;

import com.library.lib.code.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GlobalException extends RuntimeException{
    private final StatusCode errorCode;
}

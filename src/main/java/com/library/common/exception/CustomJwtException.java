package com.library.common.exception;

import com.library.common.code.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomJwtException extends RuntimeException {
  private final ErrorCode errorCode;
}
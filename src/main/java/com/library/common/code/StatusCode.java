package com.library.common.code;

import org.springframework.http.HttpStatus;

public interface StatusCode {
    HttpStatus getHttpStatus();
    String getMessage();
    String getName();
}

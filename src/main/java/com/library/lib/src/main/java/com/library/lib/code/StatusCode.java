package com.library.lib.code;

import org.springframework.http.HttpStatus;

public interface StatusCode {
    HttpStatus getHttpStatus();
    String getMessage();
    String getName();
}

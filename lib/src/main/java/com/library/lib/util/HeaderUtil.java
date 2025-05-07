package com.library.lib.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

public class HeaderUtil {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TYPE = "Bearer ";

    // Request Header 에서 토큰 정보 추출
    public static String getAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TYPE)) {
            return bearerToken.substring(BEARER_TYPE.length());
        }
        return null;
    }
}

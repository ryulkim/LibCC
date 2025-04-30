package com.library.common.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class RedisTokenService {
    private final RedisTemplate<String, String> redisTemplate;

    // 토큰 저장
    public void saveToken(String key, String value, long expirationMinutes) {
        redisTemplate.opsForValue().set(key, value, expirationMinutes, TimeUnit.MINUTES);
    }

    // 토큰 조회
    public String getToken(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 토큰 삭제
    public void deleteToken(String key) {
        redisTemplate.delete(key);
    }
}
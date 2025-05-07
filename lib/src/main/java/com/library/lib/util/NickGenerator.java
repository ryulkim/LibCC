package com.library.lib.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NickGenerator {
    public static String generateUniqueNickname() {
        UUID uuid = UUID.randomUUID();
        String adjective = NicknameCategory.ADJECTIVE.getWord(uuid.getLeastSignificantBits());
        String noun = NicknameCategory.NOUN.getWord(uuid.getMostSignificantBits());

        //String randomNumber = String.format("%04d", Math.abs(uuid.hashCode() % 10000)); 랜덤 넘버 삭제

        return adjective + noun;
    }

}

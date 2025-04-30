package com.library.common.util;

import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class CodeGenerator {
    private final Random random = new Random();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public String generateCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }
}

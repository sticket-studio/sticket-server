package com.ec.sticket.util;

import java.util.Random;

public class RandomUtil {
    private static Random rnd = new Random();

    public static String getRandomPassword() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append((char) ((rnd.nextInt(26)) + 'a'));
        }
        for (int i = 0; i < 4; i++) {
            sb.append((char) ((rnd.nextInt(26)) + 'A'));
        }
        for (int i = 0; i < 4; i++) {
            sb.append(rnd.nextInt(10));
        }
        return sb.toString();
    }
}

package com.game.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {

    // 固定的盐
    private static final String SALT = "9E3E97236C4ECAF22657D827086C8B2AFEBFDB2244EA36CFA777C93C662705A0";

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(SALT.getBytes());
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // 处理异常
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String password = "root";
        String hashedPassword1 = hashPassword(password);
        String hashedPassword2 = hashPassword(password);

        System.out.println("Hashed password 1: " + hashedPassword1);
        System.out.println("Hashed password 2: " + hashedPassword2);
    }
}

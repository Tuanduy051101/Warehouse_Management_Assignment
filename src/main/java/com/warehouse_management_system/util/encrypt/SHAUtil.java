package com.warehouse_management_system.util.encrypt;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class SHAUtil {
    private static final String ALGORITHM = "SHA-256";

    // create salt
    public static byte[] generateSalt() {
        // SecureRandom is class of java.security used to create random secure value
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    // hash passwd by salt
    public static String hashPassword(String password, byte[] salt) throws Exception {
        // MessageDigest is a class of java.security used to hash with algorithm
        MessageDigest md = MessageDigest.getInstance(ALGORITHM);
        // add salt in MessageDigest
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes());
        return Base64Util.encode(hashedPassword);
    }

    // check password
    public static boolean checkPassword(String inputPassword, String storedHashedPassword, byte[] salt) throws Exception {
        String hashedInputPassword = hashPassword(inputPassword, salt);
        return hashedInputPassword.equals(storedHashedPassword);
    }
}

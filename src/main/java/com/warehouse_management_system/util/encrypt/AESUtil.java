package com.warehouse_management_system.util.encrypt;

import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class AESUtil {
    // define algorithm name
    private static final String ALGORITHM = "AES";

    // define master key to encrypt dynamic key
    private static final String MASTER_KEY = "MasterKey1234567";

    // create secret key
    // SecretKey is a object used to save and manage key in JCA for symmetrical algorithm
    // n is length of key
    public static SecretKey generateKey(int n) throws Exception {
        // KeyGenerator is object in JCA used to create secret key for symmetrical algorithm
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(n); // initial length of key
        return keyGenerator.generateKey();
    }

    // encode data
    public static String encrypt(String data, SecretKey key) throws Exception {
        System.out.println("running");
        // Cipher is a class in JCA (Java Cryptography Architecture) to execute encode and decode maths for symmetrical and asymmetrical algorithm
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key); // initial cipher object with encode mode
        System.out.println(data);
        System.out.println(key);
        // cipher work with data have type is byte array
        // doFinal is method used to encrypt data and return byte array
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        // convert byte array to string base64
        return Base64Util.encode(encryptedData);
    }

    // decrypt data
    // SecretKey is a object used to present for key
    // Exception such: NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException
    public static String decrypt(String encryptedData, SecretKey key) throws Exception {
        // Cipher is a class in JCA (Java Cryptography Architecture) to execute encode and decode maths for symmetrical and asymmetrical algorithm

        // Initial cipher object with specific algorithm
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // Initial cipher object with specific mode decode and key
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedData = cipher.doFinal(Base64Util.decode(encryptedData));
        return new String(decryptedData);
    }

    // encrypt master key
    public static String encryptKey(SecretKey key) throws Exception {
        // SecretKeySpec is class of javax.crypto.spec used to create SecretKey
        SecretKeySpec masterKeySpec = new SecretKeySpec(MASTER_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, masterKeySpec);

        // key.getEncoded() return byte array
        byte[] encryptedKey = cipher.doFinal(key.getEncoded());
        System.out.println(Arrays.toString(encryptedKey));
        return Base64Util.encode(encryptedKey);
    }

    // decrypt secret key
    public static SecretKey decryptKey(String encryptedKey) throws Exception {
        SecretKeySpec masterKeySpec = new SecretKeySpec(MASTER_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, masterKeySpec);
        System.out.println(Arrays.toString(Base64Util.decode(encryptedKey)));
        byte[] decryptKey = cipher.doFinal(Base64Util.decode(encryptedKey));
        return new SecretKeySpec(decryptKey, ALGORITHM);
    }


}

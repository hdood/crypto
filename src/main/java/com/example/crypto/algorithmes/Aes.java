package com.example.crypto.algorithmes;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.lang.reflect.Array;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.stream.StreamSupport;

public class Aes {

    public static void main(String[] args) throws Exception {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(192);
        SecretKey key = keyGenerator.generateKey();

    }

    public static String update(String algo, SecretKey key, byte[] iv, int mode, String message)
            throws Exception {
        Cipher cipher = Cipher.getInstance(algo);

        if (algo.equals("AES/ECB/PKCS5Padding")) {

            cipher.init(mode, key);

        } else {

            IvParameterSpec param = new IvParameterSpec(iv);
            cipher.init(mode, key, param);

        }


        byte[] cipherText = cipher.doFinal(message.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }

    public static String decode(String algo, SecretKey key, byte[] iv, int mode, String message)
            throws Exception {
        Cipher cipher = Cipher.getInstance(algo);


        if (algo.equals("AES/ECB/PKCS5Padding")) {

            cipher.init(mode, key);

        } else {

            IvParameterSpec param = new IvParameterSpec(iv);
            cipher.init(mode, key, param);

        }


        byte[] cipherText = cipher.doFinal(Base64.getDecoder()
                .decode(message));
        return new String(cipherText);
    }
}
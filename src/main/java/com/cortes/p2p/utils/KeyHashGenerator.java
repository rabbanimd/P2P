package com.cortes.p2p.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class KeyHashGenerator {
    private static final String key = "9iUndh3i9mNNmell";
    private static final String vector = "T8iisjpoem3mslj4";

    public static String generateHash(String value)
    {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(vector.getBytes("UTF-8"));
            SecretKeySpec sKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE,sKeySpec, ivParameterSpec);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.atp.webservice.parking_reservation_10.services.algorithms;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

public class KeyHelper {

    private static final String SERVER_SECRET = "123@!@#$";

    /**
     * Generate Secret Key
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String genSecretKey() throws NoSuchAlgorithmException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(UUID.randomUUID());
        stringBuilder.append(SERVER_SECRET);
        return new String(Base64.getEncoder().encode(stringBuilder.toString().getBytes()));
    }

    /**
     * Create {@link SecretKeySpec} from user key
     * @param myKey
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    private static SecretKeySpec setKey(String myKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] key;
        MessageDigest sha = null;
        key = myKey.getBytes("UTF-8");
        sha = MessageDigest.getInstance("SHA-256");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        return  secretKey;
    }

    /**
     * Encrypt message
     * @param strToEncrypt
     * @param secret
     * @return
     */
    public static String encrypt(String strToEncrypt, String secret) {
        try {
            SecretKeySpec secretKey = setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    /**
     * Decrypt message
     * @param strToDecrypt
     * @param secret
     * @return
     */
    public static String decrypt(String strToDecrypt, String secret) {
        try {
            SecretKeySpec secretKey = setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}

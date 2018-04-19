package com.atp.webservice.parking_reservation_10.services.algorithms;


import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeypairHelper {

    /**
     * Generate key {@link KeyPair}
     * @return a RSA keypair
     * @throws NoSuchAlgorithmException
     */
    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }

    /**
     * Restore a {@link PrivateKey} from A string was encode by {@link Base64}
     * @param privateKeyContent
     * @return PrivateKey
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey restorePrivateKey(String privateKeyContent) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        PrivateKey privKey = keyFactory.generatePrivate(keySpecPKCS8);
        return privKey;
    }

    /**
     * Restore a {@link PublicKey} from A string was encode by {@link Base64}
     * @param publicKeyContent
     * @return PublicKey
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey restorePublicKey(String publicKeyContent) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        return  pubKey;
    }

    /**
     * Encrypt message by {@link PublicKey}
     * @param publicKey
     * @param message
     * @return Encrypted message as a String
     * @throws Exception
     */
    public static String encrypt(PublicKey publicKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return  Base64.getEncoder().encodeToString(cipher.doFinal(Base64.getEncoder().encode(message.getBytes())));
    }

    /**
     * Decrypt message by {@link PrivateKey}
     * @param privateKey
     * @param encrypted encrypted message
     * @return Decrypted message as a String
     * @throws Exception
     */
    public static String decrypt(PrivateKey privateKey, String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(Base64.getDecoder().decode(cipher.doFinal(Base64.getDecoder().decode(encrypted))));
    }

    /**
     * Encode private key by Base64
     * @param privateKey
     * @return
     */
    public static String EncodePrivateKeyToString(PrivateKey privateKey){
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    /**
     * Encode public key by Base64
     * @param publicKey
     * @return
     */
    public static String EncodePublicKeyToString(PublicKey publicKey){
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
}

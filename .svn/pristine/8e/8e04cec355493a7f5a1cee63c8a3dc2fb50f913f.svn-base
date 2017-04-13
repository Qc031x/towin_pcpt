package com.sgfm.base.util;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESUtil {

    // 加密算法
    private static final String ALG               = "AES";
    // 字符编码
    private static final String ENC               = "UTF-8";
    // 密钥正规化算法
    private static final String SEC_NORMALIZE_ALG = "MD5";
    
    //秘钥
    private static final String keyPassword		  = "twheheda";

    public static void main(String... args) throws Exception {
        // 加密
        String text = "Hello World! 你好，世界！";// 需要加密的数据原文
        String encrypted = encrypt(text);
        System.out.println(encrypted);

        // 解密
        System.out.println(decrypt(encrypted));
    }

    /**
     * AES加密
     * @param data
     * @return
     */
    public static String encrypt(String data) {
    	try {
    		MessageDigest dig = MessageDigest.getInstance(SEC_NORMALIZE_ALG);
            byte[] key = dig.digest(keyPassword.getBytes(ENC));
            SecretKeySpec secKey = new SecretKeySpec(key, ALG);

            Cipher aesCipher = Cipher.getInstance(ALG);
            byte[] byteText = data.getBytes(ENC);

            aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
            byte[] byteCipherText = aesCipher.doFinal(byteText);

            Base64 base64 = new Base64();
            return new String(base64.encodeBase64URLSafe(byteCipherText), ENC);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return null;
    }

    /**
     * AES解密
     * @param ciphertext
     * @return
     * @throws Exception
     */
    public static String decrypt(String ciphertext) throws Exception {
    	try {
    		MessageDigest dig = MessageDigest.getInstance(SEC_NORMALIZE_ALG);
            byte[] key = dig.digest(keyPassword.getBytes(ENC));
            SecretKeySpec secKey = new SecretKeySpec(key, ALG);

            Cipher aesCipher = Cipher.getInstance(ALG);
            aesCipher.init(Cipher.DECRYPT_MODE, secKey);
            Base64 base64 = new Base64();
            byte[] cipherbytes = base64.decodeBase64(ciphertext.getBytes());
            byte[] bytePlainText = aesCipher.doFinal(cipherbytes);
            return new String(bytePlainText, ENC);
		} catch (Exception e) {
			// TODO: handle exception
		}
        return null;
    }

}
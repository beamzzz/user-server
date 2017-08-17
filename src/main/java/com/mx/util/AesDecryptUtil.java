package com.mx.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * AesDecryptUtil
 * @author xuchangshun
 * @version 1.0
 *
 */
public class AesDecryptUtil {
    /**设置AES的IV,保证与其他系统的通信**/
    private static final String IV_STRING = "A-16-Byte-String";

    /**
     * 加密
     * @param content 明文字符串
     * @param password  密钥
     * @return
     */
    private static byte[] encrypt(String content, String password) {
        try {

            byte[] enCodeFormat = password.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] byteContent = content.getBytes("UTF-8");
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**解密
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    private static byte[] decrypt(byte[] content, String password) {
        try {
            byte[] enCodeFormat = password.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n]& 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    private static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes("utf-8");
        int iLen = arrB.length;
        byte[] arrOut = new byte[iLen / 2];

        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }
    /**
     * 获得加密后的字符串
     * @param unEncodeStr 未加密的字符串
     * @param key 密钥
     * @return
     */
    public static String getEncodeStr(String unEncodeStr,String key){
        byte [] encodeByte = encrypt(unEncodeStr,key);
        String encodeStr = byte2hex(encodeByte);
        return encodeStr;
    }
    /**
     * 获得加密后的字符串
     * @param unEncodeStr 未加密的字符串
     * @param key 密钥
     * @return
     */
    public static String getEncodeStrBase64(String unEncodeStr,String key){
        byte [] encodeByte = encrypt(unEncodeStr,key);
        String encodeStr = Base64.encodeBase64String(encodeByte);
        return encodeStr;
    }
    /**
     * 获得解密字符串
     * @param encodeStr 加密的字符串
     * @param key 密钥
     * @return 返回明文
     */
    public static String getDecodeStr(String encodeStr,String key){
        String decodeStr = null;
        try {
            byte [] encodeByte =  hexStr2ByteArr(encodeStr);
            byte [] decodeByte = decrypt(encodeByte, key);
            decodeStr = new String(decodeByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodeStr;
    }

    public static String getDecodeStrForBase64(String encodeStr,String key){
        String decodeStr = null;
        try {
            boolean ss = Base64.isBase64(encodeStr);
            System.out.println(ss);
            byte [] encodeByte = Base64.decodeBase64(encodeStr);
            byte [] decodeByte = decrypt(encodeByte, key);
            decodeStr = new String(decodeByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodeStr;
    }
}

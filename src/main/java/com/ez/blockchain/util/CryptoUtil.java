package com.ez.blockchain.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 加密工具类
 *
 * @author Ethan.Z
 * @date 2022-09-19 18:21
 */
public class CryptoUtil {

    private CryptoUtil() {}

    public static final char[] CHARS = new char[]{'0','1','2','3',
        '4','5','6','7','8','9','A','B','C','D','E','F'};

    /**
     * SHA256 算法
     *
     * @param str 字符串
     * @return 消息摘要
     */
    public static String SHA256(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            System.out.println("getSHA256 is error" + e.getMessage());
        }
        return encodeStr;
    }

    /**
     * MD5 算法
     *
     * @param str 字符串
     * @return 消息摘要
     */
    public static String MD5(String str) {
        try {
            StringBuffer buffer = new StringBuffer();
            byte [] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] targ = messageDigest.digest(bytes);
            for(byte b : targ) {
                buffer.append(CHARS[(b >> 4) & 0x0F]);
                buffer.append(CHARS[b & 0x0F]);
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * UUID
     *
     * @return UUID
     */
    private static String UUID() {
        return UUID.randomUUID().toString().replaceAll("\\-", "");
    }

    /**
     * bytes数组转16进制值的字符串
     *
     * @param bytes 字节
     * @return 16进制字符串
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                builder.append("0");
            }
            builder.append(temp);
        }
        return builder.toString();
    }
}

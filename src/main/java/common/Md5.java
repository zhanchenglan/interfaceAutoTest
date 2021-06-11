package common;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
    /**
     * 利用MD5进行加密
     */
    public String EncoderByMd5(String str) {
        String temp = "";
        //确定计算方法
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");

            byte[] encodeMd5Digest = md5.digest(str.getBytes());
            temp = convertByteToHexString(encodeMd5Digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return temp;
    }


    public static String convertByteToHexString(byte[] bytes) {
        String result = "";
        for (int i = 0; i < bytes.length; i++) {
            int temp = bytes[i] & 0xff;
            String tempHex = Integer.toHexString(temp);
            if (tempHex.length() < 2) {
                result += "0" + tempHex;
            } else {
                result += tempHex;
            }

        }
        return result;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String password = "123456";
        Md5 md5 = new Md5();
        String passwordMd5 = md5.EncoderByMd5(password);
        System.out.println(passwordMd5);
    }

}

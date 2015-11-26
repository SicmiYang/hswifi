package com.nfyg.hswx.utils.common;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * MD5工具
 */
public class MD5Utils {
    public static String encode(String passwd){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] result = messageDigest.digest(passwd.getBytes("utf-8"));

            StringBuffer sb = new StringBuffer();

            for(byte b: result){
                int num = b & 0xff; // 将原本byte型的数转换成int,从而使得原本的负数转为正数
                if (num < 16) {
                    sb.append(0);
                }
                sb.append(Integer.toHexString(num));
            }

            return sb.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.ctbu.utils;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description 密码的md5加密算法
 * @Author LIYING
 */
public class Md5Util {
    public static String toMd5(String info) {
        byte[] secretByte;
        try {
            //初始化MessageDigest信息摘要对象,并指定为MD5
            MessageDigest md = MessageDigest.getInstance("md5");
            //将字符串转换为字节数组并计算摘要
            secretByte = md.digest(info.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("找不到md5算法");
        }
        //将每个字节的二进制补码按顺序连接起来后去掉开头的0后返回
        StringBuilder md5Code = new StringBuilder(new BigInteger(1, secretByte).toString(16));
        for (int i = 0; i < 32 - md5Code.length(); i++) {
            md5Code.insert(0, "0");
        }
        //返回md5字符串
        return md5Code.toString();
    }
}

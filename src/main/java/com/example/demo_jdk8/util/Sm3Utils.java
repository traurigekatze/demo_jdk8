package com.example.demo_jdk8.util;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Arrays;

@Slf4j
public class Sm3Utils {

    /**
     * 默认UTF-8编码
     */
    public static final String DEFAULT_ENCODING = "UTF-8";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 返回加密后，固定长度为32的16进制字符串
     * @param plainText 明文
     * @return
     */
    public static String encrypt(String plainText) throws UnsupportedEncodingException {
        // 将返回的hash值转换成16进制字符串
        String resultHex = "";
        // 将字符串转换成byte数组
        byte[] srcData = plainText.getBytes(DEFAULT_ENCODING);
        // hash
        byte[] resultHash = hash(srcData);
        // 将返回的hash值转换成16进制字符串
        resultHex = ByteUtils.toHexString(resultHash);
        return resultHex;
    }

    /**
     * 返回长度为32的byte数组
     * @explain 生成对应的hash值
     * @param srcData
     * @return
     */
    public static byte[] hash(byte[] srcData) {
        SM3Digest digest = new SM3Digest();
        digest.update(srcData, 0, srcData.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        return hash;
    }

    /**
     * 验签
     * @param plainText 明文
     * @param sm3HexStr sm3 加密后字符串
     * @return
     * @throws UnsupportedEncodingException
     */
    public static boolean verify(String plainText, String sm3HexStr) throws UnsupportedEncodingException {
        byte[] plainData = plainText.getBytes(DEFAULT_ENCODING);
        byte[] sm3Hash = ByteUtils.fromHexString(sm3HexStr);
        byte[] plainHash = hash(plainData);
        return Arrays.equals(plainHash, sm3Hash);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String idCard = "430224199609107215";
        String sm3Secret = encrypt(idCard);
        log.info("sm3Secret：{}", sm3Secret);
        log.info("verify：{}", verify(idCard, sm3Secret));
    }

}

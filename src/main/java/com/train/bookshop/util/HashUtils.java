package com.train.bookshop.util;

import java.nio.charset.Charset;

import com.google.common.hash.Hashing;

/**
 * MD5加密
 * 
 * @author liuj-s
 */
public class HashUtils {

    public static String md5(CharSequence input) {
        return md5(input, Charset.forName("utf8"));
    }

    public static String md5(CharSequence input, Charset charset) {
        return Hashing.md5().hashString(input, charset).toString();
    }

    public static String sha256(CharSequence input) {
        return sha256(input, Charset.forName("utf8"));
    }

    public static String sha256(CharSequence input, Charset charset) {
        return Hashing.sha256().hashString(input, charset).toString();
    }
}

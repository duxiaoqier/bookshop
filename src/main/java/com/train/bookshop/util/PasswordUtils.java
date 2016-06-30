package com.train.bookshop.util;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.emptyToNull;

import org.apache.commons.lang.RandomStringUtils;

/**
 * 密码加密
 * 
 * @author liuj-s
 */
public class PasswordUtils {

    public static String encodeLegacyPassword(String password, String salt) {
        checkNotNull(emptyToNull(password), "password is empty");
        checkNotNull(emptyToNull(salt), "salt is empty");

        return HashUtils.md5(salt + password);
    }

    public static boolean isLegacyPasswordValid(String hashed, String origin, String salt) {
        return hashed != null && hashed.equalsIgnoreCase(encodeLegacyPassword(origin, salt));
    }

    public static String generateSalt() {
        return RandomStringUtils.randomAlphanumeric(16);
    }

    // public static String encodePassword(long id, String password, String salt) {
    // checkNotNull(emptyToNull(password), "password is empty");
    // checkNotNull(emptyToNull(salt), "salt is empty");
    //
    // return HashUtils.sha256(salt + password + id);
    // }

    // public static boolean isPasswordValid(String hashed, long id, String password, String salt) {
    // return hashed != null && !StringUtils.isBlank(salt)
    // && hashed.equalsIgnoreCase(encodePassword(id, password, salt));
    // }

    // reset password
    public static void main(String[] args) {

        String salt = generateSalt();
        System.out.println(salt);
        String encodePassword = encodeLegacyPassword("123456", salt);
        System.out.println(encodePassword);

        // String str = HashUtils.md5("892020949@qq.com");
        // System.out.println(str.substring(0, 10));
    }

}

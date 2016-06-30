/**
 * 
 */
package com.train.bookshop.exception;

/**
 * 登录异常
 * 
 * @Created by liuj-s on 2016-06-03.
 */
public class LoginException extends BookShopRuntimeException {

    private static final long serialVersionUID = 2452935565072927240L;

    private final static String CODE_LOGIN_EXCEPTION = "login.exception";

    public LoginException() {
        super(CODE_LOGIN_EXCEPTION);
    }

    public LoginException(String message) {
        super(CODE_LOGIN_EXCEPTION, message);
    }

    public LoginException(String message, Throwable cause) {
        super(CODE_LOGIN_EXCEPTION, message, cause);
    }
}

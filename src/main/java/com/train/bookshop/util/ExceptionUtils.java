package com.train.bookshop.util;

import com.train.bookshop.exception.BookShopRuntimeException;

public class ExceptionUtils {

    // 通用错误码
    private static final String CODE_SYSTEM_ERROR     = "system.error";
    private static final String CODE_ILLEGAL_ARGUMENT = "illegal.argument";

    /**
     * 根据Exception获取code
     * 
     * @param exception
     * @return
     */
    public static String getCode(Exception exception) {
        if (exception instanceof BookShopRuntimeException) {
            BookShopRuntimeException e = (BookShopRuntimeException) exception;
            return e.getCode();
        }
        if (exception instanceof IllegalArgumentException) {
            return CODE_ILLEGAL_ARGUMENT;
        }
        return CODE_SYSTEM_ERROR;
    }

    /**
     * 根据Exception获取message
     * 
     * @param exception
     * @return
     */
    public static String getMessage(Exception exception) {
        return exception.getMessage() == null ? exception.getClass().getName() : exception.getMessage();
    }
}

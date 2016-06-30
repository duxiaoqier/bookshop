package com.train.bookshop.exception;

public class BookShopRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 3960592581471439923L;

    private String code;

    public BookShopRuntimeException() {
        new BookShopRuntimeException(null);
    }

    public BookShopRuntimeException(String code) {
        super();
        setCode(code);
    }

    public BookShopRuntimeException(String code, String message) {
        super(message);
        setCode(code);
    }

    public BookShopRuntimeException(String code, String message, Throwable cause) {
        super(message, cause);
        setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

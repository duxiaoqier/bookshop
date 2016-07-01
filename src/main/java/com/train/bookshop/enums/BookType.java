package com.train.bookshop.enums;

public enum BookType {

    COMPUTER((byte) 1, "computer"),

    TOOLS((byte) 2, "tools"),

    NOVEL((byte) 3, "novel");

    private byte value;
    private String name;

    public byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    private BookType(byte value, String name) {
        this.value = value;
        this.name = name;
    }

    public static BookType of(String name) {
        for (BookType e : BookType.values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return null;
    }
}

package com.train.bookshop.enums;

public enum BookType {

    PROGRAM((byte) 1, "编程图书"),

    TOOLS((byte) 2, "工具书"),

    NOVEL((byte) 3, "小说"),

    ALL((byte) -99, "全部种类");

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

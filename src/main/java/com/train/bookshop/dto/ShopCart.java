package com.train.bookshop.dto;

public class ShopCart {

    private String cartId;

    private Long bookId;

    private Integer count;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId == null ? null : cartId.trim();
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ShopCart [cartId=" + cartId + ", bookId=" + bookId + ", count=" + count + "]";
    }
}

package com.train.bookshop.dto;

import java.util.List;

public class ShopCartDetails {

    private String cartId;

    private List<Book> books;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Long getSumPrice() {
        long sumPrice = 0l;
        for (Book b : books) {
            sumPrice += b.getPrice() * b.getCount();
        }
        return sumPrice;
    }

    @Override
    public String toString() {
        return "ShopCartDetails [cartId=" + cartId + ", books=" + books + "]";
    }

}

package com.train.bookshop.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.train.bookshop.dto.ShopCart;

public interface ShopCartMapper {

    int insert(ShopCart record);

    List<ShopCart> selectByCartId(String cartId);

    ShopCart selectByConditions(@Param("cartId") String cartId, @Param("bookId") Long bookId);

    int update(ShopCart record);

    int deleteByConditions(@Param("cartId") String cartId, @Param("bookId") Long bookId);
}

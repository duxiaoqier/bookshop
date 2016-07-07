package com.train.bookshop.service;

import com.train.bookshop.bean.page.Page;
import com.train.bookshop.dto.ShopCartDetails;

/**
 * 购买服务
 * 
 * @author dup
 */
public interface ShopService {

    /**
     * 加入购物车
     * 
     * @param cartId
     * @param longIds
     * @return
     */
    String addToCart(String cartId, Long[] bookIds);

    /**
     * 根据sessionId查询购物车书籍列表
     * 
     * @param id
     * @param page
     * @return
     */
    ShopCartDetails queryByCartId(String id, Page page);

    /**
     * 从购物车删除选中书籍
     * 
     * @param cartId
     * @param bookIds
     * @return
     */
    String deleteFromCart(String cartId, Long[] bookIds);

}

package com.train.bookshop.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.train.bookshop.bean.page.Page;
import com.train.bookshop.dao.mapper.BookMapper;
import com.train.bookshop.dao.mapper.ShopCartMapper;
import com.train.bookshop.dto.Book;
import com.train.bookshop.dto.ShopCart;
import com.train.bookshop.dto.ShopCartDetails;
import com.train.bookshop.service.ShopService;

/**
 * @author dup
 */
@Service("shopService")
public class ShopServiceImpl implements ShopService {

    private static final Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Autowired
    private ShopCartMapper shopCartMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public String addToCart(String cartId, Long[] bookIds) {
        Assert.notNull(cartId, "shop cart id must not null!");

        logger.info("add to shop cart, cart id:[{}], bookIds:[{}]", cartId, bookIds.toString());
        // 购物车添加书籍
        for (Long bookId : bookIds) {
            // 检查书剩余数量是否为0
            Book b = bookMapper.selectByPrimaryKey(bookId);
            if (b == null || b.getCount() <= 0) {
                return MessageFormat.format("对不起，{0}库存不足", b.getName());
            }
            // 根据购物车id和书籍id查找记录
            ShopCart sc = shopCartMapper.selectByConditions(cartId, bookId);
            if (sc != null) {
                // 如果找到了记录，就将相应记录的数量+1
                sc.setCount(sc.getCount() + 1);
                shopCartMapper.update(sc);

            } else {
                // 如果找不到记录，就在购物表增加一条书籍信息记录
                ShopCart shopCart = new ShopCart();
                shopCart.setBookId(bookId);
                shopCart.setCartId(cartId);
                shopCart.setCount(1);
                shopCartMapper.insert(shopCart);
            }
        }
        return "ok";
    }

    @Override
    public ShopCartDetails queryByCartId(String cartId, Page page) {
        List<ShopCart> shopcart = shopCartMapper.selectByCartId(cartId);
        List<Book> books = new ArrayList<>();
        for (ShopCart sc : shopcart) {
            Book b = bookMapper.selectByPrimaryKey(sc.getBookId());
            b.setCount(sc.getCount());
            books.add(b);
        }
        ShopCartDetails scd = new ShopCartDetails();
        scd.setCartId(cartId);
        scd.setBooks(books);
        return scd;
    }

    @Override
    public String deleteFromCart(String cartId, Long[] bookIds) {
        Assert.notNull(cartId, "cart id must not null.");

        for (Long bookId : bookIds) {
            logger.info("delete from shop cart. cartId:[{}], bookId:[{}]", cartId, bookId);
            shopCartMapper.deleteByConditions(cartId, bookId);
        }
        return "ok";
    }

}

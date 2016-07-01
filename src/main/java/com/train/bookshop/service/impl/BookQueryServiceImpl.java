package com.train.bookshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.train.bookshop.bean.page.Page;
import com.train.bookshop.bean.page.PagedList;
import com.train.bookshop.dao.mapper.BookMapper;
import com.train.bookshop.dto.Book;
import com.train.bookshop.service.BookQueryService;

@Service("bookQueryService")
public class BookQueryServiceImpl implements BookQueryService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public PagedList<Book> queryByConditions(Long id, String name, Byte type, Page page) {
        Assert.notNull(page, "page must not be null.");

        List<Book> list = bookMapper.selectByConditions(id, name, type);
        page.setTotalCount(list.size());

        return new PagedList<Book>(list, page);
    }

}

package com.train.bookshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.train.bookshop.dao.mapper.BookMapper;
import com.train.bookshop.dto.Book;
import com.train.bookshop.enums.BookType;
import com.train.bookshop.service.BookQueryService;

@Service("bookQueryService")
public class BookQueryServiceImpl implements BookQueryService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> queryByConditions(Long id, String name, String type) {
        Byte bookTypeByte;
        if (BookType.of(type) == null) {
            bookTypeByte = null;
        } else {
            bookTypeByte = BookType.valueOf(type).getValue();
        }
        return bookMapper.selectByConditions(id, name, bookTypeByte);
    }

}

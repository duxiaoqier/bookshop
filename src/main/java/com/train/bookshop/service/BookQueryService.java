package com.train.bookshop.service;

import java.util.List;

import com.train.bookshop.dto.Book;

/**
 * @author dup
 */
public interface BookQueryService {

    List<Book> queryByConditions(Long id, String name, String type);

}

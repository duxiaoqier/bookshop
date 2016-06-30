package com.train.bookshop.service;

import com.train.bookshop.bean.page.PagedList;
import com.train.bookshop.dto.Book;

/**
 * @author dup
 */
public interface BookQueryService {

    PagedList<Book> queryByConditions(Long id, String name, String type);

}

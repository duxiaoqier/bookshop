package com.train.bookshop.service;

import com.train.bookshop.bean.page.Page;
import com.train.bookshop.bean.page.PagedList;
import com.train.bookshop.dto.Book;

/**
 * @author dup
 */
public interface BookService {

    /**
     * 查询展示列表
     * 
     * @param id
     * @param name
     * @param type
     * @param page
     * @return
     */
    PagedList<Book> queryByConditions(Long id, String name, Byte type, Page page);

    /**
     * 删除书籍
     * 
     * @param bookIds
     */
    void delete(Long[] bookIds);

    /**
     * 查询指定ID书籍信息
     * 
     * @param bookId
     * @return
     */
    Book queryByPrimaryKey(Long bookId);

    /**
     * 更新书籍信息，返回报错信息
     * 
     * @param book
     * @return
     */
    String update(Book book);

    /**
     * 添加书籍信息，返回报错信息
     * 
     * @param book
     * @return
     */
    String add(Book book);

}

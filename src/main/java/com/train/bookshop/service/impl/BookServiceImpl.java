package com.train.bookshop.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.train.bookshop.bean.page.Page;
import com.train.bookshop.bean.page.PagedList;
import com.train.bookshop.dao.mapper.BookMapper;
import com.train.bookshop.dto.Book;
import com.train.bookshop.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookMapper bookMapper;

    @Override
    public PagedList<Book> queryByConditions(Long id, String name, Byte type, Page page) {
        Assert.notNull(page, "page must not be null.");

        logger.info("query books by conditions, id:[{}], name[{}], type[{}]", id, name, type);
        List<Book> list = bookMapper.selectByConditions(id, name, type);
        page.setTotalCount(list.size());

        return new PagedList<Book>(list, page);
    }

    @Override
    public void delete(Long[] bookIds) {
        logger.info("delete books by ids:[{}]", bookIds.toString());

        for (Long bookId : bookIds) {
            bookMapper.deleteByPrimaryKey(bookId);
        }
    }

    @Override
    public Book queryByPrimaryKey(Long bookId) {
        Assert.notNull(bookId);

        return bookMapper.selectByPrimaryKey(bookId);
    }

    @Override
    public String update(Book book) {
        if (book == null) {
            return "更新失败，书籍信息不能为空!";
        }
        if (book.getId() == null) {
            return "更新失败，书籍ID不能为空!";
        }
        if (book.getName() == null) {
            return "更新失败，书籍名称不能为空!";
        }
        if (book.getType() == null) {
            return "更新失败，书籍类型不能为空!";
        }
        if (book.getPrice() == null) {
            return "更新失败，书籍价格不能为空!";
        }

        logger.info("update book:[{}]", book.toString());
        bookMapper.updateByPrimaryKey(book);
        return "更新成功！";
    }

    @Override
    public String add(Book book) {
        if (book == null) {
            return "添加失败，书籍信息不能为空!";
        }
        if (book.getId() == null) {
            return "添加失败，书籍ID不能为空!";
        }
        if (book.getName() == null) {
            return "添加失败，书籍名称不能为空!";
        }
        if (book.getType() == null) {
            return "添加失败，书籍类型不能为空!";
        }
        if (book.getPrice() == null) {
            return "添加失败，书籍价格不能为空!";
        }

        logger.info("insert book:[{}]", book.toString());
        bookMapper.insert(book);
        return "添加成功！";
    }

}

package com.train.bookshop.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.train.bookshop.dto.Book;

public interface BookMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> selectByConditions(@Param("id") Long id, @Param("name") String name, @Param("type") Byte type);

}

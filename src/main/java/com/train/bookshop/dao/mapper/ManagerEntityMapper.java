package com.train.bookshop.dao.mapper;

import java.util.List;

import com.train.bookshop.dto.ManagerEntity;

/**
 * 用户管理信息
 * 
 * @Created by liuj-s on 2016-06-02.
 */
public interface ManagerEntityMapper {

    int insert(ManagerEntity managerEntity);

    List<ManagerEntity> selectByLoginName(String loginName);

    int deleteByPrimaryKey(String loginName);

    int insertSelective(ManagerEntity record);

    ManagerEntity selectByPrimaryKey(String loginName);

    int updateByPrimaryKeySelective(ManagerEntity record);

    int updateByPrimaryKey(ManagerEntity record);
}
